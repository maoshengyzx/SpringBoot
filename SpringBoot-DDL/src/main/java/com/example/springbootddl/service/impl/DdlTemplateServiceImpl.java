package com.example.springbootddl.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import com.example.springbootddl.db_enum.DbEnum;
import com.example.springbootddl.db_enum.IndexImplementation;
import com.example.springbootddl.db_enum.mysql.MySQLIndexEnum;
import com.example.springbootddl.db_enum.mysql.MySQLIndexImplementation;
import com.example.springbootddl.entity.DdlTemplate;
import com.example.springbootddl.entity.module.mysql.MySQLField;
import com.example.springbootddl.entity.module.mysql.MySQLIndex;
import com.example.springbootddl.entity.module.mysql.MySQLTable;
import com.example.springbootddl.entity.module.oracle.OracleField;
import com.example.springbootddl.entity.module.oracle.OracleIndex;
import com.example.springbootddl.entity.module.oracle.OracleTable;
import com.example.springbootddl.entity.module.postgresql.PostgresqlField;
import com.example.springbootddl.entity.module.postgresql.PostgresqlTable;
import com.example.springbootddl.mapper.DdlTemplateMapper;
import com.example.springbootddl.service.DdlTemplateService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;


/**
 * ddll模板表(DdlTemplate)表服务实现类
 *
 * @author makejava
 * @since 2025-06-21 10:24:03
 */
@Service("ddlTemplateService")
@Slf4j
public class DdlTemplateServiceImpl implements DdlTemplateService {
    @Resource
    private DdlTemplateMapper ddlTemplateMapper;

    @Resource
    private GroupTemplate groupTemplate;

    /**
     * 创建DDL语句
     */
    @Override
    public void createDdl(String version, String dbType) {
        DbEnum dbEnum = DbEnum.fromString(dbType);
        Assert.notNull(dbType, "数据库类型不支持");

        switch (dbEnum) {
            case MYSQL:
                generateMysqlDdl(version, dbType, buildMySQLData());
                break;
            case ORACLE:
                generateOracleDdl(version, dbType, buildOracleData());
                break;
            case POSTGRESQL:
                generatePostgresqlDdl(version, dbType, buildPostgresqlData());
                break;
        }
    }

    /**
     * 这里只是模拟一下，实际情况应该是从数据库中查询的
     *
     * @return
     */
    private Map<String, Object> buildMySQLData() {
        MySQLTable tableVO = MySQLTable.builder().tableCode("t_user").tableComment("用户表").build();
        List<MySQLField> fieldVOList = buildMySQLFieldList();
        // 创建索引
        List<MySQLIndex> indexList = List.of(
                MySQLIndex.builder().indexName("idx_name").indexType(MySQLIndexEnum.INDEX.name()).indexColumns(CollUtil.join(List.of("name"), ", "))
                        .indexImplementation(MySQLIndexImplementation.BTREE.name()).build(),
                MySQLIndex.builder().indexName("idx_age_email").indexType(MySQLIndexEnum.COMBINED.name()).indexColumns(CollUtil.join(List.of("age", "email"), ", "))
                        .indexImplementation(MySQLIndexImplementation.BTREE.name()).indexComment("组合索引").build(),
                MySQLIndex.builder().indexName("idx_address").indexType(MySQLIndexEnum.FULLTEXT.name()).indexColumns(CollUtil.join(List.of("address"), ", "))
                        .indexImplementation(MySQLIndexImplementation.BTREE.name()).indexComment("全文索引").build(),
                MySQLIndex.builder().indexName("idx_create_time").indexType(MySQLIndexEnum.UNIQUE.name()).indexColumns(CollUtil.join(List.of("create_time"), ", "))
                        .indexImplementation(MySQLIndexImplementation.BTREE.name()).indexComment("唯一索引").build()
        );

        return Map.of("table", tableVO, "fieldList", fieldVOList, "primaryKey", "id", "indexList", indexList);
    }

    private Map<String, Object> buildOracleData() {
        OracleTable tableVO = OracleTable.builder().tableCode("t_user").tableComment("用户表").build();
        List<OracleField> fieldVOList =  buildOracleFieldList();

        List<OracleIndex> indexList = List.of(
                OracleIndex.builder().indexName("idx_name").indexType(null).indexColumns(CollUtil.join(List.of("name"), ", "))
                        .build());
        return Map.of("table", tableVO, "fieldList", fieldVOList, "primaryKey", "id", "indexList", indexList);
    }

    private Map<String, Object> buildPostgresqlData() {
        PostgresqlTable tableVO = PostgresqlTable.builder().tableCode("t_user").tableComment("用户表").build();
        List<PostgresqlField> fieldVOList = buildPostgresqlFieldList();
        return Map.of("table", tableVO, "fieldList", fieldVOList, "primaryKey", "id");
    }


    private void generateMysqlDdl(String version, String dbType, Map<String, Object> data) {
        try {
            // 可以考虑将模版文件缓存
            Template template = groupTemplate.getTemplate(queryByUnique(version, dbType).getTemplate());
            template.binding(data);
            String render = template.render();
            log.info("生成MysqlDDL语句成功");
            FileUtil.writeUtf8String(render, "D:/ddl.sql");
        } catch (Exception e) {
            throw new RuntimeException("生成DDL语句失败", e);
        }
    }

    private void generateOracleDdl(String version, String dbType, Map<String, Object> data) {
        try {
            // 可以考虑将模版文件缓存
            Template template = groupTemplate.getTemplate(queryByUnique(version, dbType).getTemplate());
            template.binding(data);
            String render = template.render();
            log.info("生成OracleDDL语句成功");
            FileUtil.writeUtf8String(render, "D:/ddl.sql");
        } catch (Exception e) {
            throw new RuntimeException("生成DDL语句失败", e);
        }
    }

    private void generatePostgresqlDdl(String version, String dbType, Map<String, Object> data) {
        // 可以考虑将模版文件缓存
        Template template = groupTemplate.getTemplate(queryByUnique(version, dbType).getTemplate());
        template.binding(data);
        String render = template.render();
        log.info("生成PgSQLDDL语句成功");
        FileUtil.writeUtf8String(render, "D:/ddl.sql");
    }


    private List<MySQLField> buildMySQLFieldList() {
        return List.of(
                MySQLField.builder().code("id").comment("主键").type("int").len(11).scale(0.0).autoIncrement(true).notNull(true).build(),
                MySQLField.builder().code("name").comment("姓名").type("varchar").len(50).scale(0.0).autoIncrement(false).defaultValue("").notNull(true).build(),
                MySQLField.builder().code("age").comment("年龄").type("int").len(11).scale(0.0).autoIncrement(false).notNull(true).build(),
                MySQLField.builder().code("email").comment("邮箱").type("varchar").len(50).scale(0.0).autoIncrement(false).defaultValue("").notNull(true).build(),
                MySQLField.builder().code("create_time").comment("创建时间").type("datetime").len(0).scale(0.0).autoIncrement(false).notNull(true).build(),
                MySQLField.builder().code("update_time").comment("更新时间").type("datetime").len(0).scale(0.0).autoIncrement(false).notNull(true).build(),

                // 新增字段如下：
                MySQLField.builder().code("gender").comment("性别: 1男 2女 3未知").type("tinyint").len(4).scale(0.0).autoIncrement(false).defaultValue("3").notNull(true).build(),
                MySQLField.builder().code("phone").comment("手机号").type("char").len(11).scale(0.0).autoIncrement(false).defaultValue("").notNull(true).build(),
                MySQLField.builder().code("address").comment("地址").type("varchar").len(255).scale(0.0).autoIncrement(false).defaultValue("").notNull(false).build(),
                MySQLField.builder().code("status").comment("状态: 1启用 0禁用").type("tinyint").len(4).scale(0.0).autoIncrement(false).defaultValue("1").notNull(true).build(),
                MySQLField.builder().code("last_login_time").comment("最后登录时间").type("datetime").len(0).scale(0.0).autoIncrement(false).notNull(false).build(),
                MySQLField.builder().code("avatar_url").comment("头像URL").type("varchar").len(512).scale(0.0).autoIncrement(false).defaultValue("").notNull(false).build(),
                MySQLField.builder().code("is_deleted").comment("是否删除: 1是 0否").type("tinyint").len(4).scale(0.0).autoIncrement(false).defaultValue("0").notNull(true).build()
        );
    }


    private List<OracleField> buildOracleFieldList() {
        return List.of(
                OracleField.builder().code("id").comment("主键").type("NUMBER").len(11).scale(0.0).autoIncrement(true).notNull(true).build(),
                OracleField.builder().code("name").comment("姓名").type("varchar").len(50).scale(0.0).autoIncrement(false).defaultValue("").notNull(true).build(),
                OracleField.builder().code("age").comment("年龄").type("NUMBER").len(11).scale(0.0).autoIncrement(false).notNull(true).build(),
                OracleField.builder().code("email").comment("邮箱").type("varchar").len(50).scale(0.0).autoIncrement(false).defaultValue("").notNull(true).build(),
                OracleField.builder().code("create_time").comment("创建时间").type("date").len(0).scale(0.0).autoIncrement(false).notNull(true).notNull(true).build(),

                // 新增字段如下：
                OracleField.builder().code("gender").comment("性别: 1男 2女 3未知").type("NUMBER").len(4).scale(0.0).autoIncrement(false).defaultValue("3").notNull(true).build(),
                OracleField.builder().code("phone").comment("手机号").type("char").len(11).scale(0.0).autoIncrement(false).defaultValue("").notNull(true).build(),
                OracleField.builder().code("address").comment("地址").type("varchar").len(255).scale(0.0).autoIncrement(false).defaultValue("").notNull(false).build(),
                OracleField.builder().code("status").comment("状态: 1启用 0禁用").type("NUMBER").len(4).scale(0.0).autoIncrement(false).defaultValue("1").notNull(true).build(),
                OracleField.builder().code("last_login_time").comment("最后登录时间").type("date").len(0).scale(0.0).autoIncrement(false).notNull(false).build(),
                OracleField.builder().code("avatar_url").comment("头像URL").type("varchar").len(512).scale(0.0).autoIncrement(false).defaultValue("").notNull(false).build(),
                OracleField.builder().code("is_deleted").comment("是否删除: 1是 0否").type("NUMBER").len(4).scale(0.0).autoIncrement(false).defaultValue("0").notNull(true).build()
        );
    }

    private List<PostgresqlField> buildPostgresqlFieldList() {
        return List.of(
                PostgresqlField.builder().code("id").comment("主键").type("integer").autoIncrement(true).notNull(true).build(),
                PostgresqlField.builder().code("name").comment("姓名").type("varchar").len(50).scale(0.0).autoIncrement(false).defaultValue("").notNull(true).build(),
                PostgresqlField.builder().code("age").comment("年龄").type("int").autoIncrement(false).notNull(true).build(),
                PostgresqlField.builder().code("email").comment("邮箱").type("varchar").len(50).scale(0.0).autoIncrement(false).defaultValue("").notNull(true).build(),
                PostgresqlField.builder().code("create_time").comment("创建时间").type("timestamp").len(0).scale(0.0).autoIncrement(false).notNull(true).notNull(true).build(),
                // 新增字段如下：
                PostgresqlField.builder().code("gender").comment("性别: 1男 2女 3未知").type("integer").autoIncrement(false).defaultValue("3").notNull(true).build(),
                PostgresqlField.builder().code("phone").comment("手机号").type("char").len(11).scale(0.0).autoIncrement(false).defaultValue("").notNull(true).build(),
                PostgresqlField.builder().code("address").comment("地址").type("varchar").len(255).scale(0.0).autoIncrement(false).defaultValue("").notNull(false).build(),
                PostgresqlField.builder().code("status").comment("状态: 1启用 0禁用").type("integer").autoIncrement(false).defaultValue("1").notNull(true).build(),
                PostgresqlField.builder().code("last_login_time").comment("最后登录时间").type("timestamp").len(0).scale(0.0).autoIncrement(false).notNull(false).build(),
                PostgresqlField.builder().code("avatar_url").comment("头像URL").type("varchar").len(512).scale(0.0).autoIncrement(false).defaultValue("").notNull(false).build(),
                PostgresqlField.builder().code("is_deleted").comment("是否删除: 1是 0否").type("integer").autoIncrement(false).defaultValue("0").notNull(true).build()
        );
    }

    @Override
    public DdlTemplate queryByUnique(String version, String dbType) {
        return this.ddlTemplateMapper.queryByUnique(version, dbType);
    }

}

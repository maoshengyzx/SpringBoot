package com.example.springbootddl.util;

import com.example.springbootddl.db_enum.DbEnum;
import com.example.springbootddl.entity.module.mysql.MySQLTable;

/**
 * ClassName: DdlGenerateUtil
 * Package: com.example.springbootddl.util
 * Description:
 * dd
 *
 * @Author ms
 * @Create 2025/6/21 10:40
 * @Version 1.0
 */
public class DdlGenerateUtil {


    /**
     * 生产表ddl语句
     *
     * @param dbType
     * @param tableVO
     * @param ddlTemplate
     */
    public String generateDdl(String dbType, MySQLTable tableVO, String ddlTemplate) {
        DbEnum dbEnum = getDbEnum(dbType);
        switch (dbEnum) {
            case MYSQL:
//                return generateMysqlDdl(tableVO, ddlTemplate);
        }
        return null;
    }




    private DbEnum getDbEnum(String dbType) {
        DbEnum dbEnum = DbEnum.fromString(dbType);
        if (dbEnum == null) {
            throw new IllegalArgumentException("Unsupported database type: " + dbType);
        }
        return dbEnum;
    }



}

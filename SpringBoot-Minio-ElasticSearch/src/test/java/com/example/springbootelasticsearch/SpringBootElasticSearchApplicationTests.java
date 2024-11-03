package com.example.springbootelasticsearch;

import com.example.springbootelasticsearch.entity.Item;
import com.example.springbootelasticsearch.repostory.ItemRepository;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.ParsedAvg;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.multiMatchQuery;


@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringBootElasticSearchApplicationTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootElasticSearchApplicationTests.class);
    @Resource
    private ElasticsearchOperations elasticsearchOperations;

    @Resource
    private ItemRepository itemRepository;


    @Test
    public void createIndex() {
        // 获取索引操作对象
        IndexOperations indexOperations = elasticsearchOperations.indexOps(Item.class);
        // 创建索引
        if (!indexOperations.exists()) {
            indexOperations.create();
        }
        // 创建mapping映射
        Document document = indexOperations.createMapping();
        LOGGER.info("document:{}", document);
        indexOperations.putMapping(document);
    }

    @Test
    public void deleteIndex() {
        // 获取索引操作对象
        IndexOperations indexOperations = elasticsearchOperations.indexOps(Item.class);
        indexOperations.delete();
    }

    @Test
    public void insertItem() {
        Item item = new Item();
        item.setId(1L);
        item.setTitle("小米手机");
        item.setCategory("手机");
        item.setBrand("小米");
        item.setPrice(2999.00);
        item.setImages("URL_ADDRESS");
        itemRepository.save(item);
    }


    @Test
    public void insertBatchItem() {
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(1L, "小米手机8", "手机", "小米", 3299.00, "http://image.leyou.com/13123.jpg"));
        items.add(new Item(2L, "坚果手机R1", "手机", "锤子", 3699.00, "http://image.leyou.com/13123.jpg"));
        items.add(new Item(3L, "华为META10", "手机", "华为", 4499.00, "http://image.leyou.com/13123.jpg"));
        items.add(new Item(4L, "小米Mix2S", "手机", "小米", 4299.00, "http://image.leyou.com/13123.jpg"));
        items.add(new Item(5L, "荣耀V10", "手机", "华为", 2799.00, "http://image.leyou.com/13123.jpg"));
        itemRepository.saveAll(items);
    }


    @Test
    public void updateItem() {
        // 如果字段id相同，则新增即修改
        itemRepository.save(new Item(1L, "大手机", "手机", "小米", 3299.00, "http://image.leyou.com/13123.jpg"));
    }

    @Test
    public void deleteItem() {
        itemRepository.deleteById(1L);
    }


    @Test
    public void findAll() {
        Iterable<Item> all = itemRepository.findAll();
        all.forEach(System.out::println);

        System.out.println("--------------------");

        // 查询全部。并按照价格降序
        Iterable<Item> all1 = itemRepository.findAllByOrderByPriceDesc();
        all1.forEach(System.out::println);
    }

    @Test
    public void findByPriceBetween() {
        Iterable<Item> all = itemRepository.findByPriceBetween(3000.00, 4000.00);
        all.forEach(System.out::println);
    }

    /**
     * 自定义查询  根据title查询
     */
    @Test
    public void findByTitle() {
        Criteria criteria = new Criteria("title").contains("小米");
        Query query = new CriteriaQuery(criteria);

        // 自定义查询  使用elasticsearchOperations
        // SearchHits 是Spring为ES查询结果封装的一个对象
        SearchHits<Item> searchHits1 = elasticsearchOperations.search(query, Item.class);

        // 获取查询结果
        searchHits1.forEach(System.out::println);

        System.out.println("---------------------");

        // 也可以执行分页查询
        query.setPageable(PageRequest.of(0, 1));   // 第一页，每页1条
        SearchHits<Item> searchHits2 = elasticsearchOperations.search(query, Item.class);
        searchHits2.forEach(System.out::println);
    }

    @Test
    public void findTitleByHighlightCount() {
        // 创建查询构建器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加标题包含特定关键词的查询条件
        queryBuilder.withQuery(multiMatchQuery("小米", "title", "brand"));
        // 设置高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();

        // 通过循环逐个添加字段
        String[] fields = {"title", "brand"};
        for (String field : fields) {
            highlightBuilder.field(field);
        }

        highlightBuilder.preTags("<em>");
        highlightBuilder.postTags("</em>");
        highlightBuilder.order();
        queryBuilder.withHighlightBuilder(highlightBuilder);
        // 构建查询
        Query query = queryBuilder.build();
        // 执行查询并获取结果
        SearchHits<Item> searchHits = elasticsearchOperations.search(query, Item.class);
        searchHits.forEach(System.out::println);
    }


    /**
     * 根据价格降序
     */
    @Test
    public void findPriceByDesc() {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 创建 FieldSortBuilder 对价格字段进行降序排序
        SortBuilder<FieldSortBuilder> sortBuilder = new FieldSortBuilder("price").order(SortOrder.DESC);
        queryBuilder.withSort(sortBuilder);

        // 如果需要对多个字段排序，则再创还能一个SortBuilder，比如根据销量升序
        // 创建销量升序的SortBuilder
//        SortBuilder<FieldSortBuilder> salesSortBuilder = new FieldSortBuilder("sales").order(SortOrder.ASC);
//        queryBuilder.withSort(salesSortBuilder);

        NativeSearchQuery nativeSearchQuery = queryBuilder.build();
        nativeSearchQuery.setPageable(PageRequest.of(0, 10));
        SearchHits<Item> searchHits = elasticsearchOperations.search(nativeSearchQuery, Item.class);
        searchHits.forEach(System.out::println);
    }


    /**
     * 根据品牌创建桶，求桶的平均价格
     */
    @Test
    public void findBrandByAggregation() {
        // 创建搜索查询构造器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 创建品牌字段的分桶聚合
        TermsAggregationBuilder brandTermsBuilder = AggregationBuilders.terms("brandTerms").field("brand");
        // 创建平均价格的聚合
        AvgAggregationBuilder avgPriceBuilder = AggregationBuilders.avg("avgPrice").field("price");
        brandTermsBuilder.subAggregation(avgPriceBuilder);

        queryBuilder.addAggregation(brandTermsBuilder);

        // 构建查询
        NativeSearchQuery searchQuery = queryBuilder.build();


        PageRequest pageRequest = PageRequest.of(0, 10); // 第一页，每页10条
        searchQuery.setPageable(pageRequest);

        // 执行查询并获取聚合结果
        SearchHits<Item> searchHits = elasticsearchOperations.search(searchQuery, Item.class);

        ArrayList<Item> items = new ArrayList<>();
        searchHits.forEach(item -> {
            Item item1 = new Item();
            BeanUtils.copyProperties(item, item1);
            items.add(item1);
        });
        AggregatedPageImpl<Item> aggregatedPage = new AggregatedPageImpl<>(items, pageRequest, searchHits.getTotalHits(), searchHits.getAggregations());

        // 获取品牌分桶聚合结果
        // 因为是利用String类型字段来进行的term聚合，所以结果要强转为StringTerm类型
        ParsedStringTerms brandTerms = aggregatedPage.getAggregations().get("brandTerms");
        List<? extends Terms.Bucket> buckets = brandTerms.getBuckets();
        for (Terms.Bucket bucket : buckets) {
            // 获取桶中的Key，即品牌名称
            System.out.println(bucket.getKeyAsString());
            // 获取桶中的文档数量  即分组后的个数
            System.out.println(bucket.getDocCount());
            // 获取平均价格
            ParsedAvg avgPrice = bucket.getAggregations().get("avgPrice");
            System.out.println("avgPrice :" + avgPrice.getValue());
        }
    }

}

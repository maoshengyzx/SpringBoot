package com.example.springbootelasticsearch.repostory;


import com.example.springbootelasticsearch.entity.Item;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ItemRepository extends PagingAndSortingRepository<Item, Long> {

    /**
     * 查询全部按照价格降序
     * @return
     */
    Iterable<Item> findAllByOrderByPriceDesc();


    /**
     * 根据价格区间查询
     * @param begin
     * @param end
     * @return
     */
    Iterable<Item> findByPriceBetween(Double begin, Double end);

}

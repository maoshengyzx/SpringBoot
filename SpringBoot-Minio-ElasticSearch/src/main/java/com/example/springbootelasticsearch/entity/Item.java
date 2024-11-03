package com.example.springbootelasticsearch.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * ClassName: Item
 * Package: com.example.springbootelasticsearch.entity
 * Description:
 *
 * @Author ms
 * @Create 2024/11/2 15:06
 * @Version 1.0
 */
@Document(indexName = "item")
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    private Long id;

    @Field(type = FieldType.Text, analyzer = "ik_max_word", name = "title")
    private String title; //标题


    @Field(type = FieldType.Keyword, name = "category")
    private String category;// 分类

    @Field(type = FieldType.Text, analyzer = "ik_max_word", name = "brand",fielddata = true)
    private String brand; // 品牌

    @Field(type = FieldType.Double, name = "price")
    private Double price; // 价格

    @Field(type = FieldType.Keyword, index = false, name = "images")
    private String images; // 图片地址

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", images='" + images + '\'' +
                '}';
    }
}

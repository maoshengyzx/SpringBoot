package com.nmap4j.springbootnmap4j.pojo;

public class NmapPortInfo {


    /**
     * 产品
     */
    private String product;


    /**
     *  版本
     */
    private String version;

    public NmapPortInfo(String product, String version) {
        this.product = product;
        this.version = version;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
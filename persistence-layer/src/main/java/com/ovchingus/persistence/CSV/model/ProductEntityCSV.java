package com.ovchingus.persistence.CSV.model;

import java.util.List;

public class ProductEntityCSV {

    private Integer id;
    private String name;
    private List<ProductInfo> products;

    public List<ProductInfo> getProducts() {
        return products;
    }

    public void setProducts(List<ProductInfo> products) {
        this.products = products;
    }

    public ProductEntityCSV(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProductEntityCSV() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
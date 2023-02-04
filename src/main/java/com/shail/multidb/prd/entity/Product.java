package com.shail.multidb.prd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

    @Id
    private Integer id;

    private String name;

    private String price;

    @Column(length = 5000)
    private String description;

    private String weight;

    private String brand;

    private String onForSale;

    private String status;

    public Product() {
    }

    public Product(Integer id, String name, String price, String description, String weight, String brand, String onForSale, String status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.weight = weight;
        this.brand = brand;
        this.onForSale = onForSale;
        this.status = status;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getOnForSale() {
        return onForSale;
    }

    public void setOnForSale(String onForSale) {
        this.onForSale = onForSale;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

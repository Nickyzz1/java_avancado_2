package com.desktopapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// cria um modelo para o objeto user

@Entity
@Table(name = "tbProduct")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;

    public Long getIdProduct() {
        return idProduct;
    }

    private String nameProduct;

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String name) {
        this.nameProduct = name;
    }
    private Double priceProduct;

    public Double getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(Double price) {
        this.priceProduct = price;
    }
}

package com.example.ProductMicro;

import jakarta.persistence.*;


@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(nullable = false)
    private Double price = 0.0;
    private Boolean available;


    public Product() {}


    public Product(Long id, String name, Double price, Boolean available) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.available = available;
    }


    public Long getId() { return id; }
    public String getName() { return name; }
    public Double getPrice() { return price; }
    public Boolean getAvailable() { return available; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(Double price) { this.price = price; }
    public void setAvailable(Boolean available) { this.available = available; }

    @Override
    public String toString() {
        return "Product{id=" + id + ", name='" + name + "', price=" + price + ", available=" + available + "}";
    }

}

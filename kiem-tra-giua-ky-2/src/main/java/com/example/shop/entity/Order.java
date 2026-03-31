package com.example.shop.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double total;

    public Integer getId() { return id; }
    public Double getTotal() { return total; }

    public void setId(Integer id) { this.id = id; }
    public void setTotal(Double total) { this.total = total; }
}
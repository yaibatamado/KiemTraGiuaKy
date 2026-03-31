package com.example.shop.service;

import com.example.shop.entity.Product;
import com.example.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public Page<Product> getProducts(String keyword, Integer category, Pageable pageable) {
        return repo.search(keyword, category, pageable);
    }
}
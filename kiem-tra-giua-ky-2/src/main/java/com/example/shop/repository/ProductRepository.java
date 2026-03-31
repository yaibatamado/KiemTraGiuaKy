package com.example.shop.repository;

import com.example.shop.entity.Product;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE " +
            "(:keyword IS NULL OR p.name LIKE %:keyword%) AND " +
            "(:category IS NULL OR p.categoryId = :category)")
    Page<Product> search(
            @Param("keyword") String keyword,
            @Param("category") Integer category,
            Pageable pageable
    );
}
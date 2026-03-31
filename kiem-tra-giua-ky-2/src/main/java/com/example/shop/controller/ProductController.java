package com.example.shop.controller;

import com.example.shop.entity.Product;
import com.example.shop.repository.CategoryRepository;
import com.example.shop.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private CategoryRepository categoryRepo;

    @GetMapping("/")
    public String home(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String sort,
            Model model
    ) {

        Sort sorting = Sort.unsorted();

        if ("asc".equals(sort)) {
            sorting = Sort.by("price").ascending();
        } else if ("desc".equals(sort)) {
            sorting = Sort.by("price").descending();
        }

        Pageable pageable = PageRequest.of(page, 5, sorting);

        Page<Product> products = service.getProducts(keyword, category, pageable);

        model.addAttribute("products", products.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", products.getTotalPages());

        // 🔥 THÊM DÒNG NÀY
        model.addAttribute("categories", categoryRepo.findAll());

        return "index";
    }
}
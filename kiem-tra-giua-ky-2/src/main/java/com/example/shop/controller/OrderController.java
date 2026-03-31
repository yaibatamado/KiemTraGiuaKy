package com.example.shop.controller;

import com.example.shop.entity.Order;
import com.example.shop.repository.OrderDetailRepository;
import com.example.shop.repository.ProductRepository;
import com.example.shop.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @Autowired
    private OrderDetailRepository detailRepo;

    @Autowired
    private ProductRepository productRepo;

    @GetMapping("/checkout")
    public String checkout(Model model) {

        Order order = service.checkout();

        var details = detailRepo.findAll()
                .stream()
                .filter(d -> d.getOrderId().equals(order.getId()))
                .toList();

        model.addAttribute("order", order);
        model.addAttribute("details", details);
        model.addAttribute("productRepo", productRepo);

        return "invoice";
    }
}
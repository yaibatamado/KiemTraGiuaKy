package com.example.shop.service;

import com.example.shop.entity.*;
import com.example.shop.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private OrderDetailRepository detailRepo;

    public Order checkout() {

        double total = 0;

        for (var entry : cartService.getCart().entrySet()) {
            Product p = productRepo.findById(entry.getKey()).orElse(null);
            total += p.getPrice() * entry.getValue();
        }

        Order order = new Order();
        order.setTotal(total);
        orderRepo.save(order);

        for (var entry : cartService.getCart().entrySet()) {

            Product p = productRepo.findById(entry.getKey()).orElse(null);

            OrderDetail d = new OrderDetail();
            d.setOrderId(order.getId());
            d.setProductId(p.getId());
            d.setQuantity(entry.getValue());
            d.setPrice(p.getPrice());

            detailRepo.save(d);
        }

        cartService.clear();

        return order; // 🔥 QUAN TRỌNG
    }
}
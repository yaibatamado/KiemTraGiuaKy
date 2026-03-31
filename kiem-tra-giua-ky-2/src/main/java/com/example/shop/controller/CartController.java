package com.example.shop.controller;

import com.example.shop.entity.Product;
import com.example.shop.repository.ProductRepository;
import com.example.shop.service.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductRepository productRepo;

    @GetMapping
    public String viewCart(Model model) {

        List<Map<String, Object>> result = new ArrayList<>();
        double total = 0;

        for (var entry : cartService.getCart().entrySet()) {
            Product p = productRepo.findById(entry.getKey()).orElse(null);

            double sub = p.getPrice() * entry.getValue();
            total += sub;

            Map<String, Object> item = new HashMap<>();
            item.put("name", p.getName());
            item.put("price", p.getPrice());
            item.put("quantity", entry.getValue());
            item.put("total", sub);

            result.add(item);
        }

        model.addAttribute("cart", result);
        model.addAttribute("total", total);

        return "cart";
    }

    @GetMapping("/add/{id}")
    public String add(@PathVariable Integer id) {
        cartService.add(id);
        return "redirect:/";
    }
}
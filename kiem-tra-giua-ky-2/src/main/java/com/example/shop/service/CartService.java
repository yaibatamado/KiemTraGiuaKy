package com.example.shop.service;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CartService {

    private Map<Integer, Integer> cart = new HashMap<>();

    public void add(Integer id) {
        cart.put(id, cart.getOrDefault(id, 0) + 1);
    }

    public Map<Integer, Integer> getCart() {
        return cart;
    }

    public void clear() {
        cart.clear();
    }
}
package com.revature.repositories;

import java.util.List;

import com.revature.models.Cart;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    public List<Cart> findByUserId(int userId);
}

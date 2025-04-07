package com.example.assjava6.repository;

import com.example.assjava6.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByAccount_Username(String username);
}


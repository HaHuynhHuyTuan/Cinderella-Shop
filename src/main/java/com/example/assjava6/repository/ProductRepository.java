package com.example.assjava6.repository;

import com.example.assjava6.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product  p WHERE p.category.id =?1")
    List<Product> findByCategoryId(Long cid);
    List<Product> findAll();
}


package com.example.assjava6.service;

import com.example.assjava6.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();

    Optional<Product> findById(Long id);

    List<Product> findByCategoryId(Long cid);

    Product save(Product product);

    void deleteById(Long id);

}

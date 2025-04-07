package com.example.assjava6.service;

import com.example.assjava6.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();
    Category save(Category category);
    Optional<Category> findById(Long id); // Thay Object thành Category
    void deleteById(Long id);
}

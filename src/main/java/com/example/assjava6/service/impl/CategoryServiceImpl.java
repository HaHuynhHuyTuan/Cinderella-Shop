package com.example.assjava6.service.impl;

import com.example.assjava6.model.Category;
import com.example.assjava6.repository.CategoryRepository;
import com.example.assjava6.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        List<Category> list = categoryRepository.findAll();
        System.out.println("🔍 DEBUG: Số danh mục trong DB = " + list.size());
        return list; // ❌ Xoá dòng gọi findAll() lần 2
    }


    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }


    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }


    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }


}

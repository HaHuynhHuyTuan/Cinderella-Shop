package com.example.assjava6.controller;

import com.example.assjava6.model.Category;
import com.example.assjava6.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("cates")  // ✅ Danh mục luôn có mặt trong tất cả view
    public List<Category> getCategories() {
        List<Category> categories = categoryService.findAll();
        System.out.println("✅ DEBUG: Trả về " + categories.size() + " danh mục.");
        return categories;
    }
}


package com.example.assjava6.service.impl;

import com.example.assjava6.model.Product;
import com.example.assjava6.repository.ProductRepository;
import com.example.assjava6.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService    {
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findByCategoryId(Long cid) {
        try {
            Long categoryId = Long.parseLong(String.valueOf(cid)); // Chuyển Long -> String
            List<Product> products = productRepository.findByCategoryId(categoryId);
            System.out.println("🛠 DEBUG: Sản phẩm thuộc danh mục " + categoryId + ": " + products);
            return productRepository.findByCategoryId(categoryId);
        } catch (NumberFormatException e) {
            return List.of(); // Trả về danh sách rỗng nếu cid không hợp lệ
        }
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }


    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}

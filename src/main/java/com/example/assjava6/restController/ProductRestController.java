package com.example.assjava6.restController;

import com.example.assjava6.model.Product;
import com.example.assjava6.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/products")
public class ProductRestController {
    @Autowired
    ProductService productService;

    // API lấy tất cả sản phẩm
    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    // API lấy sản phẩm theo ID
    @GetMapping("{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") Long id) {
        Optional<Product> product = productService.findById(id);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product not found!");
        }
        return ResponseEntity.ok(product);
    }
}

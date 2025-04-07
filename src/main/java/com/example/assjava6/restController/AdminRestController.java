package com.example.assjava6.restController;

import com.example.assjava6.model.Account;
import com.example.assjava6.model.Category;
import com.example.assjava6.model.Product;
import com.example.assjava6.service.AccountService;
import com.example.assjava6.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.assjava6.service.CategoryService;

import java.util.List;


@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*") // Cho phép AngularJS gọi API từ domain khác
public class AdminRestController {

    @Autowired
    private ProductService productService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CategoryService categoryService;

    // --- PRODUCT ---
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @PostMapping("/products")
    public Product saveProduct(@RequestBody Product product) {
        return productService.save(product); // Trả về đối tượng vừa lưu
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
    }

    // --- ACCOUNT ---
    @GetMapping("/accounts")
    public List<Account> getAllAccounts() {
        return accountService.findAll();
    }

    @PostMapping("/accounts")
    public Account saveAccount(@RequestBody Account account) {
        return accountService.save(account);
    }

    @DeleteMapping("/accounts/{id}")
    public void deleteAccount(@PathVariable Long id) {
        accountService.deleteById(id);
    }

    // --- CATEGORY ---
    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryService.findAll();
    }

    @PostMapping("/categories")
    public Category saveCategory(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @DeleteMapping("/categories/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
    }
}

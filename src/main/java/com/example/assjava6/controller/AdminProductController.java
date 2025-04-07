package com.example.assjava6.controller;

import com.example.assjava6.model.Product;
import com.example.assjava6.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public String getAllProducts(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        model.addAttribute("product", new Product());  // Đảm bảo rằng product có sẵn trong model
        return "admin/products";  // Trả về trang danh sách sản phẩm
    }

    @PostMapping("/products")
    public String addOrUpdateProduct(@ModelAttribute Product product,
                                     @RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                // Lưu file ảnh vào thư mục /uploads/
                String fileName = file.getOriginalFilename();
                String uploadDir = "src/main/resources/static/uploads/";
                Path uploadPath = Paths.get(uploadDir);

                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                InputStream inputStream = file.getInputStream();
                Files.copy(inputStream, uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
                product.setImage(fileName);  // Lưu tên file vào database
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        productService.save(product);
        return "redirect:/admin/products";
    }


    @GetMapping("/products/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        Optional<Product> productOpt = productService.findById(id);
        if (productOpt.isPresent()) {
            model.addAttribute("product", productOpt.get());
        } else {
            model.addAttribute("product", new Product());  // Nếu không tìm thấy, trả về object rỗng
        }
        model.addAttribute("products", productService.findAll());
        return "admin/products";  // Hiển thị lại danh sách kèm form chỉnh sửa
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/admin/products";  // Sau khi xóa, quay lại danh sách sản phẩm
    }
}

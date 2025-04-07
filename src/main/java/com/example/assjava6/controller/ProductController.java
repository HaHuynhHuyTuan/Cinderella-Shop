package com.example.assjava6.controller;

import com.example.assjava6.model.Category;
import com.example.assjava6.model.Product;
import com.example.assjava6.service.CategoryService;
import com.example.assjava6.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/list")
    public ModelAndView list(@RequestParam(name = "cid", required = false) Long cid) {
        ModelAndView mav = new ModelAndView("product/list");

        List<Product> products = (cid != null) ? productService.findByCategoryId(cid) : productService.findAll();
        List<Category> categories = categoryService.findAll();

        mav.addObject("items", products);
        mav.addObject("cates", categories);
        mav.addObject("selectedCid", cid); // Giữ trạng thái danh mục đã chọn

        return mav;
    }

    @RequestMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView();
        Optional<Product> optionalItem = productService.findById(id);

        if (optionalItem.isPresent()) {
            mav.setViewName("product/detail");
            mav.addObject("item", optionalItem.get());
        } else {
            mav.setViewName("redirect:/product/list");
        }

        return mav;
    }
}

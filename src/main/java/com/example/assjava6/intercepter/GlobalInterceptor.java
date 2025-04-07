package com.example.assjava6.intercepter;


import com.example.assjava6.model.Category;
import com.example.assjava6.service.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Component
public class GlobalInterceptor implements HandlerInterceptor {

    @Autowired
    private CategoryService categoryService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView == null || modelAndView.getViewName() == null) {
            System.out.println("❌ modelAndView NULL hoặc không có view -> Không thêm danh mục!");
            return;
        }

        if (modelAndView.getViewName().startsWith("redirect:")) {
            System.out.println("🚀 Redirect detected -> Bỏ qua Interceptor!");
            return;
        }

        System.out.println("✅ DEBUG: Đã thêm danh mục vào model!");
        modelAndView.addObject("cates", categoryService.findAll());
    }

}


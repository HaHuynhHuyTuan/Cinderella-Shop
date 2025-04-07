package com.example.assjava6.intercepter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

public interface HanderIntercepter {
    void postHandle(HttpServletRequest request, HttpServletResponse response,
                    Object handler, ModelAndView modelAndView) throws Exception;
}

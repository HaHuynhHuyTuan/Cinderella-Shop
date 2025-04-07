package com.example.assjava6.controller;

import com.example.assjava6.model.Order;
import com.example.assjava6.model.OrderDetail;
import com.example.assjava6.repository.OrderDetailRepository;
import com.example.assjava6.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class OrderViewController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @RequestMapping("/orders/checkout")
    public String checkout(){
        return "order/checkout";
    }

    @RequestMapping("/orders/list")
    public String list(Model model) {
        // Lấy danh sách đơn hàng
        List<Order> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);  // Truyền dữ liệu vào view
        return "order/list";
    }

    @GetMapping("/orders/success")
    public String showSuccessPage() {
        return "order/success"; // trả về file success.html trong templates/order/
    }

    // Hiển thị chi tiết đơn hàng
    @RequestMapping("/orders/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        // Lấy chi tiết đơn hàng
        List<OrderDetail> orderDetails =    orderDetailRepository.findByOrderId(id);
        model.addAttribute("orderDetails", orderDetails);  // Truyền dữ liệu vào view
        model.addAttribute("orderId", id);  // Truyền orderId vào view
        return "order/detail";
    }

}

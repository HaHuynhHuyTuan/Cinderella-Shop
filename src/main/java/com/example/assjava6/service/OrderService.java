package com.example.assjava6.service;

import com.example.assjava6.dto.OrderRequest;
import com.example.assjava6.model.Order;
import com.example.assjava6.model.OrderDetail;
import com.example.assjava6.model.Product;
import com.example.assjava6.model.Account;
import com.example.assjava6.repository.OrderRepository;
import com.example.assjava6.repository.OrderDetailRepository;
import com.example.assjava6.repository.ProductRepository;
import com.example.assjava6.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Order checkout(String username, String address, List<Long> productIds, List<Integer> quantities) {
        Account account = accountRepository.findById(username).orElseThrow(() -> new RuntimeException("User not found"));

        Order order = new Order();
        order.setCreateDate(new Date());
        order.setAddress(address);
        order.setAccount(account);
        order = orderRepository.save(order);

        for (int i = 0; i < productIds.size(); i++) {
            Product product = productRepository.findById(productIds.get(i)).orElseThrow(() -> new RuntimeException("Product not found"));
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProduct(product);
            orderDetail.setPrice(product.getPrice());
            orderDetail.setQuantity(quantities.get(i));
            orderDetailRepository.save(orderDetail);
        }

        return order;
    }

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByUsername(String username) {
        return orderRepository.findByAccount_Username(username);
    }

    public Order createOrder(String username, String phone, String address, List<Product> cartProducts) {
        // Lấy thông tin tài khoản người dùng từ cơ sở dữ liệu
        Account account = accountRepository.findById(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Tạo đơn hàng
        Order order = new Order();
        order.setAccount(account);
        order.setCreateDate(new Date());
        order.setAddress(address);
        order.setStatus("Pending"); // Trạng thái mặc định có thể là "Pending"

        // Lưu đơn hàng vào cơ sở dữ liệu
        order = orderRepository.save(order);

        // Thêm chi tiết sản phẩm vào đơn hàng
        for (Product product : cartProducts) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProduct(product);
            orderDetail.setQuantity(1); // Giả sử số lượng mặc định là 1
            orderDetail.setPrice(product.getPrice());

            // Lưu chi tiết đơn hàng vào cơ sở dữ liệu
            orderDetailRepository.save(orderDetail);
        }

        return order;
    }
}

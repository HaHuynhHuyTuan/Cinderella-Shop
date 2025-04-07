package com.example.assjava6.restController;

import com.example.assjava6.dto.CheckoutRequest;
import com.example.assjava6.model.Order;
import com.example.assjava6.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CheckoutController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(@RequestBody CheckoutRequest request) {
        System.out.println("✅ Nhận được yêu cầu checkout");
        Order order = orderService.checkout(
                request.getUsername(),
                request.getAddress(),
                request.getCart().stream().map(CheckoutRequest.CartItem::getProductId).toList(),
                request.getCart().stream().map(CheckoutRequest.CartItem::getQuantity).toList()
        );
        return ResponseEntity.ok(order);
    }
}

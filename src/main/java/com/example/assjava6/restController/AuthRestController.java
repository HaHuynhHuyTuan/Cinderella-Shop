package com.example.assjava6.restController;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api")
public class AuthRestController {
    @GetMapping("/check-login")
    public ResponseEntity<Boolean> checkLogin(Authentication authentication) {
        return ResponseEntity.ok(authentication != null && authentication.isAuthenticated());
    }
}
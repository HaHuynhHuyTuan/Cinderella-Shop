package com.example.assjava6.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@AllArgsConstructor
public class Account {
    @Id
    private String username;

    private String password;
    private String fullname;
    private String email;
    private String photo;

    @OneToMany(mappedBy = "account",  fetch = FetchType.LAZY)
    @JsonIgnore // ✅ Chặn vòng lặp vô hạn
    private List<Order> orders;

    @OneToMany(mappedBy = "account",  fetch = FetchType.LAZY)
    @JsonIgnore // ✅ Chặn vòng lặp vô hạn
    private List<Authority> authorities;



    public Account() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    // ✅ Trả về danh sách tên role (VD: ["ROLE_ADMIN", "ROLE_USER"])
    public List<String> getRoles() {
        return authorities.stream()
                .map(auth -> auth.getRole().getName()) // 🔥 Đảm bảo getRole() trả về một Role
                .collect(Collectors.toList());
    }

}


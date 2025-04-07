 create database Java6Asm;
 use  Java6Asm;

SET IDENTITY_INSERT product ON;

INSERT INTO product (id, available, create_date, image, name, price, category_id) VALUES
(1, 1, GETDATE(), 'photo-1.jpg', 'Laptop Dell', 1500.00, 1),
(2, 1, GETDATE(), 'photo-2.jpg', 'iPhone 14', 999.99, 2),
(3, 1, GETDATE(), 'photo-3.jpg', 'Tai nghe Sony', 199.99, 3),
(4, 1, GETDATE(), 'photo-4.jpg', 'Bàn phím cơ Razer', 129.99, 4),
(5, 1, GETDATE(), 'photo-5.jpg', 'Đồng hồ Apple Watch', 399.99, 5);
SET IDENTITY_INSERT product OFF;

SET IDENTITY_INSERT category OFF;

INSERT INTO category (id, name) VALUES
(1, 'Laptop'),
(2, 'Mobile'),
(3, 'Audio'),
(4, 'Accessories'),
(5, 'Wearable');

SELECT * FROM product;

 -- Tạo tài khoản
 INSERT INTO account (username, password, fullname, email, photo)
 VALUES
     ('customer1', '$2a$10$XvFh4mT.PDlG7rXbPzQweuBHT.T1Za5BY3ntW1e4.0FZMzBW2VVq6', 'Customer 1', 'cus1@example.com', 'cus1.jpg'),
     ('staff1', '$2a$10$XvFh4mT.PDlG7rXbPzQweuBHT.T1Za5BY3ntW1e4.0FZMzBW2VVq6', 'Staff 1', 'staff1@example.com', 'staff1.jpg'),
     ('director1', '$2a$10$XvFh4mT.PDlG7rXbPzQweuBHT.T1Za5BY3ntW1e4.0FZMzBW2VVq6', 'Director 1', 'dir1@example.com', 'dir1.jpg');

-- Tạo vai trò
 INSERT INTO role (name) VALUES ('CUSTOMER'), ('STAFF'), ('DIRECTOR');

-- Gán quyền cho tài khoản
 INSERT INTO authority (account_username, role_id) VALUES
                                                       ('customer1', 1),
                                                       ('staff1', 2),
                                                       ('director1', 3);



-- Tạo đơn hàng mới cho customer1
 INSERT INTO orders (create_date, address, username)
 VALUES (GETDATE(), '123 Đường ABC, TP.HCM', 'customer1');

-- Lấy ID đơn hàng vừa tạo
 DECLARE @OrderId BIGINT;
 SET @OrderId = SCOPE_IDENTITY();

-- Thêm chi tiết đơn hàng cho đơn hàng trên
 INSERT INTO order_detail (order_id, product_id, price, quantity)
 VALUES (@OrderId, 1, 1500.00, 1), -- Laptop Dell
        (@OrderId, 3, 199.99, 2); -- Tai nghe Sony
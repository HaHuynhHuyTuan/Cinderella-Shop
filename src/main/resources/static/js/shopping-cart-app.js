const app = angular.module("shopping-cart-app", []);

app.controller("shopping-cart-ctrl", function ($scope, $http) {
    $scope.products = []; // Danh sách sản phẩm

    // Gọi API lấy danh sách sản phẩm
    $http.get("/rest/products")
        .then(resp => {
            $scope.products = resp.data;
            console.log("✅ Products loaded:", $scope.products);
        })
        .catch(error => {
            console.error("❌ Lỗi khi tải danh sách sản phẩm:", error);
        });

    /*
    * QUẢN LÝ GIỎ HÀNG
    */
    $scope.cart = {
        items: JSON.parse(localStorage.getItem("cart")) || [],
        shippingAddress: "",

        add(event) {
            let button = event.currentTarget;
            let product = {
                id: button.getAttribute("data-id"),
                name: button.getAttribute("data-name"),
                price: parseFloat(button.getAttribute("data-price")),
                image: button.getAttribute("data-image")
            };

            console.log("🛒 Thêm sản phẩm:", product);

            $http.get("/api/check-login").then(resp => {
                if (resp.data) {
                    console.log("✅ User is logged in. Adding product ID:", product.id);
                    $scope.cart.addToCart(product);
                } else {
                    console.warn("⚠️ User is NOT logged in. Redirecting to login...");
                    window.location.href = "/login";
                }
            }).catch(error => {
                console.error("❌ Error checking login:", error);
            });
        },

        addToCart(product) {
            let existing = this.items.find(item => item.id == product.id);
            if (existing) {
                existing.qty++;
            } else {
                product.qty = 1;
                this.items.push(product);
            }
            this.saveToLocalStorage();
        },

        updateQuantity(id, qty) {
            let product = this.items.find(item => item.id == id);
            if (product) {
                product.qty = Math.max(qty, 1); // Không cho phép số lượng nhỏ hơn 1
                this.saveToLocalStorage();
                console.log("🔄 Quantity updated:", product);
            }
        },

        get count() {
            return this.items.reduce((total, item) => total + item.qty, 0);
        },

        get amount() {
            return this.items.reduce((total, item) => total + (item.qty * item.price), 0);
        },

        remove(id) {
            this.items = this.items.filter(item => item.id !== id);
            this.saveToLocalStorage();
        },

        clear() {
            this.items = [];
            this.saveToLocalStorage();
        },

        amt_of(item) {
            return item.qty * item.price;
        },

        saveToLocalStorage() {
            localStorage.setItem("cart", JSON.stringify(this.items));
            console.log("💾 Giỏ hàng đã lưu:", this.items);
        },

        checkout() {
            let payload = {
                username: "guest", // hoặc null, hoặc bỏ luôn nếu backend không cần
                address: this.shippingAddress || "Không có địa chỉ",
                cart: this.items.map(item => ({
                    productId: item.id,
                    quantity: item.qty
                }))
            };

            // Giả lập hành động checkout mà không cần gọi API
            console.log("✅ Checkout thành công");

            // Xóa giỏ hàng (nếu có)
            this.clear();

            // Chuyển hướng sang trang /orders/success
            window.location.href = "/orders/success";  // Chuyển hướng trực tiếp đến trang success
        }
    };

    console.log("📤 Giỏ hàng đã tải:", $scope.cart.items);
    console.log("Script shopping-cart-app.js đã chạy!");
});

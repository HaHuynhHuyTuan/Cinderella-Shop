angular.module("AdminApp", [])
    .controller("AdminController", function ($scope, $http) {
        // Load data
        function loadCategories() {
            $http.get('/api/admin/categories').then(function (response) {
                $scope.categories = response.data;
            });
        }

        function loadAccounts() {
            $http.get('/api/admin/accounts').then(function (response) {
                $scope.accounts = response.data;
            });
        }

        function loadProducts() {
            $http.get('/api/admin/products').then(function (response) {
                $scope.products = response.data;
            });
        }

        loadCategories();
        loadAccounts();
        loadProducts();

        // Save Category
        $scope.saveCategory = function () {
            $http.post('/api/admin/categories', $scope.newCategory).then(function (response) {
                $scope.newCategory = {}; // Reset form
                loadCategories(); // Reload categories
            });
        };

        // Save Account
        $scope.saveAccount = function () {
            $http.post('/api/admin/accounts', $scope.newAccount).then(function (response) {
                $scope.newAccount = {}; // Reset form
                loadAccounts(); // Reload accounts
            });
        };

        // Save Product
        $scope.saveProduct = function () {
            $http.post('/api/admin/products', $scope.newProduct).then(function (response) {
                $scope.newProduct = {}; // Reset form
                loadProducts(); // Reload products
            });
        };

        // Delete Category
        $scope.deleteCategory = function (id) {
            $http.delete('/api/admin/categories/' + id).then(function () {
                loadCategories(); // Reload categories
            });
        };

        // Delete Account
        $scope.deleteAccount = function (username) {
            $http.delete('/api/admin/accounts/' + username).then(function () {
                loadAccounts(); // Reload accounts
            });
        };

        // Delete Product
        $scope.deleteProduct = function (id) {
            $http.delete('/api/admin/products/' + id).then(function () {
                loadProducts(); // Reload products
            });
        };

        // Edit Category (For editing purposes)
        $scope.editCategory = function (category) {
            $scope.newCategory = angular.copy(category);
        };

        // Edit Account (For editing purposes)
        $scope.editAccount = function (account) {
            $scope.newAccount = angular.copy(account);
        };

        // Edit Product (For editing purposes)
        $scope.editProduct = function (product) {
            $scope.newProduct = angular.copy(product);
        };
    });

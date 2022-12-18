angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $rootScope, $http, $localStorage) {

    const contextPath = 'http://localhost:8189/shop/api/v1';

    $scope.loadProducts = function () {
        $http.get('http://localhost:8189/shop/api/v1/products').then(function (response){
            $scope.prodList = response.data;
            // console.log(response.data());
        });
    }

    $scope.loadCart = function () {
        $http.get('http://localhost:8190/shop-cart/api/v1/cart').then(function (response){
            $scope.cart = response.data;
        });
    }

    $scope.addToCart = function (productId) {
        $http.get('http://localhost:8190/shop-cart/api/v1/cart/add/' + productId).then(function (response){
            $scope.loadCart();
        });
    }

    $scope.clearCart = function (productId) {
        $http.get('http://localhost:8190/shop-cart/api/v1/cart/clear').then(function (response){
            $scope.loadCart();
        });
    }

    $scope.increase = function (productId) {
        $http.get('http://localhost:8190/shop-cart/api/v1/cart/inc/' + productId).then(function (response){
            $scope.loadCart();
        });
    }

    $scope.reduce = function (productId) {
        console.log(productId);
        $http.get('http://localhost:8190/shop-cart/api/v1/cart/reduce/' + productId).then(function (response){
            $scope.loadCart();
        });
    }

    $scope.remove = function (productId) {
        $http.get('http://localhost:8190/shop-cart/api/v1/cart/remove/' + productId).then(function (response){
            $scope.loadCart();
        });
    }

    $scope.tryToAuth = function () {
        $http.post('http://localhost:8189/shop/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.springWebUser = {login: $scope.user.login, token: response.data.token};

                    $scope.user.login = null;
                    $scope.user.password = null;
                }
            }, function errorCallback(response) {
                console.log("Auth is wrong(")
            });
    };

    $scope.tryToReg = function () {
        $http.post('http://localhost:8189/app/reg', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.springWebUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;
                }
            }, function errorCallback(response) {

            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        if ($scope.user.login) {
            $scope.user.login = null;
        }
        if ($scope.user.password) {
            $scope.user.password = null;
        }
    };

    $scope.clearUser = function () {
        delete $localStorage.springWebUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $rootScope.isUserLoggedIn = function () {
        if ($localStorage.springWebUser) {
            return true;
        } else {
            return false;
        }
    };

    $scope.makeOrder = function () {
        $http.post('http://localhost:8189/shop/api/v1/order/' + $localStorage.springWebUser.login)
            .then(function successCallback() {
                $scope.clearCart();
                $scope.loadCart();
            }, function errorCallback() {
                console.log("Order is wrong(")
            });
    };

    $scope.loadProducts();
    $scope.loadCart();
});
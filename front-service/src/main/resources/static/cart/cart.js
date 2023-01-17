angular.module('app').controller('cartController', function ($scope, $rootScope, $http, $localStorage) {

    const contextPath = 'http://localhost:8190/cart/api/v1';

    $scope.loadCart = function () {
        $http.get(contextPath).then(function (response) {
            $scope.cart = response.data;
        });
    }

    $scope.addToCart = function (productId) {
        $http.get(contextPath + 'add/' + productId).then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.clearCart = function (productId) {
        $http.get(contextPath + '/clear').then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.remove = function (productId) {
        $http.get(contextPath + '/remove/' + productId).then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.makeOrder = function () {
        $http.post('http://localhost:8189/shop-core/api/v1/order/' + $localStorage.springWebUser.login)
            .then(function successCallback() {
                $scope.clearCart();
                $scope.loadCart();
            }, function errorCallback() {
                console.log("Order is wrong(")
            });
    };
});
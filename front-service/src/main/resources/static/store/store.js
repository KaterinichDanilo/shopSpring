angular.module('app').controller('storeController', function ($scope, $rootScope, $http, $localStorage) {

    const contextPath = 'http://localhost:8189/store/api/v1';

    $scope.loadProducts = function () {
        $http.get('http://localhost:8189/shop-core/api/v1/products').then(function (response){
            $scope.prodList = response.data;
            // console.log(response.data());
        });
    }

    $scope.increase = function (productId) {
        $http.get('http://localhost:8190/cart/api/v1/cart/inc/' + productId).then(function (response){
            $scope.loadCart();
        });
    }

    $scope.reduce = function (productId) {
        console.log(productId);
        $http.get('http://localhost:8190/cart/api/v1/cart/reduce/' + productId).then(function (response){
            $scope.loadCart();
        });
    }
});
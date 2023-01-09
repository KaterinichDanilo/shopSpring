// angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $rootScope, $http, $localStorage) {
//
//     const contextPath = 'http://localhost:8189/shop/api/v1';
//
//     $scope.tryToAuth = function () {
//         $http.post('http://localhost:8192/auth/auth', $scope.user)
//             .then(function successCallback(response) {
//                 if (response.data.token) {
//                     $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
//                     $localStorage.springWebUser = {login: $scope.user.login, token: response.data.token};
//
//                     $scope.user.login = null;
//                     $scope.user.password = null;
//                 }
//             }, function errorCallback(response) {
//                 console.log("Auth is wrong(")
//             });
//     };
//
//     $scope.tryToReg = function () {
//         $http.post('http://localhost:8189/shop-core/reg', $scope.user)
//             .then(function successCallback(response) {
//                 if (response.data.token) {
//                     $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
//                     $localStorage.springWebUser = {username: $scope.user.username, token: response.data.token};
//
//                     $scope.user.username = null;
//                     $scope.user.password = null;
//                 }
//             }, function errorCallback(response) {
//
//             });
//     };
//
//     $scope.tryToLogout = function () {
//         $scope.clearUser();
//         if ($scope.user.login) {
//             $scope.user.login = null;
//         }
//         if ($scope.user.password) {
//             $scope.user.password = null;
//         }
//     };
//
//     $scope.clearUser = function () {
//         delete $localStorage.springWebUser;
//         $http.defaults.headers.common.Authorization = '';
//     };
//
//     $rootScope.isUserLoggedIn = function () {
//         if ($localStorage.springWebUser) {
//             return true;
//         } else {
//             return false;
//         }
//     };
//
//     $scope.loadProducts();
// });

(function () {
    angular
        .module('app', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider){
        $routeProvider
            .when('/store', {
            templateUrl: 'store/store.html',
            controller: 'storeController'
        })
            .when('/cart', {
                templateUrl: 'cart/cart.html',
                controller: 'cartController'
            })
    }

    function run($rootScope, $http, $localStorage){
        if ($localStorage.springWebUser){
            try {
                let jwt = $localStorage.springWebUser.token;
                let payload = JSON.parse(jwt.split(".")[1]);
                let currentTime = parseInt(new Date().getTime() / 1000);
                if (currentTime > payload.exp){
                    console.log("Token is expired.");
                    delete $localStorage.springWebUser;
                    $http.default.headers.common.Authorization = '';
                }
            } catch (e){
            }

            $http.default.headers.common.Authorization = 'Bearer' + $localStorage.springWebUser.token;
        }
    }
})();

angular.module('app').controller('indexController', function ($scope, $rootScope, $http, $localStorage) {

    const contextPath = 'http://localhost:8189/shop/api/v1';

    $scope.tryToAuth = function () {
        $http.post('http://localhost:8192/auth/auth', $scope.user)
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
        $http.post('http://localhost:8189/shop-core/reg', $scope.user)
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

    $scope.loadProducts();
});
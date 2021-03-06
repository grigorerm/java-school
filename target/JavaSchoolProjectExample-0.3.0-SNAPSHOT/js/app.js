angular.module('exampleApp', ['ngRoute', 'ngCookies', 'exampleApp.services'])
    .config(
        ['$routeProvider', '$locationProvider', '$httpProvider', function ($routeProvider, $locationProvider, $httpProvider) {

            $routeProvider.when('/create', {
                templateUrl: 'partials/create.html',
                controller: CreateController
            });

            $routeProvider.when('/edit/:id', {
                templateUrl: 'partials/edit.html',
                controller: EditController
            });

            $routeProvider.when('/login', {
                templateUrl: 'partials/login.html',
                controller: LoginController
            });

            $routeProvider.when('/front', {
                templateUrl: 'partials/front.html',
                controller: frontController

            });

            $routeProvider.when('/history', {
                templateUrl: 'partials/history.html',
                controller: RaportController
            });


            $routeProvider.otherwise({
                templateUrl: 'partials/index.html',
                controller: IndexController
            });


            /* Register error provider that shows message on failed requests or redirects to login page on
             * unauthenticated requests */
            $httpProvider.interceptors.push(function ($q, $rootScope, $location) {
                    return {
                        'responseError': function (rejection) {
                            var status = rejection.status;
                            var config = rejection.config;
                            var method = config.method;
                            var url = config.url;

                            if (status == 401) {
                                $location.path("/login");
                            } else {
                                $rootScope.error = method + " on " + url + " failed with status " + status;
                            }

                            return $q.reject(rejection);
                        }
                    };
                }
            );

            /* Registers auth token interceptor, auth token is either passed by header or by query parameter
             * as soon as there is an authenticated user */
            $httpProvider.interceptors.push(function ($q, $rootScope, $location) {
                    return {
                        'request': function (config) {
                            var isRestCall = config.url.indexOf('rest') == 0;
                            if (isRestCall && angular.isDefined($rootScope.authToken)) {
                                var authToken = $rootScope.authToken;
                                if (exampleAppConfig.useAuthTokenHeader) {
                                    config.headers['X-Auth-Token'] = authToken;
                                } else {
                                    config.url = config.url + "?token=" + authToken;
                                }
                            }
                            return config || $q.when(config);
                        }
                    };
                }
            );

        }]
    ).run(function ($rootScope, $location, $cookieStore, UserService) {

    /* Reset error when a new view is loaded */
    $rootScope.$on('$viewContentLoaded', function () {
        delete $rootScope.error;
    });

    $rootScope.hasRole = function (role) {
        if ($rootScope.user === undefined) {
            return false;
        }

        if ($rootScope.user.roles[role] === undefined) {
            return false;
        }

        return $rootScope.user.roles[role];
    };

    $rootScope.logout = function () {
        delete $rootScope.user;
        delete $rootScope.authToken;
        $cookieStore.remove('authToken');
        $location.path("/login");
    };

    /* Try getting valid user from cookie or go to login page */
    var originalPath = $location.path();
    $location.path("/login");
    var authToken = $cookieStore.get('authToken');
    if (authToken !== undefined) {
        $rootScope.authToken = authToken;
        UserService.get(function (user) {
            $rootScope.user = user;
            $location.path(originalPath);
        });
    }

    $rootScope.initialized = true;
});

var cart = [];
function IndexController($scope, UserService, $cookieStore, $http, NewsService, ProductsService, $rootScope) {

    $scope.newsEntries = NewsService.query();
    $scope.products = ProductsService.query();
    //$scope.currentUser = UserService.query();


    var currentQuantity = 0;
    var setQuantity = false;
    var quantityBadge = 0;
    var idCommand = 0;

    $scope.setQ = function (index) {
        var aux = document.getElementById(index);
        currentQuantity = aux.value;
        setQuantity = true;
    };
    $scope.disableButton = function () {
        $scope.isDisabled = true;
    }

    $scope.deleteEntry = function (newsEntry) {
        newsEntry.$remove(function () {
            $scope.newsEntries = NewsService.query();
        });
    };


    $scope.invoice = {
        items: [{

            name: '',
            supplier: '',
            price: 0,
            quantity: 0
        }]
    };

    $scope.cart = [{
        id: 0,
        name: "",
        supplier: "",
        price: 0,
        quantity: 0,

    }];
    $scope.input = {
        badge: 0
    }

    $scope.addItem = function (id, name, supplier, price, quantity) {
        if (setQuantity == true) {
            $scope.invoice.items.push({
                name: name,
                supplier: supplier,
                price: price,
                quantity: (quantity - currentQuantity)
            });

            quantityBadge += parseInt(currentQuantity);

            $scope.cart.push({
                id: id,
                name: name,
                supplier: supplier,
                price: currentQuantity * price,
                quantity: currentQuantity,


            });
            $scope.input = {
                badge: quantityBadge
            }
        }

        setQuantity = false;
    };


    $scope.myFunction = function (currentUserName) {

        var delivaryDate = document.getElementById("deliveryDate").value;
        alert("Your order has been placed and will be delivered on " + delivaryDate);

        var config = {
            headers: {
                'Content-Type': 'application/json'
            }
        }

        // update in products
        for (var i = 0; i < $scope.cart.length; i++) {
            if ($scope.cart[i].quantity != 0) {
                var data = {
                    "supplier_name": $scope.cart[i].supplier,
                    "price": $scope.invoice.items[i].price,
                    "quantity": $scope.invoice.items[i].quantity,
                    "name": $scope.cart[i].name,
                    "id": $scope.cart[i].id
                }
                var url = "http://localhost:8080/rest/products/" + $scope.cart[i].id;
                $scope.stiri = $http.post(url, data, config).then(function (response) {
                    $scope.ceva = response.data;
                });
                var orderInfo = "has ordered " + $scope.invoice.items[i].quantity
                    + " x " + $scope.cart[i].name + " " + $scope.cart[i].supplier +
                    " for a total of " + $scope.cart[i].price + " with delivary date " + document.getElementById("deliveryDate").value;
                var name = "" + currentUserName;
                data = {
                    "user": name,
                    "date_delivery": "delivaryDate",
                    "orderInfo": orderInfo,
                    "store": "lidl",
                    "address": "str. pipera"
                }

                var url = "http://localhost:8080/rest/history/";
                $scope.stiri = $http.post(url, data, config).then(function (response) {
                    $scope.ceva = response.data;

                });
            }
        }
        ;


        location.reload();

    }

    $scope.removeItem = function (index) {
        $scope.invoice.items.splice(index, 1);
    };

}

function RaportController($scope, $http, $routeParams, $cookieStore, $location, UserService) {

    var orders;
    //$scope.contents = $cookieStore.get('content');
    //alert($scope.contents);
    var url = "http://localhost:8080/rest/history/";
    $scope.stiri = $http.get(url).then(function (response) {
        $scope.orders = response.data;

    });

}

function EditController($scope, $routeParams, $location, NewsService) {

    $scope.newsEntry = NewsService.get({id: $routeParams.id});

    $scope.save = function () {
        $scope.newsEntry.$save(function () {
            $location.path('/');
        });
    };
};


function CreateController($scope, $location, NewsService) {

    $scope.newsEntry = new NewsService();

    $scope.save = function () {
        $scope.newsEntry.$save(function () {
            $location.path('/');
        });
    };
};


function LoginController($scope, $rootScope, $location, $cookieStore, UserService) {
    $scope.isHomePage = true;
    $scope.rememberMe = false;

    $scope.login = function () {
        UserService.authenticate($.param({
            username: $scope.username,
            password: $scope.password
        }), function (authenticationResult) {
            var authToken = authenticationResult.token;
            $rootScope.authToken = authToken;
            if ($scope.rememberMe) {
                $cookieStore.put('authToken', authToken);
            }
            UserService.get(function (user) {
                $rootScope.user = user;
                $location.path("/front");
            });
        });
    };
};


function frontController($scope, $location) {


    $scope.go = function () {
        $location.path('/register');
    };
}


var services = angular.module('exampleApp.services', ['ngResource']);

services.factory('UserService', function ($resource) {

    return $resource('rest/user/:action', {},
        {
            authenticate: {
                method: 'POST',
                params: {'action': 'authenticate'},
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            },
            create: {
                method: 'POST',
                params: {'action': 'create'},
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            }

        }
    );
});

services.factory('NewsService', function ($resource) {

    return $resource('rest/news/:id', {id: '@id'});
});

services.factory('ProductsService', function ($resource) {

    return $resource('rest/products/:id_product', {id_product: '@id_product'});
});


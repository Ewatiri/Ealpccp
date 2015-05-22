var ealpApp = angular.module("ealpApp", ['ngRoute', 'angularUtils.directives.dirPagination', 'angular-loading-bar', 'ngAnimate', 'angular-svg-round-progress', 'ngQuickDate']);

// routing configuration
ealpApp.config(function ($routeProvider) {
    $routeProvider
            .when('/', {
                templateUrl: "./partials/home.jsp",
                controller: 'homeCtrl'
            }).when('/scholarapp', {
        templateUrl: "./partials/scholar1.jsp",
        controller: 'scholar1Ctrl'
    }).when('/counselor1', {
        templateUrl: "./partials/counselor1.jsp",
        controller: 'counselor1Ctrl'
    }).when('/admin1', {
        templateUrl: "./partials/head.jsp",
        controller: 'adminCtrl'
    }).when('/reset', {
        templateUrl: "./partials/reset.jsp",
        controller: 'homeCtrl'
    }).when('/collegeprep', {
        templateUrl: "./partials/scholar2.jsp",
        controller: 'scholar2Ctrl'
    }).when('/counselor2', {
        templateUrl: "./partials/counselor2.jsp",
        controller: 'counselor2Ctrl'
    }).when('/admin2', {
        templateUrl: "./partials/head2.jsp",
        controller: 'admin2Ctrl'
    }).otherwise({redirectTo: "/"})
});

ealpApp.controller("homeCtrl", function ($scope, homeService,ealpService, utility) {

    $scope.frmsignup = {};
    $scope.myuser = {};
    $scope.scholar = {};
    $scope.lg = false;
    $scope.ealp = {};
    $scope.usertypes = [{type: "Counselor", class: "1"}, {type: "Scholar", class: "0"}];
    $scope.lg1 = false;

    $scope.reset = {};
    $scope.showsignin = true;
    $scope.showaccesscode = false;
    $scope.showfirstlogin = false;
    $scope.showSignin = function () {
        if ($scope.showsignin == true) {
            $scope.showsignin = false;
            $scope.showfirstlogin = false;
        } else {
            $scope.showsignin = true;
            $scope.showaccesscode = false;
            $scope.showfirstlogin = false;
        }
    }

    $scope.showAccess = function () {
        $scope.showaccesscode = true;
        $scope.showfirstlogin = false;
        $scope.showsignin = false;
    }
    $scope.showSignup = function () {
        $scope.showaccesscode = false;
        $scope.showfirstlogin = false;
        $scope.showsignin = false;

    }

    $scope.$watch("showfirstlogin", function () {
        if ($scope.showfirstlogin == true) {
            $scope.showsignin = false;
            $scope.showaccesscode = false;
        }
    }, true);
    $scope.$watch("frmsignup.me", function () {
        if ($scope.frmsignup.me == "1") {

            $(".new-scholar>div").css("margin-top", '10%');
        }
        else if ($scope.frmsignup.me == "2") {

            $(".new-scholar>div").css("margin-top", '10%');
        }
        else if ($scope.frmsignup.me == "3") {

            $(".new-scholar>div").css("margin-top", '1%');
        }
        else if ($scope.frmsignup.me == "4") {
            $(".new-scholar>div").css("margin-top", '10%');
        }
    }, true);


    $scope.login = function () {
        //console.log($scope.myuser);
        
        homeService.login($scope.myuser);
        $scope.myuser = {};
    }
    $scope.access = {};
    $scope.newscholar = {};
    $scope.accessCode = function () {


        var d = {};
        $scope.is = {};
        homeService.loginAccessCode($scope.access).then(function (data) {
            d = data.data;
            if (d.responseCode == "200") {
                $scope.is = d;
                $scope.showfirstlogin = true;
                $scope.newscholar.email = d.message;
                $scope.newscholar.type = $scope.frmsignup.me;
            }
        });
    }

    $scope.addScholar = function () {
        $scope.scholar.mytype = $scope.frmsignup.me;
        homeService.addScholar($scope.scholar);
        $scope.scholar = {};
        $scope.frmsignup = {};
    }

    $scope.createScholar = function () {
        homeService.createScholar($scope.newscholar).then(function (data) {
            d = data.data;
            if (d.responseCode == "200") {
                $scope.showfirstlogin = false;
                $scope.showsignin = true;
                $scope.lg = true;
                utility.showAlert(".login-alert", "success", d.message, 10000);
            }
        });
    }

    $scope.init = function () {
        ealpService.get({action:"getstate"},"home").then(function(data){
            if (data.data.responseCode == "200"){
                $scope.state = data.message;
            }
                
            
        });
        /*var state = {};
        state.action = "getstate";

        $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'home',
            data: $.param(state),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == 200) {
                $scope.state = data.message;



            }
        }).error(function (data) {

        });*/
    }

    $scope.addealpScholar = function () {
        //console.log($scope.ealp);
        homeService.createealpScholar($scope.ealp).then(function (data) {
            if (data.data.responseCode == "200") {
                // $scope.is = d;
                $scope.showfirstlogin = true;
                $scope.newscholar.email = $scope.ealp.email;
                $scope.newscholar.type = $scope.frmsignup.me;
                $scope.newscholar.pf = $scope.ealp.pf;
            }
        });
    }

    $scope.resetPassword = function () {
        homeService.resetPassword($scope.reset).then(function (data) {
            if (data.data.responseCode == "200") {
                $scope.lg1 = true;




            }
        })
        $scope.reset = {};
    }

});

/*ealpApp.factory('ifcompleteService', function () {
    var factory = {};
    var chk = true;
    factory.check = function (object) {
        for (var key in object) {
            if (object[key] === "" || object[key] === null) {

                chk = false;
                /* console.log("key "+key);
                 console.log(object[key]);
            }
        }
        return chk;
    }
    return factory;
});*/


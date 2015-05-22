ealpApp.directive('resources', function () {
    return{
        restrict: 'E',
        scope: true,
        templateUrl: './partials/resources/resources.jsp',
        controller: function ($scope) {
            $scope.currentPage = 1;
            $scope.pageSize = 10;

        }
    }
});
ealpApp.directive('addResources', function (ealpService) {
    return{
        restrict: 'E',
        scope: true,
        templateUrl: './partials/resources/addres.jsp',
        controller: function ($scope) {
            $scope.res = {};
            $scope.addRes = function () {
                $scope.res.action = "addres";
                if ($scope.res.type != "2") {
                    var fd = new FormData();
                    angular.forEach($scope.res, function (value, key) {
                        if (key == "file") {
                            fd.append(key, value[0]);
                        } else {
                            fd.append(key, value);
                        }
                    })
                    ealpService.uploadDocument(fd, "addResource", ".mres-alert").then(function (data) {
                        if (data.data.responseCode == "200") {
                            $scope.res = {};
                            //console.log("hello");
                        }
                    });
                } else {
                    ealpService.set($scope.res, "admin1", ".mres-alert").then(function (data) {
                        if (data.data.responseCode == "200") {
                            $scope.res = {};
                        }
                    });
                }


            }
        }
    }
});
ealpApp.directive("manageResources", function (ealpService) {
    return{
        restrict: "E",
        scope: true,
        templateUrl: "./partials/resources/manageres.jsp",
        controller: function ($scope, $rootScope, $window) {

            $scope.currentPage = 1;
            $scope.pageSize = 5;
            //$scope.pages = 3;

            $scope.edit = function (ind) {
                $scope.number = (ind + 1) + ($scope.currentPage - 1) * $scope.pageSize;
                $rootScope.resources[$scope.number - 1].edit = "1";
            }

            $scope.reRoute = function (url) {
                $window.location.href = url;
                // console.log("hello");
            }

            $scope.save = function (ind) {
                $scope.number = (ind + 1) + ($scope.currentPage - 1) * $scope.pageSize;
                $rootScope.resources[$scope.number - 1].edit = "0";
                $rootScope.resources[$scope.number - 1].action = "editRes";
                ealpService.set($rootScope.resources[$scope.number - 1], "admin1", ".res1-alert");
            }
            $scope.delete = function (ind) {
                $scope.number = (ind + 1) + ($scope.currentPage - 1) * $scope.pageSize;
                $rootScope.resources[$scope.number - 1].action = "deleteRes";
                ealpService.set($rootScope.resources[$scope.number - 1], "admin1", ".res1-alert").then(function (data) {
                    if (data.data.responseCode == "200") {
                        $rootScope.resources.splice($scope.number - 1, 1);
                    }
                });
            }
        }
    }
});
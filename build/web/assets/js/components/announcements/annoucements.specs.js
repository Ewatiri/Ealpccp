ealpApp.directive('announcements', function () {
    return{
        restrict: 'E',
        scope: true,
        templateUrl: './partials/announcements/announcements.jsp',
        controller: function ($scope) {
            $scope.currentPage = 1;
            $scope.pageSize = 5;
        }
    }
});

ealpApp.directive('addAnnouncements', function (ealpService,$rootScope) {
    return{
        restrict: 'E',
        scope: true,
        templateUrl: './partials/announcements/addannoun.jsp',
        controller: function ($scope) {
            $scope.announ = {};
            $scope.addAnnoun = function () {
                $scope.announ.action = "addannoun";
                ealpService.set($scope.announ,"admin1" ,".announ-alert").then(function(data){
                   if (data.data.responseCode == "200"){
                       var announ = {};
                       announ.title = $scope.announ.title;
                       //console.log( $scope.announ.title);
                       announ.description = $scope.announ.desc;
                       announ.by = "Me :-)";
                       announ.resdate = new Date();
                       $rootScope.announcements.push(announ);
                   }
                   $scope.announ = {};
                });
                
            }
        }
    }
});

ealpApp.directive("manageAnnouncements", function (ealpService) {
    return{
        restrict: 'E',
        scope: true,
        templateUrl: "./partials/announcements/manageannoun.jsp",
        controller: function ($scope, $rootScope) {

            $scope.currentPage = 1;
            $scope.pageSize = 2;
            //$scope.pages = 3;
            $scope.edit = function (ind) {
                $scope.number = (ind + 1) + ($scope.currentPage - 1) * $scope.pageSize;
                $rootScope.announcements[$scope.number - 1].edit = "1";
            }
            $scope.save = function (ind) {
                $scope.number = (ind + 1) + ($scope.currentPage - 1) * $scope.pageSize;
                $rootScope.announcements[$scope.number - 1].edit = "0";
                $rootScope.announcements[$scope.number - 1].action = "editAnnoun";
                ealpService.set($rootScope.announcements[$scope.number - 1],"admin1" ,".announ1-alert");
            }
            $scope.delete = function (ind) {
                $scope.number = (ind + 1) + ($scope.currentPage - 1) * $scope.pageSize;
                $rootScope.announcements[$scope.number - 1].action = "deleteAnnoun";
                ealpService.set($rootScope.announcements[$scope.number - 1], "admin1",".announ1-alert").then(function(data){
                    if (data.data.responseCode == "200"){
                        $rootScope.announcements.splice($scope.number - 1, 1);
                    }
                });
                
            }
        }
    }
});
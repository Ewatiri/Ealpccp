ealpApp.directive("approvals", function (ealpService) {
    return{
        restrict: 'E',
        scope: true,
        templateUrl: './partials/useraccounts/approval.jsp',
        controller: function ($scope, $rootScope) {
            $scope.currentPage = 1;
            $scope.pageSize = 5;

            $scope.approve = function (ind) {
                //console.log($scope.currentPage);
                $scope.number = (ind + 1) + ($scope.currentPage - 1) * $scope.pageSize;
                $rootScope.approvalScholars[$scope.number - 1].action = "approvescholar";

                // send , 'admin1'
                ealpService.set($rootScope.approvalScholars[$scope.number - 1], "admin1", ".ascholar-alert").then(function (data) {
                    if (data.data.responseCode == "200") {
                        $rootScope.approvalScholars.splice($scope.number - 1, 1);
                    }
                })


            }
            $scope.reject = function (ind) {
                $scope.number = (ind + 1) + ($scope.currentPage - 1) * $scope.pageSize;
                $rootScope.approvalScholars[$scope.number - 1].action = "rejectscholar";

                ealpService.set($rootScope.approvalScholars[$scope.number - 1], "admin1", ".ascholar-alert").then(function (data) {
                    if (data.data.responseCode == "200") {
                        $rootScope.approvalScholars.splice($scope.number - 1, 1);
                    }
                });


            }
        }
    }
});

ealpApp.directive("addMentor", function (ealpService) {
    return{
        restrict: "E",
        scope: true,
        templateUrl: './partials/useraccounts/addmentor.jsp',
        controller: function ($scope) {
            $scope.mentor = {};
            $scope.addUser = function () {
                $scope.mentor.action = "addmentor";
                ealpService.set($scope.mentor, "admin1", ".addmentor-alert").then(function (data) {
                    if (data.data.responseCode == "200") {

                        $scope.mentor = {};
                    }
                })



            }
        }
    }
});
ealpApp.directive("delMentor", function (ealpService) {
    return{
        restrict: "E",
        scope: true,
        templateUrl: './partials/useraccounts/delmentor.jsp',
        controller: function ($scope, $rootScope) {
            $scope.currentPage = 1;
            $scope.pageSize = 5;

            $scope.activate = function (ind) {
                var index = ((ind + 1) + ($scope.currentPage - 1) * $scope.pageSize) - 1;
                $rootScope.mentors[index].action = "activateaccount";
                ealpService.set($rootScope.mentors[index], "admin2", ".mentor-alert").then(function (data) {
                    if (data.data.responseCode == "200") {
                        $rootScope.mentors[index].status = "1";
                    }
                })
            }
            $scope.deactivate = function (ind) {
                var index = ((ind + 1) + ($scope.currentPage - 1) * $scope.pageSize) - 1;
                $rootScope.mentors[index].action = "delmentor";
                ealpService.set($rootScope.mentors[index], "admin2", ".mentor-alert").then(function (data) {
                    if (data.data.responseCode == "200") {
                        $rootScope.mentors[index].status = "-1";
                    }
                })
            }
            $scope.switchtoStandard = function (ind) {
                var index = ((ind + 1) + ($scope.currentPage - 1) * $scope.pageSize) - 1;
                $rootScope.mentors[index].action = "switchstd";
                ealpService.set($rootScope.mentors[index], "admin2", ".mentor-alert").then(function (data) {
                    if (data.data.responseCode == "200") {
                        $rootScope.mentors[index].class = "2";
                        //console.log($rootScope.mentors[index].class);
                    }
                })
            }
            $scope.switchtoAdmin = function (ind) {
                var index = ((ind + 1) + ($scope.currentPage - 1) * $scope.pageSize) - 1;
                $rootScope.mentors[index].action = "switchadmin";
                ealpService.set($rootScope.mentors[index], "admin2", ".mentor-alert").then(function (data) {
                    if (data.data.responseCode == "200") {
                        $rootScope.mentors[index].class = "1";
                        //console.log($rootScope.mentors[index].class);
                    }
                })
            }
        }
    }
})
ealpApp.directive("account", function (utility,ealpService) {
    return{
        scope: {
            type: "="
        },
        restrict: 'E',
        templateUrl: './partials/myaccount/account.jsp',
        controller: function ($scope) {
            $scope.account = {};
            // console.log($rootScope.elem1);
            $scope.changePass = function () {
                $scope.account.action = "changepass";
                if ($scope.account.pass1 == $scope.account.pass2) {
                    if ($scope.type == "1") {
                        ealpService.set($scope.account,"admin1",".changepass-alert");
                        $scope.account = {};
                    } else if ($scope.type == "2") {
                        ealpService.set($scope.account,"scholar1",".changepass-alert");
                        $scope.account = {};
                    }
                } else {
                    utility.showAlert(".changepass-alert", "alert", "The passwords do not match.", 3000);
                }

            }
        },
        
    }
});


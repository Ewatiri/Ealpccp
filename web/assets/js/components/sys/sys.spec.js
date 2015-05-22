ealpApp.directive("sys", function (ealpService, utility) {
    return{
        restrict: "E",
        scope: true,
        templateUrl: './partials/sys/sys.jsp',
        controller: function ($scope) {
            $scope.module = {};
            $scope.setModule = function () {
                if ($scope.module.cycle.charAt(4) !== '/') {
                    utility.showAlert(".setmodule-alert", "alert", "Replace the " + $scope.module.cycle.charAt(4) + " seperator with a slash('/')");
                } else {
                    $scope.module.action = "setmodule"
                    ealpService.set($scope.module, "admin1", ".setmodule-alert");
                    $scope.module = {};
                }


            }
        }
    }
});


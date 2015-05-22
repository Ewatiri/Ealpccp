ealpApp.directive("myProfile", function (ealpService) {
    return{
        restrict: "E",
        scope: true,
        templateUrl: './partials/profile/profile.jsp',
        controller: function ($scope, $rootScope) {
            $scope.pageSize = 2;
            $scope.currentPage = 1;
            $scope.allcolleges = true;

            $scope.selectedcollege = {};
            $scope.essays = {};

            $scope.getEssays = function (college) {
                $scope.allcolleges = false;
                $scope.selectedcollege = college;
                var d = {};
                d.action = "getcollegessays";
                d.collegeid = college.id;
                ealpService.get(d, "scholar2").then(function (data) {
                    if (data.data.responseCode != "400" && data.data.responseCode !== "403") {
                        $scope.essays = data.data;
                    }
                });


            }


            $scope.goBack = function () {
                $scope.allcolleges = true;
            }

            $scope.pickCollege = function (college) {
                var d = {};
                d.action = "pickcollege";
                d.collegeid = college.id;
                ealpService.set(d, "scholar2", ".scholarcollege-alert").then(function (data) {
                    if (data.data.responseCode == "200") {
                        var index = $rootScope.colleges.indexOf(college);
                        $rootScope.colleges.splice(index, 1);
                        college.mycollege = "1";
                        $rootScope.mycolleges.push(college);
                    }
                })
            }
            $scope.drop = function (college) {
                var d = {};
                d.action = "dropcollege";
                d.collegeid = college.id;
                ealpService.set(d,"scholar2",".mycollege-alert").then(function (data) {
                    if (data.data.responseCode == "200") {
                        var index = $rootScope.mycolleges.indexOf(college);
                        $rootScope.mycolleges.splice(index, 1);
                        $rootScope.colleges.push(college);
                    }
                });

            }
            $scope.addDecision = function (decision) {
                decision.action = "addecision";
                ealpService.set(decision,"scholar2",".mycollege-alert").then(function (data) {
                    if (data.data.responseCode == "200") {
                        decision.edit = "1";
                    }
                })
            }
            $scope.edit = function (field) {
                field.edit = "0";
            }
            $scope.getProgress = function () {

            }
        }
    }
});

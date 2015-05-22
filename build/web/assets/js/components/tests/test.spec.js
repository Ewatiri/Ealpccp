ealpApp.directive("addTest", function (ealpService) {
    return{
        restrict: "E",
        scope: true,
        templateUrl: './partials/tests/addtest.jsp',
        controller: function ($scope) {
            $scope.test = {};
            $scope.ttime = "";

            $scope.$watch("ttime", function () {
                $scope.test.time = moment($scope.ttime).format('HH:mm');
            }, true);

            $scope.addTest = function () {
                $scope.test.date = moment($scope.ttime).format('DD-MM-YYYY');
                $scope.test.action = "addtest";
                ealpService.set($scope.test, 'admin2', ".test1-alert").then(function (data) {
                    if (data.data.responseCode == "200") {
                        $scope.test = {};
                        $scope.ttime = "";
                    }
                })


            }
        }
    }
});
ealpApp.directive("manageTests", function (ealpService) {
    return{
        restrict: "E",
        scope: true,
        templateUrl: './partials/tests/managetest.jsp',
        controller: function ($scope, $rootScope) {
            $scope.currentPage = 1;
            $scope.pageSize = 5;


            $scope.edit = function (ind) {

                var index = ((ind + 1) + ($scope.currentPage - 1) * $scope.pageSize) - 1;
                $rootScope.tests[index].edit = "1";
            }

            $scope.save = function (ind) {
                var index = ((ind + 1) + ($scope.currentPage - 1) * $scope.pageSize) - 1;
                $rootScope.tests[index].date = moment($rootScope.tests[index].ttime).format('DD-MM-YYYY');
                $rootScope.tests[index].time = moment($rootScope.tests[index].ttime).format('HH:mm');
                $rootScope.tests[index].action = "updatetest";
                ealpService.set($rootScope.tests[index], 'admin2', ".test2-alert").then(function (data) {
                    if (data.data.responseCode == "200") {
                        $rootScope.tests[index].edit = "0";
                    }
                });
            }
        }
    }
});
ealpApp.directive("testDates", function (ealpService) {
    return{
        restrict: "E",
        scope: true,
        templateUrl: './partials/tests/testdates.jsp',
        controller: function ($scope, $rootScope) {
            $scope.pageSize = 5;

            $scope.currentPage = 1;
            $scope.currentPage1 = 1;

            $scope.register = function (test) {
                test.action = "registertest";
                ealpService.set(test, 'scholar2', ".test3-alert").then(function (data) {
                    if (data.data.responseCode == "200") {
                        var index = $rootScope.tests2.indexOf(test);
                        $rootScope.tests2.splice(index, 1);
                        $rootScope.tests1.push(test);
                    }
                });
            }

            $scope.unregister = function (test) {
                test.action = "registertest"
                ealpService.set(test, "scholar2", ".test3-alert").then(function (data) {
                    if (data.data.responseCode == "200") {
                        var index = $rootScope.tests1.indexOf(test);
                        $rootScope.tests1.splice(index, 1);
                        $rootScope.tests2.push(test);

                        // console.log($rootScope.events2);
                    }
                });

            }
        }
    }
});
ealpApp.directive("testResults", function (ealpService) {
    return{
        restrict: "E",
        scope: true,
        templateUrl: './partials/tests/testresults.jsp',
        controller: function ($scope, $rootScope) {
            $scope.pageSize = 5;

            $scope.currentPage = 1;
            $scope.selected = {};

            $scope.subjects = ["Mathematics Level 1", "Mathematics Level 2", "Biology E/M", "Chemistry", "Physics"];

            $scope.uploadScore = function () {
                var d = {};
                //console.log($scope.selected.test);
                d.testid = $scope.selected.test.testid;

                if ($scope.selected.test.type == "sat1") {
                    d.s1 = "math";
                    d.score1 = $scope.selected.math;
                    d.s2 = "cr";
                    d.score2 = $scope.selected.cr;
                    d.s3 = "writing";
                    d.score3 = $scope.selected.writing;

                } else if ($scope.selected.test.type == "sat2") {
                    d.s1 = $scope.selected.subj1;
                    d.s2 = $scope.selected.subj2;
                    d.s3 = $scope.selected.subj3;

                    d.score1 = $scope.selected.score1;
                    d.score2 = $scope.selected.score2;
                    d.score3 = $scope.selected.score3;
                } else {
                    d.score = $scope.selected.score;
                }
                if ($scope.selected.test.type == "sat2" || $scope.selected.test.type == "sat1") {
                    d.action = "addsatresult";
                    ealpService.set(d, "scholar2", ".test4-alert").then(function (data) {
                        if (data.data.responseCode == "200") {
                            var index = $rootScope.pastests.indexOf($scope.selected);
                            $rootScope.pastests.splice(index, 1);

                            d.test = $scope.selected.test.title;
                            d.date = $scope.selected.test.tdate;
                            d.type = $scope.selected.test.type;
                            $rootScope.results.push(d);
                        }
                    });
                } else {
                    d.action = "addotheresult";
                    ealpService.set(d, "scholar2", ".test4-alert").then(function (data) {
                        if (data.data.responseCode == "200") {
                            var index = $rootScope.pastests.indexOf($scope.selected);
                            $rootScope.pastests.splice(index, 1);

                            d.test = $scope.selected.test.title;
                            d.date = $scope.selected.test.tdate;
                            d.type = $scope.selected.test.type;
                            $rootScope.results.push(d);
                        }
                    });
                }
            }
        }
    }
});
ealpApp.directive("testList", function (ealpService) {
    return{
        restrict: "E",
        scope: true,
        templateUrl: './partials/tests/testlist.jsp',
        controller: function ($scope) {
            $scope.pageSize = 10;
            $scope.currentPage = 1;

            $scope.showlist = true;
            $scope.stest = {};
            $scope.testscholars = {};

            $scope.getList = function (test) {
                var d = {};
                d.action = "gettesttakers";
                d.testid = test.testid;
                $scope.stest = test;
                ealpService.get(d, "admin2").then(function (data) {
                    if (data.data.responseCode != "400" && data.data.responseCode != "403") {

                        $scope.testscholars = data.data;
                        $scope.showlist = false;
                    }
                });
            }
            $scope.goBack = function () {
                $scope.showlist = true;
            }
            $scope.printTestList = function (test) {
                window.open("reports?action=" + "gettestlist" + "&testid=" + test.testid);

            }
        }
    }
});

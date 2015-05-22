ealpApp.directive("collegeEssays", function () {
    return{
        restrict: 'E',
        transclude: true,
        template: '<div  >' +
                '<div ng-transclude ></div>' +
                '</div>'

    }
})

ealpApp.directive("collegeEssay", function () {
    return{
        restrict: 'E',
        transclude: true,
        template: "<form name='essayForm' ng-submit='commitEssay(field)'>" +
                "<div ng-transclude ></div>" +
                "</form>"
    }
});

ealpApp.directive("collegeProfiles", function () {
    return{
        restrict: 'E',
        transclude: true,
        template: '<div  >' +
                '<div ng-transclude ></div>' +
                '</div>'

    }
})

ealpApp.directive("collegeProfile", function () {
    return{
        restrict: 'E',
        transclude: true,
        template: "<form name='profileForm' ng-submit='addDecision(field)'>" +
                "<div ng-transclude ></div>" +
                "</form>"
    }
});

ealpApp.directive("addCollege", function (ealpService) {
    return{
        restrict: "E",
        scope: true,
        templateUrl: './partials/colleges/addcollege.jsp',
        controller: function ($scope) {
            $scope.college = {};
            $scope.addCollege = function () {
                $scope.college.action = "addcollege";
                ealpService.set($scope.college, "admin2", ".college-alert");
                $scope.college = {};
            }

        }
    }
});
ealpApp.directive("editCollege", function (ealpService) {
    return{
        restrict: "E",
        scope: true,
        templateUrl: './partials/colleges/editcolleges.jsp',
        controller: function ($scope, $rootScope) {
            $scope.currentPage = 1;
            $scope.pageSize = 5;


            $scope.edit = function (ind) {
                var index = ((ind + 1) + ($scope.currentPage - 1) * $scope.pageSize) - 1;
                $rootScope.colleges[index].edit = "1";
            }

            $scope.save = function (ind) {
                var index = ((ind + 1) + ($scope.currentPage - 1) * $scope.pageSize) - 1;
                $rootScope.colleges[index].action = "updatecollege";
                ealpService.set($rootScope.colleges[index], "admin2", ".college-alert2").then(function (data) {
                    if (data.data.responseCode == "200") {
                        $rootScope.colleges[index].edit = "0";
                    }
                })
            }
        }
    }
});
ealpApp.directive("myCollegeEssays", function (ealpService, utility) {
    return{
        restrict: "E",
        scope: true,
        templateUrl: './partials/colleges/collegessays.jsp',
        controller: function ($scope, $rootScope) {
            $scope.currentPage = 1;
            $scope.pageSize = 5;

            $scope.essays = [];
            $scope.showessay = true;

            $scope.mycollege = {};
            $scope.switchView = function (ind) {
                $scope.essays = [];
                $scope.showessay = false;
                var index = ((ind + 1) + ($scope.currentPage - 1) * $scope.pageSize) - 1;
                $scope.mycollege = $rootScope.colleges[index];
                var d = {};
                d.id = $rootScope.colleges[index].id;
                d.action = "getcollegessay";
                ealpService.get(d, "admin2").then(function (data) {
                    if (data.data.responseCode != "400") {
                        $.each(data.data, function (i, dt) {
                            $scope.essays.push(dt);
                        })
                    }
                });

            }
            $scope.addEssay = function () {
                $scope.essays.push({});
            }
            $scope.goBack = function () {
                $scope.showessay = true;
            }
            $scope.removeEssay = function (field) {
                var index = $scope.essays.indexOf(field);
                //console.log(field);
                if (index >= 0) {
                    $scope.essays.splice(index, 1);
                }
            }
            $scope.commitEssay = function (field) {
                var d = {};
                d = field;
                d.college = $scope.mycollege.id;
                d.action = "addcollegessay";
                if (field.req != "" && field.req != null) {
                    ealpService.set(d, "admin2", ".collegessay-alert");
                } else {
                    utility.showAlert(".collegessay-alert", "alert", "Select Reqiured/Not Required", 5000);
                }

            }
            $scope.edit = function (field) {
                field.edit = "1";
            }
            $scope.save = function (field) {
                if (field.req != "" && field.req != null) {
                    field.action = "editcollegessay"
                    ealpService.set(field, "admin2", ".collegessay-alert").then(function (data) {
                        if (data.data.responseCode == "200") {
                            field.edit = "0";
                            field.status = "0";
                        }
                    })
                } else {
                    utility.showAlert(".collegessay-alert", "alert", "Select Reqiured/Not Required", 5000);
                }
            }
            $scope.delete = function (field) {
                var d = {};
                d.id = field.id;
                d.action = "deletecollegessay";
                ealpService.set(d,"admin2",".collegessay-alert").then(function (data) {
                    if (data.data.responseCode == "200") {
                        var index = $scope.essays.indexOf(field);
                        //console.log(field);
                        if (index >= 0) {
                            $scope.essays.splice(index, 1);
                        }
                    }
                })
            }
        }
    }
});
ealpApp.directive("reviewCollegeEssays", function (ealpService) {
    return{
        restrict: "E",
        scope: true,
        templateUrl: './partials/colleges/reviewcollegessays.jsp',
        controller: function ($scope, $rootScope) {
            $scope.currentPage = 1;
            $scope.pageSize = 2;

            $scope.coll = {};
            $scope.showcoll = true;

            $scope.selectCollege = function (ind) {
                $scope.showcoll = false;
                var index = ((ind + 1) + ($scope.currentPage - 1) * $scope.pageSize) - 1;
                $scope.coll = $rootScope.approvalEssays[index];
            }

            $scope.showMessage = function (ind) {
                $scope.coll.essays[ind].edit = "1";
            }
            $scope.approve = function (ind) {
                var d = {};
                d.action = "approveessay";
                d.collid = $scope.coll.collid;
                d.essayid = $scope.coll.essays[ind].essayid;
                ealpService.set(d,"admin2",".essay1-alert").then(function (data) {
                    if (data.data.responseCode == "200") {
                        var index = $scope.coll.essays.indexOf($scope.coll.essays[ind]);
                        //console.log(field);
                        if (index >= 0) {
                            $scope.coll.essays.splice(index, 1);
                        }
                        if ($scope.coll.essays.length == 0) {
                            var i = $rootScope.approvalEssays.indexOf($scope.coll);
                            $rootScope.approvalEssays.splice(i, 1);
                        }

                    }
                });
            }
            $scope.reject = function (ind) {
                var d = {};
                d.action = "rejectessay";
                d.mailto = $scope.coll.essays[ind].addedby1;
                d.subject = $scope.coll.name + " Essay Corrections";
                d.essayid = $scope.coll.essays[ind].essayid;
                d.message = $scope.coll.essays[ind].message;
                ealpService.set(d,"admin2",".essay1-alert").then(function (data) {
                    if (data.data.responseCode == "200") {
                        var index = $scope.coll.essays.indexOf($scope.coll.essays[ind]);
                        //console.log(field);
                        if (index >= 0) {
                            $scope.coll.essays.splice(index, 1);

                        }
                        if ($scope.coll.essays.length == 0) {
                            var i = $rootScope.approvalEssays.indexOf($scope.coll);
                            $rootScope.approvalEssays.splice(i, 1);
                        }
                    }
                });
            }
            $scope.goBack = function () {
                $scope.showcoll = true;
            }
        }
    }
});
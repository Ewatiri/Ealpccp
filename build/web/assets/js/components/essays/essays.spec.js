ealpApp.directive("myEssays", function (ealpService,utility) {
    return{
        restrict: "E",
        scope: true,
        templateUrl: './partials/essays/myessays.jsp',
        controller: function ($scope) {
            $scope.pageSize = 5;
            $scope.currentPage = 1;

            $scope.showcolleges = true;
            $scope.myessays = {};
            $scope.feedback = {};

            $scope.selectedcollege = {};
            $scope.hist = {};
            $scope.goBack = function () {
                $scope.showcolleges = true;
            }
            $scope.getmyEssays = function (college) {
                if (college.name != "Common App") {
                    var d = {};
                    d.id = college.id;
                    var d1 = {};
                    d1.id = college.id;
                    $scope.myessays = {};
                    $scope.feedback = {};
                    d.action = "getmyessays";
                    ealpService.get(d, "scholar2").then(function (data) {
                        if (data.data.responseCode != "400" && data.data.responseCode != "403") {
                            $scope.myessays = data.data;
                            $scope.selectedcollege = college;
                            $scope.showcolleges = false;
                            // console.log($scope.selectedcollege);
                        }
                    });
                    d1.action = "getessaystatus";
                    ealpService.get(d1, "scholar2").then(function (data) {
                        //console.log(d1);
                        if (data.data.responseCode != "400" && data.data.responseCode != "403") {
                            $scope.feedback = data.data;
                        }
                    });
                } else {
                    $scope.myessays = {};
                    //console.log($scope.selectedcollege);
                    var d = {};
                    d.action = "getallcommonapp";
                    ealpService.get(d, "admin2").then(function (data) {
                        if (data.data.responseCode != "400" && data.data.responseCode != "403") {
                            $scope.myessays = data.data;
                            $scope.selectedcollege = {name: "Common App"};
                            $scope.showcolleges = false;
                            // console.log($scope.selectedcollege);
                        }
                    });
                    $scope.feedback = {};
                    ealpService.get({action: "getcommonappstatus"}, "scholar2").then(function (data) {
                        if (data.data.responseCode != "400" && data.data.responseCode != "403") {
                            $scope.feedback = data.data;
                        }
                    });

                }

            }
            $scope.uploadEssay = function (ey) {
                var fd = new FormData();
                if ($scope.selectedcollege.name !== "Common App") {
                    angular.forEach(ey, function (value, key) {
                        if (key == "file") {
                            fd.append(key, value[0]);
                        } else {
                            fd.append(key, value);
                        }
                    });
                } else {
                    angular.forEach(ey, function (value, key) {
                        fd.append("type", "5");
                        if (key == "file") {
                            fd.append(key, value[0]);
                        } else {
                            fd.append(key, value);
                        }
                    });
                }
                ealpService.uploadDocument(fd, "addCollegeEssay1").then(function (data) {

                });

            }
            $scope.checkFeedback = function (ey) {
                var d = {};
                if (ey.name !== "Common App") {
                    d.essayid = ey.essayid;
                    d.action = "checkessayfeedback";
                } else {
                    d.type = "5";
                    d.action = "getdocfeedback";
                }

                ealpService.set(d, "scholar2", ".essayhist-alert").then(function (data) {
                    if (data.data.responseCode == "400" && data.data.responseCode == "403") {
                        ey.feedback = "0";
                        ey.url = data.data.description;
                    }
                })
            }
            $scope.getHist = function (y) {
                var d = {};
                $scope.hist = {};
                $(".essayhist-alert").css("display", "none");
                if (y.name == "Common App") {
                    d.id = "5";
                    d.action = "getdochist";
                } else {
                    d.essayid = y.essayid;
                    d.action = "getessayhist";
                }
                // console.log(y);
                ealpService.get(d, "scholar2").then(function (data) {
                    if (data.data.responseCode != "400" && data.data.responseCode != "403") {
                        $scope.hist = data.data;
                        y.field = "0";
                    } else {
                        utility.showAlert(".essayhist-alert", "alert", data.message, 5000);
                    }
                });
            }

            $scope.deleteDoc = function (h) {
                if (h.type) {
                    h.action = "deletedocument";
                } else {
                    h.action = "deletecollegessay";
                }
                ealpService.set(h,"scholar2",".essayhist-alert").then(function (data) {
                    if (data.data.responseCode == "200") {
                        var index = $scope.hist.indexOf(h);
                        $scope.hist.splice(index, 1);
                    }
                });
            }

        }
    }
});

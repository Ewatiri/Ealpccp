ealpApp.directive("allScholars", function (ealpService) {
    return{
        restrict: "E",
        scope: true,
        templateUrl: './partials/scholars/allscholars.jsp',
        controller: function ($scope, $rootScope) {
            $scope.currentPage = 1;
            $scope.pageSize = 5;

            $scope.subjects = ["Mathematics Level 1", "Mathematics Level 2", "Chemistry", "Biology E/M", "Physics"];
            $scope.showscholar = true;

            $scope.selected = {};
            $scope.selected.test = {};

            $scope.pickScholar = function (ind) {
                var index = ((ind + 1) + ($scope.currentPage - 1) * $scope.pageSize) - 1;
                $rootScope.scholars[index].action = "pickscholar";
                ealpService.set($rootScope.scholars[index], "admin2", ".all-alert").then(function (data) {
                    if (data.data.responseCode == "200") {
                        $rootScope.myscholars.push($rootScope.scholars[index]);
                        var i = $rootScope.scholars.indexOf($rootScope.scholars[index]);
                        //console.log(field);
                        if (i >= 0) {
                            $rootScope.scholars.splice(i, 1);
                        }
                    }
                })
            }
            $scope.selectScholar = function (ind) {
                $scope.showscholar = false;
                var index = ((ind + 1) + ($scope.currentPage - 1) * $scope.pageSize) - 1;
                $scope.selected = $rootScope.scholars[index];
            }
            $scope.uploadScore = function () {
                var d = {};
                //console.log($scope.selected.test);
                d.testid = $scope.selected.test.id;
                d.sid = $scope.selected.sid;
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
                    d.action = "addsatscore";
                    ealpService.set(d, "admin2", ".score-alert").then(function (data) {
                        $scope.selected.test = {};
                    });
                } else {
                    d.action = "addotherscore";
                    ealpService.set(d, "admin2", ".score-alert").then(function (data) {
                        $scope.selected.test = {};
                    });
                }

            }

            $scope.goBack = function () {
                $scope.showscholar = true;
            }
        }
    }
});

ealpApp.directive("myScholars", function (ealpService) {
    return{
        restrict: "E",
        scope: true,
        templateUrl: './partials/scholars/myscholars.jsp',
        controller: function ($scope, $rootScope) {
            $scope.currentPage = 1;
            $scope.pageSize = 5;
            $scope.programs = [];
            $scope.my = true;

            $scope.controls = false;
            $scope.controls1 = false;
            $scope.testresults = {};
            $scope.sdocuments = [];
            $scope.essays = [];
            $scope.selected = {};

            $scope.pasttests = {};
            $scope.scolleges = {};

            $scope.subjects = ["Mathematics Level 1", "Mathematics Level 2", "Chemistry", "Biology E/M", "Physics"];

            $scope.stest = {};
            $scope.stest.test = {};

            $scope.showTestScores = function () {
                $scope.controls = true;
                $scope.controls1 = false;
            }
            $scope.showuploadTest = function () {
                $scope.controls = false;
                $scope.controls1 = true;
            }

            $scope.initScholar = function (scholar) {
                if (scholar.status == "10") {
                    $scope.programs = ["MCF Only", "Reg + MCF", "Drop from the program"];
                } else if (scholar.status == "20") {
                    $scope.programs = ["Reg Only", "Reg + MCF", "Drop from the program"];
                } else {
                    $scope.programs = ["MCF Only", "Reg Only", "Drop from the program"];
                }
                $scope.essays = [];
                $scope.sdocuments = [];
                scholar.action = "getdocs";
                ealpService.get(scholar, "admin2").then(function (data) {
                    if (data.data.responseCode != "400" && data.data.responseCode != "403") {
                        $.each(data.data, function (i, dt) {
                            if (dt.essayid) {
                                $scope.essays.push(dt);
                            } else {
                                $scope.sdocuments.push(dt);
                            }
                            //console.log($scope.sdocuments);
                        });
                    }
                });

                $scope.selected = scholar;
                $scope.my = false;
                scholar.action = "getpasttest";
                ealpService.get(scholar, "admin2").then(function (data) {
                    if (data.data.responseCode != "400" && data.data.responseCode != "403") {
                        $scope.pasttests = data.data;
                    }
                });
                scholar.action = "gettestscores";
                ealpService.get(scholar, "admin2").then(function (data) {
                    if (data.data.responseCode != "400" && data.data.responseCode != "403") {
                        $scope.testresults = data.data;
                    }
                });
                scholar.action = "getmyscholarcolleges";
                ealpService.get(scholar, "admin2").then(function (data) {
                    if (data.data.responseCode != "400" && data.data.responseCode != "403") {
                        $scope.scolleges = data.data;
                    }
                });

            }

            $scope.uploadDocument = function (document) {
                document.sid = $scope.selected.sid;

                var fd = new FormData();
                angular.forEach(document, function (value, key) {
                    if (key == "file") {
                        fd.append(key, value[0]);
                    } else {
                        fd.append(key, value);
                    }
                });
                ealpService.uploadDocument(fd, "addDocument1", ".mydocs-alert");
                //adminService.uploadNewDocument(fd);
            }
            $scope.uploadEssay = function (document) {
                document.sid = $scope.selected.sid;
                var fd = new FormData();
                angular.forEach(document, function (value, key) {
                    if (key == "file") {
                        fd.append(key, value[0]);
                    } else {
                        fd.append(key, value);
                    }
                });
                ealpService.uploadDocument(fd, "addCollegeEssay2", ".myes-alert");
            }
            $scope.dropScholar = function () {
                var i = $rootScope.myscholars.indexOf($scope.selected);
                $rootScope.myscholars[i].action = "dropscholar";
                ealpService.set($rootScope.myscholars[i],"admin2",".sprofile-alert").then(function (data) {
                    if (data.data.responseCode == "200") {
                        $rootScope.scholars.push($rootScope.myscholars[i]);

                        if (i >= 0) {
                            $rootScope.myscholars.splice(i, 1);
                        }
                    }
                })
            }
            $scope.uploadScore = function () {
                var d = {};
                //console.log($scope.selected.test);
                d.testid = $scope.stest.test.id;
                d.sid = $scope.selected.sid;
                if ($scope.stest.test.type == "sat1") {
                    d.s1 = "math";
                    d.score1 = $scope.stest.math;
                    d.s2 = "cr";
                    d.score2 = $scope.stest.cr;
                    d.s3 = "writing";
                    d.score3 = $scope.stest.writing;

                } else if ($scope.stest.test.type == "sat2") {
                    d.s1 = $scope.stest.subj1;
                    d.s2 = $scope.stest.subj2;
                    d.s3 = $scope.stest.subj3;

                    d.score1 = $scope.stest.score1;
                    d.score2 = $scope.stest.score2;
                    d.score3 = $scope.stest.score3;
                } else {
                    d.score = $scope.stest.score;
                }

                if ($scope.stest.test.type == "sat2" || $scope.stest.test.type == "sat1") {
                    d.action = "addsatscore";
                    ealpService.set(d,"admin2",".score-alert").then(function (data) {
                        $scope.stest.test = {};
                    });
                } else {
                    d.action = "addotherscore";
                    ealpService.set(d,"admin2",".score-alert").then(function (data) {
                        $scope.stest.test = {};
                    });
                }

            }
            $scope.setStatus = function () {
                var d = {};
                $scope.programs = ["Reg Only", "Reg + MCF", "Drop from the program"];
                d.action = "setstatus";
                d.reason = $scope.selected.reason;
                d.sid = $scope.selected.sid;
                if ($scope.selected.prog == "MCF Only") {
                    d.status = "20";
                    $scope.selected.status = 20;
                } else if ($scope.selected.prog == "Reg Only") {
                    d.status = "10";
                    $scope.selected.status = 10;
                } else if ($scope.selected.prog == "Reg + MCF") {
                    d.status = "11";
                    $scope.selected.status = 11;
                } else if ($scope.selected.prog == "Drop from the program") {
                    d.status = "-1";
                }
                ealpService.set(d,"admin2",".drop-alert").then(function (data) {
                    if (data.data.responseCode == "200") {

                    }
                })
            }
        }
    }
});


ealpApp.directive("scholarReport", function (ealpService) {
    return{
        restrict: "E",
        scope: true,
        templateUrl: './partials/reports/scholars1report.jsp',
        controller: function ($scope) {
            $scope.pageSize = 5;
            $scope.currentPage1 = 1;
            $scope.currentPage = 1;
            $scope.currentPage2 = 1;

            $scope.accepted = false;
            $scope.all = false;
            $scope.rej = false
            $scope.msg = "";

            $scope.acceptedscholar = [];
            $scope.rscholars = {};
            $scope.applicants = {};

            $scope.getAcceptedScholars = function () {

                ealpService.get({action: "getacceptedreport"}, "admin1").then(function (data) {
                    $scope.msg = "";
                    if (data.data.responseCode != "400" && data.data.responseCode != "403") {
                        $scope.acceptedscholar = [];
                        $scope.accepted = true;
                        $scope.all = false;
                        $scope.rej = false;

                        $.each(data.data, function (i, dt) {
                            $scope.acceptedscholar.push(dt);
                        })

                    } else {
                        $scope.msg = "accepted";
                        $scope.accepted = false;
                        $scope.all = false;
                        $scope.rej = false;
                    }
                });
            }
            $scope.getRScholars = function () {
                ealpService.get({action: "getrscholars"}, "admin1").then(function (data) {
                    $scope.msg = "";
                    if (data.data.responseCode != "400" && data.data.responseCode != "403") {
                        $scope.rscholars = data.data;
                        $scope.rej = true;
                        $scope.accepted = false;
                        $scope.all = false;
                    } else {
                        $scope.msg = "re";
                        $scope.rej = false;
                        $scope.accepted = false;
                        $scope.all = false;
                    }
                });
            }
            $scope.getAllApplicants = function () {
                ealpService.get({action: "getallapplicants"}, "admin1").then(function (data) {
                    $scope.msg = "";
                    if (data.data.responseCode != "400" && data.data.responseCode != "403") {
                        $scope.applicants = data.data;
                        $scope.all = true;
                        $scope.accepted = false;
                        $scope.rej = false;
                    } else {
                        $scope.msg = "al";
                        $scope.all = false;
                        $scope.accepted = false;
                        $scope.rej = false;
                    }
                });
            }
            $scope.getAcceptedPdf = function () {
                window.open("reports?action=" + "getacceptedlist");

            }
            $scope.getRejPdf = function () {
                window.open("reports?action=" + "getrejlist");

            }
            $scope.getAllPdf = function () {
                window.open("reports?action=" + "allapplicants");

            }

            $scope.acceptScholar = function (scholar) {
                scholar.action = "rejaccept";
                ealpService.set(scholar, "admin1", ".rejaccept-alert").then(function (data) {
                    if (data.data.responseCode == "200") {
                        var index = $scope.rscholars.indexOf(scholar);
                        $scope.rscholars.splice(index, 1);
                        $scope.acceptedscholar.push(scholar);
                    }
                });
            }
        }
    }
});

ealpApp.directive("applicationAnalysis", function (ealpService, utility) {
    return{
        restrict: "E",
        scope: true,
        templateUrl: './partials/reports/applicationAnalysis.jsp',
        controller: function ($scope) {
            $scope.analysis = {};
            $scope.s = false;
            $scope.getAnalysis = function () {
                ealpService.get({action: "getanalysis1"}, "admin1").then(function (data) {
                    if (data.data.responseCode != "400" && data.data.responseCode != "403") {

                        $scope.s = true;
                        $.each(data.data, function (i, dt) {
                            $scope.analysis = dt;
                        })

                    } else {
                        $scope.s = false;
                        utility.showAlert(".analysis1-alert", "alert", data.data.message, 5000);
                    }
                });
            }
            $scope.getAnalysis1Pdf = function () {
                window.open("reports?action=" + "getanalysis1");

            }
        }
    }
});
ealpApp.directive("mod2ScholarReports", function (ealpService) {
    return{
        restrict: "E",
        scope: true,
        templateUrl: './partials/reports/mod2scholareport.jsp',
        controller: function ($scope) {
            $scope.view = "menu";
            $scope.pageSize = 20;
            $scope.currentPage = 1;
            $scope.currentPage1 = 1;

            $scope.sreports = {};
            $scope.empty = true;
            $scope.type = "";
            $scope.parameters = false;
            $scope.college = {};
            $scope.rptitle = "";

            $scope.getScholarsbyType = function (type) {
                $scope.sreports = {};
                $scope.empty = true;
                $scope.view = "report1";
                var d = {};
                d.action = "getscholarbytype";
                d.type = type;
                $scope.type = type;

                ealpService.getWithAlert(d,"admin2",".sreport1-alert").then(function (data) {
                    if (data.data.responseCode != "400" && data.data.responseCode != "403") {
                        $scope.sreports = data.data;
                        $scope.empty = false;
                    } else {
                        $scope.empty = true;
                    }
                })
            }
            $scope.goBack = function () {
                $scope.view = "menu";
            }
            $scope.getScholarTypePdf = function () {
                window.open("reports?action=" + "getscholartypereport" + "&type=" + $scope.type);
            }
            $scope.setParams = function (type) {
                $scope.college.type = type;
                $scope.parameters = true;
            }
            $scope.getScholarsbyColleges = function () {
                $scope.sreports = {};
                var d = {};
                d.action = "getscholarsbycollege";
                d.college = $scope.college.name;
                d.type = $scope.college.type;
                ealpService.getWithAlert(d,"admin2",".sreport11-alert").then(function (data) {
                    if (data.data.responseCode != "400" && data.data.responseCode != "403" && data.data.responseCode != "401") {
                        $scope.sreports = data.data;
                        $scope.empty = false;
                        $scope.view = "report2";
                    } else {
                        $scope.empty = true;
                    }

                });
            }
            $scope.getScholarCollegePdf = function () {
                window.open("reports?action=" + "getapplicationtypereport" + "&college=" + $scope.college.name + "&type=" + $scope.college.type);
            }
            $scope.getAppStatus = function (type) {
                $scope.sreports = {};

                $scope.empty = true;
                $scope.view = "report3";
                var d = {};
                d.action = "getappstatus";
                d.type = type;
                $scope.type = type;

                ealpService.getWithAlert(d,"admin2",".sreport3-alert").then(function (data) {
                    if (data.data.responseCode != "400" && data.data.responseCode != "403") {
                        $scope.empty = false;
                        $scope.sreports = data.data;
                    } else {
                        $scope.empty = true;
                    }
                });
            }
            $scope.getStatusPdf = function () {
                window.open("reports?action=" + "getappstatusreport" + "&type=" + $scope.type);
            }
            //ng-click="getAppStatus('all')"

        }
    }
});
ealpApp.directive("testReports", function (ealpService) {
    return{
        restrict: "E",
        scope: true,
        templateUrl: './partials/reports/testreports.jsp',
        controller: function ($scope) {

            $scope.showlist = true;
            $scope.pageSize = 10;

            $scope.pageSize2 = 20;
            $scope.currentPage = 1;
            $scope.currentPage1 = 1;
            $scope.currentPage2 = 1;

            $scope.stest = {};
            $scope.results = {};
            $scope.analysis = [];
            $scope.getList = function (test) {
                var d = {};
                d.action = "getresultlist";
                d.testid = test.testid;
                $scope.stest = test;
                ealpService.get(d,"admin2").then(function (data) {
                    if (data.data.responseCode != "400" && data.data.responseCode != "403") {
                        $scope.showlist = false;
                        $scope.results = data.data;
                    }
                });
                var k = {};
                k.action = "gettestanalysis";
                k.testid = test.testid;
                ealpService.get(k,"admin2").then(function (data) {
                    if (data.data.responseCode != "400") {
                        $.each(data.data, function (i, dt) {
                            $scope.analysis = dt;
                        })

                    }
                });
            }
            $scope.goBack = function () {
                $scope.showlist = true;
            }
            $scope.printTestResults = function (test) {
                window.open("reports?action=" + "gettestreport" + "&testid=" + test.testid);

            }
        }
    }
});
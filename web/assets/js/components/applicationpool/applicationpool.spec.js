ealpApp.directive("appPool", function (ealpService,utility) {
    return{
        restrict: 'E',
        scope: true,
        templateUrl: "./partials/applicationpool/pool.jsp",
        controller: function ($scope, $rootScope) {
            $scope.currentPage = 1;
            $scope.pageSize = 10;

            $scope.review = {};

            $scope.hi = true;
            $scope.app = {}
            //$scope.applicants = [{name: 'Jake Spidermonkey'}, {name: 'Adam Lyon'}, {name: 'Ross Geller'}, {name: 'Chandler Bing'}];
            $scope.getDetails = function (sid) {
                $scope.hi = false;
                loadTabs();
                $scope.review.sid = sid;
                //$scope.app.name = name;
                // loadfamily 
                $scope.family = {};
                $scope.school = {};
                $scope.transcript = {};
                $scope.holiday = {};
                $scope.essays = [];
                $scope.honors = [];
                $scope.vol = [];
                
                var d1 = {};
                d1.action = "getfamily";
                d1.sid = sid;
                
                
                ealpService.get(d1,"admin1").then(function (data) {
                    //    console.log(sid);
                    $.each(data.data, function (i, dt) {
                        $scope.family.orphan = dt.orphan;
                        $scope.family.orphan1 = dt.orphan1;
                        if (dt.orphan == "1") {
                            $scope.family.parent1 = dt.parent1;
                        } else if (dt.orphan == "2") {
                            $scope.family.parent1 = dt.parent1;
                            $scope.family.parent2 = dt.parent2;
                        }
                        $scope.family.income = dt.income;
                        $scope.family.siblings = dt.siblings;
                        if (dt.siblings > "0") {
                            $scope.family.siblingsinfo = dt.siblingsinfo;
                        }
                        $scope.family.location = dt.location;
                        $scope.family.livehome = dt.livehome;
                        if (dt.livehome == "1") {
                            $scope.family.hreason = dt.hreason;
                        }
                        $scope.family.payfees = dt.payfees;
                        if (dt.payfees == "parents") {
                            $scope.family.beenhome = dt.beenhome;
                        } else {
                            $scope.family.sponsor = dt.sponsor;
                            $scope.family.sponsordesc = dt.sponsordesc;
                        }


                    });
                });
                var d2 = {};
                d2.sid = sid;
                d2.action = "gettranscript";
                ealpService.get(d2,"admin1").then(function (data) {
                    $.each(data.data, function (i, dt) {
                        $scope.transcript.swa = utility.getGrade(dt.swa);
                        $scope.transcript.eng = utility.getGrade(dt.eng);
                        $scope.transcript.math = utility.getGrade(dt.math);
                        if (dt.chem) {
                            $scope.transcript.chem = utility.getGrade(dt.chem);
                        }
                        if (dt.bio) {
                            $scope.transcript.bio = utility.getGrade(dt.bio);
                        }
                        if (dt.phy) {
                            $scope.transcript.phy = utility.getGrade(dt.phy);
                        }
                        if (dt.geog) {
                            $scope.transcript.geog = utility.getGrade(dt.geog);
                        }
                        if (dt.hist) {
                            $scope.transcript.hist = utility.getGrade(dt.hist);
                        }
                        if (dt.rs) {
                            $scope.transcript.rs = utility.getGrade(dt.rs);
                        }
                        $scope.transcript.elec = dt.techsubj;
                        $scope.transcript.egrade = utility.getGrade(dt.tech);
                        $scope.transcript.pop = dt.pop;
                        $scope.transcript.pos = dt.pos;
                    });
                });
                var d3 = {};
                d3.sid = sid;
                d3.action = "getschool";
                ealpService.get(d3,"admin1").then(function (data) {
                    $.each(data.data, function (i, dt) {
                        $scope.school.type = dt.type;
                        $scope.school.score = dt.score;
                    });
                });
                var d4 = {};
                d4.sid = sid;
                d4.action = "gethonors";
                ealpService.get(d4,"admin1").then(function (data) {
                    $.each(data.data, function (i, dt) {
                        $scope.honors.push(dt);
                    });
                });
                
                var d5 = {};
                d5.sid = sid;
                d5.action = "getvol";
                ealpService.get(d5,"admin1").then(function (data) {
                    $.each(data.data, function (i, dt) {
                        $scope.vol.push(dt);
                    });
                });
                
                var d6 = {};
                d6.sid = sid;
                d6.action = "getessays1";
                ealpService.get(d6,"admin1").then(function (data) {
                    $.each(data.data, function (i, dt) {
                        $scope.essays.push(dt);
                    });
                });
                
                var d7 = {};
                d7.sid = sid;
                d7.action = "getholiday";
                ealpService.get(d7,"admin1").then(function (data) {
                    $.each(data.data, function (i, dt) {
                        $scope.holiday.interest = dt.interest;
                        $scope.holiday.hol = dt.hol;
                    });
                });
            }

            $scope.saveReview = function () {
                $scope.review.action = "savereview";
                if ($scope.review.comment == "" || $scope.review.comment == null) {
                    utility.showAlert(".review-alert", "alert", "Make a comment", 5000);
                } else if ($scope.review.s1 == "" || $scope.review.s1 == null) {
                    utility.showAlert(".review-alert", "alert", "Missing score - Academic Section", 5000);
                } else if ($scope.review.s2 == "" || $scope.review.s2 == null) {
                    utility.showAlert(".review-alert", "alert", "Missing Score - Activities Section", 5000);
                } else if ($scope.review.s3 == "" || $scope.review.s3 == null) {
                    utility.showAlert(".review-alert", "alert", "Missing Score - Essays Section", 5000);
                } else if ($scope.review.mcf == "" || $scope.review.mcf == null) {
                    utility.showAlert(".review-alert", "alert", "Missing MCF qualification", 5000);
                } else {
                    ealpService.set($scope.review,"admin1",".review-alert").then(function (data) {
                        if (data.data.responseCode == 200) {
                            $scope.hi = true;
                           
                            ealpService.get({action:"getapplicants"},"admin1").then(function (data) {
                                if (data.data.responseCode != "403" && data.data.responseCode != "400"){
                                    $rootScope.applications = data.data;
                                }
                                
                                //activateAccordions();
                            });
                        }
                    });
                }

            }
        }
    }
});

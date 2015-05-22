ealpApp.directive("myHonorsForm", function () {
    return{
        restrict: 'E',
        transclude: true,
        template: "<form name='honorsForm' ng-submit='submitHonors()'>" +
                "<div ng-transclude ></div>" +
                "</form>"
    }
});
ealpApp.directive("myHonorsFields", function () {
    return{
        restrict: 'E',
        transclude: true,
        template: '<div ng-form="honorsFieldsForm" >' +
                '<div ng-transclude ></div>' +
                '</div>'

    }
})

ealpApp.directive("myHonorsField", function () {
    return{
        restrict: 'E',
        transclude: true,
        template: '<div ng-form="honorsFieldForm">' +
                '<div ng-transclude></div>' +
                '</div>'
    }
});

ealpApp.directive("volunteerForm", function () {
    return{
        restrict: 'E',
        transclude: true,
        template: "<form name='volForm' ng-submit = 'submitVol()'>" +
                "<div ng-transclude ></div>" +
                "</form>"
    }
});

ealpApp.directive("volunteerFields", function () {
    return{
        restrict: 'E',
        transclude: true,
        template: '<div ng-form="volFieldsForm" >' +
                '<div ng-transclude ></div>' +
                '</div>',
    }
});

ealpApp.directive("volunteerField", function () {
    return{
        restrict: 'E',
        transclude: true,
        template: '<div ng-form="volFieldForm">' +
                '<div ng-transclude></div>' +
                '</div>'
    }
});

ealpApp.directive('scholarApplication', function (utility, ealpService) {
    return{
        restrict: 'E',
        templateUrl: './partials/application/application.jsp',
        controller: function ($scope) {
            $scope.appstate = {};
            //$scope.sid = "";
            //form data
            $scope.frmPdets = {};
            $scope.frmAdets = {};

            $scope.frmgrades = {};
            $scope.frmhols = {};
            $scope.frmessay = {};

            $scope.essay1 = {};
            $scope.essay2 = {};
            $scope.essay3 = {};

            $scope.myhonorsdata = [];

            $scope.myhonorsdata.fields = [];

            $scope.addHonors = function () {
                $scope.myhonorsdata.fields.push({});
            }
            $scope.removeHonor = function (field) {
                var index = $scope.myhonorsdata.fields.indexOf(field);
                if (index >= 0) {
                    $scope.myhonorsdata.fields.splice(index, 1);
                }
            }

            // extras

            $scope.voldata = [];
            $scope.voldata.fields = [];

            $scope.addVol = function () {
                $scope.voldata.fields.push({});
            }
            $scope.removeVol = function (field) {
                var index = $scope.voldata.fields.indexOf(field);
                if (index >= 0) {
                    $scope.voldata.fields.splice(index, 1);
                }
            }
            $scope.dob = "";
            $scope.e1 = false;
            $scope.e2 = false;
            $scope.e3 = false;

            $scope.appstate.s7 = false;

            $scope.init = function () {

                ealpService.get({action: "getpdets"}, "scholar1").then(function (data) {
                    $.each(data.data, function (i, dt) {
                        $scope.frmPdets.sid = dt.sid;
                        if (dt.email) {
                            $scope.frmPdets.email = dt.email;
                        }
                        if (dt.mname) {

                            $scope.frmPdets.mname = dt.mname;
                            $scope.frmPdets.fname = dt.fname;

                            $scope.frmPdets.sname = dt.surname;

                            $scope.dob = dt.dob;
                            $scope.frmPdets.phone = dt.phone;

                            $scope.frmPdets.siblingsno = parseInt(dt.siblings, 10);


                            $scope.frmPdets.orphan = dt.orphan;

                            $scope.frmPdets.income = dt.income;

                            $scope.frmPdets.parent1 = dt.parent1;

                            $scope.frmPdets.parent2 = dt.parent2;

                            $scope.frmPdets.livehome = dt.home;

                            $scope.frmPdets.location = dt.location;

                            $scope.frmPdets.homereason = dt.homereason;

                            $scope.frmPdets.siblingso = dt.siblingso;
                        }
                        if (dt.orphan) {
                            $scope.appstate.s1 = 1;
                        }
                    });
                    // $scope.frmPdets = data.data;
                });

                ealpService.get({action: "getschool"}, "scholar1").then(function (data) {
                    if (data.data.responseCode != "400") {
                        $.each(data.data, function (i, dt) {
                            $scope.frmAdets.name = dt.name;
                            if (dt.type1) {
                                $scope.appstate.s2 = 1;
                            }
                            $scope.frmAdets.type1 = dt.type1;
                            $scope.frmAdets.type2 = dt.type2;
                            $scope.frmAdets.type3 = dt.type3
                            $scope.frmAdets.ptel = dt.mobile;
                            $scope.frmAdets.tel = dt.tel;
                            $scope.frmAdets.county = dt.county;
                            $scope.frmAdets.pname = dt.principal;
                            $scope.frmAdets.score = parseFloat(dt.score);
                            $scope.frmAdets.desc = dt.desc;

                            $scope.frmAdets.payfees = dt.fees;
                            $scope.frmAdets.sponsor = dt.sponsor;
                            $scope.frmAdets.beenhome = dt.been;

                        });
                    }
                })
                ealpService.get({action: "gettranscript"}, "scholar1").then(function (data) {
                    if (data.data.responseCode != "400") {
                        $scope.appstate.s3 = 1;
                        $.each(data.data, function (i, dt) {
                            $scope.frmgrades.eng = {grade: "", points: parseInt(dt.eng)};
                            $scope.frmgrades.math = {grade: "", points: parseInt(dt.math)};
                            $scope.frmgrades.swa = {points: parseInt(dt.swa)};
                            $scope.frmgrades.tech = {points: parseInt(dt.tech)};
                            $scope.frmgrades.techsubj = dt.techsubj;
                            $scope.frmgrades.phy = {points: parseInt(dt.phy)};
                            $scope.frmgrades.chem = {points: parseInt(dt.chem)};
                            $scope.frmgrades.bio = {points: parseInt(dt.bio)};
                            $scope.frmgrades.geog = {points: parseInt(dt.geog)};
                            $scope.frmgrades.hist = {points: parseInt(dt.hist)};
                            $scope.frmgrades.rs = {points: parseInt(dt.rs)};
                            $scope.frmgrades.pop = parseInt(dt.pop);
                            $scope.frmgrades.pos = parseInt(dt.pos);
                        });
                    }
                });

                ealpService.get({action: "gethonors"}, "scholar1").then(function (data) {

                    if (data.data.responseCode != "400") {
                        $.each(data.data, function (i, dt) {
                            $scope.myhonorsdata.fields.push({level: dt.class, pos: dt.desc, level2: dt.level});
                            // console.log($scope.myhonorsdata.fields);
                        });
                        $scope.appstate.s4 = 1;
                    }
                });

                ealpService.get({action: "getextras"}, "scholar1").then(function (data) {
                    if (data.data.responseCode != "400") {
                        $.each(data.data, function (i, dt) {
                            $scope.voldata.fields.push({lvl: dt.class, desc: dt.desc});
                        });
                        $scope.appstate.s5 = 1;
                    }
                });

                ealpService.get({action: "gethols"}, "scholar1").then(function (data) {
                    if (data.data.responseCode != "400") {
                        $.each(data.data, function (i, dt) {
                            $scope.frmhols.hol = dt.hol;
                            $scope.frmhols.interest = dt.interest;
                        });
                        $scope.appstate.s6 = 1;
                    }
                });

                ealpService.get({action: "getessays"}, "scholar1").then(function (data) {
                    if (data.data.responseCode != "400") {
                        $.each(data.data, function (i, dt) {
                            if (dt.essay == "2") {
                                $scope.e2 = true;
                                $scope.essay2.url = dt.url;
                            } else if (dt.essay == "3") {
                                $scope.e3 = true;
                                $scope.essay3.url = dt.url;
                            } else {
                                $scope.e1 = true;
                                $scope.essay1.essayid = dt.essay;
                                $scope.essay1.url = dt.url;
                            }
                        });
                    }
                });

                ealpService.get({action: "checksubmit"}, "scholar1").then(function (data) {
                    if (data.data.responseCode != '403') {
                        $scope.appstate.s7 = false;
                    } else {
                        $scope.appstate.s7 = true;
                    }
                });
            }

            $scope.level = ["Form 1", "Form 2", "Form 3", "Form 4"];
            $scope.rec = ["National", "County", "District"];

            // grades
            $scope.grades = [{grade: 'Pick a grade', points: ''}, {grade: 'A', points: 12}, {grade: 'A-', points: 11}, {grade: 'B+', points: 10}, {grade: "B", points: 9}, {grade: 'B-', points: 8}, {grade: 'C+', points: 7}, {grade: 'C', points: 6}, {grade: "C-", points: 5}, {grade: 'D', points: 4}];
            $scope.picked = 0;
            var c = 0;
            $scope.$watch('frmgrades', function () {
                if (!jQuery.isEmptyObject($scope.frmgrades)) {
                    //console.log($scope.frmgrades);
                    $scope.frmgrades.points = 0;
                    c = 0;
                    $.each($scope.frmgrades, function (i, dt) {
                        if (($.isNumeric(dt.points)) && (dt != null)) {
                            $scope.frmgrades.points += dt.points;
                            c += 1;
                            //console.log(dt);
                        }
                    });

                    if ((($scope.frmgrades.chem) && $.isNumeric($scope.frmgrades.chem.points)) && (($scope.frmgrades.bio) && $.isNumeric($scope.frmgrades.bio.points)) && (($scope.frmgrades.phy) && $.isNumeric($scope.frmgrades.phy.points))) {
                        if (($scope.frmgrades.chem.points < $scope.frmgrades.bio.points) && ($scope.frmgrades.chem.points < $scope.frmgrades.phy.points)) {
                            $scope.frmgrades.points -= $scope.frmgrades.chem.points;
                        } else if (($scope.frmgrades.bio.points < $scope.frmgrades.chem.points) && ($scope.frmgrades.bio.points < $scope.frmgrades.phy.points)) {
                            $scope.frmgrades.points -= $scope.frmgrades.bio.points;
                        } else if (($scope.frmgrades.phy.points < $scope.frmgrades.bio.points) && ($scope.frmgrades.phy.points < $scope.frmgrades.chem.points)) {
                            $scope.frmgrades.points -= $scope.frmgrades.phy.points;
                        } else {
                            $scope.frmgrades.points -= $scope.frmgrades.phy.points;
                        }
                    } else if ((($scope.frmgrades.geog) && $.isNumeric($scope.frmgrades.geog.points)) && (($scope.frmgrades.hist) && $.isNumeric($scope.frmgrades.hist.points))) {
                        if ($scope.frmgrades.geog.points > $scope.frmgrades.hist.points) {
                            $scope.frmgrades.points -= $scope.frmgrades.hist.points;
                        } else {
                            $scope.frmgrades.points -= $scope.frmgrades.geog.points;
                        }
                    } else if ((($scope.frmgrades.geog) && $.isNumeric($scope.frmgrades.geog.points)) && (($scope.frmgrades.rs) && $.isNumeric($scope.frmgrades.rs.points))) {
                        if ($scope.frmgrades.geog.points > $scope.frmgrades.rs.points) {
                            $scope.frmgrades.points -= $scope.frmgrades.rs.points;
                        } else {
                            $scope.frmgrades.points -= $scope.frmgrades.geog.points;
                        }
                    } else if ((($scope.frmgrades.hist) && $.isNumeric($scope.frmgrades.hist.points)) && (($scope.frmgrades.rs) && $.isNumeric($scope.frmgrades.rs.points))) {
                        if ($scope.frmgrades.hist.points > $scope.frmgrades.rs.points) {
                            $scope.frmgrades.points -= $scope.frmgrades.rs.points;
                        } else {
                            $scope.frmgrades.points -= $scope.frmgrades.hist.points;
                        }
                    }
                    //console.log(c);
                    if (c > 9) {
                        utility.showAlert(".transcript-alert", "alert", "The number of subject selected is not valid", 5000);
                    }
                    //$scope.frmgrades.points = $scope.frmgrades.swa.points +$scope.frmgrades.eng.points;	
                }
            }, true);

            $scope.addPdets = function () {
                $scope.frmPdets.action = "addpdets";
                $scope.frmPdets.dob = moment($scope.dob).format('DD/MM/YYYY');
                //console.log($scope.frmPdets.dob);
                ealpService.set($scope.frmPdets, "scholar1", ".pdets-alert").then(function (data) {
                    if (data.data.responseCode == "200") {
                        $scope.appstate.s1 = 1;
                    }
                })
            }
            $scope.addSchoolinfo = function () {
                $scope.frmAdets.sid = $scope.frmPdets.sid;
                $scope.frmAdets.action = "addschool";

                ealpService.set($scope.frmAdets, "scholar1", ".adets-alert").then(function (data) {
                    if (data.data.responseCode == "200") {
                        $scope.appstate.s2 = 1;
                    } else {
                        $scope.appstate.s2 = 0;
                    }
                });

            }

            $scope.addTranscript = function () {
                $scope.frmgrades.action = "addtranscript";
                $scope.frmgrades.sid = $scope.frmPdets.sid;
                if ((c <= 9) && ($scope.frmgrades.pos <= $scope.frmgrades.pop)) {
                    ealpService.set($scope.frmgrades, "scholar1", ".transcript-alert").then(function (data) {
                        if (data.data.responseCode == "200") {
                            $scope.appstate.s3 = 1;
                        }
                    });

                } else {
                    if (c > 9) {
                        utility.showAlert(".transcript-alert", "alert", "The number of subject selected is not valid", 5000);
                    } else {
                        utility.showAlert(".transcript-alert", "alert", "Uhm... position is higher than the class population", 5000);
                    }
                }
            }

            $scope.submitHonors = function () {
                $scope.myhonorsdata.fields.action = "addhonors";
                var mydata = {};
                mydata.action = "addhonors";
                mydata.fields = [];
                $.each($scope.myhonorsdata.fields, function (i, dt) {
                    mydata.fields.push(dt);
                })
                //console.log(data);
                ealpService.setActivities(mydata, ".honors-alert").then(function (data) {
                    if (data.data.responseCode == "200") {
                        $scope.appstate.s4 = 1;
                    }
                });
            }

            $scope.submitVol = function () {
                //$scope.voldata.fields.action = "";
                var mydata = {};
                mydata.action = "addextras";
                mydata.fields = [];
                $.each($scope.voldata.fields, function (i, dt) {
                    mydata.fields.push(dt);
                })
                //console.log(data);
                ealpService.setActivities(mydata, ".vol-alert").then(function (data) {
                    if (data.data.responseCode == "200") {
                        $scope.appstate.s5 = 1;
                    }
                });

            }
            $scope.addHols = function () {
                $scope.frmhols.action = "addhols";
                ealpService.set($scope.frmhols, "scholar1", ".hols-alert").then(function (data) {
                    if (data.data.responseCode == "200") {
                        $scope.appstate.s6 = 1;
                    }
                })

            }

            /*$scope.addEssays = function () {
             $scope.frmessay.action = "addres";
             var fd = new FormData();
             angular.forEach($scope.frmessay, function (value, key) {
             if (key == "essay1") {
             fd.append(key, value[0]);
             } else if (key == "essay2") {
             fd.append("essay2", "2");
             fd.append(key, value[0]);
             } else if (key == "essay3") {
             fd.append("essay3", "3");
             fd.append(key, value[0]);
             } else {
             fd.append(key, value);
             }
             
             })
             applicationService.uploadEssay(fd);
             
             }*/

            $scope.deleteEssay = function (esid) {
                var d = {};
                d.action = "deleteessay";
                if (esid == '1') {
                    d.essayid = $scope.essay1.essayid
                    d.url = $scope.essay1.url;
                } else if (esid == '2') {
                    d.essayid = "2";
                    d.url = $scope.essay2.url;
                } else if (esid == '3') {
                    d.essayid = "3";
                    d.url = $scope.essay3.url;
                }

                ealpService.set(d, "scholar1", ".essay-alert").then(function (data) {
                    if (data.data.responseCode == "200") {
                        //   utility.showAlert("", "success", data.message, 5000);
                        if (esid == '1') {
                            $scope.e1 = false;
                            $scope.essay1.essayid = "";
                        } else if (esid == '2') {
                            $scope.e2 = false;
                            //$scope.essay2.essayid = "";
                        } else if (esid == '3') {
                            $scope.e3 = false;
                            //$scope.essay3.essayid = "";
                        }
                    }
                });

            }

            $scope.addEssay = function (esid) {
                var fd = new FormData();
                var save = true;
                if (esid == "1") {
                    if ($scope.essay1.essayid == null || $scope.essay1.essayid == "") {
                        save = false;
                        utility.showAlert(".essay-alert", "alert", "Select an essay option", 5000);
                    } else {
                        angular.forEach($scope.essay1, function (value, key) {
                            if (key == "file") {
                                fd.append(key, value[0]);
                            } else {
                                fd.append(key, value);
                            }
                        });
                    }

                } else if (esid == '2') {
                    angular.forEach($scope.essay2, function (value, key) {
                        fd.append("essayid", "2");
                        if (key == "file") {
                            fd.append(key, value[0]);
                        } else {
                            fd.append(key, value);
                        }
                    });
                } else if (esid == '3') {
                    angular.forEach($scope.essay3, function (value, key) {
                        fd.append("essayid", "3");
                        if (key == "file") {
                            fd.append(key, value[0]);
                        } else {
                            fd.append(key, value);
                        }
                    });
                }
                if (save) {
                    ealpService.uploadDocument(fd,"addEssay",".essay-alert").then(function (data) {
                        if (data.data.responseCode == "200") {
                            if (esid == '1') {
                                $scope.e1 = true;
                                $scope.essay1.url = data.data.description;
                            } else if (esid == '2') {
                                $scope.e2 = true;
                                $scope.essay2.url = data.data.description;
                            } else if (esid = '3') {
                                $scope.e3 = true;
                                $scope.essay3.url = data.data.description;
                            }
                        }
                    })
                }
            }

            $scope.submitApp = function () {
                if ($scope.appstate.s1 == 1 && $scope.appstate.s2 == 1 && $scope.appstate.s3 == 1 && $scope.appstate.s4 == 1 && $scope.appstate.s5 == 1 && $scope.appstate.s6 == 1) {
                    if ($scope.e1 == true && $scope.e2 == true && $scope.e3 == true) {
                        ealpService.set({action:"submitapp"},"scholar1",".submit-alert", d).then(function (data) {
                            if (data.data.responseCode == "200") {
                                $scope.appstate.s7 = true;
                            }
                        });

                    } else {
                        utility.showAlert(".submit-alert", "alert", "Upload all the essays", 5000);
                    }
                } else {
                    utility.showAlert(".submit-alert", "alert", "There are changes that have not been committed. Please do so to continue", 5000);
                }
            }

            $scope.editState = function (form) {
                if (form == '1') {
                    $scope.appstate.s1 = 0;
                } else if (form == '2') {
                    $scope.appstate.s2 = 0;
                } else if (form == '3') {
                    $scope.appstate.s3 = 0;
                } else if (form == '4') {
                    $scope.appstate.s4 = 0;
                } else if (form == '5') {
                    $scope.appstate.s5 = 0;
                } else if (form == '6') {
                    $scope.appstate.s6 = 0;
                }
            }
        }
    }
});



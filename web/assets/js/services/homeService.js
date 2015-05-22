/*ealpApp.service("adminService", function ($http, utility) {
    this.addMentor = function (mentor) {
        mentor.action = "addmentor";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin1',
            data: $.param(mentor),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode === 200) {

                utility.showAlert(".addmentor-alert", "success", data.message, 3000);

            } else {
                utility.showAlert(".addmentor-alert", "alert", data.message, 3000);

            }
        }).error(function (data) {
            utility.showAlert("", "alert", data.message, 3000);

        });
    };

    this.setModule = function (module) {
        

    }
    this.changePass = function (password) {
        password.action = "changepass";
        $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin1',
            data: $.param(password),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode === 200) {
                utility.showAlert(".changepass-alert", "success", data.message, 3000);
            } else {
                utility.showAlert(".changepass-alert", "alert", data.message, 3000);
            }
        }).error(function (data) {
            utility.showAlert(".changepass-alert", "alert", data.message, 3000);
        });
    };
    /*this.getFamily = function (sid) {
        var d = {};
        d.sid = sid;
        d.action = "getfamily";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin1',
            data: $.param(d),
            dataType: 'json'
        }).success(function (data) {

        }).error(function (data) {

        });
    };
    this.getSchool = function (sid) {
        var d = {};
        d.sid = sid;
        d.action = "getschool";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin1',
            data: $.param(d),
            dataType: 'json'
        }).success(function (data) {

        }).error(function (data) {

        });
    };
    this.getTranscript = function (sid) {
        var d = {};
        d.sid = sid;
        d.action = "gettranscript";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin1',
            data: $.param(d),
            dataType: 'json'
        }).success(function (data) {

        }).error(function (data) {

        });
    };
    this.getHonors = function (sid) {
        var d = {};
        d.sid = sid;
        d.action = "gethonors";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin1',
            data: $.param(d),
            dataType: 'json'
        }).success(function (data) {

        }).error(function (data) {

        });
    };
    this.getVol = function (sid) {
        var d = {};
        d.sid = sid;
        d.action = "getvol";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin1',
            data: $.param(d),
            dataType: 'json'
        }).success(function (data) {

        }).error(function (data) {

        });
    }
    this.getEssays = function (sid) {
        var d = {};
        d.sid = sid;
        d.action = "getessays1";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin1',
            data: $.param(d),
            dataType: 'json'
        }).success(function (data) {

        }).error(function (data) {

        });
    };
    this.getHols = function (sid) {
        var d = {};
        d.sid = sid;
        d.action = "getholiday";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin1',
            data: $.param(d),
            dataType: 'json'
        }).success(function (data) {

        }).error(function (data) {

        });
    };
    this.saveReview = function (review) {
        //review.action = ;
        
    };
    this.publishResults = function (score) {
        var d = {};
        d.score = score;
        d.action = "publishresults";

        $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin1',
            data: $.param(d),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "200") {
                utility.showAlert(".publish-alert", "success", data.message, 5000);
            } else {
                utility.showAlert(".publish-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".publish-alert", "alert", data.message, 5000);
        });
    };
    
   /* this.updateEvent = function (event) {
        event.action = "updateevent";
        $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin1',
            data: $.param(event),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "200") {
                utility.showAlert(".ev-alert", "success", data.message, 5000);
            } else {
                utility.showAlert(".ev-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".ev-alert", "alert", data.message, 5000);
        });
    };

    this.deleteEvent = function (event) {
        event.action = "deleteevent";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin1',
            data: $.param(event),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "200") {
                utility.showAlert(".ev-alert", "success", data.message, 5000);
            } else {
                utility.showAlert(".ev-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".ev-alert", "alert", data.message, 5000);
        });
    };*/
    /*this.addList = function (callist) {
        callist.action = "addcallist";
        $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin2',
            data: $.param(callist),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "200") {
                utility.showAlert(".callist-alert", "success", data.message, 5000);
            } else {
                utility.showAlert(".callist-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".callist-alert", "alert", data.message, 5000);
        });
    };*/
    /*this.loadScholars = function (id) {
        var d = {};
        d.id = id;
        d.action = "getscholarlist";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin1',
            data: $.param(d),
            dataType: 'json'
        }).success(function (data) {


        }).error(function (data) {

        });
    };*/
   /* this.editList = function (list) {
        list.action = "fun";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin2',
            data: $.param(list)
        }).success(function (data) {
            if (data.responseCode == "200") {
                utility.showAlert(".oldcall-alert", "success", data.message, 5000);
            } else {
                utility.showAlert(".oldcall-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".oldcall-alert", "alert", data.message, 5000);
        });
    };
    this.deleteList = function (list) {
        list.action = "deletecallist";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin2',
            data: $.param(list)
        }).success(function (data) {
            if (data.responseCode == "200") {
                utility.showAlert(".oldcall-alert", "success", data.message, 5000);
            } else {
                utility.showAlert(".oldcall-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".oldcall-alert", "alert", data.message, 5000);
        });
    };*/
    /*this.addScholartolist = function (scholar, list) {
        scholar.action = "addscholarlist";
        scholar.listid = list.id;
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin2',
            data: $.param(scholar),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "200") {
                utility.showAlert(".oldcall2-alert", "success", data.message, 5000);
            } else {
                utility.showAlert(".oldcall2-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".oldcall-alert2", "alert", data.message, 5000);
        });
    };
    this.updateFeedback = function (scholar, list) {
        scholar.action = "editfeedback";
        scholar.listid = list.id;
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin2',
            data: $.param(scholar),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "200") {
                utility.showAlert(".oldcall2-alert", "success", data.message, 5000);
            } else {
                utility.showAlert(".oldcall2-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".oldcall-alert2", "alert", data.message, 5000);
        });
    };
    this.addCollege = function (college) {
        college.action = "addcollege";
        $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin2',
            data: $.param(college),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "200") {
                utility.showAlert(".college-alert", "success", data.message, 5000);
            } else {
                utility.showAlert(".college-alert", "alert", data.message, 5000);
            }

        }).error(function (data) {
            utility.showAlert(".college-alert", "alert", data.message, 5000);
        });
    };
    this.updateCollege = function (college) {
        college.action = "updatecollege";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin2',
            data: $.param(college),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "200") {
                utility.showAlert(".college-alert2", "success", data.message, 5000);
            } else {
                utility.showAlert(".college-alert2", "alert", data.message, 5000);
            }

        }).error(function (data) {
            utility.showAlert(".college-alert2", "alert", data.message, 5000);
        });
    };
    this.addCollegeEssay = function (essay) {
        $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin2',
            data: $.param(essay),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "200") {
                utility.showAlert(".collegessay-alert", "success", data.message, 5000);
            } else {
                utility.showAlert(".collegessay-alert", "alert", data.message, 5000);
            }

        }).error(function (data) {
            utility.showAlert(".collegessay-alert", "alert", data.message, 5000);
        });
    };
    this.getCollegeEssay = function (id) {
        var d = {};
        d.id = id;
        d.action = "getcollegessay";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin2',
            data: $.param(d),
            dataType: 'json'
        }).success(function (data) {

        }).error(function (data) {

        })
    };
    this.editCollegeEssay = function (essay) {
        essay.action = "editcollegessay";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin2',
            data: $.param(essay),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "200") {
                utility.showAlert(".collegessay-alert", "success", data.message, 5000);
            } else {
                utility.showAlert(".collegessay-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".collegessay-alert", "alert", data.message, 5000);
        })
    };
    this.deleteCollegeEssay = function (id) {
        var d = {};
        d.id = id;
        d.action = "deletecollegessay";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin2',
            data: $.param(d),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "200") {
                utility.showAlert(".collegessay-alert", "success", data.message, 5000);
            } else {
                utility.showAlert(".collegessay-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".collegessay-alert", "alert", data.message, 5000);
        });
    };
    /*this.addTest = function (test) {
        test.action = "addtest";
        $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin2',
            data: $.param(test),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "200") {
                utility.showAlert(".test1-alert", "success", data.message, 5000);
            } else {
                utility.showAlert(".test1-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".test1-alert", "alert", data.message, 5000);
        });
    };
    this.updateTest = function (test) {
        test.action = "updatetest";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin2',
            data: $.param(test),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "200") {
                utility.showAlert(".test2-alert", "success", data.message, 5000);
            } else {
                utility.showAlert(".test2-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".test2-alert", "alert", data.message, 5000);
        });
    };
    this.pickScholar = function (scholar) {
        scholar.action = "pickscholar";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin2',
            data: $.param(scholar),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "200") {
                utility.showAlert(".all-alert", "success", data.message, 5000);
            } else {
                utility.showAlert(".all-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".all-alert", "alert", data.message, 5000);
        });
    };
    this.addSatScore = function (score) {
        score.action = "addsatscore";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin2',
            data: $.param(score),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "200") {
                utility.showAlert(".score-alert", "success", data.message, 5000);
            } else {
                utility.showAlert(".score-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".score-alert", "alert", data.message, 5000);
        });
    };
    this.addOtherScore = function (score) {
        score.action = "addotherscore";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin2',
            data: $.param(score),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "200") {
                utility.showAlert(".score-alert", "success", data.message, 5000);
            } else {
                utility.showAlert(".score-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".score-alert", "alert", data.message, 5000);
        });
    };
    this.approveEssay = function (essay) {
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin2',
            data: $.param(essay),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "200") {
                utility.showAlert(".essay1-alert", "success", data.message, 5000);
            } else {
                utility.showAlert(".essay1-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".essay1-alert", "alert", data.message, 5000);
        });
    };
    this.rejectEssay = function (essay) {
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin2',
            data: $.param(essay),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "200") {
                utility.showAlert(".essay1-alert", "success", data.message, 5000);
            } else {
                utility.showAlert(".essay1-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".essay1-alert", "alert", data.message, 5000);
        });
    };
    this.activateAccount = function (mentor) {
        mentor.action = "activateaccount";
        
    };
    
    this.deactivateAccount = function (mentor) {
        mentor.action = "delmentor";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin2',
            data: $.param(mentor),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "200") {
                utility.showAlert(".mentor-alert", "success", data.message, 5000);
            } else {
                utility.showAlert(".mentor-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".mentor-alert", "alert", data.message, 5000);
        });
    };
    this.printEventList = function (event) {
        event.action = "geteventlist";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'reports',
            data: $.param(event),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "200") {
                utility.showAlert(".mentor-alert", "success", data.message, 5000);
            } else {
                utility.showAlert(".mentor-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".mentor-alert", "alert", data.message, 5000);
        });
    };

    this.getMyScholarTest = function (scholar) {
        scholar.action = "getpasttest";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin2',
            data: $.param(scholar),
            dataType: 'json'
        }).success(function (data) {

        }).error(function (data) {

        });
    };
    this.getDocuments = function (scholar) {
        scholar.action = "getdocs";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin2',
            data: $.param(scholar),
            dataType: 'json'
        }).success(function (data) {

        }).error(function (data) {

        });
    };
    this.uploadNewDocument = function (fd) {
        return $http({
            headers: {'Content-Type': undefined},
            method: 'POST',
            url: 'addDocument1',
            transformRequest: angular.identity,
            data: fd,
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "200") {
                utility.showAlert(".mydocs-alert", "success", data.message, 5000);

            } else {
                utility.showAlert(".mydocs-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".mydocs-alert", "alert", data.message, 5000);
        });
    };
    this.uploadNewEssay = function (fd) {
        return $http({
            headers: {'Content-Type': undefined},
            method: 'POST',
            url: 'addCollegeEssay2',
            transformRequest: angular.identity,
            data: fd,
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "200") {
                utility.showAlert(".myes-alert", "success", data.message, 5000);

            } else {
                utility.showAlert(".myes-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".mydocs-alert", "alert", data.message, 5000);
        });
    };
    this.getMyScholarColleges = function (scholar) {
        scholar.action = "getmyscholarcolleges";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin2',
            data: $.param(scholar),
            dataType: 'json'
        }).success(function (data) {

        }).error(function (data) {

        });
    };
    this.dropScholar = function (scholar) {
        scholar.action = "dropscholar";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin2',
            data: $.param(scholar),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "200") {
                utility.showAlert(".sprofile-alert", "success", data.message, 5000);
            } else {
                utility.showAlert(".sprofile-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".sprofile-alert", "alert", data.message, 5000);
        });
    };
    this.getTestScores = function (scholar) {
        scholar.action = "gettestscores";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin2',
            data: $.param(scholar),
            dataType: 'json'
        }).success(function (data) {

        }).error(function (data) {

        });
    };
    this.closeApplication = function (sys) {
        sys.action = "closeapplications";
        $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin1',
            data: $.param(sys),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "200") {
                utility.showAlert(".sysapp-alert", "success", data.message, 5000);
            } else {
                utility.showAlert(".sysapp-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".sysapp-alert", "alert", data.message, 5000);
        });
    };
    /*this.acceptRScholar = function (scholar) {
        
    };
    this.getAnalysis1 = function () {
        var d = {};
        d.action = "getanalysis1";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin1',
            data: $.param(d),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "400" || data.responseCode == "403") {
                utility.showAlert(".analysis1-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".analysis1-alert", "alert", data.message, 5000);
        });
    };
    this.switchStd = function (mentor) {
        mentor.action = "switchstd";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin2',
            data: $.param(mentor),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "200") {
                utility.showAlert(".mentor-alert", "success", data.message, 5000);
            } else {
                utility.showAlert(".mentor-alert", "alert", data.message, 5000);
            }

        }).error(function (data) {
            utility.showAlert(".mentor-alert", "alert", data.message, 5000);
        });
    };
    this.switchAdmin = function (mentor) {
        mentor.action = "switchadmin";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin2',
            data: $.param(mentor),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "200") {
                utility.showAlert(".mentor-alert", "success", data.message, 5000);
            } else {
                utility.showAlert(".mentor-alert", "alert", data.message, 5000);
            }

        }).error(function (data) {
            utility.showAlert(".mentor-alert", "alert", data.message, 5000);
        });
    };*/
    // check alerts
   /* this.getTestList = function (test) {
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin2',
            data: $.param(test),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "200") {
                utility.showAlert(".mentor-alert", "success", data.message, 5000);
            } else {
                utility.showAlert(".mentor-alert", "alert", data.message, 5000);
            }

        }).error(function (data) {
            utility.showAlert(".mentor-alert", "alert", data.message, 5000);
        });
    };
    this.getResultList = function (test) {
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin2',
            data: $.param(test),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "200") {
                utility.showAlert(".mentor-alert", "success", data.message, 5000);
            } else {
                utility.showAlert(".mentor-alert", "alert", data.message, 5000);
            }

        }).error(function (data) {
            utility.showAlert(".mentor-alert", "alert", data.message, 5000);
        });
    };
    this.getResultAnalysis = function (test) {
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin2',
            data: $.param(test),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "200") {
                utility.showAlert(".mentor-alert", "success", data.message, 5000);
            } else {
                utility.showAlert(".mentor-alert", "alert", data.message, 5000);
            }

        }).error(function (data) {
            utility.showAlert(".mentor-alert", "alert", data.message, 5000);
        });
    };
    this.getScholarbyType = function (d) {
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin2',
            data: $.param(d),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "400" || data.responseCode == "403") {
                utility.showAlert(".sreport1-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".sreport1-alert", "alert", data.message, 5000);
        })
    };
    this.getScholarbyCollege = function (d) {
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin2',
            data: $.param(d),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "401") {
                utility.showAlert(".sreport11-alert", "alert", data.message, 5000);
            }
            if (data.responseCode == "400" || data.responseCode == "403") {
                utility.showAlert(".sreport11-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".sreport11-alert", "alert", data.message, 5000);
        })
    };
    this.getAppStatus = function (d) {
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin2',
            data: $.param(d),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "400" || data.responseCode == "403") {
                utility.showAlert(".sreport3-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".sreport3-alert", "alert", data.message, 5000);
        })
    };
    this.changeProg = function (scholar) {
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin2',
            data: $.param(scholar),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "200") {
                utility.showAlert(".drop-alert", "success", data.message, 5000);
            } else {
                utility.showAlert(".drop-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".drop-alert", "alert", data.message, 5000);
        });
    };
    this.addCommonApp = function (d) {
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin2',
            data: $.param(d),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "200") {
                utility.showAlert(".commonapp-alert", "success", data.message, 5000);
            } else {
                utility.showAlert(".commonapp-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".commonapp-alert", "alert", data.message, 5000);
        });
    };
    this.deleteCommonApp = function (d) {
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin2',
            data: $.param(d),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "200") {
                utility.showAlert(".commonapp-alert", "success", data.message, 5000);
            } else {
                utility.showAlert(".commonapp-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".commonapp-alert", "alert", data.message, 5000);
        });
    };

});

ealpApp.service("scholarService", function ($http, $location, utility, initService) {
    /*this.attendEvent = function (event) {
        event.action = "attend";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'scholar2',
            data: $.param(event),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == '200') {
                utility.showAlert(".attend-alert", "success", data.message, 5000);
            } else {
                utility.showAlert(".attend-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".attend-alert", "alert", data.message, 5000);
        })
    };
    this.withdrawAttendance = function (event) {
        event.action = "notattending";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'scholar2',
            data: $.param(event),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == '200') {
                utility.showAlert(".attend-alert", "success", data.message, 5000);
            } else {
                utility.showAlert(".attend-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".attend-alert", "alert", data.message, 5000);
        })
    };
    this.registerTest = function (test) {
        test.action = "registertest";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'scholar2',
            data: $.param(test),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == '200') {
                utility.showAlert(".test3-alert", "success", data.message, 5000);
            } else {
                utility.showAlert(".test3-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".test3-alert", "alert", data.message, 5000);
        })
    };
    this.unregisterTest = function (test) {
        test.action = "withdrawtest";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'scholar2',
            data: $.param(test),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == '200') {
                utility.showAlert(".test3-alert", "success", data.message, 5000);
            } else {
                utility.showAlert(".test3-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".test3-alert", "alert", data.message, 5000);
        })
    };

    this.addSatResults = function (result) {
        result.action = "addsatresult";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'scholar2',
            data: $.param(result),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == '200') {
                utility.showAlert(".test4-alert", "success", data.message, 5000);
            } else {
                utility.showAlert(".test4-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".test4-alert", "alert", data.message, 5000);
        })
    };
    this.addOtherResults = function (result) {
        result.action = "addotheresult";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'scholar2',
            data: $.param(result),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == '200') {
                utility.showAlert(".test4-alert", "success", data.message, 5000);
            } else {
                utility.showAlert(".test4-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".test4-alert", "alert", data.message, 5000);
        })
    };
    this.getCollegeEssays = function (id) {
        var d = {};
        d.action = "getcollegessays";
        d.collegeid = id;
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'scholar2',
            data: $.param(d),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == '200') {
                utility.showAlert(".scholarcollege-alert", "success", data.message, 5000);
            } else {
                utility.showAlert(".scholarcollege-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".scholarcollege-alert", "alert", data.message, 5000);
        })
    };
    this.pickCollege = function (id) {
        var d = {};
        d.action = "pickcollege";
        d.collegeid = id;
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'scholar2',
            data: $.param(d),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == '200') {
                utility.showAlert(".scholarcollege-alert", "success", data.message, 5000);
            } else {
                utility.showAlert(".scholarcollege-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".scholarcollege-alert", "alert", data.message, 5000);
        })
    };
    this.dropCollege = function (id) {
        var d = {};
        d.action = "dropcollege";
        d.collegeid = id;
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'scholar2',
            data: $.param(d),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == '200') {
                utility.showAlert(".mycollege-alert", "success", data.message, 5000);
            } else {
                utility.showAlert(".mycollege-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".mycollege-alert", "alert", data.message, 5000);
        })
    };
    this.addDecision = function (decision) {
        decision.action = "addecision";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'scholar2',
            data: $.param(decision),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == '200') {
                utility.showAlert(".mycollege-alert", "success", data.message, 5000);
            } else {
                utility.showAlert(".mycollege-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".mycollege-alert", "alert", data.message, 5000);
        })
    };
    this.uploadDocument = function (fd) {
        return $http({
            headers: {'Content-Type': undefined},
            method: 'POST',
            url: 'addDocument',
            transformRequest: angular.identity,
            data: fd,
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "200") {
                utility.showAlert(".document-alert", "success", data.message, 5000);

            } else {
                utility.showAlert(".document-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".document-alert", "alert", data.message, 5000);
        });
    };
    this.deleteDocument = function (document) {
        document.action = "deletedocument";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'scholar2',
            data: $.param(document),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "200") {
                utility.showAlert(".document-alert", "success", data.message, 5000);

            } else {
                utility.showAlert(".document-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".document-alert", "alert", data.message, 5000);
        })
    };
    this.getHistory = function (d) {
        d.action = "getdochist";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'scholar2',
            data: $.param(d),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "400" || data.responseCode == "403") {
                utility.showAlert(".dochist-alert", "alert", data.message, 5000);

            }
        }).error(function (data) {
            utility.showAlert(".dochist-alert", "alert", data.message, 5000);
        })
    };
    this.getMyEssays = function (d) {
        d.action = "getmyessays";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'scholar2',
            data: $.param(d),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "200") {
                utility.showAlert(".document-alert", "success", data.message, 5000);

            } else {
                utility.showAlert(".document-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".document-alert", "alert", data.message, 5000);
        })
    };
    this.uploadCollegeEssay = function (fd) {
        return $http({
            headers: {'Content-Type': undefined},
            method: 'POST',
            url: 'addCollegeEssay1',
            transformRequest: angular.identity,
            data: fd,
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "200") {
                utility.showAlert(".myessay-alert", "success", data.message, 5000);

            } else {
                utility.showAlert(".myessay-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".myessay-alert", "alert", data.message, 5000);
        });
    };
    this.checkEssayFeedback = function (d) {
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'scholar2',
            data: $.param(d),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "200") {
                utility.showAlert(".essayhist-alert", "success", data.message, 5000);

            } else if (data.responseCode == "403") {
                utility.showAlert(".essayhist-alert", "info", data.message, 5000);
            } else {
                utility.showAlert(".essayhist-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".essayhist-alert", "alert", data.message, 5000);
        })
    };
    this.getEssayHistory = function (d) {

        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'scholar2',
            data: $.param(d),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "400" || data.responseCode == "403") {
                utility.showAlert(".essayhist-alert", "alert", data.message, 5000);

            }
        }).error(function (data) {
            utility.showAlert(".essayhist-alert", "alert", data.message, 5000);
        })
    };
    this.deleteCollegeEssay = function (document) {
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'scholar2',
            data: $.param(document),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "200") {
                utility.showAlert(".essayhist-alert", "success", data.message, 5000);

            } else {
                utility.showAlert(".essayhist-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".essayhist-alert", "alert", data.message, 5000);
        })
    };
    this.getEssayStatus = function (d) {
        d.action = "getessaystatus";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'scholar2',
            data: $.param(d),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "400" || data.responseCode == "403") {
                utility.showAlert(".essayhist-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".essayhist-alert", "alert", data.message, 5000);
        })
    };
    this.getCommonAppPrompts = function (d) {
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin2',
            data: $.param(d),
            dataType: 'json'
        }).success(function (data) {
           
        }).error(function (data) {
           
        })
    };
    this.getCommonAppStatus = function () {
        var d = {};
        d.action = "getcommonappstatus";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'scholar2',
            data: $.param(d),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "400" || data.responseCode == "403") {
                utility.showAlert(".essayhist-alert", "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(".essayhist-alert", "alert", data.message, 5000);
        })
    };
    this.getDocumentStatus = function () {
        var d = {};
        d.action = "getdocstatus";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'scholar2',
            data: $.param(d),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "400" || data.responseCode == "403") {
                utility.showAlert(".dochist-alert", "alert", data.message, 5000);

            }
        }).error(function (data) {
            utility.showAlert(".dochist-alert", "alert", data.message, 5000);
        })
    };
})*/

ealpApp.service("homeService", function ($http, $location, utility, initService) {
    var ann = {};
    this.login = function (user) {
        user.action = "login";
        //console.log(user);
        if (user.type == "1") {
            
            $http({
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                method: 'POST',
                url: 'admin1',
                data: $.param(user),
                dataType: 'json'
            }).success(function (data) {
                if (data.responseCode === 100) {
                    if (data.message == "admin") {
                        $location.path("admin1");

                    } else {
                        $location.path("counselor1");
                    }

                } else if (data.responseCode == "200") {
                    if (data.message == "admin") {
                        $location.path("admin2");

                    } else {
                        $location.path("counselor2");
                    }
                } else if (data.responseCode === 403) {
                    utility.showAlert(".login-alert", "alert", data.message, 3000);
                }
            }).error(function () {
                utility.showAlert(".login-alert", "alert", data.message, 3000);
            });

        } else if (user.type == "0") {
            $http({
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                method: 'POST',
                url: 'scholar1',
                data: $.param(user),
                dataType: 'json'
            }).success(function (data) {
                if (data.responseCode == "100") {
                    $location.path("scholarapp");
                } else if (data.responseCode == "200") {
                    $location.path("collegeprep");
                } else if (data.responseCode === 403) {
                    utility.showAlert(".login-alert", "alert", data.message, 3000);
                }
            }).error(function (data) {
                utility.showAlert(".login-alert", "alert", data.message, 3000);
            });
        } else {
            utility.showAlert(".login-alert", "alert", "Select a valid option", 3000);
        }
    }
    this.addScholar = function (scholar) {
        scholar.action = "addscholar";
        $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'home',
            data: $.param(scholar),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == 200) {
                utility.showAlert(".newscholar-alert", "success", data.message, 3000);
            } else {
                utility.showAlert(".newscholar-alert", "alert", data.message, 3000);
            }
        }).error(function (data) {
            utility.showAlert(".newscholar-alert", "alert", data.message, 3000);
        });
    }

    this.loginAccessCode = function (scholar) {
        scholar.action = "getaccesscode";
        return  $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'home',
            data: $.param(scholar),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == 200) {
                utility.showAlert(".accesscode-alert", "success", data.message, 3000);
            } else {
                utility.showAlert(".accesscode-alert", "alert", data.message, 3000);
            }

        }).error(function (data) {
            utility.showAlert(".accesscode-alert", "alert", data.message, 3000);
        })
    };

    this.createScholar = function (scholar) {
        if (scholar.type != '4') {
            scholar.action = "createscholar";
            if (scholar.pass == scholar.pass2) {
                return $http({
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                    method: 'POST',
                    url: 'home',
                    data: $.param(scholar),
                    dataType: 'json'
                }).success(function (data) {
                    if (data.responseCode == "200") {
                        utility.showAlert(".login-alert", "success", data.message, 3000);

                    } else {
                        utility.showAlert(".nscholar-alert", "alert", data.message, 3000);
                    }

                }).error(function (data) {
                    utility.showAlert(".nscholar-alert", "alert", data.message, 3000);
                });
            } else {
                utility.showAlert(".nscholar-alert", "alert", "The passwords do not match.", 3000);
            }

        } else {
            scholar.action = "createscholar";
            if (scholar.pass == scholar.pass2) {
                return $http({
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                    method: 'POST',
                    url: 'ealpscholar',
                    data: $.param(scholar),
                    dataType: 'json'
                }).success(function (data) {
                    if (data.responseCode == "200") {
                        utility.showAlert(".login-alert", "success", data.message, 3000);

                    } else {
                        utility.showAlert(".nscholar-alert", "alert", data.message, 3000);
                    }

                }).error(function (data) {
                    utility.showAlert(".nscholar-alert", "alert", data.message, 3000);
                });
            } else {
                utility.showAlert(".nscholar-alert", "alert", "The passwords do not match.", 3000);
            }

        }


    };
    this.createealpScholar = function (scholar) {
        var d = {};
        d.pf = scholar.pf;
        d.action = "createealpscholar";
        d.email = scholar.email;
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'ealpscholar',
            data: $.param(d),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == 200) {
                utility.showAlert(".newscholar-alert", "success", data.message, 3000);
            } else {
                utility.showAlert(".newscholar-alert", "alert", data.message, 3000);
            }
        }).error(function (data) {
            utility.showAlert(".newscholar-alert", "alert", data.message, 3000);
        });
    };
    this.resetPassword = function (scholar) {
        scholar.action = "resetpassword";
        if (scholar.type == "0") {
            return $http({
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                method: 'POST',
                url: 'scholar2',
                data: $.param(scholar),
                dataType: 'json'
            }).success(function (data) {
                if (data.responseCode == 200) {
                    utility.showAlert(".reset-alert", "success", data.message, 3000);
                } else {
                    utility.showAlert(".reset-alert", "alert", data.message, 3000);
                }
            }).error(function (data) {
                utility.showAlert(".reset-alert", "alert", data.message, 3000);
            });
        } else if (scholar.type == "1") {
            return $http({
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                method: 'POST',
                url: 'admin2',
                data: $.param(scholar),
                dataType: 'json'
            }).success(function (data) {
                if (data.responseCode == 200) {
                    utility.showAlert(".reset-alert", "success", data.message, 3000);
                } else {
                    utility.showAlert(".reset-alert", "alert", data.message, 3000);
                }
            }).error(function (data) {
                utility.showAlert(".reset-alert", "alert", data.message, 3000);
            });
        }

    };


});

ealpApp.service("utility", function () {
    this.showAlert = function (alert, alertclass, message, time) {
        $(alert).html("");
        $(alert).addClass(alertclass);
        $(alert).css("display", "block");
        $(alert).append(message);
        var timeout = setTimeout(function () {
            $(alert).removeClass(alertclass);
            $(alert).css("display", "none");
        }, time);
        //clearTimeout = 
    };
    this.getGrade = function (points) {
        if (points == 12) {
            return "A";
        } else if (points == 11) {
            return "A-";
        } else if (points == 10) {
            return "B+";
        } else if (points == 9) {
            return "B";
        } else if (points == 8) {
            return "B-";
        } else if (points == 7) {
            return "C+";
        } else if (points == 6) {
            return "C";
        } else if (points == 5) {
            return "C-";
        } else if (points == 4) {
            return "D";
        }
    }
});


/*ealpApp.service("initService", function ($http, $location, utility) {


/*    this.getApprovals = function () {
        
    };
    this.getHighschool = function () {
        var scholar = {};
        scholar.action = "getschool";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'scholar1',
            data: $.param(scholar),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode != 400) {
                //console.log(data);
            }
        }).error(function (data) {

        });
    };
    this.getTranscript = function () {
        var scholar = {};
        scholar.action = "gettranscript";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'scholar1',
            data: $.param(scholar),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode != 400) {
                //console.log(data);
            }
        }).error(function (data) {

        });
    };
    this.getHonors = function () {
        var scholar = {};
        scholar.action = "gethonors";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'scholar1',
            data: $.param(scholar),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode != 400) {
                //console.log(data);
            }
        }).error(function (data) {

        });
    };
    this.getVol = function () {
        var scholar = {};
        scholar.action = "getextras";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'scholar1',
            data: $.param(scholar),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode != 400) {
                //console.log(data);
            }
        }).error(function (data) {

        });
    };
    this.getHols = function () {
        var scholar = {};
        scholar.action = "gethols";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'scholar1',
            data: $.param(scholar),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode != 400) {
                //console.log(data);
            }
        }).error(function (data) {

        });
    };
    this.getEssays = function () {
        var scholar = {};
        scholar.action = "getessays";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'scholar1',
            data: $.param(scholar),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode != 400) {
                //console.log(data);
            }
        }).error(function (data) {

        });
    };
    this.checkSubmit = function () {
        var scholar = {};
        scholar.action = "checksubmit";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'scholar1',
            data: $.param(scholar),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode != 400) {
                //console.log(data);
            }
        }).error(function (data) {

        });
    };
    this.getApplicants = function () {
       
    };
   /* this.getEvents = function () {
        var scholar = {};
        scholar.action = "getevents";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin1',
            data: $.param(scholar),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode != 400) {
                //console.log(data);
            }
        }).error(function (data) {

        });
    };
    this.getCallists = function () {
        var scholar = {};
        scholar.action = "getcallists";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin2',
            data: $.param(scholar),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode != 400) {
                //console.log(data);
            }
        }).error(function (data) {

        });
    };
    this.getColleges = function () {
        var scholar = {};
        scholar.action = "getcolleges";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin2',
            data: $.param(scholar),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode != 400) {
                //console.log(data);
            }
        }).error(function (data) {

        });
    };
    this.getTests = function () {
        var scholar = {};
        scholar.action = "gettests";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin2',
            data: $.param(scholar),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode != 400) {
                //console.log(data);
            }
        }).error(function (data) {

        });
    };
    this.getAllScholars = function () {
        var scholar = {};
        scholar.action = "getallscholars";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin2',
            data: $.param(scholar),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode != 400) {
                //console.log(data);
            }
        }).error(function (data) {

        });
    };
    this.getMyScholars = function () {
        var scholar = {};
        scholar.action = "getmyscholars";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin2',
            data: $.param(scholar),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode != 400) {
                //console.log(data);
            }
        }).error(function (data) {

        });
    };
    this.getCollegeEssays = function () {
        var scholar = {};
        scholar.action = "getapprovalessays";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin2',
            data: $.param(scholar),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode != 400) {
                //console.log(data);
            }
        }).error(function (data) {

        });
    };
    
   /* this.getScholarEvents = function () {
        var scholar = {};
        scholar.action = "getscholarevents";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'scholar2',
            data: $.param(scholar),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode != 400) {
                //console.log(data);
            }
        }).error(function (data) {

        });
    };
    this.getScholarTests = function () {
        var scholar = {};
        scholar.action = "gettests";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'scholar2',
            data: $.param(scholar),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode != 400) {
                //console.log(data);
            }
        }).error(function (data) {

        });
    };
    this.getPastTests = function () {
        var scholar = {};
        scholar.action = "getpasttests";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'scholar2',
            data: $.param(scholar),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode != 400) {
                //console.log(data);
            }
        }).error(function (data) {

        });
    };
    this.getResults = function () {
        var scholar = {};
        scholar.action = "getresults";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'scholar2',
            data: $.param(scholar),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode != 400) {
                //console.log(data);
            }
        }).error(function (data) {

        });
    };
    this.getScholarColleges = function () {
        var scholar = {};
        scholar.action = "getcolleges";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'scholar2',
            data: $.param(scholar),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode != 400) {
                //console.log(data);
            }
        }).error(function (data) {

        });
    };
    this.getDocuments = function () {
        var scholar = {};
        scholar.action = "getdocuments";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'scholar2',
            data: $.param(scholar),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode != 400) {
                //console.log(data);
            }
        }).error(function (data) {

        });
    };
    this.getApplicationProgress = function () {
        var scholar = {};
        scholar.action = "getappstatus";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin1',
            data: $.param(scholar),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode != 400) {
                //console.log(data);
            }
        }).error(function (data) {

        });
    };
    this.getSysStatus = function () {
        
    };
    this.getAcceptedScholars = function () {
       
    };
    this.getRScholars = function () {
        var scholar = {};
        //scholar.action = ;
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin1',
            data: $.param(scholar),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode != 400) {
                //console.log(data);
            }
        }).error(function (data) {

        });
    };
    this.getAllApplicants = function () {
        var scholar = {};
        scholar.action = "getallapplicants";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin1',
            data: $.param(scholar),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode != 400) {
                //console.log(data);
            }
        }).error(function (data) {

        });
    };
    /*this.getAttEvents = function () {
        var scholar = {};
        scholar.action = "geteventswithpeople";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin2',
            data: $.param(scholar),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode != 400) {
                //console.log(data);
            }
        }).error(function (data) {

        });
    };
    this.getTestLists = function () {
        var scholar = {};
        scholar.action = "gettestlist";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin2',
            data: $.param(scholar),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode != 400) {
                //console.log(data);
            }
        }).error(function (data) {

        });
    };
    this.getTestswithResults = function () {
        var scholar = {};
        scholar.action = "gettestwithresults";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin2',
            data: $.param(scholar),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode != 400) {
                //console.log(data);
            }
        }).error(function (data) {

        });
    };
    this.getMentor = function () {
        var scholar = {};
        scholar.action = "getmymentor";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'scholar2',
            data: $.param(scholar),
            dataType: 'json'
        }).success(function (data) {

        }).error(function (data) {

        })
    };
    this.getCommonapp = function () {
        var scholar = {};
        scholar.action = "getallcommonapp";
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: 'admin2',
            data: $.param(scholar),
            dataType: 'json'
        }).success(function (data) {

        }).error(function (data) {

        })
    };
});*/

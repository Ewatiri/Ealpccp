ealpApp.controller("adminCtrl", function ($scope, $rootScope, ealpService) {
    // menu
    $rootScope.menus = [
        {menuItem: "Announcements", icon1: "fa fa-bullhorn", icon2: "fa fa-chevron-down", view: "announ", child: "1", subShow: true, subMenu: [{menuItem: "View Announcements", child: "0", view: "announ"}, {menuItem: "Create Announcement", child: "0", view: "addannoun"}, {menuItem: "Manage Announcements", child: "0", view: "announ1"}]},
        {menuItem: "Applications", icon1: "fa fa-folder-open", icon2: "fa fa-chevron-down", view: "app", child: "1", subShow: false, subMenu: [{menuItem: "Application Pool", child: "0", view: "pool"}, {menuItem: "Open/Close Applications", child: "0", view: "closeapp"}, {menuItem: "Publish Results", child: "0", view: "publish"}]},
        {menuItem: "Reports", icon1: "fa fa-file", view: "report1", icon2: "fa fa-chevron-down", child: "1", subMenu: [{menuItem: "Scholars", child: "0", view: "accepted"}, {menuItem: "Score Analysis", child: "0", view: "analysis"}]},
        {menuItem: "User Accounts", icon1: "fa fa-users", child: "1", icon2: "fa fa-chevron-down", view: "useracc", subShow: false, subMenu: [{menuItem: "Scholar accounts", child: "0", view: "scholar"}, {menuItem: "Counselor accounts", icon2: "fa fa-chevron-down", icon1: "fa fa-chevron-up", child: "1", view: "counselor", subMenu: [{menuItem: "Add Account", child: "0", view: "addc"}, {menuItem: "Deactivate Account", child: "0", view: "delc"}]}]},
        {menuItem: "Resources", icon1: "fa fa-life-ring", child: "1", icon2: "fa fa-chevron-down", view: 'res', subShow: false, subMenu: [{menuItem: "View Resources", child: "0", view: 'res1'}, {menuItem: "Add Resources", child: "0", view: 'res3'}, {menuItem: "Manage Resources", child: "0", view: 'res2'}]},
        {menuItem: "System Details", icon1: "fa fa-cog", child: "0", view: "sys"},
        {menuItem: "My Account", icon1: "fa fa-user", icon2: "fa fa-chevron-down", child: "1", view: "acc", subShow: false, subMenu: [{menuItem: "Change Password", view: "acc", child: "0"}, {menuItem: "Logout", child: "0"}]}
    ];
    $rootScope.cur = 0;
    $rootScope.last = -1;
    $rootScope.lastParent2 = 0;
    $rootScope.lastParent1 = 0;

    $rootScope.element = "announ";
    $rootScope.elem1 = "announ";
    $rootScope.elem2 = "";


    $scope.publishResults = function (score) {
        var d = {};
        d.score = score;
        d.action = "publishresults"
        ealpService.set(d,"admin1",".publish-alert");

    }
    $rootScope.announcements = {};
    $rootScope.resources = {};
    $rootScope.approvalScholars = {};
    $rootScope.applications = {};
    $rootScope.mentors = {};
    $rootScope.prog = 0;
    $rootScope.all = 0;
    $rootScope.sub = 0;
    $rootScope.systatus = true;
    $scope.init = function () {

        ealpService.get({action: "getAnnouncements"}, 'admin1').then(function (data) {
            if (data.data.responseCode != "400") {
                $rootScope.announcements = data.data;
            }
        });

        ealpService.get({action: "getResources"}, "admin1").then(function (data) {
            if (data.data.responseCode != "400") {
                $rootScope.resources = data.data;
            }
        });

        ealpService.get({action: "getapprovals"}, "admin1").then(function (data) {
            if (data.data.responseCode != "400") {
                $rootScope.approvalScholars = data.data;
                //activateAccordions();
            }

        });
        ealpService.get({action: "getsystatus"}, "admin1").then(function (data) {
            if (data.data.responseCode == "200") {
                if (data.data.description == "1.5") {
                    $rootScope.systatus = false;
                } else {
                    $rootScope.systatus = true;
                }
                //activateAccordions(); 
            }

        });
        ealpService.get({action: "getapplicants"}, "admin1").then(function (data) {
            $rootScope.applications = data.data;
            //activateAccordions();
        });
        ealpService.get({action:"getmentors"},"admin2").then(function (data) {
            if (data.data.responseCode != 400 && data.data.responseCode != 403) {
                $rootScope.mentors = data.data
            }
        })
        ealpService.get({action:"getappstatus"},"admin1").then(function (data) {
            if (data.data.responseCode != 400 && data.data.responseCode != 403) {

                $.each(data.data, function (i, dt) {
                    $rootScope.prog = parseInt((dt.submitted / dt.all) * 100);
                    $rootScope.all = dt.all;
                    $rootScope.sub = dt.submitted;
                });
            }
        });

    }

    $scope.closeApp = function () {
        var d = {};
        if ($rootScope.systatus) {
            d.status = 1.5;
        } else {
            d.status = 1;
        }
        d.action ="closeapplications";
        ealpService.set(d,"admin1",".sysapp-alert");
    }

});

ealpApp.controller("admin2Ctrl", function ($scope, $rootScope, ealpService) {
    $rootScope.menus = [
        {menuItem: "Announcements", icon1: "fa fa-bullhorn", icon2: "fa fa-chevron-down", view: "announ", child: "1", subShow: true, subMenu: [{menuItem: "View Announcements", child: "0", view: "announ"}, {menuItem: "Create Announcement", child: "0", view: "addannoun"}, {menuItem: "Manage Announcements", child: "0", view: "announ1"}]},
        {menuItem: "Scholars", icon1: "fa fa-users", icon2: "fa fa-chevron-down", view: "scholars", child: "1", subShow: false, subMenu: [{menuItem: "My Scholars", child: "0", view: "myscholars"}, {menuItem: "Scholar Pool", child: "0", view: "allscholars"}]},
        {menuItem: "Tests", icon1: "fa fa-newspaper-o", child: "1", icon2: "fa fa-chevron-down", view: "tests", subShow: false, subMenu: [{menuItem: "Add Tests", child: '0', view: "addtests", }, {menuItem: "Manage Tests", child: '0', view: "mtests", }]},
        {menuItem: "Common App", child: '0', view: "commonapp", icon1: "fa fa-file-o", child: "0", subShow: false, },
        {menuItem: "College Profiles", icon1: "fa fa-university", icon2: "fa fa-chevron-down", child: "1", subShow: false, view: "colleges", subMenu: [{menuItem: "Review Profiles", child: '0', view: "colleges"}]},
        {menuItem: "Events", icon1: "fa fa-calendar", child: "1", view: "events", icon2: "fa fa-chevron-down", subShow: false, subMenu: [{menuItem: "Add New", child: '0', view: "addevent"}, {menuItem: "Event Lists", child: '0', view: "eventlist"}, {menuItem: "Manage", child: '0', view: "mevents"}]},
        {menuItem: "Call Lists", icon1: "fa fa-phone", child: "1", view: "callist", icon2: "fa fa-chevron-down", subShow: false, subMenu: [{menuItem: "New Call List", child: '0', view: "callist"}, {menuItem: "Existing Call Lists", child: '0', view: "mcallist"}]},
        {menuItem: "Reports", icon1: "fa fa-file", child: "1", icon2: "fa fa-chevron-down", subShow: false, view: "creports", subMenu: [{menuItem: "Scholars", child: "0", view: "sreports"}, {menuItem: "Tests", child: "0", view: "treports"}]},
        {menuItem: "User Accounts", icon1: "fa fa-users", child: "1", icon2: "fa fa-chevron-down", view: "useracc", subShow: false, subMenu: [{menuItem: "Create Account", child: "0", view: "addc"}, {menuItem: "Manage Accounts", icon1: "fa fa-chevron-up", child: "0", view: "delc"}]},
        {menuItem: "Resources", icon1: "fa fa-life-ring", child: "1", icon2: "fa fa-chevron-down", view: 'res', subShow: false, subMenu: [{menuItem: "View Resources", child: "0", view: 'res1'}, {menuItem: "Add Resources", child: "0", view: 'res3'}, {menuItem: "Manage Resources", child: "0", view: 'res2'}]},
        {menuItem: "System Details", icon1: "fa fa-cog", child: "0", view: "sys"},
        {menuItem: "My Account", icon1: "fa fa-user", icon2: "fa fa-chevron-down", view: "acc", child: "1", subShow: false, subMenu: [{menuItem: "Change Password", child: "0", view: "acc"}, {menuItem: "Logout", child: "0"}]}
    ];

    $rootScope.cur = 0;
    $rootScope.last = -1;
    $rootScope.lastParent2 = 0;
    $rootScope.lastParent1 = 0;

    $rootScope.element = "announ";
    $rootScope.elem1 = "announ";
    $rootScope.elem2 = "";

    $rootScope.announcements = {};
    $rootScope.resources = {};
    $rootScope.callists = {};
    $rootScope.events = {};
    $rootScope.attevents = {};

    $rootScope.tests = {};
    $rootScope.prompts = [];

    $rootScope.dtests = [];

    $rootScope.scholars = {};
    $rootScope.myscholars = [];
    $rootScope.approvalScholars = {};

    $rootScope.colleges = {};
    $rootScope.mentors = {};
    $rootScope.approvalEssays = {};

    $scope.init = function () {
        ealpService.get({action:"getAnnouncements"},'admin1').then(function (data) {
            if (data.data.responseCode != "400") {
                $rootScope.announcements = data.data;
            }
        });
     
        ealpService.get({action:"getevents"}, "admin1").then(function (data) {
            if (data.data.responseCode != 400) {
                $rootScope.events = data.data;
            }
        });
        ealpService.get({action:"getcolleges"},"admin2").then(function (data) {
            if (data.data.responseCode != 400) {
                $rootScope.colleges = data.data;
            }
        });
        ealpService.get({action:"getcallists"},'admin2').then(function (data) {
            if (data.data.responseCode != 400) {
                $rootScope.callists = data.data;
            }
        });
        ealpService.get({action:"getapprovalessays"},"admin2").then(function (data) {
            if (data.data.responseCode != 400 && data.data.responseCode != 403) {
                $rootScope.approvalEssays = data.data;
            }
        });
        ealpService.get({action:"getResources"},"admin1").then(function (data) {
            if (data.data.responseCode != "400") {
                $rootScope.resources = data.data;
            }
        });
       
        ealpService.get({action:"geteventswithpeople"}, "admin2").then(function (data) {
            if (data.data.responseCode != "400" && data.data.responseCode != "403") {
                $rootScope.attevents = data.data;
            }
        });
        ealpService.get({action:"gettests"},"admin2").then(function (data) {
            if (data.data.responseCode != 400) {
                console.log(data.data);
                $rootScope.tests = data.data;
                $.each($rootScope.tests, function (i, dt) {
                    if (dt.official == "1") {
                        $rootScope.dtests.push(dt);
                    }
                });
                //console.log($rootScope.dtests);
            }
        });
        ealpService.get({action:"getallscholars"},"admin2").then(function (data) {
            if (data.data.responseCode != 400) {
                $rootScope.scholars = data.data;
            }
        });

        ealpService.get({action:"getmyscholars"},"admin2").then(function (data) {
            if (data.data.responseCode != 400 && data.data.responseCode != 403) {
                $.each(data.data, function (i, dt) {
                    $rootScope.myscholars.push(dt);
                })
            }
        });
        ealpService.get({action:"getmentors"},"admin2").then(function (data) {
            if (data.data.responseCode != 400 && data.data.responseCode != 403) {

                $rootScope.mentors = data.data


            }
        });
        ealpService.get({action:"getallcommonapp"},"admin2").then(function (data) {
            if (data.data.responseCode != "400" && data.data.responseCode != "403") {
                $.each(data.data, function (i, dt) {
                    $rootScope.prompts.push(dt);
                })
            }
        });
        console.log($rootScope.tests);
    }

});

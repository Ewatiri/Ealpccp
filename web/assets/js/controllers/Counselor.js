ealpApp.controller("counselor1Ctrl", function ($scope, $rootScope, ealpService) {
    $rootScope.menus = [
        {menuItem: "Announcements", icon1: "fa fa-bullhorn", icon2: "fa fa-chevron-down", view: "announ", child: "1", subShow: true, subMenu: [{menuItem: "View Announcements", child: "0", view: "announ"}, {menuItem: "Create Announcement", child: "0", view: "addannoun"}, {menuItem: "Manage Announcements", child: "0", view: "announ1"}]},
        {menuItem: "Applications", icon1: "fa fa-folder-open", icon2: "fa fa-chevron-down", child: "1", view: "pool", subShow: false, subMenu: [{menuItem: "Application Pool", child: "0", view: "pool"}]},
        {menuItem: "Account Approvals", icon1: "fa fa-check", child: "0", view: "approvals"},
        {menuItem: "Resources", icon1: "fa fa-life-ring", child: "1", subShow: false, view: "res", icon2: "fa fa-chevron-down", subMenu: [{menuItem: "Add Resources", child: "0", view: "res"}, {menuItem: "Manage Resources", child: "0", view: "res1"}]},
        {menuItem: "My Account", icon1: "fa fa-user", icon2: "fa fa-chevron-down", subShow: false, child: "1", view: "myaccount", subMenu: [{menuItem: "Change Password", child: "0", view: "myaccount"}, {menuItem: "Logout", child: "0"}]}
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
    $rootScope.approvalScholars = {};
    $rootScope.applications = {};

    $scope.init = function () {
        
        ealpService.get({action:"getAnnouncements"},'admin1').then(function (data) {
            if (data.data.responseCode != "400") {
                $rootScope.announcements = data.data;
            }
        });

        ealpService.get({action:"getResources"},"admin1").then(function (data) {
            if (data.data.responseCode != "400") {
                $rootScope.resources = data.data;
            }
        });

        ealpService.get({action:"getapprovals"},"admin1").then(function (data) {
            if (data.data.responseCode != "400") {
                $rootScope.approvalScholars = data.data;
                //activateAccordions();

            }
            //console.log($rootScope.approvalScholars);
        });

        ealpService.get({action:"getapplicants"},"admin1").then(function (data) {
            $rootScope.applications = data.data;
            //activateAccordions();
        });
    }


});

ealpApp.controller("counselor2Ctrl", function ($rootScope, $scope, ealpService) {
    $rootScope.menus = [
        {menuItem: "Announcements", icon1: "fa fa-bullhorn", icon2: "fa fa-chevron-down", view: "announ", child: "1", subShow: true, subMenu: [{menuItem: "View Announcements", child: "0", view: "announ"}, {menuItem: "Create Announcement", child: "0", view: "addannoun"}, {menuItem: "Manage Announcements", child: "0", view: "announ1"}]},
        {menuItem: "Scholars", icon1: "fa fa-users", icon2: "fa fa-chevron-down", view: "scholars", child: "1", subShow: false, subMenu: [{menuItem: "My Scholars", child: "0", view: "myscholars"}, {menuItem: "Scholar Pool", child: "0", view: "allscholars"}]},
        {menuItem: "Tests", icon1: "fa fa-newspaper-o", child: "1", icon2: "fa fa-chevron-down", subShow: false, view: "tests", subMenu: [{menuItem: "Add Tests", child: '0', view: "addtests"}, {menuItem: "Manage Tests", child: '0', view: "mtests"}, {menuItem: "Test Lists", child: '0', view: "testlist"}]},
        {menuItem: "College Info", icon1: "fa fa-university", icon2: "fa fa-chevron-down", view: "colleges", child: "1", subShow: false, subMenu: [{menuItem: "Add College", child: '0', view: "addcolleges"}, {menuItem: "Manage Colleges", child: '0', view: "editcolleges"}, {menuItem: "Manage College Profiles", child: '0', view: "collegessays"}]},
        {menuItem: "Events", icon1: "fa fa-calendar", child: "1", subShow: false, view: "events", icon2: "fa fa-chevron-down", subMenu: [{menuItem: "Add New", child: '0', view: "addevent"}, {menuItem: "Event Lists", child: '0', view: "eventlist"}, {menuItem: "Manage", child: '0', view: "mevents"}]},
        {menuItem: "Call Lists", icon1: "fa fa-phone", child: "1", subShow: false, view: "callist", icon2: "fa fa-chevron-down", subMenu: [{menuItem: "New Call List", child: '0', view: "callist"}, {menuItem: "Existing Call Lists", child: '0', view: "mcallist"}]},
        {menuItem: "Reports", icon1: "fa fa-file", child: "1", icon2: "fa fa-chevron-down", subShow: false, view: "creports", subMenu: [{menuItem: "Scholars", child: "0", view: "sreports"}, {menuItem: "Tests", child: "0", view: "treports"}]},
        {menuItem: "Resources", icon1: "fa fa-life-ring", child: "1", subShow: false, view: "res", icon2: "fa fa-chevron-down", subMenu: [{menuItem: "Add Resources", child: "0", view: "res"}, {menuItem: "Manage Resources", child: "0", view: "res1"}]},
        {menuItem: "My Account", icon1: "fa fa-user", view: "myaccount", icon2: "fa fa-chevron-down", child: "1", subShow: false, subMenu: [{menuItem: "Change Password", child: "0", view: "myaccount"}, {menuItem: "Logout", child: "0"}]}
    ];

    $rootScope.cur = 0;
    $rootScope.last = -1;
    $rootScope.lastParent2 = 0;
    $rootScope.lastParent1 = 0;

    $rootScope.element = "announ";
    $rootScope.elem1 = "announ";
    $rootScope.elem2 = "";

    $rootScope.events = {};
    $rootScope.callists = {};

    $rootScope.colleges = {};
    $rootScope.tests = {};

    $rootScope.dtests = [];

    $rootScope.scholars = {};
    $rootScope.myscholars = [];
    $rootScope.resources = {};
    $rootScope.announcements = {};
    $rootScope.tlist = {};


    $rootScope.attevents = {};
    $rootScope.testresults = {};
    //$rootScope.currentPage = 1;
    $scope.init = function () {
        ealpService.get({action:"getAnnouncements"},'admin1').then(function (data) {
            if (data.data.responseCode != "400") {
                $rootScope.announcements = data.data;
            }
        });

        ealpService.get({action:"getResources"},"admin1").then(function (data) {
            if (data.data.responseCode != "400") {
                $rootScope.resources = data.data;
            }
        });
       
        ealpService.get({action:"getevents"},"admin1").then(function (data) {
            if (data.data.responseCode != 400) {
                $rootScope.events = data.data;
            }
        });
        ealpService.get({action:"getcallists"},'admin2').then(function (data) {
            if (data.data.responseCode != 400) {
                $rootScope.callists = data.data;
            }
        });
        ealpService.get({action:"getcolleges"},"admin2").then(function (data) {
            if (data.data.responseCode != 400) {
                $rootScope.colleges = data.data;
            }
        });
        ealpService.get({action:"gettests"},"admin2").then(function (data) {
            if (data.data.responseCode != 400) {
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
        ealpService.get({action:"gettestlist"},"admin2").then(function (data) {
            if (data.data.responseCode != "400" && data.data.responseCode != "403") {
                $rootScope.tlist = data.data;
            }
        });
      
        ealpService.get({action:"geteventswithpeople"},"admin2").then(function (data) {
            if (data.data.responseCode != "400" && data.data.responseCode != "403") {
                $rootScope.attevents = data.data;
            }
        });
        ealpService.get({action:"gettestwithresults"},"admin2").then(function (data) {
            if (data.data.responseCode != "400" && data.data.responseCode != "403") {
                $rootScope.testresults = data.data;
            }
        });

    }


});


ealpApp.controller("scholar1Ctrl", function ($scope, $rootScope, ealpService) {
    // menu

    $rootScope.menus = [{menuItem: "Announcements", icon1: "fa fa-bullhorn", child: '0', view: "announ"},
        {menuItem: "My Application", icon1: "fa fa-pencil", icon2: "fa fa-chevron-down", child: "1", subShow: false, view: "myapp",
            subMenu: [{menuItem: "Personal Details", child: "0", view: "pdets"}, {menuItem: "Academic Information", view: "acad", child: "1", subShow: false, icon2: "fa fa-chevron-down",
                    subMenu: [{menuItem: "School and Fees Info", child: "0", view: "fees"}, {menuItem: "Academic Transcript", child: "0", view: "transcript"}, {menuItem: "Activities", child: "0", view: "acts"}, {menuItem: "Other info", child: "0", view: "other"}]}, {menuItem: "Essays", child: "0", view: "essays"}, {menuItem: "Submit Application", child: "0", view: "submit"}]},
        {menuItem: "Resources", child: '0', icon1: "fa fa-life-ring", view: "resources"},
        {menuItem: "My Account", icon1: "fa fa-user", icon2: "fa fa-chevron-down", child: '1', subShow: false, view: "myaccount", subMenu: [{menuItem: "Change Password", child: "0", view: "changepass"}, {menuItem: "Logout", child: "0"}]}];
    $rootScope.cur = 0;
    $rootScope.last = -1;
    $rootScope.lastParent2 = -1;
    $rootScope.lastParent1 = 0;

    $rootScope.element = "announ";
    $rootScope.elem1 = "";
    $rootScope.elem2 = "";

    $scope.frmPdets = {};
    // init function
    $rootScope.announcements = {};
    $rootScope.resources = {};
    $scope.init = function () {


        ealpService.get({action: "getAnnouncements"}, 'admin1').then(function (data) {
            $rootScope.announcements = data.data;

        });
        ealpService.get({action: "getResources"}, "admin1").then(function (data) {
            $rootScope.resources = data.data;

        });


    }

});

ealpApp.controller("scholar2Ctrl", function ($scope, $rootScope, ealpService) {
    $rootScope.menus = [{menuItem: "Announcements", icon1: "fa fa-bullhorn", child: '0', view: "announ"},
        {menuItem: "Profile", icon1: "fa fa-paw", child: "0", view: "profile"},
        {menuItem: "Essays", icon1: "fa fa-file-word-o", child: "0", view: "essays"},
        {menuItem: "Other Documents", icon1: "fa fa-folder-o", child: "0", view: "docs"},
        {menuItem: "Tests", icon1: "fa fa-newspaper-o", child: "1", subShow: false, view: "tests", icon2: "fa fa-chevron-down", subMenu: [{menuItem: "Test Dates", child: "0", view: "testdates", }, {menuItem: "Test Results", child: "0", view: "testresults", }]},
        {menuItem: "Upcoming Events", icon1: "fa fa-calendar", child: '0', view: "events", },
        {menuItem: "Resources", child: '0', icon1: "fa fa-life-ring", view: "res", },
        {menuItem: "My Account", icon1: "fa fa-user", icon2: "fa fa-chevron-down", subShow: false, view: "myacc", child: '1', subMenu: [{menuItem: "Change Password", child: "0", view: "cpass", }, {menuItem: "Logout", child: "0"}]}];
    $rootScope.cur = 0;
    $rootScope.last = -1;
    $rootScope.lastParent2 = 0;
    $rootScope.lastParent1 = 0;

    $rootScope.element = "announ";
    $rootScope.elem1 = "announ";
    $rootScope.elem2 = "";

    $rootScope.events1 = [];
    $rootScope.events2 = [];

    $rootScope.tests1 = [];
    $rootScope.tests2 = [];

    $rootScope.pastests = {};
    $rootScope.results = [];

    $rootScope.colleges = [];
    $rootScope.mycolleges = [];
    $rootScope.collegessays = [];

    $rootScope.commonapp = false;

    $rootScope.myessays = [];
    $rootScope.d1 = [];
    $rootScope.announcements = {};
    $rootScope.resources = {};
    $rootScope.mentor = "";

    $scope.init = function () {

        ealpService.get({action: "getAnnouncements"}, 'admin1').then(function (data) {
            $rootScope.announcements = data.data;

        });
        ealpService.get({action: "getResources"}, "admin1").then(function (data) {
            $rootScope.resources = data.data;

        });
        ealpService.get({action: "getscholarevents"}, "scholar2").then(function (data) {
            if (data.data.responseCode != "400") {
                $.each(data.data, function (i, dt) {
                    if (dt.going == "1") {
                        $rootScope.events1.push(dt);
                    } else {
                        $rootScope.events2.push(dt);
                    }
                })
            }
        });
        ealpService.get({action:"gettests"},"scholar2").then(function (data) {
            if (data.data.responseCode != "400") {
                $.each(data.data, function (i, dt) {
                    if (dt.going == "1" || dt.official == "1") {
                        $rootScope.tests1.push(dt);
                    } else {
                        $rootScope.tests2.push(dt);
                    }
                })
            }
            console.log($rootScope.tests1);
        });
        ealpService.get({action:"getpasttests"},"scholar2").then(function (data) {
            if (data.data.responseCode != "400" || data.data.responseCode != "403") {
                $rootScope.pastests = data.data;

            }
        });
        ealpService.get({action:"getresults"},"scholar2").then(function (data) {
            if (data.data.responseCode != "400" || data.data.responseCode != "403") {
                $.each(data.data, function (i, dt) {
                    $rootScope.results.push(dt);
                })

            }
        });
        ealpService.get({action:"getcolleges"},"scholar2").then(function (data) {
            if (data.data.responseCode != "400" || data.data.responseCode != "403") {
                $.each(data.data, function (i, dt) {
                    if (dt.mycollege == "1") {
                        if (dt.commonapp == "1") {
                            $rootScope.commonapp = true;
                        }
                        $rootScope.mycolleges.push(dt);
                        $rootScope.collegessays.push(dt);
                    } else {
                        $rootScope.colleges.push(dt);
                    }

                })
                if ($rootScope.commonapp == true) {
                    $rootScope.collegessays.push({name: "Common App"});
                }

            }

        });
        ealpService.get({action:"getdocuments"},"scholar2").then(function (data) {
            if (data.data.responseCode != "400" && data.data.responseCode != "403") {
                $.each(data.data, function (i, dt) {
                    if (dt.essayid) {
                        $rootScope.myessays.push(dt);
                    } else {

                    }
                });
            }
        });
        ealpService.get({action:"getmymentor"},"scholar2").then(function (data) {
            if (data.data.responseCode == "403") {
                $rootScope.mentor = "Not Assigned";
            } else {
                $rootScope.mentor = data.data.description;
            }
        });

    }
});


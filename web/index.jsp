<%-- 
    Document   : index
    Created on : Jan 27, 2015, 5:24:32 PM
    Author     : eva
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EALP CCP</title>

        <link href="assets/css/foundation.min.css" rel="stylesheet" type="text/css"/>
        <link href="assets/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <link href="assets/css/menu.css" rel="stylesheet" type="text/css"/>
        <link href="assets/css/tabs.css" rel="stylesheet" type="text/css"/>
        <link href="assets/css/loading-bar.css" rel="stylesheet" type="text/css"/>
        <link href="assets/css/accordion.css" rel="stylesheet" type="text/css"/>
        <link href="assets/css/sardinhatable.css" rel="stylesheet" type="text/css"/>
        <link href="assets/css/progressbar.css" rel="stylesheet" type="text/css"/>
        <link href="assets/css/angular-date-picker-polyfill-basic.css" rel="stylesheet" type="text/css"/>
        <link href="assets/css/styles.css" rel="stylesheet" type="text/css"/>
        <link href="assets/css/ng-quick-date-plus-default-theme.css" rel="stylesheet" type="text/css"/>
    </head>
    <body ng-app = "ealpApp"  >
        <div ng-view=""></div>
        <script src="assets/js/libs/jquery-1.10.2.min.js" type="text/javascript"></script>
        <script src="assets/js/libs/modernizr.js" type="text/javascript"></script>
        <script src="assets/js/libs/angular.min.js" type="text/javascript"></script>
        <script src="assets/js/libs/angular-route.js" type="text/javascript"></script>
        <script src="assets/js/libs/angular-animate.min.js" type="text/javascript"></script>

        <script src="assets/js/libs/dirPagination.js" type="text/javascript"></script>
        <script src="assets/js/libs/loading-bar.js" type="text/javascript"></script>
        <script src="assets/js/libs/roundProgress.min.js" type="text/javascript"></script>
        <script src="assets/js/libs/moment.js" type="text/javascript"></script>
        <script src="assets/js/libs/ng-quick-date.js" type="text/javascript"></script>

        <script src="assets/js/app.js" type="text/javascript"></script>
        <script src="assets/js/services/utils.js" type="text/javascript"></script>
        <script src="assets/js/services/ealpService.js" type="text/javascript"></script>
        <script src="assets/js/services/homeService.js" type="text/javascript"></script>


        <script src="assets/js/libs/classie.js" type="text/javascript"></script>
        <script src="assets/js/libs/menu.js" type="text/javascript"></script>
        <script src="assets/js/libs/tabs.js" type="text/javascript"></script>

        <script src="assets/js/controllers/Counselor.js" type="text/javascript"></script>
        <script src="assets/js/controllers/Scholar1.js" type="text/javascript"></script>
        <script src="assets/js/controllers/admin.js" type="text/javascript"></script>

        <script src="assets/js/components/fileInput/fileInput.spec.js" type="text/javascript"></script>
        <script src="assets/js/components/announcements/annoucements.specs.js" type="text/javascript"></script>
        <script src="assets/js/components/application/application.spec.js" type="text/javascript"></script>
        <script src="assets/js/components/applicationpool/applicationpool.spec.js" type="text/javascript"></script>
        <script src="assets/js/components/callist/callist.spec.js" type="text/javascript"></script>
        <script src="assets/js/components/colleges/colleges.spec.js" type="text/javascript"></script>
        <script src="assets/js/components/commonapp/commonapp.spec.js" type="text/javascript"></script>
        <script src="assets/js/components/documents/documents.spec.js" type="text/javascript"></script>
        <script src="assets/js/components/essays/essays.spec.js" type="text/javascript"></script>
        <script src="assets/js/components/events/events.spec.js" type="text/javascript"></script>
        <script src="assets/js/components/myaccount/myaccount.spec.js" type="text/javascript"></script>
        <script src="assets/js/components/profile/profile.spec.js" type="text/javascript"></script>
        <script src="assets/js/components/reports/reports.spec.js" type="text/javascript"></script>
        <script src="assets/js/components/resources/resources.spec.js" type="text/javascript"></script>
        <script src="assets/js/components/scholars/scholars.spec.js" type="text/javascript"></script>
        <script src="assets/js/components/sys/sys.spec.js" type="text/javascript"></script>
        <script src="assets/js/components/tests/test.spec.js" type="text/javascript"></script>
        <!--<script src="assets/js/application.js" type="text/javascript"></script>-->
        <script src="assets/js/components/useraccounts/useraccounts.spec.js" type="text/javascript"></script>

    </body>

</html>

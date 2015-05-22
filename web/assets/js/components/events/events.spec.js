ealpApp.directive("addEvent", function (ealpService) {
    return{
        restrict: 'E',
        scope: true,
        templateUrl: './partials/events/addevent.jsp',
        controller: function ($scope) {
            $scope.event = {};
            $scope.etime = "";
            $scope.$watch("etime", function () {
                $scope.event.time = moment($scope.etime).format('HH:mm');
            }, true);
            $scope.addEvent = function () {
                $scope.event.date = moment($scope.etime).format('DD-MM-YYYY');
                $scope.event.action = "addevent";
                ealpService.set($scope.event,  "admin1",".event-alert");
                $scope.event = {};
                $scope.etime = "";
            }
        }
    }
});

ealpApp.directive("manageEvents", function (ealpService) {
    return{
        restrict: "E",
        scope: true,
        templateUrl: "./partials/events/managevent.jsp",
        controller: function ($rootScope, $scope) {
            $scope.currentPage = 1;
            $scope.pageSize = 5;
            //$scope.pages = 3;
            //$scope.etime = "";
            //$scope.i = 0;

            $scope.edit = function (ind) {
                // $scope.i = ind;
                $scope.number = (ind + 1) + ($scope.currentPage - 1) * $scope.pageSize;
                $rootScope.events[$scope.number - 1].edit = "1";

                // console.log();
            }
            $scope.save = function (ind) {
                $scope.number = (ind + 1) + ($scope.currentPage - 1) * $scope.pageSize;
                //console.log($scope.etime);
                $rootScope.events[$scope.number - 1].date = moment($rootScope.events[$scope.number - 1].etime).format('DD-MM-YYYY');
                $rootScope.events[$scope.number - 1].time = moment($rootScope.events[$scope.number - 1].etime).format('HH:mm');
                $rootScope.events[$scope.number - 1].action = "updateevent";
                ealpService.set($rootScope.events[$scope.number - 1],"admin1",".ev-alert").then(function(data){
                    if (data.data.responseCode == "200"){
                        $rootScope.events[$scope.number - 1].edit = "0";
                    }
                })
                
            }

            $scope.delete = function (ind) {
                $scope.number = (ind + 1) + ($scope.currentPage - 1) * $scope.pageSize;
                ealpService.set($rootScope.events[$scope.number - 1],"admin1",".deleteevent").then(function (data) {
                    if (data.data.responseCode == "200") {
                        $rootScope.events.splice($scope.number - 1, 1);
                    }
                });

            }
        }
    }
});
ealpApp.directive("upcomingEvents", function (ealpService) {
    return{
        restrict: "E",
        scope: true,
        templateUrl: './partials/events/upcomingevents.jsp',
        controller: function ($scope, $rootScope) {
            $scope.pageSize = 3;

            $scope.currentPage = 1;
            $scope.currentPage1 = 1;

            $scope.attend = function (event) {
                event.action = "attend";
                ealpService.set(event,"scholar2",".attend-alert").then(function (data) {
                    if (data.data.responseCode == "200") {
                        var index = $rootScope.events2.indexOf(event);
                        $rootScope.events2.splice(index, 1);
                        $rootScope.events1.push(event);
                    }
                });
            }
            $scope.withdraw = function (event) {
                event.action = "notattending";
                ealpService.set(event,"scholar2",".attend-alert").then(function (data) {
                    if (data.data.responseCode == "200") {
                        var index = $rootScope.events1.indexOf(event);
                        $rootScope.events1.splice(index, 1);
                        $rootScope.events2.push(event);

                        // console.log($rootScope.events2);
                    }
                });
            }
        }
    }
});

ealpApp.directive("eventList", function (ealpService) {
    return{
        restrict: "E",
        scope: true,
        templateUrl: './partials/events/eventlist.jsp',
        controller: function ($scope) {
            $scope.pageSize = 5;
            $scope.currentPage = 1;
            $scope.pageSize1 = 5;
            $scope.currentPage1 = 1;

            $scope.eventlist = {};
            $scope.sevent = {};

            $scope.showlist = true;
            $scope.getList = function (event) {
                event.action = "geteventlist";
                ealpService.get(event,"admin2").then(function (data) {
                    if (data.data.responseCode != "400" && data.data.responseCode != "403") {
                        $scope.showlist = false;
                        $scope.eventlist = data.data;
                        $scope.sevent = event;
                    }
                })
            }
            $scope.goBack = function () {
                $scope.showlist = true;
            }
            $scope.printEventList = function (event) {
                window.open("reports?action=" + "geteventlist" + "&id=" + event.id);
            }
        }
    }
});

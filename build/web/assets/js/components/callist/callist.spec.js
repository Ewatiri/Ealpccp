ealpApp.directive("callList", function (ealpService) {
    return{
        restrict: "E",
        templateUrl: "./partials/callist/callist.jsp",
        controller: function ($scope) {

            $scope.callist = {};
            $scope.addList = function () {
                $scope.callist.action = "addcallist";
                ealpService.set($scope.callist, 'admin2', ".callist-alert").then(function (data) {
                    $scope.callist = {};
                })

            }
        }
    }
});
ealpApp.directive("manageCallist", function (ealpService, utility) {
    return{
        restrict: "E",
        scope: true,
        templateUrl: "./partials/callist/oldcallist.jsp",
        controller: function ($scope, $rootScope) {
            $scope.currentPage = 1;
            $scope.pageSize = 5;

            $scope.currentPage1 = 1;
            $scope.page3 = 1;

            $scope.pageSize1 = 1;
            // console.log("parent scope" +$parent.$id );
            $scope.manage = false;
            $scope.scholarlist = [];
            $scope.allscholars = [];

            $scope.edit = function (ind) {
                $scope.number = (ind + 1) + ($scope.currentPage - 1) * $scope.pageSize;
                $rootScope.callists[$scope.number - 1].edit = "1";
                //console.log( ind);
                //console.log($scope.currentPage);
            }
            $scope.save = function (ind) {
                var index = (ind + 1) + ($scope.currentPage - 1) * $scope.pageSize;
                $scope.list = $rootScope.callists[index - 1];
                // console.log($scope.list);
                $scope.list.action = "fun";
                ealpService.set($scope.list, "admin2", ".oldcall-alert").then(function (data) {
                    if (data.data.responseCode == "200") {
                        $rootScope.callists[index - 1].edit = "0";
                    }
                });
            }

            $scope.delete = function (ind) {
                var index = (ind + 1) + ($scope.currentPage - 1) * $scope.pageSize;
                $rootScope.callists[index - 1].action = "deletecallist";
                ealpService.set($rootScope.callists[index - 1], "admin2", ".oldcall-alert").then(function (data) {
                    if (data.data.responseCode == "200") {
                        //$rootScope.callists[index - 1].edit = "0";
                        $rootScope.callists.splice(index - 1, 1);
                    }
                });
            }

            $scope.manageScholars = function (ind) {
                var index = (ind + 1) + ($scope.currentPage - 1) * $scope.pageSize;
                $scope.scholarlist = [];
                $scope.allscholars = [];
                var d = {};
                d.id = $rootScope.callists[index - 1].id;
                d.action = "getscholarlist";
                ealpService.get(d, "admin1").then(function (data) {
                    if (data.data.responseCode != "400") {
                        $.each(data.data, function (i, dt) {
                            if (dt.status) {
                                $scope.scholarlist.push(dt);
                            } else {
                                $scope.allscholars.push(dt);
                            }
                        });

                    }
                });

                $scope.list = $rootScope.callists[index - 1];
                $scope.manage = true;

            }
            $scope.addScholar = function (ind) {
                var index = ((ind + 1) + ($scope.page3 - 1) * $scope.pageSize1) - 1;
                if ($scope.allscholars[index].feedback == "" || $scope.allscholars[index].feedback == null) {
                    //console.log($scope.pageSize1);
                    //console.log($scope.page3);
                    //console.log($scope.allscholars[index]);
                    utility.showAlert(".oldcall2-alert", "alert", "The feedback field is empty", 5000);
                } else {
                    $scope.allscholars[index].action = "addscholarlist";
                    $scope.allscholars[index].listid = $scope.list.id;
                    ealpService.set($scope.allscholars[index], "admin2", ".oldcall2-alert").then(function (data) {
                        if (data.data.responseCode != "400") {
                            $scope.allscholars[index].edit = "1";
                            $scope.scholarlist.push($scope.allscholars[index]);
                            $scope.allscholars.splice(index, 1);
                        }
                    });
                }


            }
            $scope.viewCallists = function () {
                $scope.manage = false;
            }
            $scope.editFeedback = function (ind) {
                var index = ((ind + 1) + ($scope.currentPage1 - 1) * $scope.pageSize1) - 1;
                $scope.scholarlist[index].edit = "2";
            }
            $scope.saveFeedback = function (ind) {
                var index = ((ind + 1) + ($scope.currentPage1 - 1) * $scope.pageSize1) - 1;
                $scope.scholarlist[index].action = "editfeedback";
                $scope.scholarlist[index].listid = $scope.list.id;
                ealpService.set($scope.scholarlist[index],'admin2',".oldcall2-alert").then(function (data) {
                    if (data.data.responseCode == "200") {
                        $scope.scholarlist[index].edit = "1";
                    }
                });
            }
        }
    }
});

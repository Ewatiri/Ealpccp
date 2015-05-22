ealpApp.directive("documents", function (ealpService, utility) {
    return{
        restrict: "E",
        scope: true,
        templateUrl: './partials/documents/document.jsp',
        controller: function ($scope) {
            $scope.doc1 = {};
            $scope.doc2 = {};
            $scope.doc3 = {};

            $scope.dochist = {};
            $scope.fback = {};

            $scope.getDocStatus = function () {
                ealpService.get({action:"getdocstatus"},"scholar2").then(function (data) {
                    if (data.data.responseCode != "400" && data.data.responseCode != "403") {
                        $scope.fback = data.data;
                    }
                });
            }

            $scope.getHistory = function (id) {
                $scope.dochist = {};
                $(".dochist-alert").css("display", "none");
                var d = {};
                d.id = id;
                d.action = "getdochist";
                ealpService.get(d,"scholar2").then(function (data) {
                    if (data.data.responseCode != "400" && data.data.responseCode != "403") {
                        $scope.dochist = data.data;
                    }
                })
            }

            $scope.uploadDocument = function (form) {
                var fd = new FormData();


                if (form == "1") {
                    angular.forEach($scope.doc1, function (value, key) {
                        fd.append("type", "0");
                        if (key == "file") {
                            fd.append(key, value[0]);
                        } else {
                            fd.append(key, value);
                        }
                    });
                } else if (form == '2') {
                    angular.forEach($scope.doc2, function (value, key) {
                        fd.append("type", "1");
                        if (key == "file") {
                            fd.append(key, value[0]);
                        } else {
                            fd.append(key, value);
                        }
                    });
                } else if (form == '3') {
                    angular.forEach($scope.doc3, function (value, key) {
                        fd.append("type", "2");
                        if (key == "file") {
                            fd.append(key, value[0]);
                        } else {
                            fd.append(key, value);
                        }
                    });
                }

                ealpService.uploadDocument(fd,"addDocument",".document-alert").then(function (data) {

                })

            }
            $scope.deleteDoc = function (doc) {
                doc.action = "deletedocument";
                ealpService.set(doc,"scholar2",".document-alert").then(function (data) {
                    if (data.data.responseCode == "200") {
                        var index = $scope.dochist.indexOf(doc);
                        $scope.dochist.splice(index, 1);
                    }
                });
            }
        }
    }
});

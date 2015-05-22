ealpApp.directive("commonappEssays", function () {
    return{
        restrict: 'E',
        transclude: true,
        template: '<div  >' +
                '<div ng-transclude ></div>' +
                '</div>'

    }
})

ealpApp.directive("commonappEssay", function () {
    return{
        restrict: 'E',
        transclude: true,
        template: "<form name='essayForm' ng-submit='commitPrompt(field)'>" +
                "<div ng-transclude ></div>" +
                "</form>"
    }
});
ealpApp.directive("commonApp", function (ealpService) {
    return{
        restrict: "E",
        scope: true,
        templateUrl: './partials/commonapp/commonapp.jsp',
        controller: function ($scope, $rootScope) {

            $scope.addEssay = function () {
                $rootScope.prompts.push({});
            }
            $scope.removePrompt = function (field) {
                var index = $rootScope.prompts.indexOf(field);
                //console.log(field);
                if (index >= 0) {
                    $rootScope.prompts.splice(index, 1);
                }
            }

            $scope.commitPrompt = function (field) {
                var d = {};
                d.action = "addcommonapp";
                d.prompt = field.prompt;
                ealpService.set(d,"admin2",".commonapp-alert").then(function (data) {
                    if (data.data.responseCode == "200") {
                        field.edit = "0";
                    }
                })
            }
            $scope.delete = function (field) {
                var d = {};
                d.action = "deletecommonapp";
                d.id = field.id;
                ealpService.set(d,"admin2",".commonapp-alert").then(function (data) {
                    if (data.data.responseCode == "200") {
                        var index = $rootScope.prompts.indexOf(field);
                        //console.log(field);
                        if (index >= 0) {
                            $rootScope.prompts.splice(index, 1);
                        }
                    }
                });
            }
        }
    }
});



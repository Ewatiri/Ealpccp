ealpApp.directive("fileInput", ['$parse', function ($parse) {
        return{
            restrict: 'A',
            link: function (scope, elem, attr) {
                elem.bind('change', function () {
                    $parse(attr.fileInput).assign(scope, elem[0].files);
                    scope.$apply();
                })
            }
        }
 }]);



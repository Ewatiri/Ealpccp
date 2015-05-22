ealpApp.directive("myMenu", function ($rootScope, $http, $location) {
    return{
        restrict: 'A',
        scope: {
            ind: "=",
            level: "=",
            item: "="
        },
        transclude: true,
        link: function (scope, elem, attrs, ctrl, transclude) {
            elem.on("click", function (e) {
                e.preventDefault();
                var ind = scope.ind;
                var click = 0;
                // close elements
                if ($rootScope.lastParent1 != -1) {

                    if (scope.level == "1") {
                        click += 1;
                        if ($rootScope.menus[$rootScope.lastParent1].child == "1" && $rootScope.lastParent1 != ind) {

                            scope.$apply(function () {
                                $rootScope.menus[$rootScope.lastParent1].subShow = false;
                                $rootScope.menus[$rootScope.lastParent1].icon2 = "fa fa-chevron-down";
                            });

                            if ($rootScope.lastParent2 !== -1) {
                                if ($rootScope.menus[$rootScope.lastParent1].subMenu[$rootScope.lastParent2]) {
                                    if ($rootScope.menus[$rootScope.lastParent1].subMenu[$rootScope.lastParent2].child == "1") {
                                        scope.$apply(function () {
                                            $rootScope.menus[$rootScope.lastParent1].subMenu[$rootScope.lastParent2].subShow = false;
                                            $rootScope.menus[$rootScope.lastParent1].subMenu[$rootScope.lastParent2].icon2 = "fa fa-chevron-down";
                                        });
                                    }
                                }


                            }
                        }


                    } else if (scope.level == "2") {
                        if ($rootScope.lastParent2 !== -1) {
                            //console.log($rootScope.lastParent1);
                            //console.log($rootScope.lastParent2);
                            if ($rootScope.menus[$rootScope.lastParent1].subMenu[$rootScope.lastParent2]) {
                                if ($rootScope.menus[$rootScope.lastParent1].subMenu[$rootScope.lastParent2].child == "1" && $rootScope.lastParent2 != ind) {
                                    scope.$apply(function () {
                                        $rootScope.menus[$rootScope.lastParent1].subMenu[$rootScope.lastParent2].subShow = false;
                                        $rootScope.menus[$rootScope.lastParent1].subMenu[$rootScope.lastParent2].icon2 = "fa fa-chevron-down";
                                    });
                                }
                            }


                        }

                    }
                }




                // open elements level 1
                if (scope.level == "1") {
                    if ($rootScope.menus[ind].child == "1") {
                        scope.$apply(function () {
                            $rootScope.menus[ind].subShow = true;
                            $rootScope.lastParent1 = ind;
                            // toggle chevy
                            $rootScope.menus[ind].icon2 = "fa fa-chevron-up";
                            // switch the view
                            $rootScope.element = $rootScope.menus[ind].view;
                            $rootScope.elem1 = $rootScope.menus[ind].subMenu[0].view;
                        });
                    } else {
                        scope.$apply(function () {
                            $rootScope.lastParent1 = ind;
                            $rootScope.element = $rootScope.menus[ind].view;
                        });
                    }

                } else if (scope.level == "2") {
                    // open elements level 2
                    if ($rootScope.menus[$rootScope.lastParent1].subMenu[ind].child == "1") {
                        scope.$apply(function () {
                            $rootScope.menus[$rootScope.lastParent1].subMenu[ind].subShow = true;
                            $rootScope.lastParent2 = ind;

                            $rootScope.menus[$rootScope.lastParent1].subMenu[ind].icon2 = "fa fa-chevron-up";
                            $rootScope.elem1 = $rootScope.menus[$rootScope.lastParent1].subMenu[ind].view;
                            $rootScope.elem2 = $rootScope.menus[$rootScope.lastParent1].subMenu[ind].subMenu[0].view;
                        });

                    } else {
                        scope.$apply(function () {
                            $rootScope.lastParent2 = ind;

                            $rootScope.elem1 = $rootScope.menus[$rootScope.lastParent1].subMenu[ind].view;
                        });
                    }

                } else if (scope.level == "3") {
                    // open elements level 1
                    scope.$apply(function () {
                        $rootScope.last = ind;

                        $rootScope.elem2 = $rootScope.menus[$rootScope.lastParent1].subMenu[$rootScope.lastParent2].subMenu[ind].view;
                    });
                }
                click = 0;

                $(this).closest('ul').find('.selected').removeClass('selected');
                $(this).parent().addClass('selected');

                if (scope.item == "Logout") {
                    scope.logout = {};
                    scope.logout.action = "logout";
                    $http({
                        method: 'POST',
                        url: 'admin1',
                        dataType: 'json',
                        data: $.param(scope.logout),
                        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                    }).success(function (data) {
                        if (data.responseCode == 200) {
                            $location.path("");
                        }
                    }).error(function (data) {

                    });
                }
            });

        }
    }
});
ealpApp.directive("chrisAccordion", function () {
    return{
        restrict: "A",
        scope:false,
        transclude: false,
        link: function (scope, elem, attrs, ctrl, transclude) {
            elem.on('click', function (e) {
                e.stopPropagation();
                e.preventDefault();
                if (e.target && e.target.nodeName == "A") {
                    var classes = e.target.className.split(" ");
                    if (classes) {
                        for (var x = 0; x < classes.length; x++) {
                            if (classes[x] == "accordionTitle") {
                                var title = e.target;

                                //next element sibling needs to be tested in IE8+ for any crashing problems
                                var content = e.target.parentNode.nextElementSibling;

                                //use classie to then toggle the active class which will then open and close the accordion

                                classie.toggle(title, 'accordionTitleActive');
                                //this is just here to allow a custom animation to treat the content
                                if (classie.has(content, 'accordionItemCollapsed')) {
                                    if (classie.has(content, 'animateOut')) {
                                        classie.remove(content, 'animateOut');
                                    }
                                    classie.add(content, 'animateIn');

                                } else {
                                    classie.remove(content, 'animateIn');
                                    classie.add(content, 'animateOut');
                                }
                                //remove or add the collapsed state
                                classie.toggle(content, 'accordionItemCollapsed');




                            }
                        }
                    }

                }
            });
           // transclude(scope.$new(), function (clone, scope) {
                //elem.append(clone);
            //});
      
        }

    }
});
ealpApp.directive("codyHouseTabs", function () {
    return{
        restrict: "A",
        scope:false,
        transclude: false,
        link: function (scope, elem, attrs, ctrl, transclude) {
            var tabItems = $('.cd-tabs-navigation a'),
                    tabContentWrapper = $('.cd-tabs-content');
            tabItems.on('click', function (event) {
                event.preventDefault();
                //console.log("called");
                var selectedItem = $(this);
                if (!selectedItem.hasClass('selected')) {
                    var selectedTab = selectedItem.data('content'),
                            selectedContent = tabContentWrapper.find('li[data-content="' + selectedTab + '"]'),
                            slectedContentHeight = selectedContent.innerHeight();

                    tabItems.removeClass('selected');
                    selectedItem.addClass('selected');
                    selectedContent.addClass('selected').siblings('li').removeClass('selected');
                    //animate tabContentWrapper height when content changes 
                    tabContentWrapper.animate({
                        'height': slectedContentHeight
                    }, 200);
                }
            });
          /* transclude(scope.$parent, function (clone, scope) {
                //elem.append(clone);
            });*/
            
        },
    }
});
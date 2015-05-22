<%-- 
    Document   : scholar2
    Created on : Mar 3, 2015, 2:51:58 PM
    Author     : eva
--%>

<div class="row" >
    <div class="scholarapp-content" >
        <div class="menu-wrapper small-5" >
             <ul class="form" >
                <div ng-repeat="menu in menus">
                    <li ng-class="{selected: $index == 0}">
                        <a  href my-menu ng-transclude   ind="$index" level="1"  ><i  ng-if = "menu.icon1"  class="{{menu.icon1}}" ></i> {{menu.menuItem}} <em ng-if="menu.icon2" ><i class="{{menu.icon2}}"></i></em> </a>	
                    </li>
                    <li ng-if = "menu.subShow" >
                        <ul  >
                            <div ng-repeat="sbmenu in menu.subMenu" >
                                <li ng-class="{selected: $index == 0}">
                                    <a  href my-menu ng-transclude level="2" ind="$index"  item="sbmenu.menuItem" >{{sbmenu.menuItem}} <em ng-if="sbmenu.icon2" ><i class="{{sbmenu.icon2}}"></i></em>  </a>
                                </li>
                                <li ng-if = "sbmenu.subShow" >
                                    <ul>
                                        <li ng-repeat = "smenu in sbmenu.subMenu" ><a class="sub2" href my-menu ng-transclude level="3" ind = "$index" level="3" item="smenu.menuItem" >{{ smenu.menuItem}}</a></li>
                                    </ul>
                                </li>	
                            </div>

                        </ul>
                    </li>
                </div>
            </ul>
        </div>
        <div class="app-content small-9 columns" ng-init="init()" >
            <div class="announcements" ng-if="element=='announ'" >
                <announcements  ></announcements>
            </div>
            <div class="profile" ng-if="element=='profile'" >
                <my-profile></my-profile>
            </div>
            <div class="essays" ng-if="element=='essays'" >
                <my-essays></my-essays>
            </div>
            <div class="documents" ng-if="element=='docs'" >
                <documents></documents>
            </div>
            <div class="tests" ng-if="element=='tests'" >
                <test-dates ng-if="elem1=='testdates'" ></test-dates>
                <test-results ng-if="elem1=='testresults'"></test-results>
            </div>
            <div class="events" ng-if="element=='events'" >
                <upcoming-events></upcoming-events>
            </div>
            <div class="resources" ng-if="element=='res'" >
                <resources></resources>
            </div>
            <div class="account" ng-if="element=='myacc'" >
                <account ng-if="elem1=='cpass'" type="2"></account>
            </div>
        </div>
    </div>
</div>

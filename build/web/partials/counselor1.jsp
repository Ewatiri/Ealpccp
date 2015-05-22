<%-- 
    Document   : counselor1
    Created on : Feb 5, 2015, 10:45:08 AM
    Author     : eva
--%>

<div class="row" ng-init="init()" >
    <div class="counselor1-content" >
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
        <div class="app-content small-9 columns" >
            <div class="announcements" ng-if="element == 'announ'" >
                <announcements ng-if="elem1 == 'announ'"></announcements>
                <add-announcements ng-if="elem1 == 'addannoun'" ></add-announcements>
                <manage-announcements ng-if="elem1 == 'announ1'"></manage-announcements>
            </div>
            <div class="app-pool" ng-if="element == 'pool'">
                <app-pool ng-if="elem1 == 'pool'" ></app-pool>
            </div>
            <div class="approvals" ng-if=" element == 'approvals'" >
                <approvals ng-if=" element == 'approvals'" ></approvals>
            </div>
            <div class="resources" ng-if=" element == 'res'"  >
                <add-resources ng-if="elem1 == 'res'" ></add-resources>
                <manage-resources ng-if="elem1 == 'res1'" ></manage-resources>
            </div>
            <div class="c-account"ng-if="element == 'myaccount'"  >
                <account ng-if="elem1 == 'myaccount'" ></account>
            </div>
        </div>
    </div>
</div>
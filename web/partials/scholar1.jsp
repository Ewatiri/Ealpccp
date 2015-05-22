<%-- 
    Document   : scholar1
    Created on : Feb 1, 2015, 3:35:55 PM
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
            <!-- Announcements-->
            <announcements ng-if="element=='announ'" ></announcements>
            <!--Application-->
            <scholar-application ng-if="element=='myapp'" ></scholar-application>

            <!--Resources-->
            <resources ng-if="element=='resources'" ></resources>
            <!--Account-->
            <account ng-if="elem1=='changepass'" type="2"></account>
        </div>
    </div>



</div>

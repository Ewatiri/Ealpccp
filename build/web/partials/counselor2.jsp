<%-- 
    Document   : counselor2
    Created on : Mar 3, 2015, 3:03:52 PM
    Author     : eva
--%>
<div class="row" ng-init="init()" >
    <div>
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
                <add-announcements ng-if="elem1=='addannoun'" ></add-announcements>
                <manage-announcements ng-if="elem1 == 'announ1'"></manage-announcements>
            </div>
            <div class="scholars" ng-if="element == 'scholars'"  >
                <my-scholars ng-if="elem1 == 'myscholars'" ></my-scholars>
                <all-scholars ng-if="elem1 == 'allscholars'" ></all-scholars>
            </div>
            <div class="tests" ng-if="element == 'tests'"  >
                <add-test ng-if="elem1 == 'addtests'" ></add-test>
                <manage-tests ng-if="elem1 == 'mtests'" ></manage-tests>
                <test-list ng-if="elem1 == 'testlist'" ></test-list>
            </div>
        
            <div class="colleges"  ng-if="element == 'colleges'" >
                <add-college  ng-if="elem1 == 'addcolleges'" ></add-college>
                <edit-college ng-if="elem1 == 'editcolleges'"></edit-college>
                <my-college-essays ng-if="elem1 == 'collegessays'"></my-college-essays>
            </div>
            <div class="events" ng-if="element == 'events'"  >
                <add-event ng-if="elem1 == 'addevent'" ></add-event>
                <event-list ng-if="elem1 == 'eventlist'"></event-list>
                <manage-events ng-if="elem1 == 'mevents'"></manage-events>
            </div>
            <div class="call-list"ng-if="element == 'callist'"  >
                <call-list ng-if="elem1 == 'callist'" ></call-list>
                <manage-callist ng-if="elem1 == 'mcallist'" ></manage-callist>
            </div>
            <div class="reports" ng-if="element == 'creports'" >
                <mod2-scholar-reports ng-if="elem1=='sreports'" ></mod2-scholar-reports>
                <test-reports ng-if="elem1=='treports'" ></test-reports>
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
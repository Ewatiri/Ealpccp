<%-- 
    Document   : head
    Created on : Feb 11, 2015, 4:48:19 PM
    Author     : eva
--%>


<div class="row" ng-init="init()">
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
        <div class="app-content small-9 columns" >
            <div class="announcements" ng-if="element == 'announ'" >
                <announcements ng-if="elem1 == 'announ'"></announcements>
                <add-announcements ng-if="elem1=='addannoun'" ></add-announcements>
                <manage-announcements ng-if="elem1 == 'announ1'"></manage-announcements>
            </div>
            <div class="application" ng-if="element == 'app'" >
                <app-pool ng-if="elem1 == 'pool'" ></app-pool>
                <div class="close-app" ng-if="elem1 == 'closeapp'" >
                    <div class="progress-wrapper prog1 "  >
                        <div class="nprogress" >{{sub}}/{{all}} applications <br/>have been submitted</div>
                        <div
                            round-progress
                            max="all"
                            current="sub"
                            color="#21abcd"
                            bgcolor="#efefef"
                            radius="100"
                            stroke="3"

                            rounded="false"
                            iterations="50"
                            animation="easeInOutQuart">

                        </div>
                    </div>
                    <div>
                        <form ng-submit="closeApp()" >
                            <fieldset>
                                <legend>Open/Close Application Submission</legend>
                                <div class="alert-box sysapp-alert" ></div>
                                <div class="row " >
                                    <div class="small-6 columns" >
                                        Slide the switch to open/close submission 
                                    </div>
                                    <div class="small-6 columns" >
                                        <div class="switch">
                                            <input id="appswitch" type="checkbox" ng-model="systatus" >
                                            <label for="appswitch"></label>
                                        </div>
                                    </div>
                                    <div class="row" >
                                        <div class="small-12 columns" >
                                            <div ng-if="systatus" ><em>The submission is open</em></div>
                                            <div ng-if="!systatus" ><em>The submission is closed</em></div>
                                        </div>

                                    </div>
                                    <div class="row" >
                                        <div class="small-12 columns" >
                                            <button class="small center-btn" type="submit" >Save</button>
                                        </div>
                                    </div>
                                </div>

                            </fieldset>
                        </form>
                    </div>
                </div>
                <div class="application-publish" ng-if="elem1 == 'publish'" >
                    <form ng-submit="publishResults(score)">
                        <fieldset>
                            <legend>Publish Results</legend>
                            <div class="alert-box publish-alert" ></div>
                            <div class="row">
                                <div class="small-8 columns">
                                    <div class="row collapse"  >
                                        <div class="small-4 columns" >
                                            <span class="prefix" >Minimum Score(per section)</span>
                                        </div>
                                        <div class="small-8 columns" >
                                            <input type="number" min="1" step="0.1" ng-model="score"  max="5" placeholder="3" required/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row" >
                                <div class="small-8" >
                                    <button class="button center-btn" type="submit" >Set</button>
                                </div>

                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
            <div class="reports" ng-if="element == 'report1'" >
                <div class="accepted" ng-if="elem1=='accepted'" >
                    <scholar-report></scholar-report>
                </div>
                <div class="analysis" ng-if="elem1=='analysis'" >
                    <application-analysis></application-analysis>
                </div>
                
            </div>
            <div class="approval" ng-if="element == 'useracc'" >
                <approvals ng-if="elem1 == 'scholar'" ></approvals>
                <div ng-if="elem1 == 'counselor'" >
                    <add-mentor ng-if="elem2 == 'addc'" ></add-mentor>
                    <del-mentor ng-if="elem2 == 'delc'"></del-mentor>
                </div>
            </div>
            <div class="resources" ng-if="element == 'res'" >
                
                <resources ng-if="elem1 == 'res1'" ></resources>
                <add-resources ng-if="elem1 == 'res3'"></add-resources>
                <manage-resources ng-if="elem1 == 'res2'" ></manage-resources>
            </div>

            <div class="system-details" ng-if="element == 'sys'" >
                <sys ng-if="element == 'sys'" ></sys>
            </div>
            <div class="account" ng-if="element == 'acc'">
                <account ng-if="elem1 == 'acc'" type = "1"></account>
            </div>

        </div>
    </div>
</div>
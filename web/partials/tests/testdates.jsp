<%-- 
    Document   : testdates
    Created on : Mar 18, 2015, 2:29:01 PM
    Author     : eva
--%>
<div class="row" >
    <div class="small-12 columns" >
        <div class="cd-tabs" >
            <nav>
                <ul class="cd-tabs-navigation">
                    <li><a  data-content="mytests" class="selected" ng-href cody-house-tabs ><i class="fa fa-user icon"></i>My Tests</a></li>
                    <li><a data-content="othertests"ng-href cody-house-tabs  ><i class="fa fa-pencil icon"></i>Other Tests</a></li>

                </ul> 
            </nav>
            <ul class="cd-tabs-content">
                <li data-content="mytests" class="selected">
                    <div class="row" >
                        <div class="small-12 columns" >
                            <div class="row collapse" >
                                <div class="small-4 columns" >
                                    <span class="prefix" >Filter</span>
                                </div>
                                <div class="small-8 columns" >
                                    <input type="text" ng-model="fil1" />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="alert-box test3-alert " ></div>
                    <div class="stitched" dir-paginate="test in tests1 |filter:fil1 |itemsPerPage: pageSize" current-page="currentPage" pagination-id="t1" >
                        <div class="row" >
                            <div class="small-12 columns" >
                                <p>Test: {{ test.title}}  </p>
                            </div>

                        </div>
                        <div class="row" >
                            <div class="small-6 columns" ng-if="test.official == '0'">
                                <p  >Test Type: <span class="capitalize" >Official</span>  </p>
                            </div>
                            <div class="small-6 columns" ng-if="test.official == '1'">
                                <p  >Test Type: <span class="capitalize" >Diagnostic Test</span>  </p>
                            </div>
                            <div class="small-6 columns" >
                                <p>Test Type: <span class="capitalize" >{{ test.type}}</span>  </p>
                            </div>
                        </div>
                        <div class="row" >
                            <div class="small-6 columns" >
                                <p> Time: {{ test.ttime}}  </p>
                            </div>
                            <div class="small-6 columns" >
                                <p> Date: {{ test.tdate}}  </p>
                            </div>
                        </div>
                        <div class="row" >
                            <div class="small-12 columns" >
                                <p> Location: {{ test.location}}  </p>
                            </div>
                        </div>
                        <div class="row" >
                            <button ng-if="test.official == '0'" ng-click="unregister(test)"class="small radius center-btn " type="button" >Unregister</button>
                        </div>
                    </div>
                <dir-pagination-controls pagination-id="t1"  ></dir-pagination-controls>       
                </li>
                <li data-content="othertests" >
                    <div class="row" >
                        <div class="small-12 columns" >
                            <div class="row collapse" >
                                <div class="small-4 columns" >
                                    <span class="prefix" >Filter</span>
                                </div>
                                <div class="small-8 columns" >
                                    <input type="text" ng-model="fil" />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="alert-box test3-alert " ></div>
                    <div class="stitched" dir-paginate="test in tests2 |filter:fil |itemsPerPage: pageSize" current-page="currentPage1" pagination-id="t2" >
                        <div class="row" >
                            <div class="small-12 columns" >
                                <p>Test: {{ test.title}}  </p>
                            </div>

                        </div>
                        <div class="row" >
                            <div class="small-6 columns" ng-if="test.official == '0'">
                                <p  >Test Type: <span class="capitalize" >Official</span>  </p>
                            </div>
                            <div class="small-6 columns" ng-if="test.official == '1'">
                                <p  >Test Type: <span class="capitalize" >Diagnostic Test</span>  </p>
                            </div>
                            <div class="small-6 columns" >
                                <p>Test Type: <span class="capitalize" >{{ test.type}}</span>  </p>
                            </div>
                        </div>
                        <div class="row" >
                            <div class="small-6 columns" >
                                <p> Time: {{ test.ttime}}  </p>
                            </div>
                            <div class="small-6 columns" >
                                <p> Date: {{ test.tdate}}  </p>
                            </div>
                        </div>
                        <div class="row" >
                            <div class="small-12 columns" >
                                <p> Location: {{ test.location}}  </p>
                            </div>
                        </div>
                        <div class="row" >
                            <button  class="small radius center-btn " ng-click="register(test)" type="button" >Register for Test</button>
                        </div>
                    </div>
                <dir-pagination-controls pagination-id="t2"  ></dir-pagination-controls>       
                </li>
            </ul>
        </div>
    </div>
</div>
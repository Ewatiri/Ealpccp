<%-- 
    Document   : testresults
    Created on : Mar 18, 2015, 5:28:30 PM
    Author     : eva
--%>
<div class="row" >
    <div class="small-12 columns" >
        <div class="cd-tabs" >
            <nav>
                <ul class="cd-tabs-navigation">
                    <li><a  data-content="mytests" class="selected" ng-href cody-house-tabs ><i class="fa fa-user icon"></i>Upload Test Scores</a></li>
                    <li><a data-content="othertests"ng-href cody-house-tabs  ><i class="fa fa-pencil icon"></i>Test Scores</a></li>

                </ul> 
            </nav>
            <ul class="cd-tabs-content">
                <li data-content="mytests" class="selected">
                    <div class="alert-box test4-alert " ></div>
                    <form ng-submit="uploadScore()" >
                        <fieldset>
                            <legend>Test scores</legend>
                            <div class="alert-box score-alert" ></div>
                            <div class="row collapse" >
                                <div class="small-4 columns" >
                                    <span class="prefix" >Pick Test</span>
                                </div>
                                <div  >
                                    <select class="small-8 columns" ng-model="selected.test" ng-options="t.title for t in pastests" required>
                                        <option value="">--</option>  
                                    </select>
                                </div>
                            </div>
                            <div class="stitched" ></div>
                            <fieldset ng-if ="selected.test">
                                <legend>Test Details</legend>
                                <p>Time: {{selected.test.ttime}} | Date: {{selected.test.tdate}}</p>
                                <p>Type : {{ selected.test.type}}</p>
                                <p>Location: {{selected.test.location}}</p>
                            </fieldset>
                            <fieldset>
                                <legend>Test Score</legend>
                                <div ng-if="selected.test.type == 'sat1'" >
                                    <div class="row collapse" >
                                        <div class="small-4 columns" >
                                            <span class="prefix" >Mathematics</span>
                                        </div>
                                        <div class="small-8 columns" >
                                            <input type="number" ng-model="selected.math" required="" max="800" min="1" />
                                        </div>
                                    </div>
                                    <div class="row collapse" >
                                        <div class="small-4 columns" >
                                            <span class="prefix" >Critical Reading</span>
                                        </div>
                                        <div class="small-8 columns" >
                                            <input type="number" min="0" ng-model="selected.cr" max="800" required="" />
                                        </div>
                                    </div>
                                    <div class="row collapse" >
                                        <div class="small-4 columns" >
                                            <span class="prefix" >Writing</span>
                                        </div>
                                        <div class="small-8 columns" >
                                            <input type="number" ng-model="selected.writing" min="0" max="800" required="" />
                                        </div>
                                    </div>
                                </div>
                                <div ng-if="selected.test.type == 'sat2'" >
                                    <div class="row collapse" >
                                        <div class="small-4 columns" >
                                            <select ng-model="selected.subj1" ng-options="subj for subj in subjects " required></select>
                                        </div>
                                        <div class="small-8 columns" >
                                            <input type="number" min="0" max="800" ng-model="selected.score1" required="" />
                                        </div>
                                    </div>
                                    <div class="row collapse" >
                                        <div class="small-4 columns" >
                                            <select ng-model="selected.subj2" ng-options="subj for subj in subjects " required></select>
                                        </div>
                                        <div class="small-8 columns" >
                                            <input type="number" min="0" max="800" ng-model="selected.score2" required="" />
                                        </div>
                                    </div>
                                    <div class="row collapse" >
                                        <div class="small-4 columns" >
                                            <select ng-model="selected.subj3" ng-options="subj for subj in subjects " ></select>
                                        </div>
                                        <div class="small-8 columns" >
                                            <input type="number" min="0" max="800" ng-model="selected.score3" />
                                        </div>
                                    </div>
                                </div>
                                <div ng-if="selected.test.type == 'act' || selected.test.type == 'toefl'" >
                                    <div class="row collapse" >
                                        <div class="small-4 columns" >
                                            <span class="prefix" >Score</span>
                                        </div>
                                        <div class="small-8 columns" >
                                            <input type="number" min="0" ng-model="selected.score"  required="" />
                                        </div>
                                    </div>
                                </div>
                            </fieldset>  
                            <button type="submit" class ="small button radius">Add</button>
                        </fieldset>
                    </form>

                </li>
                <li data-content="othertests" >
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
                    <div dir-paginate="result in results |filter:fil1 |itemsPerPage: pageSize" current-page="currentPage" pagination-id="t1" >
                        <fieldset>
                            <legend>{{result.test}}</legend>
                            <div class="row" >
                                <div class="small-3 columns" >
                                    <p  >Test Date: {{ result.date }}</p>
                                </div>
                                <div class="small-9 columns"  ng-if="result.type != 'sat1' && result.type != 'sat2'" >
                                    <p> Test Score : {{ result.score }}  </p>
                                </div>
                                <div ng-if="result.type == 'sat1' || result.type == 'sat2'">
                                    <div class="small-3 columns" >
                                        <p class="capitalize" >{{result.s1}} : {{result.score1}}</p>
                                    </div>
                                    <div class="small-3 columns" >
                                        <p class="capitalize" >{{result.s2}} : {{result.score2}}</p>
                                    </div>
                                    <div class="small-3 columns" >
                                        <p class="capitalize" >{{result.s3}} : {{result.score3}}</p>
                                    </div>
                                </div>
                            </div>
                        </fieldset>
                    </div>
                <dir-pagination-controls pagination-id="t1"  ></dir-pagination-controls>       
                </li>
            </ul>
        </div>
    </div>
</div>
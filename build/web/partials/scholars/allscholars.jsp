<%-- 
    Document   : allscholars
    Created on : Mar 10, 2015, 3:06:04 PM
    Author     : eva
--%>
<div class="row" >
    <div class="row" ng-if="showscholar" >
        <div class="small-12 columns " >
            <div class="main-title" >Scholars</div>
            <div class="alert-box all-alert " ></div>
            <div class="accordion1" id="accordion1" chris-accordion  >
                <dl>
                    <div dir-paginate="scholar in scholars | itemsPerPage: pageSize" current-page="currentPage" >
                        <dt><a class="accordionTitle" ng-href>{{ scholar.name}}</a></dt>
                        <dd class="accordionItem accordionItemCollapsed">
                            <div class="row" ng-if ="scholar.colleges" >
                                <div class="small-12 columns" >
                                    {{scholar.name}} is applying to the following college(s):
                                    <div ng-repeat="c in scholar.colleges" >
                                        {{ c.college}}
                                    </div>  
                                </div>

                            </div>
                            <div class='row'  ng-if ="!scholar.colleges">
                                <div class="small-12 columns" >
                                    {{scholar.name}} has not yet picked any colleges.
                                </div>

                            </div>
                            <div class="row" >
                                <div class="small-12 columns" >
                                    <button type="button" class="tiny button radius" ng-click="pickScholar($index)" >Pick</button>
                                    <button type="button" class="tiny button radius" ng-click="selectScholar($index)" >Upload Test results</button>
                                </div>
                            </div>

                        </dd>
                    </div>

                </dl>
            </div>
            <dir-pagination-controls  ></dir-pagination-controls> 
        </div>
    </div>
    <div class="row" ng-if="!showscholar" >
        <div class="row" >
            <div class="small-12 columns" >
                <div class="main-title" >{{ selected.name}}</div>
                <div  class=""><button class="large-2 button secondary radius" type="button" ng-click="goBack()"><i class="fa fa-hand-o-left" ></i>Back</button></div>
                <form ng-submit="uploadScore()" >
                    <fieldset>
                        <legend>Test scores</legend>
                        <div class="alert-box score-alert" ></div>
                        <div class="row collapse" >
                            <div class="small-4 columns" >
                                <span class="prefix" >Pick Test</span>
                            </div>
                            <div  >
                                <select class="small-8 columns" ng-model="selected.test" ng-options="t.title for t in dtests">
                                    <option value="" >--</option>
                                </select>
                            </div>
                        </div>
                        <div class="stitched" ></div>
                        <fieldset ng-if ="selected.test">
                            <legend>Test Details</legend>
                            <p>Time: {{selected.test.time}} | Date: {{selected.test.date}}</p>
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
                                        <select ng-model="selected.subj3" ng-options="subj for subj in subjects " required>
                                            <option value="" >--</option>
                                        </select>
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
            </div>
        </div>
    </div>
</div>

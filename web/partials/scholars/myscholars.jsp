<%-- 
    Document   : myscholars
    Created on : Mar 10, 2015, 10:04:21 PM
    Author     : eva
--%>
<div class="row" >
    <div class="row"ng-if="my"   >
        <div class="small-12 columns" >
            <div class="main-title" >My Scholars</div>
            <div class="accordion1" id="accordion1"  >
                <dl>
                    <div dir-paginate="s in myscholars | itemsPerPage: pageSize" current-page="currentPage" >
                        <dt><a class="accordionTitle" ng-click="initScholar(s)" ng-href>{{ s.name}}</a></dt>
                    </div>
                </dl>
                <dir-pagination-controls  ></dir-pagination-controls>  
            </div>
        </div>
    </div>
    <div class="row" ng-if="!my" >
        <div class="small-12 columns" >
            <div class="main-title" >{{ selected.name}}</div>
            <div class="cd-tabs" >
                <nav>
                    <ul class="cd-tabs-navigation">
                        <li><a  data-content="profile" class="selected" ng-href cody-house-tabs ><i class="fa fa-user"></i>Scholar Profile</a></li>
                        <li><a data-content="essays"ng-href cody-house-tabs  ><i class="fa fa-pencil"></i>Essays</a></li>
                        <li><a data-content="tests" ng-href cody-house-tabs  ><i class="fa fa-folder-o"></i>Tests</a></li>
                        <li><a  data-content="docs" ng-href cody-house-tabs ><i class="fa fa-file-word-o"></i>Documents</a></li>
                    </ul> 
                </nav>
                <ul class="cd-tabs-content">
                    <li data-content="profile" class="selected">
                        <div class="alert-box sprofile-alert" ></div>
                        <fieldset class = "row">
                            <div ng-if="selected.status == '11'" >Program Type - MCF + Reg</div>  
                            <div ng-if="selected.status == '10'" >Program Type - Reg Only</div>  
                            <div ng-if="selected.status == '20'" >Program Type - MCF Only</div>  
                        </fieldset>
                        <div class="row" ng-if="scolleges" >
                            <fieldset >
                                <div class="small-12 columns" >
                                    {{ selected.name}} is applying to the following colleges:
                                </div>
                                <div class="row" >
                                    <div class="small-12 columns" >
                                        <div ng-repeat="c in scolleges" >
                                            {{ c.college}}
                                        </div>

                                    </div>
                                </div>
                            </fieldset>


                        </div>
                        <fieldset ng-if="!scolleges" class = "row">
                            <div class="small-12 columns" >
                                {{ selected.name}} is is yet to pick colleges
                            </div>

                        </fieldset>

                        <fieldset class="row" >
                            <div class="small-12 columns " >
                                <button class="tiny" ng-click="dropScholar()" type="button" >Drop Scholar</button>
                            </div>
                        </fieldset>
                        <form ng-submit="setStatus()" class = "row" >
                            <fieldset>
                                <legend>Drop scholar from program/Switch program</legend>
                                <div class="alert-box drop-alert" ></div>
                                <div class = "row collapse" >
                                    <div class="small-4 columns" >
                                        <span class="prefix" >Program</span>
                                    </div>
                                    <div class="small-8 columns">
                                        <select required="" ng-options="p for p in programs" ng-model="selected.prog">
                                            <option value="" >--</option>
                                        </select>
                                    </div>
                                </div>
                                <div class = "row collapse" >
                                    <div class="small-4 columns" >
                                        <span class="prefix" >Reason</span>
                                    </div>
                                    <div class="small-8 columns">
                                        <textarea ng-model="selected.reason" reqiured></textarea>
                                    </div>
                                </div>
                                <button type="submit" class="button center-btn"  >Save</button>
                            </fieldset>
                        </form>
                    </li>
                    <li data-content="essays" >
                        <div class="alert-box myes-alert" ></div>
                        <div ng-repeat="doc1 in essays" >
                            <form ng-submit="uploadEssay(doc1)" >
                                <fieldset>
                                    <legend>
                                        Essay {{ $index + 1}}
                                    </legend>
                                    <div class="row" >
                                        {{ doc1.prompt}}
                                    </div>
                                    <div class = "row collapse" >
                                        <div class="small-4 columns" >
                                            <span class="prefix" >Document</span>
                                        </div>
                                        <div class="small-8 columns">
                                            <input type="file" file-input="doc1.file"  required ng-model="doc1.file" />
                                        </div>
                                    </div>
                                    <div class = "row collapse" >
                                        <div class="small-4 columns" >
                                            <span class="prefix" >Score</span>
                                        </div>
                                        <div >
                                            <select class="small-8 columns" ng-model="doc1.score" required="">
                                                <option value="" >--</option>
                                                <option value="1" >1</option>
                                                <option value="2" >2</option>
                                                <option value="3" >3</option>
                                                <option value="4" >4</option>
                                                <option value="5" >5</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class = "row collapse" >
                                        <div class="small-4 columns" >
                                            <span class="prefix" >Status</span>
                                        </div>
                                        <div >
                                            <select class="small-8 columns" ng-model="doc1.status" required="">
                                                <option value="" >--</option>
                                                <option value="1" >In Progress</option>
                                                <option value="2" >Complete</option>
                                            </select>
                                        </div>
                                    </div>
                                    <button  class="small button radius" type="submit" >Upload Document</button>

                                    <div class="row" >
                                        <div class="small-6 columns" >
                                            <a class="button  tiny" ng-href="{{ doc1.url}}" target="_blank" ><i class="fa fa-download" >Download Document</i></a>
                                        </div>
                                        <div class="small-6 columns" >
                                            <span ng-if="doc1.status == '0' || doc1.status == '1'" >In Progress</span> 
                                            <span ng-if="doc1.status == '2'" >Complete</span> 
                                        </div>

                                    </div>
                                </fieldset>

                            </form>
                        </div>
                    </li>
                    <li data-content="tests" >
                        <div class="row"  >
                            <div class="small-12 columns" >
                                <button class="button" type="button" ng-click="showuploadTest()"  >Upload Test Scores</button>
                            </div>

                        </div>
                        <div class="row"  >
                            <div class="small-12 columns" >
                                <button class="button" type="button" ng-click="showTestScores()" >View Test Scores</button>
                            </div>
                        </div>
                        <div class="row"  >
                            <div class="row" ng-if="controls" >
                                <div class="small-12 columns" >
                                    <fieldset>
                                        <legend>Test Scores</legend>
                                        <fieldset ng-repeat="result in testresults" >
                                            <legend>{{result.test}}</legend>
                                            <div class="row" >
                                                <div class="small-3 columns" >
                                                    <p  >Test Date: {{ result.date}}</p>
                                                </div>
                                                <div class="small-9 columns"  ng-if="result.type != 'sat1' && result.type != 'sat2'" >
                                                    <p> Test Score : {{ result.score}}  </p>
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
                                    </fieldset>
                                </div>
                            </div>
                            <div class="row" ng-if="controls1" >
                                <div class="small-12 columns" >
                                    <form ng-submit="uploadScore()" >
                                        <fieldset>
                                            <legend>Test scores</legend>
                                            <div class="alert-box score-alert" ></div>
                                            <div class="row collapse" >
                                                <div class="small-4 columns" >
                                                    <span class="prefix" >Pick Test</span>
                                                </div>
                                                <div  >
                                                    <select class="small-8 columns" ng-model="stest.test" ng-options="t.title for t in dtests" required>
                                                        <option value="" >--</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="stitched" ></div>
                                            <fieldset ng-if ="stest.test">
                                                <legend>Test Details</legend>
                                                <p>Time: {{stest.test.time}} | Date: {{stest.test.date}}</p>
                                                <p>Type : {{ stest.test.type}}</p>
                                                <p>Location: {{stest.test.location}}</p>
                                            </fieldset>
                                            <fieldset>
                                                <legend>Test Score</legend>
                                                <div ng-if="stest.test.type == 'sat1'" >
                                                    <div class="row collapse" >
                                                        <div class="small-4 columns" >
                                                            <span class="prefix" >Mathematics</span>
                                                        </div>
                                                        <div class="small-8 columns" >
                                                            <input type="number" ng-model="stest.math" required="" max="800" min="1" />
                                                        </div>
                                                    </div>
                                                    <div class="row collapse" >
                                                        <div class="small-4 columns" >
                                                            <span class="prefix" >Critical Reading</span>
                                                        </div>
                                                        <div class="small-8 columns" >
                                                            <input type="number" min="0" ng-model="stest.cr" max="800" required="" />
                                                        </div>
                                                    </div>
                                                    <div class="row collapse" >
                                                        <div class="small-4 columns" >
                                                            <span class="prefix" >Writing</span>
                                                        </div>
                                                        <div class="small-8 columns" >
                                                            <input type="number" ng-model="stest.writing" min="0" max="800" required="" />
                                                        </div>
                                                    </div>
                                                </div>
                                                <div ng-if="stest.test.type == 'sat2'" >
                                                    <div class="row collapse" >
                                                        <div class="small-4 columns" >
                                                            <select ng-model="stest.subj1" ng-options="subj for subj in subjects " ></select>
                                                        </div>
                                                        <div class="small-8 columns" >
                                                            <input type="number" min="0" max="800" ng-model="stest.score1" required="" />
                                                        </div>
                                                    </div>
                                                    <div class="row collapse" >
                                                        <div class="small-4 columns" >
                                                            <select ng-model="stest.subj2" ng-options="subj for subj in subjects " required></select>
                                                        </div>
                                                        <div class="small-8 columns" >
                                                            <input type="number" min="0" max="800" ng-model="stest.score2" required="" />
                                                        </div>
                                                    </div>
                                                    <div class="row collapse" >
                                                        <div class="small-4 columns" >
                                                            <select ng-model="stest.subj3" ng-options="subj for subj in subjects " ></select>
                                                        </div>
                                                        <div class="small-8 columns" >
                                                            <input type="number" min="0" max="800" ng-model="stest.score3" />
                                                        </div>
                                                    </div>
                                                </div>
                                                <div ng-if="stest.test.type == 'act' || stest.test.type == 'toefl'" >
                                                    <div class="row collapse" >
                                                        <div class="small-4 columns" >
                                                            <span class="prefix" >Score</span>
                                                        </div>
                                                        <div class="small-8 columns" >
                                                            <input type="number" min="0" ng-model="stest.score"  required="" />
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
                    </li>
                    <li data-content="docs" >
                        <div class="alert-box mydocs-alert" ></div>
                        <div ng-repeat="doc in sdocuments" >
                            <form ng-submit="uploadDocument(doc)" >
                                <fieldset>
                                    <legend>
                                        <span ng-if="doc.type == '0'" >Teacher Recommendation 1</span>
                                        <span ng-if="doc.type == '1'" >Teacher Recommendation 2</span>
                                        <span ng-if="doc.type == '2'" >Counselor Recommendation </span>
                                        <span ng-if="doc.type == '5'" >Common App Essay</span>
                                    </legend>
                                    <div class = "row collapse" >
                                        <div class="small-4 columns" >
                                            <span class="prefix" >Document</span>
                                        </div>
                                        <div class="small-8 columns">
                                            <input type="file" file-input="doc.file"  required ng-model="doc.file" />
                                        </div>
                                    </div>
                                    <div class = "row collapse" >
                                        <div class="small-4 columns" >
                                            <span class="prefix" >Status</span>
                                        </div>
                                        <div >
                                            <select class="small-8 columns" ng-model="doc.status" required="">
                                                <option value="">--</option>
                                                <option value="1" >In Progress</option>
                                                <option value="2" >Complete</option>
                                            </select>
                                        </div>
                                    </div>
                                    <button  class="small button radius" type="submit" >Upload Document</button>

                                    <div class="row" >
                                        <div class="small-6 columns" >
                                            <a class="button  tiny" ng-href="{{ doc.url}}" target="_blank" ><i class="fa fa-download" >Download Document</i></a>
                                        </div>
                                        <div class="small-6 columns" >
                                            <span ng-if="doc.status == '0' || doc.status == '1'" >In Progress</span> 
                                            <span ng-if="doc.status == '2'" >Complete</span> 
                                        </div>

                                    </div>
                                </fieldset>

                            </form>
                        </div>

                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>

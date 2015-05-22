<%-- 
    Document   : profile
    Created on : Mar 21, 2015, 11:10:55 PM
    Author     : eva
--%>

<div class="row" ng-if="allcolleges" >
    <div class="small-12 columns" >
        <div class="cd-tabs" >
            <nav>
                <ul class="cd-tabs-navigation">
                    <li><a  data-content="dashboard" class="selected" ng-href cody-house-tabs ><i class="fa fa-user icon"></i>Dashboard</a></li>
                    <li><a data-content="mycolleges"ng-href  cody-house-tabs  ><i class="fa fa-pencil icon"></i>My Colleges</a></li>
                    <li><a data-content="collegesearch"ng-href cody-house-tabs ><i class="fa fa-search icon"></i>College Search</a></li>

                </ul> 
            </nav>
            <ul class="cd-tabs-content">
                <li data-content="dashboard" class="selected" >
                    <div class="row" >
                        <div class="small-12 columns" >
                            <fieldset>
                                <legend>My Profile Summary</legend>
                                <fieldset>
                                    <legend  >Mentor</legend>
                                    <div class="capitalize" >
                                        {{ mentor }}
                                    </div>
                                </fieldset>
                                <fieldset>
                                    <legend>Colleges</legend>
                                    <div ng-repeat="c in mycolleges" >
                                        {{ c.name }} - {{ c.type }}
                                    </div>
                                </fieldset>
                            </fieldset>
                            
                        </div>
                    </div>
                </li>
                <li data-content="mycolleges" >
                <college-profiles model="profiles" >
                    <div class="alert-box mycollege-alert " ></div>
                    <college-profile ng-repeat="field in mycolleges" >
                        <fieldset>
                            <legend>{{ field.name}}</legend>

                            <div class="row" >
                                <div class="small-7 columns" >
                                    <div class="row collapse" >
                                        <div class="small-4 columns" >
                                            <span class="prefix" >Application Type</span>
                                        </div>
                                        <div >
                                            <select class="small-8 columns" ng-model="field.type" ng-disabled="field.edit == '1'" required >
                                                <option value="" >--</option>
                                                <option value="ed" >Early Decision</option>
                                                <option value="rd" >Regular Decision</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="row collapse" >
                                        <div class="small-4 columns" >
                                            <span class="prefix" >College Decision</span>
                                        </div>
                                        <div  >
                                            <select class="small-8 columns" ng-model="field.decision" ng-disabled="field.edit == '1'" required>
                                                <option value="" >--</option>
                                                <option value="0" >Yet to submit application</option>
                                                <option value="3" >Complete Awaiting Decision</option>
                                                <option value="1" >Accepted</option>
                                                <option value="2" >Not Accepted</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <button class="button radius small" type="submit" ng-if="!field.edit || field.edit == '0'" >Upload</button>
                            <button class="button radius small" type="button" ng-if="field.edit == '1'" ng-click="edit(field)">Edit</button>
                            <button class="button radius small" type="button" ng-click="drop(field)" >Drop College</button>

                        </fieldset>
                    </college-profile> 
                </college-profiles>
                </li>
                <li data-content="collegesearch" >
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
                    <div class="alert-box scholarcollege-alert " ></div>
                    <div class="accordion1 small-9 scollege" id="accordion1" chris-accordion >
                        <dl>
                            <div dir-paginate="college in colleges |filter:fil1 | itemsPerPage: pageSize" current-page="currentPage" >
                                <dt><a class="accordionTitle" ng-href>{{ college.name}}</a></dt>
                                <dd class="accordionItem accordionItemCollapsed">
                                    <div class="row" >
                                        <div class="small-6 columns" >
                                            Early Decision Deadline : {{ college.ed}}
                                        </div>
                                        <div class="small-6 columns" >
                                            Regular Decision Deadline : {{ college.reg}}
                                        </div>
                                    </div>
                                    <div class="row" >
                                        <div class="small-6 columns" >
                                            <p ng-if="college.commonapp == '1'" >Common App <b>Required</b></p>
                                            <p ng-if="college.commonapp == '0'" >Common App <b>Not</b> Required</p>
                                        </div>
                                        <div class="small-6 columns" >
                                            <p ng-if="college.mcf == '1'" >College under MCF Scholarships</p>
                                            <p ng-if="college.mcf == '0'" >College NOT under MCF Scholarships</p>
                                        </div>
                                    </div>
                                    <div class="row" >
                                        <div class="small-6 columns" ng-if="college.essays == '1'">
                                            <button  type="button" class="radius button small" ng-click="getEssays(college)" >College Essays</button>
                                        </div>
                                        <div class="small-6 columns" >
                                            <button ng-if="college.allowed == '1'" type="button" ng-click="pickCollege(college)" class="radius button small" >Add to My Colleges</button>
                                        </div>

                                    </div>
                                </dd>
                            </div>
                            <dir-pagination-controls  ></dir-pagination-controls>        
                        </dl>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</div>
<div class="row" ng-if="!allcolleges" >
    <div class="main-title" >{{ selectedcollege.name}} Essays</div>
    <div class="small-12 columns" >
        <ul   ng-repeat="e in essays">
            <li class="row" >
                <div class="small-8 columns" >
                    {{ $index + 1}}   {{ e.prompt}}
                </div>
                <div class="small-4 columns" >
                    <p ng-if="e.required == '1'" >Required Essay</p>
                    <p ng-if="e.required == '0'" >Optional Essay</p>
                </div>
            </li>
        </ul>
        <div class="row" >
            <div class="small-6 columns" >
                <button ng-if="selectedcollege.allowed == '1'" class="small radius button" type="button">Add to My Colleges</button>
            </div>
            <div class="small-6 columns" >
                <button class="radius button small" type="button" ng-click="goBack()">Back</button>
            </div>


        </div>
    </div>
</div>
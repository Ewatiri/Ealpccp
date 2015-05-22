<%-- 
    Document   : pool
    Created on : Mar 1, 2015, 5:42:25 PM
    Author     : eva
--%>

<div class="accordion1 mid acc" ng-if="hi" >
    <div class="main-title" >Applications</div>
    <dl>
        <dt dir-paginate="a in applications | itemsPerPage: pageSize" current-page="currentPage"><a ng-href ng-click="getDetails(a.sid)" class="accordionTitle">{{ a.applicant}}</a></dt>
    </dl>
    <dir-pagination-controls  ></dir-pagination-controls>        
</div>

<div class="selec" ng-show="!hi" >
    
    <div class="cd-tabs">
        <nav>
            <ul class="cd-tabs-navigation">
                <li><a  data-content="family" class="selected" ng-href><i class="fa fa-group"></i>Family Details</a></li>
                <li><a data-content="academic"ng-href><i class="fa fa-graduation-cap"></i>Academic Details</a></li>
                <li><a data-content="activities" ng-href ><i class="fa fa-futbol-o"></i>Activities</a></li>
                <li><a  data-content="essays" ng-href><i class="fa fa-pencil"></i>Essays</a></li>
            </ul> 

        </nav>
        <ul class="cd-tabs-content">
            <li data-content="family" class="selected">
                <fieldset>
                    <legend>Family Info</legend>

                    <div class="row" >
                        <div class="small-6 columns" >
                            <div class="row collapse" >
                                <div class="small-6 columns" >
                                    <span class='prefix' >Orphan</span>
                                </div>
                                <div class="small-6 columns" >
                                    <input type="text" ng-model="family.orphan1" readonly="" />
                                </div>
                            </div>
                        </div>
                        <div class="small-6 columns" >
                            <div class="row collapse" >
                                <div class="small-6 columns" >
                                    <span class='prefix' >Family Income</span>
                                </div>
                                <div class="small-6 columns" >
                                    <input type="text" ng-model="family.income" readonly="" />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row" >
                        <div class="small-6 columns"ng-if="family.orphan == '1' || family.orphan == '2'" >
                            <div class="row collapse" >
                                <div class="small-6 columns" >
                                    <span class='prefix' >Parent 1 Occupation</span>
                                </div>
                                <div class="small-6 columns" >
                                    <input type="text" ng-model="family.parent1" readonly="" />
                                </div>
                            </div>
                        </div>
                        <div class="small-6 columns" ng-if="family.orphan == '2'">
                            <div class="row collapse" >
                                <div class="small-6 columns" >
                                    <span class='prefix' >Parent 2 Occupation</span>
                                </div>
                                <div class="small-6 columns" >
                                    <input type="text" ng-model="family.parent2" readonly="" />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row" >
                        <div class="small-6 columns" >
                            <div class="row collapse" >
                                <div class="small-4 columns" >
                                    <span class="prefix" >Number of siblings</span>
                                </div>
                                <div class="small-8 columns" >
                                    <input type="text" readonly="" ng-model="family.siblings" />
                                </div>
                            </div>
                        </div>
                        <div class="small-6 columns" >
                            <div class="row collapse" >
                                <div class="small-4 columns" >
                                    <span class="prefix" >Sibling(s) occupation</span>
                                </div>
                                <div class="small-8 columns" >
                                    <textarea  readonly="" ng-model="family.siblingsinfo" ></textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row" >
                        <div class="small-6 columns" >
                            <div class="row collapse" >
                                <div class="small-6 columns" >
                                    <span class='prefix' >Current Location</span>
                                </div>
                                <div class="small-6 columns" >
                                    <input type="text" readonly="" ng-model="family.location" />
                                </div>
                            </div>
                        </div>
                        <div class="small-6 columns" >
                            <div class="row collapse" >
                                <div class="small-6 columns" >
                                    <span class='prefix' >Stay at home?</span>
                                </div>
                                <div class="small-6 columns" >
                                    <input type="text" readonly="" ng-model="family.livehome" />
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="row" ng-if="family.livehome == 'no'" >
                        <div class="small-8 columns" >
                            <div class="row collapse" >
                                <div class="small-4 columns" >
                                    <span class='prefix' >Reason</span>
                                </div>
                                <div class="small-8 columns" >
                                    <textarea readonly="" ng-model="family.hreason" ></textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row" >
                        <div class="small-6 columns" >
                            <div class="row collapse" >
                                <div class="small-6 columns" >
                                    <span class='prefix' >Who paid school fees?</span>
                                </div>
                                <div class="small-6 columns" >
                                    <input type="text" readonly="" ng-model="family.payfees"/>
                                </div>
                            </div>
                        </div>
                        <div class="small-6 columns" ng-if="family.payfees == 'parents'" >
                            <div class="row collapse" >
                                <div class="small-6 columns" >
                                    <span class='prefix' >Ever been sent home?</span>
                                </div>
                                <div class="small-6 columns" >
                                    <input type="text" readonly=""ng-model="family.beenhome" />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row" ng-if="family.payfees == 'sponsor'" >
                        <div class="small-6 columns" >
                            <div class="row collapse" >
                                <div class="small-6 columns" >
                                    <span class='prefix' >Sponsor's Name</span>
                                </div>
                                <div class="small-6 columns" >
                                    <input type="text" readonly="" ng-model="family.sponsor" />
                                </div>
                            </div>
                        </div>
                        <div class="small-6 columns" >
                            <div class="row collapse" >
                                <div class="small-6 columns" >
                                    <span class='prefix' >Scholarship description</span>
                                </div>
                                <div class="small-6 columns" >
                                    <textarea readonly="" ng-model="family.sponsordesc" ></textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                    <fieldset  >
                        <legend>Score Section</legend>
                        <div class="row" >
                            <div class="small-12 columns" >
                                <div class="row collapse" >
                                    <div class="small-5 columns" >
                                        <span class='prefix' >Does the scholar qualify for MCF scholarships?</span>
                                    </div>
                                    <div class="small-7 columns" >
                                        <select ng-model="review.mcf">
                                            <option value="1">Yes</option>
                                            <option value="2">No</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row" >
                            <div class="small-12 columns" >
                                <div class="row collapse" >
                                    <div class="small-5 columns" >
                                        <span class='prefix' >Comment</span>
                                    </div>
                                    <div class="small-7 columns" >
                                        <textarea rows="4" ng-model="review.comment"></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </fieldset>
                </fieldset>
            </li>
            <li data-content="academic" >
                <fieldset>
                    <legend>Academic Info</legend>
                    <div class="row" >
                        <div class="small-9 columns" >
                            School Type : {{ school.type}}
                        </div>
                        <div class="small-3 columns" >
                            Mean Score: {{ school.score}}
                        </div>
                    </div>
                    <div class="row" >
                        <div class="small-4 columns" >
                            <div class="row collapse" >
                                <div class="small-6 columns" >
                                    <span class='prefix' >English</span>
                                </div>
                                <div class="small-6 columns" >
                                    <input type="text" readonly="" ng-model="transcript.eng"  />
                                </div>
                            </div>
                        </div>
                        <div class="small-4 columns" ng-if="transcript.bio" >
                            <div class="row collapse" >
                                <div class="small-6 columns" >
                                    <span class='prefix' >Biology</span>
                                </div>
                                <div class="small-6 columns" >
                                    <input type="text" readonly="" ng-model="transcript.bio"  />
                                </div>
                            </div>
                        </div>
                        <div class="small-4 columns" ng-if="transcript.geog">
                            <div class="row collapse" >
                                <div class="small-6 columns" >
                                    <span class='prefix' >Geography</span>
                                </div>
                                <div class="small-6 columns" >
                                    <input type="text" readonly="" ng-model="transcript.geog"  />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row" >
                        <div class="small-4 columns" >
                            <div class="row collapse" >
                                <div class="small-6 columns" >
                                    <span class='prefix' >Kiswahili</span>
                                </div>
                                <div class="small-6 columns" >
                                    <input type="text" readonly="" ng-model="transcript.swa" />
                                </div>
                            </div>
                        </div>
                        <div class="small-4 columns" ng-if="transcript.chem" >
                            <div class="row collapse" >
                                <div class="small-6 columns" >
                                    <span class='prefix' >Chemistry</span>
                                </div>
                                <div class="small-6 columns" >
                                    <input type="text" readonly=""ng-model="transcript.chem"  />
                                </div>
                            </div>
                        </div>
                        <div class="small-4 columns" ng-if="transcript.hist">
                            <div class="row collapse" >
                                <div class="small-6 columns" >
                                    <span class='prefix' >History</span>
                                </div>
                                <div class="small-6 columns" >
                                    <input type="text" ng-model="transcript.hist"  readonly="" />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row" >
                        <div class="small-4 columns" >
                            <div class="row collapse" >
                                <div class="small-6 columns" >
                                    <span class='prefix' >Mathematics</span>
                                </div>
                                <div class="small-6 columns" >
                                    <input type="text" ng-model="transcript.math"  readonly="" />
                                </div>
                            </div>
                        </div>
                        <div class="small-4 columns" ng-if="transcript.phy" >
                            <div class="row collapse" >
                                <div class="small-6 columns" >
                                    <span class='prefix' >Physics</span>
                                </div>
                                <div class="small-6 columns" >
                                    <input type="text" ng-model="transcript.phy"  readonly="" />
                                </div>
                            </div>
                        </div>
                        <div class="small-4 columns" ng-if="transcript.rs">
                            <div class="row collapse" >
                                <div class="small-6 columns" >
                                    <span class='prefix' >Religious Studies</span>
                                </div>
                                <div class="small-6 columns" >
                                    <input type="text" ng-model="transcript.rs"  readonly="" />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row" >
                        <div class="small-4 columns" >
                            <div class="row collapse" >
                                <div class="small-6 columns" >
                                    <span class='prefix' >{{ transcript.elec}}</span>
                                </div>
                                <div class="small-6 columns" >
                                    <input type="text" ng-model="transcript.egrade"  readonly="" />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row" >
                        <div class="small-6 columns" >Position : {{ transcript.pos}} </div> 
                        <div class="small-6 columns" >Out of : {{ transcript.pop}} </div> 
                    </div>
                    <fieldset  >
                        <legend>Score Section</legend>
                        <div class="row" >
                            <div class="small-12 columns" >
                                <div class="row collapse" >
                                    <div class="small-5 columns" >
                                        <span class='prefix' >Section score</span>
                                    </div>
                                    <div class="small-7 columns" >
                                        <select ng-model="review.s1">
                                            <option value="1">1</option>
                                            <option value="2">2</option>
                                            <option value="3">3</option>
                                            <option value="4">4</option>
                                            <option value="5">5</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row" >
                            <div class="small-12 columns" >
                                <div class="row collapse" >
                                    <div class="small-5 columns" >
                                        <span class='prefix' >Comment</span>
                                    </div>
                                    <div class="small-7 columns" >
                                        <textarea rows="4" ng-model="review.comment"></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </fieldset>
                </fieldset>
            </li>
            <li data-content="activities">
                <fieldset>
                    <fieldset >
                        <legend>Honors</legend>
                        <div  ng-repeat="honor in honors" >
                            <div class="center-num" >{{ $index + 1}}</div>
                            <div class="row" >
                                <div class="small-12 columns" >
                                    <div class="row collapse" >
                                        <div class="small-4 columns" >
                                            <span class='prefix' >Title</span>
                                        </div>
                                        <div class="small-8 columns" >
                                            <textarea ng-model="honor.desc" readonly=""></textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row" >
                                <div class="small-6 columns" >
                                    <div class="row collapse" >
                                        <div class="small-6 columns" >
                                            <span class='prefix' >Class</span>
                                        </div>
                                        <div class="small-6 columns" >
                                            <input type="text" ng-model="honor.class"   readonly="" />
                                        </div>
                                    </div>
                                </div>

                                <div class="small-6 columns" >
                                    <div class="row collapse" >
                                        <div class="small-6 columns" >
                                            <span class='prefix' >Level of Recognition</span>
                                        </div>
                                        <div class="small-6 columns" >
                                            <input type="text" ng-model="honor.level"   readonly="" />
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </fieldset>
                    <fieldset>
                        <legend>Extracurricular Activities</legend>
                        <div class="row" ng-repeat="v in vol"  >
                            <div class="center-num" >{{ $index + 1}}</div>
                            <div class="small-4 columns" >
                                <div class="row collapse" >
                                    <div class="small-6 columns" >
                                        <span class='prefix' >Class</span>
                                    </div>
                                    <div class="small-6 columns" >
                                        <input type="text" ng-model="v.class" readonly="" />
                                    </div>
                                </div>
                            </div>
                            <div class="small-8 columns" >
                                <div class="row collapse" >
                                    <div class="small-4 columns" >
                                        <span class='prefix' >Description</span>
                                    </div>
                                    <div class="small-8 columns" >
                                        <textarea ng-model="v.desc" rows="4" readonly=""></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </fieldset>
                    <fieldset>
                        <legend>Misc</legend>
                        <div class="row"> 
                            <div class="small-12 columns" >
                                <div class="row collapse" >
                                    <div class="small-4 columns" >
                                        <span class='prefix' >Holiday activities</span>
                                    </div>
                                    <div class="small-8 columns" >
                                        <textarea ng-model="holiday.hol" rows="4" readonly=""></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row"> 
                            <div class="small-12 columns" >
                                <div class="row collapse" >
                                    <div class="small-4 columns" >
                                        <span class='prefix' >Interest in studying abroad</span>
                                    </div>
                                    <div class="small-8 columns" >
                                        <textarea ng-model="holiday.interest" rows="4" readonly=""></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </fieldset>
                    <fieldset  >
                        <legend>Score Section</legend>
                        <div class="row" >
                            <div class="small-12 columns" >
                                <div class="row collapse" >
                                    <div class="small-5 columns" >
                                        <span class='prefix' >Section score</span>
                                    </div>
                                    <div class="small-7 columns" >
                                        <select required ng-model="review.s2">
                                            <option value="1">1</option>
                                            <option value="2">2</option>
                                            <option value="3">3</option>
                                            <option value="4">4</option>
                                            <option value="5">5</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row" >
                            <div class="small-12 columns" >
                                <div class="row collapse" >
                                    <div class="small-5 columns" >
                                        <span class='prefix' >Comment</span>
                                    </div>
                                    <div class="small-7 columns" >
                                        <textarea rows="4" ng-model="review.comment" required></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </fieldset>
                </fieldset>
            </li>
            <li data-content="essays">
                <fieldset>
                    <fieldset>
                        <legend>Essays</legend>
                        <div class="row" >
                            <div class="old-essay" ng-repeat="e in essays" >
                                <div class="row" >
                                    <div class="small-5 columns" >
                                        <a ng-href="{{e.url}}" target="_blank" >Essay {{ e.essay }}</a>
                                    </div> 
                                </div>
                            </div>
                           
                        </div>
                    </fieldset>
                    <fieldset >
                        <legend>Score Section</legend>
                        <div class="alert-box review-alert" ></div>
                        <div class="row" >
                            <div class="small-12 columns" >
                                <div class="row collapse" >
                                    <div class="small-5 columns" >
                                        <span class='prefix' >Section Score</span>
                                    </div>
                                    <div class="small-7 columns" >
                                        <select required ng-model="review.s3" >
                                            <option value="1">1</option>
                                            <option value="2">2</option>
                                            <option value="3">3</option>
                                            <option value="4">4</option>
                                            <option value="5">5</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row" >
                            <div class="small-12 columns" >
                                <div class="row collapse" >
                                    <div class="small-5 columns" >
                                        <span class='prefix' >Comment</span>
                                    </div>
                                    <div class="small-7 columns" >
                                        <textarea rows="4" ng-model="review.comment"></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row" >
                            <button class="large-12 button" ng-click="saveReview()" >Submit Review</button>
                        </div>
                    </fieldset>
                </fieldset>
            </li>
        </ul>
    </div>
</div>

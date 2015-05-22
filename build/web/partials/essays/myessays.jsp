<%-- 
    Document   : myessays
    Created on : Mar 25, 2015, 9:22:57 PM
    Author     : eva
--%>
<div class="accordion1 mid acc" ng-if="showcolleges" >
    <div class="main-title" >My Colleges</div>
    <dl>
        <dt dir-paginate="coll in collegessays | itemsPerPage: pageSize" current-page="currentPage"><a ng-href ng-click="getmyEssays(coll)" class="accordionTitle">{{ coll.name}}</a></dt>
    </dl>
    <dir-pagination-controls  ></dir-pagination-controls>        
</div>
<div class="row" ng-if="!showcolleges" >
    <div class="small-12 columns" >
        <div class="main-title" >{{ selectedcollege.name}}</div>
        <div>
            <button class="tiny secondary" ng-click="goBack()"><i class ="fa fa-hand-o-left icon">Back</i></button>
        </div>
        <div class="cd-tabs" >
            <nav>
                <ul class="cd-tabs-navigation">
                    <li><a  data-content="myessays" class="selected" ng-href cody-house-tabs ><i class="fa fa-user icon"></i>Upload Essays</a></li>
                    <li><a data-content="essayhist"ng-href cody-house-tabs ><i class="fa fa-pencil icon"></i>Essay Status</a></li>

                </ul> 
            </nav>
            <ul class="cd-tabs-content">
                <li data-content="myessays" class="selected">
                    <div class="alert-box myessay-alert" ></div>
                    <div ng-repeat="ey in myessays" ng-if="selectedcollege.name != 'Common App'" >
                        <form ng-submit="uploadEssay(ey)">
                            <fieldset>
                                <legend>Essay {{  $index + 1}}</legend>

                                <div row >
                                    {{ ey.prompt}}
                                </div>
                                <div class="row" >
                                    <p ng-if="ey.required == '1'" >Required Essay</p>
                                    <p ng-if="ey.required == '0'" >Optional Essay</p>
                                </div>
                                <div class = "row collapse" >
                                    <div class="small-4 columns" >
                                        <span class="prefix" >Document</span>
                                    </div>
                                    <div class="small-8 columns">
                                        <input type="file" file-input="e.file"  required ng-model="e.file" />
                                    </div>
                                </div>
                                <button  class="small button radius" type="submit" >Upload Essay</button>

                            </fieldset>

                        </form>
                    </div>
                    <div ng-if="selectedcollege.name == 'Common App'" >
                        <div ng-repeat="e in myessays" >
                            <p>Prompt {{ index + 1}}. {{ e.prompt}} </p>
                        </div>
                        <form ng-submit="uploadEssay(e)">
                            <div class = "row collapse" >
                                <div class="small-4 columns" >
                                    <span class="prefix" >Document</span>
                                </div>
                                <div class="small-8 columns">
                                    <input type="file" file-input="e.file"  required ng-model="ey.file" />
                                </div>
                            </div>
                            <button  class="small button radius" type="submit" >Upload Essay</button>
                        </form>
                    </div>
                </li>
                <li data-content="essayhist" >
                    <div class="alert-box essayhist-alert" ></div>
                    <div ng-repeat="y in feedback" >
                        <fieldset>
                            <div class="row" >
                                <div class="small-6 columns" >
                                    {{ $index + 1}}. {{ y.prompt}} 
                                </div>
                                <div class="small-3 columns" >
                                    <p ng-if="y.status == '2'" >Complete</p>
                                    <p ng-if="y.status == '1'" >Reviewed</p>
                                    <p ng-if="y.status == '0'" >Not yet reviewed</p>
                                </div>
                                <div class ="small-3 columns" ng-if="y.score" >
                                    Score -> {{ y.score}}/5
                                </div>
                            </div>
                            <div  class="row">
                                <div class="small-5 columns" >
                                    <a class="button tiny secondary" ng-href="{{ y.loc}}" target="_blank" ><i class ="fa fa-download"></i>Download Document</a>
                                </div>

                            </div>
                        </fieldset>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</div>
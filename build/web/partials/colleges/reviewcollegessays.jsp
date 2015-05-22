<%-- 
    Document   : reviewcollegessays
    Created on : Mar 11, 2015, 10:44:50 AM
    Author     : eva
--%>
<div class="row" >
    <div class="row" ng-if="showcoll">
        <div class="main-title" >Colleges</div>
        <div class="small-12 columns" >
            <div class="accordion1 mid acc" >
                <dl>
                    <dt dir-paginate="college in approvalEssays |itemsPerPage: pageSize" current-page="currentPage"><a ng-href ng-click="selectCollege($index)" class="accordionTitle">{{ college.name}}</a></dt>
                </dl>
                <dir-pagination-controls  ></dir-pagination-controls>        
            </div>
        </div>
    </div>
    <!--College essays-->
    <div class="row" ng-if="!showcoll" >
        <div class="main-title" >{{ coll.name}}</div>
        <div class="small-12 columns" >
            <div class="alert-box essay1-alert" ></div>
            <div> <button type="button"  class="button radius secondary" ng-click="goBack()" ><i class="fa fa-hand-o-left icon" ></i>Back to College Profiles</button> </div>
            <div ng-repeat="essay in coll.essays" >
                <fieldset >
                    <p>Essay Prompt: {{ essay.prompt}}</p>
                    <p  ng-if="essay.required == '1'" > <em>Required</em> </p>
                    <p ng-if="essay.required == '0'" > <em>Not Required</em> </p>
                    <p class="capitalize " >Last Edited by {{ essay.addedby}}  </p>
                    <div  >
                        <button class="button tiny radius" ng-if="essay.edit == '0'" ng-click="approve($index)" >Approve</button>
                        <button class="button tiny radius" ng-if="essay.edit == '0'" ng-click="showMessage($index)">Reject</button>
                    </div>
                    <div class="message" ng-if="essay.edit != '0'" >
                        <form ng-submit="reject($index)" >
                            <div class="row collapse" >
                                <div class="small-4 columns" >
                                    <span class="prefix" >Corrections</span>
                                </div>
                                <div class="small-8 columns" >
                                    <textarea rows="6" ng-model="essay.message"  required></textarea>
                                </div>
                            </div>
                            <button class="button tiny radius " >Send</button>
                        </form>

                    </div>
                </fieldset>
            </div>
        </div>
    </div>
</div>

<%-- 
    Document   : delmentor
    Created on : Mar 11, 2015, 9:13:36 PM
    Author     : eva
--%>
<div class="row" >
    <div class="small-12 columns" >
        <div class="main-title" >Scholars</div>
        <div class="alert-box mentor-alert " ></div>
        <div class="accordion1" id="accordion1" chris-accordion  >
            <dl>
                <div dir-paginate="mentor in mentors | itemsPerPage: pageSize" current-page="currentPage" pagination-id="mentors" >
                    <dt><a class="accordionTitle" ng-href>{{ mentor.name}}</a></dt>
                    <dd class="accordionItem accordionItemCollapsed">
                        <div class="row" >
                            <div class="small-12 columns" >
                                <button  ng-if="mentor.status == '-1'" type="button" class="tiny button radius" ng-click="activate($index)" >Activate</button>
                                <button ng-if="mentor.status == '1'"type="button" class="tiny button radius" ng-click="deactivate($index)" >Deactivate </button>
                                <button ng-click="switchtoStandard($index)" ng-if="mentor.class == '1' && mentor.status =='1'" type = "button" class="tiny button radius secondary" >Switch to Standard Account</button>
                                <button ng-click="switchtoAdmin($index)" ng-if="mentor.class == '2' && mentor.status =='1'" type = "button" class="tiny button radius secondary" >Switch to Admin Account</button>
                            </div>
                        </div>

                    </dd>
                </div>

            </dl>
        </div>  
        <dir-pagination-controls pagination-id="mentors" ></dir-pagination-controls> 
    </div>
</div>
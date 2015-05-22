<%-- 
    Document   : approval
    Created on : Feb 22, 2015, 6:58:53 PM
    Author     : eva
--%>
<!--<script src="../assets/js/libs/accordion.js" type="text/javascript"></script>-->
<div class="row" >
    <div class ="main-title">Pending Account Approvals</div>
    
    <div class="small-12 columns" >
        <div class="ascholar-alert alert-box" ></div>
        <div class="accordion1" id="accordion1" chris-accordion  >

            <dl>
                <div dir-paginate="scholar in approvalScholars | itemsPerPage: pageSize" current-page="currentPage" >

                    <dt><a class="accordionTitle" ng-href>{{ scholar.fullname}} </a></dt>
                    <dd class="accordionItem accordionItemCollapsed">
                        <div class="row" >
                            <div class="small-4 columns capitalize" ng-if ="scholar.hs">
                                High School: {{ scholar.hs}}
                            </div>
                            <div class="small-4 columns capitalize" ng-if ="scholar.branch" >
                                Branch : {{ scholar.branch}}
                            </div>
                            <div class="small-4 columns capitalize" >
                                {{ scholar.type}}
                            </div>
                        </div>
                        <div class="row" ng-if="scholar.staffname">
                            <div class="small-4 columns capitalize" >
                                Staff Name: {{ scholar.staffname}}
                            </div>
                            <div class="small-4 columns capitalize" >
                                Staff PF No:{{ scholar.pfno}}
                            </div>
                            <div class="small-4 columns capitalize" >
                                Job Title : {{ scholar.title}}
                            </div>
                        </div>
                        <div class="row" ng-if="scholar.staffname" >
                            <div class="small-4 columns" >
                                Staff Mobile : {{ scholar.mobile}}
                            </div>
                            <div class="small-4 columns" >
                                Staff Email : {{ scholar.semail}}
                            </div>
                            <div class="small-4 columns" >
                                Staff Avaya : {{ scholar.avaya}}
                            </div>
                        </div>
                        <div class="announ-controls" > 
                            <button type="button" class="button tiny" ng-click="approve($index)" >Approve</button>
                            <button type="button" class="button tiny" ng-click="reject($index)" >Reject</button>
                        </div>
                    </dd>
                </div>

            </dl>

            <dir-pagination-controls  ></dir-pagination-controls>
        </div>

    </div>

</div>

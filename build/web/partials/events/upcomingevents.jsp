<%-- 
    Document   : upcomingevents
    Created on : Mar 12, 2015, 7:20:22 PM
    Author     : eva
--%>
<div class="row" >
    <div class="small-12 columns" >
        <div class="cd-tabs" >
            <nav>
                <ul class="cd-tabs-navigation">
                    <li><a  data-content="myevents" class="selected" ng-href cody-house-tabs ><i class="fa fa-user icon "></i>Attending</a></li>
                    <li><a data-content="otherevents"ng-href cody-house-tabs ><i class="fa fa-pencil icon"></i>Not Attending</a></li>

                </ul> 
            </nav>
            <ul class="cd-tabs-content">
                <li data-content="myevents" class="selected">
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
                    <div class="alert-box attend-alert " ></div>
                    <div class="stitched" dir-paginate="event in events1 |filter:fil1 |itemsPerPage: pageSize" current-page="currentPage" pagination-id="ev1" >
                        <div class="row" >
                            <div class="small-12 columns" >
                                <p>Event: {{ event.title}}  </p>
                            </div>
                        </div>
                        <div class="row" >
                            <div class="small-12 columns" >
                                <p>Description: {{ event.description}}  </p>
                            </div>
                        </div>
                        <div class="row" >
                            <div class="small-6 columns" >
                                <p> Time: {{ event.etime}}  </p>
                            </div>
                            <div class="small-6 columns" >
                                <p> Date: {{ event.edate}}  </p>
                            </div>
                        </div>
                        <div class="row" >
                            <div class="small-12 columns" >
                                <p> Venue: {{ event.venue}}  </p>
                            </div>
                        </div>
                        <div class="row" >
                            <button class="small radius center-btn " ng-click="withdraw(event)" type="button" >Withdraw attendance</button>
                        </div>
                    </div>
                <dir-pagination-controls pagination-id="ev1"  ></dir-pagination-controls>       
                </li>
                <li data-content="otherevents" >
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
                    <div class="alert-box attend-alert " ></div>
                    <div class="stitched" dir-paginate="event in events2 |filter:fil |itemsPerPage: pageSize" current-page="currentPage1" pagination-id="ev2" >
                        <div class="row" >
                            <div class="small-12 columns" >
                                <p>Event: {{ event.title}}  </p>
                            </div>
                        </div>
                        <div class="row" >
                            <div class="small-12 columns" >
                                <p>Description: {{ event.description}}  </p>
                            </div>
                        </div>
                        <div class="row" >
                            <div class="small-6 columns" >
                                <p> Time: {{ event.etime}}  </p>
                            </div>
                            <div class="small-6 columns" >
                                <p> Date: {{ event.edate}}  </p>
                            </div>
                        </div>
                        <div class="row" >
                            <div class="small-12 columns" >
                                <p> Venue: {{ event.venue}}  </p>
                            </div>
                        </div>
                        <div class="row" >
                            <button class="small radius center-btn " ng-click="attend(event)" type="button" >Attend</button>
                        </div>
                    </div>           

                    <div class="text-center" >
                        <dir-pagination-controls pagination-id="ev2"  ></dir-pagination-controls>       
                    </div>
                
                </li>
            </ul>
        </div>
    </div>
</div>
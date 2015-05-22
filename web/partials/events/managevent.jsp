<%-- 
    Document   : managevent
    Created on : Mar 4, 2015, 7:31:45 PM
    Author     : eva
--%>
<div class="row" >
    <div class="main-title">Events</div>
    <div class="alert-box ev-alert" ></div>
    <div class="small-12 columns" >
        <div class="stitched" dir-paginate="ev in events | itemsPerPage: pageSize track by ev.id" current-page="currentPage"> 
            <div class="row" >
                <p ng-if ='ev.edit == 0'> {{ ev.title}}</p>
                <div class="row collapse" ng-if ='ev.edit == 1' >
                    <div class="small-4 columns" >
                        <span class="prefix" >New Title</span>
                    </div>
                    <div class="small-8 columns" >
                        <input type="text"  ng-model="ev.title" required="" />
                    </div>
                </div>
            </div>
            <div class="row" >
                <p ng-if ='ev.edit == 0'> {{ ev.desc}}</p>
                <div class="row collapse" ng-if ='ev.edit == 1' >
                    <div class="small-4 columns" >
                        <span class="prefix" >New Description</span>
                    </div>
                    <div class="small-8 columns" >
                        <textarea rows="4" ng-model="ev.desc" required="" ></textarea>
                    </div>
                </div>
            </div>
            <div class="row" >
                <div class="small-6 columns" >
                    <p ng-if ='ev.edit == 0'> {{ ev.date}}</p>
                    <div class="row collapse" ng-if ='ev.edit == 1' >
                        <div class="small-4 columns" >
                            <span class="prefix" >New Date</span>
                        </div>
                        <div class="small-8 columns" >
                            <quick-datepicker ng-model="ev.etime"   placeholder = "Select Date and Time"></quick-datepicker>
                            <!--<input type="text"  ng-model="ev.date" required="" />--> 
                        </div>
                    </div>
                </div>
                <div class="small-6 columns" >
                    <p ng-if ='ev.edit == 0'> {{ ev.time}}</p>
                    <div class="row collapse" ng-if ='ev.edit == 1' >
                        <div class="small-4 columns" >
                            <span class="prefix" >New Time</span>
                        </div>
                        <div class="small-8 columns" >
                            <input type="text"  ng-model="ev.time" readonly=""  required="" />
                        </div>
                    </div>
                </div>


            </div>
            <div class="row" >
                <p ng-if ='ev.edit == 0'> {{ ev.venue}}</p>
                <div class="row collapse" ng-if ='ev.edit == 1' >
                    <div class="small-4 columns" >
                        <span class="prefix" >New Venue</span>
                    </div>
                    <div class="small-8 columns" >
                        <input type="text"  ng-model="ev.venue" required="" />
                    </div>
                </div>
            </div>
            <div class="row" >
                Last Edited by : {{ ev.last}}
            </div>
            <div class="announ-controls" >
                <ul class="button-group" >
                    <li ng-if ='ev.edit == 0'><button type="button" class="small button" ng-click="edit($index)" >Edit</button></li>
                    <li ng-if='ev.edit == 1' ><button type="button" class="small button" ng-click="save($index)" >Save</button></li>
                    <li ng-if ='ev.edit == 0'><button type="button" class="small button" ng-click = "delete($index)">Delete</button></li>
                </ul>

            </div>
        </div>    
    </div>
    <dir-pagination-controls  ></dir-pagination-controls>        
</div>

<%-- 
    Document   : manageannoun
    Created on : Feb 21, 2015, 9:41:23 AM
    Author     : eva
--%>

<div class="row" >
    <div class="main-title">Announcements</div>
    <div class="alert-box announ1-alert" ></div>
    <div class="small-12 columns" >
        <div class="announ stitched" dir-paginate="ann in announcements | itemsPerPage: pageSize" current-page="currentPage"> 
            <div class="" >
                <p ng-if ='ann.edit == 0'>{{ ann.title}}</p>
                <div class="row collapse" ng-if ='ann.edit == 1' >
                    <div class="small-4 columns" >
                        <span class="prefix" >New Title</span>
                    </div>
                    <div class="small-8 columns" >
                        <textarea  ng-model="ann.title" ></textarea>
                    </div>
                </div>
            </div>
            <div class="" > 
                <p ng-if ='ann.edit == 0' > {{ ann.description}}</p>
                <div class="row collapse" ng-if ='ann.edit == 1' >
                    <div class="small-4 columns" >
                        <span class="prefix" >New Description</span>
                    </div>
                    <div class="small-8 columns" >
                        <textarea  ng-model="ann.description" ></textarea>
                    </div>
                </div>

            </div>
            <div class="announ-signature" >
                <p class="capitalize" >{{ ann.by}}</p>
                <p>{{ ann.resdate}}</p>
            </div>
            <div class="announ-controls" >
                <ul class="button-group" >
                    <li ng-if ='ann.edit == 0'><button type="button" class="small button" ng-click="edit($index)" >Edit</button></li>
                    <li ng-if='ann.edit == 1' ><button type="button" class="small button" ng-click="save($index)" >Save</button></li>
                    <li ng-if ='ann.edit == 0'><button type="button" class="small button" ng-click = "delete($index)">Delete</button></li>
                </ul>

            </div>
        </div>
    </div>
    <dir-pagination-controls  ></dir-pagination-controls>        
</div>

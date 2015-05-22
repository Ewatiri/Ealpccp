<%-- 
    Document   : manageres
    Created on : Feb 21, 2015, 7:07:07 PM
    Author     : eva
--%>
<div class="row" >
    <div class="main-title">Resources</div>
    <div class="alert-box res1-alert" ></div>
    <div class="small-12 columns" >
        <div class="res stitched" dir-paginate="res in resources | itemsPerPage: pageSize" current-page="currentPage"> 
            <div class="res-title" >
               <p ng-if ='res.edit == 0'> {{ res.title}}</p>
               <div class="row collapse" ng-if ='res.edit == 1' >
                    <div class="small-4 columns" >
                        <span class="prefix" >New Title</span>
                    </div>
                    <div class="small-8 columns" >
                        <textarea  ng-model="res.title" ></textarea>
                    </div>
                </div>
            </div>
            <div class="announ-body" > 
                <p ng-if ='res.edit == 0' > {{ res.description}}</p>
                <div class="row collapse" ng-if ='res.edit == 1' >
                    <div class="small-4 columns" >
                        <span class="prefix" >New Description</span>
                    </div>
                    <div class="small-8 columns" >
                        <textarea  ng-model="res.description" ></textarea>
                    </div>
                </div>

            </div>
            <div class="res-link" >
                <a ng-href="{{res.url }}" target="_blank" >Link</a>
            </div>
            <div class="announ-signature" >
                <p class="capitalize" >{{ res.by}}</p>
            </div>
            <div class="announ-controls" >
                <ul class="button-group" >
                    <li ng-if ='res.edit == 0'><button type="button" class="small button" ng-click="edit($index)" >Edit</button></li>
                    <li ng-if='res.edit == 1' ><button type="button" class="small button" ng-click="save($index)" >Save</button></li>
                    <li ng-if ='res.edit == 0'><button type="button" class="small button" ng-click = "delete($index)">Delete</button></li>
                </ul>

            </div>
        </div>
    </div>
    <dir-pagination-controls  ></dir-pagination-controls>        
</div>
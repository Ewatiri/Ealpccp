<%-- 
    Document   : resources
    Created on : Feb 4, 2015, 10:21:58 AM
    Author     : eva
--%>

<div class="row resources-panel" >
    <div class="main-title">Resources </div>
    <div class="small-12 columns videos" >

        <div class="res stitched" dir-paginate="res in resources | itemsPerPage: pageSize" current-page="currentPage"> 
            <div class="" >
                <div class="res-title" >
                    Title : {{ res.title}}
                </div>
                <div class="res-type" >
                    <p ng-if="res.type == '1'">Type - Document</p>
                    <p ng-if="res.type == '2'">Type - Link</p>
                    <p ng-if="res.type == '3'">Type - Video</p>
                </div>
                <div class="announ-body">
                    Description :{{ res.description}}
                </div>
                <div class="res-link" >
                    <a ng-href="{{res.url}}" target="_blank" >Link</a>
                </div>
                
            </div>
        </div>
        <dir-pagination-controls  ></dir-pagination-controls>        
    </div>



</div>

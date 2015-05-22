<%-- 
    Document   : announcements
    Created on : Feb 4, 2015, 10:19:36 AM
    Author     : eva
--%>

<div class="announcements-panel row"  >
    <div class="main-title">Announcements</div>
    <div class="small-12 columns" >
        <div class="announ stitched" dir-paginate="ann in announcements | itemsPerPage: pageSize" current-page="currentPage" >
            <div class="capitalize" >
                <p ><b>{{ ann.title}}</b></p>
            </div>
            <div class="announ-body" > 
                <p > {{ ann.description}}</p>
            </div>
            <div class="announ-signature" >
                <p class="capitalize" >{{ ann.by}}</p>
                <p>{{ ann.resdate}}</p>
            </div>
        </div>

    </div>
    <dir-pagination-controls  ></dir-pagination-controls>        
</div>

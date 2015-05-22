<%-- 
    Document   : testlist
    Created on : Apr 2, 2015, 4:01:58 PM
    Author     : eva
--%>
<div class="row" ng-if="showlist" >
    <div class="small-12 columns" >
        <div class="main-title" >Test Lists</div>
        <div class="accordion1 mid acc" >
            <dl>
               <dt dir-paginate="t in tlist | itemsPerPage: pageSize" current-page="currentPage"><a ng-href  class="accordionTitle" ng-click="getList(t)" >{{ t.test }}</a></dt>
            </dl>
            <dir-pagination-controls  ></dir-pagination-controls>        
        </div>
        
    </div>
</div>
<div ng-show="!showlist" class="row" >
    <div class="small-12 columns" >
        <div class="main-title" >Test List</div>

        <div class="row" >
            <div class="small-4 columns" >
                Test : {{ stest.test }}
            </div>
            <div class="small-4 columns" >
                Venue : {{ stest.venue}}
            </div>
            <div class="small-4 columns" >
                Date : {{ stest.date}}
            </div>
            <div class="small-4 columns" >
                Time : {{ stest.time}}
            </div>
        </div>
        <div class="row" >
            <div class="small-12 columns" >
                <button class="tiny" ng-click="goBack()" >Back</button>
                <button class="tiny" ng-click="printTestList(stest)" ><i class="fa fa-file-pdf-o" >Export to PDF</i></button>
            </div>

        </div>
        <table class="small-12 columns">
            <thead>
                <tr>
                    <th>Scholar Name</th>
                    <th>Phone Number</th>
                    <th>Location</th>
                </tr>
                <tr>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                    <th>Search Key</th>
                    <th><input ng-model="fil"  type="text" /></th>
                </tr>
            </thead>
            <tbody>
                <tr dir-paginate="scholar in testscholars | filter: fil| itemsPerPage: pageSize1 " current-page="currentPage1">
                    <td class="capitalize" >{{ scholar.name}}</td>
                    <td>{{ scholar.phone}}</td>
                    <td >{{ scholar.location}}</td>
                </tr>
            </tbody>

        </table>
        <dir-pagination-controls  ></dir-pagination-controls>       
    </div>
</div>
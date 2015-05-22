<%-- 
    Document   : eventlist
    Created on : Mar 26, 2015, 12:04:55 PM
    Author     : eva
--%>
<div class="row" ng-show="showlist" >
    <div class="small-12 columns" >
        <div class="main-title" >Events</div>
        <div class="accordion1 mid acc" >
            <dl>
                <dt dir-paginate="ev in attevents |filter:fil1 | itemsPerPage: pageSize" current-page="currentPage"><a ng-href  class="accordionTitle" ng-click="getList(ev)" >{{ ev.title}}</a></dt>
            </dl>

        </div>
        <dir-pagination-controls  ></dir-pagination-controls>        
    </div>
</div>
<div ng-show="!showlist" class="row" >
    <div class="small-12 columns" >
        <div class="main-title" >Event List</div>
        
        <div class="row" >
            <div class="small-4 columns" >
                Event : {{ sevent.title }}
            </div>
            <div class="small-4 columns" >
                Venue : {{ sevent.venue }}
            </div>
            <div class="small-4 columns" >
                Date : {{ sevent.date }}
            </div>
            <div class="small-4 columns" >
                Time : {{ sevent.time }}
            </div>
        </div>
        <div class="row" >
            <div class="small-12 columns" >
                <button class="tiny" ng-click="goBack()" >Back</button>
                <button class="tiny" ng-click="printEventList(sevent)" ><i class="fa fa-file-pdf-o icon" >Export to PDF</i></button>
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
                <tr dir-paginate="scholar in eventlist | filter: fil| itemsPerPage: pageSize1 " current-page="currentPage1">
                    <td class="capitalize" >{{ scholar.name}}</td>
                    <td>{{ scholar.phone}}</td>
                    <td >{{ scholar.location}}</td>
                </tr>
            </tbody>

        </table>
        <dir-pagination-controls  ></dir-pagination-controls>       
    </div>
</div>
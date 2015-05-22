<%-- 
    Document   : testreports
    Created on : Apr 3, 2015, 9:50:17 AM
    Author     : eva
--%>
<div class="row" ng-if="showlist" >
    <div class="small-12 columns" >
        <div class="main-title" >Test Score Analysis</div>
        <div class="accordion1 mid acc" >
            <dl>
                <dt dir-paginate="t in testresults | itemsPerPage: pageSize" current-page="currentPage"><a ng-href  class="accordionTitle" ng-click="getList(t)" >{{ t.test}}</a></dt>
            </dl>
            <dir-pagination-controls  ></dir-pagination-controls>        
        </div>

    </div>
</div>
<div ng-if="!showlist" >
    <fieldset>
        <legend>Test Info</legend>
        <div class="row" >
            <div class="small-4 columns" >
                Test : {{ stest.test}}
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

            <div class="small-3 columns" >
                Number of students who took the test : {{ analysis.all}}
            </div>
            <div class="small-3 columns" >
                Number of students with uploaded scores : {{ analysis.taken}} 
            </div>
            <div class="small-3 columns" >
                Test Mean Score : {{ analysis.mean}} 
            </div>
            <div class="small-3 columns" >
                Test Median Score : {{ analysis.median}}  
            </div>

        </div>
        <div ng-if="stest.type == 'sat1'" >
            <div class="row" >
                <div class="small-6 columns" >
                    Math Section Mean Score: {{ analysis.s1mean}}
                </div>
                <div class="small-6 columns" >
                    Math Section Median Score : {{ analysis.s1median}}
                </div>
            </div>
            <div class="row" >
                <div class="small-6 columns" >
                    Critical Reading Mean Score : {{ analysis.s2mean}}
                </div>
                <div class="small-6 columns" >
                    Critical Reading Median Score : {{ analysis.s2median}}
                </div>
            </div>
            <div class="row" >
                <div class="small-6 columns" >
                    Writing Section Mean Score : {{ analysis.s3median}}
                </div>
                <div class="small-6 columns" >
                    Writing Section Median Score : {{ analysis.s3median}}
                </div>
            </div>
        </div>
        <div class="row" >
            <div class="small-12 columns" >
                <button class="tiny" ng-click="goBack()" >Back</button>
                <button class="tiny" ng-click="printTestResults(stest)" ><i class="fa fa-file-pdf-o icon" >Export to PDF</i></button>
            </div>

        </div>
    </fieldset>
</div>
<div ng-if="!showlist && (stest.type == 'sat1' || stest.type == 'sat2')" >
    <table class="small-12 columns">
        <thead>
            <tr>
                <th>Scholar Name</th>
                <th>Section</th>
                <th>Score</th>
                <th>Section</th>
                <th>Score</th>
                <th>Section</th>
                <th>Score</th>
            </tr>
            <tr>
                <th>&nbsp;</th>
                <th>&nbsp;</th>
                <th>&nbsp;</th>
                <th>&nbsp;</th>
                <th>&nbsp;</th>
                <th>&nbsp;</th>
                <th>Search Key</th>
                <th><input ng-model="fil"  type="text" /></th>
            </tr>
        </thead>
        <tbody>
            <tr dir-paginate="scholar in results | filter: fil| itemsPerPage: pageSize1 " current-page="currentPage1">
                <td class="capitalize" >{{ scholar.name}}</td>
                <td>{{ scholar.s1}}</td>
                <td >{{ scholar.score1}}</td>
                <td>{{ scholar.s2}}</td>
                <td >{{ scholar.score2}}</td>
                <td>{{ scholar.s3}}</td>
                <td >{{ scholar.score3}}</td>
            </tr>
        </tbody>

    </table>
    <dir-pagination-controls  ></dir-pagination-controls>
</div>
<div ng-if="!showlist && (stest.type != 'sat1' && stest.type != 'sat2')" >
    <table class="small-12 columns">
        <thead>
            <tr>
                <th>Scholar Name</th>
                <th>Score</th>
            </tr>
            <tr>
                <th>&nbsp;</th>
                <th>&nbsp;</th>
                <th>Search Key</th>
                <th><input ng-model="fil"  type="text" /></th>
            </tr>
        </thead>
        <tbody>
            <tr dir-paginate="scholar in results | filter: fil| itemsPerPage: pageSize1 " current-page="currentPage2">
                <td class="capitalize" >{{ scholar.name}}</td>
                <td >{{ scholar.score}}</td>
            </tr>
        </tbody>

    </table>
    <dir-pagination-controls  ></dir-pagination-controls>
</div>

<%-- 
    Document   : mod2scholareport
    Created on : Apr 4, 2015, 3:13:20 PM
    Author     : eva
--%>
<fieldset ng-show="view == 'menu'">
    <fieldset>
        <legend>Scholars by Program Type</legend>
        <div class="row" >
            <div class="small-12 columns" >
                <ul class="button-group" >
                    <li> <button class="button" type="button" ng-click="getScholarsbyType('all')"  >All Scholars</button> </li>
                    <li> <button class="button" type="button" ng-click="getScholarsbyType('mcfreg')" >Regular + MCF Scholars</button> </li>
                    <li> <button class="button" type="button" ng-click="getScholarsbyType('mcf')" >MCF Only Scholars</button> </li>
                    <li> <button class="button" type="button"ng-click="getScholarsbyType('reg')" >Regular Scholars</button> </li>
                </ul>
            </div>
        </div>
    </fieldset>
    <fieldset>
        <legend>Colleges and Scholars</legend>
        <div class="row" >
            <div class="small-12 columns" >
                <ul class="button-group" >
                    <li> <button class="button" type="button" ng-click="setParams('all')" >Scholars + College</button> </li>
                    <li> <button class="button" type="button" ng-click="setParams('rd')" >Scholars + Colleges + Regular Decision</button> </li>
                    <li> <button class="button" type="button" ng-click="setParams('ed')" >Scholars + Colleges + Early Decision</button> </li>
                </ul>
                <div ng-if="parameters" >
                    <form ng-submit="getScholarsbyColleges()" >
                        <div class="alert-box sreport11-alert" ></div>
                        <div class="row collapse" >
                            <div class="small-4 columns" >
                                <span class="prefix" >College Name</span>
                            </div>
                            <div class="small-8 columns" >
                                <input type="text" ng-model="college.name" placeholder="College Name" required/>
                            </div>
                        </div>
                        <button type="submit"  class="button small center-btn" >Submit</button>

                    </form>
                </div>
            </div>
        </div>
    </fieldset>
    <fieldset>
        <legend>Scholars and Application Status</legend>
        <div class="row" >
            <div class="small-12 columns" >
                <ul class="button-group" >
                    <li> <button class="button" type="button" ng-click="getAppStatus('all')" >All Scholars</button> </li>
                    <li> <button class="button" type="button" ng-click="getAppStatus('yet')">Yet to submit</button> </li>
                    <li> <button class="button" type="button" ng-click="getAppStatus('complete')" >Complete awaiting decisions</button> </li>
                    <li> <button class="button" type="button" ng-click="getAppStatus('accepted')" >Accepted</button> </li>
                    <li> <button class="button" type="button" ng-click="getAppStatus('rej')">Not Accepted</button> </li>
                </ul>
            </div>
        </div>
    </fieldset>
</fieldset>

<div class="row" ng-show="view == 'report1'" >
    <div class="small-12 columns" >
        <div class ="main-title" >Scholars by Program type</div>
        <div class="alert-box sreport1-alert" ></div>
        <fieldset  >
            <button class ="button tiny" type="button" ng-click="goBack()"  ><i class="fa fa-hand-o-left icon" ></i>Back to Menu</button>
            <button class ="button tiny" type="button" ng-click="getScholarTypePdf()"  ng-if="!empty" ><i class="fa fa-file-pdf-o icon" ></i>Export to PDF</button>
        </fieldset>
        <table class="small-12" ng-show="!empty" >
            <thead>
                <tr>
                    <th>Scholar Name</th>
                    <th>Program Type</th>
                </tr>
                <tr>
                    <th>&nbsp;</th>
                    <th>Filter</th>
                    <th><input ng-model="fil"  type="text" /></th>
                </tr>
            </thead>
            <tbody>
                <tr dir-paginate="scholar in sreports | filter: fil| itemsPerPage: pageSize " current-page="currentPage">
                    <td class="capitalize" >{{ scholar.name}}</td>
                    <td>{{ scholar.type}}</td>
                </tr>
            </tbody>

        </table>
    </div>
</div>
<div class="row" ng-show="view == 'report2'">
    <div class="small-12 columns" >
        <div class ="main-title capitalize" > {{ college.name}} Applicants </div>
        <div class="alert-box sreport2-alert" ></div>
        <fieldset>
            <button class ="button tiny" type="button" ng-click="goBack()"  ><i class="fa fa-hand-o-left icon" ></i>Back to Menu</button>
            <button class ="button tiny" type="button" ng-click="getScholarCollegePdf()"  ng-if="!empty" ><i class="fa fa-file-pdf-o icon" ></i>Export to PDF</button>
        </fieldset>
        <table class="small-12" ng-show="!empty" >
            <thead>
                <tr>
                    <th>Scholar Name</th>
                    <th>Application Type</th>
                </tr>
                <tr>
                    <th>&nbsp;</th>
                    <th>Filter</th>
                    <th><input ng-model="fil1"  type="text" /></th>
                </tr>
            </thead>
            <tbody>
                <tr dir-paginate="scholar in sreports | filter: fil1 | itemsPerPage: pageSize " current-page="currentPage1">
                    <td class="capitalize" >{{ scholar.name}}</td>
                    <td>{{ scholar.type}}</td>
                </tr>
            </tbody>

        </table>
    </div>
</div>
<div class="row" ng-show="view == 'report3'">
    <div class="small-12 columns" >
        <div class ="main-title capitalize" > Application Status Report </div>
        <div class="alert-box sreport3-alert" ></div>
        <fieldset>
            <button class ="button tiny" type="button" ng-click="goBack()"  ><i class="fa fa-hand-o-left icon" ></i>Back to Menu</button>
            <button class ="button tiny" type="button" ng-click="getStatusPdf()"  ng-if="!empty" ><i class="fa fa-file-pdf-o icon" ></i>Export to PDF</button>
        </fieldset>
        <table class="small-12" ng-show="!empty" >
            <thead>
                <tr>
                    <th>Scholar Name</th>
                    <th>College</th>
                    <th>Application Type</th>
                    <th>Status</th>
                </tr>
                <tr>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                    <th>Filter</th>
                    <th><input ng-model="fil1"  type="text" /></th>
                </tr>
            </thead>
            <tbody>
                <tr dir-paginate="scholar in sreports | filter: fil1 | itemsPerPage: pageSize " current-page="currentPage1">
                    <td class="capitalize" >{{ scholar.name}}</td>
                    <td> {{ scholar.college }} </td>
                    <td>{{ scholar.type}}</td>
                    <td>{{ scholar.status}}</td>
                </tr>
            </tbody>

        </table>
    </div>
</div>
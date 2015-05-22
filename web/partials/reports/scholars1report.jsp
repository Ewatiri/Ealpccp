<%-- 
    Document   : scholars1report
    Created on : Mar 30, 2015, 12:25:41 PM
    Author     : eva
--%>

<div class="row" >
    <div class="small-12 columns" >
        <fieldset>
            <legend>Scholar Reports</legend>
            <div class="row" >
                <div class="small-4 columns" >
                    <button type="button" class="button" ng-click="getAllApplicants()" >All Applicants</button>
                </div>
                <div class="small-4 columns" >
                    <button type="button" class="button" ng-click="getAcceptedScholars()" >Successful Applicants</button>
                </div>
                <div class="small-4 columns" >
                    <button type="button" class="button" ng-click="getRScholars()" >Unsuccessful Applicants</button>
                </div>
            </div>
            <div class="row"  >
                <div class="small-12 columns" >
                    <div ng-if="msg=='al'" >There are no submitted applications yet</div>
                    <button ng-if="all" class="tiny secondary" ng-click="getAllPdf()" type='button' ><i class="fa fa-file-pdf-o icon" ></i>Export to PDF</button>
                    <table class="small-12" ng-if="all">
                        <thead>
                            <tr>
                                <th>Scholar Name</th>
                                <th>Application Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr dir-paginate="app in applicants | itemsPerPage: pageSize " current-page="currentPage2" pagination-id="t3">
                                <td class="capitalize">{{ app.name}}</td>
                                <td>{{ app.status }}</td>
                            </tr>
                        </tbody>
                    </table>
                    <dir-pagination-controls pagination-id="t3" ></dir-pagination-controls>       
                </div>
            </div>
            <div class="row" >
                <div class="small-12 columns" >
                    <div ng-if="msg == 'accepted'" >There are no accepted scholars</div>
                    <button ng-if="accepted" class="tiny secondary" ng-click="getAcceptedPdf()" type='button' ><i class="fa fa-file-pdf-o icon" ></i>Export to PDF</button>
                    <table class="small-12" ng-if="accepted">
                        <thead>
                            <tr>
                                <th>Scholar Name</th>
                                <th>Reviewer 1</th>
                                <th>Score 1</th>
                                <th>MCF</th>
                                <th>Reviewer 2</th>
                                <th>Score 2</th>
                                <th>MCF</th>
                                <th>Reviewer 3</th>
                                <th>Score 3</th>
                                <th>MCF</th>

                            </tr>
                        </thead>

                        <tbody>
                            <tr dir-paginate="scholar in acceptedscholar | itemsPerPage: pageSize " current-page="currentPage1" pagination-id="t1">
                                <td class="capitalize">{{ scholar.name}}</td>
                                <td class="capitalize">{{ scholar.r1}}</td>
                                <td >{{ scholar.score1}}</td>
                                <td >{{ scholar.mcf1}}</td>
                                <td class="capitalize" >{{ scholar.r2}}</td>
                                <td >{{ scholar.score2}}</td>
                                <td >{{ scholar.mcf2}}</td>
                                <td class="capitalize" >{{ scholar.r3}}</td>
                                <td >{{ scholar.score3}}</td>
                                <td >{{ scholar.mcf3}}</td>

                            </tr>

                        </tbody>
                    </table>
                    <dir-pagination-controls pagination-id="t1" ></dir-pagination-controls>       
                </div>
            </div>
            <div class="row"  >
                <div class="small-12 columns" >
                    <div ng-if="msg =='re'" >There are no scholars</div>
                    <button ng-if="rej" class="tiny secondary" ng-click="getRejPdf()" type='button' ><i class="fa fa-file-pdf-o icon" ></i>Export to PDF</button>
                    <div class="alert-box rejaccept-alert" ></div>
                    <table class="small-12" ng-if="rej" >
                        <thead>
                            <tr>
                                <th>Scholar Name</th>
                                <th>Reviewer 1</th>
                                <th>Score 1</th>
                                <th>MCF</th>
                                <th>Reviewer 2</th>
                                <th>Score 2</th>
                                <th>MCF</th>
                                <th>Reviewer 3</th>
                                <th>Score 3</th>
                                <th>MCF</th>
                                <th>Accept</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr dir-paginate="rscholar in rscholars | itemsPerPage: pageSize " current-page="currentPage" pagination-id="t2">
                                <td class="capitalize">{{ rscholar.name}}</td>
                                <td class="capitalize">{{ rscholar.r1}}</td>
                                <td >{{ rscholar.score1}}</td>
                                <td >{{ rscholar.mcf1}}</td>
                                <td class="capitalize" >{{ rscholar.r2}}</td>
                                <td >{{ rscholar.score2}}</td>
                                <td >{{ rscholar.mcf2}}</td>
                                <td class="capitalize" >{{ rscholar.r3}}</td>
                                <td >{{ rscholar.score3}}</td>
                                <td >{{ rscholar.mcf3}}</td>
                                <td>
                                    <select ng-model="rscholar.status">
                                        <option value="">--</option>
                                        <option value="11" >Regular + MCF</option>
                                        <option value="10" >Regular Only</option>
                                        <option value="20" >MCF Only</option>
                                    </select>
                                    <button class="tiny" type="button" ng-click="acceptScholar(rscholar)" >Accept</button>
                                </td>
                                <td  ></td>
                            </tr>
                        </tbody>

                    </table>
                    <dir-pagination-controls pagination-id="t2" ></dir-pagination-controls> 
                </div>
            </div>
        </fieldset>
    </div>
</div>
<%-- 
    Document   : applicationAnalysis
    Created on : Mar 31, 2015, 11:02:01 AM
    Author     : eva
--%>

<div class="row" >
    <div class="small-12 columns" >
        <fieldset>
            <legend>Analysis Report</legend>
            <div class="alert-box analysis1-alert" ></div>
            <div>
                <button class="small" type="button" ng-click="getAnalysis()" >Get Analysis</button>
            </div>
            <button ng-if="s"  class="tiny secondary" ng-click="getAnalysis1Pdf()" type='button' ><i class="fa fa-file-pdf-o icon" ></i>Export to PDF</button>
            <div></div>

            <div class="row" ng-if="s" >
                <div class="small-6 columns" >
                    <fieldset>
                        <legend>Applicants Analysis</legend>
                        <table>
                            <tbody>
                                <tr>
                                    <td>Successful Applicants</td>
                                    <td>{{ analysis.successful}}</td>
                                </tr>
                                <tr>
                                    <td>Unsuccessful Applicants</td>
                                    <td>{{ analysis.rej}}</td>
                                </tr>
                                <tr>
                                    <td><b>Total Number of Applicants</b></td>
                                    <td><b>{{ analysis.total}}</b></td>
                                </tr>
                            </tbody>
                        </table>
                    </fieldset>
                </div>
                <div class="small-6 columns" >
                    <fieldset>
                        <legend>Successful Applicants Analysis</legend>
                        <table>
                            <tbody>
                                <tr>
                                    <td>Regular + MCF </td>
                                    <td>{{ analysis.regmcf}}</td>
                                </tr>
                                <tr>
                                    <td>Regular Only</td>
                                    <td>{{ analysis.reg}}</td>
                                </tr>
                                <tr>
                                    <td>MCF Only</td>
                                    <td>{{ analysis.mcf}}</td>
                                </tr>
                                <tr>
                                    <td><b>Total Successful</b></td>
                                    <td><b>{{ analysis.successful }}</b></td>
                                </tr>
                            </tbody>
                        </table>
                    </fieldset>
                </div>
            </div>
        </fieldset>

    </div>
</div>

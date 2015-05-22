<%-- 
    Document   : oldcallist
    Created on : Mar 6, 2015, 4:05:36 PM
    Author     : eva
--%>
<div class="row"  ng-show="!manage" >
    <div class="small-12 columns" >
        <div class="alert-box oldcall-alert" ></div>
        <div class="accordion1" id="accordion1" chris-accordion  >

            <dl>
                <div dir-paginate="callist in callists | itemsPerPage: pageSize" current-page="currentPage" pagination-id="t2">
                    <dt><a class="accordionTitle" ng-href>{{ callist.title}} </a></dt>
                    <dd class="accordionItem accordionItemCollapsed">
                        <div  ng-if="callist.edit != '0'"  >
                            <div class="row collapse" >
                                <div class="small-4 columns" >
                                    <span class="prefix" >New Title</span>
                                </div>
                                <div class="small-8 columns" >
                                    <input type="text"  ng-model="callist.title" />
                                </div>
                            </div>
                        </div>
                        <div  >
                            <div class="small-12 columns" >
                                <div ng-if="callist.edit == '0'" > Description: {{ callist.des}}</div>
                                <div ng-if="callist.edit != '0'" >
                                    <div class="row collapse" >
                                        <div class="small-4 columns" >
                                            <span class="prefix" >New Description</span>
                                        </div>
                                        <div class="small-8 columns" >
                                            <textarea placeholder="Description"  ng-model="callist.des"  required=""></textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div  >
                            <div class="small-5 columns" >
                                Creator: {{ callist.by}}<br/>
                                Last Edited By: {{ callist.last}}
                            </div>
                        </div>

                        <div class="" > 
                            <button ng-if="callist.edit == '0'" type="button" class="button tiny radius " ng-click="manageScholars($index)"  >Manage Scholars</button>
                            <button ng-if="callist.edit == '1'"  type="button" class="button tiny radius" ng-click="save($index)" >Save</button>
                            <button ng-if="callist.edit == '0'" type="button" class="button tiny radius" ng-click="edit($index)" >Edit</button>
                            <button ng-if="callist.edit == '0'" type="button" class="button tiny radius" ng-click="delete($index)" >Delete</button>
                        </div>


                    </dd>
                </div>

            </dl>
            <dir-pagination-controls pagination-id="t2" ></dir-pagination-controls>
        </div>

    </div>

</div>
<div class="row" ng-show="manage"  >
    <div class="row " >
        <div class="small-12 columns " >
            <div class="alert-box oldcall2-alert" ></div>
            <fieldset>
                <legend>Call List Details</legend>
                <div class="row" >
                    <div class="small-12 columns" >
                        <b>Title</b> {{ list.title}}
                    </div>
                </div>
                <div class="row" >
                    <div class="small-12 columns" >
                        <b>Description </b>  {{ list.des}}
                    </div>
                </div>
                <div class="row" >
                    <div class="small-12 columns" >
                        <button class="tiny button secondary" ng-click="viewCallists()">View All Call lists</button>
                    </div>
                </div>
            </fieldset>

        </div>
    </div>
    <div class="row" >
        <div class="small-12 columns" >
            <div class="cd-tabs" >
                <nav>
                    <ul class="cd-tabs-navigation">
                        <li><a  data-content="me" class="selected" ng-href cody-house-tabs ><i class="fa fa-user"></i>Call List Members</a></li>
                        <li><a data-content="others"ng-href cody-house-tabs  ><i class="fa fa-pencil"></i>Other Scholar</a></li>
                    </ul>
                </nav>
                <ul class="cd-tabs-content">
                    <li data-content="me" class="selected">
                        <table class="small-12 columns">
                            <thead>
                                <tr>
                                    <th>Scholar Name</th>
                                    <th>Comment</th>
                                    <th>Status</th>
                                </tr>
                                <tr>
                                    <th>&nbsp;</th>
                                    <th>&nbsp;</th>
                                    <th>Search Key</th>
                                    <th><input ng-model="fil1"  type="text" /></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr dir-paginate="scholar in scholarlist | filter: fil1| itemsPerPage: pageSize1 " current-page="currentPage1" pagination-id="t1">
                                    <td class="capitalize" >{{ scholar.name}}</td>
                                    <td><textarea ng-model="scholar.feedback" ng-readonly="scholar.edit == '1'" > </textarea></td>
                                    <td  ><select ng-disabled="scholar.edit == '1'" ng-model="scholar.status"><option value="0" >Not started</option><option value="1">In progress</option><option value ="2">Complete</option></select></td>
                                    <td>
                                        <button class="button tiny radius secondary" ng-show="scholar.edit == '1'" type="button"ng-click="editFeedback($index)" >Edit</button>
                                        <button class="button tiny radius secondary" ng-show="scholar.edit == '2'" type="button" ng-click="saveFeedback($index)">Save</button>
                                    </td>
                                </tr>
                            </tbody>

                        </table>
                    <dir-pagination-controls pagination-id="t1" ></dir-pagination-controls>       
                    </li>
                    <li data-content="others" >
                        <table class="small-12 columns">
                            <thead>
                                <tr>
                                    <th>Scholar Name</th>
                                    <th>Comment</th>
                                    <th>Status</th>
                                </tr>
                                <tr>
                                    <th>&nbsp;</th>
                                    <th>&nbsp;</th>
                                    <th>Search Key</th>
                                    <th><input ng-model="fil"  type="text" /></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr dir-paginate="sch in allscholars | filter: fil|itemsPerPage:pageSize1" current-page="page3" pagination-id="t5" >
                                    <td class="capitalize" >{{ sch.name}}</td>
                                    <td><textarea ng-model="sch.feedback"  > </textarea></td>
                                    <td  ><select  ng-model="sch.status"><option value="0" >Not started</option><option value="1">In progress</option><option value ="2">Complete</option></select></td>
                                    <td>
                                        <button class="button tiny radius secondary" type="button" ng-click="addScholar($index)" >Add</button>

                                    </td>
                                </tr>
                            </tbody>

                        </table>
                    <dir-pagination-controls pagination-id="t5" ></dir-pagination-controls>       
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
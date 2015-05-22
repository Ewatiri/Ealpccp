<%-- 
    Document   : collegessays
    Created on : Mar 8, 2015, 4:05:10 PM
    Author     : eva
--%>
<div class="colleges" ng-if="showessay"  >
    <div class="main-title" >Colleges</div>
    <div class="row"  >
        <div class="row collapse" >
            <div class="small-4 columns" >
                <span class="prefix" >Filter</span>
            </div>
            <div class="small-8 columns" >
                <input type="text" ng-model="q" />
            </div>
        </div>
    </div>
    <div class="accordion1 mid acc" >
        <dl>
            <dt dir-paginate="college in colleges |filter: q |itemsPerPage: pageSize" current-page="currentPage"><a ng-href ng-click="switchView($index)" class="accordionTitle">{{ college.name}}</a></dt>
        </dl>
        <dir-pagination-controls  ></dir-pagination-controls>        
    </div>
</div>
<div class="essays" ng-if="!showessay" >
    <div class="row" >
        <div class="small-12 columns" >
            <div class="main-title" >{{ mycollege.name}}</div>
            <div>
                <button class="button secondary radius" type="button"  ng-click="goBack()"><i class="fa fa-hand-o-left icon" ></i>Back to Colleges</button>
            </div>
            <div class="alert-box collegessay-alert " ></div>
            <college-essays model="essays">
                <college-essay ng-repeat="field in essays"   >
                    <fieldset>
                        <legend>Prompt {{ $index + 1}}</legend>
                        <div class="row" >
                            <div class="small-7 columns" >
                                <div class="row collapse" >
                                    <div class="small-4 columns" >
                                        <span class="prefix" >Prompt</span>
                                    </div>
                                    <div class="small-8 columns" >
                                        <textarea ng-model="field.prompt" ng-readonly="field.edit=='0'" required></textarea>
                                    </div>
                                </div>

                            </div>
                            <div class="small-3 columns"  ng-if="!field.status || field.edit=='1'" >
                                <label>Is the essay required?</label>
                                <input type="radio" name="commonapp" value="1" id="pokemonRed" ng-model="field.req" ><label for="pokemonRed">Yes</label>
                                <input type="radio" name="commonapp" value="0" id="pokemonBlue" ng-model="field.req"><label for="pokemonBlue">No</label>

                            </div>
                            <div class="small-4 columns" ng-if="field.status && field.edit=='0'" >
                                <p ng-if="field.required == '1'">Required </p>
                                <p ng-if="field.required == '0'">Not Required </p>
                            </div>
                            <div class="small-1 columns myclose"  ng-if="!field.status" >
                                <a href class="close" ng-click="removeEssay(field)"  >&times;</a>
                            </div>

                        </div>
                        <div ng-if="field.status" >
                            <p ng-if="field.status == '1'" >Approved</p>
                            <p ng-if="field.status == '0'" >Pending Approval</p>
                            <p ng-if="field.status == '-1'" >Rejected</p>
                        </div>
                        <button type="submit" ng-if="!field.status" class="button small" >Add</button>
                        <button type="button" ng-if="field.status && field.edit =='0'" class="button small" ng-click="edit(field)" >Edit Prompt</button>
                        <button type="button" ng-if="field.edit == '1'" class="button small" ng-click="save(field)" >Save Prompt</button>
                        <button type="button" ng-if="field.status && field.edit =='0'" class="button small" ng-click="delete(field)" >Delete Prompt</button>
                    </fieldset>
                </college-essay>
                <button class="button tiny" type="button" ng-click="addEssay()" >New Prompt</button>
            </college-essays>
        </div>
    </div>
</div>
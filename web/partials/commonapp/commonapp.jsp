<%-- 
    Document   : commonapp
    Created on : Apr 16, 2015, 11:39:52 AM
    Author     : eva
--%>
<div class="row" >
    <div class="small-12 columns" >
        <fieldset>
            <legend>Common App Essays</legend>
            <div class="alert-box commonapp-alert " ></div>
            <commonapp-essays model="essays">
                <commonapp-essay ng-repeat="field in prompts"   >
                    <fieldset>
                        <legend>Prompt {{ $index + 1}}</legend>
                        <div class="row" >
                            <div class="small-7 columns" >
                                <div class="row collapse" >
                                    <div class="small-4 columns" >
                                        <span class="prefix" >Prompt</span>
                                    </div>
                                    <div class="small-8 columns" >
                                        <textarea ng-model="field.prompt" ng-readonly="field.edit == '1'" required></textarea>
                                    </div>
                                </div>

                            </div>

                            <div class="small-1 columns myclose"  ng-if="!field.edit == '1'" >
                                <a href class="close" ng-click="removePrompt(field)"  >&times;</a>
                            </div>

                        </div>

                        <button type="submit" ng-if="field.edit != 1" class="button small" >Add</button>
                        <button type="button" ng-if="field.edit == '1'" class="button small" ng-click="delete(field)" >Delete Prompt</button>
                    </fieldset>
                </commonapp-essay>
                <button class="button tiny" type="button" ng-click="addEssay()" >New Prompt</button>
            </commonapp-essays>
        </fieldset>

    </div>
</div>


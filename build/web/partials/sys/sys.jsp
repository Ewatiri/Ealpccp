<%-- 
    Document   : sys
    Created on : Mar 11, 2015, 9:03:08 PM
    Author     : eva
--%>
<div class="row" >
    <div class="small-12 columns" >
        <form ng-submit="setModule()">
            <fieldset>
                <legend>Current Module</legend>
                <div class="alert-box setmodule-alert" ></div>
                <div class="row collapse" >
                    <div class="small-4 columns" >
                        <span class="prefix" >Admission Cycle</span>
                    </div>
                    <div class="small-8 columns" >
                        <input type="text" placeholder = "2011/2012" ng-model="module.cycle"  required/>
                    </div>
                </div>
                <div class="row collapse" >
                    <div class="small-4 columns" >
                        <span class="prefix" >Select Module </span>
                    </div>
                    <div  >
                        <select ng-model="module.mod" class="small-8 columns" >
                            <option value="" >--</option>
                            <option value="1" >1</option>
                            <option value="2" >2</option>
                        </select>
                    </div>
                </div>
                <button class="button center-btn" type="submit" >Update</button>
            </fieldset>
        </form>
    </div>
</div>

<%-- 
    Document   : manageres
    Created on : Feb 18, 2015, 12:27:37 PM
    Author     : eva
--%>


<div class="row" >
    <div class="small-12 columns " >
        <form ng-submit="addRes()">
            <fieldset>
                <legend>Add Resource</legend>
                <div class="alert-box mres-alert" ></div>
                <div class="row collapse" >
                    <div class="small-4 columns" >
                        <span class="prefix" >Title</span>
                    </div>
                    <div class="small-8 columns" >
                        <input type="text" placeholder = "Helpful Documents" ng-model="res.title" required/>
                    </div>
                </div>
                <div class="row collapse" >
                    <div class="small-4 columns" >
                        <span class="prefix" >Description</span>
                    </div>
                    <div class="small-8 columns" >
                        <textarea placeholder="Description" rows="5" ng-model="res.desc"  required=""></textarea>
                    </div>
                </div>
                <div class="row collapse" >
                    <div class="small-4 columns" >
                        <span class="prefix" >Type</span>
                    </div>
                    <div  >
                        <select required="" ng-model="res.type" class="small-8 columns">
                            <option value="1" >Document</option>
                            <option value="2" >URL</option>
                            <option value="3" >Video</option>
                        </select>
                    </div>
                </div>
                <div class="row collapse" ng-if="res.type == '2'"  >
                    <div class="small-4 columns" >
                        <span class="prefix" >Link</span>
                    </div>
                    <div class="small-8 columns" >
                        <input type="text" ng-model="res.link" />
                    </div>
                </div>
                <div class="row collapse" ng-if="res.type == '1' || res.type == '3'"  >
                    <div class="small-4 columns" >
                        <span class="prefix" >File</span>
                    </div>
                    <div class="small-8 columns" >
                        <input type="file" ng-model="res.file" file-input="res.file" />
                    </div>
                </div>
                <button class="center-btn" type="submit">Add</button>
            </fieldset>
        </form>
    </div>
</div>

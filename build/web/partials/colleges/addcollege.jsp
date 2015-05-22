<%-- 
    Document   : colleges
    Created on : Mar 8, 2015, 3:07:32 PM
    Author     : eva
--%>

<div class="add-colleges" >
    <div class="row" >
        <div class="small-12 columns" >
            <form ng-submit="addCollege()">
                <fieldset>
                    <legend>Add College</legend>
                    <div class="alert-box college-alert" ></div>
                    <div class="row collapse" >
                        <div class="small-4 columns" >
                            <span class="prefix" >College Name</span>
                        </div>
                        <div class="small-8 columns" >
                            <input type="text" placeholder = "XYZ College" ng-model="college.title" required/>
                        </div>
                    </div>
                    <div class="row" >
                        <div class="small-6 columns" >
                            <div class="row collapse" >
                                <div class="small-6 columns" >
                                    <span class="prefix" >Early Decision Deadline</span>
                                </div>
                                <div class="small-6 columns" >
                                    <input type="text" placeholder = "DD/MM" ng-model="college.ed" required/>
                                </div>
                            </div>
                        </div>
                        <div class="small-6 columns" >
                            <div class="row collapse" >
                                <div class="small-6 columns" >
                                    <span class="prefix" >Regular Decision Deadline</span>
                                </div>
                                <div class="small-6 columns" >
                                    <input type="text" placeholder = "DD/MM" ng-model="college.reg" required/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row" >
                        <div class="large-6 columns">
                            <label>Does the college/university use Common App?</label>
                            <input type="radio" name="commonapp" value="1" id="pokemonRed" ng-model="college.commonapp" ><label for="pokemonRed">Yes</label>
                            <input type="radio" name="commonapp" value="0" id="pokemonBlue" ng-model="college.commonapp"><label for="pokemonBlue">No</label>
                        </div>
                    </div>
                    <div class="row" >
                        <div class="large-6 columns">
                            <label>Is the college/university under MCF scholarships?</label>
                            <input type="radio" name="mcf" value="1" id="pokemonRed" ng-model="college.mcf" ><label for="pokemonRed">Yes</label>
                            <input type="radio" name="mcf" value="0" id="pokemonBlue" ng-model="college.mcf"><label for="pokemonBlue">No</label>
                        </div>
                    </div>
                    <button class="button center-btn" type="submit" >Add College</button>
                </fieldset>
            </form>
        </div>
    </div>
</div>

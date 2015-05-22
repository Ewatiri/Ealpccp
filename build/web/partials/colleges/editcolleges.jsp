<%-- 
    Document   : editcolleges
    Created on : Mar 8, 2015, 3:23:49 PM
    Author     : eva
--%>
<div class="row" >
    <div class="main-title">Colleges</div>
    <div class="alert-box college-alert2" ></div>
    <div class="small-12 columns" >
        <div class="stitched" dir-paginate="college in colleges | itemsPerPage: pageSize" current-page="currentPage"> 

            <div class="row" >
                <div class="small-12 columns" >
                    <p ng-if ='college.edit == 0'> {{ college.name}}</p>
                    <div class="row collapse" ng-if ='college.edit == 1' >
                        <div class="small-4 columns" >
                            <span class="prefix" >New Name</span>
                        </div>
                        <div class="small-8 columns" >
                            <input type="text" ng-model="college.name" />
                        </div>
                    </div>
                </div>


            </div>
            <div class="row" >
                <div class="small-6 columns" >
                    <p ng-if="college.edit == '0'" >{{ college.ed}}</p>
                    <div class="row collapse" ng-if ='college.edit == 1' >
                        <div class="small-4 columns" >
                            <span class="prefix" >Early Decision Deadline</span>
                        </div>
                        <div class="small-8 columns" >
                            <input type="text" ng-model="college.ed" />
                        </div>
                    </div>
                </div>

                <div class="small-6 columns" >
                    <p ng-if="college.edit == '0'" >{{ college.reg}}</p>
                    <div class="row collapse" ng-if ='college.edit == 1' >
                        <div class="small-4 columns" >
                            <span class="prefix" >Regular Decision Deadline</span>
                        </div>
                        <div class="small-8 columns" >
                            <input type="text" ng-model="college.reg" />
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <p ng-if ='college.commonapp == 1 && college.edit == 0'>The college/university uses Common App</p>
                <p ng-if ='college.commonapp == 0 && college.edit == 0'>The college/university does not use Common App</p>
                <div class="large-6 columns" ng-if ='college.edit == 1'>
                    <label>Does the college/university use Common App?</label>
                    <input type="radio" name="commonapp" value="1" id="pokemonRed" ng-model="college.commonapp" ><label for="pokemonRed">Yes</label>
                    <input type="radio" name="commonapp" value="0" id="pokemonBlue" ng-model="college.commonapp"><label for="pokemonBlue">No</label>
                </div>
            </div>
            <div class="row">
                <p ng-if ='college.mcf == 1 && college.edit == 0'>The college/university is under MCF scholarships</p>
                <p ng-if ='college.mcf == 0 && college.edit == 0'>The college/university is not under MCF scholarships</p>
                <div class="large-6 columns" ng-if ='college.edit == 1'>
                    <label>Is the college/university under MCF scholarships?</label>
                    <input type="radio" name="mcf" value="1" id="pokemonRed" ng-model="college.mcf" ><label for="pokemonRed">Yes</label>
                    <input type="radio" name="mcf" value="0" id="pokemonBlue" ng-model="college.mcf"><label for="pokemonBlue">No</label>
                </div>
            </div>
            <div class="announ-controls" >
                <ul class="button-group" >
                    <li ng-if ="college.edit == '0'"><button type="button" class="small button" ng-click="edit($index)" >Edit</button></li>
                    <li ng-if ="college.edit == '1'" ><button type="button" class="small button" ng-click="save($index)" >Save</button></li>
                </ul>

            </div>
        </div>
    </div>
    <dir-pagination-controls  ></dir-pagination-controls>        
</div>


<%-- 
    Document   : managetest
    Created on : Mar 9, 2015, 11:52:56 AM
    Author     : eva
--%>

<div class="row" >
    <div class="main-title">Tests</div>
    <div class="alert-box test2-alert" ></div>
    <div class="small-12 columns" >
        <div class="res stitched" dir-paginate="test in tests | itemsPerPage: pageSize" current-page="currentPage"> 
            <div class="row" >
                <div class="small-12 columns" >
                    <p ng-if ='test.edit == 0'> {{ test.title}}</p>
                    <div class="row collapse" ng-if ='test.edit == 1' >
                        <div class="small-4 columns" >
                            <span class="prefix" >New Title</span>
                        </div>
                        <div class="small-8 columns" >
                            <input type="text"  ng-model="test.title" />
                        </div>
                    </div>
                </div>
            </div>

            <div  class="row" >
                <div class="small-6 columns" >
                    <p ng-if ='test.edit == 0'> {{ test.date}}</p>
                    <div class="row collapse" ng-if ='test.edit == 1' >
                        <div class="small-4 columns" >
                            <span class="prefix" >New Date</span>
                        </div>
                        <div class="small-8 columns" >
                            <quick-datepicker ng-model="test.ttime"   placeholder="Select Date and Time"  ></quick-datepicker>
                            <!--<input type="text"  ng-model="test.date" />-->
                        </div>
                    </div>
                </div>
                <div class="small-6 columns" >
                    <p ng-if ='test.edit == 0'> {{ test.time}}</p>
                    <div class="row collapse" ng-if ='test.edit == 1' >
                        <div class="small-4 columns" >
                            <span class="prefix" >New Time</span>
                        </div>
                        <div class="small-8 columns" >
                            <input type="text"  ng-model="test.time" readonly="" />
                        </div>
                    </div>
                </div>


            </div>
            <div  class="row">
                <div class="small-6 columns" >
                    <div ng-if ='test.edit == 0' >
                        <p > {{ test.type}}</p>

                    </div>

                    <div class="row collapse" ng-if ='test.edit == 1' >
                        <div class="small-4 columns" >
                            <span class="prefix" >Type 1</span>
                        </div>
                        <div class="small-8 columns" >
                            <select ng-model="test.type">
                                <option value="" >--</option>
                                <option value="sat1" >SAT 1</option>
                                <option value="sat2" >SAT 2</option>
                                <option value="act" >ACT</option>
                                <option value="toefl" >TOEFL</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="small-6 columns" >
                    <div ng-if ='test.edit == 0' >
                        <p ng-if="test.official == '0'">    Official</p>
                        <p ng-if="test.official == '1'">   Diagnostic</p>
                    </div>

                    <div class="row collapse" ng-if ='test.edit == 1' >
                        <div class="small-4 columns" >
                            <span class="prefix" >Type 2</span>
                        </div>
                        <div class="small-8 columns" >
                            <select ng-model="test.official">
                                <option value="0" >Official</option>
                                <option value="1" >Diagnostic</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row" >
                <div class="small-12 columns" >
                    <p ng-if ='test.edit == 0'> {{ test.location}}</p>
                    <div class="row collapse" ng-if ='test.edit == 1' >
                        <div class="small-4 columns" >
                            <span class="prefix" >New Location</span>
                        </div>
                        <div class="small-8 columns" >
                            <input type="text"  ng-model="test.location" />
                        </div>
                    </div>
                </div>
            </div>
            <div class="row" >
                <div class="small-12 columns" >
                    <button class="button radius small" ng-click="edit($index)" ng-if="test.edit == '0'" >Edit</button>
                    <button class="button radius small" ng-click="save($index)" ng-if="test.edit == '1'">Save</button>
                </div>
            </div>
        </div>
    </div>
</div>

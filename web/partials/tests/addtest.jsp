<%-- 
    Document   : addtest
    Created on : Mar 9, 2015, 10:18:27 AM
    Author     : eva
--%>
<div class="row" >
    <div class="small-12 columns" >
        <form ng-submit="addTest()">
            <fieldset>
                <legend>Add Test</legend>
                <div class="alert-box test1-alert" ></div>
                <div class="row" >
                    <div class="small-12 columns" >
                        <div class="row collapse" >
                            <div class="small-4 columns" >
                                <span class="prefix" >Title</span>
                            </div>
                            <div class="small-8 columns" >
                                <input type="text" placeholder = "Official SAT 1" ng-model="test.title" required/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row" >
                    <div class="small-6 columns" >
                        <div class="row collapse" >
                            <div class="small-4 columns" >
                                <span class="prefix" >Type 1</span>
                            </div>
                            <div  >
                                <select class="small-8 columns" ng-model="test.type1" required>
                                    <option value="">--</option>
                                    <option value="act" >ACT</option>
                                    <option value="sat1" >SAT 1 </option>
                                    <option value="sat2" >SAT 2 </option>
                                    <option value="toefl" >TOEFL</option>
                                </select>
                                
                            </div>
                        </div>
                    </div>
                    <div class="small-6 columns" >
                        <div class="row collapse" >
                            <div class="small-4 columns" >
                                <span class="prefix" >Type 2</span>
                            </div>
                            <div  >
                                <select ng-model="test.type2" required class="small-8 columns" >
                                    <option value="" >--</option>
                                    <option value="0" >Official</option>
                                    <option value="1" >Diagnostic</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row" >
                    <div class="small-6 columns" >
                        <div class="row collapse" >
                            <div class="small-4 columns" >
                                <span class="prefix" >Date</span>
                            </div>
                            <div class="small-8 columns" >
                                 <quick-datepicker ng-model="ttime"   ></quick-datepicker>
                                <!--<input type="text" ng-model="test.date" placeholder="01/01/2015" required/>-->

                            </div>
                        </div> 
                    </div>
                    <div class="small-6 columns" >
                        <div class="row collapse" >
                            <div class="small-4 columns" >
                                <span class="prefix" >Time</span>
                            </div>
                            <div class="small-8 columns" >
                                <input type="text" ng-model="test.time" placeholder="7.00" readonly required/>
                               
                            </div>
                        </div>
                    </div>
                    
                </div>
                <div class="row" >
                    <div class="small-12 columns" >
                        <div class="row collapse" >
                            <div class="small-4 columns" >
                                <span class="prefix" >Location</span>
                            </div>
                            <div class="small-8 columns" >
                                <input type="text" ng-model="test.location" placeholder="Braeburn School" required/>

                            </div>
                        </div>
                    </div>
                </div>
                <button class="button radius center-btn" type="submit" >Add Test</button>

            </fieldset>
        </form>
    </div>
</div>

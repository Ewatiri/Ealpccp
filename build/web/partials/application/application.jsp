<%-- 
    Document   : personal
    Created on : Feb 4, 2015, 10:20:00 AM
    Author     : eva
--%>

<div class="myapp-panel" ng-init="init()" >
    <div class="personal-app" ng-if="elem1 == 'pdets'">
        <form ng-submit="addPdets()">
            <fieldset>
                <legend>Personal Details</legend>
                <div class="alert-box pdets-alert"></div>
                <div class="row" >
                    <div class="small-4 columns" >
                        <div class="row collapse" >
                            <div class="small-4 columns" >
                                <span class="prefix" >First Name</span>
                            </div>
                            <div class="small-8 columns" >
                                <input type="text" ng-readonly="appstate.s1 == 1" placeholder = "John" ng-model="frmPdets.fname" required/>
                            </div>
                        </div>
                    </div>
                    <div class="small-4 columns" >
                        <div class="row collapse" >
                            <div class="small-4 columns" >
                                <span class="prefix" >Middle Name</span>
                            </div>
                            <div class="small-8 columns" >
                                <input type="text" placeholder = "Smith" ng-readonly="appstate.s1 == 1"  ng-model="frmPdets.mname" />
                            </div>
                        </div>
                    </div>
                    <div class="small-4 columns" >
                        <div class="row collapse" >
                            <div class="small-4 columns" >
                                <span class="prefix" >Surname</span>
                            </div>
                            <div class="small-8 columns" >
                                <input type="text" placeholder = "Doe" ng-readonly="appstate.s1 == 1"  ng-model="frmPdets.sname" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row" >
                    <div class="small-4 columns" >
                        <div class="row collapse" >
                            <div class="small-4 columns" >
                                <span class="prefix" >Date of Birth</span>
                            </div>
                            <div class="small-8 columns" >
                                <!--<input type="text" class="dob" placeholder = "01-01-1970"  ng-model="frmPdets.dob" id="dp1" ng-readonly="appstate.s1 == 1"  required/>-->
                                <quick-datepicker ng-model="dob"   disable-timepicker='true'></quick-datepicker>
                            </div>
                        </div>
                    </div>
                    
                    <div class="small-4 columns" >
                        <div class="row collapse" >
                            <div class="small-4 columns" >
                                <span class="prefix" >Email Address</span>
                            </div>
                            <div class="small-8 columns" >
                                <input type="text" placeholder = "johnsmith@example.com"  ng-model="frmPdets.email" readonly="" required/> 
                            </div>
                        </div>
                    </div>
                    <div class="small-4 columns" >
                        <div class="row collapse" >
                            <div class="small-4 columns" >
                                <span class="prefix" >Phone Number</span>
                            </div>
                            <div class="small-8 columns" >
                                <input type="text" placeholder = "+254 721 234 567" ng-readonly="appstate.s1 == 1" ng-model="frmPdets.phone"required/>
                            </div>
                        </div>
                    </div>
                </div>
            </fieldset>
            <fieldset>
                <legend>Family Details</legend>
                <div class="row">
                    <div class="small-4 columns">
                        <div class="row collapse" >
                            <div class="small-8 columns" >
                                <span class="prefix" >Number of Siblings</span>
                            </div>
                            <div class="small-4 columns" >
                                <input type="number" ng-model="frmPdets.siblingsno" min="0" ng-readonly="appstate.s1 == 1"  placeholder="1" required/>
                            </div>
                        </div>
                    </div>
                    <div class="small-4 columns">
                        <div class="row collapse" >
                            <div class="small-8 columns" >
                                <span class="prefix" >Are you an orphan?</span>
                            </div>
                            <div class="small-4 columns" >
                                <select ng-model="frmPdets.orphan" ng-disabled="appstate.s1 == 1" >
                                    <option value="0" >Total</option>
                                    <option value="1" >Partial</option>
                                    <option value="2" >Neither</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="small-4 columns">
                        <div class="row collapse" >
                            <div class="small-5 columns" >
                                <span class="prefix" >Family Income</span>
                            </div>
                            <div class="small-7 columns" >
                                <select ng-model="frmPdets.income" ng-disabled="appstate.s1 == '1'">
                                    <option value="0" >Below 10,000</option>
                                    <option value="1" >10,000 - 25,000</option>
                                    <option value="2" >50,000 - 100,000</option>
                                    <option value="3" >100,000 - 300,000</option>
                                    <option value="4" >300,000 - 500,000 </option>
                                    <option value="5" >500,000 - 1,000,000</option>
                                    <option value="6" >Above 1,000,000</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row" >
                    <div class="small-6 columns" ng-if="frmPdets.orphan == '1' || frmPdets.orphan == '2'" >
                        <div class="row collapse" >
                            <div class="small-4 columns" >
                                <span class="prefix" >Parent 1 Occupation</span>
                            </div>
                            <div class="small-8 columns" >
                                <input type="text" placeholder="Farmer" ng-model="frmPdets.parent1" ng-readonly="appstate.s1 == 1" />
                            </div>
                        </div>
                    </div>
                    <div class="small-6 columns" ng-if="frmPdets.orphan == '2'" >
                        <div class="row collapse" >
                            <div class="small-4 columns" >
                                <span class="prefix" >Parent 2 Occupation</span>
                            </div>
                            <div class="small-8 columns" >
                                <input type="text" placeholder="Farmer" ng-model="frmPdets.parent2" ng-readonly="appstate.s1 == 1" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row" >
                    <div class="small-6 columns" >
                        <div class="row">
                            <div class="small-12">
                                <div class="row">
                                    <div class="small-5 columns">
                                        <label for="right-label" class="right">Do you live at home?</label>
                                    </div>
                                    <div class="small-7 columns">
                                        <select ng-model="frmPdets.livehome" ng-disabled="appstate.s1 == 1"  required="">
                                            <option value="0" >Yes</option>
                                            <option value="1" >No</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="small-6 columns" >
                        <div class="row collapse" >
                            <div class="small-4 columns" >
                                <span class="prefix" >Location</span>
                            </div>
                            <div class="small-8 columns" >
                                <input type="text" ng-model="frmPdets.location" ng-readonly="appstate.s1 == 1" placeholder="Nairobi" required/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row" ng-if="frmPdets.livehome == '1'" >
                    <div class="small-8 columns" >
                        <div class="row">
                            <div class="small-12">
                                <div class="row">
                                    <div class="small-5 columns">
                                        <label for="right-label" class="right">Explain why you do not live at home</label>
                                    </div>
                                    <div class="small-7 columns">
                                        <textarea ng-model="frmPdets.homereason"  ng-readonly="appstate.s1 == 1"></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row" ng-if ="frmPdets.siblingsno >= 1" >
                    <div class="small-8 columns" >
                        <div class="row">
                            <div class="small-12">
                                <div class="row">
                                    <div class="small-5 columns">
                                        <label for="right-label" class="right">Describe your siblings occupation</label>
                                    </div>
                                    <div class="small-7 columns">
                                        <textarea ng-model="frmPdets.siblingso" ng-readonly="appstate.s1 == 1"></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </fieldset>
            <button ng-if="appstate.s1 != 1"  class="button" type="submit" >Save</button>
            <button ng-if="appstate.s1 == 1 && !appstate.s7" class="button" type="button" ng-click="editState('1')" >Edit</button>
        </form>
    </div>
    <div class="academics-app" ng-if="elem1 == 'acad'" >
        <form ng-submit="addSchoolinfo()"  ng-if="elem2 == 'fees'" >
            <fieldset>
                <legend>School Information</legend>
                <div class="alert-box adets-alert" ></div>
                <div class="row myrow">
                    <div class="small-4 columns" >
                        <div class="row collapse" >
                            <div class="small-4 columns" >
                                <span class="prefix" >High School</span>
                            </div>
                            <div class="small-8 columns" >
                                <input type="text" ng-model="frmAdets.name" ng-readonly="appstate.s2 == 1" placeholder="Othaya Girls'" required/>
                            </div>
                        </div>
                    </div>
                    <div class="small-4 columns" >
                        <div class="row collapse" >
                            <div class="small-4 columns" >
                                <span class="prefix" >County</span>
                            </div>
                            <div class="small-8 columns" >
                                <input type="text" ng-model="frmAdets.county" ng-readonly="appstate.s2 == 1" placeholder="Nyeri" required/>
                            </div>
                        </div>
                    </div>
                    <div class="small-4 columns" >
                        <div class="row collapse" >
                            <div class="small-4 columns" >
                                <span class="prefix" >School Tel</span>
                            </div>
                            <div class="small-8 columns" >
                                <input type="text" placeholder="061-011-011" ng-model="frmAdets.tel" ng-readonly="appstate.s2 == 1" required/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row " >
                    <label>Select the options that describe your school</label>
                    <div class="small-4 columns">
                        <div class="row" >
                            <div class="small-6 columns" >
                                <input type="radio" ng-model="frmAdets.type1" ng-disabled="appstate.s2 == 1" name="type1" value="private" id="type11"><label for="type11">Private</label>
                            </div>
                            <div class="small-6 columns" >
                                <input type="radio" name="type1" ng-model="frmAdets.type1" ng-disabled="appstate.s2 == 1" value="public" id="type12"><label for="type12">Public</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row" >
                    <div class="small-4 columns">
                        <div class="row" >
                            <div class="small-6 columns" >
                                <input type="radio" name="type2" ng-model="frmAdets.type2" ng-disabled="appstate.s2 == 1" value="day" id="type21"><label for="type21">Day</label>
                            </div>
                            <div class="small-6 columns" >
                                <input type="radio" name="type2" ng-model="frmAdets.type2"ng-disabled="appstate.s2 == 1" value="boarding" id="type22"><label for="type22">Boarding</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row" >
                    <div class="small-4 columns">
                        <div class="row" >
                            <div class="small-6 columns" >
                                <input type="radio" name="type3" value="mixed" ng-model="frmAdets.type3" ng-disabled="appstate.s2 == 1" id="type31"><label for="type31">Mixed</label>
                            </div>
                            <div class="small-6 columns" >
                                <input type="radio" name="type3" value="single" ng-model="frmAdets.type3" id="type32" ng-disabled="appstate.s2 == 1"><label for="type32">Single Gender</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row myrow" >
                    <div class="small-6 columns" >
                        <div class="row collapse" >
                            <div class="small-6 columns" >
                                <span class="prefix" >Principal's Name</span>
                            </div>
                            <div class="small-6 columns" >
                                <input type="text" ng-model="frmAdets.pname" ng-readonly="appstate.s2 == 1" placeholder="Jane Doe" required/>
                            </div>
                        </div>
                    </div>
                    <div class="small-6 columns" >
                        <div class="row collapse" >
                            <div class="small-6 columns" >
                                <span class="prefix" >Principal's Mobile</span>
                            </div>
                            <div class="small-6 columns" >
                                <input type="text" placeholder="0712345678" ng-readonly="appstate.s2 == 1" ng-model="frmAdets.ptel" required/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row myrow" >
                    <div class="small-6 columns" >
                        <div class="row collapse" >
                            <div class="small-6 columns" >
                                <span class="prefix" >School's Mean score</span>
                            </div>
                            <div class="small-6 columns" >
                                <input type="number" step="0.10" min='0' ng-readonly="appstate.s2 == 1" ng-model="frmAdets.score" placeholder="0.0" required/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row myrow" >
                    <div class="small-6 columns" >
                        <div class="row">
                            <div class="small-12">
                                <div class="row">
                                    <div class="small-5 columns">
                                        <label for="right-label" class="right">Who paid your school fees?</label>
                                    </div>
                                    <div class="small-7 columns">
                                        <select ng-model="frmAdets.payfees" ng-disabled="appstate.s2 == 1">
                                            <option value="parents" >Parents</option>
                                            <option value="sponsor" >Sponsor</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="row myrow" >
                    <div class="small-6 columns" ng-if = "frmAdets.payfees == 'parents'" >
                        <div class="row"  >
                            <div class="small-12">
                                <div class="row">
                                    <div class="small-5 columns">
                                        <label for="right-label" class="right">Have you ever been sent home for fees?</label>
                                    </div>
                                    <div class="small-7 columns">
                                        <select ng-model="frmAdets.beenhome" ng-disabled="appstate.s2 == 1">
                                            <option value="yes" >Yes</option>
                                            <option value="no" >No</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="small-6 columns" ng-if = "frmAdets.payfees == 'sponsor'">
                        <div class="row collapse" >
                            <div class="small-6 columns" >
                                <span class="prefix" >Sponsor's Name</span>
                            </div>
                            <div class="small-6 columns" >
                                <input type="text" ng-model="frmAdets.sponsor" ng-readonly="appstate.s2 == 1" placeholder="XYZ Organisation" required/>
                            </div>
                        </div>
                    </div>
                    <div class="small-6 columns" ng-if = "frmAdets.payfees == 'sponsor'" >
                        <div class="row">
                            <div class="small-12">
                                <div class="row">
                                    <div class="small-5 columns">
                                        <label for="right-label" class="right">Describe how you got the scholarship</label>
                                    </div>
                                    <div class="small-7 columns">
                                        <textarea ng-model="frmAdets.desc" ng-readonly="appstate.s2 == 1"></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row" >
                    <div class="small-5 columns" >
                        <button ng-if="appstate.s2 != 1" class="button" type="submit" >Save</button>
                        <button ng-if="appstate.s2 == 1 && !appstate.s7" class="button" type="button" ng-click="editState('2')" >Edit</button>
                    </div>
                </div>

            </fieldset>
        </form>
        <form ng-submit="addTranscript()" ng-if="elem2 == 'transcript'">
            <fieldset>
                <legend>KCSE Transcript</legend>
                <p><b>Instructions :</b> Select <em>ONLY</em> the subjects sat for in your KCSE</p>
                <div class="alert-box transcript-alert "></div>
                <div class="row" >
                    <div class="small-6 columns" >
                        <div class = "row">
                            <div class="small-10" >
                                <div class="row collapse" >
                                    <div class="small-6 columns" >
                                        <span class="prefix" >English</span>
                                    </div>
                                    <div class="small-6 columns" >
                                        <select ng-model='frmgrades.eng' ng-options="g.grade for g in grades track by g.points" ng-disabled="appstate.s3 == 1" >

                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class = "row">
                            <div class="small-10" >
                                <div class="row collapse" >
                                    <div class="small-6 columns" >
                                        <span class="prefix" >Kiswahili</span>
                                    </div>
                                    <div class="small-6 columns" >
                                        <select ng-model='frmgrades.swa' ng-options="g.grade for g in grades track by g.points" ng-disabled="appstate.s3 == 1" >

                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class = "row">
                            <div class="small-10" >
                                <div class="row collapse" >
                                    <div class="small-6 columns" >
                                        <span class="prefix" >Mathematics</span>
                                    </div>
                                    <div class="small-6 columns" >
                                        <select ng-model='frmgrades.math' ng-options="g.grade for g in grades track by g.points" ng-disabled="appstate.s3 == 1" >

                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="small-6 columns" >
                        <div class = "row">
                            <div class="small-10" >
                                <div class="row collapse" >
                                    <div class="small-6 columns" >
                                        <span class="prefix" >Biology</span>
                                    </div>
                                    <div class="small-6 columns" >
                                        <select ng-model='frmgrades.bio' ng-options="g.grade for g in grades track by g.points" ng-disabled="appstate.s3 == 1" >

                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class = "row">
                            <div class="small-10" >
                                <div class="row collapse" >
                                    <div class="small-6 columns" >
                                        <span class="prefix" >Chemistry</span>
                                    </div>
                                    <div class="small-6 columns" >
                                        <select ng-model='frmgrades.chem' ng-options="g.grade for g in grades track by g.points" ng-disabled="appstate.s3 == 1">

                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class = "row">
                            <div class="small-10" >
                                <div class="row collapse" >
                                    <div class="small-6 columns" >
                                        <span class="prefix" >Physics</span>
                                    </div>
                                    <div class="small-6 columns" >
                                        <select ng-model='frmgrades.phy' ng-options="g.grade for g in grades track by g.points" ng-disabled="appstate.s3 == 1" >

                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="row">
                    <div class="small-6 columns" >
                        <div class = "row">
                            <div class="small-10" >
                                <div class="row collapse" >
                                    <div class="small-6 columns" >
                                        <span class="prefix" >Geography</span>
                                    </div>
                                    <div class="small-6 columns" >
                                        <select ng-model='frmgrades.geog' ng-options="g.grade for g in grades track by g.points" ng-disabled="appstate.s3 == 1" >

                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class = "row">
                            <div class="small-10" >
                                <div class="row collapse" >
                                    <div class="small-6 columns" >
                                        <span class="prefix" >History</span>
                                    </div>
                                    <div class="small-6 columns" >
                                        <select ng-model='frmgrades.hist' ng-options="g.grade for g in grades track by g.points" ng-disabled="appstate.s3 == 1" >

                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class = "row">
                            <div class="small-10" >
                                <div class="row collapse" >
                                    <div class="small-6 columns" >
                                        <span class="prefix" >Religious Studies</span>
                                    </div>
                                    <div class="small-6 columns" >
                                        <select ng-model='frmgrades.rs' ng-options="g.grade for g in grades track by g.points" ng-disabled="appstate.s3 == 1" >

                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="small-6 columns" >
                        <div class = "row">
                            <div class="small-10" >
                                <div class="row collapse" >
                                    <div class="small-6 columns" >
                                        <select ng-model="frmgrades.techsubj" ng-disabled="appstate.s3 == 1">
                                            <option>Computer Studies</option>
                                            <option>Business Studies</option>
                                            <option>Home Science</option>
                                            <option>Agriculture</option>
                                            <option>Music</option>
                                        </select>
                                    </div>
                                    <div class="small-6 columns" >
                                        <select ng-model='frmgrades.tech' ng-options="g.grade for g in grades track by g.points" ng-disabled="appstate.s3 == 1" >

                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row" >
                    <div class="small-4 columns" >
                        <div class="row collapse" >
                            <div class="small-6 columns" >
                                <span class="prefix" >Position</span>
                            </div>
                            <div class="small-6 columns" >
                                <input type="number" ng-model='frmgrades.pos' step="1" ng-readonly="appstate.s3 == 1" min="1" required=""/>
                            </div>
                        </div>
                    </div>
                    <div class="small-4 columns" >
                        <div class="row collapse" >
                            <div class="small-6 columns" >
                                <span class="prefix" >Class Population</span>
                            </div>
                            <div class="small-6 columns" >
                                <input type="number" step="1" ng-readonly="appstate.s3 == 1" ng-model='frmgrades.pop' min="1" required=""/>
                            </div>
                        </div>
                    </div>
                    <div class="small-4 columns" >
                        <div class="row collapse" >
                            <div class="small-6 columns" >
                                <span class="prefix" >Points</span>
                            </div>
                            <div class="small-6 columns" >
                                <input type="number" ng-model ="frmgrades.points" min="1"readonly/>
                            </div>
                        </div>
                    </div>
                </div>

                <button ng-if="appstate.s3 != 1" class="button" type="submit">Save</button>
                <button ng-if="appstate.s3 == 1 && !appstate.s7" class="button" type="button" ng-click="editState('3')" >Edit</button>
            </fieldset>
        </form>
        <div class="activities" ng-if="elem2 == 'acts'">
            <my-honors-form model='myhonorsdata'  >
                <fieldset>
                    <legend>Honors and Awards</legend>
                    <div class="alert-box honors-alert" ></div>
                    <div class="row" >
                        <div class='small-12 columns' >
                            <p>List <em>all</em> academic distinctions and honors you have received in secondary school.<br/>
                                Examples include merit certificates and district/provincial/national academic contest awards and certificates such as maths contests. <em>Note that all activities and accomplishments will be verified when EGF mentors visit your former schools.</em>
                            </p>
                            <p>Click 'Add' to add Honors and 'X' button to remove an element </p>
                            <my-honors-fields fields="myhonorsdata.fields" >
                                <my-honors-field ng-repeat="field in myhonorsdata.fields" >
                                    <div class="row" >
                                        <div class="small-1 columns">
                                            {{ $index + 1}}
                                        </div>
                                        <div class="small-2 columns" >
                                            <div class="row">
                                                <div class="small-12">
                                                    <div class="row">
                                                        <div class="small-5 columns">
                                                            <label for="right-label" class="right">Level</label>
                                                        </div>
                                                        <div class="small-7 columns">
                                                            <select ng-model='field.level' ng-options='l for l in level' ng-disabled="appstate.s4 == 1"  required></select>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>
                                        <div class="small-5 columns" >
                                            <div class="row">
                                                <div class="small-12">
                                                    <div class="row">
                                                        <div class="small-3 columns">
                                                            <label for="right-label" class="right">Title</label>
                                                        </div>
                                                        <div class="small-9 columns">
                                                            <input type="text" ng-model="field.pos" ng-readonly="appstate.s4 == 1" placeholder="Position 1 in SCIMA - Chemistry Contest" required/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="small-3 columns" >
                                            <div class="row">
                                                <div class="small-12">
                                                    <div class="row">
                                                        <div class="small-5 columns">
                                                            <label for="right-label" class="right">Level of Recognition</label>
                                                        </div>
                                                        <div class="small-7 columns">
                                                            <select ng-model='field.level2' ng-options='l for l in rec' ng-disabled="appstate.s4 == 1" required></select>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="small-1 columns myclose" >
                                            <a href class="close" ng-if="appstate.s4 != 1" ng-click="removeHonor(field)"  >&times;</a>
                                        </div>

                                    </div>
                                </my-honors-field>
                            </my-honors-fields>
                            <button type="button" ng-if="appstate.s4 != 1" class="button small" ng-click="addHonors()" >Add</button>
                        </div>
                    </div>
                    <button ng-if="appstate.s4 != 1" class="button" type="submit" >Save</button>
                    <button ng-if="appstate.s4 == 1 && !appstate.s7" class="button" type="button" ng-click="editState('4')" >Edit</button>
                </fieldset>            
            </my-honors-form>
            <volunteer-form model='voldata'>
                <fieldset>
                    <legend>Extracurricular Activities</legend>
                    <div class="vol-alert alert-box " ></div>
                    <div class="row" >
                        <div class="small-12 columns" >
                            <p>
                                List and explain <em>all</em> extracurricular, community, and volunteer activities you have participated in during high school.<br/>
                                Examples include participation in music, choir, drama, church activities, community service, sports and games, school clubs, Science Congress, debate, school leadership, etc.  Make sure to include details about specific events and major accomplishments, for example: "Position 2 in Provincial Science Congress, Western Province, Form 3, Biology Talk Category".<br/>
                                Also indicate if you held a leadership role in any activity, for example: Deputy School Captain or Secretary of the Wildlife Club.<br/>  <em>Note that all activities and accomplishments will be verified when EGF mentors visit your former schools.</em>
                            </p>
                            <p>Click 'Add' to add Honors and 'X' button to remove an element </p>
                        </div>
                    </div>
                    <volunteer-fields fields="voldata.fields">
                        <volunteer-field ng-repeat="field1 in voldata.fields">
                            <div class="row" >
                                <div class="small-1 columns">
                                    {{ $index + 1}}
                                </div>
                                <div class="small-3 columns" >
                                    <div class="row">
                                        <div class="small-12">
                                            <div class="row">
                                                <div class="small-5 columns">
                                                    <label for="right-label" class="right">Level</label>
                                                </div>
                                                <div class="small-7 columns">
                                                    <select ng-model='field1.lvl' ng-disabled="appstate.s5 == 1" ng-options='l for l in level' required></select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                                <div class="small-6 columns" >
                                    <div class="row">
                                        <div class="small-12">
                                            <div class="row">
                                                <div class="small-3 columns">
                                                    <label for="right-label" class="right">Description</label>
                                                </div>
                                                <div class="small-9 columns">
                                                    <textarea ng-model="field1.desc" ng-readonly="appstate.s5 == 1" required></textarea>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="small-1 columns myclose" >
                                    <a href class="close" ng-if="appstate.s5 != 1" ng-click="removeVol(field1)"  >&times;</a>
                                </div>

                            </div>
                        </volunteer-field>                    
                    </volunteer-fields>
                    <div class="row" >
                        <button type="button" ng-if="appstate.s5 != 1" class="button small" ng-click="addVol()" >Add</button>
                    </div>
                    <div class="row" >
                        <button ng-if="appstate.s5 != 1" type="submit" class="button" >Save</button>
                        <button ng-if="appstate.s5 == 1 && !appstate.s7" type="button" ng-click="editState('5')" class="button" >Edit</button>
                    </div>

                </fieldset>
            </volunteer-form>
        </div>
        <form ng-submit="addHols()" ng-if="elem2 == 'other'" >
            <fieldset>
                <legend >Holiday Activities</legend>
                <div class="alert-box hols-alert" ></div>
                <div class="row" >
                    <div class="small-12 columns" >
                        <div class="row">
                            <div class="small-12">
                                <div class="row">
                                    <div class="small-5 columns">
                                        <label for="right-label" class="right">Briefly describe what you did during your school holidays</label>
                                    </div>
                                    <div class="small-7 columns">
                                        <textarea ng-model="frmhols.hol" ng-readonly="appstate.s6 == 1"required=""></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row" >
                    <div class="small-12 columns" >
                        <div class="row">
                            <div class="small-12">
                                <div class="row">
                                    <div class="small-5 columns">
                                        <label for="right-label" class="right">Briefly describe your interest in studying abroad</label>
                                    </div>
                                    <div class="small-7 columns">
                                        <textarea ng-model="frmhols.interest" ng-readonly="appstate.s6 == 1" required=""></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <button ng-if="appstate.s6 != 1" class="button" type="submit" >Save</button>
                <button class="button" type="button" ng-if="appstate.s6 == 1 && !appstate.s7" ng-click="editState('6')" >Edit</button>
            </fieldset>
        </form>
    </div>
    <div class="essay-app" ng-if="elem1 == 'essays'">

        <fieldset>
            <legend>Essays</legend>
            <div class="alert-box essay-alert" ></div>
            <div class="row" >
                <div class="small-12 columns" >
                    <div class="essay" >
                        <form ng-submit="addEssay('1')" >
                            <b><i>Essay 1</i></b>
                            <p>Using the Common Application essay writing guide found here (link), write 500-600 words on ONE of the following prompts:</p>
                            <div class="row">
                                <div class="small-1 columns" >
                                    <input type="radio" name="es1" value="1a" ng-model="essay1.essayid" />
                                </div>
                                <div class="small-11 columns" >
                                    Some students have a background or story that is so central to their identity that they believe their application would be incomplete without it.If this sounds like you, then please share your story.
                                </div>
                            </div>
                            <div class="row">
                                <div class="small-1 columns" >
                                    <input type="radio" name="es1" value="1b" ng-model="essay1.essayid" />
                                </div>
                                <div class="small-11 columns" >
                                    Recount an incident or time when you experienced failure. How did it affect you, and what lessons did you learn?
                                </div>
                            </div>
                            <div class="row">
                                <div class="small-1 columns" >
                                    <input type="radio" name="es1" value="1c" ng-model="essay1.essayid" />
                                </div>
                                <div class="small-11 columns" >
                                    Reflect on a time when you challenged a belief or idea. What prompted you to act? Would you make the same decision again?
                                </div>
                            </div>
                            <div class="row">
                                <div class="small-1 columns" >
                                    <input type="radio" name="es1" value="1d" ng-model="essay1.essayid" />
                                </div>
                                <div class="small-11 columns" >
                                    Describe a place or environment where you are perfectly content. What do you do or experience there, and why is it meaningful to you?
                                </div>
                            </div>
                            <div class="row">
                                <div class="small-1 columns" >
                                    <input  type="radio" name="es1" value="1e" ng-model="essay1.essayid" />
                                </div>
                                <div class="small-11 columns" >
                                    Discuss an accomplishment or event, formal or informal, that marked your transition from childhood to adulthood within your culture, community, or family.
                                </div>
                            </div>
                            <input type="file" ng-if="!e1 && !appstate.s7" file-input="essay1.file" ng-model="essay1.file" required/>
                            <button class="medium button" ng-if="!e1" >Upload Essay 1</button>
                            <div class="old-essay" ng-if="e1" >
                                <div class="row" >
                                    <div class="small-5 columns" >
                                        <a ng-href="{{essay1.url}}" target="_blank" >Essay 1</a>
                                    </div> 

                                    <div class="small-1 columns  essay-close" ng-if="!appstate.s7" >
                                        <a href class="close" ng-click="deleteEssay('1')"  >&times;</a>
                                    </div>
                                </div>


                            </div>
                        </form>
                    </div>
                </div>

            </div>
            <div class="row" >
                <div class="small-12 columns" >
                    <div class="essay" >
                        <form ng-submit="addEssay('2')">
                            <i>Essay 2</i>
                            <p>Describe any unusual circumstances in your life or any particular challenges or hardships that you faced, either at home or at school while pursuing your education. (250-500 words)</p>
                            <input type="file" ng-if="!e2 && !appstate.s7" file-input="essay2.file" ng-model="essay2.file" required/>
                            <button class="medium button" ng-if="!e2" >Upload Essay 2</button>
                            <div class ="old-essay"  ng-if="e2" >
                                <div class="row" >
                                    <div class="small-5 columns" >
                                        <a ng-href="{{essay2.url}}" target="_blank" >Essay 2</a>
                                    </div> 

                                    <div class="small-1 columns  essay-close" ng-if="!appstate.s7">
                                        <a href class="close" ng-click="deleteEssay('2')"  >&times;</a>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="row" >
                <div class="small-12 columns" >
                    <div class="essay" >
                        <form ng-submit="addEssay('3')">
                            <i>Essay 3</i>
                            <p>
                                The best universities in the U.S. can receive over 30,000 applications for fewer than 3,000 spots in a given year.
                                To stand out among the 30,000 applicants, students must not only succeed academically, but must also distinguish themselves from their peers in other diverse ways.
                                Tell us something about you-for example, an interest, a talent, an accomplishment, a hobby, an experience, or something else-that you believe is totally unique to you that we won't find in any other application to the EGF college counseling program. (250-500 words)
                            </p>
                            <input type="file" ng-if="!e3"  ng-model="essay3.file" file-input="essay3.file" required />
                            <button class="medium button" ng-if="!e3 && !appstate.s7" >Upload Essay 3</button>
                            <div class ="old-essay"  ng-if="e3" >
                                <div class="row" >
                                    <div class="small-5 columns" >
                                        <a ng-href="{{essay3.url}}" target="_blank" >Essay 3</a>
                                    </div> 

                                    <div class="small-1 columns  essay-close" ng-if="!appstate.s7" >
                                        <a href class="close"  ng-click="deleteEssay('3')"  >&times;</a>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </fieldset>

    </div>
    <div class="submit" ng-if="elem1 == 'submit'" >
        <form ng-submit="submitApp()">
            <fieldset>
                <div class="alert-box submit-alert" ></div>
                <p>You will only be allowed to submit the application once!</p>
                <p class="warning" >All changes made and not submitted in their respective sections will not be committed by submitting the application</p>
                <button  ng-if="!appstate.s7" type="submit" class="button large-12" >Submit</button>
            </fieldset>
        </form>

    </div>
</div>


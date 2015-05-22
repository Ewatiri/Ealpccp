<%-- 
    Document   : home
    Created on : Jan 29, 2015, 9:45:23 AM
    Author     : eva
--%>


<div class="home-content" ng-init="init()" >
    <div class="old-scholar row" ng-if="showsignin" >
        <div class="small-12 columns " >

            <form class="signin-form" ng-submit="login()" >
                <p>Welcome back to EALP College Counseling Portal!</p>
                <div class="alert-box login-alert" ></div>
                <div class="" ng-if="lg">Account created! Login to access the portal. </div>
                <div class="row collapse" >
                    <div class="small-3 columns" >
                        <span class ="prefix" >Email Address</span>
                    </div>
                    <div class="small-9 columns" >
                        <input type="email"  placeholder="janedoe@example.com" ng-model="myuser.email"  required/>	
                    </div>
                </div>
                <div class="row collapse" >
                    <div class="small-3 columns" >
                        <span class ="prefix" >Password</span>
                    </div>
                    <div class="small-9 columns" >
                        <input type="password"  placeholder="Password" ng-model="myuser.pass"  required/>	
                    </div>
                </div>
                <div class="row collapse" >
                    <div class="small-3 columns">
                        <span class="prefix" >Account type</span>
                    </div>
                    <div  >
                        <select ng-model="myuser.type"  class="small-9 columns" required>
                            <option value="">--</option>
                            <option value="0" >Scholar</option>
                            <option value="1">Counselor</option>
                        </select>
                    </div>
                </div>
                <button class="button">Sign in!</button>
                <p ng-if="state == 1"  >Do not have an account? Sign up <a href ng-click="showSignin()" >here</a>!</p>
                <p ng-if="state == 1" >Got an access code? Create an account <a href ng-click="showAccess()" >here</a>!</p>
                <a ng-href="#/reset" class="button tiny " >Forgot Password</a>
            </form>

        </div>
    </div>
    <div class="accesscode row" ng-if="showaccesscode" >
        <div class="small-12 columns" >
            <form ng-submit = "accessCode()">
                <p>Create Account using an access code</p>
                <div class="alert-box accesscode-alert " >  </div>
                <div class="row collapse" >
                    <div class="small-3 columns ">
                        <span class="prefix" >Email Address</span>
                    </div>
                    <div class="small-9 columns" >
                        <input type="email" ng-model="access.email" placeholder="Email" required/>
                    </div>
                </div>
                <div class="row collapse" >
                    <div class="small-3 columns ">
                        <span class="prefix" >Access Code</span>
                    </div>
                    <div class="small-9 columns" >
                        <input type="text" ng-model="access.code" placeholder="Access code" required/>
                    </div>
                </div>
                <button type="submit" class="button" >Create Account!</button>
                <p>Already have an account? Sign in <a href ng-click="showSignin()">here</a>!</p>
                <p >Do not have an account? Sign up <a href ng-click="showSignup()" >here</a>!</p>
            </form>

        </div>
    </div>
    <div class="new-scholar row" ng-if="!showsignin && !showaccesscode && !showfirstlogin" >
        <div class="small-12 columns" >
            <p>Welcome new User!</p>
            <form class="signup-form1" >
                <div class="alert-box newscholar-alert" ></div>
                <div class="row">
                    <div class="small-2 columns">
                        <label for="stype" class="right" >I am </label>
                    </div>
                    <div class="small-10 columns">
                        <select id="stype" ng-model="frmsignup.me" >
                            <option value = "1">an Invited Scholar</option>
                            <option value = "2">a Wings to Fly Scholar</option>
                            <option value = "3">an Equity Bank Staff Relation</option>
                            <option value = "4">an EALP Scholar</option>
                        </select>
                    </div>
                </div>
            </form>
            <form class="invited-form" ng-if="frmsignup.me == '1'" ng-submit="addScholar()">
                <div class="row collapse" >
                    <div class="small-3 columns ">
                        <span class="prefix" >Full Name</span>
                    </div>
                    <div class="small-9 columns" >
                        <input type="text" placeholder="Jane Doe" ng-model="scholar.name1"  required/>
                    </div>
                </div>
                <div class="row collapse" >
                    <div class="small-3 columns ">
                        <span class="prefix" >Email</span>
                    </div>
                    <div class="small-9 columns" >
                        <input type="email" placeholder="janedoe@example.com" ng-model="scholar.email1" required/>
                    </div>
                </div>
                <div class="row collapse" >
                    <div class="small-3 columns ">
                        <span class="prefix" >High School</span>
                    </div>
                    <div class="small-9 columns" >
                        <input type="text" placeholder="XYZ High School" ng-model="scholar.sch1" required/>
                    </div>
                </div>
                <button class="button" type="submit" >Get Access Code!</button>

            </form>
            <form class="w2f-form" ng-if="frmsignup.me == '2'" ng-submit="addScholar()">
                <div class="row collapse" >
                    <div class="small-3 columns ">
                        <span class="prefix" >Full Name</span>
                    </div>
                    <div class="small-9 columns" >
                        <input type="text" placeholder="Jane Doe" ng-model="scholar.name2" required/>
                    </div>
                </div>
                <div class="row collapse" >
                    <div class="small-3 columns ">
                        <span class="prefix" >Email</span>
                    </div>
                    <div class="small-9 columns" >
                        <input type="email" placeholder="janetdoe@example.com" ng-model="scholar.email2" required/>
                    </div>
                </div>
                <div class="row collapse" >
                    <div class="small-3 columns ">
                        <span class="prefix" >High School</span>
                    </div>
                    <div class="small-9 columns" >
                        <input type="text" placeholder="XYZ High School" ng-model="scholar.sch2" required/>
                    </div>
                </div>
                <div class="row collapse" >
                    <div class="small-3 columns ">
                        <span class="prefix" >Reporting Branch</span>
                    </div>
                    <div class="small-9 columns" >
                        <input type="text" placeholder="Nakuru Kenyatta Avenue" ng-model="scholar.branch2" required/>
                    </div>
                </div>
                <button class="button" type="submit" >Get Access Code</button>

            </form>
            <form class="staff-form" ng-if="frmsignup.me == '3'" ng-submit="addScholar()">
                <div class="row collapse" >
                    <div class="small-3 columns">
                        <span class="prefix" >Full Name</span>
                    </div>
                    <div class="small-9 columns" >
                        <input type="text" placeholder="John Smith Doe" ng-model="scholar.name3" required/>
                    </div>
                </div>
                <div class="row collapse" >
                    <div class="small-3 columns ">
                        <span class="prefix" >Email</span>
                    </div>
                    <div class="small-9 columns" >
                        <input type="email" placeholder="janetdoe@example.com" ng-model="scholar.email3" required/>
                    </div>
                </div>
                <div class="row collapse" >
                    <div class="small-3 columns">
                        <span class="prefix" >Staff Name</span>
                    </div>
                    <div class="small-9 columns" >
                        <input type="text" placeholder="Jane Doe" ng-model="scholar.staffname" required/>
                    </div>
                </div>
                <div class="row collapse" >
                    <div class="small-3 columns">
                        <span class="prefix" >Job Title</span>
                    </div>
                    <div class="small-9 columns" >
                        <input type="text" placeholder="Relationship Officer - Account Opening" ng-model="scholar.job" required/>
                    </div>
                </div>
                <div class="row collapse" >
                    <div class="small-3 columns">
                        <span class="prefix" >Branch</span>
                    </div>
                    <div class="small-9 columns" >
                        <input type="text" placeholder="Nakuru Kenyatta Avenue Branch" ng-model="scholar.branch3" required/>
                    </div>
                </div>
                <div class="row collapse" >
                    <div class="small-3 columns">
                        <span class="prefix" >PF Number</span>
                    </div>
                    <div class="small-9 columns" >
                        <input type="text" placeholder="JD1111" ng-model="scholar.pf1" required/>
                    </div>
                </div>
                <div class="row collapse" >
                    <div class="small-3 columns">
                        <span class="prefix" >Staff Email</span>
                    </div>
                    <div class="small-9 columns" >
                        <input type="email" placeholder="jane.doe@equitybank.co.ke" ng-model="scholar.staffemail" required/>
                    </div>
                </div>
                <div class="row collapse" >
                    <div class="small-3 columns">
                        <span class="prefix" >Mobile Phone</span>
                    </div>
                    <div class="small-9 columns" >
                        <input type="text" placeholder="+254 712 345 678" ng-model="scholar.mobile" required/>
                    </div>
                </div>
                <div class="row collapse" >
                    <div class="small-3 columns">
                        <span class="prefix" >Staff Avaya</span>
                    </div>
                    <div class="small-9 columns" >
                        <input type="text" placeholder="8080" ng-model="scholar.avaya" required/>
                    </div>
                </div>
                <div class="row collapse" >
                    <div class="small-3 columns">
                        <span class="prefix" >Relation</span>
                    </div>
                    <div class="small-9 columns" >
                        <input type="text" placeholder="Aunt" ng-model="scholar.relation" required/>
                    </div>
                </div>
                <button class="button" type="submit" >Get Access Code</button>

            </form>
            <form class="ealp-form" ng-submit="addealpScholar()" ng-if="frmsignup.me == '4'">
                <div class="alert-box ealp-alert" ></div>
                <div class="row collapse" >
                    <div class="small-3 columns" >
                        <span class="prefix" >Portal User Name</span>
                    </div>
                    <div class="small-9 columns" >
                        <input type="text" ng-model="ealp.pf" placeholder="01234" required/>
                    </div>
                </div>
                <div class="row collapse" >
                    <div class="small-3 columns" >
                        <span class="prefix" >Email</span>
                    </div>
                    <div class="small-9 columns" >
                        <input type="email" ng-model="ealp.email" placeholder="jane@example.com" required/>
                    </div>
                </div>
                <button class="button" type="submit" >Create Account!</button>

            </form>
            <p>Already have an account? Sign in <a href ng-click="showSignin()">here</a>!</p>
            <p >Got an access code? Create an account <a href ng-click="showAccess()" >here</a>!</p>
        </div>
    </div>
    <div class="first-login" ng-if="showfirstlogin" >
        <form ng-submit="createScholar()" >
            <p>Account Details</p>
            <div class="alert-box nscholar-alert" ></div>
            <div class="row collapse" >
                <div class="small-3 columns" >
                    <span class="prefix" >Email Address</span>
                </div>
                <div class="small-9 columns" >
                    <input type="email" ng-model="newscholar.email"  placeholder="janedoe@example.com" required/>
                </div>
            </div>
            <div class="row collapse" >
                <div class="small-3 columns" >
                    <span class="prefix" >Password</span>
                </div>
                <div class="small-9 columns" >
                    <input type="password" ng-model="newscholar.pass" placeholder="Password" required/>
                </div>
            </div>
            <div class="row collapse" >
                <div class="small-3 columns" >
                    <span class="prefix" >Confirm Password</span>
                </div>
                <div class="small-9 columns" >
                    <input type="password" ng-model="newscholar.pass2" placeholder="Password" required/>
                </div>
            </div>
            <button type="submit" class="button" >Save</button>
        </form>
    </div>
</div>

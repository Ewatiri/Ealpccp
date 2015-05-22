<%-- 
    Document   : account
    Created on : Feb 4, 2015, 10:22:05 AM
    Author     : eva
--%>

<div class="myacc-panel" > 
    
    <div class="row" >
        <form ng-submit = "changePass()">
            <fieldset>
                <legend>Change Password</legend>
                <div class="alert-box changepass-alert" ></div>
                <div class="row collapse" >
                    <div class="small-4 columns" >
                        <span class="prefix" >Current Password</span>
                    </div>
                    <div class="small-8 columns" >
                        <input type="password" placeholder = "Current Password" ng-model="account.cpass"  required/>
                    </div>
                </div>
                <div class="row collapse" >
                    <div class="small-4 columns" >
                        <span class="prefix" >New Password</span>
                    </div>
                    <div class="small-8 columns" >
                        <input type="password" placeholder = "New Password" ng-model="account.pass1"  required/>
                    </div>
                </div>
                <div class="row collapse" >
                    <div class="small-4 columns" >
                        <span class="prefix" >Confirm Password</span>
                    </div>
                    <div class="small-8 columns" >
                        <input type="password" placeholder = "Confirm Password" ng-model="account.pass2"  required/>
                    </div>
                </div>
                <button type="submit" class="button center-btn" >Change Password</button>
            </fieldset>

        </form>
    </div>
</div>

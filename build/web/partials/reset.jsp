<%-- 
    Document   : reset
    Created on : Mar 16, 2015, 11:47:13 AM
    Author     : eva
--%>
<div class="row" >
    <div class="small-12 columns" >
        
        <form class="signin-form" ng-submit="resetPassword()" >
            
            <fieldset>
                <legend>Reset Password</legend>
                <div class="alert-box reset-alert" ></div>
                 <div ng-if="lg1" > <a ng-href="/Ealpccp" class="button large-12 radius"  >Login</a> </div>   
                <div class="row collapse" >
                    <div class="small-3 columns" >
                        <span class ="prefix" >Email Address</span>
                    </div>
                    <div class="small-9 columns" >
                        <input type="email"  placeholder="janedoe@gmail.com" ng-model="reset.email"  required/>	
                    </div>
                </div>
                <div class="row collapse" >
                    <div class="small-3 columns" >
                        <span class ="prefix" >Account Type</span>
                    </div>
                    <div  >
                        <select required="" ng-model="reset.type"  class="small-9 columns" >
                            <option value="0" >Scholar</option>
                            <option value="1" >Counselor</option>
                        </select>
                    </div>
                </div>
                <button class="button radius small" type="submit" >Reset Password</button>
            </fieldset> 

        </form>
    </div>
</div>


<%-- 
    Document   : addmentor
    Created on : Mar 11, 2015, 9:02:35 PM
    Author     : eva
--%>
<div class="row" >
    <div class="small-12 columns" >
        <form ng-submit="addUser()" >
            <fieldset>
                <legend>Add user</legend>
                <div class="alert-box addmentor-alert" ></div>
                <div class="row" >
                    <div class="small-6 columns" >
                        <div class="row collapse" >
                            <div class="small-4 columns" >
                                <span class="prefix" >First Name</span>
                            </div>
                            <div class="small-8 columns" >
                                <input type="text" placeholder = "Jane" ng-model="mentor.fname" required/>
                            </div>
                        </div>
                    </div>
                    <div class="small-6 columns" >
                        <div class="row collapse" >
                            <div class="small-4 columns" >
                                <span class="prefix" >Surname</span>
                            </div>
                            <div class="small-8 columns" >
                                <input type="text" placeholder = "Doe" ng-model="mentor.surname"required/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row" >
                    <div class="small-6 columns" >
                        <div class="row collapse" >
                            <div class="small-4 columns" >
                                <span class="prefix" >Email</span>
                            </div>
                            <div class="small-8 columns" >
                                <input type="email" placeholder = "janedoe@example.com" ng-model="mentor.email" required/>
                            </div>
                        </div>
                    </div>
                    <div class="small-6 columns" >
                        <div class="row collapse" >
                            <div class="small-4 columns" >
                                <span class="prefix" >User Class</span>
                            </div>
                            <div   >
                                <select ng-model="mentor.class" class="small-8 columns" required>
                                    <option value="1" >Administrator</option>
                                    <option value="2" >Standard</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <button class="center-btn" >Add User</button>
            </fieldset>
        </form>
    </div>
</div>

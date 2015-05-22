<%-- 
    Document   : manageannoun
    Created on : Feb 7, 2015, 7:00:26 PM
    Author     : eva
--%>

<div class="row" >
    <div class="small-12 columns" >
        <form ng-submit="addAnnoun()" >
            <fieldset>
                <legend>Add Announcement</legend>
                <div class="alert-box announ-alert" ></div>
                <div class="row collapse" >
                    <div class="small-4 columns" >
                        <span class="prefix" >Announcement Title</span>
                    </div>
                    <div class="small-8 columns" >
                        <input type="text" placeholder = "Upcoming Deadlines" ng-model="announ.title" required/>
                    </div>
                </div>
                <div class="row collapse" >
                    <div class="small-4 columns" >
                        <span class="prefix" >Announcement Description</span>
                    </div>
                    <div class="small-8 columns" >
                        <textarea placeholder="Description" rows="5" ng-model="announ.desc"  required=""></textarea>
                    </div>
                </div>
                <button class="center-btn" type="submit">Add</button>
            </fieldset> 
        </form>
    </div>
</div>
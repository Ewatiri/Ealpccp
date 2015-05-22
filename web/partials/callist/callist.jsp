<%-- 
    Document   : callist
    Created on : Mar 4, 2015, 10:47:31 PM
    Author     : eva
--%>

<div class="row" >
    <div class ="row">
        <div class="small-12 columns " >
            <form ng-submit="addList()" >
                <fieldset>
                    <legend>Add New Call list</legend>
                    <div class="alert-box callist-alert" ></div>
                    <div class="row collapse" >
                        <div class="small-4 columns" >
                            <span class="prefix" >Title</span>
                        </div>
                        <div class="small-8 columns" >
                            <input type="text" ng-model="callist.title"  placeholder = "Call list title" required/>
                        </div>
                    </div>
                    <div class="row collapse" >
                        <div class="small-4 columns" >
                            <span class="prefix" >Description</span>
                        </div>
                        <div class="small-8 columns" >
                            <textarea required="" ng-model="callist.desc" placeholder="Description"></textarea>
                        </div>
                    </div>
                    <button class="button center-btn" >Save</button>
                </fieldset>
            </form>
        </div>
    </div>

    

</div>
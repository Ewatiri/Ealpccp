<%-- 
    Document   : addevent
    Created on : Mar 3, 2015, 4:23:39 PM
    Author     : eva
--%>
<div class="row" >
    <div class="small-12 columns" >
        <form ng-submit="addEvent()">
            <fieldset>
                <legend>Create new event</legend>
                <div class="alert-box event-alert" ></div>
                <div class="row collapse" >
                    <div class="small-4 columns" >
                        <span class="prefix" >Event</span>
                    </div>
                    <div class="small-8 columns" >
                        <input type="text" ng-model="event.title" placeholder = "Interaction Session" required/>
                    </div>
                </div>
                <div class="row collapse" >
                    <div class="small-4 columns" >
                        <span class="prefix" >Description</span>
                    </div>
                    <div class="small-8 columns" >
                        <textarea  rows="4" ng-model="event.desc" required=""></textarea>
                    </div>
                </div>
                <div class="row" >
                    <div class="small-6 columns" >
                        <div class="row collapse" >
                            <div class="small-4 columns" >
                                <span class="prefix" >Date</span>
                            </div>
                            <div class="small-8 columns" >
                                <quick-datepicker ng-model="etime" date-format="d/M/yyyy"   placeholder="Select Date and time"></quick-datepicker>
                                <!--<input type="text" ng-model="event.date" placeholder = "01/01/2015" required/>-->
                            </div>
                        </div>
                    </div>
                    <div class="small-6 columns" >
                        <div class="row collapse" >
                            <div class="small-4 columns" >
                                <span class="prefix" >Time</span>
                            </div>
                            <div class="small-8 columns" >
                                <input type="text" ng-model="event.time" placeholder = "07:00" readonly="" required/>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="row collapse" >
                    <div class="small-4 columns" >
                        <span class="prefix" >Venue</span>
                    </div>
                    <div class="small-8 columns" >
                        <input type="text" ng-model="event.venue" placeholder = "Equity Centre" required/>
                    </div>
                </div>
                <button class="button center-btn "  >Add</button>
            </fieldset>
        </form>
    </div>
</div>

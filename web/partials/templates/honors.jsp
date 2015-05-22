<%-- 
    Document   : honors
    Created on : Feb 7, 2015, 11:20:37 AM
    Author     : eva
--%>

<div id="honors" >
    <div class="row" >
        <div class="small-3 columns" >
            <div class="row">
                <div class="small-12">
                    <div class="row">
                        <div class="small-5 columns">
                            <label for="right-label" class="right">Level</label>
                        </div>
                        <div class="small-7 columns">
                            <select ng-model='lvl1' ng-options='l for l in level' ></select>
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
                            <input type="text" placeholder="Position 1 in SCIMA - Chemistry Contest" />
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
                            <select ng-model='lvl2' ng-options='l for l in rec' ></select>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="small-1 columns myclose" >
           <a href class="close">&times;</a>
        </div>
         
    </div>

</div>

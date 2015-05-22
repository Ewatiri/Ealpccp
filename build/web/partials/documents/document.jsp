<%-- 
    Document   : document
    Created on : Mar 25, 2015, 9:04:33 AM
    Author     : eva
--%>
<div class="cd-tabs" ng-init="getDocStatus()">
    <nav>
        <ul class="cd-tabs-navigation">
            <li><a  data-content="mydocuments" class="selected" ng-href cody-house-tabs ><i class="fa fa-user icon"></i>My Documents</a></li>
            <li><a data-content="dochist"ng-href cody-house-tabs ><i class="fa fa-pencil icon"></i>Document Status</a></li>

        </ul> 
    </nav>
    <ul class="cd-tabs-content">
        <li data-content="mydocuments" class="selected">
            <div class="row" >
                <div class="small-12 columns" >
                    <div ng-if="commonapp" >
                        <div class="document-alert alert-box" ></div>
                        <form ng-submit="uploadDocument('1')">
                            <fieldset>
                                <legend>Teacher Recommendation 1</legend>

                                <div class = "row collapse" >
                                    <div class="small-4 columns" >
                                        <span class="prefix" >Document</span>
                                    </div>
                                    <div class="small-8 columns">
                                        <input type="file" file-input="doc1.file"  required ng-model="doc1.file" />
                                    </div>
                                </div>
                                <button  class="small button radius" type="submit" >Upload Document</button>
                            </fieldset>

                        </form>
                        <form ng-submit="uploadDocument('2')">
                            <fieldset>
                                <legend>Teacher Recommendation 2</legend>

                                <div class="row collapse" >
                                    <div class="small-4 columns" >
                                        <span class="prefix" >Document</span>
                                    </div>
                                    <div class="small-8 columns">
                                        <input type="file" file-input="doc2.file"  required ng-model="doc2.file" />
                                    </div>
                                </div>
                                <button class="small button radius" type="submit" >Upload Document</button>
                            </fieldset>

                        </form>
                        <form ng-submit="uploadDocument('3')">
                            <fieldset>
                                <legend>Counselor Recommendation</legend>
                                <div class="row collapse" >
                                    <div class="small-4 columns" >
                                        <span class="prefix" >Document</span>
                                    </div>
                                    <div class="small-8 columns">
                                        <input type="file" file-input="doc3.file"  required ng-model="doc3.file" />
                                    </div>
                                </div>

                                <button class="small button radius" type="submit" >Upload Document</button>
                            </fieldset>

                        </form>
                    </div>
                </div>
            </div>
        </li>
        <li data-content="dochist" >
            <div class="alert-box dochist-alert " ></div>
            <div class="row" >
                <div class="small-12 columns" ng-repeat="k in fback" >
                    <fieldset>
                        <legend ng-if="k.type == '0'" >TR 1</legend>
                        <legend ng-if="k.type == '1'" >TR 2</legend>
                        <legend ng-if="k.type == '2'" >CR </legend>
                        <div class="row" >
                            <div class="small-4 columns" >
                                <p ng-if="k.status=='0'" >Not Reviewed</p>
                                <p ng-if="k.status=='1'" >Reviewed</p>
                                <p ng-if="k.status=='2'" >Complete</p>
                            </div>
                            <div class="small-3 columns" >
                                <a ng-href="{{k.doc}}" target="_blank" ><i class="fa fa-download icon" ></i>Download Document</a>
                            </div>
                        </div>
                        
                    </fieldset>
                </div>
            </div>
            
        </li>
    </ul>
</div>


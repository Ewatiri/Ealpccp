<%-- 
    Document   : dashboard
    Created on : Mar 26, 2015, 10:37:26 AM
    Author     : eva
--%>
<div class="row" >
    <div class="small-12 columns" >
        <div class="accordion1" id="accordion1" chris-accordion ng-transclude >
            <dl>
                <div ng-repeat="coll in mycolleges" >
                    <dt><a class="accordionTitle" ng-href>{{ coll.name}}</a></dt>
                    <dd class="accordionItem accordionItemCollapsed">
                        <div class="row" >
                            <div class="small-6 columns" >
                                <button class="small secondary" type="button" >Get Progress</button>
                            </div>
                            <div class="small-6 columns" >
                                <button class="small secondary" type="button" >Get detailed Progress</button>
                            </div>
                        </div>
                        
                    </dd>
                </div>

            </dl>
        </div>
    </div>
</div>

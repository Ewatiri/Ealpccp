package com.ealp.entities.two;

import com.ealp.entities.CallFeedback;
import com.ealp.entities.CallList;
import com.ealp.entities.Scholar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-19T13:51:48")
@StaticMetamodel(CallFeedback.class)
public class CallFeedback_ { 

    public static volatile SingularAttribute<CallFeedback, Integer> id;
    public static volatile SingularAttribute<CallFeedback, String> feedback;
    public static volatile SingularAttribute<CallFeedback, Scholar> sid;
    public static volatile SingularAttribute<CallFeedback, Integer> status;
    public static volatile SingularAttribute<CallFeedback, CallList> cid;

}
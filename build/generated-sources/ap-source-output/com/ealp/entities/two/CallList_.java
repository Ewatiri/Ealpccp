package com.ealp.entities.two;

import com.ealp.entities.CallFeedback;
import com.ealp.entities.CallList;
import com.ealp.entities.Mentor;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-19T13:51:48")
@StaticMetamodel(CallList.class)
public class CallList_ { 

    public static volatile SingularAttribute<CallList, Integer> lastupdate;
    public static volatile SingularAttribute<CallList, String> cycle;
    public static volatile SingularAttribute<CallList, String> title;
    public static volatile SingularAttribute<CallList, String> description;
    public static volatile SingularAttribute<CallList, Mentor> createdby;
    public static volatile SingularAttribute<CallList, Date> date;
    public static volatile ListAttribute<CallList, CallFeedback> callFeedbackList;
    public static volatile SingularAttribute<CallList, Integer> cid;

}
package com.ealp.entities.one;

import com.ealp.entities.Attendance;
import com.ealp.entities.Event;
import com.ealp.entities.Mentor;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-09T11:18:50")
@StaticMetamodel(Event.class)
public class Event_ { 

    public static volatile SingularAttribute<Event, Date> timestamp;
    public static volatile SingularAttribute<Event, String> cycle;
    public static volatile SingularAttribute<Event, String> title;
    public static volatile SingularAttribute<Event, Mentor> postedby;
    public static volatile SingularAttribute<Event, Date> etime;
    public static volatile SingularAttribute<Event, Date> edate;
    public static volatile SingularAttribute<Event, String> description;
    public static volatile ListAttribute<Event, Attendance> attendanceList;
    public static volatile SingularAttribute<Event, String> venue;
    public static volatile SingularAttribute<Event, Integer> eid;

}
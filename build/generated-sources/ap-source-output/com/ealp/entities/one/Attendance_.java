package com.ealp.entities.one;

import com.ealp.entities.Attendance;
import com.ealp.entities.Event;
import com.ealp.entities.Scholar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-09T11:18:50")
@StaticMetamodel(Attendance.class)
public class Attendance_ { 

    public static volatile SingularAttribute<Attendance, Integer> id;
    public static volatile SingularAttribute<Attendance, Scholar> sid;
    public static volatile SingularAttribute<Attendance, Event> eid;

}
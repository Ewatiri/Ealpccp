package com.ealp.entities.two;

import com.ealp.entities.Announcement;
import com.ealp.entities.Mentor;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-09T11:18:50")
@StaticMetamodel(Announcement.class)
public class Announcement_ { 

    public static volatile SingularAttribute<Announcement, String> title;
    public static volatile SingularAttribute<Announcement, String> description;
    public static volatile SingularAttribute<Announcement, Integer> anid;
    public static volatile SingularAttribute<Announcement, Mentor> createdby;
    public static volatile SingularAttribute<Announcement, Mentor> editedby;
    public static volatile SingularAttribute<Announcement, Date> ctime;

}
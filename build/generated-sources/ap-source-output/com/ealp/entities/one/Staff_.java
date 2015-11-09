package com.ealp.entities.one;

import com.ealp.entities.InvitedScholar;
import com.ealp.entities.Staff;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-09T11:18:50")
@StaticMetamodel(Staff.class)
public class Staff_ { 

    public static volatile SingularAttribute<Staff, Integer> id;
    public static volatile SingularAttribute<Staff, String> title;
    public static volatile SingularAttribute<Staff, InvitedScholar> invitedScholar;
    public static volatile SingularAttribute<Staff, String> email;
    public static volatile SingularAttribute<Staff, String> avaya;
    public static volatile SingularAttribute<Staff, String> relation;
    public static volatile SingularAttribute<Staff, String> sname;
    public static volatile SingularAttribute<Staff, String> pfno;
    public static volatile SingularAttribute<Staff, String> mobile;

}
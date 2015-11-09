package com.ealp.entities.two;

import com.ealp.entities.InvitedScholar;
import com.ealp.entities.Mentor;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-09T11:18:50")
@StaticMetamodel(InvitedScholar.class)
public class InvitedScholar_ { 

    public static volatile SingularAttribute<InvitedScholar, Integer> id;
    public static volatile SingularAttribute<InvitedScholar, String> cycle;
    public static volatile SingularAttribute<InvitedScholar, Mentor> approvedby;
    public static volatile SingularAttribute<InvitedScholar, String> school;
    public static volatile SingularAttribute<InvitedScholar, String> email;
    public static volatile SingularAttribute<InvitedScholar, Integer> status;
    public static volatile SingularAttribute<InvitedScholar, String> branch;
    public static volatile SingularAttribute<InvitedScholar, String> code;
    public static volatile SingularAttribute<InvitedScholar, Integer> type;
    public static volatile SingularAttribute<InvitedScholar, String> fullname;

}
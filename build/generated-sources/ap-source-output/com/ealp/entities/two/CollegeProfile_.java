package com.ealp.entities.two;

import com.ealp.entities.College;
import com.ealp.entities.CollegeProfile;
import com.ealp.entities.Scholar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-19T13:51:48")
@StaticMetamodel(CollegeProfile.class)
public class CollegeProfile_ { 

    public static volatile SingularAttribute<CollegeProfile, Integer> id;
    public static volatile SingularAttribute<CollegeProfile, String> cycle;
    public static volatile SingularAttribute<CollegeProfile, Scholar> sid;
    public static volatile SingularAttribute<CollegeProfile, String> decisionType;
    public static volatile SingularAttribute<CollegeProfile, Integer> decision;
    public static volatile SingularAttribute<CollegeProfile, College> collid;

}
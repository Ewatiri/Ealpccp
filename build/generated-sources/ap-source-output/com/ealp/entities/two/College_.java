package com.ealp.entities.two;

import com.ealp.entities.College;
import com.ealp.entities.CollegeEssay;
import com.ealp.entities.CollegeProfile;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-09T11:18:50")
@StaticMetamodel(College.class)
public class College_ { 

    public static volatile ListAttribute<College, CollegeProfile> collegeProfileList;
    public static volatile ListAttribute<College, CollegeEssay> collegeEssayList;
    public static volatile SingularAttribute<College, String> rdeadline;
    public static volatile SingularAttribute<College, String> name;
    public static volatile SingularAttribute<College, Integer> mcf;
    public static volatile SingularAttribute<College, Integer> commonapp;
    public static volatile SingularAttribute<College, String> edeadline;
    public static volatile SingularAttribute<College, Integer> collid;

}
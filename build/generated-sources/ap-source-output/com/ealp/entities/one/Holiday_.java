package com.ealp.entities.one;

import com.ealp.entities.Holiday;
import com.ealp.entities.Scholar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-09T11:18:50")
@StaticMetamodel(Holiday.class)
public class Holiday_ { 

    public static volatile SingularAttribute<Holiday, Integer> sid;
    public static volatile SingularAttribute<Holiday, String> hol;
    public static volatile SingularAttribute<Holiday, String> interest;
    public static volatile SingularAttribute<Holiday, Scholar> scholar;

}
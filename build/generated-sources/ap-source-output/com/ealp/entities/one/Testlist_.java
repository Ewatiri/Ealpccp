package com.ealp.entities.one;

import com.ealp.entities.Scholar;
import com.ealp.entities.Test;
import com.ealp.entities.Testlist;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-09T11:18:50")
@StaticMetamodel(Testlist.class)
public class Testlist_ { 

    public static volatile SingularAttribute<Testlist, Integer> id;
    public static volatile SingularAttribute<Testlist, Scholar> sid;
    public static volatile SingularAttribute<Testlist, Test> testid;

}
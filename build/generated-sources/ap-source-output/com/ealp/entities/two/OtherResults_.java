package com.ealp.entities.two;

import com.ealp.entities.OtherResults;
import com.ealp.entities.Scholar;
import com.ealp.entities.Test;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-09T11:18:50")
@StaticMetamodel(OtherResults.class)
public class OtherResults_ { 

    public static volatile SingularAttribute<OtherResults, Integer> id;
    public static volatile SingularAttribute<OtherResults, Scholar> sid;
    public static volatile SingularAttribute<OtherResults, Integer> score;
    public static volatile SingularAttribute<OtherResults, Test> testid;

}
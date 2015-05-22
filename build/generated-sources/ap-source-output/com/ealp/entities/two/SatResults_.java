package com.ealp.entities.two;

import com.ealp.entities.SatResults;
import com.ealp.entities.Scholar;
import com.ealp.entities.Test;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-19T13:51:48")
@StaticMetamodel(SatResults.class)
public class SatResults_ { 

    public static volatile SingularAttribute<SatResults, Integer> id;
    public static volatile SingularAttribute<SatResults, Scholar> sid;
    public static volatile SingularAttribute<SatResults, String> s2;
    public static volatile SingularAttribute<SatResults, String> s1;
    public static volatile SingularAttribute<SatResults, Integer> score2;
    public static volatile SingularAttribute<SatResults, Integer> score3;
    public static volatile SingularAttribute<SatResults, Integer> score1;
    public static volatile SingularAttribute<SatResults, Test> testid;
    public static volatile SingularAttribute<SatResults, String> s3;

}
package com.ealp.entities.two;

import com.ealp.entities.OtherResults;
import com.ealp.entities.SatResults;
import com.ealp.entities.Test;
import com.ealp.entities.Testlist;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-19T13:51:48")
@StaticMetamodel(Test.class)
public class Test_ { 

    public static volatile SingularAttribute<Test, Date> testDate;
    public static volatile SingularAttribute<Test, String> cycle;
    public static volatile SingularAttribute<Test, String> title;
    public static volatile SingularAttribute<Test, String> location;
    public static volatile SingularAttribute<Test, Integer> official;
    public static volatile SingularAttribute<Test, Integer> testid;
    public static volatile SingularAttribute<Test, String> type;
    public static volatile SingularAttribute<Test, Date> testTime;
    public static volatile ListAttribute<Test, OtherResults> otherResultsList;
    public static volatile ListAttribute<Test, Testlist> testlistList;
    public static volatile ListAttribute<Test, SatResults> satResultsList;

}
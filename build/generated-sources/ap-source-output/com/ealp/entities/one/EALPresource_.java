package com.ealp.entities.one;

import com.ealp.entities.EALPresource;
import com.ealp.entities.Mentor;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-19T13:51:48")
@StaticMetamodel(EALPresource.class)
public class EALPresource_ { 

    public static volatile SingularAttribute<EALPresource, String> title;
    public static volatile SingularAttribute<EALPresource, Integer> rid;
    public static volatile SingularAttribute<EALPresource, String> description;
    public static volatile SingularAttribute<EALPresource, Mentor> createdby;
    public static volatile SingularAttribute<EALPresource, Integer> type;
    public static volatile SingularAttribute<EALPresource, String> url;

}
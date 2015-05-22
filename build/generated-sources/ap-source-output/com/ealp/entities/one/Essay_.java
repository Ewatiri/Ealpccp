package com.ealp.entities.one;

import com.ealp.entities.Essay;
import com.ealp.entities.Scholar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-19T13:51:48")
@StaticMetamodel(Essay.class)
public class Essay_ { 

    public static volatile SingularAttribute<Essay, Integer> id;
    public static volatile SingularAttribute<Essay, Scholar> sid;
    public static volatile SingularAttribute<Essay, String> essay;
    public static volatile SingularAttribute<Essay, String> url;

}
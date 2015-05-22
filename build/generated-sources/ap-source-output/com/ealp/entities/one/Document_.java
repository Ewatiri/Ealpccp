package com.ealp.entities.one;

import com.ealp.entities.CollegeEssay;
import com.ealp.entities.Document;
import com.ealp.entities.Scholar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-19T13:51:48")
@StaticMetamodel(Document.class)
public class Document_ { 

    public static volatile SingularAttribute<Document, Integer> id;
    public static volatile SingularAttribute<Document, Scholar> sid;
    public static volatile SingularAttribute<Document, String> location;
    public static volatile SingularAttribute<Document, Integer> status;
    public static volatile SingularAttribute<Document, Integer> score;
    public static volatile SingularAttribute<Document, CollegeEssay> essayid;
    public static volatile SingularAttribute<Document, Integer> type;
    public static volatile SingularAttribute<Document, Integer> version;

}
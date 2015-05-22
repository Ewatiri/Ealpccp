package com.ealp.entities.one;

import com.ealp.entities.College;
import com.ealp.entities.CollegeEssay;
import com.ealp.entities.Document;
import com.ealp.entities.Mentor;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-19T13:51:48")
@StaticMetamodel(CollegeEssay.class)
public class CollegeEssay_ { 

    public static volatile SingularAttribute<CollegeEssay, String> cycle;
    public static volatile SingularAttribute<CollegeEssay, Mentor> addedby;
    public static volatile ListAttribute<CollegeEssay, Document> documentList;
    public static volatile SingularAttribute<CollegeEssay, Integer> status;
    public static volatile SingularAttribute<CollegeEssay, Integer> essayid;
    public static volatile SingularAttribute<CollegeEssay, String> prompt;
    public static volatile SingularAttribute<CollegeEssay, Integer> required;
    public static volatile SingularAttribute<CollegeEssay, College> collid;

}
package com.ealp.entities.two;

import com.ealp.entities.CallList;
import com.ealp.entities.CollegeEssay;
import com.ealp.entities.Event;
import com.ealp.entities.InvitedScholar;
import com.ealp.entities.Mentor;
import com.ealp.entities.Review;
import com.ealp.entities.Scholar;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-09T11:18:50")
@StaticMetamodel(Mentor.class)
public class Mentor_ { 

    public static volatile SingularAttribute<Mentor, Integer> uid;
    public static volatile ListAttribute<Mentor, CollegeEssay> collegeEssayList;
    public static volatile SingularAttribute<Mentor, Integer> status;
    public static volatile ListAttribute<Mentor, CallList> callListList;
    public static volatile ListAttribute<Mentor, Review> reviewList2;
    public static volatile SingularAttribute<Mentor, String> surname;
    public static volatile ListAttribute<Mentor, Scholar> scholarList;
    public static volatile SingularAttribute<Mentor, String> password;
    public static volatile CollectionAttribute<Mentor, Event> eventCollection;
    public static volatile ListAttribute<Mentor, Review> reviewList1;
    public static volatile ListAttribute<Mentor, Review> reviewList;
    public static volatile SingularAttribute<Mentor, Integer> class1;
    public static volatile SingularAttribute<Mentor, String> email;
    public static volatile CollectionAttribute<Mentor, InvitedScholar> invitedScholarCollection;
    public static volatile SingularAttribute<Mentor, String> fname;

}
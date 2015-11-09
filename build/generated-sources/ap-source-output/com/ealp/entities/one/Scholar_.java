package com.ealp.entities.one;

import com.ealp.entities.Attendance;
import com.ealp.entities.CallFeedback;
import com.ealp.entities.CollegeProfile;
import com.ealp.entities.Document;
import com.ealp.entities.Mentor;
import com.ealp.entities.OtherResults;
import com.ealp.entities.Review;
import com.ealp.entities.SatResults;
import com.ealp.entities.Scholar;
import com.ealp.entities.Testlist;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-09T11:18:50")
@StaticMetamodel(Scholar.class)
public class Scholar_ { 

    public static volatile SingularAttribute<Scholar, Integer> home;
    public static volatile ListAttribute<Scholar, CollegeProfile> collegeProfileList;
    public static volatile SingularAttribute<Scholar, String> location;
    public static volatile SingularAttribute<Scholar, String> reason;
    public static volatile SingularAttribute<Scholar, String> siblingInfo;
    public static volatile SingularAttribute<Scholar, String> surname;
    public static volatile SingularAttribute<Scholar, String> password;
    public static volatile SingularAttribute<Scholar, String> parent1;
    public static volatile SingularAttribute<Scholar, String> income;
    public static volatile SingularAttribute<Scholar, String> parent2;
    public static volatile ListAttribute<Scholar, CallFeedback> callFeedbackList;
    public static volatile SingularAttribute<Scholar, String> fname;
    public static volatile SingularAttribute<Scholar, String> mname;
    public static volatile SingularAttribute<Scholar, Mentor> mentor;
    public static volatile SingularAttribute<Scholar, String> cycle;
    public static volatile SingularAttribute<Scholar, Integer> sid;
    public static volatile SingularAttribute<Scholar, Integer> status;
    public static volatile ListAttribute<Scholar, Document> documentList;
    public static volatile SingularAttribute<Scholar, String> homeReason;
    public static volatile ListAttribute<Scholar, Attendance> attendanceList;
    public static volatile SingularAttribute<Scholar, Integer> siblings;
    public static volatile ListAttribute<Scholar, OtherResults> otherResultsList;
    public static volatile SingularAttribute<Scholar, Integer> orphan;
    public static volatile SingularAttribute<Scholar, String> email;
    public static volatile SingularAttribute<Scholar, Date> dob;
    public static volatile ListAttribute<Scholar, Testlist> testlistList;
    public static volatile ListAttribute<Scholar, SatResults> satResultsList;
    public static volatile SingularAttribute<Scholar, Review> review;
    public static volatile SingularAttribute<Scholar, String> mobile;

}
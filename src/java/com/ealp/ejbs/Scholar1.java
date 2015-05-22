/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ealp.ejbs;

import com.ealp.entities.Academics;
import com.ealp.entities.Activities;
import com.ealp.entities.Attendance;
import com.ealp.entities.College;
import com.ealp.entities.CollegeEssay;
import com.ealp.entities.CollegeProfile;
import com.ealp.entities.Contacts;
import com.ealp.entities.Document;
import com.ealp.entities.Essay;
import com.ealp.entities.Event;
import com.ealp.entities.Highschool;
import com.ealp.entities.Highschools;
import com.ealp.entities.Holiday;
import com.ealp.entities.InvitedScholar;
import com.ealp.entities.Login;
import com.ealp.entities.OtherResults;
import com.ealp.entities.Review;
import com.ealp.entities.SatResults;
import com.ealp.entities.Scholar;
import com.ealp.entities.Staff;
import com.ealp.entities.Test;
import com.ealp.entities.Testlist;
import com.ealp.entities.Transcript;
import com.ealp.jpa.JTAProvider;
import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author eva
 */
@Stateless
@LocalBean
public class Scholar1 {

    private JTAProvider jp;

    public Scholar1() {

    }

    public Scholar1(JTAProvider jp) {
        this.jp = jp;
    }

    public boolean addInvited(InvitedScholar scholar) {
        boolean res = false;
        try {
            res = jp.create(scholar);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return res;
        }
    }

    public int getinvitedScholarId(String mail) {
        int res = -1;
        Query q;
        InvitedScholar is = null;
        try {
            q = jp.createQuery("SELECT s FROM InvitedScholar s WHERE s.email = :mail");
            q.setParameter("mail", mail);
            is = (InvitedScholar) q.getSingleResult();
            if (is != null) {
                res = is.getId();
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            return res;
        }
    }

    public InvitedScholar getInvitedScholar(String email) {
        InvitedScholar res = null;
        Query q;
        try {
            q = jp.createQuery("SELECT s FROM InvitedScholar s WHERE s.email = :mail");
            q.setParameter("mail", email);
            res = (InvitedScholar) q.getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return res;
        }
    }

    public boolean updateinvitedScholar(InvitedScholar is) {
        boolean res = false;
        try {
            res = jp.update(is);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return res;
        }
    }

    public boolean addStaff(Staff staff) {
        boolean res = false;
        try {
            res = jp.create(staff);
        } catch (Exception ex) {
        } finally {
            return res;
        }

    }

    public String getAccessCode(String email, String code) {
        String res = null;
        int stat = 0;
        Query q;
        try {
            q = jp.createQuery("SELECT s.email FROM InvitedScholar s WHERE s.email = :mail AND s.code =:code AND s.status =:stat");
            q.setParameter("mail", email);
            q.setParameter("code", code);
            q.setParameter("stat", stat);
            res = jp.getOneFromQuery(q);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return res;
        }
    }

    public boolean createScholar(Scholar sch) {
        boolean res = false;
        try {
            res = jp.create(sch);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return res;
        }
    }

    public boolean updateScholar(Scholar sch) {
        boolean res = false;
        try {
            res = jp.update(sch);
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public int getScholarId(String email) {
        int res = -1;
        Query q;
        try {
            q = jp.createQuery("SELECT s.sid FROM Scholar s WHERE s.email = :email");
            q.setParameter("email", email);
            res = jp.getOneFromQuery(q);
            System.out.print(email);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return res;
        }
    }

    public Scholar getScholarDetails(int id) {
        Scholar res = null;
        Query q;
        try {
            q = jp.createQuery("SELECT s FROM Scholar s WHERE s.sid = :sid");
            q.setParameter("sid", id);
            res = (Scholar) jp.getOneFromQuery(q);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return res;
        }
    }

    public Scholar checkScholar(String email) {
        Scholar res = null;
        Query q;
        try {
            q = jp.createQuery("SELECT s FROM Scholar s WHERE s.email = :email");
            q.setParameter("email", email);
            res = jp.getOneFromQuery(q);
            
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public boolean checkSchool(int sid) {
        boolean res = false;
        Query q;
        try {
            q = jp.createQuery("SELECT h FROM Highschool h WHERE h.sid = :sid ");
            q.setParameter("sid", sid);
            Highschool hs = (Highschool) jp.getOneFromQuery(q);
            if (hs != null) {
                res = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return res;
        }
    }

    public boolean checkTranscript(int sid) {
        boolean res = false;
        Query q;
        try {
            q = jp.createQuery("SELECT t FROM Transcript t WHERE t.sid = :sid ");
            q.setParameter("sid", sid);
            Transcript hs = (Transcript) jp.getOneFromQuery(q);
            if (hs != null) {
                res = true;
            }
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public Highschool getHighSchool(int id) {
        Highschool res = null;
        Query q;
        try {
            q = jp.createQuery("SELECT h FROM Highschool h WHERE h.sid = :sid");
            q.setParameter("sid", id);
            res = (Highschool) jp.getOneFromQuery(q);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return res;
        }
    }

    public List<Activities> getActivities(int sid) {
        List<Activities> res = null;
        Query q;
        try {
            q = jp.createQuery("SELECT a FROM Activities a WHERE a.sid = :sid");
            q.setParameter("sid", sid);
            res = jp.getManyFromQuery(q);
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public List<Activities> getSpecificActivity(Scholar sid, int type) {
        List<Activities> res = null;
        Query q;
        try {
            q = jp.createQuery("SELECT a FROM Activities a WHERE a.sid = :sid AND a.type = :type");
            q.setParameter("sid", sid);
            q.setParameter("type", type);
            res = jp.getManyFromQuery(q);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return res;
        }

    }

    public boolean addActivity(List<Activities> act) {
        boolean res = false;
        try {
            res = jp.createList(act);
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public boolean deleteActivities(List<Activities> act) {
        boolean res = false;
        Query q;
        try {
            res = jp.deleteList(act);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return res;
        }
    }

    public Transcript getTranscript(int sid) {
        Transcript res = null;
        Query q;
        try {
            q = jp.createQuery("SELECT t FROM  Transcript t WHERE t.sid = :sid");
            q.setParameter("sid", sid);
            res = jp.getOneFromQuery(q);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return res;
        }
    }

    public boolean createSchool(Highschool h) {
        boolean res = false;
        try {
            res = jp.create(h);
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public boolean updateSchool(Highschool h) {
        boolean res = false;
        try {
            res = jp.update(h);
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public boolean createTranscript(Transcript t) {
        boolean res = false;
        try {
            res = jp.create(t);
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public boolean updateTranscript(Transcript t) {
        boolean res = false;
        try {
            res = jp.update(t);
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public boolean createHoliday(Holiday hol) {
        boolean res = false;
        try {
            res = jp.create(hol);
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }
    
    public Holiday getHoliday(int sid){
        Holiday res = null;
        Query q;
        try{
            q = jp.createQuery("SELECT h FROM Holiday h WHERE h.sid = :sid");
            q.setParameter("sid", sid);
            res = jp.getOneFromQuery(q);
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            return res;
        }
    }
    
    public boolean checkHoliday(int sid) {
        boolean res = false;
        Query q;
        try {
            q = jp.createQuery("SELECT h FROM Holiday h WHERE h.sid = :sid ");
            q.setParameter("sid", sid);
            Holiday hs = (Holiday) jp.getOneFromQuery(q);
            if (hs != null) {
                res = true;
            }
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public boolean checkEssay(Scholar sid) {
        boolean res = false;
        Query q;
        try {
            q = jp.createQuery("SELECT e FROM Essay e WHERE e.sid = :sid ");
            q.setParameter("sid", sid);
           List< Essay> hs =  jp.getManyFromQuery(q);
            if (hs != null) {
                res = true;
            }
        } catch (Exception ex) {
           ex.printStackTrace();
        } finally {
            return res;
        }
    }

    public boolean updateHoliday(Holiday hol) {
        boolean res = false;
        try {
            res = jp.update(hol);
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public boolean createEssay(List<Essay> es) {
        boolean res = false;
        try {
            res = jp.createList(es);
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public boolean updateEssay(Essay es) {
        boolean res = false;
        try {
            res = jp.update(es);
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }
    
    public boolean deleteEssay(List<Essay> es){
        boolean res = false;
        try{
            res = jp.deleteList(es);
        }catch(Exception ex){
            
        }finally{
            return res;
        }
    }
    public List<Essay> getEssays(Scholar s){
        List <Essay> res = null;
        Query q;
        try{
            q = jp.createQuery("SELECT e FROM Essay e WHERE e.sid = :sid");
            q.setParameter("sid", s);
            res = jp.getManyFromQuery(q);
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            return res;
        }
    }
    
    public Essay getoneEssay(Scholar s, String eid){
       Essay res = null;
       Query q;
       try{
           q = jp.createQuery("SELECT e FROM Essay e WHERE e.sid = :scholar AND e.essay = :eid");
           q.setParameter("scholar", s);
           q.setParameter("eid", eid);
           res = (Essay) jp.getOneFromQuery(q);
       }catch(Exception ex){
           ex.printStackTrace();
       }finally{
           return res;
       }
    }
    
    public boolean deleteoneEssay(Essay es){
        boolean res = false;
        try{
            res = jp.delete(es);
        }catch(Exception ex){
            
        }finally{
            return res;
        }
    }
    
    public boolean createoneEssay(Essay es){
        boolean res = false;
        try{
            res = jp.create(es);
        }catch(Exception ex){
            
        }finally{
            return res;
        }
    }
    public boolean submitApp(Review r){
        boolean res = false;
        try{
         res = jp.create(r);
        }catch(Exception ex){
            
        }finally{
            return res;
        }
    }
    
    public boolean checkSubmit(int sid){
        boolean res = false;
        Query q;
        try{
            q = jp.createQuery("SELECT r FROM Review r WHERE r.sid = :sid");
            q.setParameter("sid", sid);
            Review r = (Review) jp.getOneFromQuery(q);
            if (r != null){
                res = true;
            }
        }catch(Exception ex){
            
        }finally{
            return res;
        }
    }
    
    public String getealpMail(String pf){
        String res = null;
        Query q;
        try{
            q = jp.createQuery("SELECT c.email FROM Contacts c WHERE c.pfNo = :pf");
            q.setParameter("pf", pf);
            res = jp.getOneFromQuery(q);
            
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            return res;
        }
    }
   
    public Login getScholarName(String pf){
        Login res = null;
        Query q;
        try{
            q = jp.createQuery("SELECT l FROM Login l WHERE l.pfNo = :pf");
            q.setParameter("pf", pf);
            res = jp.getOneFromQuery(q);
        }catch(Exception ex){
            
        }finally{
            return res;
        }
    }
    
    public Academics getealpHighschool(String pf){
        Academics res = null;
        Query q;
        try{
            q = jp.createQuery("SELECT a FROM Academics a WHERE a.pfNo = :pf");
            q.setParameter("pf", pf);
            res = jp.getOneFromQuery(q);
        }catch(Exception ex){
            
        }finally{
            return res;
        }
    }
    
    public Contacts getealpContacts(String pf){
        Contacts res = null;
        Query q;
        try{
            q =jp.createQuery("SELECT c FROM Contacts c WHERE c.pfNo = :pf");
            q.setParameter("pf", pf);
            res = jp.getOneFromQuery(q);
        }catch(Exception ex){
            
        }finally{
            return res;
        }
    }
    
    public List<Event> getEvents(Date date,String cycle){
        List<Event> res = null;
        Query q;
        try{
            q = jp.createQuery("SELECT e FROM Event e WHERE e.edate >= :today AND e.cycle = :cycle");
            q.setParameter("today", new java.sql.Date(date.getTime()), TemporalType.DATE);
            q.setParameter("cycle",cycle);
            res = jp.getManyFromQuery(q);
        }catch(Exception ex){
            
        }finally{
            return res;
        }
    }
    
    public List<Attendance> getAttendance(Scholar sid){
        List<Attendance> res = null;
        Query q;
        try{
            q = jp.createQuery("SELECT a FROM Attendance a WHERE a.sid = :sid");
            q.setParameter("sid", sid);
            res = jp.getManyFromQuery(q);
        }catch(Exception ex){
            
        }finally{
            return res;
        }
    }
    
    public Attendance getAttendance(Scholar s,Event e){
        Attendance res  = null;
        Query q;
        try{
            q = jp.createQuery("SELECT a FROM Attendance a WHERE a.eid = :event AND a.sid = :scholar");
            q.setParameter("event", e);
            q.setParameter("scholar", s);
            res = jp.getOneFromQuery(q);
        }catch(Exception ex){
            
        }finally{
            return res;
        }
    }
    
    public boolean addAttendance(Attendance att){
        boolean res = false;
        try{
            res = jp.create(att);
        }catch(Exception ex){
            
        }finally{
            return res;
        }
    }
   
    public boolean deleteAttendance(Attendance att){
        boolean res = false;
        try{
            res = jp.delete(att);
        }catch(Exception ex){
            
        }finally{
            return res;
        }
    }
    
   public List<Test> getTests(Date date,String cycle){
       List<Test> res = null;
       Query q;
       try{
           q = jp.createQuery("SELECT t FROM Test t WHERE t.testDate >= :today AND t.cycle = :cycle");
           q.setParameter("today", date);
           q.setParameter("cycle", cycle);
           res = jp.getManyFromQuery(q);
       }catch(Exception ex){
           ex.printStackTrace();
       }finally{
           return res;
       }
   }
    
   public List<Testlist> getTestList(Scholar s){
       List<Testlist> res = null;
       Query q;
       try{
           q = jp.createQuery("SELECT t FROM Testlist t WHERE t.sid = :scholar");
           q.setParameter("scholar", s);
           res=jp.getManyFromQuery(q);
       }catch(Exception ex){
           ex.printStackTrace();
       }finally{
           return res;
       }
   }
   
   public boolean addtoTestList(Testlist t){
       boolean res = false;
       try{
           res = jp.create(t);
       }catch(Exception ex){
           
       }finally{
           return res;
       }
   }
   
   public Testlist getTestList(Test t, Scholar s){
       Testlist res = null;
       Query q;
       try{
           q = jp.createQuery("SELECT t FROM Testlist t WHERE t.testid = :test AND t.sid = :scholar");
           q.setParameter("test",t);
           q.setParameter("scholar", s);
           res = jp.getOneFromQuery(q);
       }catch(Exception ex){
           
       }finally{
           return res;
       }
   }
   
   public boolean deleteTestlist(Testlist t){
       boolean res = false;
       try{
           res = jp.delete(t);
       }catch(Exception ex){
           
       }finally{
           return res;
       }
   }
   
   public List<Testlist> getPastTestsList(Scholar s){
       List<Testlist> res = null;
       Query q;
       try{
           q = jp.createQuery("SELECT t FROM Testlist t WHERE t.sid = :scholar");
           q.setParameter("scholar", s);
           res = jp.getManyFromQuery(q);
       }catch(Exception ex){
           ex.printStackTrace();
       }finally{
           return res;
       }
   }
   
   public SatResults getSatResults(Test t,Scholar s){
       SatResults res = null;
       Query q;
       try{
           q = jp.createQuery("SELECT s FROM SatResults s WHERE s.testid = :test AND s.sid = :scholar");
           q.setParameter("test", t);
           q.setParameter("scholar", s);
           res = jp.getOneFromQuery(q);
       }catch(Exception ex){
           
       }finally{
           return res;
       }
   }
   
   public OtherResults getOtherResults(Test t,Scholar s){
       OtherResults res = null;
       Query q;
       try{
           q = jp.createQuery("SELECT o FROM OtherResults o WHERE o.testid = :test AND o.sid = :scholar");
           q.setParameter("test", t);
           q.setParameter("scholar", s);
           res = jp.getOneFromQuery(q);
       }catch(Exception ex){
           
       }finally{
           return res;
       }
   }
   
   public List<SatResults> getSatResults(Scholar s){
       List<SatResults> res = null;
       Query q;
       try{
           q = jp.createQuery("SELECT s FROM SatResults s WHERE s.sid = :scholar");
           q.setParameter("scholar", s);
           res = jp.getManyFromQuery(q);
       }catch(Exception ex){
           
       }finally{
           return res;
       }
   }
   
   public List<OtherResults> getOtherResults(Scholar s){
       List<OtherResults> res = null;
       Query q;
       try{
           q = jp.createQuery("SELECT o FROM OtherResults o WHERE o.sid = :scholar");
           q.setParameter("scholar", s);
           res = jp.getManyFromQuery(q);
       }catch(Exception ex){
           
       }finally{
           return res;
       }
   }
   
   public List<CollegeEssay> getCollegeEssays(College c, String cycle){
       List<CollegeEssay> res = null;
       Query q;
       try{
           q = jp.createQuery("SELECT c FROM CollegeEssay c WHERE c.collid = :college AND c.cycle = :cycle AND c.status = :status");
           q.setParameter("college", c);
           q.setParameter("cycle", cycle);
           q.setParameter("status", 1);
           res = jp.getManyFromQuery(q);
       }catch(Exception ex){
           
       }finally{
           return res;
       }
   }
   
   public boolean createProfile(CollegeProfile cp){
       boolean res = false;
       try{
           res = jp.create(cp);
       }catch(Exception ex){
           
       }finally{
           return res;
       }
   }
   
   public CollegeProfile getCollegeProfile(College c, Scholar s){
       CollegeProfile res = null;
       Query q;
       try{
           q = jp.createQuery("SELECT c FROM CollegeProfile c WHERE c.collid = :college AND c.sid = :scholar");
           q.setParameter("college", c);
           q.setParameter("scholar", s);
           res = jp.getOneFromQuery(q);
       }catch(Exception ex){
           
       }finally{
           return res;
       }
   }
   
   public boolean deleteCollegeProfile(CollegeProfile c){
       boolean res = false;
       try{
           res = jp.delete(c);
       }catch(Exception ex){
           
       }finally{
           return res;
       }
   }
   
   public boolean updateCollegeProfile(CollegeProfile c){
       boolean res = false;
       try{
           res = jp.update(c);
       }catch(Exception ex){
           
       }finally{
           return res;
       }
   }
  
  public Document getDocument(Scholar s, Integer type){
      Document res = null;
      Query q;
      try{
        q = jp.createQuery("SELECT d FROM Document d WHERE d.type = :type AND d.sid = :scholar ");
        q.setParameter("type", type);
        q.setParameter("scholar", s);
        res = jp.getOneFromQuery(q);
      }catch(Exception ex){
          
      }finally{
          return res;
      }
  }
  public Document getDocEssay(Scholar s, CollegeEssay ce){
      Document res = null;
      Query q;
      try{
        q = jp.createQuery("SELECT d FROM Document d WHERE d.essayid = :type AND d.sid = :scholar ");
        q.setParameter("type", ce);
        q.setParameter("scholar", s);
        res = jp.getOneFromQuery(q);
      }catch(Exception ex){
          
      }finally{
          return res;
      }
  }
  
  public boolean createDocument(Document doc){
      boolean res = false;
      try{
          res = jp.create(doc);
      }catch(Exception ex){
          ex.printStackTrace();
      }finally{
          return res;
      }
  }
  
  public boolean updateDocument(Document doc){
      boolean res = false;
      try{
          res = jp.update(doc);
      }catch(Exception ex){
          ex.printStackTrace();
      }finally{
          return res;
      }
  }
  public List<Document> getDocuments(Scholar s){
      List<Document> res = null;
      Query q;
      try{
          q = jp.createQuery("SELECT d FROM Document d WHERE d.sid = :scholar");
          q.setParameter("scholar",s);
          res = jp.getManyFromQuery(q);
      }catch(Exception ex){
          ex.printStackTrace();
      }finally{
          return res;
      }
  }
  
  public boolean deleteDocument(Document doc){
      boolean res = false;
      try{
          res = jp.delete(doc);
      }catch(Exception ex){
          
      }finally{
          return res;
      }
  }
  public Document getCollegeEssay(Scholar s, CollegeEssay ce){
      Document res = null;
      Query q;
      try{
          q = jp.createQuery("SELECT d FROM Document d WHERE d.sid = :scholar AND d.essayid = :essay");
          q.setParameter("scholar", s);
          q.setParameter("essay", ce);
          res = jp.getOneFromQuery(q);
      }catch(Exception ex){
          
      }finally{
          return res;
      }
  }
}

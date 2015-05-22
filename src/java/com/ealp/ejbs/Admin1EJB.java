/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ealp.ejbs;

import com.ealp.entities.Announcement;
import com.ealp.entities.CallFeedback;
import com.ealp.entities.CallList;
import com.ealp.entities.College;
import com.ealp.entities.CollegeEssay;
import com.ealp.entities.CollegeProfile;
import com.ealp.entities.Commonapp;
import com.ealp.entities.EALPresource;
import com.ealp.entities.Event;
import com.ealp.entities.InvitedScholar;
import com.ealp.entities.Mentor;
import com.ealp.entities.OtherResults;
import com.ealp.entities.Review;
import com.ealp.entities.SatResults;
import com.ealp.entities.Scholar;
import com.ealp.entities.Staff;
import com.ealp.entities.Sys;
import com.ealp.entities.Test;
import com.ealp.jpa.JTAProvider;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author eva
 */
@Stateless
@LocalBean
public class Admin1EJB {

    private JTAProvider jp;

    public Admin1EJB() {

    }

    public Admin1EJB(JTAProvider jp) {
        this.jp = jp;
    }

    public boolean addMentor(Mentor mentor) {
        boolean res = false;
        try {
            if (jp == null) {

            } else {
                res = jp.create(mentor);
            }

            //if (res == true){
            // send email
            //}
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return res;
        }

    }

    public boolean setModule(Sys sys) {
        boolean res = false;
        try {

            res = jp.update(sys);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return res;
        }

    }

    public Sys getModule() {
        Sys sys = null;
        Query q;
        try {
            q = jp.createQuery("SELECT m FROM Sys m WHERE m.id = :id");
            q.setParameter("id", 1);
            sys = (Sys) q.getSingleResult();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return sys;
        }
    }

    public int getUid(String email) {
        int res = -1;
        Mentor mentor = null;
        Query q;
        try {
            q = jp.createQuery("SELECT m FROM Mentor m WHERE m.email = :mail");
            q.setParameter("mail", email);
            mentor = (Mentor) q.getSingleResult();
            if (mentor != null) {
                res = mentor.getUid();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return res;
        }
    }

    public boolean updateMentor(Mentor mentor) {
        boolean res = false;
        try {
            res = jp.update(mentor);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return res;
        }
    }

    public Mentor getMentor(Integer uid) {
        Mentor mentor = null;
        Query q;
        try {
            q = jp.createQuery("SELECT m FROM Mentor m WHERE m.uid = :id");
            q.setParameter("id", uid);
            mentor = (Mentor) q.getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return mentor;
        }
    }

    public boolean addAnnouncement(Announcement announ) {
        boolean res = false;
        try {
            res = jp.create(announ);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return res;
        }
    }

    public boolean addRes(EALPresource resource) {
        boolean res = false;
        try {
            res = jp.create(resource);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return res;
        }

    }

    public List<Announcement> getAnnouncement() {
        List<Announcement> res = null;
        Query q;
        try {
            q = jp.createQuery("SELECT a FROM Announcement a ORDER by a.ctime desc");

            res = jp.getManyFromQuery(q);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return res;
        }
    }

    public List<EALPresource> getResource() {
        List<EALPresource> res = null;
        Query q;
        try {
            q = jp.createQuery("SELECT r FROM EALPresource r ORDER by r.rid desc");
            res = jp.getManyFromQuery(q);
        } catch (Exception ex) {
        } finally {
            return res;
        }

    }

    public boolean editAnn(Announcement ann) {
        boolean res = false;
        try {
            res = jp.update(ann);
        } catch (Exception ex) {
        } finally {
            return res;
        }
    }

    public boolean deleteAnn(Announcement ann) {
        boolean res = false;
        try {
            res = jp.delete(ann);
        } catch (Exception ex) {
        } finally {
            return res;
        }
    }

    public boolean deleteRes(EALPresource resource) {
        boolean res = false;
        try {
            res = jp.delete(resource);
        } catch (Exception ex) {
        } finally {
            return res;
        }
    }

    public boolean editRes(EALPresource resource) {
        boolean res = false;
        try {
            res = jp.update(resource);
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public List<InvitedScholar> getinvitedScholar() {
        List<InvitedScholar> res = null;
        Query q;
        int id = -1;
        try {
            q = jp.createQuery("SELECT i FROM InvitedScholar i WHERE i.status = :k");
            q.setParameter("k", id);
            res = jp.getManyFromQuery(q);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return res;
        }
    }

    public Staff getStaff(int iud) {
        Staff res = null;
        Query q;
        try {
            q = jp.createQuery("SELECT s FROM Staff s WHERE s.id = :id");
            q.setParameter("id", iud);
            res = jp.getOneFromQuery(q);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return res;
        }
    }

    public boolean updateIscholar(InvitedScholar is) {
        boolean res = false;
        try {
            res = jp.update(is);
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public Mentor checkMentor(String email) {
        Mentor res = null;
        Query q;
        try {
            q = jp.createQuery("SELECT m FROM Mentor m WHERE m.email = :email");
            q.setParameter("email", email);
            res = jp.getOneFromQuery(q);

        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public List getApplicantggs(int sid, String cycle) {
        List res = null;
        Query q;
        try {
            q = jp.createQuery("SELECT s.sid,s.fname,s.surname FROM Scholar s WHERE s.sid = :sid AND s.cycle = :cylce");
            q.setParameter("sid", sid);
            q.setParameter("sid", cycle);
            res = jp.getManyFromQuery(q);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return res;
        }
    }

    public List<Review> getApplicants(String cycle) {
        List<Review> res = null;
        Query q;
        try {
            q = jp.createQuery("SELECT r FROM Review r WHERE (r.cycle = :cycle)");
            q.setParameter("cycle", cycle);
            //q.setParameter("uid", uid);
            //Mentor m = new Mentor();
            //m.setUid(-1);
            //q.setParameter("def",m);
            res = jp.getManyFromQuery(q);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return res;
        }
    }

    public Review getReview(int sid) {
        Review res = null;
        Query q;
        try {
            q = jp.createQuery("SELECT r FROM Review r WHERE r.sid = :sid");
            q.setParameter("sid", sid);
            res = jp.getOneFromQuery(q);
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public boolean updateReview(Review r) {
        boolean res = false;
        try {
            res = jp.update(r);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return res;
        }
    }

    public List<Review> getReviews(String cycle) {
        List<Review> res = null;
        Query q;
        try {
            q = jp.createQuery("SELECT r FROM Review r WHERE r.cycle = :cycle");
            q.setParameter("cycle", cycle);
            res = jp.getManyFromQuery(q);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return res;
        }
    }

    public boolean createEvent(Event e) {
        boolean res = false;
        try {
            res = jp.create(e);
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public boolean updateEvent(Event e) {
        boolean res = false;
        try {
            res = jp.update(e);
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public List<Event> getEvents(String cycle) {
        List<Event> res = null;
        Query q;
        try {
            q = jp.createQuery("SELECT e FROM Event e WHERE e.cycle = :cycle");
            q.setParameter("cycle", cycle);
            res = jp.getManyFromQuery(q);
        } catch (Exception ex) {

        } finally {
            return res;
        }

    }

    public boolean deleteEvent(Event e) {
        boolean res = false;
        try {
            res = jp.delete(e);
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public Event getEvent(int eid) {
        Event res = null;
        Query q;
        try {

            q = jp.createQuery("SELECT e FROM Event e WHERE e.eid = :eid ");
            q.setParameter("eid", eid);
            res = jp.getOneFromQuery(q);
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public List<Scholar> getScholars(String cycle) {
        List<Scholar> res = null;
        Query q;
        try {
            q = jp.createQuery("SELECT s FROM Scholar s WHERE s.cycle = :cycle AND s.status = :status OR s.status = :status1 OR s.status = :status2");
            q.setParameter("cycle", cycle);
            q.setParameter("status", 10);
            q.setParameter("status1", 11);
            q.setParameter("status2", 20);
            res = jp.getManyFromQuery(q);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return res;
        }
    }

    public boolean createCallist(CallList c) {
        boolean res = false;
        try {
            res = jp.create(c);
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public boolean updateCallist(CallList c) {
        boolean res = false;
        try {
            res = jp.update(c);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return res;
        }
    }

    public List<CallList> getCallists(String cycle) {
        List<CallList> res = null;
        Query q;
        try {
            q = jp.createQuery("SELECT c FROM CallList c WHERE c.cycle = :cycle");
            q.setParameter("cycle", cycle);
            res = jp.getManyFromQuery(q);
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public CallList getCallist(int id) {
        CallList res = null;
        Query q;
        try {
            q = jp.createQuery("SELECT c FROM CallList c WHERE c.cid = :id");
            q.setParameter("id", id);
            res = jp.getOneFromQuery(q);
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public boolean deleteCallist(CallList c) {
        boolean res = false;
        try {
            res = jp.delete(c);
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public List<CallFeedback> getFeedback(CallList id) {
        List<CallFeedback> res = null;
        Query q;
        try {
            q = jp.createQuery("SELECT f FROM CallFeedback f WHERE f.cid = :id");
            q.setParameter("id", id);
            res = jp.getManyFromQuery(q);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return res;
        }
    }

    public boolean addScholarToList(CallFeedback c) {
        boolean res = false;
        try {
            res = jp.create(c);
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public CallFeedback getCallfeedback(Scholar s, CallList c) {
        CallFeedback res = null;
        Query q;
        try {
            q = jp.createQuery("SELECT c FROM CallFeedback c WHERE c.cid = :id AND c.sid = :sid");
            q.setParameter("id", c);
            q.setParameter("sid", s);
            res = jp.getOneFromQuery(q);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return res;
        }
    }

    public boolean updateFeedback(CallFeedback c) {
        boolean res = false;
        try {
            res = jp.update(c);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return res;
        }
    }

    public boolean createCollege(College c) {
        boolean res = false;
        try {
            res = jp.create(c);
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public College getCollege(int id) {
        College res = null;
        Query q;
        try {
            q = jp.createQuery("SELECT c FROM College c WHERE c.collid = :id");
            q.setParameter("id", id);
            res = jp.getOneFromQuery(q);
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public boolean updateCollege(College c) {
        boolean res = false;
        try {
            res = jp.update(c);
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public List<College> getColleges() {
        List<College> res = null;
        Query q;
        try {
            q = jp.createQuery("SELECT c FROM College c");
            res = jp.getManyFromQuery(q);
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public boolean addEssay(CollegeEssay ce) {
        boolean res = false;
        try {
            res = jp.create(ce);
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public List<CollegeEssay> getCollegeEssays(College college, String cycle) {
        List<CollegeEssay> res = null;
        Query q;
        try {
            q = jp.createQuery("SELECT c FROM CollegeEssay c WHERE c.collid = :id AND c.cycle = :cycle");
            q.setParameter("id", college);
            q.setParameter("cycle", cycle);
            res = jp.getManyFromQuery(q);
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public boolean updateCollegeEssay(CollegeEssay ce) {
        boolean res = false;
        try {
            res = jp.update(ce);
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public CollegeEssay getCollegeEssay(int id) {
        CollegeEssay res = null;
        Query q;
        try {
            q = jp.createQuery("SELECT c FROM CollegeEssay c WHERE c.essayid = :id");
            q.setParameter("id", id);
            res = jp.getOneFromQuery(q);
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public boolean deleteCollegeEssay(CollegeEssay ce) {
        boolean res = false;

        try {
            res = jp.delete(ce);
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public boolean createTest(Test t) {
        boolean res = false;

        try {
            res = jp.create(t);
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public List<Test> getTests(String cycle) {
        List<Test> res = null;
        Query q;
        try {
            q = jp.createQuery("SELECT t FROM Test t WHERE t.cycle = :cycle");
            q.setParameter("cycle", cycle);
            res = jp.getManyFromQuery(q);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return res;
        }
    }

    public Test getTest(int testid) {
        Test res = null;
        Query q;
        try {
            q = jp.createQuery("SELECT t FROM Test t WHERE t.testid = :testid");
            q.setParameter("testid", testid);
            res = jp.getOneFromQuery(q);
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public boolean updateTest(Test t) {
        boolean res = false;
        try {
            res = jp.update(t);
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public List<Scholar> getAllScholars(String cycle) {
        List<Scholar> res = null;
        Query q;
        try {
            q = jp.createQuery("SELECT s FROM Scholar s WHERE s.cycle = :cycle AND s.mentor IS NULL AND s.status = :status OR s.status = :status1 OR s.status = :status2");
            q.setParameter("cycle", cycle);
            q.setParameter("status", 11);
            q.setParameter("status1", 10);
            q.setParameter("status2", 20);
            res = jp.getManyFromQuery(q);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return res;
        }
    }

    public List<CollegeProfile> getColleges(Scholar s, String cycle) {
        List<CollegeProfile> res = null;
        Query q;
        try {
            q = jp.createQuery("SELECT c FROM CollegeProfile c WHERE c.sid = :sid AND c.cycle = :cycle");
            q.setParameter("sid", s);
            q.setParameter("cycle", cycle);
            res = jp.getManyFromQuery(q);
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public boolean addSatScore(SatResults s) {
        boolean res = false;
        try {
            res = jp.create(s);
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public boolean addOtherScore(OtherResults s) {
        boolean res = false;
        try {
            res = jp.create(s);
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public List<Scholar> getMyScholars(Mentor m) {
        List<Scholar> res = null;
        Query q;
        try {
            q = jp.createQuery("SELECT s FROM Scholar s WHERE s.mentor = :mentor");
            q.setParameter("mentor", m);
            res = jp.getManyFromQuery(q);
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public List<CollegeEssay> getApprovalEssays(College c, String cycle) {
        List<CollegeEssay> res = null;
        Query q;
        try {
            q = jp.createQuery("SELECT c FROM CollegeEssay c WHERE c.collid = :college AND c.cycle = :cycle AND c.status = :status");
            q.setParameter("college", c);
            q.setParameter("cycle", cycle);
            q.setParameter("status", 0);
            res = jp.getManyFromQuery(q);
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public List<Mentor> getMentors() {
        List<Mentor> res = null;
        Query q;
        try {
            q = jp.createQuery("SELECT m FROM Mentor m");
            res = jp.getManyFromQuery(q);
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public List<Review> getAllReviews(String cycle) {
        List<Review> res = null;
        Query q;
        try {
            q = jp.createQuery("SELECT r FROM Review r WHERE r.cycle = :cycle");
            q.setParameter("cycle", cycle);
            res = jp.getManyFromQuery(q);
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public List<Scholar> getDiagScholars(String cycle) {
        List<Scholar> res = null;
        Query q;
        try {
            q = jp.createQuery("SELECT s FROM Scholar s WHERE s.cycle = :cycle AND s.mentor IS NULL AND s.status = :status OR s.status = :status1");
            q.setParameter("cycle", cycle);
            q.setParameter("status", 11);
            q.setParameter("status1", 10);
            res = jp.getManyFromQuery(q);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return res;
        }
    }

    public List<Scholar> getScholarsbyType(String cycle, int status) {
        List<Scholar> res = null;
        Query q;
        try {
            q = jp.createQuery("SELECT s FROM Scholar s WHERE s.cycle = :cycle AND s.status = :status");
            q.setParameter("cycle", cycle);
            q.setParameter("status", status);
            res = jp.getManyFromQuery(q);
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public List<College> getCollegebyName(String name) {
        List<College> res = null;
        Query q;
        try {
            q = jp.createQuery("SELECT c FROM College c WHERE c.name LIKE :college");
            q.setParameter("college", "%" + name + "%");
            System.out.print(q);
            res = jp.getManyFromQuery(q);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return res;
        }
    }

    public List<CollegeProfile> getCollegeProfile(College c, String cycle) {
        List<CollegeProfile> res = null;
        Query q;
        try {
            q = jp.createQuery("SELECT c FROM CollegeProfile c WHERE c.collid = :college AND c.cycle = :cycle");
            q.setParameter("college", c);
            q.setParameter("cycle", cycle);
            res = jp.getManyFromQuery(q);
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public List<CollegeProfile> getProfilesbyType(College c, String cycle, String type) {
        List<CollegeProfile> res = null;
        Query q;
        try {
            q = jp.createQuery("SELECT c FROM CollegeProfile c WHERE c.collid = :college AND c.cycle = :cycle AND c.decisionType = :type");
            q.setParameter("college", c);
            q.setParameter("cycle", cycle);
            q.setParameter("type", type);
            res = jp.getManyFromQuery(q);
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public List<CollegeProfile> getCurrentProfile(String cycle) {
        List<CollegeProfile> res = null;
        Query q;
        try {
            q = jp.createQuery("SELECT c FROM CollegeProfile c WHERE c.cycle = :cycle");
            q.setParameter("cycle", cycle);
            res = jp.getManyFromQuery(q);
        } catch (Exception ex) {

        } finally {
            return res;
        }
    }

    public List<CollegeProfile> getProfilebyStatus(String cycle, int status) {
        List<CollegeProfile> res = null;
        Query q;
        try {
            q = jp.createQuery("SELECT c FROM CollegeProfile c WHERE c.cycle = :cycle AND c.decision = :status");
            q.setParameter("cycle", cycle);
            q.setParameter("status", status);
            res = jp.getManyFromQuery(q);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return res;
        }
    }

    public List<Scholar> getAllScholarsProgress(String cycle) {
        List<Scholar> res = null;
        Query q;
        try {
            q = jp.createQuery("SELECT s FROM Scholar s WHERE s.cycle = :cycle");
            q.setParameter("cycle", cycle);
            res = jp.getManyFromQuery(q);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return res;
        }
    }
    
    public boolean createCommonApp(Commonapp commonapp){
        boolean res = false;
        try{
            res = jp.create(commonapp);
        }catch(Exception ex){
            
        }finally{
            return res;
        }
    }
    
    public Commonapp getCommonApp(int id){
        Commonapp res = null;
        Query q;
        try{
            q = jp.createQuery("SELECT c FROM Commonapp c WHERE c.id = :id");
            q.setParameter("id", id);
            res = jp.getOneFromQuery(q);
        }catch(Exception ex){
           
        }finally{
            return res;
        }
    }
    
    public boolean deleteCommonapp(Commonapp c){
        boolean res = false;
        try{
            res = jp.delete(c);
        }catch(Exception ex){
            
        }finally{
            return res;
        }
    }
    
    public List<Commonapp> getAllCommonApp(){
        List<Commonapp> res = null;
        Query q;
        try{
            q = jp.createQuery("SELECT c FROM Commonapp c");
            res = jp.getManyFromQuery(q);
        }catch(Exception ex){
            
        }finally{
            return res;
        }
    }

}

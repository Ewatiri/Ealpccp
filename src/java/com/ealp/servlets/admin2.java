/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ealp.servlets;

import com.ealp.ejbs.Admin1EJB;
import com.ealp.ejbs.Scholar1;
import com.ealp.entities.Attendance;
import com.ealp.entities.CallFeedback;
import com.ealp.entities.CallList;
import com.ealp.entities.College;
import com.ealp.entities.CollegeEssay;
import com.ealp.entities.CollegeProfile;
import com.ealp.entities.Commonapp;
import com.ealp.entities.Document;
import com.ealp.entities.Event;
import com.ealp.entities.Mentor;
import com.ealp.entities.OtherResults;
import com.ealp.entities.SatResults;
import com.ealp.entities.Scholar;
import com.ealp.entities.Test;
import com.ealp.entities.Testlist;
import com.ealp.jpa.JTAProvider;
import com.ealp.utils.Calc;
import com.ealp.utils.Mail;
import com.google.gson.Gson;
import com.utilities.JsonResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 *
 * @author eva
 */
public class admin2 extends HttpServlet {

    @PersistenceContext(unitName = "EalpccpPU")
    private EntityManager em;

    @Resource
    UserTransaction userTx;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        DateFormat dtform = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat timeformat = new SimpleDateFormat("HH:mm");
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("addcallist")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            Mentor mentor = new Mentor();
            mentor.setUid(Integer.parseInt(session.getAttribute("uid").toString()));
            try {
                CallList c = new CallList();
                c.setCreatedby(mentor);
                c.setLastupdate(Integer.parseInt(session.getAttribute("uid").toString()));
                c.setCycle(admin1.getModule().getAdmcycle());
                c.setDescription(request.getParameter("desc"));
                c.setTitle(request.getParameter("title"));
                if (admin1.createCallist(c)) {
                    jr.setResponseCode(200);
                    jr.setMessage("Call List created successfully");
                } else {
                    jr.setResponseCode(400);
                    jr.setMessage("An error occured");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("getcallists")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                String cycle = admin1.getModule().getAdmcycle();
                List<CallList> callists = admin1.getCallists(cycle);
                if (!callists.isEmpty()) {
                    List q = new ArrayList();
                    for (CallList c : callists) {
                        HashMap<String, String> tmp = new HashMap<>();
                        tmp.put("id", c.getCid().toString());
                        tmp.put("title", c.getTitle());
                        tmp.put("edit", "0");
                        tmp.put("des", c.getDescription());
                        tmp.put("by", c.getCreatedby().getFname() + " " + c.getCreatedby().getSurname());
                        tmp.put("by1", c.getCreatedby().getUid().toString());
                        tmp.put("last", admin1.getMentor(c.getLastupdate()).getFname() + " " + admin1.getMentor(c.getLastupdate()).getSurname());
                        tmp.put("last1", Integer.toString(c.getLastupdate()));

                        q.add(tmp);
                    }
                    out.print(gson.toJson(q));
                } else {
                    jr.setResponseCode(400);
                    jr.setMessage("No callists at the moment");
                    out.print(gson.toJson(jr));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("fun")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            try {
                CallList c = new CallList();
                c.setCid(Integer.parseInt(request.getParameter("id")));
                c.setTitle(request.getParameter("title"));
                c.setCycle(admin1.getModule().getAdmcycle());
                c.setDescription(request.getParameter("des"));
                Mentor m = new Mentor();
                m.setUid(Integer.parseInt(request.getParameter("by1")));
                c.setLastupdate(Integer.parseInt(session.getAttribute("uid").toString()));
                c.setCreatedby(m);

                if (admin1.updateCallist(c)) {
                    jr.setResponseCode(200);
                    jr.setMessage("Call list updated successfully");
                } else {
                    jr.setResponseCode(400);
                    jr.setMessage("An error occured");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setMessage("An error occured");
                jr.setResponseCode(400);
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("deletecallist")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                CallList c = admin1.getCallist(Integer.parseInt(request.getParameter("id")));
                if (admin1.deleteCallist(c)) {
                    jr.setResponseCode(200);
                    jr.setMessage("Call list deleted successfully");
                } else {
                    jr.setResponseCode(400);
                    jr.setMessage("An error occured");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("addscholarlist")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                CallFeedback c = new CallFeedback();
                CallList cl = new CallList();
                cl.setCid(Integer.parseInt(request.getParameter("listid")));
                c.setCid(cl);
                Scholar s = new Scholar();
                s.setSid(Integer.parseInt(request.getParameter("sid")));
                c.setSid(s);
                c.setFeedback(request.getParameter("feedback"));
                c.setStatus(Integer.parseInt(request.getParameter("status")));
                if (admin1.addScholarToList(c)) {
                    jr.setResponseCode(200);
                    jr.setMessage("Feedback saved successfully");
                } else {
                    jr.setResponseCode(400);
                    jr.setMessage("An error occured");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("editfeedback")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                Scholar s = new Scholar();
                s.setSid(Integer.parseInt(request.getParameter("sid")));
                CallList c = new CallList();
                c.setCid(Integer.parseInt(request.getParameter("listid")));
                CallFeedback k = admin1.getCallfeedback(s, c);

                k.setFeedback(request.getParameter("feedback"));
                k.setStatus(Integer.parseInt(request.getParameter("status")));
                if (admin1.updateFeedback(k)) {
                    jr.setResponseCode(200);
                    jr.setMessage("Record updated successfully");
                } else {
                    jr.setResponseCode(400);
                    jr.setMessage("An error occured");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("addcollege")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                College c = new College();
                c.setName(request.getParameter("title"));
                c.setCommonapp(Integer.parseInt(request.getParameter("commonapp")));
                c.setMcf(Integer.parseInt(request.getParameter("mcf")));
                c.setEdeadline(request.getParameter("ed"));
                c.setRdeadline(request.getParameter("reg"));

                if (admin1.createCollege(c)) {
                    jr.setResponseCode(200);
                    jr.setMessage("College added successfully");
                } else {
                    jr.setResponseCode(400);
                    jr.setMessage("An error occured. Check to make sure the college doesn't exist before adding another instance");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("getcolleges")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                List<College> colleges = admin1.getColleges();
                if (!colleges.isEmpty()) {
                    List q = new ArrayList();
                    for (College c : colleges) {
                        HashMap<String, String> tmp = new HashMap<>();
                        tmp.put("id", c.getCollid().toString());
                        tmp.put("name", c.getName());
                        tmp.put("edit", "0");
                        tmp.put("ed", c.getEdeadline());
                        tmp.put("reg", c.getRdeadline());
                        tmp.put("mcf", c.getMcf().toString());
                        tmp.put("commonapp", c.getCommonapp().toString());
                        q.add(tmp);
                    }
                    out.print(gson.toJson(q));
                } else {
                    jr.setResponseCode(400);
                    jr.setMessage("No Colleges to display");
                    out.print(gson.toJson(jr));
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("updatecollege")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                College c = admin1.getCollege(Integer.parseInt(request.getParameter("id")));
                c.setName(request.getParameter("name"));
                c.setEdeadline(request.getParameter("ed"));
                c.setRdeadline(request.getParameter("reg"));
                c.setMcf(Integer.parseInt(request.getParameter("mcf")));
                c.setCommonapp(Integer.parseInt(request.getParameter("commonapp")));
                if (admin1.updateCollege(c)) {
                    jr.setResponseCode(200);
                    jr.setMessage("Record updated successfully");
                } else {
                    jr.setResponseCode(400);
                    jr.setMessage("An error occured");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
            } finally {
                out.print(gson.toJson(jr));
            }

        } else if (action.equalsIgnoreCase("addcollegessay")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            Mentor mentor = new Mentor();
            mentor.setUid(Integer.parseInt(session.getAttribute("uid").toString()));
            try {
                CollegeEssay ce = new CollegeEssay();
                ce.setAddedby(mentor);
                College c = new College();
                c.setCollid(Integer.parseInt(request.getParameter("college")));
                ce.setCycle(admin1.getModule().getAdmcycle());
                ce.setPrompt(request.getParameter("prompt"));
                ce.setRequired(Integer.parseInt(request.getParameter("req")));
                ce.setStatus(0);
                ce.setCollid(c);
                if (admin1.addEssay(ce)) {
                    jr.setResponseCode(200);
                    jr.setMessage("Essay prompt added successfully");
                } else {
                    jr.setResponseCode(400);
                    jr.setMessage("An error occured");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("getcollegessay")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));

            try {
                College c = new College();
                c.setCollid(Integer.parseInt(request.getParameter("id")));
                String cycle = admin1.getModule().getAdmcycle();
                List<CollegeEssay> ce = admin1.getCollegeEssays(c, cycle);
                if (!ce.isEmpty()) {
                    List q = new ArrayList();
                    for (CollegeEssay k : ce) {
                        HashMap<String, String> tmp = new HashMap<>();
                        tmp.put("id", k.getEssayid().toString());
                        tmp.put("collid", k.getCollid().getCollid().toString());
                        tmp.put("prompt", k.getPrompt());
                        tmp.put("cycle", k.getCycle());
                        tmp.put("status", k.getStatus().toString());
                        tmp.put("required", k.getRequired().toString());
                        tmp.put("edit", "0");
                        q.add(tmp);
                    }
                    out.print(gson.toJson(q));
                } else {
                    jr.setResponseCode(400);
                    jr.setMessage("No Essays");
                    out.print(gson.toJson(jr));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
                out.print(gson.toJson(jr));
            }

        } else if (action.equalsIgnoreCase("editcollegessay")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            Mentor mentor = new Mentor();
            mentor.setUid(Integer.parseInt(session.getAttribute("uid").toString()));

            try {
                CollegeEssay ce = new CollegeEssay();
                ce.setAddedby(mentor);
                College c = new College();
                c.setCollid(Integer.parseInt(request.getParameter("collid")));
                ce.setCollid(c);
                ce.setCycle(request.getParameter("cycle"));
                ce.setEssayid(Integer.parseInt(request.getParameter("id")));
                ce.setRequired(Integer.parseInt(request.getParameter("required")));
                ce.setPrompt(request.getParameter("prompt"));
                ce.setStatus(0);
                if (admin1.updateCollegeEssay(ce)) {
                    jr.setResponseCode(200);
                    jr.setMessage("Record updated successfully");
                } else {
                    jr.setResponseCode(400);
                    jr.setMessage("An error occured");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("deletecollegessay")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                CollegeEssay c = admin1.getCollegeEssay(Integer.parseInt(request.getParameter("id")));
                if (admin1.deleteCollegeEssay(c)) {
                    jr.setResponseCode(200);
                    jr.setMessage("Record deleted successfully");
                } else {
                    jr.setResponseCode(400);
                    jr.setMessage("An error occured");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("addtest")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                Test t = new Test();
                t.setTitle(request.getParameter("title"));
                t.setType(request.getParameter("type1"));
                t.setOfficial(Integer.parseInt(request.getParameter("type2")));
                t.setTestTime(timeformat.parse(request.getParameter("time")));
                //System.out.print(timeformat.parse(request.getParameter("time")));
                t.setTestDate(dtform.parse(request.getParameter("date")));
                t.setLocation(request.getParameter("location"));
                t.setCycle(admin1.getModule().getAdmcycle());
                if (admin1.createTest(t)) {
                    jr.setResponseCode(200);
                    jr.setMessage("Test created successfully");
                } else {
                    jr.setResponseCode(400);
                    jr.setMessage("An error occured");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("gettests")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                String cycle = admin1.getModule().getAdmcycle();
                List<Test> tests = admin1.getTests(cycle);
                if (!tests.isEmpty()) {
                    List q = new ArrayList();
                    for (Test t : tests) {
                        HashMap<String, String> tmp = new HashMap<>();
                        tmp.put("id", t.getTestid().toString());
                        tmp.put("title", t.getTitle());
                        tmp.put("edit", "0");
                        tmp.put("cycle", t.getCycle());
                        tmp.put("location", t.getLocation());
                        tmp.put("official", t.getOfficial().toString());
                        tmp.put("type", t.getType());
                        tmp.put("time", timeformat.format(t.getTestTime()));
                        tmp.put("date", dtform.format(t.getTestDate()));
                        q.add(tmp);
                    }
                    out.print(gson.toJson(q));
                } else {
                    jr.setResponseCode(400);
                    jr.setMessage("No tests");
                    out.print(gson.toJson(jr));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("updatetest")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                Test t = new Test();
                t.setTestid(Integer.parseInt(request.getParameter("id")));
                t.setTitle(request.getParameter("title"));
                t.setCycle(request.getParameter("cycle"));
                t.setTestTime(timeformat.parse(request.getParameter("time")));
                t.setTestDate(dtform.parse(request.getParameter("date")));
                t.setType(request.getParameter("type"));
                t.setLocation(request.getParameter("location"));
                t.setOfficial(Integer.parseInt(request.getParameter("official")));

                if (admin1.updateTest(t)) {
                    jr.setResponseCode(200);
                    jr.setMessage("Record updated successfully");
                } else {
                    jr.setResponseCode(400);
                    jr.setMessage("An error occured");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setResponseCode(400);
                jr.setMessage("An error occured");

            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("getallscholars")) {
            //JSONObject j = new JSONObject();
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);

            int id = (Integer.parseInt(session.getAttribute("uid").toString()));
            try {
                List<Scholar> scholars = admin1.getAllScholars(admin1.getModule().getAdmcycle());
                if (!scholars.isEmpty()) {
                    List q = new ArrayList();
                    for (Scholar s : scholars) {
                        if (s.getMentor() == null) {
                            JSONObject tmp = new JSONObject();
                            //tmp.
                            tmp.put("sid", s.getSid());
                            tmp.put("status", s.getStatus().toString());
                            tmp.put("name", s.getFname() + " " + s.getMname() + " " + s.getSurname());
                            List<CollegeProfile> cp = admin1.getColleges(s, admin1.getModule().getAdmcycle());
                            if (!cp.isEmpty()) {
                                JSONObject tmp1 = new JSONObject();

                                for (CollegeProfile c : cp) {
                                    tmp1.put("collegeid", c.getCollid().getCollid());
                                    tmp1.put("college", c.getCollid().getName());
                                }
                                List q1 = new ArrayList();
                                q1.add(tmp1);
                                tmp.put("colleges", q1);

                            }
                            q.add(tmp);

                        }

                    }
                    if (q.isEmpty()) {
                        jr.setMessage("No scholars");
                        jr.setResponseCode(400);
                        out.print(gson.toJson(jr));
                    } else {
                        out.print(q);
                    }

                } else {
                    jr.setMessage("No scholars");
                    jr.setResponseCode(400);
                    out.print(gson.toJson(jr));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setMessage("An error occured");
                jr.setResponseCode(400);
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("pickscholar")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));
            try {
                Scholar s = sch.getScholarDetails(Integer.parseInt(request.getParameter("sid")));

                Subject currentUser = SecurityUtils.getSubject();
                Session session = currentUser.getSession(true);
                Mentor mentor = new Mentor();
                mentor.setUid(Integer.parseInt(session.getAttribute("uid").toString()));

                s.setMentor(mentor);
                if (sch.updateScholar(s)) {
                    jr.setResponseCode(200);
                    jr.setMessage(request.getParameter("name") + " has been successfully assigned to you?");
                } else {
                    jr.setResponseCode(400);
                    jr.setMessage("Huh! An error occured");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setMessage("An error occured");
                jr.setResponseCode(400);
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("addsatscore")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                SatResults sr = new SatResults();
                sr.setS1(request.getParameter("s1"));
                sr.setS2(request.getParameter("s2"));
                sr.setS3(request.getParameter("s3"));
                Scholar s = new Scholar();
                s.setSid(Integer.parseInt(request.getParameter("sid")));
                sr.setSid(s);
                Test t = new Test();
                t.setTestid(Integer.parseInt(request.getParameter("testid")));
                sr.setTestid(t);
                sr.setScore1(Integer.parseInt(request.getParameter("score1")));
                sr.setScore2(Integer.parseInt(request.getParameter("score2")));
                sr.setScore3(Integer.parseInt(request.getParameter("score3")));

                if (admin1.addSatScore(sr)) {
                    jr.setMessage("Score uploaded successfully");
                    jr.setResponseCode(200);
                } else {
                    jr.setMessage("A scholar can't have two scores for one exam aye?");
                    jr.setResponseCode(400);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setMessage("An error occured");
                jr.setResponseCode(400);
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("addotherscore")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                OtherResults r = new OtherResults();
                r.setScore(Integer.parseInt(request.getParameter("score")));
                Scholar s = new Scholar();
                s.setSid(Integer.parseInt(request.getParameter("sid")));
                r.setSid(s);
                Test t = new Test();
                t.setTestid(Integer.parseInt(request.getParameter("testid")));
                r.setTestid(t);
                if (admin1.addOtherScore(r)) {
                    jr.setMessage("Score uploaded successfully");
                    jr.setResponseCode(200);
                } else {
                    jr.setMessage("A scholar can't have two scores for one exam aye?");
                    jr.setResponseCode(400);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setMessage("An error occured");
                jr.setResponseCode(400);
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("getmyscholars")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            Mentor mentor = new Mentor();
            mentor.setUid(Integer.parseInt(session.getAttribute("uid").toString()));

            try {
                List<Scholar> scholar = admin1.getMyScholars(mentor);
                if (!scholar.isEmpty()) {
                    List q = new ArrayList();
                    for (Scholar s : scholar) {
                        HashMap<String, String> tmp = new HashMap<>();
                        tmp.put("sid", s.getSid().toString());
                        tmp.put("name", s.getFname() + " " + s.getMname() + " " + s.getSurname());
                        tmp.put("status", s.getStatus().toString());

                        q.add(tmp);
                    }
                    out.print(gson.toJson(q));
                } else {
                    jr.setResponseCode(400);
                    jr.setMessage("You have no scholars are currently assigned to you");
                    out.print(gson.toJson(jr));
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("getapprovalessays")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                List<College> colleges = admin1.getColleges();
                if (!colleges.isEmpty()) {
                    List q = new ArrayList();
                    for (College c : colleges) {

                        List<CollegeEssay> ce = admin1.getApprovalEssays(c, admin1.getModule().getAdmcycle());
                        if (!ce.isEmpty()) {
                            JSONObject j = new JSONObject();
                            j.put("collid", c.getCollid().toString());
                            j.put("name", c.getName());
                            List essays = new ArrayList();
                            for (CollegeEssay ce1 : ce) {

                                HashMap<String, String> tmp = new HashMap<>();
                                tmp.put("essayid", ce1.getEssayid().toString());
                                tmp.put("addedby", ce1.getAddedby().getFname() + " " + ce1.getAddedby().getSurname());
                                tmp.put("addedby1", ce1.getAddedby().getUid().toString());
                                tmp.put("prompt", ce1.getPrompt());
                                tmp.put("status", ce1.getStatus().toString());
                                tmp.put("required", ce1.getRequired().toString());
                                tmp.put("edit", "0");
                                essays.add(tmp);

                                j.put("essays", essays);
                            }

                            q.add(j);
                        }

                    }
                    out.print(q);
                } else {
                    jr.setMessage("No colleges");
                    jr.setResponseCode(403);
                    out.print(gson.toJson(jr));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setMessage("An error occured");
                jr.setResponseCode(400);
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("approveessay")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                CollegeEssay ce = admin1.getCollegeEssay(Integer.parseInt(request.getParameter("essayid")));
                ce.setStatus(1);
                if (admin1.updateCollegeEssay(ce)) {
                    jr.setMessage("Essay approved!");
                    jr.setResponseCode(200);
                } else {
                    jr.setMessage("An error occured");
                    jr.setResponseCode(400);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setMessage("An error occured");
                jr.setResponseCode(400);

            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("rejectessay")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                Mail mail = new Mail();
                Mentor mentor = admin1.getMentor(Integer.parseInt(request.getParameter("mailto")));
                if (mail.sendMail(mentor.getEmail(), "localhost", request.getParameter("subject"), request.getParameter("message"))) {
                    CollegeEssay ce = admin1.getCollegeEssay(Integer.parseInt(request.getParameter("essayid")));
                    ce.setStatus(-1);
                    if (admin1.updateCollegeEssay(ce)) {
                        jr.setMessage("Message sent successfully");
                        jr.setResponseCode(200);
                    } else {
                        jr.setMessage("An error occured");
                        jr.setResponseCode(400);
                    }
                } else {
                    jr.setMessage("Huh? Mail Server trouble?");
                    jr.setResponseCode(400);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setMessage("An error occured");
                jr.setResponseCode(400);
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("getmentors")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);

            int myid = (Integer.parseInt(session.getAttribute("uid").toString()));

            try {
                List<Mentor> mentors = admin1.getMentors();
                if (!mentors.isEmpty() && mentors != null) {
                    List q = new ArrayList();
                    for (Mentor m : mentors) {
                        if (myid != m.getUid()) {
                            HashMap<String, String> tmp = new HashMap<>();
                            tmp.put("uid", m.getUid().toString());
                            tmp.put("name", m.getFname() + " " + m.getSurname());
                            tmp.put("class", m.getClass1().toString());
                            tmp.put("status", m.getStatus().toString());
                            q.add(tmp);
                        }

                    }
                    out.print(gson.toJson(q));
                } else {
                    jr.setMessage("No mentors currently");
                    jr.setResponseCode(403);
                    out.print(gson.toJson(jr));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setMessage("An error occured");
                jr.setResponseCode(400);
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("activateaccount")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                Mentor m = admin1.getMentor(Integer.parseInt(request.getParameter("uid")));
                m.setStatus(1);

                Random rand = new Random();
                int myRandomNumber = rand.nextInt(300000000) + 1000000000;
                String result = Integer.toHexString(myRandomNumber);
                m.setPassword((new Sha512Hash(result).toString()));

                Mail mail = new Mail();
                String msg = "Hi " + m.getFname() + "," + "<br/>" + "Your EALP CCP account has been activated.<br/>Your username is " + m.getEmail() + ".<br/> Your password is " + result + "<br/>College Counseling Team";
                if (mail.sendMail(m.getEmail(), "localhost", "EALP CCP Account", msg)) {
                    if (admin1.updateMentor(m)) {
                        jr.setMessage("Account activated successfully");
                        jr.setResponseCode(200);
                    } else {
                        jr.setMessage("An error occured");
                        jr.setResponseCode(400);
                    }
                } else {
                    jr.setMessage("Mail error");
                    jr.setResponseCode(400);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setMessage("An error occured");
                jr.setResponseCode(400);
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("delmentor")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));

            try {
                Mentor m = admin1.getMentor(Integer.parseInt(request.getParameter("uid")));

                List<Scholar> s = m.getScholarList();
                if (!s.isEmpty()) {
                    jr.setMessage("The account cannot be deactivated. " + m.getFname() + " still has scholars assigned to them.");
                    jr.setResponseCode(400);
                } else {
                    m.setStatus(-1);
                    if (admin1.updateMentor(m)) {
                        jr.setMessage("Account deactivated successfully");
                        jr.setResponseCode(200);
                    } else {
                        jr.setMessage("An error occured");
                        jr.setResponseCode(400);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setMessage("An error occured");
                jr.setResponseCode(400);
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("resetpassword")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                Mentor mentor = admin1.checkMentor(request.getParameter("email"));
                if (mentor == null) {
                    jr.setResponseCode(403);
                    jr.setMessage("User account not found");
                } else {
                    Random rand = new Random();
                    int myRandomNumber = rand.nextInt(30000000) + 10000000;
                    String result = Integer.toHexString(myRandomNumber);

                    mentor.setPassword((new Sha512Hash(result).toString()));
                    Mail m = new Mail();
                    String msg = "Hi " + mentor.getFname() + "," + "<br/>" + "Your username is " + mentor.getEmail() + ".<br/> Your password is " + result + "<br/>College Counseling Team";
                    if (m.sendMail(mentor.getEmail(), "localhost", "EALP CCP Account", msg)) {
                        if (admin1.updateMentor(mentor)) {
                            // send email
                            jr.setResponseCode(200);
                            jr.setMessage("Password reset successfully. A new password has been sent to your email account.");

                        } else {
                            jr.setResponseCode(400);
                            jr.setMessage("An error occured");
                        }
                    } else {
                        jr.setResponseCode(400);
                        jr.setMessage("Mail Server Issue");
                    }

                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("geteventlist")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                Event event = admin1.getEvent(Integer.parseInt(request.getParameter("id")));
                List<Attendance> attlist = event.getAttendanceList();
                if (!attlist.isEmpty()) {
                    List q = new ArrayList();
                    for (Attendance a : attlist) {
                        HashMap<String, String> tmp = new HashMap<>();
                        Scholar s = a.getSid();
                        tmp.put("name", s.getFname() + " " + s.getSurname());
                        tmp.put("location", s.getLocation());
                        tmp.put("phone", s.getMobile());
                        q.add(tmp);
                    }
                    out.print(gson.toJson(q));
                } else {
                    jr.setMessage("No attendants at the moment");
                    jr.setResponseCode(403);
                    out.print(gson.toJson(jr));
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setMessage("An error occured");
                jr.setResponseCode(400);
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("getdocs")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));

            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));

            try {
                Scholar s = new Scholar();
                s.setSid(Integer.parseInt(request.getParameter("sid")));
                List<Document> documents = sch.getDocuments(s);
                if (!documents.isEmpty()) {
                    List q = new ArrayList();
                    for (Document doc : documents) {
                        HashMap<String, String> tmp = new HashMap<>();
                        tmp.put("url", doc.getLocation());
                        tmp.put("status", doc.getStatus().toString());
                        if (doc.getType() != null) {
                            tmp.put("type", doc.getType().toString());
                        }
                        if (doc.getEssayid() != null) {
                            tmp.put("essayid", doc.getEssayid().getEssayid().toString());
                            tmp.put("college", doc.getEssayid().getCollid().getName());
                            tmp.put("prompt", doc.getEssayid().getPrompt());
                        }
                        q.add(tmp);
                    }
                    out.print(gson.toJson(q));
                } else {
                    jr.setResponseCode(403);
                    jr.setMessage("No documents");
                    out.print(gson.toJson(jr));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("getpasttest")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));

            Scholar s = new Scholar();
            s.setSid(Integer.parseInt(request.getParameter("sid")));

            Date date = new Date();

            try {
                List<Testlist> testlist = sch.getPastTestsList(s);
                List q = new ArrayList();
                if (testlist != null || !testlist.isEmpty()) {
                    for (Testlist tl : testlist) {
                        Test t = tl.getTestid();
                        if (t.getTestDate().before(date) && t.getOfficial() == 0) {
                            HashMap<String, String> tmp = new HashMap<>();

                            if (t.getType().equalsIgnoreCase("sat2") || t.getType().equalsIgnoreCase("sat1")) {
                                SatResults st = sch.getSatResults(t, s);
                                if (st == null) {
                                    tmp.put("testid", t.getTestid().toString());
                                    tmp.put("type", t.getType());
                                    tmp.put("title", t.getTitle());
                                    tmp.put("ttime", timeformat.format(t.getTestTime()));
                                    tmp.put("tdate", dtform.format(t.getTestDate()));
                                    tmp.put("location", t.getLocation());
                                    q.add(tmp);
                                }
                            } else {
                                OtherResults ot = sch.getOtherResults(t, s);
                                if (ot == null) {
                                    tmp.put("testid", t.getTestid().toString());
                                    tmp.put("type", t.getType());
                                    tmp.put("title", t.getTitle());
                                    tmp.put("ttime", timeformat.format(t.getTestTime()));
                                    tmp.put("tdate", dtform.format(t.getTestDate()));
                                    tmp.put("location", t.getLocation());
                                    q.add(tmp);
                                }
                            }

                        }
                    }
                    out.print(gson.toJson(q));
                } else {
                    jr.setMessage("No tests");
                    jr.setResponseCode(403);
                    out.print(gson.toJson(jr));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setMessage("An error occured");
                jr.setResponseCode(400);
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("getmyscholarcolleges")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                Scholar s = new Scholar();
                s.setSid(Integer.parseInt(request.getParameter("sid")));
                List<CollegeProfile> cp = admin1.getColleges(s, admin1.getModule().getAdmcycle());

                if (!cp.isEmpty()) {
                    List q = new ArrayList();

                    for (CollegeProfile c : cp) {
                        HashMap<String, String> tmp1 = new HashMap<>();
                        tmp1.put("collegeid", c.getCollid().getCollid().toString());
                        tmp1.put("college", c.getCollid().getName());
                        q.add(tmp1);
                    }
                    out.print(gson.toJson(q));
                } else {
                    jr.setMessage("The scholar is yet to pick colleges");
                    jr.setResponseCode(403);
                    out.print(gson.toJson(jr));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setMessage("An error occured");
                jr.setResponseCode(400);
                out.print(gson.toJson(jr));
            }

        } else if (action.equalsIgnoreCase("dropscholar")) {
            JsonResponse jr = new JsonResponse();

            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));

            try {
                Scholar s = sch.getScholarDetails(Integer.parseInt(request.getParameter("sid")));
                s.setMentor(null);
                if (sch.updateScholar(s)) {
                    jr.setMessage("You have been unassigned the scholar");
                    jr.setResponseCode(200);
                } else {
                    jr.setResponseCode(400);
                    jr.setMessage("An error has occured");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setMessage("An error has occured");
                jr.setResponseCode(400);
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("gettestscores")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));
            try {
                Scholar s = new Scholar();
                s.setSid(Integer.parseInt(request.getParameter("sid")));

                List<SatResults> satresults = sch.getSatResults(s);
                List q = new ArrayList();
                if (satresults != null) {
                    for (SatResults st : satresults) {
                        HashMap<String, String> tmp = new HashMap<>();
                        Test t = st.getTestid();
                        tmp.put("test", t.getTitle());
                        tmp.put("type", t.getType());
                        tmp.put("date", dtform.format(t.getTestDate()));
                        tmp.put("s1", st.getS1());
                        tmp.put("score1", st.getScore1().toString());
                        tmp.put("s2", st.getS2());
                        tmp.put("score2", st.getScore2().toString());
                        tmp.put("s3", st.getS3());
                        tmp.put("score3", st.getScore3().toString());
                        q.add(tmp);
                    }
                }
                List<OtherResults> otheresults = sch.getOtherResults(s);
                if (otheresults != null) {
                    for (OtherResults ot : otheresults) {
                        HashMap<String, String> tmp = new HashMap<>();
                        Test t = ot.getTestid();
                        tmp.put("test", t.getTitle());
                        tmp.put("type", t.getType());
                        tmp.put("date", dtform.format(t.getTestDate()));
                        tmp.put("score", ot.getScore().toString());
                        q.add(tmp);
                    }
                }
                if (q.isEmpty()) {
                    jr.setMessage("No test results yet");
                    jr.setResponseCode(403);
                    out.print(gson.toJson(jr));
                } else {
                    out.print(gson.toJson(q));

                }

            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setMessage("An error occured");
                jr.setResponseCode(400);
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("getmyscholaressays")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                Scholar s = sch.getScholarDetails(Integer.parseInt(request.getParameter("sid")));
                List<CollegeProfile> profile = s.getCollegeProfileList();
                if (!profile.isEmpty()) {
                    List q = new ArrayList();
                    for (CollegeProfile c : profile) {
                        HashMap<String, String> tmp = new HashMap<>();
                        List<CollegeEssay> ce = sch.getCollegeEssays(c.getCollid(), admin1.getModule().getAdmcycle());
                        tmp.put("college", c.getCollid().getName());
                        //tmp.put("",c)
                    }
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
            }
        } else if (action.equalsIgnoreCase("switchstd")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                Mentor m = admin1.getMentor(Integer.parseInt(request.getParameter("uid")));
                m.setClass1(2);
                if (admin1.updateMentor(m)) {
                    jr.setResponseCode(200);
                    jr.setMessage("Account switched successfully");
                } else {
                    jr.setMessage("An error occured");
                    jr.setResponseCode(400);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setMessage("An error occured");
                jr.setResponseCode(400);
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("switchadmin")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                Mentor m = admin1.getMentor(Integer.parseInt(request.getParameter("uid")));
                m.setClass1(1);
                if (admin1.updateMentor(m)) {
                    jr.setResponseCode(200);
                    jr.setMessage("Account switched successfully");
                } else {
                    jr.setMessage("An error occured");
                    jr.setResponseCode(400);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setMessage("An error occured");
                jr.setResponseCode(400);
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("geteventswithpeople")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                List<Event> events = admin1.getEvents(admin1.getModule().getAdmcycle());
                if (events != null && !events.isEmpty()) {
                    List q = new ArrayList();
                    for (Event e : events) {
                        List att = e.getAttendanceList();
                        if (!att.isEmpty()) {
                            HashMap<String, String> tmp = new HashMap<>();
                            tmp.put("id", e.getEid().toString());
                            tmp.put("title", e.getTitle());
                            tmp.put("desc", e.getDescription());
                            tmp.put("time", timeformat.format(e.getEtime()));
                            tmp.put("date", dtform.format(e.getEdate()));
                            tmp.put("venue", e.getVenue());
                            q.add(tmp);
                        }
                    }
                    out.print(gson.toJson(q));
                } else {
                    jr.setResponseCode(403);
                    jr.setMessage("No events currently");
                    out.print(gson.toJson(jr));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("gettestlist")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));

            try {
                List<Test> tests = admin1.getTests(admin1.getModule().getAdmcycle());
                if (tests != null && !tests.isEmpty()) {
                    List q = new ArrayList();
                    for (Test t : tests) {
                        List<Testlist> list = t.getTestlistList();
                        if (!list.isEmpty()) {
                            HashMap<String, String> tmp = new HashMap<>();
                            tmp.put("testid", t.getTestid().toString());
                            tmp.put("test", t.getTitle());
                            tmp.put("venue", t.getLocation());
                            tmp.put("time", timeformat.format(t.getTestTime()));
                            tmp.put("date", dtform.format(t.getTestDate()));
                            tmp.put("official", t.getOfficial().toString());
                            tmp.put("type", t.getType());
                            q.add(tmp);
                        }
                    }
                    out.print(gson.toJson(q));
                } else {
                    jr.setResponseCode(403);
                    jr.setMessage("The scolars have not signed up for any tests");
                    out.print(gson.toJson(jr));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("gettesttakers")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));

            try {
                Test test = admin1.getTest(Integer.parseInt(request.getParameter("testid")));
                List<Testlist> list = test.getTestlistList();
                List q = new ArrayList();
                if (!list.isEmpty()) {
                    for (Testlist t : list) {
                        HashMap<String, String> tmp = new HashMap<>();
                        tmp.put("name", t.getSid().getFname() + " " + t.getSid().getMname() + " " + t.getSid().getSurname());
                        tmp.put("phone", t.getSid().getMobile());
                        tmp.put("location", t.getSid().getLocation());
                        q.add(tmp);
                    }
                    out.print(gson.toJson(q));
                } else {
                    jr.setResponseCode(403);
                    jr.setMessage("Scholars are yet to sign up for the test");
                    out.print(gson.toJson(jr));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("gettestwithresults")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                List<Test> tests = admin1.getTests(admin1.getModule().getAdmcycle());
                List q = new ArrayList();
                if (tests != null && !tests.isEmpty()) {
                    for (Test t : tests) {
                        if (t.getTestDate().before(new Date())) {
                            List<Testlist> l = t.getTestlistList();
                            if (t.getType().equalsIgnoreCase("sat2") || t.getType().equalsIgnoreCase("sat1")) {
                                List<SatResults> satresults = t.getSatResultsList();
                                if (!satresults.isEmpty()) {
                                    HashMap<String, String> tmp = new HashMap<>();
                                    tmp.put("testid", t.getTestid().toString());
                                    tmp.put("test", t.getTitle());
                                    tmp.put("venue", t.getLocation());
                                    tmp.put("time", timeformat.format(t.getTestTime()));
                                    tmp.put("date", dtform.format(t.getTestDate()));
                                    tmp.put("official", t.getOfficial().toString());
                                    tmp.put("type", t.getType());
                                    q.add(tmp);

                                }
                            } else {
                                List<OtherResults> others = t.getOtherResultsList();
                                if (!others.isEmpty()) {
                                    HashMap<String, String> tmp = new HashMap<>();
                                    tmp.put("testid", t.getTestid().toString());
                                    tmp.put("test", t.getTitle());
                                    tmp.put("venue", t.getLocation());
                                    tmp.put("time", timeformat.format(t.getTestTime()));
                                    tmp.put("date", dtform.format(t.getTestDate()));
                                    tmp.put("official", t.getOfficial().toString());
                                    tmp.put("type", t.getType());
                                    q.add(tmp);
                                }
                            }
                        }
                    }
                    if (q.isEmpty()) {
                        jr.setResponseCode(403);
                        jr.setMessage("No results are out yet");
                        out.print(gson.toJson(jr));
                    } else {
                        out.print(gson.toJson(q));
                    }
                } else {
                    jr.setResponseCode(403);
                    jr.setMessage("There are no tests in this admission cycle");
                    out.print(gson.toJson(jr));
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setMessage("An error occured");
                jr.setResponseCode(400);
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("getresultlist")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                Test t = admin1.getTest(Integer.parseInt(request.getParameter("testid")));
                List q = new ArrayList();
                if (t.getOfficial() == 0) {
                    List<Testlist> list = t.getTestlistList();
                    for (Testlist tl : list) {
                        HashMap<String, String> tmp = new HashMap<>();
                        tmp.put("name", tl.getSid().getFname() + " " + tl.getSid().getMname() + " " + tl.getSid().getSurname());
                        if (t.getType().equalsIgnoreCase("sat1") || t.getType().equalsIgnoreCase("sat2")) {
                            SatResults sr = sch.getSatResults(t, tl.getSid());
                            if (sr == null) {
                                tmp.put("s1", "--");
                                tmp.put("score1", "--");
                                tmp.put("s2", "--");
                                tmp.put("score2", "--");
                                tmp.put("s3", "--");
                                tmp.put("score3", "--");
                                tmp.put("total", "--");
                            } else {
                                tmp.put("s1", sr.getS1());
                                tmp.put("score1", sr.getScore1().toString());
                                tmp.put("s2", sr.getS2());
                                tmp.put("score2", sr.getScore2().toString());
                                tmp.put("s3", sr.getS3());
                                tmp.put("score3", sr.getScore3().toString());
                                tmp.put("total", Integer.toString(sr.getScore1() + sr.getScore2() + sr.getScore3()));
                            }

                        } else {
                            OtherResults ot = sch.getOtherResults(t, tl.getSid());
                            if (ot == null) {
                                tmp.put("score", "--");
                            } else {
                                tmp.put("score", ot.getScore().toString());
                            }

                        }
                        q.add(tmp);
                    }
                    out.print(gson.toJson(q));
                } else {
                    List<Scholar> scholars = admin1.getDiagScholars(admin1.getModule().getAdmcycle());
                    for (Scholar s : scholars) {
                        HashMap<String, String> tmp = new HashMap<>();
                        tmp.put("name", s.getFname() + " " + s.getMname() + " " + s.getSurname());
                        if (t.getType().equalsIgnoreCase("sat1") || t.getType().equalsIgnoreCase("sat2")) {
                            SatResults sr = sch.getSatResults(t, s);
                            if (sr == null) {
                                tmp.put("s1", "--");
                                tmp.put("score1", "--");
                                tmp.put("s2", "--");
                                tmp.put("score2", "--");
                                tmp.put("s3", "--");
                                tmp.put("score3", "--");
                                tmp.put("total", "--");
                            } else {
                                tmp.put("s1", sr.getS1());
                                tmp.put("score1", sr.getScore1().toString());
                                tmp.put("s2", sr.getS2());
                                tmp.put("score2", sr.getScore2().toString());
                                tmp.put("s3", sr.getS3());
                                tmp.put("score3", sr.getScore3().toString());
                                tmp.put("total", Integer.toString(sr.getScore1() + sr.getScore2() + sr.getScore3()));
                            }

                        } else {
                            OtherResults ot = sch.getOtherResults(t, s);
                            if (ot == null) {
                                tmp.put("score", "--");
                            } else {
                                tmp.put("score", ot.getScore().toString());
                            }

                        }
                        q.add(tmp);
                    }
                    out.print(gson.toJson(q));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("gettestanalysis")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));

            try {
                List q = new ArrayList();
                Test t = admin1.getTest(Integer.parseInt(request.getParameter("testid")));
                HashMap<String, String> tmp = new HashMap<>();
                List<Scholar> scholars = null;
                //List<Testlist> list = null;
                int all = 0;
                int taken = 0;
                if (t.getOfficial() == 1) {
                    scholars = admin1.getDiagScholars(admin1.getModule().getAdmcycle());
                    all = scholars.size();
                } else {
                    all = t.getTestlistList().size();
                }
                Calc calc = new Calc();
                if (t.getType().equalsIgnoreCase("sat1") || t.getType().equalsIgnoreCase("sat2")) {
                    List<SatResults> sr = t.getSatResultsList();
                    taken = sr.size();
                    double[] total = new double[sr.size()];
                    double[] sect1 = new double[sr.size()];
                    double[] sect2 = new double[sr.size()];
                    double[] sect3 = new double[sr.size()];
                    for (int i = 0; i < sr.size(); i++) {
                        SatResults r = sr.get(i);
                        total[i] = r.getScore1() + r.getScore2() + r.getScore3();
                        sect1[i] = r.getScore1();
                        sect2[i] = r.getScore2();
                        sect3[i] = r.getScore3();
                    }
                    double mean = calc.getMean(total);
                    double median = calc.getMedian(total);
                    if (t.getType().equalsIgnoreCase("sat1")) {
                        double s1mean = calc.getMean(sect1);
                        double s2mean = calc.getMean(sect2);
                        double s3mean = calc.getMean(sect3);

                        double s1median = calc.getMedian(sect1);
                        double s2median = calc.getMedian(sect2);
                        double s3median = calc.getMedian(sect3);

                        tmp.put("s1mean", Double.toString(s1mean));
                        tmp.put("s2mean", Double.toString(s2mean));
                        tmp.put("s3mean", Double.toString(s3mean));
                        tmp.put("s1median", Double.toString(s1median));
                        tmp.put("s2median", Double.toString(s2median));
                        tmp.put("s3median", Double.toString(s3median));
                    }
                    tmp.put("all", Integer.toString(all));
                    tmp.put("taken", Integer.toString(taken));
                    tmp.put("mean", Double.toString(mean));
                    tmp.put("median", Double.toString(median));
                } else {
                    List<OtherResults> ot = t.getOtherResultsList();
                    taken = ot.size();
                    double[] total = new double[ot.size()];
                    for (int i = 0; i < ot.size(); i++) {
                        OtherResults r = ot.get(i);
                        total[i] = r.getScore();
                    }

                    double mean = calc.getMean(total);
                    double median = calc.getMedian(total);
                    tmp.put("all", Integer.toString(all));
                    tmp.put("taken", Integer.toString(taken));
                    tmp.put("mean", Double.toString(mean));
                    tmp.put("median", Double.toString(median));
                }
                q.add(tmp);
                out.print(gson.toJson(q));
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setMessage("An error occured");
                jr.setresponseCode(400);
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("getscholarbytype")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                List<Scholar> scholars = null;
                String type = request.getParameter("type");
                if (type.equalsIgnoreCase("all")) {
                    scholars = admin1.getScholars(admin1.getModule().getAdmcycle());
                } else if (type.equalsIgnoreCase("mcf")) {
                    scholars = admin1.getScholarsbyType(admin1.getModule().getAdmcycle(), 20);
                } else if (type.equalsIgnoreCase("mcfreg")) {
                    scholars = admin1.getScholarsbyType(admin1.getModule().getAdmcycle(), 11);
                } else if (type.equalsIgnoreCase("reg")) {
                    scholars = admin1.getScholarsbyType(admin1.getModule().getAdmcycle(), 10);
                }

                if (scholars != null && !scholars.isEmpty()) {
                    List q = new ArrayList();
                    for (Scholar s : scholars) {
                        HashMap<String, String> tmp = new HashMap<>();
                        tmp.put("name", s.getFname() + " " + s.getMname() + " " + s.getSurname());
                        if (s.getStatus() == 10) {
                            tmp.put("type", "Regular Only");
                        } else if (s.getStatus() == 11) {
                            tmp.put("type", "Regular + MCF");
                        } else if (s.getStatus() == 20) {
                            tmp.put("type", "MCF Only");
                        }
                        q.add(tmp);
                    }
                    out.print(gson.toJson(q));
                } else {
                    jr.setMessage("There are no scholars at the moment");
                    jr.setResponseCode(403);
                    out.print(gson.toJson(jr));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("getscholarsbycollege")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                List<College> matches = admin1.getCollegebyName(request.getParameter("college"));
                if (matches.size() != 1) {
                    if (matches.size() == 0) {
                        jr.setResponseCode(401);
                        jr.setMessage("College not found");
                        out.print(gson.toJson(jr));
                    } else {
                        jr.setResponseCode(401);
                        jr.setMessage("Please refine your search. More than one college match the name provided");
                        out.print(gson.toJson(jr));
                    }
                    //System.out.print(matches.size());

                } else {
                    College col = null;
                    for (College c : matches) {
                        col = c;
                    }
                    List<CollegeProfile> collegeprofile = null;
                    String type = request.getParameter("type");
                    if (type.equalsIgnoreCase("all")) {
                        collegeprofile = admin1.getCollegeProfile(col, admin1.getModule().getAdmcycle());
                    } else {
                        collegeprofile = admin1.getProfilesbyType(col, admin1.getModule().getAdmcycle(), type);
                    }

                    if (collegeprofile != null && !collegeprofile.isEmpty()) {
                        List q = new ArrayList();
                        for (CollegeProfile k : collegeprofile) {
                            HashMap<String, String> tmp = new HashMap<>();
                            tmp.put("name", k.getSid().getFname() + " " + k.getSid().getMname() + " " + k.getSid().getSurname());
                            if (k.getDecisionType() == null) {
                                tmp.put("type", "--");
                            } else {
                                tmp.put("type", k.getDecisionType().toUpperCase());
                            }

                            q.add(tmp);
                        }
                        out.print(gson.toJson(q));
                    } else {
                        jr.setResponseCode(403);
                        jr.setMessage("No applicants to the selected college at the moment under the selected program");
                        out.print(gson.toJson(jr));
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (action.equalsIgnoreCase("getappstatus")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                List<CollegeProfile> collegeprofile = null;
                String type = request.getParameter("type");
                if (type.equalsIgnoreCase("all")) {
                    collegeprofile = admin1.getCurrentProfile(admin1.getModule().getAdmcycle());
                } else if (type.equalsIgnoreCase("yet")) {
                    collegeprofile = admin1.getProfilebyStatus(admin1.getModule().getAdmcycle(), 0);
                } else if (type.equalsIgnoreCase("complete")) {
                    collegeprofile = admin1.getProfilebyStatus(admin1.getModule().getAdmcycle(), 3);
                } else if (type.equalsIgnoreCase("accepted")) {
                    collegeprofile = admin1.getProfilebyStatus(admin1.getModule().getAdmcycle(), 1);
                } else if (type.equalsIgnoreCase("rej")) {
                    collegeprofile = admin1.getProfilebyStatus(admin1.getModule().getAdmcycle(), 2);
                }
                List q = new ArrayList();
                if (collegeprofile != null && !collegeprofile.isEmpty()) {
                    for (CollegeProfile c : collegeprofile) {
                        HashMap<String, String> tmp = new HashMap<>();
                        tmp.put("name", c.getSid().getFname() + " " + c.getSid().getMname() + " " + c.getSid().getSurname());
                        tmp.put("college", c.getCollid().getName());
                        if (c.getDecisionType() == null) {
                            tmp.put("type", "--");
                        } else {
                            tmp.put("type", c.getDecisionType().toUpperCase());
                        }
                        if (c.getDecision() == null) {
                            tmp.put("status", "--");
                        } else if (c.getDecision() == 0) {
                            tmp.put("status", "Yet to submit");
                        } else if (c.getDecision() == 1) {
                            tmp.put("status", "Accepted");
                        } else if (c.getDecision() == 2) {
                            tmp.put("status", "Not Accepted");
                        } else if (c.getDecision() == 3) {
                            tmp.put("status", "Complete awaiting college decision");
                        }
                        q.add(tmp);

                    }
                    out.print(gson.toJson(q));
                } else {
                    jr.setResponseCode(403);
                    jr.setMessage("No applications have been made for this admission cycle");
                    out.print(gson.toJson(jr));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("setstatus")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));
            try {
                Scholar s = sch.getScholarDetails(Integer.parseInt(request.getParameter("sid")));
                s.setStatus(Integer.parseInt(request.getParameter("status")));
                s.setReason(request.getParameter("reason"));
                if (s.getStatus() == -1) {
                    s.setMentor(null);
                }
                if (sch.updateScholar(s)) {
                    jr.setResponseCode(200);
                    jr.setMessage("Record updated successfully");
                } else {
                    jr.setResponseCode(400);
                    jr.setMessage("An error occured");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("addcommonapp")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                Commonapp c = new Commonapp();
                c.setPrompt(request.getParameter("prompt"));
                if (admin1.createCommonApp(c)) {
                    jr.setresponseCode(200);
                    jr.setMessage("Essay added successfully");

                } else {
                    jr.setresponseCode(400);
                    jr.setMessage("An error occured");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setMessage("An error occured");
                jr.setResponseCode(400);
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("getallcommonapp")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                List<Commonapp> prompts = admin1.getAllCommonApp();
                if (prompts != null && !prompts.isEmpty()) {
                    List q = new ArrayList();
                    for (Commonapp c : prompts) {
                        HashMap<String, String> tmp = new HashMap<>();
                        tmp.put("id", c.getId().toString());
                        tmp.put("prompt", c.getPrompt());
                        tmp.put("edit", "1");
                        q.add(tmp);
                    }
                    out.print(gson.toJson(q));
                } else {
                    jr.setMessage("No essays");
                    jr.setResponseCode(403);
                    out.print(gson.toJson(jr));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setMessage("An error occured");
                jr.setResponseCode(400);
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("deletecommonapp")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                Commonapp c = admin1.getCommonApp(Integer.parseInt(request.getParameter("id")));
                if (admin1.deleteCommonapp(c)) {
                    jr.setResponseCode(200);
                    jr.setMessage("Prompt deleted successfully");
                } else {
                    jr.setResponseCode(400);
                    jr.setMessage("An error occured");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
            } finally {
                out.print(gson.toJson(jr));
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

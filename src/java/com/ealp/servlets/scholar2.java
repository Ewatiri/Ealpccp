/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ealp.servlets;

import com.ealp.ejbs.Admin1EJB;
import com.ealp.ejbs.Scholar1;
import com.ealp.entities.Attendance;
import com.ealp.entities.College;
import com.ealp.entities.CollegeEssay;
import com.ealp.entities.CollegeProfile;
import com.ealp.entities.Document;
import com.ealp.entities.Event;
import com.ealp.entities.OtherResults;
import com.ealp.entities.SatResults;
import com.ealp.entities.Scholar;
import com.ealp.entities.Test;
import com.ealp.entities.Testlist;
import com.ealp.jpa.JTAProvider;
import com.ealp.utils.Mail;
import com.google.gson.Gson;
import com.utilities.JsonResponse;
import java.io.File;
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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 *
 * @author eva
 */
public class scholar2 extends HttpServlet {

    @PersistenceContext(unitName = "EalpccpPU")
    private EntityManager em;

    @Resource
    UserTransaction userTx;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        Gson gson = new Gson();
        String action;
        DateFormat dtform = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat timeformat = new SimpleDateFormat("HH:mm");
        action = request.getParameter("action");
        if (action.equalsIgnoreCase("getscholarevents")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            int id = Integer.parseInt(session.getAttribute("sid").toString());
            Scholar s = new Scholar();
            s.setSid(id);
            try {
                List<Event> events = sch.getEvents(new Date(), admin1.getModule().getAdmcycle());
                List<Attendance> att = sch.getAttendance(s);
                if (!events.isEmpty()) {
                    List q = new ArrayList();
                    for (Event e : events) {
                        HashMap<String, String> tmp = new HashMap<>();
                        if (att != null) {
                            for (Attendance a : att) {
                                if (a.getEid().getEid() == e.getEid()) {
                                    tmp.put("going", "1");
                                }
                            }
                        } else {
                            tmp.put("going", "0");
                        }

                        tmp.put("eventid", e.getEid().toString());
                        tmp.put("title", e.getTitle());
                        tmp.put("venue", e.getVenue());
                        tmp.put("description", e.getDescription());
                        tmp.put("edate", dtform.format(e.getEdate()));
                        tmp.put("etime", timeformat.format(e.getEtime()));
                        q.add(tmp);
                    }
                    out.print(gson.toJson(q));
                } else {
                    jr.setResponseCode(400);
                    jr.setMessage("No events");
                    out.print(gson.toJson(jr));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
                out.print(gson.toJson(jr));
            }

        } else if (action.equalsIgnoreCase("resetpassword")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));

            try {
                Scholar scholar = sch.checkScholar(request.getParameter("email"));
                if (scholar == null) {
                    jr.setResponseCode(403);
                    jr.setMessage("Account does not exist. Create a new account.");
                } else {
                    Random rand = new Random();
                    int myRandomNumber = rand.nextInt(30000000) + 10000000;
                    String result = Integer.toHexString(myRandomNumber);

                    scholar.setPassword((new Sha512Hash(result).toString()));

                    Mail m = new Mail();
                    String msg = "Hi " + scholar.getFname() + "," + "<br/>" + "Your username is " + scholar.getEmail() + ".<br/> Your password is " + result + "<br/>College Counseling Team";

                    if (m.sendMail(scholar.getEmail(), "localhost", "EALP CCP Account", msg)) {
                        if (sch.updateScholar(scholar)) {
                            jr.setResponseCode(200);
                            jr.setMessage("Password reset successfully. A new password has been sent to your email account.");
                        } else {
                            jr.setResponseCode(400);
                            jr.setMessage("An error occured");
                        }
                    } else {
                        jr.setResponseCode(401);
                        jr.setMessage("Mail error");
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
            } finally {
                out.print(gson.toJson(jr));
            }

        } else if (action.equalsIgnoreCase("attend")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            int id = Integer.parseInt(session.getAttribute("sid").toString());
            Scholar s = new Scholar();
            s.setSid(id);
            try {
                Attendance att = new Attendance();
                Event e = new Event();
                e.setEid(Integer.parseInt(request.getParameter("eventid")));
                att.setEid(e);
                att.setSid(s);
                if (sch.addAttendance(att)) {
                    jr.setResponseCode(200);
                    jr.setMessage("See you at " + request.getParameter("title"));
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
        } else if (action.equalsIgnoreCase("notattending")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            int id = Integer.parseInt(session.getAttribute("sid").toString());
            Scholar s = new Scholar();
            s.setSid(id);

            try {
                Event e = new Event();
                e.setEid(Integer.parseInt(request.getParameter("eventid")));
                Attendance att = sch.getAttendance(s, e);
                if (sch.deleteAttendance(att)) {
                    jr.setMessage("Record updated successfully");
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

        } else if (action.equalsIgnoreCase("gettests")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            int id = Integer.parseInt(session.getAttribute("sid").toString());
            Scholar s = new Scholar();
            s.setSid(id);

            try {
                List<Test> tests = sch.getTests(new Date(), admin1.getModule().getAdmcycle());
                List<Testlist> list = sch.getTestList(s);
                if (!tests.isEmpty()) {
                    List q = new ArrayList();
                    for (Test t : tests) {
                        HashMap<String, String> tmp = new HashMap<>();
                        if (list != null) {
                            for (Testlist l : list) {
                                if (l.getTestid().getTestid() == t.getTestid()) {
                                    tmp.put("going", "1");
                                }
                            }
                        }
                        tmp.put("testid", t.getTestid().toString());
                        tmp.put("title", t.getTitle());
                        tmp.put("location", t.getLocation());
                        tmp.put("type", t.getType());
                        tmp.put("official", t.getOfficial().toString());
                        tmp.put("tdate", dtform.format(t.getTestDate()));
                        tmp.put("ttime", timeformat.format(t.getTestTime()));
                        q.add(tmp);
                    }
                    out.print(gson.toJson(q));
                } else {
                    jr.setResponseCode(400);
                    jr.setMessage("No Tests");
                    out.print(gson.toJson(jr));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("registertest")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            int id = Integer.parseInt(session.getAttribute("sid").toString());
            Scholar s = new Scholar();
            s.setSid(id);

            try {
                Testlist tl = new Testlist();
                tl.setSid(s);
                Test t = new Test();
                t.setTestid(Integer.parseInt(request.getParameter("testid")));
                tl.setTestid(t);
                if (sch.addtoTestList(tl)) {
                    jr.setResponseCode(200);
                    jr.setMessage("You have successfully registered for the test. All the best!");
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
        } else if (action.equalsIgnoreCase("withdrawtest")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            int id = Integer.parseInt(session.getAttribute("sid").toString());
            Scholar s = new Scholar();
            s.setSid(id);

            try {
                Test t = new Test();
                t.setTestid(Integer.parseInt(request.getParameter("testid")));
                Testlist tl = sch.getTestList(t, s);
                if (sch.deleteTestlist(tl)) {
                    jr.setResponseCode(200);
                    jr.setMessage("You have unregistered successfully?");
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
        } else if (action.equalsIgnoreCase("getpasttests")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            int id = Integer.parseInt(session.getAttribute("sid").toString());
            Scholar s = new Scholar();
            s.setSid(id);

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
        } else if (action.equalsIgnoreCase("getresults")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            int id = Integer.parseInt(session.getAttribute("sid").toString());
            Scholar s = new Scholar();
            s.setSid(id);

            try {
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
        } else if (action.equalsIgnoreCase("addsatresult")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            int id = Integer.parseInt(session.getAttribute("sid").toString());
            Scholar s = new Scholar();
            s.setSid(id);

            SatResults st = new SatResults();
            try {
                Test t = new Test();
                t.setTestid(Integer.parseInt(request.getParameter("testid")));
                st.setS1(request.getParameter("s1"));
                st.setS2(request.getParameter("s2"));
                st.setS3(request.getParameter("s3"));
                st.setScore1(Integer.parseInt(request.getParameter("score1")));
                st.setScore2(Integer.parseInt(request.getParameter("score2")));
                st.setScore3(Integer.parseInt(request.getParameter("score3")));
                st.setSid(s);
                st.setTestid(t);
                if (admin1.addSatScore(st)) {
                    jr.setResponseCode(200);
                    jr.setMessage("Results uploaded successfully");
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
        } else if (action.equalsIgnoreCase("addotheresult")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            int id = Integer.parseInt(session.getAttribute("sid").toString());
            Scholar s = new Scholar();
            s.setSid(id);

            OtherResults ot = new OtherResults();
            try {
                Test t = new Test();
                t.setTestid(Integer.parseInt(request.getParameter("testid")));
                ot.setScore(Integer.parseInt(request.getParameter("score")));
                ot.setTestid(t);
                ot.setSid(s);
                if (admin1.addOtherScore(ot)) {
                    jr.setResponseCode(200);
                    jr.setMessage("Results uploaded successfully");
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
        } else if (action.equalsIgnoreCase("getcolleges")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            int id = Integer.parseInt(session.getAttribute("sid").toString());
            Scholar s = sch.getScholarDetails(id);

            try {
                List<College> colleges = admin1.getColleges();
                List<CollegeProfile> profile = admin1.getColleges(s, admin1.getModule().getAdmcycle());
                if (!colleges.isEmpty() && colleges != null) {
                    List q = new ArrayList();
                    for (College college : colleges) {
                        HashMap<String, String> tmp = new HashMap<>();
                        tmp.put("id", college.getCollid().toString());
                        tmp.put("name", college.getName());
                        tmp.put("ed", college.getEdeadline());
                        tmp.put("reg", college.getRdeadline());
                        tmp.put("commonapp", college.getCommonapp().toString());
                        tmp.put("mcf", college.getMcf().toString());
                        List<CollegeEssay> ce = sch.getCollegeEssays(college, admin1.getModule().getAdmcycle());
                        if (ce.isEmpty()) {
                            tmp.put("essays", "0");
                        } else {
                            tmp.put("essays", "1");
                        }
                        if (s.getStatus() == 20 && college.getMcf() == 0) {
                            tmp.put("allowed", "0");
                        } else if (s.getStatus() == 10 && college.getMcf() == 1) {
                            tmp.put("allowed", "0");
                        } else {
                            tmp.put("allowed", "1");
                        }
                        if (!profile.isEmpty()) {
                            for (CollegeProfile cp : profile) {
                                if (cp.getCollid().getCollid() == college.getCollid()) {
                                    tmp.put("mycollege", "1");
                                    if (cp.getDecision() != null) {
                                        tmp.put("decision", cp.getDecision().toString());
                                        tmp.put("type", cp.getDecisionType());
                                        tmp.put("edit", "1");
                                    }
                                }
                            }
                        }
                        q.add(tmp);
                    }
                    out.print(gson.toJson(q));
                } else {
                    jr.setMessage("No colleges have been added to the system. Please contact the System Adminstrator.");
                    jr.setResponseCode(403);
                    out.print(gson.toJson(jr));
                }
            } catch (Exception ex) {
                jr.setMessage("An error occured");
                jr.setResponseCode(400);
                ex.printStackTrace();
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("getcollegessays")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));

            try {
                College c = new College();
                c.setCollid(Integer.parseInt(request.getParameter("collegeid")));
                List<CollegeEssay> essays = sch.getCollegeEssays(c, admin1.getModule().getAdmcycle());
                if (essays != null) {
                    List q = new ArrayList();
                    for (CollegeEssay essay : essays) {
                        HashMap<String, String> tmp = new HashMap<>();
                        tmp.put("prompt", essay.getPrompt());
                        tmp.put("required", essay.getRequired().toString());
                        q.add(tmp);
                    }
                    out.print(gson.toJson(q));
                } else {
                    jr.setMessage("Another error?");
                    jr.setResponseCode(403);
                    out.print(gson.toJson(jr));
                }
            } catch (Exception ex) {
                jr.setMessage("An error occured");
                jr.setResponseCode(400);
                ex.printStackTrace();
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("pickcollege")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            int id = Integer.parseInt(session.getAttribute("sid").toString());
            Scholar s = sch.getScholarDetails(id);

            try {
                CollegeProfile cp = new CollegeProfile();
                College c = new College();
                c.setCollid(Integer.parseInt(request.getParameter("collegeid")));
                cp.setCollid(c);
                cp.setSid(s);
                cp.setCycle(admin1.getModule().getAdmcycle());

                if (sch.createProfile(cp)) {
                    jr.setResponseCode(200);
                    jr.setMessage("College added successfully");
                } else {
                    jr.setResponseCode(400);
                    jr.setMessage("An error occured");
                }
            } catch (Exception ex) {
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
                ex.printStackTrace();
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("dropcollege")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            int id = Integer.parseInt(session.getAttribute("sid").toString());
            Scholar s = sch.getScholarDetails(id);

            try {
                College c = new College();
                c.setCollid(Integer.parseInt(request.getParameter("collegeid")));
                CollegeProfile cp = sch.getCollegeProfile(c, s);
                if (sch.deleteCollegeProfile(cp)) {
                    jr.setResponseCode(200);
                    jr.setMessage("College removed from your list successfully");
                } else {
                    jr.setResponseCode(400);
                    jr.setMessage("An error occured");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setResponseCode(403);
                jr.setMessage("An error occured");
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("addecision")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            int id = Integer.parseInt(session.getAttribute("sid").toString());
            Scholar s = sch.getScholarDetails(id);

            try {
                College c = new College();
                c.setCollid(Integer.parseInt(request.getParameter("id")));
                CollegeProfile cp = sch.getCollegeProfile(c, s);
                cp.setDecisionType(request.getParameter("type"));
                cp.setDecision(Integer.parseInt(request.getParameter("decision")));
                // check essays
                if (sch.updateCollegeProfile(cp)) {
                    jr.setMessage("Record updated successfully");
                    jr.setResponseCode(200);
                } else {
                    jr.setMessage("An error occured");
                    jr.setResponseCode(400);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("getdocuments")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            int id = Integer.parseInt(session.getAttribute("sid").toString());
            Scholar s = sch.getScholarDetails(id);

            try {
                List<Document> documents = sch.getDocuments(s);
                if (!documents.isEmpty()) {
                    List q = new ArrayList();
                    for (Document doc : documents) {
                        HashMap<String, String> tmp = new HashMap<>();
                        tmp.put("location", doc.getLocation());
                        if (doc.getType() != null) {
                            tmp.put("type", doc.getType().toString());
                        }
                        if (doc.getEssayid() != null) {
                            tmp.put("id", doc.getEssayid().getEssayid().toString());
                        }
                        tmp.put("version", Integer.toString(doc.getVersion()));
                        if (doc.getStatus() == 2) {
                            tmp.put("edit", "1");
                        } else {
                            tmp.put("edit", "0");
                        }
                        tmp.put("status", doc.getStatus().toString());

                        q.add(tmp);
                    }
                    out.print(gson.toJson(q));
                } else {
                    jr.setMessage("No documents to display");
                    jr.setResponseCode(403);
                    out.print(gson.toJson(jr));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setMessage("An error occured");
                jr.setResponseCode(400);
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("deletedocument")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            int id = Integer.parseInt(session.getAttribute("sid").toString());
            Scholar s = sch.getScholarDetails(id);

            try {
                if (request.getParameter("current") != null) {
                    Document old = sch.getDocument(s, Integer.parseInt(request.getParameter("type")));
                    if (sch.deleteDocument(old)) {
                        File f = new File(request.getParameter("loc"));
                        if (f.delete()) {
                            jr.setResponseCode(200);
                            jr.setMessage("File deleted successfully");
                        } else {
                            jr.setResponseCode(400);
                            jr.setMessage("An error has occured");
                        }
                    } else {
                        jr.setResponseCode(400);
                        jr.setMessage("An error has occured");
                    }
                } else {
                    File f = new File(request.getParameter("loc"));
                    if (f.delete()) {
                        jr.setResponseCode(200);
                        jr.setMessage("File deleted successfully");
                    } else {
                        jr.setResponseCode(400);
                        jr.setMessage("An error has occured");
                    }
                }

                /*if (sch.deleteDocument(old)) {
                 for (int k = 0; k <= old.getVersion(); k++) {
                 int index1 = old.getLocation().substring(30).indexOf("_");
                 String nm = old.getLocation().substring(30).substring(0, index1);
                 int y = old.getLocation().substring(30).indexOf(".");
                 String et = old.getLocation().substring(30).substring(y);
                 String fname = "/var/webapp/" + nm + "_" + k + et;

                 File f = new File(fname);
                 f.delete();
                 }
                 jr.setResponseCode(200);
                 jr.setMessage("Document deleted successfully");
                 } else {
                 jr.setMessage("An error occured");
                 jr.setResponseCode(400);
                 }*/
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setMessage("An error occured");
                jr.setResponseCode(400);
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("getmyessays")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            int id = Integer.parseInt(session.getAttribute("sid").toString());
            Scholar s = sch.getScholarDetails(id);

            try {
                College c = new College();
                c.setCollid(Integer.parseInt(request.getParameter("id")));
                List<CollegeEssay> ce = sch.getCollegeEssays(c, admin1.getModule().getAdmcycle());
                if (!ce.isEmpty()) {
                    List q = new ArrayList();
                    for (CollegeEssay e : ce) {
                        HashMap<String, String> tmp = new HashMap<>();
                        tmp.put("essayid", e.getEssayid().toString());
                        tmp.put("collid", e.getCollid().getCollid().toString());
                        tmp.put("prompt", e.getPrompt());
                        tmp.put("required", e.getRequired().toString());
                        q.add(tmp);
                    }
                    out.print(gson.toJson(q));
                } else {
                    jr.setMessage("The college has no essays uploaded yet");
                    jr.setResponseCode(403);
                    out.print(gson.toJson(jr));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setMessage("An error has occured");
                jr.setResponseCode(400);
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("getdochist")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            int id = Integer.parseInt(session.getAttribute("sid").toString());
            Scholar s = sch.getScholarDetails(id);

            try {
                Document old = sch.getDocument(s, Integer.parseInt(request.getParameter("id")));
                if (old == null) {
                    jr.setMessage("No Existing history");
                    jr.setResponseCode(403);
                    out.print(gson.toJson(jr));
                } else {
                    List q = new ArrayList();
                    for (int k = 0; k <= old.getVersion(); k++) {
                        int index1 = old.getLocation().substring(30).indexOf("_");
                        String nm = old.getLocation().substring(30).substring(0, index1);
                        int y = old.getLocation().substring(30).indexOf(".");
                        String et = old.getLocation().substring(30).substring(y);
                        String fname = "/var/webapp/" + nm + "_" + k + et;

                        File f = new File(fname);
                        if (f.exists()) {
                            HashMap<String, String> tmp = new HashMap<>();
                            if (k == old.getVersion()) {
                                if (old.getStatus() == 1) {
                                    tmp.put("edit", "0");
                                }
                                tmp.put("current", "0");
                            }

                            tmp.put("url", old.getLocation());
                            tmp.put("loc", fname);
                            tmp.put("type", old.getType().toString());
                            q.add(tmp);
                        }
                        //f.delete();
                    }
                    out.print(gson.toJson(q));
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setMessage("An error has occured");
                jr.setResponseCode(400);
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("checkessayfeedback")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            int id = Integer.parseInt(session.getAttribute("sid").toString());
            Scholar s = sch.getScholarDetails(id);

            try {
                CollegeEssay ce = new CollegeEssay();
                ce.setEssayid(Integer.parseInt(request.getParameter("essayid")));
                Document doc = sch.getCollegeEssay(s, ce);
                if (doc != null) {
                    if (doc.getStatus() == 1 || doc.getStatus() == 2) {
                        jr.setResponseCode(200);
                        jr.setMessage("There's feedback!");
                        jr.setDescription(doc.getLocation());
                    } else {
                        jr.setResponseCode(403);
                        jr.setMessage("No feedback yet");

                    }
                } else {
                    jr.setMessage("You're yet to upload the essay!");
                    jr.setResponseCode(403);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setResponseCode(400);
                jr.setMessage("An error has occured");
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("getdocfeedback")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            int id = Integer.parseInt(session.getAttribute("sid").toString());
            Scholar s = sch.getScholarDetails(id);

            try {
                Document doc = sch.getDocument(s, Integer.parseInt(request.getParameter("type")));
                if (doc != null) {
                    if (doc.getStatus() == 1 || doc.getStatus() == 2) {
                        jr.setResponseCode(200);
                        jr.setMessage("There's feedback!");
                        jr.setDescription(doc.getLocation());
                    } else {
                        jr.setResponseCode(403);
                        jr.setMessage("No feedback yet");

                    }
                } else {
                    jr.setMessage("You're yet to upload the document!");
                    jr.setResponseCode(404);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setResponseCode(400);
                jr.setMessage("An error has occured");
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("getessayhist")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            int id = Integer.parseInt(session.getAttribute("sid").toString());
            Scholar s = sch.getScholarDetails(id);

            try {
                CollegeEssay ce = new CollegeEssay();
                ce.setEssayid(Integer.parseInt(request.getParameter("essayid")));
                Document old = sch.getCollegeEssay(s, ce);
                if (old == null) {
                    jr.setMessage("No Existing history");
                    jr.setResponseCode(403);
                    out.print(gson.toJson(jr));
                } else {
                    List q = new ArrayList();
                    for (int k = 0; k <= old.getVersion(); k++) {
                        int index1 = old.getLocation().substring(30).indexOf("_");
                        String nm = old.getLocation().substring(30).substring(0, index1);
                        int y = old.getLocation().substring(30).indexOf(".");
                        String et = old.getLocation().substring(30).substring(y);
                        String fname = "/var/webapp/" + nm + "_" + k + et;

                        File f = new File(fname);
                        if (f.exists()) {
                            HashMap<String, String> tmp = new HashMap<>();
                            if (k == old.getVersion()) {
                                if (old.getStatus() == 1) {
                                    tmp.put("edit", "0");
                                }
                                tmp.put("current", "0");
                            }
                            tmp.put("essayid", old.getEssayid().getEssayid().toString());
                            tmp.put("url", old.getLocation());
                            tmp.put("loc", fname);

                            q.add(tmp);
                        }
                        //f.delete();
                    }
                    out.print(gson.toJson(q));
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setMessage("An error has occured");
                jr.setResponseCode(400);
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("deletecollegessay")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            int id = Integer.parseInt(session.getAttribute("sid").toString());
            Scholar s = sch.getScholarDetails(id);

            try {
                if (request.getParameter("current") != null) {
                    CollegeEssay ce = new CollegeEssay();
                    ce.setEssayid(Integer.parseInt(request.getParameter("essayid")));
                    Document old = sch.getCollegeEssay(s, ce);
                    if (sch.deleteDocument(old)) {
                        File f = new File(request.getParameter("loc"));
                        if (f.delete()) {
                            jr.setResponseCode(200);
                            jr.setMessage("File deleted successfully");
                        } else {
                            jr.setResponseCode(400);
                            jr.setMessage("An error has occured");
                        }
                    } else {
                        jr.setResponseCode(400);
                        jr.setMessage("An error has occured");
                    }
                } else {
                    File f = new File(request.getParameter("loc"));
                    if (f.delete()) {
                        jr.setResponseCode(200);
                        jr.setMessage("File deleted successfully");
                    } else {
                        jr.setResponseCode(400);
                        jr.setMessage("An error has occured");
                    }
                }

                /*if (sch.deleteDocument(old)) {
                 for (int k = 0; k <= old.getVersion(); k++) {
                 int index1 = old.getLocation().substring(30).indexOf("_");
                 String nm = old.getLocation().substring(30).substring(0, index1);
                 int y = old.getLocation().substring(30).indexOf(".");
                 String et = old.getLocation().substring(30).substring(y);
                 String fname = "/var/webapp/" + nm + "_" + k + et;

                 File f = new File(fname);
                 f.delete();
                 }
                 jr.setResponseCode(200);
                 jr.setMessage("Document deleted successfully");
                 } else {
                 jr.setMessage("An error occured");
                 jr.setResponseCode(400);
                 }*/
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setMessage("An error occured");
                jr.setResponseCode(400);
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("getfullprogress")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            int id = Integer.parseInt(session.getAttribute("sid").toString());
            Scholar s = sch.getScholarDetails(id);

            try {
                List q = new ArrayList();
                College college = new College();
                college.setCollid(Integer.parseInt(request.getParameter("collegeid")));
                List<CollegeEssay> allessays = sch.getCollegeEssays(college, admin1.getModule().getAdmcycle());
                int total = allessays.size();
                //List<Document> docs = 
            } catch (Exception ex) {

            }
        } else if (action.equalsIgnoreCase("getmymentor")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            int id = Integer.parseInt(session.getAttribute("sid").toString());
            try {
                Scholar s = sch.getScholarDetails(id);
                if (s.getMentor() != null) {
                    jr.setResponseCode(200);
                    jr.setDescription(s.getMentor().getFname() + " " + s.getMentor().getSurname());
                } else {
                    jr.setResponseCode(403);
                    jr.setMessage("Not assigned");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setMessage("An error occured");
                jr.setresponseCode(400);
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("getessaystatus")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            int id = Integer.parseInt(session.getAttribute("sid").toString());
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            Scholar s = new Scholar();
            s.setSid(id);
            try {
                College c = new College();
                c.setCollid(Integer.parseInt(request.getParameter("id")));
                List<CollegeEssay> essays = sch.getCollegeEssays(c, admin1.getModule().getAdmcycle());
                if (essays != null && !essays.isEmpty()) {
                    List q = new ArrayList();
                    for (CollegeEssay e : essays) {

                        Document doc = sch.getCollegeEssay(s, e);
                        if (doc != null) {
                            HashMap<String, String> tmp = new HashMap<>();
                            tmp.put("prompt", e.getPrompt());
                            tmp.put("loc", doc.getLocation());
                            tmp.put("status", doc.getStatus().toString());
                            if (doc.getScore() != null) {
                                tmp.put("score", doc.getScore().toString());
                            }

                            q.add(tmp);
                        }

                    }
                    out.print(gson.toJson(q));
                } else {
                    jr.setResponseCode(403);
                    jr.setMessage("No essays");
                    out.print(gson.toJson(jr));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("getcommonappstatus")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            int id = Integer.parseInt(session.getAttribute("sid").toString());
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            Scholar s = new Scholar();
            s.setSid(id);
            try {
                Document doc = sch.getDocument(s, 5);
                List q = new ArrayList();
                if (doc != null) {
                    HashMap<String, String> tmp = new HashMap<>();
                    tmp.put("loc", doc.getLocation());
                    tmp.put("status", doc.getStatus().toString());
                    if (doc.getScore() != null) {
                        tmp.put("score", doc.getScore().toString());
                    }

                    q.add(tmp);
                    out.print(gson.toJson(q));
                } else {
                    jr.setResponseCode(403);
                    jr.setMessage("No essays");
                    out.print(gson.toJson(jr));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("getdocstatus")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            int id = Integer.parseInt(session.getAttribute("sid").toString());
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            Scholar s = new Scholar();
            s.setSid(id);
            try {
                List q = new ArrayList();
                Document d1 = sch.getDocument(s, 0);
                if (d1 != null) {
                    HashMap<String, String> tmp = new HashMap<>();
                    tmp.put("type","0");
                    tmp.put("doc", d1.getLocation());
                    tmp.put("status", d1.getStatus().toString());
                    if (d1.getScore() != null) {
                        tmp.put("score", d1.getScore().toString());
                    }
                    q.add(tmp);
                }
                Document d2 = sch.getDocument(s, 1);
                if (d2 != null) {
                    HashMap<String, String> tmp = new HashMap<>();
                    tmp.put("type","1");
                    tmp.put("doc", d2.getLocation());
                    tmp.put("status", d2.getStatus().toString());
                    if (d2.getScore() != null) {
                        tmp.put("score", d2.getScore().toString());
                    }
                    q.add(tmp);
                }
                Document d3 = sch.getDocument(s, 2);
                if (d3 != null) {
                    HashMap<String, String> tmp = new HashMap<>();
                    tmp.put("type","2");
                    tmp.put("doc", d3.getLocation());
                    tmp.put("status", d3.getStatus().toString());
                    if (d3.getScore() != null) {
                        tmp.put("score", d3.getScore().toString());
                    }
                    q.add(tmp);
                }
                if (q.isEmpty()) {
                    jr.setMessage("No documents");
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

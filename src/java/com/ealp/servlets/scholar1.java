/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ealp.servlets;

import com.ealp.ejbs.Admin1EJB;
import com.ealp.ejbs.Scholar1;
import com.ealp.entities.Activities;
import com.ealp.entities.Essay;
import com.ealp.entities.Highschool;
import com.ealp.entities.Holiday;
import com.ealp.entities.Review;
import com.ealp.entities.Scholar;
import com.ealp.entities.Sys;
import com.ealp.entities.Transcript;
import com.ealp.jpa.JTAProvider;
import com.google.gson.Gson;
import com.utilities.JsonResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Float.NaN;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 *
 * @author eva
 */
public class scholar1 extends HttpServlet {

    @PersistenceContext(unitName = "EalpccpPU")
    private EntityManager em;

    @Resource
    UserTransaction userTx;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        Gson gson = new Gson();
        String action;
        DateFormat dtform = new SimpleDateFormat("dd/MM/yyyy");
        JSONObject j = null;
        if (request.getParameter("action") != null) {
            action = request.getParameter("action");
        } else {

            StringBuffer jb = new StringBuffer();
            String line = null;
            try {
                BufferedReader reader = request.getReader();
                while ((line = reader.readLine()) != null) {
                    jb.append(line);
                    //       System.out.print(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {

                j = JSONObject.fromObject(jb.toString());
                action = (j.get("action")).toString();
            } catch (Exception e) {
                // crash and burn
                throw new IOException("Error parsing JSON request string");
            }
        }

        if (action.equalsIgnoreCase("login")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));
            Admin1EJB admin = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                Scholar s = sch.checkScholar(request.getParameter("email"));
                if (s != null) {
                    Subject currentUser = SecurityUtils.getSubject();
                    Session session = currentUser.getSession(true);
                    Sys sys = admin.getModule();
                    if (!currentUser.isAuthenticated()) {
                        UsernamePasswordToken token = new UsernamePasswordToken(request.getParameter("email").toLowerCase(), (new Sha512Hash(request.getParameter("pass")).toString()));
                        try {
                            currentUser.login(token);

                            session = currentUser.getSession(true);
                            session.setAttribute("email", request.getParameter("email"));
//                    System.out.print(request.getParameter("email"));
                            session.setAttribute("sid", sch.getScholarId(request.getParameter("email").toLowerCase()));
                            //System.out.print(sch.getScholarId(request.getParameter("email").toLowerCase()));
                            //out.print(session.getAttribute("uid").toString());
                            if (s.getStatus() != null) {
                                if (s.getStatus() == -1) {
                                    jr.setResponseCode(100);
                                    jr.setMessage("admin");
                                } else {
                                    if (sys.getModule() == 1 || sys.getModule() == 1.5) {
                                        jr.setResponseCode(100);
                                        jr.setMessage("admin");
                                    } else {
                                        jr.setResponseCode(200);
                                        jr.setMessage("admin");
                                    }
                                }

                            } else {
                                jr.setResponseCode(100);
                                jr.setMessage("admin");
                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                            jr.setResponseCode(403);
                            jr.setMessage("Incorrect password details");
                        }
                    } else {
                        //check if session is still valid
                        if (session != null) {
                            currentUser.logout();
                            jr.setResponseCode(403);
                            jr.setMessage("Login again?");

                        } else {
                            jr.setResponseCode(403);
                            jr.setMessage("Session Timeout or session not valid");

                        }
                        //out.print(gson.toJson(jr));
                    }
                } else {
                    jr.setResponseCode(403);
                    jr.setMessage("Not a scholar, aye?");

                }
            } catch (Exception ex) {
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
            } finally {
                out.print(gson.toJson(jr));
            }

        } else if (action.equalsIgnoreCase("getpdets")) {
            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            int id = Integer.parseInt(session.getAttribute("sid").toString());

            JsonResponse jr = new JsonResponse();
            //System.out.print(id);
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));
            Scholar s = new Scholar();
            try {
                s = sch.getScholarDetails(id);
                if (s != null) {
                    List q = new ArrayList();
                    HashMap<String, String> tmp = new HashMap<>();
                    tmp.put("sid", s.getSid().toString());
                    tmp.put("fname", s.getFname());
                    tmp.put("mname", s.getMname());
                    tmp.put("surname", s.getSurname());
                    tmp.put("email", s.getEmail());
                    //DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                    if (s.getDob() != null) {
                        tmp.put("dob", dtform.format(s.getDob()));
                    }
                    if (s.getHome() != null && s.getSiblings() != null && s.getOrphan() != null) {

                        tmp.put("siblings", s.getSiblings().toString());
                        tmp.put("home", s.getHome().toString());

                        tmp.put("orphan", s.getOrphan().toString());
                    }
                    tmp.put("siblingso", s.getSiblingInfo());
                    tmp.put("phone", s.getMobile());
                    tmp.put("homereason", s.getHomeReason());
                    tmp.put("parent1", s.getParent1());
                    tmp.put("parent2", s.getParent2());
                    tmp.put("location", s.getLocation());
                    tmp.put("income", s.getIncome());
                    q.add(tmp);
                    out.print(gson.toJson(q));
                } else {
                    jr.setResponseCode(400);
                    jr.setMessage("No existing records");
                    out.print(gson.toJson(jr));
                }
            } catch (Exception ex) {
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
                ex.printStackTrace();
                out.print(gson.toJson(jr));
            }

        } else if (action.equalsIgnoreCase("addpdets")) {
            Scholar s = new Scholar();
            JsonResponse jr = new JsonResponse();
            try {
                s.setSid(Integer.parseInt(request.getParameter("sid")));
                s.setFname(request.getParameter("fname"));
                s.setMname(request.getParameter("mname"));
                s.setSurname(request.getParameter("sname"));
                s.setStatus(0);
                Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));

                s.setCycle(admin1.getModule().getAdmcycle());
                DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                //parse date from string value
                s.setDob(formatter.parse(request.getParameter("dob")));

                s.setEmail(request.getParameter("email"));
                s.setMobile(request.getParameter("phone"));
                s.setSiblings(Integer.parseInt(request.getParameter("siblingsno")));
                s.setOrphan(Integer.parseInt(request.getParameter("orphan")));
                s.setIncome(request.getParameter("income"));

                if (s.getOrphan() == 1) {
                    s.setParent1(request.getParameter("parent1"));
                } else if (s.getOrphan() == 2) {
                    s.setParent1(request.getParameter("parent1"));
                    s.setParent2(request.getParameter("parent2"));
                }
                s.setLocation(request.getParameter("location"));
                s.setHome(Integer.parseInt(request.getParameter("livehome")));
                if (s.getHome() == 1) {
                    s.setHomeReason(request.getParameter("homereason"));
                }
                if (s.getSiblings() > 0) {
                    s.setSiblingInfo("siblingso");
                }
                Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));
                s.setPassword(sch.getScholarDetails(Integer.parseInt(request.getParameter("sid"))).getPassword());
                if (sch.updateScholar(s)) {
                    jr.setResponseCode(200);
                    jr.setMessage("Details saved successfully");
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

        } else if (action.equalsIgnoreCase("addschool")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));
            Highschool h = new Highschool();
            h.setSid(Integer.parseInt(request.getParameter("sid")));

            h.setFees(request.getParameter("payfees"));
            h.setLocation(request.getParameter("county"));
            h.setMeangrade(Double.parseDouble(request.getParameter("score")));
            h.setMobile(request.getParameter("pmobile"));
            h.setName(request.getParameter("name"));
            h.setPhone(request.getParameter("tel"));
            h.setPrincipal(request.getParameter("pname"));
            h.setMobile(request.getParameter("ptel"));
            if (h.getFees().equalsIgnoreCase("sponsor")) {
                h.setSponsor(request.getParameter("sponsor"));
                h.setDescription(request.getParameter("desc"));
            } else {
                h.setBeenhome(request.getParameter("beenhome"));
            }
            h.setType1(request.getParameter("type1"));
            h.setType2(request.getParameter("type2"));
            h.setType3(request.getParameter("type3"));
            h.setMobile(request.getParameter("ptel"));
            try {
                if (sch.checkSchool(h.getSid())) {
                    if (sch.updateSchool(h)) {
                        jr.setResponseCode(200);
                        jr.setMessage("Record added successfully");
                    } else {
                        jr.setResponseCode(400);
                        jr.setMessage("An error occured");
                    }
                } else {
                    if (sch.createSchool(h)) {
                        jr.setResponseCode(200);
                        jr.setMessage("Record added successfully");
                    } else {
                        jr.setResponseCode(400);
                        jr.setMessage("An error occured");
                    }
                }
            } catch (Exception ex) {
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("getschool")) {
            JsonResponse jr = new JsonResponse();

            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            int id = Integer.parseInt(session.getAttribute("sid").toString());
            try {
                if (sch.checkSchool(id)) {
                    Highschool h = sch.getHighSchool(id);
                    // System.out.print(h.getName());
                    //out.print(gson.toJson(h));
                    List q = new ArrayList();
                    HashMap<String, String> tmp = new HashMap<>();
                    tmp.put("name", h.getName());
                    tmp.put("desc", h.getDescription());
                    tmp.put("fees", h.getFees());
                    tmp.put("mobile", h.getMobile());
                    tmp.put("tel", h.getPhone());
                    tmp.put("principal", h.getPrincipal());
                    tmp.put("sponsor", h.getSponsor());
                    tmp.put("type1", h.getType1());
                    tmp.put("type2", h.getType2());
                    tmp.put("type3", h.getType3());
                    tmp.put("score", Double.toString(h.getMeangrade()));
                    tmp.put("county", h.getLocation());
                    tmp.put("been", h.getBeenhome());
                    q.add(tmp);
                    out.print(gson.toJson(q));
                } else {
                    jr.setResponseCode(400);
                    jr.setMessage("No information exists");
                    out.print(gson.toJson(jr));
                }
            } catch (Exception ex) {
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
                out.print(gson.toJson(jr));
                ex.printStackTrace();
            }
        } else if (action.equalsIgnoreCase("addtranscript")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            int id = Integer.parseInt(session.getAttribute("sid").toString());

            Transcript t = new Transcript();
            if (request.getParameter("bio[points]") != null && !(request.getParameter("bio[points]")).equals("NaN") && (!request.getParameter("bio[points]").equalsIgnoreCase(""))) {
                t.setBio(Integer.parseInt(request.getParameter("bio[points]")));
            }
            if (request.getParameter("chem[points]") != null && !(request.getParameter("chem[points]")).equals("NaN") && (!request.getParameter("chem[points]").equalsIgnoreCase(""))) {
                t.setChem(Integer.parseInt(request.getParameter("chem[points]")));
            }
            if (request.getParameter("phy[points]") != null && !(request.getParameter("phy[points]")).equals("NaN") && (!(request.getParameter("phy[points]").equalsIgnoreCase("")))) {
                t.setPhy(Integer.parseInt(request.getParameter("phy[points]")));
            }
            if (request.getParameter("rs[points]") != null && !(request.getParameter("rs[points]")).equals("NaN") && (!(request.getParameter("rs[points]")).equalsIgnoreCase(""))) {
                t.setRel(Integer.parseInt(request.getParameter("rs[points]")));
            }
            if (request.getParameter("geog[points]") != null && !(request.getParameter("geog[points]")).equals("NaN") && (!request.getParameter("geog[points]").equalsIgnoreCase(""))) {
                t.setGeog(Integer.parseInt(request.getParameter("geog[points]")));
            }
            if (request.getParameter("hist[points]") != null && !(request.getParameter("hist[points]")).equals("NaN") && (!(request.getParameter("hist[points]")).equalsIgnoreCase(""))) {
                t.setHist(Integer.parseInt(request.getParameter("hist[points]")));
            }
            if (request.getParameter("tech[points]") != null && !(request.getParameter("tech[points]")).equals("NaN") && (!(request.getParameter("tech[points]").equalsIgnoreCase("")))) {
                t.setElect(request.getParameter("techsubj"));
                t.setEgrade(Integer.parseInt(request.getParameter("tech[points]")));
            }
            t.setEng(Integer.parseInt(request.getParameter("eng[points]")));
            t.setMath(Integer.parseInt(request.getParameter("math[points]")));
            t.setSwa(Integer.parseInt(request.getParameter("swa[points]")));
            t.setSid(Integer.parseInt(request.getParameter("sid")));
            t.setPop(Integer.parseInt(request.getParameter("pop")));
            t.setPosition(Integer.parseInt(request.getParameter("pos")));
            // System.out.print(t.getBio());
            try {
                if (sch.checkTranscript(id)) {
                    sch.updateTranscript(t);
                    jr.setResponseCode(200);
                    jr.setMessage("Record added successfully");
                } else {
                    sch.createTranscript(t);
                    jr.setResponseCode(200);
                    jr.setMessage("Record added successfully");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("getTranscript")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            int id = Integer.parseInt(session.getAttribute("sid").toString());

            try {
                if (sch.checkTranscript(id)) {
                    Transcript t = sch.getTranscript(id);

                    List q = new ArrayList();
                    HashMap<String, String> tmp = new HashMap<>();
                    if (t.getEng() != null) {
                        tmp.put("eng", t.getEng().toString());
                    }
                    if (t.getBio() != null) {
                        tmp.put("bio", t.getBio().toString());
                    }
                    if (t.getChem() != null) {
                        tmp.put("chem", t.getChem().toString());
                    }
                    if (t.getPhy() != null) {
                        tmp.put("phy", t.getPhy().toString());
                    }
                    if (t.getGeog() != null) {
                        tmp.put("geog", t.getGeog().toString());
                    }
                    if (t.getHist() != null) {
                        tmp.put("hist", t.getHist().toString());
                    }
                    if (t.getRel() != null) {
                        tmp.put("rs", t.getRel().toString());
                    }
                    if (t.getEgrade() != null) {
                        tmp.put("techsubj", t.getElect());
                        tmp.put("tech", t.getEgrade().toString());
                    }

                    tmp.put("math", t.getMath().toString());
                    tmp.put("swa", t.getSwa().toString());

                    tmp.put("pop", t.getPop().toString());
                    tmp.put("pos", t.getPosition().toString());

                    q.add(tmp);

                    out.print(gson.toJson(q));
                } else {
                    jr.setResponseCode(400);
                    jr.setMessage("No records found");
                    out.print(gson.toJson(jr));
                }
            } catch (Exception ex) {
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
                out.print(gson.toJson(jr));
                ex.printStackTrace();
            }
        } else if (action.equals("addhonors")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            int id = Integer.parseInt(session.getAttribute("sid").toString());

            List<Activities> acts = new ArrayList<Activities>();
            JSONArray q;
            q = j.getJSONArray("fields");
            for (int i = 0; i < q.length(); i++) {
                Activities ac = new Activities();
                Scholar s = new Scholar();
                s.setSid(id);
                ac.setSid(s);
                ac.setType(0);
                JSONObject item = q.getJSONObject(i);
                ac.setLevel(item.getString("level2"));
                ac.setClass1(item.getString("level"));
                ac.setDescription(item.getString("pos"));
                //System.out.print(ac.getLevel());
                acts.add(ac);
            }
            try {
                Scholar s1 = new Scholar();
                s1.setSid(id);
                List<Activities> act = sch.getSpecificActivity(s1, 0);
                if (act != null) {
                    if (sch.deleteActivities(act)) {
                        if (sch.addActivity(acts)) {
                            jr.setResponseCode(200);
                            jr.setMessage("Record added successfully");
                        } else {
                            jr.setResponseCode(400);
                            jr.setMessage("An error occured");
                        }
                    } else {
                        jr.setResponseCode(400);
                        jr.setMessage("An error occured");
                    }
                } else {

                    if (sch.addActivity(acts)) {
                        jr.setResponseCode(200);
                        jr.setMessage("Record added successfully");
                    } else {
                        jr.setResponseCode(400);
                        jr.setMessage("An error occured");
                    }

                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("gethonors")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            int id = Integer.parseInt(session.getAttribute("sid").toString());
            try {
                Scholar s1 = new Scholar();
                s1.setSid(id);
                List<Activities> acts = sch.getSpecificActivity(s1, 0);
                if (!acts.isEmpty()) {
                    List q = new ArrayList();
                    for (Activities a : acts) {
                        HashMap<String, String> tmp = new HashMap<>();
                        tmp.put("type", a.getType().toString());
                        tmp.put("class", a.getClass1().toString());
                        tmp.put("desc", a.getDescription());
                        if (a.getLevel() != null) {
                            tmp.put("level", a.getLevel());
                        }
                        q.add(tmp);

                    }
                    out.print(gson.toJson(q));
                } else {
                    jr.setResponseCode(400);
                    jr.setMessage("No records found");
                    out.print(gson.toJson(jr));
                }
            } catch (Exception ex) {
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
                out.print(gson.toJson(jr));
                ex.printStackTrace();
            }
        } else if (action.equalsIgnoreCase("getExtras")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            int id = Integer.parseInt(session.getAttribute("sid").toString());
            try {
                Scholar s1 = new Scholar();
                s1.setSid(id);
                List<Activities> acts = sch.getSpecificActivity(s1, 1);
                if (!acts.isEmpty()) {
                    List q = new ArrayList();
                    for (Activities a : acts) {
                        HashMap<String, String> tmp = new HashMap<>();
                        tmp.put("type", a.getType().toString());
                        tmp.put("class", a.getClass1().toString());
                        tmp.put("desc", a.getDescription());
                        if (a.getLevel() != null) {
                            tmp.put("level", a.getLevel());
                        }
                        q.add(tmp);

                    }
                    out.print(gson.toJson(q));
                } else {
                    jr.setResponseCode(400);
                    jr.setMessage("No records found");
                    out.print(gson.toJson(jr));
                }
            } catch (Exception ex) {
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
                out.print(gson.toJson(jr));
                ex.printStackTrace();
            }
        } else if (action.equalsIgnoreCase("addextras")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            int id = Integer.parseInt(session.getAttribute("sid").toString());

            List<Activities> acts = new ArrayList<Activities>();
            JSONArray q;
            q = j.getJSONArray("fields");
            for (int i = 0; i < q.length(); i++) {
                Activities ac = new Activities();
                Scholar s = new Scholar();
                s.setSid(id);
                ac.setSid(s);
                ac.setType(1);
                JSONObject item = q.getJSONObject(i);
                //ac.setLevel(item.getString("lvl"));
                ac.setClass1(item.getString("lvl"));
                ac.setDescription(item.getString("desc"));
                //System.out.print(ac.getLevel());
                acts.add(ac);
            }
            try {
                Scholar s1 = new Scholar();
                s1.setSid(id);
                List<Activities> act = sch.getSpecificActivity(s1, 1);
                if (act != null) {
                    if (sch.deleteActivities(act)) {
                        if (sch.addActivity(acts)) {
                            jr.setResponseCode(200);
                            jr.setMessage("Record added successfully");
                        } else {
                            jr.setResponseCode(400);
                            jr.setMessage("An error occured");
                        }
                    } else {
                        jr.setResponseCode(400);
                        jr.setMessage("An error occured");
                    }
                } else {

                    if (sch.addActivity(acts)) {
                        jr.setResponseCode(200);
                        jr.setMessage("Record added successfully");
                    } else {
                        jr.setResponseCode(400);
                        jr.setMessage("An error occured");
                    }

                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("addhols")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            int id = Integer.parseInt(session.getAttribute("sid").toString());

            Holiday h = new Holiday();
            h.setSid(id);
            h.setHol(request.getParameter("hol"));
            h.setInterest(request.getParameter("interest"));

            try {
                if (sch.checkHoliday(id)) {
                    if (sch.updateHoliday(h)) {
                        jr.setResponseCode(200);
                        jr.setMessage("Record updated successfully");

                    } else {
                        jr.setResponseCode(400);
                        jr.setMessage("An error occured");

                    }
                } else {
                    if (sch.createHoliday(h)) {
                        jr.setResponseCode(200);
                        jr.setMessage("Record updated successfully");
                    } else {
                        jr.setResponseCode(400);
                        jr.setMessage("An error occured");
                    }
                }
            } catch (Exception ex) {
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
                ex.printStackTrace();
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("getHols")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            int id = Integer.parseInt(session.getAttribute("sid").toString());

            try {
                if (sch.checkHoliday(id)) {
                    Holiday h = sch.getHoliday(id);
                    List q = new ArrayList();
                    HashMap<String, String> tmp = new HashMap<>();
                    tmp.put("hol", h.getHol());
                    tmp.put("interest", h.getInterest());
                    q.add(tmp);

                    out.print(gson.toJson(q));
                } else {
                    jr.setResponseCode(400);
                    jr.setMessage("No records found");
                    out.print(gson.toJson(jr));
                }
            } catch (Exception ex) {
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
                ex.printStackTrace();
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("getessays")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            int id = Integer.parseInt(session.getAttribute("sid").toString());
            Scholar s = new Scholar();
            s.setSid(id);
            try {
                if (sch.checkEssay(s)) {
                    List<Essay> es = sch.getEssays(s);
                    List q = new ArrayList();
                    for (Essay i : es) {
                        HashMap<String, String> tmp = new HashMap<>();
                        tmp.put("essay", i.getEssay());
                        tmp.put("url", i.getUrl());
                        q.add(tmp);
                    }
                    out.print(gson.toJson(q));
                } else {
                    jr.setResponseCode(400);
                    jr.setMessage("No records found");
                    out.print(gson.toJson(jr));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setResponseCode(400);
                jr.setMessage("An error has occured");
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase(
                "deleteessay")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            int id = Integer.parseInt(session.getAttribute("sid").toString());
            Scholar s = new Scholar();
            s.setSid(id);

            Essay e = new Essay();

            try {
                e = sch.getoneEssay(s, request.getParameter("essayid"));
                String filename = "/var/webapp/" + request.getParameter("url").substring(30);
                File f = new File(filename);
                if (f.delete()) {
                    if (sch.deleteoneEssay(e)) {
                        jr.setResponseCode(200);
                        jr.setMessage("Essay deleted successfully");
                    } else {
                        jr.setResponseCode(400);
                        jr.setMessage("An error has occured");
                    }
                } else {
                    jr.setResponseCode(400);
                    jr.setMessage("An error has occured");
                }
            } catch (Exception ex) {
                jr.setResponseCode(400);
                jr.setMessage("An error has occured");
                ex.printStackTrace();
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase(
                "submitapp")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            int id = Integer.parseInt(session.getAttribute("sid").toString());

            Review r = new Review();
            r.setSid(id);

            try {
                Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
                r.setCycle(admin1.getModule().getAdmcycle());
                if (sch.submitApp(r)) {
                    jr.setResponseCode(200);
                    jr.setMessage("Your application has been submitted for review");
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
        } else if (action.equalsIgnoreCase("checksubmit")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            int id = Integer.parseInt(session.getAttribute("sid").toString());

            try {
                if (sch.checkSubmit(id)) {
                    jr.setResponseCode(403);
                    jr.setMessage("Submitted");
                } else {
                    Sys sys = admin1.getModule();
                    if (sys.getModule() == 1.5) {
                        jr.setResponseCode(403);
                        jr.setMessage("Submission period close");
                    } else {
                        jr.setResponseCode(200);
                        jr.setMessage("Not submitted");
                    }

                }
            } catch (Exception ex) {
                jr.setResponseCode(200);
                jr.setMessage("Not submitted");
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("changepass")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            int sid = Integer.parseInt(session.getAttribute("sid").toString());
            String pass = new Sha512Hash(request.getParameter("cpass")).toString();
            try {
                Scholar s = sch.getScholarDetails(sid);
                System.out.print(sid);
                if (s.getPassword().equals(pass)) {
                    s.setPassword(new Sha512Hash(request.getParameter("pass1")).toString());
                    if (sch.updateScholar(s)) {
                        jr.setResponseCode(200);
                        jr.setMessage("Password changed successfully");
                    } else {
                        jr.setResponseCode(400);
                        jr.setMessage("An error occured");
                    }
                } else {
                    jr.setResponseCode(403);
                    jr.setMessage("Incorrect password");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("getsid")) {
            JsonResponse jr = new JsonResponse();
            //Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);

            // int sid = Integer.parseInt();
            if (currentUser != null) {
                jr.setDescription(session.getAttribute("sid").toString());
                jr.setResponseCode(200);
                //jr.setMessage("");
            } else {
                //jr.setDescription(session.getAttribute("sid").toString());
                jr.setResponseCode(400);
                jr.setMessage("");
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

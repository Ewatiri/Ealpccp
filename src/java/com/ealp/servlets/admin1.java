/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ealp.servlets;

import com.ealp.ejbs.Admin1EJB;
import com.ealp.ejbs.Scholar1;
import com.ealp.entities.Activities;
import com.ealp.entities.Announcement;
import com.ealp.entities.CallFeedback;
import com.ealp.entities.CallList;
import com.ealp.entities.EALPresource;
import com.ealp.entities.Essay;
import com.ealp.entities.Event;
import com.ealp.entities.Highschool;
import com.ealp.entities.Holiday;
import com.ealp.entities.InvitedScholar;
import com.ealp.entities.Mentor;
import com.ealp.entities.Review;
import com.ealp.entities.Scholar;
import com.ealp.entities.Staff;
import com.ealp.entities.Sys;
import com.ealp.entities.Transcript;
import com.ealp.jpa.JTAProvider;
import com.ealp.utils.Calc;
import com.ealp.utils.Mail;
import com.google.gson.Gson;
import com.utilities.JsonResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import javax.annotation.Resource;
import javax.ejb.EJB;
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
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 *
 * @author eva
 */
@WebServlet(name = "admin1", urlPatterns = {"/admin1"})
public class admin1 extends HttpServlet {

    @PersistenceContext(unitName = "EalpccpPU")
    private EntityManager em;

    @Resource
    UserTransaction userTx;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        Gson gson = new Gson();
        DateFormat dtform = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat timeformat = new SimpleDateFormat("HH:mm");
        String action = request.getParameter("action");
        //System.err.print(action);
        if (action.equalsIgnoreCase("addmentor")) {
            JsonResponse jr = new JsonResponse();
            try {

                Mentor mentor = new Mentor();
                mentor.setFname(request.getParameter("fname").toLowerCase());
                mentor.setSurname(request.getParameter("surname").toLowerCase());
                mentor.setEmail(request.getParameter("email").toLowerCase());
                mentor.setClass1(Integer.parseInt(request.getParameter("class")));
                // autogen a new pass
                Random rand = new Random();
                int myRandomNumber = rand.nextInt(30000000) + 10000000;
                String result = Integer.toHexString(myRandomNumber);

                mentor.setPassword((new Sha512Hash(result).toString()));
                mentor.setStatus(1);
                Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));

                Mail m = new Mail();
                String msg = "Hi " + mentor.getFname() + "," + "<br/>" + "Your username is " + mentor.getEmail() + ".<br/> Your password is " + result + "<br/>College Counseling Team";
                if (m.sendMail(mentor.getEmail(), "localhost", "EALP CCP Account", msg)) {
                    if (admin1.addMentor(mentor)) {
                        // send email
                        jr.setResponseCode(200);
                        jr.setMessage("Mentor added");

                    } else {
                        jr.setResponseCode(400);
                        jr.setMessage("An error occured");
                    }
                } else {
                    jr.setResponseCode(400);
                    jr.setMessage("Mail Server Issue");
                }

            } catch (Exception e) {
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
                e.printStackTrace();
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("setmodule")) {
            JsonResponse jr = new JsonResponse();
            try {
                Sys sys = new Sys();
                sys.setId(1);
                sys.setAdmcycle(request.getParameter("cycle"));
                sys.setModule(Float.parseFloat(request.getParameter("mod")));
                Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
                admin1.setModule(sys);
                
                jr.setResponseCode(200);
                jr.setMessage("The module switched successfully. Logout to access the module");
            } catch (Exception e) {
                jr.setResponseCode(400);
                jr.setMessage("An error has occured");
                e.printStackTrace();
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("login")) {
            // System.out.print(System.getProperty("alternatedocroot_1"));
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                Mentor m = admin1.checkMentor(request.getParameter("email"));
                if (m != null) {
                    if (m.getStatus() == 1) {
                        Subject currentUser = SecurityUtils.getSubject();
                        Session session = currentUser.getSession(true);
                        if (!currentUser.isAuthenticated()) {
                            UsernamePasswordToken token = new UsernamePasswordToken(request.getParameter("email").toLowerCase(), (new Sha512Hash(request.getParameter("pass")).toString()));
                            try {
                                currentUser.login(token);

                                session = currentUser.getSession(true);
                                session.setAttribute("email", request.getParameter("email"));
                                session.setAttribute("uid", admin1.getUid(request.getParameter("email").toLowerCase()));
                                //out.print(session.getAttribute("uid").toString());
                                Sys s = admin1.getModule();

                                if (currentUser.hasRole("1")) {
                                    jr.setMessage("admin");
                                } else if (currentUser.hasRole("2")) {

                                    jr.setMessage("standard");
                                } else {
                                    jr.setResponseCode(403);
                                    jr.setMessage("Incorrect details");
                                }
                                if (s.getModule() == 1 || s.getModule() == 1.5) {
                                    jr.setResponseCode(100);
                                } else {
                                    jr.setResponseCode(200);
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

                            } else {
                                jr.setResponseCode(403);
                                jr.setMessage("Session Timeout or session not valid");

                            }
                            //out.print(gson.toJson(jr));
                        }
                    } else {
                        jr.setResponseCode(403);
                        jr.setMessage("Your account is inactive. Please contact the administrator");
                    }
                } else {
                    jr.setResponseCode(403);
                    jr.setMessage("You're not a counselor!");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
            } finally {
                out.print(gson.toJson(jr));
            }

        } else if (action.equalsIgnoreCase("changepass")) {
            JsonResponse jr = new JsonResponse();
            Mentor mentor = new Mentor();
            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            mentor.setUid(Integer.parseInt(session.getAttribute("uid").toString()));
            mentor.setPassword(new Sha512Hash(request.getParameter("pass1")).toString());
            String newpass = (new Sha512Hash(request.getParameter("pass1")).toString());
            String cpass = (new Sha512Hash(request.getParameter("cpass")).toString());
            try {
                Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
                Mentor oldmentor = admin1.getMentor(mentor.getUid());
                mentor.setFname(oldmentor.getFname());
                mentor.setSurname(oldmentor.getSurname());
                mentor.setEmail(oldmentor.getEmail());
                mentor.setStatus(oldmentor.getStatus());
                mentor.setClass1(oldmentor.getClass1());
                //out.print((session.getAttribute("uid").toString()));
                if (cpass.equals(oldmentor.getPassword())) {
                    if (newpass.equals(oldmentor.getPassword())) {
                        jr.setResponseCode(403);
                        jr.setMessage("This is the current password. Select a new password");
                    } else {
                        if (admin1.updateMentor(mentor)) {
                            jr.setResponseCode(200);
                            jr.setMessage("Password change successfully");
                        }
                    }
                } else {
                    jr.setResponseCode(403);
                    jr.setMessage("Incorrect password. Provide the correct password.");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                out.print(gson.toJson(jr));
            }
            //mentor.setPassword(action);
        } else if (action.equalsIgnoreCase("addannoun")) {
            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            JsonResponse jr = new JsonResponse();
            Announcement announ = new Announcement();
            announ.setTitle(request.getParameter("title"));
            announ.setDescription(request.getParameter("desc"));
            Mentor mentor = new Mentor();
            mentor.setUid(Integer.parseInt(session.getAttribute("uid").toString()));
            announ.setCreatedby(mentor);
            announ.setEditedby(mentor);
            try {
                Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
                if (admin1.addAnnouncement(announ)) {
                    jr.setResponseCode(200);
                    jr.setMessage("Announcement added successfully");
                } else {
                    jr.setResponseCode(400);
                    jr.setMessage("An error has occured");
                }
            } catch (Exception ex) {
                jr.setResponseCode(400);
                jr.setMessage("An error has occured");
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("addres")) {
            JsonResponse jr = new JsonResponse();

            EALPresource resource = new EALPresource();

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);

            Mentor mentor = new Mentor();
            mentor.setUid(Integer.parseInt(session.getAttribute("uid").toString()));
            resource.setCreatedby(mentor);

            resource.setTitle(request.getParameter("title"));
            resource.setDescription(request.getParameter("desc"));
            resource.setType(Integer.parseInt(request.getParameter("type")));
            resource.setUrl(request.getParameter("link"));

            try {
                Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
                if (admin1.addRes(resource)) {
                    jr.setResponseCode(200);
                    jr.setMessage("Resource added successfully");
                } else {
                    jr.setResponseCode(400);
                    jr.setMessage("An error occured");
                }
            } catch (Exception ex) {
                jr.setResponseCode(400);
                jr.setMessage(ex.getMessage());
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("getAnnouncements")) {
            JsonResponse jr = new JsonResponse();
            try {
                Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
                List<Announcement> list = admin1.getAnnouncement();
                List q = new ArrayList();
                if (list != null) {

                    for (Announcement l : list) {
                        HashMap<String, String> tmp = new HashMap<>();
                        tmp.put("id", l.getAnid().toString());
                        tmp.put("title", l.getTitle());
                        tmp.put("description", l.getDescription());
                        Mentor m = admin1.getMentor(l.getEditedby().getUid());
                        tmp.put("by", m.getFname() + " " + m.getSurname());
                        tmp.put("creator", l.getCreatedby().getUid().toString());
                        if (l.getCtime() != null) {
                            //System.out.print(l.getCtime().toString());
                            tmp.put("resdate", l.getCtime().toString());
                        } else {
                            tmp.put("resdate", dtform.format(new Date().getTime()));
                        }

                        // out.print(q);
                        tmp.put("edit", "0");
                        tmp.put("action", "none");
                        q.add(tmp);
                    }
                    out.print(gson.toJson(q));
                } else {
                    jr.setResponseCode(401);
                    jr.setMessage("There are no announcements at this time");
                    out.print(gson.toJson(jr));
                }
            } catch (Exception ex) {
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
                out.print(gson.toJson(jr));
                ex.printStackTrace();
            }
        } else if (action.equalsIgnoreCase("editannoun")) {
            JsonResponse jr = new JsonResponse();
            Announcement ann = new Announcement();
            ann.setAnid(Integer.parseInt(request.getParameter("id")));
            ann.setTitle(request.getParameter("title"));
            ann.setDescription(request.getParameter("description"));
            //Date d = new Date();
            //ann.setCtime(new Date().getTime());
            Mentor m = new Mentor();
            m.setUid(Integer.parseInt(request.getParameter("creator")));
            ann.setCreatedby(m);
            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);

            Mentor mentor = new Mentor();
            mentor.setUid(Integer.parseInt(session.getAttribute("uid").toString()));
            ann.setEditedby(mentor);
            try {
                Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
                if (admin1.editAnn(ann)) {
                    jr.setResponseCode(200);
                    jr.setMessage("Announcement updated successfully");
                }
            } catch (Exception ex) {
                jr.setResponseCode(400);
                jr.setMessage(ex.getMessage());
                ex.printStackTrace();
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("deleteAnnoun")) {
            JsonResponse jr = new JsonResponse();
            Announcement ann = new Announcement();
            ann.setAnid(Integer.parseInt(request.getParameter("id")));
            try {
                Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
                if (admin1.deleteAnn(ann)) {
                    jr.setResponseCode(200);
                    jr.setMessage("Announcement was deleted successfully");
                }
            } catch (Exception ex) {
                jr.setResponseCode(400);
                jr.setMessage(ex.getMessage());
                ex.printStackTrace();
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("getresources")) {
            JsonResponse jr = new JsonResponse();
            try {
                Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
                List<EALPresource> list = admin1.getResource();
                List q = new ArrayList();
                if (list != null) {

                    for (EALPresource l : list) {
                        HashMap<String, String> tmp = new HashMap<>();
                        tmp.put("id", l.getRid().toString());
                        tmp.put("title", l.getTitle());
                        tmp.put("description", l.getDescription());
                        Mentor m = admin1.getMentor(l.getCreatedby().getUid());
                        tmp.put("by", m.getFname() + " " + m.getSurname());
                        tmp.put("creator", l.getCreatedby().getUid().toString());
                        tmp.put("type", l.getType().toString());
                        //tmp.put("url", request.getContextPath() + "/" + l.getUrl());
                        tmp.put("url", l.getUrl());
                        // out.print(q);
                        tmp.put("edit", "0");
                        tmp.put("action", "none");
                        q.add(tmp);
                    }
                    out.print(gson.toJson(q));
                } else {
                    jr.setResponseCode(401);
                    jr.setMessage("There are no resources at this time");
                    out.print(gson.toJson(jr));
                }
            } catch (Exception ex) {
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
                out.print(gson.toJson(jr));
                ex.printStackTrace();
            }
        } else if (action.equalsIgnoreCase("editres")) {
            JsonResponse jr = new JsonResponse();

            EALPresource res = new EALPresource();
            res.setRid(Integer.parseInt(request.getParameter("id")));
            res.setTitle(request.getParameter("title"));
            res.setDescription(request.getParameter("description"));
            res.setType(Integer.parseInt(request.getParameter("type")));
            res.setUrl(request.getParameter("url"));
            //Date d = new Date();
            //ann.setCtime(new Date().getTime());
            Mentor m = new Mentor();
            m.setUid(Integer.parseInt(request.getParameter("creator")));
            res.setCreatedby(m);
            try {
                Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
                if (admin1.editRes(res)) {
                    jr.setResponseCode(200);
                    jr.setMessage("Resource updated successfully");
                } else {
                    jr.setResponseCode(400);
                    jr.setMessage("An error occured");
                }
            } catch (Exception ex) {
                jr.setResponseCode(400);
                jr.setMessage(ex.getMessage());
            } finally {
                out.print(gson.toJson(jr));
            }

        } else if (action.equalsIgnoreCase("deleteres")) {
            JsonResponse jr = new JsonResponse();
            EALPresource resource = new EALPresource();
            resource.setRid(Integer.parseInt(request.getParameter("id")));
            resource.setTitle(request.getParameter("title"));
            try {
                Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
                String filename = "/var/webapp/" + request.getParameter("url").substring(30);
                File f = new File(filename);
                // System.out.print(filename);
                if (f.delete()) {
                    if (admin1.deleteRes(resource)) {
                        jr.setResponseCode(200);
                        jr.setMessage("Resource deleted successfully");
                    } else {
                        jr.setResponseCode(400);
                        jr.setMessage("An error occured");
                    }
                } else {
                    jr.setResponseCode(400);
                    jr.setMessage("An error occured");
                }

            } catch (Exception ex) {
                jr.setResponseCode(400);
                jr.setMessage(ex.getMessage());
                ex.printStackTrace();
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("logout")) {
            JsonResponse jr = new JsonResponse();
            Subject currentUser = SecurityUtils.getSubject();

            currentUser.logout();
            jr.setResponseCode(200);
            jr.setMessage("User logged out successfully");
            out.print(gson.toJson(jr));
        } else if (action.equalsIgnoreCase("getApprovals")) {
            JsonResponse jr = new JsonResponse();
            try {
                Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
                List<InvitedScholar> list = admin1.getinvitedScholar();
                if (list != null) {
                    List q = new ArrayList();
                    for (InvitedScholar l : list) {
                        HashMap<String, String> tmp = new HashMap<>();
                        tmp.put("fullname", l.getFullname());
                        tmp.put("id", l.getId().toString());
                        tmp.put("email", l.getEmail());
                        int type = l.getType();

                        if (type == 1 || type == 2) {
                            tmp.put("hs", l.getSchool());
                        } else {
                            tmp.put("hs", null);
                        }
                        if ((type == 2) || (type == 3)) {
                            tmp.put("branch", l.getBranch());

                        } else {
                            tmp.put("branch", null);
                        }
                        tmp.put("tp", l.getType().toString());
                        if (type == 1) {
                            tmp.put("type", "Invited Scholar");
                        } else if (type == 2) {
                            tmp.put("type", "Wings to Fly Scholar");
                        } else if (type == 3) {
                            tmp.put("type", "Staff affliated Scholar");
                            Staff s = admin1.getStaff(l.getId());
                            tmp.put("staffname", s.getSname());
                            tmp.put("pfno", s.getPfno());
                            tmp.put("semail", s.getEmail());
                            tmp.put("mobile", s.getMobile());
                            tmp.put("avaya", s.getAvaya());
                            tmp.put("title", s.getTitle());
                        }
                        q.add(tmp);
                    }
                    out.print(gson.toJson(q));
                } else {
                    jr.setResponseCode(401);
                    jr.setMessage("There are no pending verifications at the time");
                    out.print(gson.toJson(jr));

                }
            } catch (Exception ex) {
                jr.setResponseCode(400);
                jr.setMessage(ex.getMessage());
                out.print(gson.toJson(jr));
                ex.printStackTrace();
            }
        } else if (action.equalsIgnoreCase("approvescholar")) {
            InvitedScholar is = new InvitedScholar();
            JsonResponse jr = new JsonResponse();

            is.setId(Integer.parseInt(request.getParameter("id")));
            is.setFullname(request.getParameter("fullname"));
            is.setBranch(request.getParameter("branch"));
            is.setSchool(request.getParameter("hs"));
            is.setType(Integer.parseInt(request.getParameter("tp")));
            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);

            Mentor mentor = new Mentor();
            mentor.setUid(Integer.parseInt(session.getAttribute("uid").toString()));

            is.setApprovedby(mentor);
            is.setEmail(request.getParameter("email"));
            is.setStatus(0);

            Random rand = new Random();
            int myRandomNumber = rand.nextInt(30000000) + 10000000;
            String result = Integer.toHexString(myRandomNumber);
            is.setCode(result);

            try {
                Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
                Mail m = new Mail();
                is.setCycle(admin1.getModule().getAdmcycle());
                String msg = "Hi " + is.getFullname().toUpperCase() + "," + "<br/>" + "Your account activation code is " + is.getCode() + "<br/>All the best!<br/> College Counseling Team";
                if (m.sendMail(is.getEmail(), "localhost", "EALP CCP Account Activation Code", msg)) {
                    if (admin1.updateIscholar(is)) {

                        jr.setResponseCode(200);
                        jr.setMessage("Account activation code sent");
                    } else {
                        jr.setResponseCode(400);
                        jr.setMessage("An error occured");
                    }
                } else {
                    jr.setResponseCode(400);
                    jr.setMessage("Mail Server Issue");
                }

            } catch (Exception ex) {
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("rejectscholar")) {
            InvitedScholar is = new InvitedScholar();
            JsonResponse jr = new JsonResponse();

            is.setId(Integer.parseInt(request.getParameter("id")));
            is.setFullname(request.getParameter("fullname"));
            is.setBranch(request.getParameter("branch"));
            is.setSchool(request.getParameter("hs"));
            is.setType(Integer.parseInt(request.getParameter("tp")));

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);

            Mentor mentor = new Mentor();
            mentor.setUid(Integer.parseInt(session.getAttribute("uid").toString()));

            is.setApprovedby(mentor);
            is.setEmail(request.getParameter("email"));
            is.setStatus(-2);
            try {
                Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
                is.setCycle(admin1.getModule().getAdmcycle());
                Mail m = new Mail();
                String msg = "Hi " + is.getFullname().toUpperCase() + "," + "<br/>" + "Your account could not be verified " + "<br/>College Counseling Team";
                if (m.sendMail(is.getEmail(), "localhost", "Your EALP CCP Account Activation Request", msg)) {
                    if (admin1.updateIscholar(is)) {

                        jr.setResponseCode(200);
                        jr.setMessage("Account activation code sent");
                    } else {
                        jr.setResponseCode(400);
                        jr.setMessage("An error occured");
                    }
                } else {
                    jr.setResponseCode(400);
                    jr.setMessage("Mail Server Issue");
                }

            } catch (Exception ex) {
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("getapplicants")) {
            JsonResponse jr = new JsonResponse();
            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);

            int uid = Integer.parseInt(session.getAttribute("uid").toString());
            try {
                Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
                List<Review> rev = admin1.getApplicants(admin1.getModule().getAdmcycle());
                if (rev != null) {
                    List q = new ArrayList();

                    for (Review a : rev) {
                        boolean b = true;
                        boolean space = false;
                        //System.out.print(a.getSid());
                        if (a.getReviewer1() == null) {
                            space = true;
                            // System.out.print("1a" + space + ":" + b);
                            //System.out.print(a.getSid());
                        } else {
                            if (a.getReviewer1().getUid() == uid) {
                                b = false;
                                //  System.out.print("1b" + space + ":" + b);
                            }
                        }
                        if (a.getReviewer2() == null) {

                            space = true;
                            //System.out.print("2a"+space+":"+b);
                        } else {
                            if (a.getReviewer2().getUid() == uid) {
                                b = false;
                                //  System.out.print("2b"+space+":"+b);
                            }
                        }
                        if (a.getReviewer3() == null) {
                            space = true;
                            //System.out.print("3a"+space+":"+b);
                        } else {
                            if (a.getReviewer3().getUid() == uid) {
                                b = false;
                                // System.out.print("3b"+space+":"+b);
                            }
                        }
                        if (space && b) {
                            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));
                            HashMap<String, String> tmp = new HashMap<>();
                            Scholar sc = new Scholar();
                            sc = sch.getScholarDetails(a.getSid());
                            tmp.put("sid", sc.getSid().toString());
                            tmp.put("applicant", sc.getFname() + " " + sc.getSurname());

                            q.add(tmp);

                        }
                    }
                    Collections.shuffle(q);
                    out.print(gson.toJson(q));

                } else {
                    jr.setMessage("No pending applications");
                    jr.setResponseCode(400);
                    out.print(gson.toJson(jr));

                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setMessage("An error occured");
                jr.setResponseCode(400);
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("getfamily")) {
            JsonResponse jr = new JsonResponse();
            try {
                Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));
                Scholar s = sch.getScholarDetails(Integer.parseInt(request.getParameter("sid")));
                List q = new ArrayList();
                HashMap<String, String> tmp = new HashMap<>();
                tmp.put("sid", s.getSid().toString());
                tmp.put("orphan", s.getOrphan().toString());
                if (s.getOrphan() == 1) {
                    tmp.put("parent1", s.getParent1());
                    tmp.put("orphan1", "Partial");
                } else if (s.getOrphan() == 2) {
                    tmp.put("parent1", s.getParent1());
                    tmp.put("parent2", s.getParent2());
                    tmp.put("orphan1", "Neither");
                } else {
                    tmp.put("orphan1", "Total");
                }
                if (s.getIncome().equalsIgnoreCase("0")) {
                    tmp.put("income", "Below 10,000");
                } else if (s.getIncome().equalsIgnoreCase("1")) {
                    tmp.put("income", "10,000 - 25,000");
                } else if (s.getIncome().equalsIgnoreCase("2")) {
                    tmp.put("income", "50,000 - 100,000");
                } else if (s.getIncome().equalsIgnoreCase("3")) {
                    tmp.put("income", "100,000 - 300,000");
                } else if (s.getIncome().equalsIgnoreCase("4")) {
                    tmp.put("income", "300,000 - 500,000");
                } else if (s.getIncome().equalsIgnoreCase("5")) {
                    tmp.put("income", "500,000 - 1,000,000");
                } else if (s.getIncome().equalsIgnoreCase("6")) {
                    tmp.put("income", "Above 1,000,000");
                }

                tmp.put("siblings", s.getSiblings().toString());
                if (s.getSiblings() > 0) {
                    tmp.put("siblingsinfo", s.getSiblingInfo());
                }
                tmp.put("location", s.getLocation());

                if (s.getHome() == 1) {
                    tmp.put("hreason", s.getHomeReason());
                    tmp.put("livehome", "No");
                } else {
                    tmp.put("livehome", "Yes");
                }
                Highschool h = sch.getHighSchool(Integer.parseInt(request.getParameter("sid")));
                tmp.put("payfees", h.getFees());
                if (h.getFees().equalsIgnoreCase("parents")) {
                    tmp.put("beenhome", h.getBeenhome());
                } else {
                    tmp.put("sponsor", h.getSponsor());
                    tmp.put("sponsordesc", h.getDescription());
                }
                q.add(tmp);
                out.print(gson.toJson(q));
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setMessage("An error occured");
                jr.setResponseCode(400);
                out.print(gson.toJson(jr));
            }

        } else if (action.equalsIgnoreCase("getschool")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));
            try {
                Highschool h = sch.getHighSchool(Integer.parseInt(request.getParameter("sid")));
                List q = new ArrayList();
                HashMap<String, String> tmp = new HashMap<>();
                tmp.put("type", h.getType1() + "-" + h.getType2() + "-" + h.getType3());
                tmp.put("score", Double.toString(h.getMeangrade()));
                q.add(tmp);
                out.print(gson.toJson(q));
            } catch (Exception ex) {
                jr.setMessage("An error occured");
                jr.setResponseCode(400);
                ex.printStackTrace();
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("gettranscript")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));
            try {
                Transcript t = sch.getTranscript(Integer.parseInt(request.getParameter("sid")));
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
            } catch (Exception ex) {
                jr.setMessage("An error occured");
                jr.setResponseCode(400);
                ex.printStackTrace();
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("getessays1")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));
            try {
                Scholar s = new Scholar();
                s.setSid(Integer.parseInt(request.getParameter("sid")));
                List<Essay> es = sch.getEssays(s);
                List q = new ArrayList();
                for (Essay i : es) {
                    HashMap<String, String> tmp = new HashMap<>();
                    tmp.put("essay", i.getEssay());
                    tmp.put("url", i.getUrl());
                    q.add(tmp);
                }
                out.print(gson.toJson(q));
            } catch (Exception ex) {
                jr.setMessage("An error occured");
                jr.setResponseCode(400);
                ex.printStackTrace();
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("gethonors")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));
            try {
                Scholar s1 = new Scholar();
                s1.setSid(Integer.parseInt(request.getParameter("sid")));
                List<Activities> acts = sch.getSpecificActivity(s1, 0);
                if (acts != null) {
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
        } else if (action.equalsIgnoreCase("getvol")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));
            try {
                Scholar s1 = new Scholar();
                s1.setSid(Integer.parseInt(request.getParameter("sid")));
                List<Activities> acts = sch.getSpecificActivity(s1, 1);
                if (acts != null) {
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
        } else if (action.equalsIgnoreCase("getholiday")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));
            try {
                Holiday h = sch.getHoliday(Integer.parseInt(request.getParameter("sid")));
                List q = new ArrayList();
                if (h != null) {
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
        } else if (action.equalsIgnoreCase("savereview")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);

            Mentor mentor = new Mentor();
            mentor.setUid(Integer.parseInt(session.getAttribute("uid").toString()));

            try {
                boolean a, b, c;
                a = false;
                b = false;
                c = false;
                Review r = admin1.getReview(Integer.parseInt(request.getParameter("sid")));
                if (r.getReviewer1() == null) {
                    a = true;

                }
                if (r.getReviewer2() == null) {
                    b = true;

                }
                if (r.getReviewer3() == null) {
                    c = true;

                }
                if (a) {
                    r.setComment1(request.getParameter("comment"));
                    r.setMcf1(Integer.parseInt(request.getParameter("mcf")));
                    r.setScore1(Integer.parseInt(request.getParameter("s1")));
                    r.setScore2(Integer.parseInt(request.getParameter("s2")));
                    r.setScore3(Integer.parseInt(request.getParameter("s3")));
                    r.setReviewer1(mentor);

                    if (admin1.updateReview(r)) {
                        jr.setResponseCode(200);
                        jr.setMessage("Record saved successfully");
                    } else {
                        jr.setResponseCode(400);
                        jr.setMessage("An error occured");
                    }
                } else if (b && !a) {
                    r.setComment2(request.getParameter("comment"));
                    r.setMcf2(Integer.parseInt(request.getParameter("mcf")));
                    r.setScore4(Integer.parseInt(request.getParameter("s1")));
                    r.setScore5(Integer.parseInt(request.getParameter("s2")));
                    r.setScore6(Integer.parseInt(request.getParameter("s3")));
                    r.setReviewer2(mentor);

                    if (admin1.updateReview(r)) {
                        jr.setResponseCode(200);
                        jr.setMessage("Record saved successfully");
                    } else {
                        jr.setResponseCode(400);
                        jr.setMessage("An error occured");
                    }
                } else if (c && (!a && !b)) {
                    r.setComment3(request.getParameter("comment"));
                    r.setMcf3(Integer.parseInt(request.getParameter("mcf")));
                    r.setScore7(Integer.parseInt(request.getParameter("s1")));
                    r.setScore8(Integer.parseInt(request.getParameter("s2")));
                    r.setScore9(Integer.parseInt(request.getParameter("s3")));
                    r.setReviewer3(mentor);

                    if (admin1.updateReview(r)) {
                        jr.setResponseCode(200);
                        jr.setMessage("Record saved successfully");
                    } else {
                        jr.setResponseCode(400);
                        jr.setMessage("An error occured");
                    }
                } else {
                    jr.setResponseCode(200);
                    jr.setMessage("Too late");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("publishresults")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            double min = Double.parseDouble(request.getParameter("score"));

            try {
                Sys sys = admin1.getModule();
                String acceptmsg = "Hi " + ",<br/>Congratulations! We are happy to inform you that you've been accepted into the EALP College Counseling Program for " + sys.getAdmcycle() + "admission cycle.<br/>Best Wishes,<br/>College Counseling Team.";
                String rejectmsg = "Hi " + ",<br/>Thank you for applying to the EALP College counseling program. Although your application was impressive, we regret to inform you that you weren't accepted into the program.<br/>Best Wishes,<br/>College Counseling Team.";
                if (sys.getMinscore() == null || sys.getMinscore() == 0) {
                    List<Review> r = admin1.getReviews(admin1.getModule().getAdmcycle());
                    List error = new ArrayList();

                    List<Review> reject = new ArrayList();
                    List<Review> accept = new ArrayList();

                    List<Review> full = new ArrayList();
                    List<Review> nomcf = new ArrayList();
                    List<Review> mcfonly = new ArrayList();
                    for (Review r1 : r) {
                        if (r1.getReviewer1() == null || r1.getReviewer2() == null || r1.getReviewer3() == null) {
                            error.add(r1);
                        } else {
                            double[] sect1 = new double[3];
                            double[] sect2 = new double[3];
                            double[] sect3 = new double[3];

                            int mcf = 0;

                            sect1[0] = r1.getScore1();
                            sect1[1] = r1.getScore4();
                            sect1[2] = r1.getScore7();

                            sect2[0] = r1.getScore2();
                            sect2[1] = r1.getScore5();
                            sect2[2] = r1.getScore8();

                            sect3[0] = r1.getScore3();
                            sect3[1] = r1.getScore6();
                            sect3[2] = r1.getScore9();

                            Calc c = new Calc();

                            double m1 = c.getMedian(sect1);
                            double m2 = c.getMedian(sect2);
                            double m3 = c.getMedian(sect3);

                            if (r1.getMcf1() == 1) {
                                mcf += 1;
                            }
                            if (r1.getMcf2() == 1) {
                                mcf += 1;
                            }
                            if (r1.getMcf3() == 1) {
                                mcf += 1;
                            }

                            if ((m1 >= min && m2 >= min && m3 >= min) && (mcf >= 2)) {
                                full.add(r1);
                            } else if ((m1 >= min && m2 >= min && m3 >= min) && (mcf < 2)) {
                                nomcf.add(r1);
                                System.out.print(r1.getScholar().getFname());
                                System.out.print(mcf);
                            } else if (((m1 <= min || m2 <= min && m3 <= min) && (mcf >= 2))) {
                                mcfonly.add(r1);
                            } else if (((m1 <= min && m2 <= min && m3 <= min) && (mcf < 2))) {
                                reject.add(r1);
                            }
                        }
                    }
                    if (error.isEmpty()) {
                        int l = 0;
                        int l1 = 0;
                        sys.setMinscore(Float.parseFloat(request.getParameter("score")));
                        admin1.setModule(sys);
                        Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));
                        if (!full.isEmpty()) {
                            for (Review x : full) {

                                Scholar scholar = sch.getScholarDetails(x.getSid());
                                String email = scholar.getEmail();

                                scholar.setStatus(11);

                                Mail mail = new Mail();
                                if (mail.sendMail(email, "localhost", "Your EALP College Counseling Application", acceptmsg)) {
                                    if (sch.updateScholar(scholar)) {
                                        l++;
                                    }
                                }

                            }
                        }
                        if (!nomcf.isEmpty()) {
                            for (Review x : nomcf) {

                                Scholar scholar = sch.getScholarDetails(x.getSid());
                                String email = scholar.getEmail();

                                scholar.setStatus(10);

                                Mail mail = new Mail();
                                if (mail.sendMail(email, "localhost", "Your EALP College Counseling Application", acceptmsg)) {
                                    if (sch.updateScholar(scholar)) {
                                        l++;
                                    }
                                }

                            }
                        }
                        if (!mcfonly.isEmpty()) {
                            for (Review x : mcfonly) {

                                Scholar scholar = sch.getScholarDetails(x.getSid());
                                String email = scholar.getEmail();

                                scholar.setStatus(20);

                                Mail mail = new Mail();
                                if (mail.sendMail(email, "localhost", "Your EALP College Counseling Application", acceptmsg)) {
                                    if (sch.updateScholar(scholar)) {
                                        l++;
                                    }
                                }

                            }
                        }

                        if (!reject.isEmpty()) {
                            for (Review x : reject) {
                                Scholar scholar = sch.getScholarDetails(x.getSid());
                                String email = scholar.getEmail();
                                scholar.setStatus(-1);
                                Mail mail = new Mail();
                                if (mail.sendMail(email, "localhost", "Your EALP College Counseling Application", rejectmsg)) {
                                    if (sch.updateScholar(scholar)) {
                                        l1++;
                                    }
                                }

                            }
                        }
                        //int p = full.size()+nomcf.size()+mcfonly.size();
                        //if (l1 == reject.size() && l == p) {
                        jr.setResponseCode(200);
                        jr.setMessage("Results published successfully");
                        //} else {

                        // }
                    } else {
                        jr.setResponseCode(403);
                        jr.setMessage("Operation can not be completed.The review process is not complete.");
                    }

                } else {
                    jr.setMessage("The score has already been set");
                    jr.setResponseCode(403);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setMessage("An error occured");
                jr.setResponseCode(400);
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("addevent")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);

            Mentor mentor = new Mentor();
            mentor.setUid(Integer.parseInt(session.getAttribute("uid").toString()));
            try {
                Event e = new Event();
                e.setCycle(admin1.getModule().getAdmcycle());
                e.setTitle(request.getParameter("title"));
                e.setDescription(request.getParameter("desc"));
                e.setVenue(request.getParameter("venue"));
                e.setEdate(dtform.parse(request.getParameter("date")));
                e.setPostedby(mentor);
                e.setEtime(timeformat.parse(request.getParameter("time")));
                if (admin1.createEvent(e)) {
                    jr.setResponseCode(200);
                    jr.setMessage("Event created successfully");
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

        } else if (action.equalsIgnoreCase("getevents")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));

            try {
                String cycle = admin1.getModule().getAdmcycle();

                List<Event> events = admin1.getEvents(cycle);
                List q = new ArrayList();
                if (!events.isEmpty()) {
                    for (Event e : events) {
                        HashMap<String, String> tmp = new HashMap<>();
                        tmp.put("id", e.getEid().toString());
                        tmp.put("title", e.getTitle());
                        tmp.put("edit", "0");
                        tmp.put("desc", e.getDescription());
                        tmp.put("time", timeformat.format(e.getEtime()));
                        tmp.put("date", dtform.format(e.getEdate()));
                        tmp.put("venue", e.getVenue());
                        tmp.put("last", e.getPostedby().getFname() + " " + e.getPostedby().getSurname());
                        q.add(tmp);
                    }
                    out.print(gson.toJson(q));
                } else {
                    jr.setResponseCode(403);
                    jr.setMessage("No events");
                    out.print(gson.toJson(jr));
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
                out.print(gson.toJson(jr));
            }

        } else if (action.equalsIgnoreCase("updateevent")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);
            Mentor mentor = new Mentor();
            mentor.setUid(Integer.parseInt(session.getAttribute("uid").toString()));
            try {
                Event e = new Event();
                e.setEid(Integer.parseInt(request.getParameter("id")));
                e.setCycle(admin1.getModule().getAdmcycle());
                e.setTitle(request.getParameter("title"));
                e.setDescription(request.getParameter("desc"));
                e.setVenue(request.getParameter("venue"));
                e.setEdate(dtform.parse(request.getParameter("date")));
                e.setPostedby(mentor);
                e.setEtime(timeformat.parse(request.getParameter("time")));
                if (admin1.updateEvent(e)) {
                    jr.setResponseCode(200);
                    jr.setMessage("Record updated successfully");
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

        } else if (action.equalsIgnoreCase("deleteevent")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                Event e = admin1.getEvent(Integer.parseInt(request.getParameter("id")));
                if (admin1.deleteEvent(e)) {
                    jr.setResponseCode(200);
                    jr.setMessage("Event deleted successfully");
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
        } else if (action.equalsIgnoreCase("getscholarlist")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));

            try {
                String cycle = admin1.getModule().getAdmcycle();
                List<Scholar> scholars = admin1.getScholars(cycle);
                CallList k = new CallList();
                k.setCid(Integer.parseInt(request.getParameter("id")));
                List<CallFeedback> call = admin1.getFeedback(k);
                if (call == null) {
                    if (!scholars.isEmpty()) {
                        List q = new ArrayList();
                        for (Scholar s : scholars) {
                            HashMap<String, String> tmp = new HashMap<>();
                            tmp.put("sid", s.getSid().toString());
                            tmp.put("name", s.getFname() + " " + s.getMname() + " " + s.getSurname());
                            q.add(tmp);
                        }
                        out.print(gson.toJson(q));
                    } else {
                        jr.setResponseCode(400);
                        jr.setMessage("No records");
                        out.print(gson.toJson(jr));
                    }
                } else {
                    List q = new ArrayList();
                    for (Scholar s : scholars) {
                        //System.out.print("Not null");
                        HashMap<String, String> tmp = new HashMap<>();
                        tmp.put("sid", s.getSid().toString());
                        tmp.put("edit", "0");
                        tmp.put("name", s.getFname() + " " + s.getMname() + " " + s.getSurname());
                        for (CallFeedback c : call) {
                            if (s.getSid() == c.getSid().getSid()) {
                                tmp.put("feedback", c.getFeedback());
                                tmp.put("edit", "1");
                                tmp.put("status", c.getStatus().toString());
                                if (c.getStatus() == 0) {
                                    tmp.put("stat", "Started");
                                } else if (c.getStatus() == 1) {
                                    tmp.put("stat", "In progress");
                                } else {
                                    tmp.put("stat", "Complete");
                                }

                            }
                        }
                        q.add(tmp);
                    }
                    out.print(gson.toJson(q));
                }

            } catch (Exception ex) {
                jr.setResponseCode(400);
                jr.setMessage("No records");
                out.print(gson.toJson(jr));
                ex.printStackTrace();
            }

        } else if (action.equalsIgnoreCase("getsystatus")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                Sys sys = admin1.getModule();
                if (sys != null) {
                    jr.setMessage("Okay");
                    jr.setResponseCode(200);
                    jr.setDescription(sys.getModule().toString());
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
        } else if (action.equalsIgnoreCase("getappstatus")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                List<Review> submitted = admin1.getAllReviews(admin1.getModule().getAdmcycle());
                List<Scholar> applications = admin1.getAllScholarsProgress(admin1.getModule().getAdmcycle());
                if (applications != null) {
                    List q = new ArrayList();
                    HashMap<String, String> tmp = new HashMap<>();
                    tmp.put("all", Integer.toString(applications.size()));
                    tmp.put("submitted", Integer.toString(submitted.size()));
                    q.add(tmp);
                    out.print(gson.toJson(q));
                } else {
                    jr.setResponseCode(400);
                    jr.setMessage("An error occured");
                    out.print(gson.toJson(jr));
                }
            } catch (Exception ex) {
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
                out.print(gson.toJson(jr));
                ex.printStackTrace();
            }
        } else if (action.equalsIgnoreCase("closeapplications")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                Sys sys = admin1.getModule();
                sys.setModule(Float.parseFloat(request.getParameter("status")));
                if (admin1.setModule(sys)) {
                    jr.setResponseCode(200);
                    jr.setMessage("Submission of applications has now closed");
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
        } else if (action.equalsIgnoreCase("getacceptedreport")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                List<Review> reviews = admin1.getReviews(admin1.getModule().getAdmcycle());
                if (!reviews.isEmpty()) {
                    List q = new ArrayList();
                    List incomplete = new ArrayList();
                    for (Review r : reviews) {
                        if (r.getReviewer1() == null || r.getReviewer2() == null || r.getReviewer3() == null) {
                            incomplete.add(r);
                        } else {
                            if (r.getScholar().getStatus() == 11 || r.getScholar().getStatus() == 10 || r.getScholar().getStatus() == 20) {
                                int s1 = r.getScore1() + r.getScore2() + r.getScore3();
                                int s2 = r.getScore4() + r.getScore5() + r.getScore6();
                                int s3 = r.getScore7() + r.getScore8() + r.getScore9();
                                HashMap<String, String> tmp = new HashMap<>();
                                tmp.put("name", r.getScholar().getFname() + " " + r.getScholar().getMname());
                                tmp.put("r1", r.getReviewer1().getFname() + " " + r.getReviewer1().getSurname());
                                tmp.put("score1", Integer.toString(s1));
                                tmp.put("score2", Integer.toString(s2));
                                tmp.put("score3", Integer.toString(s3));
                                if (r.getMcf1() == 1) {
                                    tmp.put("mcf1", "Yes");
                                } else {
                                    tmp.put("mcf1", "No");
                                }
                                if (r.getMcf2() == 1) {
                                    tmp.put("mcf2", "Yes");
                                } else {
                                    tmp.put("mcf2", "No");
                                }
                                if (r.getMcf3() == 1) {
                                    tmp.put("mcf3", "Yes");
                                } else {
                                    tmp.put("mcf3", "No");
                                }
                                tmp.put("r2", r.getReviewer2().getFname() + " " + r.getReviewer2().getSurname());
                                tmp.put("r3", r.getReviewer3().getFname() + " " + r.getReviewer3().getSurname());
                                q.add(tmp);
                            }

                        }

                    }
                    if (!q.isEmpty()) {
                        out.print(gson.toJson(q));
                    } else {
                        jr.setMessage("No reviews have been submitted yet");
                        jr.setResponseCode(403);
                        out.print(gson.toJson(jr));
                    }

                } else {
                    jr.setMessage("No reviews have been submitted yet");
                    jr.setResponseCode(403);
                    out.print(gson.toJson(jr));
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setMessage("An error occured");
                jr.setResponseCode(400);
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("getrscholars")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                List<Review> reviews = admin1.getReviews(admin1.getModule().getAdmcycle());
                if (!reviews.isEmpty()) {
                    List q = new ArrayList();
                    List incomplete = new ArrayList();
                    for (Review r : reviews) {
                        if (r.getReviewer1() == null || r.getReviewer2() == null || r.getReviewer3() == null) {
                            incomplete.add(r);
                        } else {
                            if (r.getScholar().getStatus() == -1) {
                                int s1 = r.getScore1() + r.getScore2() + r.getScore3();
                                int s2 = r.getScore4() + r.getScore5() + r.getScore6();
                                int s3 = r.getScore7() + r.getScore8() + r.getScore9();
                                HashMap<String, String> tmp = new HashMap<>();
                                tmp.put("sid", r.getSid().toString());
                                tmp.put("name", r.getScholar().getFname() + " " + r.getScholar().getMname());
                                tmp.put("r1", r.getReviewer1().getFname() + " " + r.getReviewer1().getSurname());
                                tmp.put("score1", Integer.toString(s1));
                                tmp.put("score2", Integer.toString(s2));
                                tmp.put("score3", Integer.toString(s3));
                                if (r.getMcf1() == 1) {
                                    tmp.put("mcf1", "Yes");
                                } else {
                                    tmp.put("mcf1", "No");
                                }
                                if (r.getMcf2() == 1) {
                                    tmp.put("mcf2", "Yes");
                                } else {
                                    tmp.put("mcf2", "No");
                                }
                                if (r.getMcf3() == 1) {
                                    tmp.put("mcf3", "Yes");
                                } else {
                                    tmp.put("mcf3", "No");
                                }
                                tmp.put("r2", r.getReviewer2().getFname() + " " + r.getReviewer2().getSurname());
                                tmp.put("r3", r.getReviewer3().getFname() + " " + r.getReviewer3().getSurname());
                                q.add(tmp);
                            }

                        }

                    }
                    if (!q.isEmpty()) {
                        out.print(gson.toJson(q));
                    } else {
                        jr.setMessage("No reviews have been submitted yet");
                        jr.setResponseCode(403);
                        out.print(gson.toJson(jr));
                    }

                } else {
                    jr.setMessage("No reviews have been submitted yet");
                    jr.setResponseCode(403);
                    out.print(gson.toJson(jr));
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setMessage("An error occured");
                jr.setResponseCode(400);
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("getallapplicants")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                List<Review> reviews = admin1.getReviews(admin1.getModule().getAdmcycle());
                if (!reviews.isEmpty()) {
                    List q = new ArrayList();
                    for (Review r : reviews) {
                        HashMap<String, String> tmp = new HashMap<>();
                        tmp.put("name", r.getScholar().getFname() + " " + r.getScholar().getMname());
                        if (r.getScholar().getStatus() == -1) {
                            tmp.put("status", "Not Accepted");
                        } else if (r.getScholar().getStatus() == 0) {
                            tmp.put("status", "Application incomplete");
                        } else if (r.getScholar().getStatus() == 11) {
                            tmp.put("status", "Accepted : Regular + MCF");
                        } else if (r.getScholar().getStatus() == 10) {
                            tmp.put("status", "Accepted: Regular Only");
                        } else if (r.getScholar().getStatus() == 20) {
                            tmp.put("status", "Accepted: MCF Only");
                        }
                        q.add(tmp);
                    }
                    if (!q.isEmpty()) {
                        out.print(gson.toJson(q));
                    } else {
                        jr.setMessage("No reviews have been submitted yet");
                        jr.setResponseCode(403);
                        out.print(gson.toJson(jr));
                    }

                } else {
                    jr.setMessage("No reviews have been submitted yet");
                    jr.setResponseCode(403);
                    out.print(gson.toJson(jr));
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setMessage("An error occured");
                jr.setResponseCode(400);
                out.print(gson.toJson(jr));
            }

        } else if (action.equalsIgnoreCase("rejaccept")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));
            try {
                Scholar s = sch.getScholarDetails(Integer.parseInt(request.getParameter("sid")));
                s.setStatus(Integer.parseInt(request.getParameter("status")));
                if (sch.updateScholar(s)) {
                    jr.setResponseCode(200);
                    jr.setMessage("The scholar has been accepted into the program");
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
        } else if (action.equalsIgnoreCase("getanalysis1")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                Sys sys = admin1.getModule();
                if (sys.getMinscore() != null && sys.getMinscore() != 0) {
                    List q = new ArrayList();
                    int reg = 0;
                    int regmcf = 0;
                    int mcf = 0;
                    int rej = 0;
                    List<Review> reviews = admin1.getReviews(admin1.getModule().getAdmcycle());
                    if (!reviews.isEmpty()) {
                        for (Review r : reviews) {
                            if (r.getScholar().getStatus() == -1) {
                                rej += 1;
                            } else if (r.getScholar().getStatus() == 11) {
                                regmcf += 1;
                            } else if (r.getScholar().getStatus() == 10) {
                                reg += 1;
                            } else if (r.getScholar().getStatus() == 20) {
                                mcf += 1;
                            }

                        }
                        HashMap<String, String> tmp = new HashMap<>();
                        tmp.put("reg", Integer.toString(reg));
                        tmp.put("regmcf", Integer.toString(regmcf));
                        tmp.put("rej", Integer.toString(rej));
                        tmp.put("mcf", Integer.toString(mcf));
                        tmp.put("total", Integer.toString(reg + regmcf + mcf + rej));
                        tmp.put("successful", Integer.toString(reg + regmcf + mcf));
                        q.add(tmp);
                        out.print(gson.toJson(q));
                    } else {
                        jr.setResponseCode(403);
                        jr.setMessage("Review's empty");
                        out.print(gson.toJson(jr));
                    }
                } else {
                    jr.setResponseCode(403);
                    jr.setMessage("The results haven't been published yet");
                    out.print(gson.toJson(jr));
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

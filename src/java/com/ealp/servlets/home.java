/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ealp.servlets;

import com.ealp.ejbs.Admin1EJB;
import com.ealp.ejbs.Scholar1;
import com.ealp.entities.InvitedScholar;
import com.ealp.entities.Scholar;
import com.ealp.entities.Staff;
import com.ealp.entities.Sys;
import com.ealp.jpa.JTAProvider;
import com.google.gson.Gson;
import com.utilities.JsonResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;
import org.apache.shiro.crypto.hash.Sha512Hash;

/**
 *
 * @author eva
 */
public class home extends HttpServlet {

    @PersistenceContext(unitName = "EalpccpPU")
    private EntityManager em;
    @Resource
    UserTransaction userTx;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("getstate")) {
            JsonResponse jr = new JsonResponse();
            try {

                Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
                Sys sys = admin1.getModule();
                if (sys != null) {
                    jr.setResponseCode(200);
                    jr.setMessage(sys.getModule().toString());
                } else {
                    jr.setResponseCode(400);
                    jr.setMessage("An error occured");
                }

            } catch (Exception e) {
                e.printStackTrace();
                jr.setResponseCode(400);
                jr.setMessage("An error occured");
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("addscholar")) {
            JsonResponse jr = new JsonResponse();
            String type = request.getParameter("mytype");
            InvitedScholar scholar = new InvitedScholar();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            if (type.equalsIgnoreCase("1")) {
                scholar.setFullname(request.getParameter("name1").toLowerCase());
                scholar.setEmail(request.getParameter("email1").toLowerCase());
                scholar.setSchool(request.getParameter("sch1").toLowerCase());

                scholar.setType(1);
                scholar.setStatus(-1);
            } else if (type.equalsIgnoreCase("2")) {
                scholar.setFullname(request.getParameter("name2").toLowerCase());
                scholar.setEmail(request.getParameter("email2").toLowerCase());
                scholar.setSchool(request.getParameter("sch2").toLowerCase());
                scholar.setBranch(request.getParameter("branch2").toLowerCase());

                scholar.setType(2);
                scholar.setStatus(-1);
            } else if (type.equalsIgnoreCase("3")) {
                scholar.setFullname(request.getParameter("name3").toLowerCase());
                scholar.setEmail(request.getParameter("email3").toLowerCase());
                scholar.setBranch(request.getParameter("branch3").toLowerCase());

                scholar.setType(3);
            }
            scholar.setStatus(-1);
            if (type.equalsIgnoreCase("2") || type.equalsIgnoreCase("1")) {
                try {
                    scholar.setCycle(admin1.getModule().getAdmcycle());
                    Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));
                    if (sch.addInvited(scholar)) {
                        jr.setResponseCode(200);
                        jr.setMessage("Thank you! An access code will be sent to you through the email address provided.");
                    } else {
                        jr.setResponseCode(400);
                        jr.setMessage("An error occured. Please contact the EALP team.");
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    jr.setResponseCode(400);
                    jr.setMessage("An error occured");
                } finally {
                    out.print(gson.toJson(jr));
                }
            } else if (type.equalsIgnoreCase("3")) {
                try {
                    Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));
                    if (sch.addInvited(scholar)) {
                        Staff staff = new Staff();
                        int staffid = sch.getinvitedScholarId(request.getParameter("email3").toLowerCase());
                        if (staffid != -1) {
                            staff.setId(staffid);
                            staff.setEmail(request.getParameter("staffemail").toLowerCase());
                            staff.setAvaya(request.getParameter("avaya"));
                            //staff.setInvitedScholar(scholar);
                            staff.setMobile(request.getParameter("mobile"));
                            staff.setSname(request.getParameter("staffname").toLowerCase());
                            staff.setPfno(request.getParameter("pf1").toLowerCase());
                            staff.setTitle(request.getParameter("job").toLowerCase());
                            staff.setRelation(request.getParameter("relation").toLowerCase());
                            if (sch.addStaff(staff)) {
                                jr.setResponseCode(200);
                                jr.setMessage("Thank you! An access code will be sent to you through the email address provided.");
                            } else {
                                jr.setResponseCode(400);
                                jr.setMessage("An error occured. Please contact the EALP team.");
                            }
                        }

                    } else {
                        jr.setResponseCode(400);
                        jr.setMessage("An error occured. Please contact the EALP team.");
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    jr.setResponseCode(400);
                    jr.setMessage("An error occured");
                } finally {
                    out.print(gson.toJson(jr));
                }
            }

        } else if (action.equalsIgnoreCase("getaccesscode")) {
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));
            String is = null;
            JsonResponse jr = new JsonResponse();
            try {
                is = sch.getAccessCode(request.getParameter("email"), request.getParameter("code"));
                if (is != null) {
                    jr.setResponseCode(200);
                    jr.setMessage(is);
                } else {
                    jr.setResponseCode(401);
                    jr.setMessage("Invalid code/email");

                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setResponseCode(400);
                jr.setMessage("An error has occured");

            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("createscholar")) {
            Scholar scholar = new Scholar();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));
            JsonResponse jr = new JsonResponse();
            try {
                scholar.setEmail(request.getParameter("email").toLowerCase());
                scholar.setPassword(new Sha512Hash(request.getParameter("pass")).toString());
                scholar.setStatus(0);

                InvitedScholar is = new InvitedScholar();
                is = sch.getInvitedScholar(request.getParameter("email"));
                is.setStatus(2);
                Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
                Sys sys = admin1.getModule();
                scholar.setCycle(sys.getAdmcycle());
                if (sch.updateinvitedScholar(is)) {
                    if (sch.createScholar(scholar)) {
                        jr.setResponseCode(200);
                        
                        jr.setMessage("Account created successfully. Login");
                    } else {
                        jr.setResponseCode(400);
                        jr.setMessage("An error has occured");
                    }
                } else {
                    jr.setResponseCode(400);
                    jr.setMessage("An error has occured");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setResponseCode(400);
                jr.setMessage("An error has occured");
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

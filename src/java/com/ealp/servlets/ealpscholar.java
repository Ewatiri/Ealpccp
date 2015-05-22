/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ealp.servlets;

import com.ealp.ejbs.Admin1EJB;
import com.ealp.ejbs.Scholar1;
import com.ealp.entities.Academics;
import com.ealp.entities.Contacts;
import com.ealp.entities.Highschool;
import com.ealp.entities.Login;
import com.ealp.entities.Scholar;
import com.ealp.jpa.JTAProvider;
import com.google.gson.Gson;
import com.utilities.JsonResponse;
import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;
import org.apache.shiro.crypto.hash.Sha512Hash;

/**
 *
 * @author eva
 */

public class ealpscholar extends HttpServlet {

    @PersistenceContext(unitName = "EalpcokePU")
    private EntityManager em;
    @Resource
    UserTransaction userTx;

    @PersistenceContext(unitName = "EalpccpPU")
    private EntityManager em1;
    @Resource
    UserTransaction userTx1;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("createealpscholar")) {
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));
            JsonResponse jr = new JsonResponse();
            try {

                if (sch.getealpMail(request.getParameter("pf")) == null) {
                    jr.setMessage("Invalid PF and or email address / Incomplete EALP Portal profile. Complete your profile before attempting to create an account");
                    jr.setResponseCode(403);
                } else if (sch.getealpMail(request.getParameter("pf")).equalsIgnoreCase(request.getParameter("email"))) {
                    jr.setMessage("Proceed to create account");
                    jr.setResponseCode(200);

                }

            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setMessage("An error occured");
                jr.setResponseCode(400);
            } finally {
                out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("createscholar")) {
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));

            Scholar1 sch1 = new Scholar1(new JTAProvider(em1, userTx1));

            Admin1EJB admin = new Admin1EJB(new JTAProvider(em1, userTx1));
            JsonResponse jr = new JsonResponse();
            String pf = request.getParameter("pf");
            Scholar s = new Scholar();
            try {
                Login l = sch.getScholarName(pf);
                if (l != null) {
                    s.setCycle(admin.getModule().getAdmcycle());
                    s.setStatus(0);
                    s.setEmail(request.getParameter("email").toLowerCase());
                    if (l.getFName() != null) {
                        s.setFname(l.getFName());
                    }
                    if (l.getMName() != null) {
                        s.setMname(l.getMName());
                    }
                    if (l.getLName() != null) {
                        s.setSurname(l.getLName());
                    }
                    if (l.getDob() != null) {
                        s.setDob(l.getDob());
                    }
                    Contacts c = sch.getealpContacts(pf);
                    if (c.getPhone() != null) {
                        s.setMobile(c.getPhone());
                    }
                    s.setPassword(new Sha512Hash(request.getParameter("pass")).toString());
                    if (sch1.createScholar(s)) {
                        int sid = sch1.getScholarId(s.getEmail());
                        Academics a = sch.getealpHighschool(pf);
                        Highschool h = new Highschool();
                        h.setName(a.getHighschool());
                        h.setSid(sid);
                        if (sch1.createSchool(h)) {
                            jr.setResponseCode(200);
                            jr.setMessage("Account created successfully");
                        } else {
                            jr.setResponseCode(200);
                            jr.setMessage("Proceed to login");
                        }
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

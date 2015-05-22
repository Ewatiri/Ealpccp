/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ealp.servlets;

import com.ealp.ejbs.Scholar1;
import com.ealp.entities.EALPresource;
import com.ealp.entities.Essay;

import com.ealp.entities.Scholar;
import com.ealp.jpa.JTAProvider;
import com.google.gson.Gson;
import com.utilities.JsonResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;
import net.sf.json.JSONObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 *
 * @author eva
 */

public class addEssays extends HttpServlet {

    @PersistenceContext(unitName = "EalpccpPU")
    private EntityManager em;

    @Resource
    UserTransaction userTx;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);

            Gson gson = new Gson();
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);

            List<Essay> es = new ArrayList<Essay>();

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);

            Scholar scholar = new Scholar();
            scholar.setSid((Integer.parseInt(session.getAttribute("sid").toString())));
            int sid = Integer.parseInt(session.getAttribute("sid").toString());

            JsonResponse jr = new JsonResponse();
            boolean exist = false;
            try {

                List<FileItem> fields = upload.parseRequest(request);
                Iterator<FileItem> it = fields.iterator();
                String e1 = "";
                while (it.hasNext()) {
                    Essay resource = new Essay();
                    resource.setSid(scholar);
                    //System.out.print(resource.getScholar().getSid());

                    FileItem fileItem = it.next();
                    boolean isFormField = fileItem.isFormField();
                    if (isFormField) {

                        if (fileItem.getFieldName().equalsIgnoreCase("opt")) {
                            // System.out.print("1");
                            e1 = fileItem.getString();
                        } else if (fileItem.getFieldName().equalsIgnoreCase("essay2")) {
                            e1 = fileItem.getString();
                        } else if (fileItem.getFieldName().equalsIgnoreCase("essay3")) {
                            e1 = fileItem.getString();
                        }

                    } else {
                        //JSONObject j = JSONObject.fromObject(resource);
                        //out.print(j);
                        try {
                            String path = "/var/webapp/ealp/mod1/essays/";
                            File saveTo = new File(path + fileItem.getName());
                            if (saveTo.exists()) {
                                exist = true;
                            } else {
                                String p = "http://localhost:8080/Ealpccp/ealp/mod1/essays/" + fileItem.getName();
                                fileItem.write(saveTo);
                                resource.setUrl(p);
                                resource.setEssay(e1);
                                es.add(resource);

                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                            jr.setResponseCode(400);
                            jr.setMessage("An error has occured");
                        }

                    }
                }
                if (!exist) {
                    Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));
                    if (sch.checkEssay(scholar)) {
                        //System.out.print("yikes");
                        List<Essay> s = sch.getEssays(scholar);
                        for (Essay e : s) {
                            String filename = "/var/webapp/" + e.getUrl().substring(30);
                            File f = new File(filename);
                            f.delete();
                        }
                        if (sch.deleteEssay(s)) {
                            if (sch.createEssay(es)) {
                                jr.setResponseCode(200);
                                jr.setMessage("Essays uploaded successfully");
                            } else {
                                jr.setResponseCode(400);
                                jr.setMessage("An error has occured");
                            }
                        } else {
                            jr.setResponseCode(400);
                            jr.setMessage("An error has occured");
                        }
                    } else {
                        if (sch.createEssay(es)) {
                            jr.setResponseCode(200);
                            jr.setMessage("Essays uploaded successfully");
                        } else {
                            jr.setResponseCode(400);
                            jr.setMessage("An error has occured");
                        }
                    }

                } else {
                    jr.setResponseCode(400);
                    jr.setMessage("File already exists. Preferred filenames are of the form scholarnameessay.pdf/doc");
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

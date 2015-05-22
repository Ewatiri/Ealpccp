/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ealp.servlets;

import com.ealp.ejbs.Admin1EJB;
import com.ealp.entities.EALPresource;
import com.ealp.entities.Mentor;
import com.ealp.jpa.JTAProvider;
import com.google.gson.Gson;
import com.utilities.JsonResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;
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
public class addResource extends HttpServlet {

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

            EALPresource resource = new EALPresource();

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);

            Mentor mentor = new Mentor();
            mentor.setUid(Integer.parseInt(session.getAttribute("uid").toString()));
            resource.setCreatedby(mentor);

            JsonResponse jr = new JsonResponse();

            try {

                List<FileItem> fields = upload.parseRequest(request);
                Iterator<FileItem> it = fields.iterator();
                while (it.hasNext()) {
                    FileItem fileItem = it.next();
                    boolean isFormField = fileItem.isFormField();
                    if (isFormField) {

                        if (fileItem.getFieldName().equalsIgnoreCase("title")) {
                            resource.setTitle(fileItem.getString());
                        } else if (fileItem.getFieldName().equalsIgnoreCase("desc")) {
                            resource.setDescription(fileItem.getString());
                        } else if (fileItem.getFieldName().equalsIgnoreCase("type")) {
                            resource.setType(Integer.parseInt(fileItem.getString()));
                        }

                    } else {
                        try {
                            //ServletContext servletContext = getServletContext();
                            String path = "/var/webapp/ealp/resources/";
                            File saveTo = new File(path + fileItem.getName());
                            if (saveTo.exists()) {
                                jr.setResponseCode(400);
                                jr.setMessage("Resource already exists");
                            } else {
                                String p = "http://localhost:8080/Ealpccp/ealp/resources/" + fileItem.getName();
                                fileItem.write(saveTo);
                                resource.setUrl(p);
                                Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
                                if (admin1.addRes(resource)) {
                                    jr.setResponseCode(200);
                                    jr.setMessage("Resource added successfully");
                                } else {
                                    jr.setResponseCode(400);
                                    jr.setMessage("An error has occured");
                                }
                            }
                            // out.print();
                            //System.out.print(saveTo);

                        } catch (Exception ex) {
                            ex.printStackTrace();
                            jr.setResponseCode(400);
                            jr.setMessage("An error has occured");
                        }

                    }
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

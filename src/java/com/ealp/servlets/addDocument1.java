/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ealp.servlets;

import com.ealp.ejbs.Scholar1;
import com.ealp.entities.Document;
import com.ealp.entities.Scholar;
import com.ealp.jpa.JTAProvider;
import com.google.gson.Gson;
import com.utilities.JsonResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
public class addDocument1 extends HttpServlet {

    @PersistenceContext(unitName = "EalpccpPU")
    private EntityManager em;

    @Resource
    UserTransaction userTx;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Gson gson = new Gson();
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));

            Scholar scholar = new Scholar();

            JsonResponse jr = new JsonResponse();
            Document resource = new Document();

            boolean ty = false;
            boolean save = true;

            File loc = null;
            try {

                List<FileItem> fields = upload.parseRequest(request);
                Iterator<FileItem> it = fields.iterator();
                while (it.hasNext()) {

                    resource.setSid(scholar);
                    //System.out.print(resource.getScholar().getSid());

                    FileItem fileItem = it.next();
                    boolean isFormField = fileItem.isFormField();
                    if (isFormField) {

                        if (fileItem.getFieldName().equalsIgnoreCase("type")) {
                            // System.out.print("1");
                            resource.setType(Integer.parseInt(fileItem.getString()));
                        } else if (fileItem.getFieldName().equalsIgnoreCase("status")) {
                            resource.setStatus(Integer.parseInt(fileItem.getString()));
                        } else if (fileItem.getFieldName().equalsIgnoreCase("sid")) {
                            scholar.setSid((Integer.parseInt(fileItem.getString())));
                        } else if (fileItem.getFieldName().equalsIgnoreCase("score")) {
                            resource.setScore(Integer.parseInt(fileItem.getString()));
                        }
                    } else {
                        //System.out.print();
                        //JSONObject j = JSONObject.fromObject(resource);
                        //out.print(j);
                        try {
                            String type = fileItem.getContentType();

                            if (type.equalsIgnoreCase("application/msword") || (type.equalsIgnoreCase("application/vnd.openxmlformats-officedocument.wordprocessingml.document"))) {
                                String p = "http://localhost:8080/Ealpccp/ealp/mod2/documents/" + fileItem.getName();
                                String path = "/var/webapp/ealp/mod2/documents/";
                                File saveTo = new File(path + fileItem.getName());
                                loc = saveTo;

                                fileItem.write(saveTo);
                                resource.setLocation(p);

                            } else {
                                save = false;
                                ty = true;
                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                            jr.setResponseCode(400);
                            jr.setMessage("An error has occured");
                        }

                    }
                }
                if (save) {
                    Document old = sch.getDocument(scholar, resource.getType());
                    if (old != null) {
                        if (old.getStatus() != 2) {
                            old.setLocation(resource.getLocation());
                            old.setStatus(resource.getStatus());
                            old.setScore(resource.getScore());
                            old.setVersion(resource.getVersion());
                            if (!old.getLocation().equalsIgnoreCase(resource.getLocation())) {
                                String fname = "/var/webapp/" + old.getLocation().substring(30);
                                File f = new File(fname);
                                f.delete();
                            }
                            if (sch.updateDocument(old)) {
                                jr.setResponseCode(200);
                                jr.setMessage("Document uploaded successfully");
                                jr.setDescription(old.getLocation());
                            } else {
                                jr.setResponseCode(400);
                                jr.setMessage("An error has occured");
                            }
                        } else {
                            jr.setMessage("This document is marked complete and cannot be updated");
                            jr.setResponseCode(403);
                        }

                    } else {
                        if (sch.createDocument(resource)) {
                            jr.setResponseCode(200);
                            jr.setMessage("Document uploaded successfully");
                            jr.setDescription(resource.getLocation());
                        } else {
                            jr.setResponseCode(400);
                            jr.setMessage("An error has occured");
                        }

                    }

                } else {
                    if (ty) {
                        jr.setResponseCode(400);
                        jr.setMessage("Unsupported file format. Upload either a  .doc or a .docx file");
                    } else {
                        jr.setResponseCode(400);
                        jr.setMessage("File already exists. Preferred filenames are of the form scholarnamedocument.doc");
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

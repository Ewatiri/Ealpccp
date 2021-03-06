/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ealp.servlets;

import com.ealp.ejbs.Scholar1;
import com.ealp.entities.Essay;
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
public class addEssay extends HttpServlet {

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

            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(true);

            Scholar scholar = new Scholar();
            scholar.setSid((Integer.parseInt(session.getAttribute("sid").toString())));
            int sid = Integer.parseInt(session.getAttribute("sid").toString());

            JsonResponse jr = new JsonResponse();
            Essay resource = new Essay();

            boolean ty = false;
            boolean save = true;
            try {

                List<FileItem> fields = upload.parseRequest(request);
                Iterator<FileItem> it = fields.iterator();
                while (it.hasNext()) {

                    resource.setSid(scholar);
                    //System.out.print(resource.getScholar().getSid());

                    FileItem fileItem = it.next();
                    boolean isFormField = fileItem.isFormField();
                    if (isFormField) {

                        if (fileItem.getFieldName().equalsIgnoreCase("essayid")) {
                            // System.out.print("1");
                            resource.setEssay(fileItem.getString());
                        }
                    } else {
                        //System.out.print();
                        //JSONObject j = JSONObject.fromObject(resource);
                        //out.print(j);
                        try {
                            String type = fileItem.getContentType();
                            String path = "/var/webapp/ealp/mod1/essays/";
                            File saveTo = new File(path + fileItem.getName());

                            if (saveTo.exists()) {
                                save = false;
                            } else {
                                if (type.equals("application/pdf") || type.equalsIgnoreCase("application/msword") || (type.equalsIgnoreCase("application/vnd.openxmlformats-officedocument.wordprocessingml.document"))) {
                                    String p = "http://localhost:8080/Ealpccp/ealp/mod1/essays/" + fileItem.getName();
                                    fileItem.write(saveTo);
                                    resource.setUrl(p);

                                } else {
                                    save = false;
                                    ty = true;
                                }

                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                            jr.setResponseCode(400);
                            jr.setMessage("An error has occured");
                        }

                    }
                }
                if (save) {
                    Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));
                    Essay old = sch.getoneEssay(scholar, resource.getEssay());
                    if (old != null) {
                        String fname = "/var/webapp/" + old.getUrl().substring(30);
                        File f = new File(fname);
                        if (f.delete()) {
                            old.setUrl(resource.getUrl());
                            if (sch.updateEssay(old)) {
                                jr.setResponseCode(200);
                                jr.setMessage("Essay uploaded successfully");
                                jr.setDescription(old.getUrl());
                            } else {
                                jr.setResponseCode(400);
                                jr.setMessage("An error has occured");
                            }
                        } else {
                            jr.setResponseCode(400);
                            jr.setMessage("An error has occured");
                        }
                    } else {
                        if (sch.createoneEssay(resource)) {
                            jr.setResponseCode(200);
                            jr.setMessage("Essay uploaded successfully");
                            jr.setDescription(resource.getUrl());
                        } else {
                            jr.setResponseCode(400);
                            jr.setMessage("An error has occured");
                        }
                    }

                } else {
                    if (ty) {
                        jr.setResponseCode(400);
                        jr.setMessage("Unsupported file format. Upload either a .pdf, a .doc or a .docx file");
                    } else {
                        jr.setResponseCode(400);
                        jr.setMessage("File already exists. Preferred filenames are of the form scholarnameessay.pdf/doc");
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

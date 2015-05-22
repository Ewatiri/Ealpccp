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
import com.ealp.entities.CollegeProfile;
import com.ealp.entities.Event;
import com.ealp.entities.OtherResults;
import com.ealp.entities.Review;
import com.ealp.entities.SatResults;
import com.ealp.entities.Scholar;
import com.ealp.entities.Sys;
import com.ealp.entities.Test;
import com.ealp.entities.Testlist;
import com.ealp.jpa.JTAProvider;
import com.ealp.utils.Calc;
import com.utilities.JsonResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 *
 * @author eva
 */
public class reports extends HttpServlet {

    @PersistenceContext(unitName = "EalpccpPU")
    private EntityManager em;

    @Resource
    UserTransaction userTx;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        DateFormat dtform = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat timeformat = new SimpleDateFormat("HH:mm");
        if (action.equalsIgnoreCase("geteventlist")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                List q = new ArrayList();
                Event event = admin1.getEvent(Integer.parseInt(request.getParameter("id")));
                List<Attendance> attlist = event.getAttendanceList();
                for (Attendance a : attlist) {
                    HashMap<String, String> tmp = new HashMap<>();
                    Scholar s = a.getSid();
                    tmp.put("name", s.getFname() + " " + s.getSurname());
                    tmp.put("location", s.getLocation());
                    tmp.put("phone", s.getMobile());
                    tmp.put("event", event.getTitle());
                    tmp.put("venue", event.getVenue());
                    tmp.put("time", timeformat.format(event.getEtime()));
                    tmp.put("date", dtform.format(event.getEdate()));
                    q.add(tmp);
                }
                JRBeanCollectionDataSource rptData = new JRBeanCollectionDataSource(q);
                ServletContext servletContext = getServletContext();
                String contextPath = servletContext.getRealPath("/reports/eventlist_subreport1.jrxml");

                InputStream input = new FileInputStream(new File(contextPath));
                JasperDesign design = JRXmlLoader.load(input);

                JasperReport jreport = JasperCompileManager.compileReport(design);
                Map parameters = new HashMap();
                parameters.put("event", request.getParameter("id"));
                JasperPrint print = JasperFillManager.fillReport(jreport, parameters, rptData);
                byte[] output;

                output = JasperExportManager.exportReportToPdf(print);

                response.setContentLength(output.length);
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "attachment; filename=report.pdf");
                OutputStream outputStream;

                outputStream = response.getOutputStream();
                outputStream.write(output);
                outputStream.flush();
                outputStream.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else if (action.equalsIgnoreCase("getacceptedlist")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                List<Review> reviews = admin1.getReviews(admin1.getModule().getAdmcycle());
                if (reviews != null || !reviews.isEmpty()) {
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
                    JRBeanCollectionDataSource rptData = new JRBeanCollectionDataSource(q);
                    ServletContext servletContext = getServletContext();
                    String contextPath = servletContext.getRealPath("/reports/acceptedReport_subreport1.jrxml");

                    InputStream input = new FileInputStream(new File(contextPath));
                    JasperDesign design = JRXmlLoader.load(input);

                    JasperReport jreport = JasperCompileManager.compileReport(design);
                    Map parameters = new HashMap();
                    parameters.put("report", "Accepted Scholars Report");
                    JasperPrint print = JasperFillManager.fillReport(jreport, parameters, rptData);
                    byte[] output;

                    output = JasperExportManager.exportReportToPdf(print);

                    response.setContentLength(output.length);
                    response.setContentType("application/pdf");
                    response.setHeader("Content-Disposition", "attachment; filename=report.pdf");
                    OutputStream outputStream;

                    outputStream = response.getOutputStream();
                    outputStream.write(output);
                    outputStream.flush();
                    outputStream.close();

                } else {
                    jr.setMessage("No reviews have been submitted yet");
                    jr.setResponseCode(403);
                    //out.print(gson.toJson(jr));
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (action.equalsIgnoreCase("getrejlist")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                List<Review> reviews = admin1.getReviews(admin1.getModule().getAdmcycle());
                if (reviews != null || !reviews.isEmpty()) {
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
                    JRBeanCollectionDataSource rptData = new JRBeanCollectionDataSource(q);
                    ServletContext servletContext = getServletContext();
                    String contextPath = servletContext.getRealPath("/reports/acceptedReport_subreport1.jrxml");

                    InputStream input = new FileInputStream(new File(contextPath));
                    JasperDesign design = JRXmlLoader.load(input);

                    JasperReport jreport = JasperCompileManager.compileReport(design);
                    Map parameters = new HashMap();
                    parameters.put("report", "Rejected Scholars Report");
                    JasperPrint print = JasperFillManager.fillReport(jreport, parameters, rptData);
                    byte[] output;

                    output = JasperExportManager.exportReportToPdf(print);

                    response.setContentLength(output.length);
                    response.setContentType("application/pdf");
                    response.setHeader("Content-Disposition", "attachment; filename=report.pdf");
                    OutputStream outputStream;

                    outputStream = response.getOutputStream();
                    outputStream.write(output);
                    outputStream.flush();
                    outputStream.close();

                } else {
                    jr.setMessage("No reviews have been submitted yet");
                    jr.setResponseCode(403);
                    //out.print(gson.toJson(jr));
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (action.equalsIgnoreCase("allapplicants")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                List<Review> reviews = admin1.getReviews(admin1.getModule().getAdmcycle());
                if (reviews != null || !reviews.isEmpty()) {
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
                    JRBeanCollectionDataSource rptData = new JRBeanCollectionDataSource(q);
                    ServletContext servletContext = getServletContext();
                    String contextPath = servletContext.getRealPath("/reports/allApplicants_subreport1.jrxml");

                    InputStream input = new FileInputStream(new File(contextPath));
                    JasperDesign design = JRXmlLoader.load(input);

                    JasperReport jreport = JasperCompileManager.compileReport(design);
                    Map parameters = new HashMap();
                    //parameters.put("report", "Rejected Scholars Report");
                    JasperPrint print = JasperFillManager.fillReport(jreport, parameters, rptData);
                    byte[] output;

                    output = JasperExportManager.exportReportToPdf(print);

                    response.setContentLength(output.length);
                    response.setContentType("application/pdf");
                    response.setHeader("Content-Disposition", "attachment; filename=report.pdf");
                    OutputStream outputStream;

                    outputStream = response.getOutputStream();
                    outputStream.write(output);
                    outputStream.flush();
                    outputStream.close();

                } else {
                    jr.setMessage("No reviews have been submitted yet");
                    jr.setResponseCode(403);
                    //    out.print(gson.toJson(jr));
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (action.equalsIgnoreCase("getanalysis1")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                Sys sys = admin1.getModule();
                if (sys.getMinscore() != 0 && sys.getMinscore() != null) {
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
                        tmp.put("successful", Integer.toString(reg + regmcf + mcf));
                        tmp.put("total", Integer.toString(reg + regmcf + mcf + rej));
                        tmp.put("mcf", Integer.toString(mcf));
                        q.add(tmp);
                        //out.print(gson.toJson(q));
                        JRBeanCollectionDataSource rptData = new JRBeanCollectionDataSource(q);
                        ServletContext servletContext = getServletContext();
                        String contextPath = servletContext.getRealPath("/reports/applicationAnalysis_subreport1.jrxml");

                        InputStream input = new FileInputStream(new File(contextPath));
                        JasperDesign design = JRXmlLoader.load(input);

                        JasperReport jreport = JasperCompileManager.compileReport(design);
                        Map parameters = new HashMap();
                        //parameters.put("report", "Rejected Scholars Report");
                        JasperPrint print = JasperFillManager.fillReport(jreport, parameters, rptData);
                        byte[] output;

                        output = JasperExportManager.exportReportToPdf(print);

                        response.setContentLength(output.length);
                        response.setContentType("application/pdf");
                        response.setHeader("Content-Disposition", "attachment; filename=report.pdf");
                        OutputStream outputStream;

                        outputStream = response.getOutputStream();
                        outputStream.write(output);
                        outputStream.flush();
                        outputStream.close();

                    } else {
                        jr.setResponseCode(403);
                        jr.setMessage("Review's empty");
                        //out.print(gson.toJson(jr));
                    }
                } else {
                    jr.setResponseCode(403);
                    jr.setMessage("The results haven't been published yet");
                    //out.print(gson.toJson(jr));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setMessage("An error occured");
                jr.setResponseCode(400);
                //out.print(gson.toJson(jr));
            }
        } else if (action.equalsIgnoreCase("gettestlist")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                List q = new ArrayList();
                Test event = admin1.getTest(Integer.parseInt(request.getParameter("testid")));
                List<Testlist> attlist = event.getTestlistList();
                for (Testlist a : attlist) {
                    HashMap<String, String> tmp = new HashMap<>();
                    Scholar s = a.getSid();
                    tmp.put("name", s.getFname() + " " + s.getSurname());
                    tmp.put("location", s.getLocation());
                    tmp.put("phone", s.getMobile());
                    tmp.put("test", event.getTitle());
                    tmp.put("venue", event.getLocation());
                    tmp.put("time", timeformat.format(event.getTestTime()));
                    tmp.put("date", dtform.format(event.getTestDate()));
                    q.add(tmp);
                }
                JRBeanCollectionDataSource rptData = new JRBeanCollectionDataSource(q);
                ServletContext servletContext = getServletContext();
                String contextPath = servletContext.getRealPath("/reports/testList_subreport1.jrxml");

                InputStream input = new FileInputStream(new File(contextPath));
                JasperDesign design = JRXmlLoader.load(input);

                JasperReport jreport = JasperCompileManager.compileReport(design);
                Map parameters = new HashMap();
                //parameters.put("event", request.getParameter("id"));
                JasperPrint print = JasperFillManager.fillReport(jreport, parameters, rptData);
                byte[] output;

                output = JasperExportManager.exportReportToPdf(print);

                response.setContentLength(output.length);
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "attachment; filename=report.pdf");
                OutputStream outputStream;

                outputStream = response.getOutputStream();
                outputStream.write(output);
                outputStream.flush();
                outputStream.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else if (action.equalsIgnoreCase("gettestreport")) {
            JsonResponse jr = new JsonResponse();
            Scholar1 sch = new Scholar1(new JTAProvider(em, userTx));
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                double mean = 0;
                double median = 0;

                double s1mean = 0;
                double s2mean = 0;
                double s3mean = 0;
                double s1median = 0;
                double s2median = 0;
                double s3median = 0;
                int all = 0;
                int taken = 0;

                Calc calc = new Calc();
                Test t = admin1.getTest(Integer.parseInt(request.getParameter("testid")));
                List q = new ArrayList();
                if (t.getType().equalsIgnoreCase("sat1")) {
                    List<SatResults> list = t.getSatResultsList();
                    taken = list.size();
                    double[] total = new double[list.size()];
                    double[] sect1 = new double[list.size()];
                    double[] sect2 = new double[list.size()];
                    double[] sect3 = new double[list.size()];
                    for (int i = 0; i < list.size(); i++) {
                        SatResults sr = list.get(i);
                        HashMap<String, String> tmp = new HashMap<>();
                        tmp.put("name", sr.getSid().getFname() + " " + sr.getSid().getMname() + " " + sr.getSid().getSurname());
                        tmp.put("s1", sr.getS1());
                        tmp.put("score1", sr.getScore1().toString());
                        tmp.put("s2", sr.getS2());
                        tmp.put("score2", sr.getScore2().toString());
                        tmp.put("s3", sr.getS3());
                        tmp.put("score3", sr.getScore3().toString());
                        tmp.put("total", Integer.toString(sr.getScore1() + sr.getScore2() + sr.getScore3()));
                        q.add(tmp);

                        total[i] = sr.getScore1() + sr.getScore2() + sr.getScore3();
                        sect1[i] = sr.getScore1();
                        sect2[i] = sr.getScore2();
                        sect3[i] = sr.getScore3();

                        mean = calc.getMean(total);
                        median = calc.getMedian(total);

                        s1mean = calc.getMean(sect1);
                        s2mean = calc.getMean(sect2);
                        s3mean = calc.getMean(sect3);

                        s1median = calc.getMedian(sect1);
                        s2median = calc.getMedian(sect2);
                        s3median = calc.getMedian(sect3);
                    }
                } else if (t.getType().equalsIgnoreCase("sat2")) {
                    List<SatResults> list = t.getSatResultsList();
                    taken = list.size();
                    double[] total = new double[list.size()];
                    for (int i = 0; i < list.size(); i++) {
                        SatResults sr = list.get(i);
                        HashMap<String, String> tmp = new HashMap<>();
                        tmp.put("name", sr.getSid().getFname() + " " + sr.getSid().getMname() + " " + sr.getSid().getSurname());
                        tmp.put("s1", sr.getS1());
                        tmp.put("score1", sr.getScore1().toString());
                        tmp.put("s2", sr.getS2());
                        tmp.put("score2", sr.getScore2().toString());
                        tmp.put("s3", sr.getS3());
                        tmp.put("score3", sr.getScore3().toString());
                        tmp.put("total", Integer.toString(sr.getScore1() + sr.getScore2() + sr.getScore3()));
                        q.add(tmp);
                        total[i] = sr.getScore1() + sr.getScore2() + sr.getScore3();
                        mean = calc.getMean(total);
                        median = calc.getMedian(total);
                    }

                } else {
                    List<OtherResults> ot = t.getOtherResultsList();
                    taken = ot.size();
                    double[] total = new double[ot.size()];
                    for (int i = 0; i < ot.size(); i++) {
                        OtherResults o = ot.get(i);
                        total[i] = o.getScore();
                        HashMap<String, String> tmp = new HashMap<>();
                        tmp.put("name", o.getSid().getFname() + " " + o.getSid().getMname() + " " + o.getSid().getSurname());
                        tmp.put("score", o.getScore().toString());
                        q.add(tmp);
                        mean = calc.getMean(total);
                        median = calc.getMedian(total);
                    }

                }

                JRBeanCollectionDataSource rptData = new JRBeanCollectionDataSource(q);
                ServletContext servletContext = getServletContext();
                String contextPath = "";
                if (t.getType().equalsIgnoreCase("sat2")) {
                    contextPath = servletContext.getRealPath("/reports/satResults_subreport1.jrxml");
                } else if (t.getType().equalsIgnoreCase("sat1")) {
                    contextPath = servletContext.getRealPath("/reports/sat1Results_subreport1.jrxml");
                } else {
                    contextPath = servletContext.getRealPath("/reports/otherResults_subreport1.jrxml");
                }

                InputStream input = new FileInputStream(new File(contextPath));
                JasperDesign design = JRXmlLoader.load(input);

                List<Scholar> scholars = null;
                if (t.getOfficial() == 1) {
                    scholars = admin1.getDiagScholars(admin1.getModule().getAdmcycle());
                    all = scholars.size();
                } else {
                    all = t.getTestlistList().size();
                }

                JasperReport jreport = JasperCompileManager.compileReport(design);
                Map parameters = new HashMap();
                parameters.put("test", t.getTitle());
                parameters.put("tdate", dtform.format(t.getTestDate()));
                parameters.put("ttime", timeformat.format(t.getTestTime()));
                parameters.put("venue", t.getLocation());
                parameters.put("mean", Double.toString(mean));
                parameters.put("median", Double.toString(median));
                parameters.put("all", Integer.toString(all));
                parameters.put("taken", Integer.toString(taken));
                if (t.getType().equals("sat1")) {
                    parameters.put("mathmean", Double.toString(s1mean));
                    parameters.put("crmean", Double.toString(s2mean));
                    parameters.put("writingmean", Double.toString(s3mean));
                    parameters.put("mathmedian", Double.toString(s1median));
                    parameters.put("crmedian", Double.toString(s2median));
                    parameters.put("writingmedian", Double.toString(s3median));
                }
                JasperPrint print = JasperFillManager.fillReport(jreport, parameters, rptData);
                byte[] output;

                output = JasperExportManager.exportReportToPdf(print);

                response.setContentLength(output.length);
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "attachment; filename=report.pdf");
                OutputStream outputStream;

                outputStream = response.getOutputStream();
                outputStream.write(output);
                outputStream.flush();
                outputStream.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (action.equalsIgnoreCase("getscholartypereport")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                List<Scholar> scholars = null;
                String type = request.getParameter("type");
                String rpt = "";
                if (type.equalsIgnoreCase("all")) {
                    scholars = admin1.getScholars(admin1.getModule().getAdmcycle());
                    rpt = "All Scholars Report";
                } else if (type.equalsIgnoreCase("mcf")) {
                    scholars = admin1.getScholarsbyType(admin1.getModule().getAdmcycle(), 20);
                    rpt = "MCF Only Scholars Report";
                } else if (type.equalsIgnoreCase("mcfreg")) {
                    scholars = admin1.getScholarsbyType(admin1.getModule().getAdmcycle(), 11);
                    rpt = "MCF + Regular Scholars Report";
                } else if (type.equalsIgnoreCase("reg")) {
                    scholars = admin1.getScholarsbyType(admin1.getModule().getAdmcycle(), 10);
                    rpt = "Regular Only Scholars Report";
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
                    JRBeanCollectionDataSource rptData = new JRBeanCollectionDataSource(q);
                    ServletContext servletContext = getServletContext();
                    String contextPath = servletContext.getRealPath("/reports/scholarsType_subreport1.jrxml");

                    InputStream input = new FileInputStream(new File(contextPath));
                    JasperDesign design = JRXmlLoader.load(input);

                    JasperReport jreport = JasperCompileManager.compileReport(design);
                    Map parameters = new HashMap();
                    parameters.put("report", rpt);
                    JasperPrint print = JasperFillManager.fillReport(jreport, parameters, rptData);
                    byte[] output;

                    output = JasperExportManager.exportReportToPdf(print);

                    response.setContentLength(output.length);
                    response.setContentType("application/pdf");
                    response.setHeader("Content-Disposition", "attachment; filename=report.pdf");
                    OutputStream outputStream;

                    outputStream = response.getOutputStream();
                    outputStream.write(output);
                    outputStream.flush();
                    outputStream.close();
                    //    out.print(gson.toJson(q));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jr.setResponseCode(400);
                jr.setMessage("An error occured");

            }
        } else if (action.equalsIgnoreCase("getapplicationtypereport")) {
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                List<College> matches = admin1.getCollegebyName(request.getParameter("college"));
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
                    //String college = request.getParameter("college");
                    JRBeanCollectionDataSource rptData = new JRBeanCollectionDataSource(q);
                    ServletContext servletContext = getServletContext();
                    String contextPath = servletContext.getRealPath("/reports/applicationType_subreport1.jrxml");

                    InputStream input = new FileInputStream(new File(contextPath));
                    JasperDesign design = JRXmlLoader.load(input);

                    JasperReport jreport = JasperCompileManager.compileReport(design);
                    Map parameters = new HashMap();
                    parameters.put("college", col.getName());
                    JasperPrint print = JasperFillManager.fillReport(jreport, parameters, rptData);
                    byte[] output;

                    output = JasperExportManager.exportReportToPdf(print);

                    response.setContentLength(output.length);
                    response.setContentType("application/pdf");
                    response.setHeader("Content-Disposition", "attachment; filename=report.pdf");
                    OutputStream outputStream;

                    outputStream = response.getOutputStream();
                    outputStream.write(output);
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (action.equalsIgnoreCase("getappstatusreport")) {
            JsonResponse jr = new JsonResponse();
            Admin1EJB admin1 = new Admin1EJB(new JTAProvider(em, userTx));
            try {
                List<CollegeProfile> collegeprofile = null;
                String type = request.getParameter("type");
                String rpt = "";
                if (type.equalsIgnoreCase("all")) {
                    rpt = "Application Status Report - All Scholars";
                    collegeprofile = admin1.getCurrentProfile(admin1.getModule().getAdmcycle());
                } else if (type.equalsIgnoreCase("yet")) {
                    rpt = "Application Status Report - Incomplete Applications";
                    collegeprofile = admin1.getProfilebyStatus(admin1.getModule().getAdmcycle(), 0);
                    
                } else if (type.equalsIgnoreCase("complete")) {
                    collegeprofile = admin1.getProfilebyStatus(admin1.getModule().getAdmcycle(), 3);
                    rpt = "Application Status Report - Complete Applications";
                } else if (type.equalsIgnoreCase("accepted")) {
                    collegeprofile = admin1.getProfilebyStatus(admin1.getModule().getAdmcycle(), 1);
                    rpt = "Application Status Report - Accepted Scholars";
                } else if (type.equalsIgnoreCase("rej")) {
                    rpt = "Application Status Report - Unsuccessful Applications";
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
                }
                JRBeanCollectionDataSource rptData = new JRBeanCollectionDataSource(q);
                ServletContext servletContext = getServletContext();
                String contextPath = servletContext.getRealPath("/reports/applicationStatus_subreport1.jrxml");

                InputStream input = new FileInputStream(new File(contextPath));
                JasperDesign design = JRXmlLoader.load(input);

                JasperReport jreport = JasperCompileManager.compileReport(design);
                Map parameters = new HashMap();
                parameters.put("report", rpt);
                JasperPrint print = JasperFillManager.fillReport(jreport, parameters, rptData);
                byte[] output;

                output = JasperExportManager.exportReportToPdf(print);

                response.setContentLength(output.length);
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "attachment; filename=report.pdf");
                OutputStream outputStream;

                outputStream = response.getOutputStream();
                outputStream.write(output);
                outputStream.flush();
                outputStream.close();

            } catch (Exception ex) {
                ex.printStackTrace();

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

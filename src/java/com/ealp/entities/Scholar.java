/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ealp.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author eva
 */
@Entity
@Table(name = "scholar", catalog = "ealp1", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Scholar.findAll", query = "SELECT s FROM Scholar s"),
    @NamedQuery(name = "Scholar.findBySid", query = "SELECT s FROM Scholar s WHERE s.sid = :sid"),
    @NamedQuery(name = "Scholar.findByCycle", query = "SELECT s FROM Scholar s WHERE s.cycle = :cycle"),
    @NamedQuery(name = "Scholar.findByFname", query = "SELECT s FROM Scholar s WHERE s.fname = :fname"),
    @NamedQuery(name = "Scholar.findByMname", query = "SELECT s FROM Scholar s WHERE s.mname = :mname"),
    @NamedQuery(name = "Scholar.findBySurname", query = "SELECT s FROM Scholar s WHERE s.surname = :surname"),
    @NamedQuery(name = "Scholar.findByDob", query = "SELECT s FROM Scholar s WHERE s.dob = :dob"),
    @NamedQuery(name = "Scholar.findByEmail", query = "SELECT s FROM Scholar s WHERE s.email = :email"),
    @NamedQuery(name = "Scholar.findByMobile", query = "SELECT s FROM Scholar s WHERE s.mobile = :mobile"),
    @NamedQuery(name = "Scholar.findBySiblings", query = "SELECT s FROM Scholar s WHERE s.siblings = :siblings"),
    @NamedQuery(name = "Scholar.findByOrphan", query = "SELECT s FROM Scholar s WHERE s.orphan = :orphan"),
    @NamedQuery(name = "Scholar.findByIncome", query = "SELECT s FROM Scholar s WHERE s.income = :income"),
    @NamedQuery(name = "Scholar.findByHome", query = "SELECT s FROM Scholar s WHERE s.home = :home"),
    @NamedQuery(name = "Scholar.findByLocation", query = "SELECT s FROM Scholar s WHERE s.location = :location"),
    @NamedQuery(name = "Scholar.findByParent1", query = "SELECT s FROM Scholar s WHERE s.parent1 = :parent1"),
    @NamedQuery(name = "Scholar.findByParent2", query = "SELECT s FROM Scholar s WHERE s.parent2 = :parent2"),
    @NamedQuery(name = "Scholar.findByStatus", query = "SELECT s FROM Scholar s WHERE s.status = :status")})
public class Scholar implements Serializable {
    @OneToMany(mappedBy = "sid")
    private List<Document> documentList;
    @OneToMany(mappedBy = "sid")
    private List<Testlist> testlistList;
    @OneToMany(mappedBy = "sid")
    private List<Attendance> attendanceList;
    @OneToMany(mappedBy = "sid")
    private List<SatResults> satResultsList;
    @OneToMany(mappedBy = "sid")
    private List<OtherResults> otherResultsList;
    @OneToMany(mappedBy = "sid")
    private List<CollegeProfile> collegeProfileList;
    @OneToMany(mappedBy = "sid")
    private List<CallFeedback> callFeedbackList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "scholar")
    private Review review;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sid", nullable = false)
    private Integer sid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "cycle", nullable = false, length = 20)
    private String cycle;
    @Size(max = 20)
    @Column(name = "fname", length = 20)
    private String fname;
    @Size(max = 20)
    @Column(name = "mname", length = 20)
    private String mname;
    @Size(max = 30)
    @Column(name = "surname", length = 30)
    private String surname;
    @Column(name = "dob")
    @Temporal(TemporalType.DATE)
    private Date dob;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "email", length = 50)
    private String email;
    @Size(max = 20)
    @Column(name = "mobile", length = 20)
    private String mobile;
    @Column(name = "siblings")
    private Integer siblings;
    @Column(name = "orphan")
    private Integer orphan;
    @Size(max = 20)
    @Column(name = "income", length = 20)
    private String income;
    @Column(name = "home")
    private Integer home;
    @Size(max = 30)
    @Column(name = "location", length = 30)
    private String location;
    @Lob
    @Size(max = 65535)
    @Column(name = "home_reason", length = 65535)
    private String homeReason;
    @Lob
    @Size(max = 65535)
    @Column(name = "sibling_info", length = 65535)
    private String siblingInfo;
    @Size(max = 20)
    @Column(name = "parent1", length = 20)
    private String parent1;
    @Size(max = 20)
    @Column(name = "parent2", length = 20)
    private String parent2;
    @Lob
    @Size(max = 65535)
    @Column(name = "password", length = 65535)
    private String password;
    @Column(name = "status")
    private Integer status;
    @Lob
    @Size(max = 65535)
    @Column(name = "reason", length = 65535)
    private String reason;
    @JoinColumn(name = "mentor", referencedColumnName = "uid")
    @ManyToOne
    private Mentor mentor;

    public Scholar() {
    }

    public Scholar(Integer sid) {
        this.sid = sid;
    }

    public Scholar(Integer sid, String cycle) {
        this.sid = sid;
        this.cycle = cycle;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getSiblings() {
        return siblings;
    }

    public void setSiblings(Integer siblings) {
        this.siblings = siblings;
    }

    public Integer getOrphan() {
        return orphan;
    }

    public void setOrphan(Integer orphan) {
        this.orphan = orphan;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public Integer getHome() {
        return home;
    }

    public void setHome(Integer home) {
        this.home = home;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHomeReason() {
        return homeReason;
    }

    public void setHomeReason(String homeReason) {
        this.homeReason = homeReason;
    }

    public String getSiblingInfo() {
        return siblingInfo;
    }

    public void setSiblingInfo(String siblingInfo) {
        this.siblingInfo = siblingInfo;
    }

    public String getParent1() {
        return parent1;
    }

    public void setParent1(String parent1) {
        this.parent1 = parent1;
    }

    public String getParent2() {
        return parent2;
    }

    public void setParent2(String parent2) {
        this.parent2 = parent2;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Mentor getMentor() {
        return mentor;
    }

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sid != null ? sid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Scholar)) {
            return false;
        }
        Scholar other = (Scholar) object;
        if ((this.sid == null && other.sid != null) || (this.sid != null && !this.sid.equals(other.sid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ealp.entities.Scholar[ sid=" + sid + " ]";
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    @XmlTransient
    public List<CallFeedback> getCallFeedbackList() {
        return callFeedbackList;
    }

    public void setCallFeedbackList(List<CallFeedback> callFeedbackList) {
        this.callFeedbackList = callFeedbackList;
    }

    @XmlTransient
    public List<CollegeProfile> getCollegeProfileList() {
        return collegeProfileList;
    }

    public void setCollegeProfileList(List<CollegeProfile> collegeProfileList) {
        this.collegeProfileList = collegeProfileList;
    }

    @XmlTransient
    public List<SatResults> getSatResultsList() {
        return satResultsList;
    }

    public void setSatResultsList(List<SatResults> satResultsList) {
        this.satResultsList = satResultsList;
    }

    @XmlTransient
    public List<OtherResults> getOtherResultsList() {
        return otherResultsList;
    }

    public void setOtherResultsList(List<OtherResults> otherResultsList) {
        this.otherResultsList = otherResultsList;
    }

    @XmlTransient
    public List<Attendance> getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(List<Attendance> attendanceList) {
        this.attendanceList = attendanceList;
    }

    @XmlTransient
    public List<Testlist> getTestlistList() {
        return testlistList;
    }

    public void setTestlistList(List<Testlist> testlistList) {
        this.testlistList = testlistList;
    }

    @XmlTransient
    public List<Document> getDocumentList() {
        return documentList;
    }

    public void setDocumentList(List<Document> documentList) {
        this.documentList = documentList;
    }
    
}

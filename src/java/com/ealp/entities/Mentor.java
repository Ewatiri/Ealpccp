/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ealp.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author eva
 */
@Entity
@Table(name = "mentor", catalog = "ealp1", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"email"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mentor.findAll", query = "SELECT m FROM Mentor m"),
    @NamedQuery(name = "Mentor.findByUid", query = "SELECT m FROM Mentor m WHERE m.uid = :uid"),
    @NamedQuery(name = "Mentor.findByFname", query = "SELECT m FROM Mentor m WHERE m.fname = :fname"),
    @NamedQuery(name = "Mentor.findBySurname", query = "SELECT m FROM Mentor m WHERE m.surname = :surname"),
    @NamedQuery(name = "Mentor.findByEmail", query = "SELECT m FROM Mentor m WHERE m.email = :email"),
    @NamedQuery(name = "Mentor.findByClass1", query = "SELECT m FROM Mentor m WHERE m.class1 = :class1"),
    @NamedQuery(name = "Mentor.findByStatus", query = "SELECT m FROM Mentor m WHERE m.status = :status")})
public class Mentor implements Serializable {
    @OneToMany(mappedBy = "approvedby")
    private Collection<InvitedScholar> invitedScholarCollection;
    @OneToMany(mappedBy = "addedby")
    private List<CollegeEssay> collegeEssayList;
    @OneToMany(mappedBy = "createdby")
    private List<CallList> callListList;
    @OneToMany(mappedBy = "postedby")
    private Collection<Event> eventCollection;
    @OneToMany(mappedBy = "reviewer3")
    private List<Review> reviewList;
    @OneToMany(mappedBy = "reviewer2")
    private List<Review> reviewList1;
    @OneToMany(mappedBy = "reviewer1")
    private List<Review> reviewList2;
    @OneToMany(mappedBy = "mentor")
    private List<Scholar> scholarList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "uid", nullable = false)
    private Integer uid;
    @Size(max = 30)
    @Column(name = "fname", length = 30)
    private String fname;
    @Size(max = 30)
    @Column(name = "surname", length = 30)
    private String surname;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "email", length = 50)
    private String email;
    @Column(name = "class")
    private Integer class1;
    @Lob
    @Size(max = 65535)
    @Column(name = "password", length = 65535)
    private String password;
    @Column(name = "status")
    private Integer status;

    public Mentor() {
    }

    public Mentor(Integer uid) {
        this.uid = uid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getClass1() {
        return class1;
    }

    public void setClass1(Integer class1) {
        this.class1 = class1;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uid != null ? uid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mentor)) {
            return false;
        }
        Mentor other = (Mentor) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ealp.entities.Mentor[ uid=" + uid + " ]";
    }

    @XmlTransient
    public List<Scholar> getScholarList() {
        return scholarList;
    }

    public void setScholarList(List<Scholar> scholarList) {
        this.scholarList = scholarList;
    }

    @XmlTransient
    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    @XmlTransient
    public List<Review> getReviewList1() {
        return reviewList1;
    }

    public void setReviewList1(List<Review> reviewList1) {
        this.reviewList1 = reviewList1;
    }

    @XmlTransient
    public List<Review> getReviewList2() {
        return reviewList2;
    }

    public void setReviewList2(List<Review> reviewList2) {
        this.reviewList2 = reviewList2;
    }

    @XmlTransient
    public Collection<Event> getEventCollection() {
        return eventCollection;
    }

    public void setEventCollection(Collection<Event> eventCollection) {
        this.eventCollection = eventCollection;
    }

    @XmlTransient
    public List<CallList> getCallListList() {
        return callListList;
    }

    public void setCallListList(List<CallList> callListList) {
        this.callListList = callListList;
    }

    @XmlTransient
    public List<CollegeEssay> getCollegeEssayList() {
        return collegeEssayList;
    }

    public void setCollegeEssayList(List<CollegeEssay> collegeEssayList) {
        this.collegeEssayList = collegeEssayList;
    }

    @XmlTransient
    public Collection<InvitedScholar> getInvitedScholarCollection() {
        return invitedScholarCollection;
    }

    public void setInvitedScholarCollection(Collection<InvitedScholar> invitedScholarCollection) {
        this.invitedScholarCollection = invitedScholarCollection;
    }
    
}

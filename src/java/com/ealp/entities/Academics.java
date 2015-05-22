/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ealp.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eva
 */
@Entity
@Table(name = "academics", catalog = "ealpcoke_ealp", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Academics.findAll", query = "SELECT a FROM Academics a"),
    @NamedQuery(name = "Academics.findByPfNo", query = "SELECT a FROM Academics a WHERE a.pfNo = :pfNo"),
    @NamedQuery(name = "Academics.findByHighschool", query = "SELECT a FROM Academics a WHERE a.highschool = :highschool"),
    @NamedQuery(name = "Academics.findByUniversity", query = "SELECT a FROM Academics a WHERE a.university = :university"),
    @NamedQuery(name = "Academics.findByField", query = "SELECT a FROM Academics a WHERE a.field = :field"),
    @NamedQuery(name = "Academics.findByDegree", query = "SELECT a FROM Academics a WHERE a.degree = :degree"),
    @NamedQuery(name = "Academics.findByDuration", query = "SELECT a FROM Academics a WHERE a.duration = :duration"),
    @NamedQuery(name = "Academics.findByGraduationyear", query = "SELECT a FROM Academics a WHERE a.graduationyear = :graduationyear")})
public class Academics implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "pfNo", nullable = false, length = 255)
    private String pfNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "highschool", nullable = false, length = 255)
    private String highschool;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "university", nullable = false, length = 255)
    private String university;
    @Size(max = 255)
    @Column(name = "field", length = 255)
    private String field;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "degree", nullable = false, length = 255)
    private String degree;
    @Basic(optional = false)
    @NotNull
    @Column(name = "duration", nullable = false)
    private int duration;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "graduationyear", nullable = false, length = 255)
    private String graduationyear;

    public Academics() {
    }

    public Academics(String pfNo) {
        this.pfNo = pfNo;
    }

    public Academics(String pfNo, String highschool, String university, String degree, int duration, String graduationyear) {
        this.pfNo = pfNo;
        this.highschool = highschool;
        this.university = university;
        this.degree = degree;
        this.duration = duration;
        this.graduationyear = graduationyear;
    }

    public String getPfNo() {
        return pfNo;
    }

    public void setPfNo(String pfNo) {
        this.pfNo = pfNo;
    }

    public String getHighschool() {
        return highschool;
    }

    public void setHighschool(String highschool) {
        this.highschool = highschool;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getGraduationyear() {
        return graduationyear;
    }

    public void setGraduationyear(String graduationyear) {
        this.graduationyear = graduationyear;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pfNo != null ? pfNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Academics)) {
            return false;
        }
        Academics other = (Academics) object;
        if ((this.pfNo == null && other.pfNo != null) || (this.pfNo != null && !this.pfNo.equals(other.pfNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ealp.entities.Academics[ pfNo=" + pfNo + " ]";
    }
    
}

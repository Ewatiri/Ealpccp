/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ealp.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "college", catalog = "ealp1", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"name"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "College.findAll", query = "SELECT c FROM College c"),
    @NamedQuery(name = "College.findByCollid", query = "SELECT c FROM College c WHERE c.collid = :collid"),
    @NamedQuery(name = "College.findByName", query = "SELECT c FROM College c WHERE c.name = :name"),
    @NamedQuery(name = "College.findByCommonapp", query = "SELECT c FROM College c WHERE c.commonapp = :commonapp")})
public class College implements Serializable {
    @Column(name = "mcf")
    private Integer mcf;
    @Size(max = 10)
    @Column(name = "edeadline", length = 10)
    private String edeadline;
    @Size(max = 10)
    @Column(name = "rdeadline", length = 10)
    private String rdeadline;
    @OneToMany(mappedBy = "collid")
    private List<CollegeProfile> collegeProfileList;
    @OneToMany(mappedBy = "collid")
    private List<CollegeEssay> collegeEssayList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "collid", nullable = false)
    private Integer collid;
    @Size(max = 100)
    @Column(name = "name", length = 100)
    private String name;
    @Column(name = "commonapp")
    private Integer commonapp;

    public College() {
    }

    public College(Integer collid) {
        this.collid = collid;
    }

    public Integer getCollid() {
        return collid;
    }

    public void setCollid(Integer collid) {
        this.collid = collid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCommonapp() {
        return commonapp;
    }

    public void setCommonapp(Integer commonapp) {
        this.commonapp = commonapp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (collid != null ? collid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof College)) {
            return false;
        }
        College other = (College) object;
        if ((this.collid == null && other.collid != null) || (this.collid != null && !this.collid.equals(other.collid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ealp.entities.College[ collid=" + collid + " ]";
    }

    @XmlTransient
    public List<CollegeEssay> getCollegeEssayList() {
        return collegeEssayList;
    }

    public void setCollegeEssayList(List<CollegeEssay> collegeEssayList) {
        this.collegeEssayList = collegeEssayList;
    }

    @XmlTransient
    public List<CollegeProfile> getCollegeProfileList() {
        return collegeProfileList;
    }

    public void setCollegeProfileList(List<CollegeProfile> collegeProfileList) {
        this.collegeProfileList = collegeProfileList;
    }

    public String getEdeadline() {
        return edeadline;
    }

    public void setEdeadline(String edeadline) {
        this.edeadline = edeadline;
    }

    public String getRdeadline() {
        return rdeadline;
    }

    public void setRdeadline(String rdeadline) {
        this.rdeadline = rdeadline;
    }

    public Integer getMcf() {
        return mcf;
    }

    public void setMcf(Integer mcf) {
        this.mcf = mcf;
    }
    
}

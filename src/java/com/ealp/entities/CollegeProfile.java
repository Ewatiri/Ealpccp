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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eva
 */
@Entity
@Table(name = "college_profile", catalog = "ealp1", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"sid", "collid", "cycle"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CollegeProfile.findAll", query = "SELECT c FROM CollegeProfile c"),
    @NamedQuery(name = "CollegeProfile.findById", query = "SELECT c FROM CollegeProfile c WHERE c.id = :id"),
    @NamedQuery(name = "CollegeProfile.findByCycle", query = "SELECT c FROM CollegeProfile c WHERE c.cycle = :cycle"),
    @NamedQuery(name = "CollegeProfile.findByDecisionType", query = "SELECT c FROM CollegeProfile c WHERE c.decisionType = :decisionType"),
    @NamedQuery(name = "CollegeProfile.findByDecision", query = "SELECT c FROM CollegeProfile c WHERE c.decision = :decision")})
public class CollegeProfile implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 30)
    @Column(name = "cycle", length = 30)
    private String cycle;
    @Size(max = 50)
    @Column(name = "decision_type", length = 50)
    private String decisionType;
    @Column(name = "decision")
    private Integer decision;
    @JoinColumn(name = "collid", referencedColumnName = "collid")
    @ManyToOne
    private College collid;
    @JoinColumn(name = "sid", referencedColumnName = "sid")
    @ManyToOne
    private Scholar sid;

    public CollegeProfile() {
    }

    public CollegeProfile(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getDecisionType() {
        return decisionType;
    }

    public void setDecisionType(String decisionType) {
        this.decisionType = decisionType;
    }

    public Integer getDecision() {
        return decision;
    }

    public void setDecision(Integer decision) {
        this.decision = decision;
    }

    public College getCollid() {
        return collid;
    }

    public void setCollid(College collid) {
        this.collid = collid;
    }

    public Scholar getSid() {
        return sid;
    }

    public void setSid(Scholar sid) {
        this.sid = sid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CollegeProfile)) {
            return false;
        }
        CollegeProfile other = (CollegeProfile) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ealp.entities.CollegeProfile[ id=" + id + " ]";
    }
    
}

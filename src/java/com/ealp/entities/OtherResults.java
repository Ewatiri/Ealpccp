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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eva
 */
@Entity
@Table(name = "other_results", catalog = "ealp1", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"sid", "testid"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OtherResults.findAll", query = "SELECT o FROM OtherResults o"),
    @NamedQuery(name = "OtherResults.findById", query = "SELECT o FROM OtherResults o WHERE o.id = :id"),
    @NamedQuery(name = "OtherResults.findByScore", query = "SELECT o FROM OtherResults o WHERE o.score = :score")})
public class OtherResults implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "score")
    private Integer score;
    @JoinColumn(name = "sid", referencedColumnName = "sid")
    @ManyToOne
    private Scholar sid;
    @JoinColumn(name = "testid", referencedColumnName = "testid")
    @ManyToOne
    private Test testid;

    public OtherResults() {
    }

    public OtherResults(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Scholar getSid() {
        return sid;
    }

    public void setSid(Scholar sid) {
        this.sid = sid;
    }

    public Test getTestid() {
        return testid;
    }

    public void setTestid(Test testid) {
        this.testid = testid;
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
        if (!(object instanceof OtherResults)) {
            return false;
        }
        OtherResults other = (OtherResults) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ealp.entities.OtherResults[ id=" + id + " ]";
    }
    
}

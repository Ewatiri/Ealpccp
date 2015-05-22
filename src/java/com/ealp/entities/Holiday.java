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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eva
 */
@Entity
@Table(name = "holiday", catalog = "ealp1", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Holiday.findAll", query = "SELECT h FROM Holiday h"),
    @NamedQuery(name = "Holiday.findBySid", query = "SELECT h FROM Holiday h WHERE h.sid = :sid")})
public class Holiday implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "sid", nullable = false)
    private Integer sid;
    @Lob
    @Size(max = 65535)
    @Column(name = "hol", length = 65535)
    private String hol;
    @Lob
    @Size(max = 65535)
    @Column(name = "interest", length = 65535)
    private String interest;
    @JoinColumn(name = "sid", referencedColumnName = "sid", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Scholar scholar;

    public Holiday() {
    }

    public Holiday(Integer sid) {
        this.sid = sid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getHol() {
        return hol;
    }

    public void setHol(String hol) {
        this.hol = hol;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public Scholar getScholar() {
        return scholar;
    }

    public void setScholar(Scholar scholar) {
        this.scholar = scholar;
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
        if (!(object instanceof Holiday)) {
            return false;
        }
        Holiday other = (Holiday) object;
        if ((this.sid == null && other.sid != null) || (this.sid != null && !this.sid.equals(other.sid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ealp.entities.Holiday[ sid=" + sid + " ]";
    }
    
}

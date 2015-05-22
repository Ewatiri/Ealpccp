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
@Table(name = "transcript", catalog = "ealp1", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transcript.findAll", query = "SELECT t FROM Transcript t"),
    @NamedQuery(name = "Transcript.findBySid", query = "SELECT t FROM Transcript t WHERE t.sid = :sid"),
    @NamedQuery(name = "Transcript.findByMath", query = "SELECT t FROM Transcript t WHERE t.math = :math"),
    @NamedQuery(name = "Transcript.findByEng", query = "SELECT t FROM Transcript t WHERE t.eng = :eng"),
    @NamedQuery(name = "Transcript.findBySwa", query = "SELECT t FROM Transcript t WHERE t.swa = :swa"),
    @NamedQuery(name = "Transcript.findByPhy", query = "SELECT t FROM Transcript t WHERE t.phy = :phy"),
    @NamedQuery(name = "Transcript.findByBio", query = "SELECT t FROM Transcript t WHERE t.bio = :bio"),
    @NamedQuery(name = "Transcript.findByChem", query = "SELECT t FROM Transcript t WHERE t.chem = :chem"),
    @NamedQuery(name = "Transcript.findByGeog", query = "SELECT t FROM Transcript t WHERE t.geog = :geog"),
    @NamedQuery(name = "Transcript.findByHist", query = "SELECT t FROM Transcript t WHERE t.hist = :hist"),
    @NamedQuery(name = "Transcript.findByRel", query = "SELECT t FROM Transcript t WHERE t.rel = :rel"),
    @NamedQuery(name = "Transcript.findByElect", query = "SELECT t FROM Transcript t WHERE t.elect = :elect"),
    @NamedQuery(name = "Transcript.findByEgrade", query = "SELECT t FROM Transcript t WHERE t.egrade = :egrade"),
    @NamedQuery(name = "Transcript.findByPosition", query = "SELECT t FROM Transcript t WHERE t.position = :position"),
    @NamedQuery(name = "Transcript.findByPop", query = "SELECT t FROM Transcript t WHERE t.pop = :pop")})
public class Transcript implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "sid", nullable = false)
    private Integer sid;
    @Column(name = "math")
    private Integer math;
    @Column(name = "eng")
    private Integer eng;
    @Column(name = "swa")
    private Integer swa;
    @Column(name = "phy")
    private Integer phy;
    @Column(name = "bio")
    private Integer bio;
    @Column(name = "chem")
    private Integer chem;
    @Column(name = "geog")
    private Integer geog;
    @Column(name = "hist")
    private Integer hist;
    @Column(name = "rel")
    private Integer rel;
    @Size(max = 20)
    @Column(name = "elect", length = 20)
    private String elect;
    @Column(name = "egrade")
    private Integer egrade;
    @Column(name = "position")
    private Integer position;
    @Column(name = "pop")
    private Integer pop;
    @JoinColumn(name = "sid", referencedColumnName = "sid", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Scholar scholar;

    public Transcript() {
    }

    public Transcript(Integer sid) {
        this.sid = sid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getMath() {
        return math;
    }

    public void setMath(Integer math) {
        this.math = math;
    }

    public Integer getEng() {
        return eng;
    }

    public void setEng(Integer eng) {
        this.eng = eng;
    }

    public Integer getSwa() {
        return swa;
    }

    public void setSwa(Integer swa) {
        this.swa = swa;
    }

    public Integer getPhy() {
        return phy;
    }

    public void setPhy(Integer phy) {
        this.phy = phy;
    }

    public Integer getBio() {
        return bio;
    }

    public void setBio(Integer bio) {
        this.bio = bio;
    }

    public Integer getChem() {
        return chem;
    }

    public void setChem(Integer chem) {
        this.chem = chem;
    }

    public Integer getGeog() {
        return geog;
    }

    public void setGeog(Integer geog) {
        this.geog = geog;
    }

    public Integer getHist() {
        return hist;
    }

    public void setHist(Integer hist) {
        this.hist = hist;
    }

    public Integer getRel() {
        return rel;
    }

    public void setRel(Integer rel) {
        this.rel = rel;
    }

    public String getElect() {
        return elect;
    }

    public void setElect(String elect) {
        this.elect = elect;
    }

    public Integer getEgrade() {
        return egrade;
    }

    public void setEgrade(Integer egrade) {
        this.egrade = egrade;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getPop() {
        return pop;
    }

    public void setPop(Integer pop) {
        this.pop = pop;
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
        if (!(object instanceof Transcript)) {
            return false;
        }
        Transcript other = (Transcript) object;
        if ((this.sid == null && other.sid != null) || (this.sid != null && !this.sid.equals(other.sid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ealp.entities.Transcript[ sid=" + sid + " ]";
    }
    
}

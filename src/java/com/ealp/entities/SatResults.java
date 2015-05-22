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
@Table(name = "sat_results", catalog = "ealp1", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"sid", "testid"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SatResults.findAll", query = "SELECT s FROM SatResults s"),
    @NamedQuery(name = "SatResults.findById", query = "SELECT s FROM SatResults s WHERE s.id = :id"),
    @NamedQuery(name = "SatResults.findByS1", query = "SELECT s FROM SatResults s WHERE s.s1 = :s1"),
    @NamedQuery(name = "SatResults.findByScore1", query = "SELECT s FROM SatResults s WHERE s.score1 = :score1"),
    @NamedQuery(name = "SatResults.findByS2", query = "SELECT s FROM SatResults s WHERE s.s2 = :s2"),
    @NamedQuery(name = "SatResults.findByScore2", query = "SELECT s FROM SatResults s WHERE s.score2 = :score2"),
    @NamedQuery(name = "SatResults.findByS3", query = "SELECT s FROM SatResults s WHERE s.s3 = :s3"),
    @NamedQuery(name = "SatResults.findByScore3", query = "SELECT s FROM SatResults s WHERE s.score3 = :score3")})
public class SatResults implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 30)
    @Column(name = "s1", length = 30)
    private String s1;
    @Column(name = "score1")
    private Integer score1;
    @Size(max = 30)
    @Column(name = "s2", length = 30)
    private String s2;
    @Column(name = "score2")
    private Integer score2;
    @Size(max = 30)
    @Column(name = "s3", length = 30)
    private String s3;
    @Column(name = "score3")
    private Integer score3;
    @JoinColumn(name = "sid", referencedColumnName = "sid")
    @ManyToOne
    private Scholar sid;
    @JoinColumn(name = "testid", referencedColumnName = "testid")
    @ManyToOne
    private Test testid;

    public SatResults() {
    }

    public SatResults(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    public Integer getScore1() {
        return score1;
    }

    public void setScore1(Integer score1) {
        this.score1 = score1;
    }

    public String getS2() {
        return s2;
    }

    public void setS2(String s2) {
        this.s2 = s2;
    }

    public Integer getScore2() {
        return score2;
    }

    public void setScore2(Integer score2) {
        this.score2 = score2;
    }

    public String getS3() {
        return s3;
    }

    public void setS3(String s3) {
        this.s3 = s3;
    }

    public Integer getScore3() {
        return score3;
    }

    public void setScore3(Integer score3) {
        this.score3 = score3;
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
        if (!(object instanceof SatResults)) {
            return false;
        }
        SatResults other = (SatResults) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ealp.entities.SatResults[ id=" + id + " ]";
    }
    
}

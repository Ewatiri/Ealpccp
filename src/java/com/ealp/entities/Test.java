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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author eva
 */
@Entity
@Table(name = "test", catalog = "ealp1", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Test.findAll", query = "SELECT t FROM Test t"),
    @NamedQuery(name = "Test.findByTestid", query = "SELECT t FROM Test t WHERE t.testid = :testid"),
    @NamedQuery(name = "Test.findByType", query = "SELECT t FROM Test t WHERE t.type = :type"),
    @NamedQuery(name = "Test.findByOfficial", query = "SELECT t FROM Test t WHERE t.official = :official"),
    @NamedQuery(name = "Test.findByTestTime", query = "SELECT t FROM Test t WHERE t.testTime = :testTime"),
    @NamedQuery(name = "Test.findByTestDate", query = "SELECT t FROM Test t WHERE t.testDate = :testDate"),
    @NamedQuery(name = "Test.findByLocation", query = "SELECT t FROM Test t WHERE t.location = :location")})
public class Test implements Serializable {
    @OneToMany(mappedBy = "testid")
    private List<Testlist> testlistList;
    @OneToMany(mappedBy = "testid")
    private List<SatResults> satResultsList;
    @OneToMany(mappedBy = "testid")
    private List<OtherResults> otherResultsList;
    @Size(max = 10)
    @Column(name = "type", length = 10)
    private String type;
    @Size(max = 20)
    @Column(name = "cycle", length = 20)
    private String cycle;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "testid", nullable = false)
    private Integer testid;
    @Lob
    @Size(max = 65535)
    @Column(name = "title", length = 65535)
    private String title;
    @Column(name = "official")
    private Integer official;
    @Column(name = "test_time")
    @Temporal(TemporalType.TIME)
    private Date testTime;
    @Column(name = "test_date")
    @Temporal(TemporalType.DATE)
    private Date testDate;
    @Size(max = 50)
    @Column(name = "location", length = 50)
    private String location;

    public Test() {
    }

    public Test(Integer testid) {
        this.testid = testid;
    }

    public Integer getTestid() {
        return testid;
    }

    public void setTestid(Integer testid) {
        this.testid = testid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Integer getOfficial() {
        return official;
    }

    public void setOfficial(Integer official) {
        this.official = official;
    }

    public Date getTestTime() {
        return testTime;
    }

    public void setTestTime(Date testTime) {
        this.testTime = testTime;
    }

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (testid != null ? testid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Test)) {
            return false;
        }
        Test other = (Test) object;
        if ((this.testid == null && other.testid != null) || (this.testid != null && !this.testid.equals(other.testid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ealp.entities.Test[ testid=" + testid + " ]";
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
    public List<Testlist> getTestlistList() {
        return testlistList;
    }

    public void setTestlistList(List<Testlist> testlistList) {
        this.testlistList = testlistList;
    }
    
}

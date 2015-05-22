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
@Table(name = "highschool", catalog = "ealp1", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Highschool.findAll", query = "SELECT h FROM Highschool h"),
    @NamedQuery(name = "Highschool.findBySid", query = "SELECT h FROM Highschool h WHERE h.sid = :sid"),
    @NamedQuery(name = "Highschool.findByName", query = "SELECT h FROM Highschool h WHERE h.name = :name"),
    @NamedQuery(name = "Highschool.findByLocation", query = "SELECT h FROM Highschool h WHERE h.location = :location"),
    @NamedQuery(name = "Highschool.findByType1", query = "SELECT h FROM Highschool h WHERE h.type1 = :type1"),
    @NamedQuery(name = "Highschool.findByType2", query = "SELECT h FROM Highschool h WHERE h.type2 = :type2"),
    @NamedQuery(name = "Highschool.findByType3", query = "SELECT h FROM Highschool h WHERE h.type3 = :type3"),
    @NamedQuery(name = "Highschool.findByPhone", query = "SELECT h FROM Highschool h WHERE h.phone = :phone"),
    @NamedQuery(name = "Highschool.findByPrincipal", query = "SELECT h FROM Highschool h WHERE h.principal = :principal"),
    @NamedQuery(name = "Highschool.findByMobile", query = "SELECT h FROM Highschool h WHERE h.mobile = :mobile"),
    @NamedQuery(name = "Highschool.findByMeangrade", query = "SELECT h FROM Highschool h WHERE h.meangrade = :meangrade"),
    @NamedQuery(name = "Highschool.findByFees", query = "SELECT h FROM Highschool h WHERE h.fees = :fees"),
    @NamedQuery(name = "Highschool.findByBeenhome", query = "SELECT h FROM Highschool h WHERE h.beenhome = :beenhome"),
    @NamedQuery(name = "Highschool.findBySponsor", query = "SELECT h FROM Highschool h WHERE h.sponsor = :sponsor")})
public class Highschool implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "sid", nullable = false)
    private Integer sid;
    @Size(max = 100)
    @Column(name = "name", length = 100)
    private String name;
    @Size(max = 60)
    @Column(name = "location", length = 60)
    private String location;
    @Size(max = 20)
    @Column(name = "type1", length = 20)
    private String type1;
    @Size(max = 20)
    @Column(name = "type2", length = 20)
    private String type2;
    @Size(max = 20)
    @Column(name = "type3", length = 20)
    private String type3;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 20)
    @Column(name = "phone", length = 20)
    private String phone;
    @Size(max = 60)
    @Column(name = "principal", length = 60)
    private String principal;
    @Size(max = 20)
    @Column(name = "mobile", length = 20)
    private String mobile;
    @Column(name = "meangrade")
    private double meangrade;
    @Size(max = 30)
    @Column(name = "fees", length = 30)
    private String fees;
    @Size(max = 3)
    @Column(name = "beenhome", length = 3)
    private String beenhome;
    @Size(max = 60)
    @Column(name = "sponsor", length = 60)
    private String sponsor;
    @Lob
    @Size(max = 65535)
    @Column(name = "description", length = 65535)
    private String description;
    @JoinColumn(name = "sid", referencedColumnName = "sid", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Scholar scholar;

    public Highschool() {
    }

    public Highschool(Integer sid) {
        this.sid = sid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public String getType3() {
        return type3;
    }

    public void setType3(String type3) {
        this.type3 = type3;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public double getMeangrade() {
        return meangrade;
    }

    public void setMeangrade(double meangrade) {
        this.meangrade = meangrade;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getBeenhome() {
        return beenhome;
    }

    public void setBeenhome(String beenhome) {
        this.beenhome = beenhome;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(object instanceof Highschool)) {
            return false;
        }
        Highschool other = (Highschool) object;
        if ((this.sid == null && other.sid != null) || (this.sid != null && !this.sid.equals(other.sid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ealp.entities.Highschool[ sid=" + sid + " ]";
    }
    
}

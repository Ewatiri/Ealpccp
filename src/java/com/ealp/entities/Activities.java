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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eva
 */
@Entity
@Table(name = "activities", catalog = "ealp1", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Activities.findAll", query = "SELECT a FROM Activities a"),
    @NamedQuery(name = "Activities.findByAid", query = "SELECT a FROM Activities a WHERE a.aid = :aid"),
    @NamedQuery(name = "Activities.findByType", query = "SELECT a FROM Activities a WHERE a.type = :type"),
    @NamedQuery(name = "Activities.findByClass1", query = "SELECT a FROM Activities a WHERE a.class1 = :class1"),
    @NamedQuery(name = "Activities.findByLevel", query = "SELECT a FROM Activities a WHERE a.level = :level")})
public class Activities implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "aid", nullable = false)
    private Integer aid;
    @Column(name = "type")
    private Integer type;
    @Size(max = 20)
    @Column(name = "class", length = 20)
    private String class1;
    @Lob
    @Size(max = 65535)
    @Column(name = "description", length = 65535)
    private String description;
    @Size(max = 30)
    @Column(name = "level", length = 30)
    private String level;
    @JoinColumn(name = "sid", referencedColumnName = "sid")
    @ManyToOne
    private Scholar sid;

    public Activities() {
    }

    public Activities(Integer aid) {
        this.aid = aid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getClass1() {
        return class1;
    }

    public void setClass1(String class1) {
        this.class1 = class1;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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
        hash += (aid != null ? aid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Activities)) {
            return false;
        }
        Activities other = (Activities) object;
        if ((this.aid == null && other.aid != null) || (this.aid != null && !this.aid.equals(other.aid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ealp.entities.Activities[ aid=" + aid + " ]";
    }
    
}

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
@Table(name = "sys")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sys.findAll", query = "SELECT s FROM Sys s"),
    @NamedQuery(name = "Sys.findById", query = "SELECT s FROM Sys s WHERE s.id = :id"),
    @NamedQuery(name = "Sys.findByAdmcycle", query = "SELECT s FROM Sys s WHERE s.admcycle = :admcycle"),
    @NamedQuery(name = "Sys.findByModule", query = "SELECT s FROM Sys s WHERE s.module = :module")})
public class Sys implements Serializable {
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "module", precision = 12)
    private Float module;
    @Column(name = "minscore", precision = 12)
    private Float minscore;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 30)
    @Column(name = "admcycle", length = 30)
    private String admcycle;

    public Sys() {
    }

    public Sys(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdmcycle() {
        return admcycle;
    }

    public void setAdmcycle(String admcycle) {
        this.admcycle = admcycle;
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
        if (!(object instanceof Sys)) {
            return false;
        }
        Sys other = (Sys) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ealp.entities.Sys[ id=" + id + " ]";
    }

    public Float getModule() {
        return module;
    }

    public void setModule(Float module) {
        this.module = module;
    }

    public Float getMinscore() {
        return minscore;
    }

    public void setMinscore(Float minscore) {
        this.minscore = minscore;
    }
    
}

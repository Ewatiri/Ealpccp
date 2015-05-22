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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eva
 */
@Entity
@Table(name = "highschools", catalog = "ealpcoke_ealp", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"school"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Highschools.findAll", query = "SELECT h FROM Highschools h"),
    @NamedQuery(name = "Highschools.findById", query = "SELECT h FROM Highschools h WHERE h.id = :id"),
    @NamedQuery(name = "Highschools.findBySchool", query = "SELECT h FROM Highschools h WHERE h.school = :school")})
public class Highschools implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "school", nullable = false, length = 255)
    private String school;

    public Highschools() {
    }

    public Highschools(Integer id) {
        this.id = id;
    }

    public Highschools(Integer id, String school) {
        this.id = id;
        this.school = school;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
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
        if (!(object instanceof Highschools)) {
            return false;
        }
        Highschools other = (Highschools) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ealp.entities.Highschools[ id=" + id + " ]";
    }
    
}

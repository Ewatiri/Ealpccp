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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eva
 */
@Entity
@Table(name = "invited_scholar", catalog = "ealp1", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"email"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InvitedScholar.findAll", query = "SELECT i FROM InvitedScholar i"),
    @NamedQuery(name = "InvitedScholar.findById", query = "SELECT i FROM InvitedScholar i WHERE i.id = :id"),
    @NamedQuery(name = "InvitedScholar.findByFullname", query = "SELECT i FROM InvitedScholar i WHERE i.fullname = :fullname"),
    @NamedQuery(name = "InvitedScholar.findByEmail", query = "SELECT i FROM InvitedScholar i WHERE i.email = :email"),
    @NamedQuery(name = "InvitedScholar.findBySchool", query = "SELECT i FROM InvitedScholar i WHERE i.school = :school"),
    @NamedQuery(name = "InvitedScholar.findByBranch", query = "SELECT i FROM InvitedScholar i WHERE i.branch = :branch"),
    @NamedQuery(name = "InvitedScholar.findByType", query = "SELECT i FROM InvitedScholar i WHERE i.type = :type"),
    @NamedQuery(name = "InvitedScholar.findByStatus", query = "SELECT i FROM InvitedScholar i WHERE i.status = :status")})
public class InvitedScholar implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "cycle", nullable = false, length = 15)
    private String cycle;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 70)
    @Column(name = "fullname", length = 70)
    private String fullname;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 60)
    @Column(name = "email", length = 60)
    private String email;
    @Size(max = 70)
    @Column(name = "school", length = 70)
    private String school;
    @Size(max = 60)
    @Column(name = "branch", length = 60)
    private String branch;
    @Column(name = "type")
    private Integer type;
    @Column(name = "status")
    private Integer status;
    @Lob
    @Size(max = 65535)
    @Column(name = "code", length = 65535)
    private String code;
    @JoinColumn(name = "approvedby", referencedColumnName = "uid")
    @ManyToOne
    private Mentor approvedby;

    public InvitedScholar() {
    }

    public InvitedScholar(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Mentor getApprovedby() {
        return approvedby;
    }

    public void setApprovedby(Mentor approvedby) {
        this.approvedby = approvedby;
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
        if (!(object instanceof InvitedScholar)) {
            return false;
        }
        InvitedScholar other = (InvitedScholar) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ealp.entities.InvitedScholar[ id=" + id + " ]";
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }
    
}

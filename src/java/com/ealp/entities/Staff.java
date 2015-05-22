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
@Table(name = "staff", catalog = "ealp1", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Staff.findAll", query = "SELECT s FROM Staff s"),
    @NamedQuery(name = "Staff.findById", query = "SELECT s FROM Staff s WHERE s.id = :id"),
    @NamedQuery(name = "Staff.findBySname", query = "SELECT s FROM Staff s WHERE s.sname = :sname"),
    @NamedQuery(name = "Staff.findByTitle", query = "SELECT s FROM Staff s WHERE s.title = :title"),
    @NamedQuery(name = "Staff.findByPfno", query = "SELECT s FROM Staff s WHERE s.pfno = :pfno"),
    @NamedQuery(name = "Staff.findByEmail", query = "SELECT s FROM Staff s WHERE s.email = :email"),
    @NamedQuery(name = "Staff.findByMobile", query = "SELECT s FROM Staff s WHERE s.mobile = :mobile"),
    @NamedQuery(name = "Staff.findByAvaya", query = "SELECT s FROM Staff s WHERE s.avaya = :avaya"),
    @NamedQuery(name = "Staff.findByRelation", query = "SELECT s FROM Staff s WHERE s.relation = :relation")})
public class Staff implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 70)
    @Column(name = "sname", length = 70)
    private String sname;
    @Size(max = 50)
    @Column(name = "title", length = 50)
    private String title;
    @Size(max = 10)
    @Column(name = "pfno", length = 10)
    private String pfno;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 70)
    @Column(name = "email", length = 70)
    private String email;
    @Size(max = 20)
    @Column(name = "mobile", length = 20)
    private String mobile;
    @Size(max = 20)
    @Column(name = "avaya", length = 20)
    private String avaya;
    @Size(max = 20)
    @Column(name = "relation", length = 20)
    private String relation;
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private InvitedScholar invitedScholar;

    public Staff() {
    }

    public Staff(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPfno() {
        return pfno;
    }

    public void setPfno(String pfno) {
        this.pfno = pfno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAvaya() {
        return avaya;
    }

    public void setAvaya(String avaya) {
        this.avaya = avaya;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public InvitedScholar getInvitedScholar() {
        return invitedScholar;
    }

    public void setInvitedScholar(InvitedScholar invitedScholar) {
        this.invitedScholar = invitedScholar;
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
        if (!(object instanceof Staff)) {
            return false;
        }
        Staff other = (Staff) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ealp.entities.Staff[ id=" + id + " ]";
    }
    
}

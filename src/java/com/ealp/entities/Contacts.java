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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eva
 */
@Entity
@Table(name = "contacts", catalog = "ealpcoke_ealp", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contacts.findAll", query = "SELECT c FROM Contacts c"),
    @NamedQuery(name = "Contacts.findByPfNo", query = "SELECT c FROM Contacts c WHERE c.pfNo = :pfNo"),
    @NamedQuery(name = "Contacts.findByEmail", query = "SELECT c FROM Contacts c WHERE c.email = :email"),
    @NamedQuery(name = "Contacts.findByPhone", query = "SELECT c FROM Contacts c WHERE c.phone = :phone"),
    @NamedQuery(name = "Contacts.findByAddress", query = "SELECT c FROM Contacts c WHERE c.address = :address")})
public class Contacts implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "pfNo", nullable = false, length = 255)
    private String pfNo;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "email", nullable = false, length = 255)
    private String email;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "phone", length = 255)
    private String phone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "address", nullable = false, length = 255)
    private String address;

    public Contacts() {
    }

    public Contacts(String pfNo) {
        this.pfNo = pfNo;
    }

    public Contacts(String pfNo, String email, String address) {
        this.pfNo = pfNo;
        this.email = email;
        this.address = address;
    }

    public String getPfNo() {
        return pfNo;
    }

    public void setPfNo(String pfNo) {
        this.pfNo = pfNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pfNo != null ? pfNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contacts)) {
            return false;
        }
        Contacts other = (Contacts) object;
        if ((this.pfNo == null && other.pfNo != null) || (this.pfNo != null && !this.pfNo.equals(other.pfNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ealp.entities.Contacts[ pfNo=" + pfNo + " ]";
    }
    
}

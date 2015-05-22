/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ealp.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eva
 */
@Entity
@Table(name = "login", catalog = "ealpcoke_ealp", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"username"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Login.findAll", query = "SELECT l FROM Login l"),
    @NamedQuery(name = "Login.findByPfNo", query = "SELECT l FROM Login l WHERE l.pfNo = :pfNo"),
    @NamedQuery(name = "Login.findByFName", query = "SELECT l FROM Login l WHERE l.fName = :fName"),
    @NamedQuery(name = "Login.findByMName", query = "SELECT l FROM Login l WHERE l.mName = :mName"),
    @NamedQuery(name = "Login.findByLName", query = "SELECT l FROM Login l WHERE l.lName = :lName"),
    @NamedQuery(name = "Login.findByGender", query = "SELECT l FROM Login l WHERE l.gender = :gender"),
    @NamedQuery(name = "Login.findByDob", query = "SELECT l FROM Login l WHERE l.dob = :dob"),
    @NamedQuery(name = "Login.findByEmail", query = "SELECT l FROM Login l WHERE l.email = :email"),
    @NamedQuery(name = "Login.findByUsername", query = "SELECT l FROM Login l WHERE l.username = :username"),
    @NamedQuery(name = "Login.findByPassword", query = "SELECT l FROM Login l WHERE l.password = :password"),
    @NamedQuery(name = "Login.findByAvator", query = "SELECT l FROM Login l WHERE l.avator = :avator"),
    @NamedQuery(name = "Login.findByUserType", query = "SELECT l FROM Login l WHERE l.userType = :userType"),
    @NamedQuery(name = "Login.findByYear", query = "SELECT l FROM Login l WHERE l.year = :year"),
    @NamedQuery(name = "Login.findByNationality", query = "SELECT l FROM Login l WHERE l.nationality = :nationality"),
    @NamedQuery(name = "Login.findByStatus", query = "SELECT l FROM Login l WHERE l.status = :status"),
    @NamedQuery(name = "Login.findByResetCount", query = "SELECT l FROM Login l WHERE l.resetCount = :resetCount")})
public class Login implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "pfNo", nullable = false, length = 10)
    private String pfNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "fName", nullable = false, length = 255)
    private String fName;
    @Size(max = 255)
    @Column(name = "mName", length = 255)
    private String mName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "lName", nullable = false, length = 255)
    private String lName;
    @Size(max = 5)
    @Column(name = "gender", length = 5)
    private String gender;
    @Column(name = "dob")
    @Temporal(TemporalType.DATE)
    private Date dob;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "email", nullable = false, length = 255)
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "username", nullable = false, length = 255)
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "password", nullable = false, length = 255)
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "avator", nullable = false, length = 255)
    private String avator;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "userType", nullable = false, length = 255)
    private String userType;
    @Size(max = 20)
    @Column(name = "year", length = 20)
    private String year;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nationality", nullable = false, length = 255)
    private String nationality;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status", nullable = false)
    private int status;
    @Column(name = "resetCount")
    private Integer resetCount;

    public Login() {
    }

    public Login(String pfNo) {
        this.pfNo = pfNo;
    }

    public Login(String pfNo, String fName, String lName, String email, String username, String password, String avator, String userType, String nationality, int status) {
        this.pfNo = pfNo;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.avator = avator;
        this.userType = userType;
        this.nationality = nationality;
        this.status = status;
    }

    public String getPfNo() {
        return pfNo;
    }

    public void setPfNo(String pfNo) {
        this.pfNo = pfNo;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getMName() {
        return mName;
    }

    public void setMName(String mName) {
        this.mName = mName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Integer getResetCount() {
        return resetCount;
    }

    public void setResetCount(Integer resetCount) {
        this.resetCount = resetCount;
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
        if (!(object instanceof Login)) {
            return false;
        }
        Login other = (Login) object;
        if ((this.pfNo == null && other.pfNo != null) || (this.pfNo != null && !this.pfNo.equals(other.pfNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ealp.entities.Login[ pfNo=" + pfNo + " ]";
    }
    
}

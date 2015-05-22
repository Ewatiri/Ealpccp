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
@Table(name = "call_feedback", catalog = "ealp1", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CallFeedback.findAll", query = "SELECT c FROM CallFeedback c"),
    @NamedQuery(name = "CallFeedback.findById", query = "SELECT c FROM CallFeedback c WHERE c.id = :id"),
    @NamedQuery(name = "CallFeedback.findByStatus", query = "SELECT c FROM CallFeedback c WHERE c.status = :status")})
public class CallFeedback implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Lob
    @Size(max = 65535)
    @Column(name = "feedback", length = 65535)
    private String feedback;
    @Column(name = "status")
    private Integer status;
    @JoinColumn(name = "sid", referencedColumnName = "sid")
    @ManyToOne
    private Scholar sid;
    @JoinColumn(name = "cid", referencedColumnName = "cid")
    @ManyToOne
    private CallList cid;

    public CallFeedback() {
    }

    public CallFeedback(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Scholar getSid() {
        return sid;
    }

    public void setSid(Scholar sid) {
        this.sid = sid;
    }

    public CallList getCid() {
        return cid;
    }

    public void setCid(CallList cid) {
        this.cid = cid;
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
        if (!(object instanceof CallFeedback)) {
            return false;
        }
        CallFeedback other = (CallFeedback) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ealp.entities.CallFeedback[ id=" + id + " ]";
    }
    
}

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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author eva
 */
@Entity
@Table(name = "call_list", catalog = "ealp1", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CallList.findAll", query = "SELECT c FROM CallList c"),
    @NamedQuery(name = "CallList.findByCid", query = "SELECT c FROM CallList c WHERE c.cid = :cid"),
    @NamedQuery(name = "CallList.findByDate", query = "SELECT c FROM CallList c WHERE c.date = :date")})
public class CallList implements Serializable {
    @OneToMany(mappedBy = "cid")
    private List<CallFeedback> callFeedbackList;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lastupdate", nullable = false)
    private int lastupdate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "cycle", nullable = false, length = 15)
    private String cycle;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "title", nullable = false, length = 65535)
    private String title;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cid", nullable = false)
    private Integer cid;
    @Lob
    @Size(max = 65535)
    @Column(name = "description", length = 65535)
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @JoinColumn(name = "createdby", referencedColumnName = "uid")
    @ManyToOne
    private Mentor createdby;

    public CallList() {
    }

    public CallList(Integer cid) {
        this.cid = cid;
    }

    public CallList(Integer cid, Date date) {
        this.cid = cid;
        this.date = date;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Mentor getCreatedby() {
        return createdby;
    }

    public void setCreatedby(Mentor createdby) {
        this.createdby = createdby;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cid != null ? cid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CallList)) {
            return false;
        }
        CallList other = (CallList) object;
        if ((this.cid == null && other.cid != null) || (this.cid != null && !this.cid.equals(other.cid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ealp.entities.CallList[ cid=" + cid + " ]";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public int getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(int lastupdate) {
        this.lastupdate = lastupdate;
    }

    @XmlTransient
    public List<CallFeedback> getCallFeedbackList() {
        return callFeedbackList;
    }

    public void setCallFeedbackList(List<CallFeedback> callFeedbackList) {
        this.callFeedbackList = callFeedbackList;
    }
    
}

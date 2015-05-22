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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eva
 */
@Entity
@Table(name = "announcement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Announcement.findAll", query = "SELECT a FROM Announcement a"),
    @NamedQuery(name = "Announcement.findByAnid", query = "SELECT a FROM Announcement a WHERE a.anid = :anid"),
    @NamedQuery(name = "Announcement.findByTitle", query = "SELECT a FROM Announcement a WHERE a.title = :title"),
    @NamedQuery(name = "Announcement.findByCtime", query = "SELECT a FROM Announcement a WHERE a.ctime = :ctime")})
public class Announcement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "anid", nullable = false)
    private Integer anid;
    @Size(max = 100)
    @Column(name = "title", length = 100)
    private String title;
    @Lob
    @Size(max = 65535)
    @Column(name = "description", length = 65535)
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ctime", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date ctime;
    @JoinColumn(name = "editedby", referencedColumnName = "uid")
    @ManyToOne
    private Mentor editedby;
    @JoinColumn(name = "createdby", referencedColumnName = "uid")
    @ManyToOne
    private Mentor createdby;

    public Announcement() {
    }

    public Announcement(Integer anid) {
        this.anid = anid;
    }

    public Announcement(Integer anid, Date ctime) {
        this.anid = anid;
        this.ctime = ctime;
    }

    public Integer getAnid() {
        return anid;
    }

    public void setAnid(Integer anid) {
        this.anid = anid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Mentor getEditedby() {
        return editedby;
    }

    public void setEditedby(Mentor editedby) {
        this.editedby = editedby;
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
        hash += (anid != null ? anid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Announcement)) {
            return false;
        }
        Announcement other = (Announcement) object;
        if ((this.anid == null && other.anid != null) || (this.anid != null && !this.anid.equals(other.anid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ealp.entities.Announcement[ anid=" + anid + " ]";
    }
    
}

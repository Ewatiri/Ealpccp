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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eva
 */
@Entity
@Table(name = "resource")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EALPresource.findAll", query = "SELECT e FROM EALPresource e"),
    @NamedQuery(name = "EALPresource.findByRid", query = "SELECT e FROM EALPresource e WHERE e.rid = :rid"),
    @NamedQuery(name = "EALPresource.findByType", query = "SELECT e FROM EALPresource e WHERE e.type = :type")})
public class EALPresource implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rid", nullable = false)
    private Integer rid;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "title", nullable = false, length = 65535)
    private String title;
    @Column(name = "type")
    private Integer type;
    @Lob
    @Size(max = 65535)
    @Column(name = "description", length = 65535)
    private String description;
    @Lob
    @Size(max = 65535)
    @Column(name = "url", length = 65535)
    private String url;
    @JoinColumn(name = "createdby", referencedColumnName = "uid")
    @ManyToOne
    private Mentor createdby;

    public EALPresource() {
    }

    public EALPresource(Integer rid) {
        this.rid = rid;
    }

    public EALPresource(Integer rid, String title) {
        this.rid = rid;
        this.title = title;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
        hash += (rid != null ? rid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EALPresource)) {
            return false;
        }
        EALPresource other = (EALPresource) object;
        if ((this.rid == null && other.rid != null) || (this.rid != null && !this.rid.equals(other.rid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ealp.entities.EALPresource[ rid=" + rid + " ]";
    }
    
}

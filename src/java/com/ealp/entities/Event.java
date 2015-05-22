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
@Table(name = "event", catalog = "ealp1", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Event.findAll", query = "SELECT e FROM Event e"),
    @NamedQuery(name = "Event.findByEid", query = "SELECT e FROM Event e WHERE e.eid = :eid"),
    @NamedQuery(name = "Event.findByTitle", query = "SELECT e FROM Event e WHERE e.title = :title"),
    @NamedQuery(name = "Event.findByVenue", query = "SELECT e FROM Event e WHERE e.venue = :venue"),
    @NamedQuery(name = "Event.findByEtime", query = "SELECT e FROM Event e WHERE e.etime = :etime"),
    @NamedQuery(name = "Event.findByEdate", query = "SELECT e FROM Event e WHERE e.edate = :edate"),
    @NamedQuery(name = "Event.findByTimestamp", query = "SELECT e FROM Event e WHERE e.timestamp = :timestamp")})
public class Event implements Serializable {
    @OneToMany(mappedBy = "eid")
    private List<Attendance> attendanceList;
    @Size(max = 20)
    @Column(name = "cycle", length = 20)
    private String cycle;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "eid", nullable = false)
    private Integer eid;
    @Size(max = 70)
    @Column(name = "title", length = 70)
    private String title;
    @Lob
    @Size(max = 65535)
    @Column(name = "description", length = 65535)
    private String description;
    @Size(max = 100)
    @Column(name = "venue", length = 100)
    private String venue;
    @Column(name = "etime")
    @Temporal(TemporalType.TIME)
    private Date etime;
    @Column(name = "edate")
    @Temporal(TemporalType.DATE)
    private Date edate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timestamp", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @JoinColumn(name = "postedby", referencedColumnName = "uid")
    @ManyToOne
    private Mentor postedby;

    public Event() {
    }

    public Event(Integer eid) {
        this.eid = eid;
    }

    public Event(Integer eid, Date timestamp) {
        this.eid = eid;
        this.timestamp = timestamp;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
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

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public Date getEtime() {
        return etime;
    }

    public void setEtime(Date etime) {
        this.etime = etime;
    }

    public Date getEdate() {
        return edate;
    }

    public void setEdate(Date edate) {
        this.edate = edate;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Mentor getPostedby() {
        return postedby;
    }

    public void setPostedby(Mentor postedby) {
        this.postedby = postedby;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (eid != null ? eid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Event)) {
            return false;
        }
        Event other = (Event) object;
        if ((this.eid == null && other.eid != null) || (this.eid != null && !this.eid.equals(other.eid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ealp.entities.Event[ eid=" + eid + " ]";
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    @XmlTransient
    public List<Attendance> getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(List<Attendance> attendanceList) {
        this.attendanceList = attendanceList;
    }
    
}

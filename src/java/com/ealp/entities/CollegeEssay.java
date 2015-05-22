/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ealp.entities;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author eva
 */
@Entity
@Table(name = "college_essay", catalog = "ealp1", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CollegeEssay.findAll", query = "SELECT c FROM CollegeEssay c"),
    @NamedQuery(name = "CollegeEssay.findByEssayid", query = "SELECT c FROM CollegeEssay c WHERE c.essayid = :essayid"),
    @NamedQuery(name = "CollegeEssay.findByCycle", query = "SELECT c FROM CollegeEssay c WHERE c.cycle = :cycle"),
    @NamedQuery(name = "CollegeEssay.findByRequired", query = "SELECT c FROM CollegeEssay c WHERE c.required = :required"),
    @NamedQuery(name = "CollegeEssay.findByStatus", query = "SELECT c FROM CollegeEssay c WHERE c.status = :status")})
public class CollegeEssay implements Serializable {
    @OneToMany(mappedBy = "essayid")
    private List<Document> documentList;
    @Column(name = "required")
    private Integer required;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "essayid", nullable = false)
    private Integer essayid;
    @Size(max = 30)
    @Column(name = "cycle", length = 30)
    private String cycle;
    @Lob
    @Size(max = 65535)
    @Column(name = "prompt", length = 65535)
    private String prompt;
    @Column(name = "status")
    private Integer status;
    @JoinColumn(name = "addedby", referencedColumnName = "uid")
    @ManyToOne
    private Mentor addedby;
    @JoinColumn(name = "collid", referencedColumnName = "collid")
    @ManyToOne
    private College collid;

    public CollegeEssay() {
    }

    public CollegeEssay(Integer essayid) {
        this.essayid = essayid;
    }

    public Integer getEssayid() {
        return essayid;
    }

    public void setEssayid(Integer essayid) {
        this.essayid = essayid;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Mentor getAddedby() {
        return addedby;
    }

    public void setAddedby(Mentor addedby) {
        this.addedby = addedby;
    }

    public College getCollid() {
        return collid;
    }

    public void setCollid(College collid) {
        this.collid = collid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (essayid != null ? essayid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CollegeEssay)) {
            return false;
        }
        CollegeEssay other = (CollegeEssay) object;
        if ((this.essayid == null && other.essayid != null) || (this.essayid != null && !this.essayid.equals(other.essayid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ealp.entities.CollegeEssay[ essayid=" + essayid + " ]";
    }

    public Integer getRequired() {
        return required;
    }

    public void setRequired(Integer required) {
        this.required = required;
    }

    @XmlTransient
    public List<Document> getDocumentList() {
        return documentList;
    }

    public void setDocumentList(List<Document> documentList) {
        this.documentList = documentList;
    }
    
}

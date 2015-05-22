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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "review", catalog = "ealp1", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Review.findAll", query = "SELECT r FROM Review r"),
    @NamedQuery(name = "Review.findBySid", query = "SELECT r FROM Review r WHERE r.sid = :sid"),
    @NamedQuery(name = "Review.findByScore1", query = "SELECT r FROM Review r WHERE r.score1 = :score1"),
    @NamedQuery(name = "Review.findByScore2", query = "SELECT r FROM Review r WHERE r.score2 = :score2"),
    @NamedQuery(name = "Review.findByScore3", query = "SELECT r FROM Review r WHERE r.score3 = :score3"),
    @NamedQuery(name = "Review.findByMcf1", query = "SELECT r FROM Review r WHERE r.mcf1 = :mcf1"),
    @NamedQuery(name = "Review.findByScore4", query = "SELECT r FROM Review r WHERE r.score4 = :score4"),
    @NamedQuery(name = "Review.findByScore5", query = "SELECT r FROM Review r WHERE r.score5 = :score5"),
    @NamedQuery(name = "Review.findByScore6", query = "SELECT r FROM Review r WHERE r.score6 = :score6"),
    @NamedQuery(name = "Review.findByMcf2", query = "SELECT r FROM Review r WHERE r.mcf2 = :mcf2"),
    @NamedQuery(name = "Review.findByScore7", query = "SELECT r FROM Review r WHERE r.score7 = :score7"),
    @NamedQuery(name = "Review.findByScore8", query = "SELECT r FROM Review r WHERE r.score8 = :score8"),
    @NamedQuery(name = "Review.findByScore9", query = "SELECT r FROM Review r WHERE r.score9 = :score9"),
    @NamedQuery(name = "Review.findByMcf3", query = "SELECT r FROM Review r WHERE r.mcf3 = :mcf3")})
public class Review implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "cycle", nullable = false, length = 20)
    private String cycle;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "sid", nullable = false)
    private Integer sid;
    @Column(name = "score1")
    private Integer score1;
    @Column(name = "score2")
    private Integer score2;
    @Column(name = "score3")
    private Integer score3;
    @Column(name = "mcf1")
    private Integer mcf1;
    @Lob
    @Size(max = 65535)
    @Column(name = "comment1", length = 65535)
    private String comment1;
    @Column(name = "score4")
    private Integer score4;
    @Column(name = "score5")
    private Integer score5;
    @Column(name = "score6")
    private Integer score6;
    @Column(name = "mcf2")
    private Integer mcf2;
    @Lob
    @Size(max = 65535)
    @Column(name = "comment2", length = 65535)
    private String comment2;
    @Column(name = "score7")
    private Integer score7;
    @Column(name = "score8")
    private Integer score8;
    @Column(name = "score9")
    private Integer score9;
    @Column(name = "mcf3")
    private Integer mcf3;
    @Lob
    @Size(max = 65535)
    @Column(name = "comment3", length = 65535)
    private String comment3;
    @JoinColumn(name = "sid", referencedColumnName = "sid", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Scholar scholar;
    @JoinColumn(name = "reviewer3", referencedColumnName = "uid")
    @ManyToOne
    private Mentor reviewer3;
    @JoinColumn(name = "reviewer2", referencedColumnName = "uid")
    @ManyToOne
    private Mentor reviewer2;
    @JoinColumn(name = "reviewer1", referencedColumnName = "uid")
    @ManyToOne
    private Mentor reviewer1;

    public Review() {
    }

    public Review(Integer sid) {
        this.sid = sid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getScore1() {
        return score1;
    }

    public void setScore1(Integer score1) {
        this.score1 = score1;
    }

    public Integer getScore2() {
        return score2;
    }

    public void setScore2(Integer score2) {
        this.score2 = score2;
    }

    public Integer getScore3() {
        return score3;
    }

    public void setScore3(Integer score3) {
        this.score3 = score3;
    }

    public Integer getMcf1() {
        return mcf1;
    }

    public void setMcf1(Integer mcf1) {
        this.mcf1 = mcf1;
    }

    public String getComment1() {
        return comment1;
    }

    public void setComment1(String comment1) {
        this.comment1 = comment1;
    }

    public Integer getScore4() {
        return score4;
    }

    public void setScore4(Integer score4) {
        this.score4 = score4;
    }

    public Integer getScore5() {
        return score5;
    }

    public void setScore5(Integer score5) {
        this.score5 = score5;
    }

    public Integer getScore6() {
        return score6;
    }

    public void setScore6(Integer score6) {
        this.score6 = score6;
    }

    public Integer getMcf2() {
        return mcf2;
    }

    public void setMcf2(Integer mcf2) {
        this.mcf2 = mcf2;
    }

    public String getComment2() {
        return comment2;
    }

    public void setComment2(String comment2) {
        this.comment2 = comment2;
    }

    public Integer getScore7() {
        return score7;
    }

    public void setScore7(Integer score7) {
        this.score7 = score7;
    }

    public Integer getScore8() {
        return score8;
    }

    public void setScore8(Integer score8) {
        this.score8 = score8;
    }

    public Integer getScore9() {
        return score9;
    }

    public void setScore9(Integer score9) {
        this.score9 = score9;
    }

    public Integer getMcf3() {
        return mcf3;
    }

    public void setMcf3(Integer mcf3) {
        this.mcf3 = mcf3;
    }

    public String getComment3() {
        return comment3;
    }

    public void setComment3(String comment3) {
        this.comment3 = comment3;
    }

    public Scholar getScholar() {
        return scholar;
    }

    public void setScholar(Scholar scholar) {
        this.scholar = scholar;
    }

    public Mentor getReviewer3() {
        return reviewer3;
    }

    public void setReviewer3(Mentor reviewer3) {
        this.reviewer3 = reviewer3;
    }

    public Mentor getReviewer2() {
        return reviewer2;
    }

    public void setReviewer2(Mentor reviewer2) {
        this.reviewer2 = reviewer2;
    }

    public Mentor getReviewer1() {
        return reviewer1;
    }

    public void setReviewer1(Mentor reviewer1) {
        this.reviewer1 = reviewer1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sid != null ? sid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Review)) {
            return false;
        }
        Review other = (Review) object;
        if ((this.sid == null && other.sid != null) || (this.sid != null && !this.sid.equals(other.sid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ealp.entities.Review[ sid=" + sid + " ]";
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }
    
}

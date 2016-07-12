/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jayaram
 */
@Entity
@Table(name = "EXPERIENCE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Experience.findAll", query = "SELECT e FROM Experience e"),
    @NamedQuery(name = "Experience.findByExperienceid", query = "SELECT e FROM Experience e WHERE e.experienceid = :experienceid"),
    @NamedQuery(name = "Experience.findByCategory", query = "SELECT e FROM Experience e WHERE e.category = :category"),
    @NamedQuery(name = "Experience.findByCompany", query = "SELECT e FROM Experience e WHERE e.company = :company"),
    @NamedQuery(name = "Experience.findByFinishdate", query = "SELECT e FROM Experience e WHERE e.finishdate = :finishdate"),
    @NamedQuery(name = "Experience.findByPostition", query = "SELECT e FROM Experience e WHERE e.postition = :postition"),
    @NamedQuery(name = "Experience.findByStartdate", query = "SELECT e FROM Experience e WHERE e.startdate = :startdate"),
    @NamedQuery(name = "Experience.findByStillinthisrole", query = "SELECT e FROM Experience e WHERE e.stillinthisrole = :stillinthisrole"),
    @NamedQuery(name = "Experience.findByTimestamp", query = "SELECT e FROM Experience e WHERE e.timestamp = :timestamp"),
    @NamedQuery(name = "Experience.findByWorksummary", query = "SELECT e FROM Experience e WHERE e.worksummary = :worksummary")})
public class Experience implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "EXPERIENCEID")
    private Integer experienceid;
    @Size(max = 255)
    @Column(name = "CATEGORY")
    private String category;
    @Size(max = 255)
    @Column(name = "COMPANY")
    private String company;
    @Column(name = "FINISHDATE")
    @Temporal(TemporalType.DATE)
    private Date finishdate;
    @Size(max = 255)
    @Column(name = "POSTITION")
    private String postition;
    @Column(name = "STARTDATE")
    @Temporal(TemporalType.DATE)
    private Date startdate;
    @Column(name = "STILLINTHISROLE")
    private Short stillinthisrole;
    @Column(name = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @Size(max = 255)
    @Column(name = "WORKSUMMARY")
    private String worksummary;
    @JoinColumn(name = "JOBSEEKERID", referencedColumnName = "JOBSEEKERID")
    @ManyToOne
    private Jobseeker jobseeker;

    public Experience() {
    }

    public Experience(Integer experienceid) {
        this.experienceid = experienceid;
    }

    public Integer getExperienceid() {
        return experienceid;
    }

    public void setExperienceid(Integer experienceid) {
        this.experienceid = experienceid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Date getFinishdate() {
        return finishdate;
    }

    public void setFinishdate(Date finishdate) {
        this.finishdate = finishdate;
    }

    public String getPostition() {
        return postition;
    }

    public void setPostition(String postition) {
        this.postition = postition;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Short getStillinthisrole() {
        return stillinthisrole;
    }

    public void setStillinthisrole(Short stillinthisrole) {
        this.stillinthisrole = stillinthisrole;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getWorksummary() {
        return worksummary;
    }

    public void setWorksummary(String worksummary) {
        this.worksummary = worksummary;
    }

    public Jobseeker getJobseeker() {
        return jobseeker;
    }

    public void setJobseeker(Jobseeker jobseeker) {
        this.jobseeker = jobseeker;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (experienceid != null ? experienceid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Experience)) {
            return false;
        }
        Experience other = (Experience) object;
        if ((this.experienceid == null && other.experienceid != null) || (this.experienceid != null && !this.experienceid.equals(other.experienceid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Experience[ experienceid=" + experienceid + " ]";
    }
    
}

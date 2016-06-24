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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
 * @author binod
 */
@Entity
@Table(name = "EXPERIENCE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Experience.findAll", query = "SELECT e FROM Experience e"),
    @NamedQuery(name = "Experience.findByExperienceid", query = "SELECT e FROM Experience e WHERE e.experienceid = :experienceid"),
    @NamedQuery(name = "Experience.findByPostition", query = "SELECT e FROM Experience e WHERE e.postition = :postition"),
    @NamedQuery(name = "Experience.findByCompany", query = "SELECT e FROM Experience e WHERE e.company = :company"),
    @NamedQuery(name = "Experience.findByCategory", query = "SELECT e FROM Experience e WHERE e.category = :category"),
    @NamedQuery(name = "Experience.findByStartdate", query = "SELECT e FROM Experience e WHERE e.startdate = :startdate"),
    @NamedQuery(name = "Experience.findByFinishdate", query = "SELECT e FROM Experience e WHERE e.finishdate = :finishdate"),
    @NamedQuery(name = "Experience.findByStillinthisrole", query = "SELECT e FROM Experience e WHERE e.stillinthisrole = :stillinthisrole"),
    @NamedQuery(name = "Experience.findByWorksummary", query = "SELECT e FROM Experience e WHERE e.worksummary = :worksummary"),
    @NamedQuery(name = "Experience.findByTimestamp", query = "SELECT e FROM Experience e WHERE e.timestamp = :timestamp")})
public class Experience implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "EXPERIENCEID")
    private Integer experienceid;
    @Size(max = 250)
    @Column(name = "POSTITION")
    private String postition;
    @Size(max = 250)
    @Column(name = "COMPANY")
    private String company;
    @Size(max = 250)
    @Column(name = "CATEGORY")
    private String category;
    @Column(name = "STARTDATE")
    @Temporal(TemporalType.DATE)
    private Date startdate;
    @Column(name = "FINISHDATE")
    @Temporal(TemporalType.DATE)
    private Date finishdate;
    @Column(name = "STILLINTHISROLE")
    private Boolean stillinthisrole;
    @Size(max = 250)
    @Column(name = "WORKSUMMARY")
    private String worksummary;
    @Column(name = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @JoinColumn(name = "JOBSEEKERID", referencedColumnName = "JOBSEEKERID")
    @ManyToOne
    private Jobseeker jobseekerid;

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

    public String getPostition() {
        return postition;
    }

    public void setPostition(String postition) {
        this.postition = postition;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getFinishdate() {
        return finishdate;
    }

    public void setFinishdate(Date finishdate) {
        this.finishdate = finishdate;
    }

    public Boolean getStillinthisrole() {
        return stillinthisrole;
    }

    public void setStillinthisrole(Boolean stillinthisrole) {
        this.stillinthisrole = stillinthisrole;
    }

    public String getWorksummary() {
        return worksummary;
    }

    public void setWorksummary(String worksummary) {
        this.worksummary = worksummary;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Jobseeker getJobseekerid() {
        return jobseekerid;
    }

    public void setJobseekerid(Jobseeker jobseekerid) {
        this.jobseekerid = jobseekerid;
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

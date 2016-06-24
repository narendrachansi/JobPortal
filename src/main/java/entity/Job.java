/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @author binod
 */
@Entity
@Table(name = "JOB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Job.findAll", query = "SELECT j FROM Job j"),
    @NamedQuery(name = "Job.findByJobid", query = "SELECT j FROM Job j WHERE j.jobid = :jobid"),
    @NamedQuery(name = "Job.findByTitle", query = "SELECT j FROM Job j WHERE j.title = :title"),
    @NamedQuery(name = "Job.findByLocation", query = "SELECT j FROM Job j WHERE j.location = :location"),
    @NamedQuery(name = "Job.findByJobsummary", query = "SELECT j FROM Job j WHERE j.jobsummary = :jobsummary"),
    @NamedQuery(name = "Job.findByWorktype", query = "SELECT j FROM Job j WHERE j.worktype = :worktype"),
    @NamedQuery(name = "Job.findByExpirydate", query = "SELECT j FROM Job j WHERE j.expirydate = :expirydate"),
    @NamedQuery(name = "Job.findBySalaryrange", query = "SELECT j FROM Job j WHERE j.salaryrange = :salaryrange"),
    @NamedQuery(name = "Job.findByIspublished", query = "SELECT j FROM Job j WHERE j.ispublished = :ispublished"),
    @NamedQuery(name = "Job.findByTimestamp", query = "SELECT j FROM Job j WHERE j.timestamp = :timestamp")})
public class Job implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "JOBID")
    private Integer jobid;
    @Size(max = 250)
    @Column(name = "TITLE")
    private String title;
    @Lob
    @Size(max = 32700)
    @Column(name = "DESCRIPTION")
    private String description;
    @Size(max = 500)
    @Column(name = "LOCATION")
    private String location;
    @Size(max = 500)
    @Column(name = "JOBSUMMARY")
    private String jobsummary;
    @Size(max = 50)
    @Column(name = "WORKTYPE")
    private String worktype;
    @Column(name = "EXPIRYDATE")
    @Temporal(TemporalType.DATE)
    private Date expirydate;
    @Size(max = 250)
    @Column(name = "SALARYRANGE")
    private String salaryrange;
    @Column(name = "ISPUBLISHED")
    private Boolean ispublished;
    @Column(name = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @OneToMany(mappedBy = "jobid")
    private Collection<Payment> paymentCollection;
    @JoinColumn(name = "RECRUITERID", referencedColumnName = "RECRUITERID")
    @ManyToOne
    private Recruiter recruiterid;
    @JoinColumn(name = "CATEGORYID", referencedColumnName = "CATEGORYID")
    @ManyToOne
    private Category categoryid;
    @OneToMany(mappedBy = "jobid")
    private Collection<Jobapplication> jobapplicationCollection;

    public Job() {
    }

    public Job(Integer jobid) {
        this.jobid = jobid;
    }

    public Integer getJobid() {
        return jobid;
    }

    public void setJobid(Integer jobid) {
        this.jobid = jobid;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getJobsummary() {
        return jobsummary;
    }

    public void setJobsummary(String jobsummary) {
        this.jobsummary = jobsummary;
    }

    public String getWorktype() {
        return worktype;
    }

    public void setWorktype(String worktype) {
        this.worktype = worktype;
    }

    public Date getExpirydate() {
        return expirydate;
    }

    public void setExpirydate(Date expirydate) {
        this.expirydate = expirydate;
    }

    public String getSalaryrange() {
        return salaryrange;
    }

    public void setSalaryrange(String salaryrange) {
        this.salaryrange = salaryrange;
    }

    public Boolean getIspublished() {
        return ispublished;
    }

    public void setIspublished(Boolean ispublished) {
        this.ispublished = ispublished;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @XmlTransient
    public Collection<Payment> getPaymentCollection() {
        return paymentCollection;
    }

    public void setPaymentCollection(Collection<Payment> paymentCollection) {
        this.paymentCollection = paymentCollection;
    }

    public Recruiter getRecruiterid() {
        return recruiterid;
    }

    public void setRecruiterid(Recruiter recruiterid) {
        this.recruiterid = recruiterid;
    }

    public Category getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Category categoryid) {
        this.categoryid = categoryid;
    }

    @XmlTransient
    public Collection<Jobapplication> getJobapplicationCollection() {
        return jobapplicationCollection;
    }

    public void setJobapplicationCollection(Collection<Jobapplication> jobapplicationCollection) {
        this.jobapplicationCollection = jobapplicationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jobid != null ? jobid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Job)) {
            return false;
        }
        Job other = (Job) object;
        if ((this.jobid == null && other.jobid != null) || (this.jobid != null && !this.jobid.equals(other.jobid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Job[ jobid=" + jobid + " ]";
    }
    
}

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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jayaram
 */
@Entity
@Table(name = "JOBAPPLICATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jobapplication.findAll", query = "SELECT j FROM Jobapplication j"),
    @NamedQuery(name = "Jobapplication.findByJobapplicationid", query = "SELECT j FROM Jobapplication j WHERE j.jobapplicationid = :jobapplicationid"),
    @NamedQuery(name = "Jobapplication.findByApplieddate", query = "SELECT j FROM Jobapplication j WHERE j.applieddate = :applieddate"),
    @NamedQuery(name = "Jobapplication.findByTimestamp", query = "SELECT j FROM Jobapplication j WHERE j.timestamp = :timestamp")})
public class Jobapplication implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "JOBAPPLICATIONID")
    private Integer jobapplicationid;
    @Column(name = "APPLIEDDATE")
    @Temporal(TemporalType.DATE)
    private Date applieddate;
    @Column(name = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @OneToMany(mappedBy = "jobapplication")
    private Collection<Document> documentCollection;
    @JoinColumn(name = "JOBSEEKERID", referencedColumnName = "JOBSEEKERID")
    @ManyToOne
    private Jobseeker jobseeker;
    @JoinColumn(name = "JOBID", referencedColumnName = "JOBID")
    @ManyToOne
    private Job job;

    public Jobapplication() {
    }

    public Jobapplication(Integer jobapplicationid) {
        this.jobapplicationid = jobapplicationid;
    }

    public Integer getJobapplicationid() {
        return jobapplicationid;
    }

    public void setJobapplicationid(Integer jobapplicationid) {
        this.jobapplicationid = jobapplicationid;
    }

    public Date getApplieddate() {
        return applieddate;
    }

    public void setApplieddate(Date applieddate) {
        this.applieddate = applieddate;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @XmlTransient
    public Collection<Document> getDocumentCollection() {
        return documentCollection;
    }

    public void setDocumentCollection(Collection<Document> documentCollection) {
        this.documentCollection = documentCollection;
    }

    public Jobseeker getJobseeker() {
        return jobseeker;
    }

    public void setJobseeker(Jobseeker jobseeker) {
        this.jobseeker = jobseeker;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jobapplicationid != null ? jobapplicationid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jobapplication)) {
            return false;
        }
        Jobapplication other = (Jobapplication) object;
        if ((this.jobapplicationid == null && other.jobapplicationid != null) || (this.jobapplicationid != null && !this.jobapplicationid.equals(other.jobapplicationid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Jobapplication[ jobapplicationid=" + jobapplicationid + " ]";
    }
    
}

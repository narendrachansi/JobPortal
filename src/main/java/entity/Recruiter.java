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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jayaram
 */
@Entity
@Table(name = "RECRUITER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recruiter.findAll", query = "SELECT r FROM Recruiter r"),
    @NamedQuery(name = "Recruiter.findByRecruiterid", query = "SELECT r FROM Recruiter r WHERE r.recruiterid = :recruiterid"),
    @NamedQuery(name = "Recruiter.findByFirstname", query = "SELECT r FROM Recruiter r WHERE r.firstname = :firstname"),
    @NamedQuery(name = "Recruiter.findByIsactive", query = "SELECT r FROM Recruiter r WHERE r.isactive = :isactive"),
    @NamedQuery(name = "Recruiter.findByLastname", query = "SELECT r FROM Recruiter r WHERE r.lastname = :lastname"),
    @NamedQuery(name = "Recruiter.findByTimestamp", query = "SELECT r FROM Recruiter r WHERE r.timestamp = :timestamp")})
public class Recruiter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "RECRUITERID")
    private Integer recruiterid;
    @Size(max = 255)
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Column(name = "ISACTIVE")
    private Short isactive;
    @Size(max = 255)
    @Column(name = "LASTNAME")
    private String lastname;
    @Column(name = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @JoinColumn(name = "RECRUITERID", referencedColumnName = "USERID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Users users;
    @JoinColumn(name = "COMPANYID", referencedColumnName = "COMPANYID")
    @ManyToOne
    private Company company;
    @OneToMany(mappedBy = "recruiter")
    private Collection<Job> jobCollection;

    public Recruiter() {
    }

    public Recruiter(Integer recruiterid) {
        this.recruiterid = recruiterid;
    }

    public Integer getRecruiterid() {
        return recruiterid;
    }

    public void setRecruiterid(Integer recruiterid) {
        this.recruiterid = recruiterid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Short getIsactive() {
        return isactive;
    }

    public void setIsactive(Short isactive) {
        this.isactive = isactive;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @XmlTransient
    public Collection<Job> getJobCollection() {
        return jobCollection;
    }

    public void setJobCollection(Collection<Job> jobCollection) {
        this.jobCollection = jobCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recruiterid != null ? recruiterid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recruiter)) {
            return false;
        }
        Recruiter other = (Recruiter) object;
        if ((this.recruiterid == null && other.recruiterid != null) || (this.recruiterid != null && !this.recruiterid.equals(other.recruiterid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Recruiter[ recruiterid=" + recruiterid + " ]";
    }
    
}

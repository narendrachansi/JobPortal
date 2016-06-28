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
@Table(name = "JOBSEEKER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jobseeker.findAll", query = "SELECT j FROM Jobseeker j"),
    @NamedQuery(name = "Jobseeker.findByJobseekerid", query = "SELECT j FROM Jobseeker j WHERE j.jobseekerid = :jobseekerid"),
    @NamedQuery(name = "Jobseeker.findByAddress", query = "SELECT j FROM Jobseeker j WHERE j.address = :address"),
    @NamedQuery(name = "Jobseeker.findByCvpath", query = "SELECT j FROM Jobseeker j WHERE j.cvpath = :cvpath"),
    @NamedQuery(name = "Jobseeker.findByDob", query = "SELECT j FROM Jobseeker j WHERE j.dob = :dob"),
    @NamedQuery(name = "Jobseeker.findByEmail", query = "SELECT j FROM Jobseeker j WHERE j.email = :email"),
    @NamedQuery(name = "Jobseeker.findByFirstname", query = "SELECT j FROM Jobseeker j WHERE j.firstname = :firstname"),
    @NamedQuery(name = "Jobseeker.findByLastname", query = "SELECT j FROM Jobseeker j WHERE j.lastname = :lastname"),
    @NamedQuery(name = "Jobseeker.findByPhone", query = "SELECT j FROM Jobseeker j WHERE j.phone = :phone"),
    @NamedQuery(name = "Jobseeker.findByQualification", query = "SELECT j FROM Jobseeker j WHERE j.qualification = :qualification"),
    @NamedQuery(name = "Jobseeker.findBySkills", query = "SELECT j FROM Jobseeker j WHERE j.skills = :skills"),
    @NamedQuery(name = "Jobseeker.findByTimestamp", query = "SELECT j FROM Jobseeker j WHERE j.timestamp = :timestamp")})
public class Jobseeker implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "JOBSEEKERID")
    private Integer jobseekerid;
    @Size(max = 255)
    @Column(name = "ADDRESS")
    private String address;
    @Size(max = 255)
    @Column(name = "CVPATH")
    private String cvpath;
    @Column(name = "DOB")
    @Temporal(TemporalType.DATE)
    private Date dob;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 255)
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Size(max = 255)
    @Column(name = "LASTNAME")
    private String lastname;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "PHONE")
    private String phone;
    @Size(max = 255)
    @Column(name = "QUALIFICATION")
    private String qualification;
    @Size(max = 255)
    @Column(name = "SKILLS")
    private String skills;
    @Column(name = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @OneToMany(mappedBy = "jobseeker")
    private Collection<Experience> experienceCollection;
    @JoinColumn(name = "JOBSEEKERID", referencedColumnName = "USERID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Users users;
    @OneToMany(mappedBy = "jobseeker")
    private Collection<Jobapplication> jobapplicationCollection;

    public Jobseeker() {
    }

    public Jobseeker(Integer jobseekerid) {
        this.jobseekerid = jobseekerid;
    }

    public Integer getJobseekerid() {
        return jobseekerid;
    }

    public void setJobseekerid(Integer jobseekerid) {
        this.jobseekerid = jobseekerid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCvpath() {
        return cvpath;
    }

    public void setCvpath(String cvpath) {
        this.cvpath = cvpath;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @XmlTransient
    public Collection<Experience> getExperienceCollection() {
        return experienceCollection;
    }

    public void setExperienceCollection(Collection<Experience> experienceCollection) {
        this.experienceCollection = experienceCollection;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
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
        hash += (jobseekerid != null ? jobseekerid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jobseeker)) {
            return false;
        }
        Jobseeker other = (Jobseeker) object;
        if ((this.jobseekerid == null && other.jobseekerid != null) || (this.jobseekerid != null && !this.jobseekerid.equals(other.jobseekerid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Jobseeker[ jobseekerid=" + jobseekerid + " ]";
    }
    
}

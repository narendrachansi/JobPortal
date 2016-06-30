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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jayaram
 */
@Entity
@Table(name = "COMPANY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Company.findAll", query = "SELECT c FROM Company c"),
    @NamedQuery(name = "Company.findByCompanyid", query = "SELECT c FROM Company c WHERE c.companyid = :companyid"),
    @NamedQuery(name = "Company.findByAddress", query = "SELECT c FROM Company c WHERE c.address = :address"),
    @NamedQuery(name = "Company.findByCategory", query = "SELECT c FROM Company c WHERE c.category = :category"),
    @NamedQuery(name = "Company.findByEmail", query = "SELECT c FROM Company c WHERE c.email = :email"),
    @NamedQuery(name = "Company.findByLogopath", query = "SELECT c FROM Company c WHERE c.logopath = :logopath"),
    @NamedQuery(name = "Company.findByName", query = "SELECT c FROM Company c WHERE c.name = :name"),
    @NamedQuery(name = "Company.findByPhone", query = "SELECT c FROM Company c WHERE c.phone = :phone"),
    @NamedQuery(name = "Company.findByTimestamp", query = "SELECT c FROM Company c WHERE c.timestamp = :timestamp")})
public class Company implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "COMPANYID")
    private Integer companyid;
    @Size(max = 255)
    @Column(name = "ADDRESS")
    private String address;
    @Size(max = 255)
    @Column(name = "CATEGORY")
    private String category;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 255)
    @Column(name = "LOGOPATH")
    private String logopath;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @OneToMany(mappedBy = "company")
    private Collection<Recruiter> recruiterCollection;

    public Company() {
    }

    public Company(Integer companyid) {
        this.companyid = companyid;
    }

    public Integer getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogopath() {
        return logopath;
    }

    public void setLogopath(String logopath) {
        this.logopath = logopath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @XmlTransient
    public Collection<Recruiter> getRecruiterCollection() {
        return recruiterCollection;
    }

    public void setRecruiterCollection(Collection<Recruiter> recruiterCollection) {
        this.recruiterCollection = recruiterCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (companyid != null ? companyid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Company)) {
            return false;
        }
        Company other = (Company) object;
        if ((this.companyid == null && other.companyid != null) || (this.companyid != null && !this.companyid.equals(other.companyid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Company[ companyid=" + companyid + " ]";
    }
    
}

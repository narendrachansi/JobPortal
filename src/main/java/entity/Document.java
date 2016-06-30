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
@Table(name = "DOCUMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Document.findAll", query = "SELECT d FROM Document d"),
    @NamedQuery(name = "Document.findByDocumentid", query = "SELECT d FROM Document d WHERE d.documentid = :documentid"),
    @NamedQuery(name = "Document.findByDocumentpath", query = "SELECT d FROM Document d WHERE d.documentpath = :documentpath"),
    @NamedQuery(name = "Document.findByDocumenttype", query = "SELECT d FROM Document d WHERE d.documenttype = :documenttype"),
    @NamedQuery(name = "Document.findByTimestamp", query = "SELECT d FROM Document d WHERE d.timestamp = :timestamp")})
public class Document implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DOCUMENTID")
    private Integer documentid;
    @Size(max = 255)
    @Column(name = "DOCUMENTPATH")
    private String documentpath;
    @Size(max = 255)
    @Column(name = "DOCUMENTTYPE")
    private String documenttype;
    @Column(name = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @JoinColumn(name = "JOBAPPLICATIONID", referencedColumnName = "JOBAPPLICATIONID")
    @ManyToOne
    private Jobapplication jobapplication;

    public Document() {
    }

    public Document(Integer documentid) {
        this.documentid = documentid;
    }

    public Integer getDocumentid() {
        return documentid;
    }

    public void setDocumentid(Integer documentid) {
        this.documentid = documentid;
    }

    public String getDocumentpath() {
        return documentpath;
    }

    public void setDocumentpath(String documentpath) {
        this.documentpath = documentpath;
    }

    public String getDocumenttype() {
        return documenttype;
    }

    public void setDocumenttype(String documenttype) {
        this.documenttype = documenttype;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Jobapplication getJobapplication() {
        return jobapplication;
    }

    public void setJobapplication(Jobapplication jobapplication) {
        this.jobapplication = jobapplication;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (documentid != null ? documentid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Document)) {
            return false;
        }
        Document other = (Document) object;
        if ((this.documentid == null && other.documentid != null) || (this.documentid != null && !this.documentid.equals(other.documentid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Document[ documentid=" + documentid + " ]";
    }
    
}

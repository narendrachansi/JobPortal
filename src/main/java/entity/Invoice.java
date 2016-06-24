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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author binod
 */
@Entity
@Table(name = "INVOICE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Invoice.findAll", query = "SELECT i FROM Invoice i"),
    @NamedQuery(name = "Invoice.findByInvoiceid", query = "SELECT i FROM Invoice i WHERE i.invoiceid = :invoiceid"),
    @NamedQuery(name = "Invoice.findByTimestamp", query = "SELECT i FROM Invoice i WHERE i.timestamp = :timestamp")})
public class Invoice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "INVOICEID")
    private Integer invoiceid;
    @Column(name = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @JoinColumn(name = "PAYMENTID", referencedColumnName = "PAYMENTID")
    @ManyToOne
    private Payment paymentid;

    public Invoice() {
    }

    public Invoice(Integer invoiceid) {
        this.invoiceid = invoiceid;
    }

    public Integer getInvoiceid() {
        return invoiceid;
    }

    public void setInvoiceid(Integer invoiceid) {
        this.invoiceid = invoiceid;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Payment getPaymentid() {
        return paymentid;
    }

    public void setPaymentid(Payment paymentid) {
        this.paymentid = paymentid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (invoiceid != null ? invoiceid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Invoice)) {
            return false;
        }
        Invoice other = (Invoice) object;
        if ((this.invoiceid == null && other.invoiceid != null) || (this.invoiceid != null && !this.invoiceid.equals(other.invoiceid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Invoice[ invoiceid=" + invoiceid + " ]";
    }
    
}

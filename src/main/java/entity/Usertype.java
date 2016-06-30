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
@Table(name = "USERTYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usertype.findAll", query = "SELECT u FROM Usertype u"),
    @NamedQuery(name = "Usertype.findByUsertypeid", query = "SELECT u FROM Usertype u WHERE u.usertypeid = :usertypeid"),
    @NamedQuery(name = "Usertype.findByTimestamp", query = "SELECT u FROM Usertype u WHERE u.timestamp = :timestamp"),
    @NamedQuery(name = "Usertype.findByType", query = "SELECT u FROM Usertype u WHERE u.type = :type")})
public class Usertype implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "USERTYPEID")
    private Integer usertypeid;
    @Column(name = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @Size(max = 255)
    @Column(name = "TYPE")
    private String type;
    @JoinColumn(name = "USERID", referencedColumnName = "USERID")
    @ManyToOne
    private Users users;

    public Usertype() {
    }

    public Usertype(Integer usertypeid) {
        this.usertypeid = usertypeid;
    }

    public Integer getUsertypeid() {
        return usertypeid;
    }

    public void setUsertypeid(Integer usertypeid) {
        this.usertypeid = usertypeid;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usertypeid != null ? usertypeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usertype)) {
            return false;
        }
        Usertype other = (Usertype) object;
        if ((this.usertypeid == null && other.usertypeid != null) || (this.usertypeid != null && !this.usertypeid.equals(other.usertypeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Usertype[ usertypeid=" + usertypeid + " ]";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Jobseeker;
import entity.Users;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author binod
 */
@Stateless
public class JobseekerEJB {
    
    @PersistenceContext(unitName = "JobPortalPU")
    private EntityManager em;
    
    public void addJobseekerInfo(Jobseeker jobseeker,Users user){       
       em.persist(user);     
       // it sets the userid of persisted user into the jobseeker. Hence, maintains the referential integrity and transaction 
       jobseeker.setUsers(user);
       em.persist(jobseeker);
    }
    
    public List<Jobseeker> getAllJobseeker(){
        TypedQuery<Jobseeker> query = em.createNamedQuery("Jobseeker.findAll", Jobseeker.class);
        return query.getResultList();
    }
    public void updateJobseeker(Jobseeker jobseeker,int id){
        Jobseeker prevJobseeker = em.find(Jobseeker.class, id);
        prevJobseeker.setFirstname(jobseeker.getFirstname());
    }
    public void deleteJobseeker(Jobseeker jobseeker){
        Jobseeker jobseekerTODelete=em.merge(jobseeker);       
        em.remove(jobseekerTODelete);
        //em.remove(jobseeker);
    }
    
    
}

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
       jobseeker.setJobseekerid(user.getUserid());
       jobseeker.setUsers(user);
       em.persist(jobseeker);
    }
    
    public List<Jobseeker> getAllJobseeker(){
        TypedQuery<Jobseeker> query = em.createNamedQuery("Jobseeker.findAll", Jobseeker.class);
        return query.getResultList();
    }
    
    public Jobseeker getJobseekerById(int id){
        TypedQuery<Jobseeker> query = em.createNamedQuery("Jobseeker.findByJobseekerid", Jobseeker.class).setParameter("id",id);
        return query.getSingleResult();
    }
    public void updateJobseeker(Jobseeker jobseeker,int id){
        Jobseeker prevJobseeker = em.find(Jobseeker.class, id);
        prevJobseeker.setFirstname(jobseeker.getFirstname());
        prevJobseeker.setLastname(jobseeker.getLastname());
        prevJobseeker.setAddress(jobseeker.getAddress());
        prevJobseeker.setPhone(jobseeker.getPhone());
        prevJobseeker.setDob(jobseeker.getDob());
        prevJobseeker.setQualification(jobseeker.getQualification());
        prevJobseeker.setSkills(jobseeker.getSkills());
        
    }
    public void deleteJobseeker(Jobseeker jobseeker){
        Jobseeker jobseekerTODelete=em.merge(jobseeker);       
        em.remove(jobseekerTODelete);
        //em.remove(jobseeker);
    }
    
    
}

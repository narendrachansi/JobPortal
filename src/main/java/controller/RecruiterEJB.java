/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Company;
import entity.Recruiter;
import entity.Users;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author SYSTEM
 */
@Stateless
public class RecruiterEJB {

    @PersistenceContext(unitName = "JobPortalPU")
    private EntityManager em;

    public void addRecruiterInfo(Recruiter recruiter, Users user, Company company) {
        em.persist(user);
        recruiter.setRecruiterid(user.getUserid());
        recruiter.setUsers(user);
        em.persist(company);
        
        recruiter.setCompany(company);
        em.persist(recruiter);
    }
}

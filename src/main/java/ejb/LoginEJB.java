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
 * @author SYSTEM
 */
@Stateless
public class LoginEJB {

    @PersistenceContext(unitName = "JobPortalPU")
    private EntityManager em;

    public Users findUser(String username, String password) {
        TypedQuery<Users> query = em.createNamedQuery("Users.findByLoginCredentials", Users.class).setParameter("email", username).setParameter("password", password);
        List var = query.getResultList();
        if (var.isEmpty()) {
            return null;
        } else {
            return (Users) var.get(0);
        }

    }
}

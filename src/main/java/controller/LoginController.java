/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.LoginEJB;
import entity.Jobseeker;
import entity.Users;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author SYSTEM
 */
@ManagedBean(name = "LoginController", eager = true)
@SessionScoped
public class LoginController {

    public LoginController() {
    }
    @Inject
    private LoginEJB loginEJB;
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String loginAuthentication() {
        Users user = loginEJB.findUser(username, password);

        FacesContext context = FacesContext.getCurrentInstance();

        if (user == null) {
            context.addMessage(null, new FacesMessage("Unknown login, try again"));
            username = null;
            password = null;
            return null;
        } else {
            Map<String, Object> sessionMap = context.getExternalContext().getSessionMap();
            //context.getExternalContext().getSessionMap().put("user", user);
            sessionMap.put("user", user);
        
            if (user.getJobseeker() != null) {
                return "/jobseeker/profile?faces-redirect=true";
            }
            return "/recruiter/profile?faces-redirect=true";
        }
    }
    
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("user");
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
    }
    
  

}

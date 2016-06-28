/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.JobseekerEJB;
import entity.Jobseeker;
import entity.Users;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.SystemException;
import javax.ws.rs.core.Response;

/**
 *
 * @author binod
 */
@ManagedBean(name = "JobseekerController", eager = true)
@RequestScoped
public class JobseekerController {

    @Inject
    private JobseekerEJB jobseekerEJB;
    private Jobseeker jobseeker = new Jobseeker();

    private Users user = new Users();

    public JobseekerController() {
    }

    public Jobseeker getJobseeker() {
        return jobseeker;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public void setJobseeker(Jobseeker jobseeker) {
        this.jobseeker = jobseeker;
    }

    /**
     * *
     * adding a job seeker informations
     */
    public void addJobSeeker(String unit, String street, String suburb, String state, String country) {
        int i;
        jobseeker.setAddress(unit + "," + street + "," + suburb + "," + state + "," + country);
        jobseekerEJB.addJobseekerInfo(jobseeker, user);
    }

    public String showAddress(String address) {
        String addressinfo[] = new String[4];
        for (int i = 0; i <= addressinfo.length; i++) {
            addressinfo = address.split(",");
        }
        return addressinfo[0] + " " + addressinfo[1];

    }

    public void uploadFile() {
    
    
    }

    /**
     * *
     * Editing job seeker informations
     */
    public String editJobseeker(Jobseeker jobseeker) {
        //jobseekerEJB.editJobseeker(jobseeker);
        setJobseeker(jobseeker);
        return "edit.xhtml";
    }

    /**
     * *
     * persist updated jobseeker
     *
     * @return
     */
    public String updateJobseeker() {
        FacesContext fc = FacesContext.getCurrentInstance();
        int id = Integer.parseInt(getIdParam(fc));
        jobseekerEJB.updateJobseeker(jobseeker, id);
        return "list.xhtml";
    }

    /**
     * *
     * Get jobseeker id using <h:param> tag
     *
     * @param fc
     * @return
     */
    public String getIdParam(FacesContext fc) {

        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get("id");

    }

    public List<Jobseeker> getAllJobseeker() {
        return jobseekerEJB.getAllJobseeker();
    }

    public String deleteJobseeker(Jobseeker jobseeker) {
        setJobseeker(jobseeker);
        jobseekerEJB.deleteJobseeker(jobseeker);
        return "list";
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.JobseekerController.getSubmittedFileName;
import ejb.JobseekerEJB;
import entity.Jobseeker;
import entity.Recruiter;
import entity.Company;
import entity.Users;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.Part;

/**
 *
 * @author SYSTEM
 */
@ManagedBean(name = "RecruiterController", eager = true)
@RequestScoped
public class RecruiterController {
     @Inject
    private RecruiterEJB recruiterEJB;
    private Recruiter recruiter = new Recruiter();
    private Users user = new Users();
    private Company company=new Company();
    public RecruiterController(){
    }
    public Recruiter getRecruiter() {
        return recruiter;
    }
    public Users getUser() {
        return user;
    }
     public void setUser(Users user) {
        this.user = user;
    }

    public void setRecruiter(Jobseeker jobseeker) {
        this.recruiter = recruiter;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
    

    Part uploadedLogo;

    public Part getUploadedLogo() {
        return uploadedLogo;
    }

    public void setUploadedLogo(Part uploadedLogo) {
        this.uploadedLogo = uploadedLogo;
    }

    public void validateLogo(FacesContext ctx, UIComponent comp, Object value) {
        List<FacesMessage> msgs = new ArrayList<FacesMessage>();
        Part file = (Part) value;
        if (file != null) {
            if (!"application/msword".equals(file.getContentType())) {
                msgs.add(new FacesMessage("not a word file"));
            }
        }

    }
    //code to get the submitted file name from the file part header. 
    public static String getSubmittedFileName(Part filePart) {
        String header = filePart.getHeader("content-disposition");
        if (header == null) {
            return null;
        }
        for (String headerPart : header.split(";")) {
            if (headerPart.trim().startsWith("filename")) {
                return headerPart.substring(headerPart.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
    public void addRecruiter(String unit, String street, String suburb, String state, String country) throws FileNotFoundException, IOException {

        Part uploadedFile = getUploadedLogo();
        if (uploadedFile != null) {
            String filePath = "e:\\" + user.getEmail() + "\\";
            new File(filePath).mkdir();
            final Path destination = Paths.get(filePath + getSubmittedFileName(uploadedFile));
            //When using servlet 3.1
            //final Path destination = Paths.get("c:/temp/"+ FilenameUtils.getName(uploadedFile.getSubmittedFileName()));
            InputStream bytes = null;
            bytes = uploadedFile.getInputStream();  //

            //Copies bytes to destination.
            Files.copy(bytes, destination);
            company.setLogopath(destination.toString());

        }
        company.setAddress(unit + "," + street + "," + suburb + "," + state + "," + country);
        recruiterEJB.addRecruiterInfo(recruiter, user,company);
    }


    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.RecruiterEJB;
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
import java.util.Map;
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
    private Company company = new Company();

    String unit;
    String street;
    String suburb;
    String state;
    String country;

    public RecruiterController() {
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public void setRecruiter(Recruiter recruiter) {
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
        recruiterEJB.addRecruiterInfo(recruiter, user, company);
    }

    public void registerRecruiter() {
        recruiterEJB.addRecruiterInfo(recruiter, user, company);
    }

    public Recruiter getRecruiterFromSession() {

        FacesContext context = FacesContext.getCurrentInstance();
        //context.getExternalContext().getRequestParameterMap().get("user");
        Map<String, Object> session = context.getExternalContext().getSessionMap();

        Users sessionUser = (Users) session.get("user");

        Recruiter recruiter = sessionUser.getRecruiter();
        return recruiter;
    }

    public String editRecruiter() {
        return "edit.xhtml?faces-redirect=true";
    }

    public void showRecruiter() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Recruiter sessionRecruiter = getRecruiterFromSession();
        splitAddress(sessionRecruiter);
        setRecruiter(sessionRecruiter);
    }

    public String showAddress(String address) {

        int size = address.split(",").length;
        String addressinfo[] = new String[size];
        addressinfo = address.split(",");
        address = "";
        for (String add : addressinfo) {
            address += add + " ";
        }
        return address.trim();

    }

    public void splitAddress(Recruiter recruiter) {
        String address = recruiter.getCompany().getAddress();
        if (address != null) {
            int size = address.split(",").length;
            if (size > 0) {
                String addressinfo[] = new String[size];
                addressinfo = address.split(",");
                setUnit(addressinfo[0]);
                setStreet(addressinfo[1]);
                setSuburb(addressinfo[2]);
                setState(addressinfo[3]);
                setCountry(addressinfo[4]);
            }

        }

    }

    Part uploadedCV;

    public Part getUploadedCV() {
        return uploadedCV;
    }

    public void setUploadedCV(Part uploadedCV) {
        this.uploadedCV = uploadedCV;
    }

    public String updateRecruiter() throws IOException {
        FacesContext fc = FacesContext.getCurrentInstance();
        //int id = Integer.parseInt(getIdParam(fc));
        Recruiter sessionUser = getRecruiterFromSession();
        company.setAddress(unit + "," + street + "," + suburb + "," + state + "," + country);
        Part uploadedFile = getUploadedCV();
        if (uploadedFile != null) {
            String filePath = "e:\\" + sessionUser.getUsers().getEmail() + "\\";
            new File(filePath).mkdir();
            final Path destination = Paths.get(filePath + getSubmittedFileName(uploadedFile));
            //When using servlet 3.1
            //final Path destination = Paths.get("c:/temp/"+ FilenameUtils.getName(uploadedFile.getSubmittedFileName()));
            InputStream bytes = null;
            bytes = uploadedFile.getInputStream();  //

            //Copies bytes to destination.
            Files.copy(bytes, destination);
            recruiter.getCompany().setLogopath(destination.toString());

        }
        //jobseeker.setAddress(unit + "," + street + "," + suburb + "," + state + "," + country);
        //jobseekerEJB.addJobseekerInfo(jobseeker, user);

        recruiterEJB.updateRecruiter(recruiter,company,sessionUser.getRecruiterid());
        return "list.xhtml?faces-redirect=true";
    }

}

package controller;

import com.sun.org.apache.xerces.internal.impl.Constants;
import ejb.JobseekerEJB;
import entity.Jobseeker;
import entity.Users;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import static javax.management.Query.gt;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.transaction.SystemException;
import javax.ws.rs.core.Response;

@ManagedBean(name = "JobseekerController", eager = true)
@RequestScoped
public class JobseekerController {

    @Inject
    private JobseekerEJB jobseekerEJB;
    private Jobseeker jobseeker = new Jobseeker();

    private Users user = new Users();

    String unit;
    String street;
    String suburb;
    String state;
    String country;

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
    Part uploadedCV;

    public Part getUploadedCV() {
        return uploadedCV;
    }

    public void setUploadedCV(Part uploadedCV) {
        this.uploadedCV = uploadedCV;
    }

    public void validateFile(FacesContext ctx, UIComponent comp, Object value) {
        List<FacesMessage> msgs = new ArrayList<FacesMessage>();
        Part file = (Part) value;
        if (file != null) {
            if (!"application/msword".equals(file.getContentType())) {
                msgs.add(new FacesMessage("not a word file"));
            }
        }

    }

    public void addJobSeeker() {
        jobseekerEJB.addJobseekerInfo(jobseeker, user);
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

    /**
     * *
     * Editing job seeker informations
     */
    public String editJobseeker() {
        //jobseekerEJB.editJobseeker(jobseeker);

        return "edit.xhtml?faces-redirect=true";
    }

    public void showJobseeker() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Jobseeker sessionJobseeker = getJobseekerFromSession();
        splitAddress(sessionJobseeker);
        setJobseeker(sessionJobseeker);
    }

    public void splitAddress(Jobseeker jobseeker) {
        String address = jobseeker.getAddress();
        if (address!=null) {
            int size = address.split(",").length;
            String addressinfo[] = new String[size];
            addressinfo = address.split(",");
            setUnit(addressinfo[0]);
            setStreet(addressinfo[1]);
            setSuburb(addressinfo[2]);
            setState(addressinfo[3]);
            setCountry(addressinfo[4]);
        }

    }

    /**
     * *
     * persist updated jobseeker
     *
     * @return
     */
    public String updateJobseeker() throws IOException {
        FacesContext fc = FacesContext.getCurrentInstance();
        //int id = Integer.parseInt(getIdParam(fc));
        Jobseeker sessionJobseeker = getJobseekerFromSession();
        jobseeker.setAddress(unit + "," + street + "," + suburb + "," + state + "," + country);
        Part uploadedFile = getUploadedCV();
        if (uploadedFile != null) {
            String filePath = "e:\\" + sessionJobseeker.getUsers().getEmail() + "\\";
            new File(filePath).mkdir();
            final Path destination = Paths.get(filePath + getSubmittedFileName(uploadedFile));
            //When using servlet 3.1
            //final Path destination = Paths.get("c:/temp/"+ FilenameUtils.getName(uploadedFile.getSubmittedFileName()));
            InputStream bytes = null;
            bytes = uploadedFile.getInputStream();  //

            //Copies bytes to destination.
            Files.copy(bytes, destination);
            jobseeker.setCvpath(destination.toString());

        }
        //jobseeker.setAddress(unit + "," + street + "," + suburb + "," + state + "," + country);
        //jobseekerEJB.addJobseekerInfo(jobseeker, user);

        jobseekerEJB.updateJobseeker(jobseeker, sessionJobseeker.getJobseekerid());
        return "list.xhtml?faces-redirect=true";
    }

    public Jobseeker getJobseekerFromSession() {

        FacesContext context = FacesContext.getCurrentInstance();
        //context.getExternalContext().getRequestParameterMap().get("user");
        Map<String, Object> session = context.getExternalContext().getSessionMap();

        Users sessionUser = (Users) session.get("user");

        Jobseeker jobseeker = sessionUser.getJobseeker();
        return jobseeker;
    }

    public boolean checkUserLogin() {

        FacesContext context = FacesContext.getCurrentInstance();
        //context.getExternalContext().getRequestParameterMap().get("user");
        Map<String, Object> session = context.getExternalContext().getSessionMap();

        Users sessionUser = (Users) session.get("user");
        if (sessionUser != null) {
            return true;
        } else {
            return false;
        }
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

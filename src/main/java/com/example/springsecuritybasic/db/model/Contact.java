package com.example.springsecuritybasic.db.model;

import com.example.springsecuritybasic.resource.springSecurityAPIResource.input.PostContactResource;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "contact_message")
public class Contact {

    @Id
    @Column(name = "id")
    private String contactId;

    @Column(name = "contact_name")
    private String contactName;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "subject")
    private String subject;

    @Column(name = "message")
    private String message;

    @Column(name = "create_dt")
    private Date createDt;

    public Contact() {
    }

    public Contact(PostContactResource postContactResource) {
        this.contactId = postContactResource.getContactId();
        this.contactName = postContactResource.getContactName();
        this.contactEmail = postContactResource.getContactEmail();
        this.subject = postContactResource.getSubject();
        this.message = postContactResource.getMessage();
        this.createDt = new Date(System.currentTimeMillis());
    }

    public String getContactId() {
        return contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "contactId='" + contactId + '\'' +
                ", contactName='" + contactName + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                ", createDt=" + createDt +
                '}';
    }
}

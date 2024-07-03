package com.example.springsecuritybasic.db.model;

import com.example.springsecuritybasic.resource.springSecurityAPIResource.input.PostUserResource;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GenericGenerator(
            name = "customerId",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "customer_id_seq"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1"),
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerId")
    @Column(name = "id")
    private int customerId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "pwd")
    private String pwd;

    @Column(name = "role")
    private String role;

    @Column(name = "create_dt")
    private Date createDt;

    public Customer() {
    }

    public Customer(PostUserResource postUserResource) {
        this.name = postUserResource.getName();
        this.email = postUserResource.getEmail();
        this.mobileNumber = postUserResource.getMobileNumber();
        this.pwd = postUserResource.getPwd();
        this.role = postUserResource.getRole();
        this.createDt = postUserResource.getCreateDt();
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getPwd() {
        return pwd;
    }

    public String getRole() {
        return role;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", pwd='" + pwd + '\'' +
                ", role='" + role + '\'' +
                ", createDt=" + createDt +
                '}';
    }
}

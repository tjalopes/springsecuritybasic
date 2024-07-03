package com.example.springsecuritybasic.resource.springSecurityAPIResource.input;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class PostUserResource {
    @NotEmpty
    private String name;
    @NotEmpty
    private String email;
    @NotEmpty
    private String mobileNumber;
    @NotEmpty
    private String pwd;
    @NotEmpty
    private String role;
    @NotNull
    private Date createDt;

    public PostUserResource() {
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
        return "PostUserResource{" +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", pwd='" + pwd + '\'' +
                ", role='" + role + '\'' +
                ", createDt=" + createDt +
                '}';
    }
}

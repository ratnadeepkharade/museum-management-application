package com.application.museummanagementbackend.model;

import java.util.Date;

public class Employee {
    private int empId;
    private String lastName;
    private String firstName;
    private String emailId;
    private java.util.Date dateOfBirth;
    private String roleName;
    private int sectionId;
    public Employee() {
    }

    public Employee(int empId, String lastName, String firstName, String emailId, Date dateOfBirth, String roleName, int sectionId) {
        this.empId = empId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.emailId = emailId;
        this.dateOfBirth = dateOfBirth;
        this.roleName = roleName;
        this.sectionId = sectionId;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", roleName='" + roleName + '\'' +
                ", sectionId=" + sectionId +
                '}';
    }
}

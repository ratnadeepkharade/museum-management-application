package com.application.museummanagementbackend.model;

public class Visitor {
    private int visitorId;
    private String firstName;
    private String lastName;
    private String gender;
    private int age;
    private String category;
    private int sectionId;

    public Visitor() {
    }

    public Visitor(String firstName, String lastName, String gender, int age, String category, int sectionId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.category = category;
        this.sectionId = sectionId;
    }

    public Visitor(int visitorId, String lastName, String firstName, String gender, int age, String category, int sectionId) {
        this.visitorId = visitorId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.gender = gender;
        this.age = age;
        this.category = category;
        this.sectionId = sectionId;
    }

    public int getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(int visitorId) {
        this.visitorId = visitorId;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    @Override
    public String toString() {
        return "Visitor{" +
                "visitorId=" + visitorId +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", category='" + category + '\'' +
                ", sectionId=" + sectionId +
                '}';
    }
}

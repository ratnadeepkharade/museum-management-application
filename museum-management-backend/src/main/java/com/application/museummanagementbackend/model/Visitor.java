package com.application.museummanagementbackend.model;

public class Visitor {
    private long visitorId;
    private String firstName;
    private String lastName;
    private String gender;
    private int age;
    private String category;
    private long entryDate;
    private long sectionId;
    private String sectionName;

    public Visitor() {
    }

    //for creating visitor
    public Visitor(String firstName, String lastName, String gender, int age, String category, long sectionId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.category = category;
        this.sectionId = sectionId;
    }

    //for updating visitor
    public Visitor(long visitorId, String lastName, String firstName, String gender, int age, String category, long sectionId) {
        this.visitorId = visitorId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.gender = gender;
        this.age = age;
        this.category = category;
        this.sectionId = sectionId;
    }

    //for getting visitor
    public Visitor(long visitorId, String firstName, String lastName, String gender, int age, String category, long entryDate, long sectionId, String sectionName) {
        this.visitorId = visitorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.category = category;
        this.entryDate = entryDate;
        this.sectionId = sectionId;
        this.sectionName = sectionName;
    }

    public long getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(long visitorId) {
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

    public long getSectionId() {
        return sectionId;
    }

    public void setSectionId(long sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public long getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(long entryDate) {
        this.entryDate = entryDate;
    }
}

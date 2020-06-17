package com.application.museummanagementbackend.model;

import java.util.Date;

public class Artifact {
    private int artifactsID;
    private String artifactName;
    private long dateArrived;
    private String artifactType;
    private int sectionid;
    private int empid;
    private int amount;
    private int quantity;
    private String acquiredFrom;
    private String sectionName;
    private String firstName;

    public Artifact() {
    }

    public Artifact(int artifactsID, String artifactName, long dateArrived, String artifactType,int sectionid,int empid,int amount,String acquiredFrom,int quantity) {
        this.artifactsID = artifactsID;
        this.artifactName = artifactName;
        this.dateArrived = dateArrived;
        this.artifactType = artifactType;
        this.sectionid = sectionid;
        this.empid = empid;
        this.amount = amount;
        this.acquiredFrom = acquiredFrom;
        this.quantity = quantity;


    }

    public int getartifactsID() {
        return artifactsID;
    }

    public void setartifactsID(int artifactsID) {
        this.artifactsID = artifactsID;
    }

    public String getartifactName() {
        return artifactName;
    }

    public void setartifactName(String artifactName) {
        this.artifactName = artifactName;
    }

    public long getdateArrived() {
        return dateArrived;
    }

    public void setdateArrived(long dateArrived) {
        this.dateArrived = dateArrived;
    }

    public String getartifactType() {
        return artifactType;
    }

    public void setartifactType(String artifactType) {
        this.artifactType = artifactType;
    }

    public int getsectionid() {
        return sectionid;
    }

    public void setsectionid(int sectionid) {
        this.sectionid = sectionid;
    }

    public int getempid() {
        return empid;
    }

    public void setempid(int empid) {
        this.empid = empid;
    }

    public int getamount() {
        return amount;
    }

    public void setamount(int amount) {
        this.amount = amount;
    }
    public int getquantity() {
        return quantity;
    }

    public void setquantity(int quantity) {
        this.quantity = quantity;
    }

    public String getacquiredFrom() {
        return acquiredFrom;
    }

    public void setacquiredFrom(String acquiredFrom) {
        this.acquiredFrom = acquiredFrom;
    }
    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


}

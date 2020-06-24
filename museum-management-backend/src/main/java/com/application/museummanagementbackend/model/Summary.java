package com.application.museummanagementbackend.model;

public class Summary {
    private int sectionid;
    private String sectionName;
    private int artifactCount;
    private int TotalAmount;
    public Summary() {
    }

    public Summary(int sectionid, String sectionName,int artifactCount,int TotalAmount) {
        this.sectionid = sectionid;
        this.sectionName = sectionName;
        this.artifactCount=artifactCount;
        this.TotalAmount=TotalAmount;
    }



    public int getSectionid() {
        return sectionid;
    }

    public void setSectionid(int sectionid) {
        this.sectionid = sectionid;
    }

    public String getSectionName() {
        return sectionName;
    }
    public void setsectionName(String sectionName) {
        this.sectionName = sectionName;
    }
    public void setartifactCount(int artifactCount) {
        this.artifactCount = artifactCount;
    }
    public int getartifactCount() {
        return artifactCount;
    }

    public int getTotalAmount() {
        return TotalAmount;
    }
    public void setTotalAmount(int TotalAmount) {
        this.TotalAmount = TotalAmount;
    }

}

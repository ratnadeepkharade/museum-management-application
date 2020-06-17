package com.application.museummanagementbackend.model;

public class TopSection {

    private String sectionName;
    private int count;

    public TopSection() {
    }

    public TopSection(String sectionName, int count) {
        this.sectionName = sectionName;
        this.count = count;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

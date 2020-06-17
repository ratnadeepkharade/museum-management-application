package com.application.museummanagementbackend.model;

public class MonthlyCount {

    private int monthId;
    private int count;

    public MonthlyCount() {
    }

    public MonthlyCount(int monthId, int count) {
        this.monthId = monthId;
        this.count = count;
    }

    public int getMonthId() {
        return monthId;
    }

    public void setMonthId(int monthId) {
        this.monthId = monthId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

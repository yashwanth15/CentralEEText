package com.yashwanth.centraleetext;

/**
 * Created by yashw on 05-12-2017.
 */

public class ListItem {
    private String company;
    private String date;
    private String employee;

    public ListItem(String company, String date, String employee) {
        this.company = company;
        this.date = date;
        this.employee = employee;
    }

    public String getCompany() {
        return company;
    }

    public String getDate() {
        return date;
    }

    public String getEmployee() {
        return employee;
    }
}

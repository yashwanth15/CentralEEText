package com.yashwanth.centraleetext;

/**
 * Created by yashw on 05-12-2017.
 */

public class ListItem {
    private String header;
    private String description;

    public ListItem(String header, String description) {
        this.header = header;
        this.description = description;
    }

    public String getHeader() {
        return header;
    }

    public String getDescription() {
        return description;
    }
}

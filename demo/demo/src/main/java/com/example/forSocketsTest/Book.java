package com.example.forSocketsTest;

public class Book {
    private String auhor;
    private String name;

    public String getAuhor() {
        return auhor;
    }

    public void setAuhor(String auhor) {
        this.auhor = auhor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Book(String auhor, String name) {
        this.auhor = auhor;
        this.name = name;
    }
}

package com.example.Shared;

public class StringContent {
    @Override
    public String toString() {
        return json;
    }

    private String json;


    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public StringContent(String random){
        json=random;
    }

}

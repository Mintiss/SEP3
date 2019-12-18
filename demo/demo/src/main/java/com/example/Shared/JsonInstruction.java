package com.example.Shared;

public class JsonInstruction {

    private String json;
    private String instruction;
    private String username;

    public JsonInstruction(String json, String username, String instruction) {
        this.json = json;
        this.instruction = instruction;
        this.username=username;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    @Override
    public String toString() {
        return "JsonInstruction{" +
                "json='" + json + '\'' +
                ", instruction='" + instruction + '\'' +
                '}';
    }
}


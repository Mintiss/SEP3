package com.example.demo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

       private String Author;

    @Override
    public String toString() {
        return "Item{" +
                "Author='" + Author + '\'' +
                '}';
    }

    public Item(String author) {
        Author = author;
    }
}


package com.example.demo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
@JsonProperty("itemId")
        private int itemId;
@JsonProperty("author")
        private String author;
@JsonProperty("title")
        private String title;
@JsonProperty("type")
        private String type;
@JsonProperty("quantity")
        private int quantity;

    public Item(){

    }

    public Item(int itemId, String author, String title, String type, int quantity) {
        this.itemId = itemId;
        this.author = author;
        this.title = title;
        this.type = type;
        this.quantity = quantity;
    }
    @Override
    public String toString() {
        return "Item{" +
                "ItemId=" + itemId +
                ", Author='" + author + '\'' +
                ", Title='" + title + '\'' +
                ", Type='" + type + '\'' +
                ", Quantity=" + quantity +
                '}';
    }

    /*public Item(String string){
        String array[]= string.split(",");

        this.itemId=Integer.parseInt(array[0]);
        this.author= array[1];
        this.title=array[2];
        this.type=array[3];
        this.quantity=Integer.parseInt(array[4]);
    }*/
}


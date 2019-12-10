package com.example.Shared;
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

    public int getItemId() {
        return itemId;
    }

    @Override
    public java.lang.String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", author=" + author +
                ", title=" + title +
                ", type=" + type +
                ", quantity=" + quantity +
                '}';
    }
}


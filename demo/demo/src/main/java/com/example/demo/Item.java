package com.example.demo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

        private int ItemId;
        private String Author;
        private String Title;
        private String Type;
        private int Quantity;

    @Override
    public String toString() {
        return "Item{" +
                "ItemId=" + ItemId +
                ", Author='" + Author + '\'' +
                ", Title='" + Title + '\'' +
                ", Type='" + Type + '\'' +
                ", Quantity=" + Quantity +
                '}';
    }

    public Item(String string){
        String array[]= string.split(",");

        this.ItemId=Integer.parseInt(array[0]);
        this.Author= array[1];
        this.Title=array[2];
        this.Type=array[3];
        this.Quantity=Integer.parseInt(array[4]);
    }

    public Item(int itemId, String author, String title, String type, int quantity) {
        ItemId = itemId;
        Author = author;
        Title = title;
        Type = type;
        Quantity = quantity;
    }

}


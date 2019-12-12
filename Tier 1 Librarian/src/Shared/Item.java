package Shared;

import java.io.Serializable;

public class Item implements Serializable {

    private int itemId;
    private String author;
    private String title;
    private String type;
    private int quantity;
    private String location;

    public Item(int itemId, String author, String title, String type, int quantity, String location) {
        this.itemId = itemId;
        this.author = author;
        this.title = title;
        this.type = type;
        this.quantity = quantity;
        this.location = location;
    }

    public int getItemId() {
        return itemId;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", quantity=" + quantity +
                ", location='" + location + '\'' +
                '}';
    }
}

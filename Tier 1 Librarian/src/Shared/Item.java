package Shared;

import java.io.Serializable;

public class Item implements Serializable {

    private int itemId;
    private String author;
    private String title;
    private String type;
    private int quantity;

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

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}

package ViewModel.EditViewModel;

import Model.IModel;
import Shared.Item;
import View.ViewHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EditViewModel {
    private SimpleStringProperty author;
    private SimpleStringProperty title;
    private SimpleStringProperty type;
    private SimpleStringProperty quantity;

    private IModel model;
    private ViewHandler viewHandler;


    public EditViewModel(IModel model, ViewHandler viewHandler) {
        this.model = model;
        this.viewHandler = viewHandler;
        author = new SimpleStringProperty();
        title = new SimpleStringProperty();
        type = new SimpleStringProperty();
        quantity = new SimpleStringProperty();
    }

    public StringProperty authorProperty(){return author;}
    public StringProperty titleProperty(){return title;}
    public StringProperty typeProperty(){return type;}
    public StringProperty quantityProperty(){return quantity;}

    public String getAuthor() { return model.getStoredItem().getAuthor();}
    public String getTitle() { return model.getStoredItem().getTitle();}
    public int getQuantity() { return model.getStoredItem().getQuantity();}
    public String getMonth() { return model.getStoredItem().getType();}
    public String getLocation() {return model.getStoredItem().getLocation();}

    public void backToMain() {
        viewHandler.openMainView();
    }

    public void editItem(String location) {
        int id = model.getStoredItem().getItemId();
        try {
            model.editItem(new Item(id, author.getValue(), title.getValue(), type.getValue(), Integer.parseInt(quantity.getValue()), location));
        }catch (NumberFormatException e)
        {
            model.error("Quantity must be a number.");
        }
    }

    public void selectLocation() {
        model.error("Please select item location");
    }
}

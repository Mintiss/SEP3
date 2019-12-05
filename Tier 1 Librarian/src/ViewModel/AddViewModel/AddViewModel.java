package ViewModel.AddViewModel;

import Model.IModel;
import Shared.Item;
import View.ViewHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AddViewModel {

    private SimpleStringProperty author;
    private SimpleStringProperty title;
    private SimpleStringProperty type;
    private SimpleStringProperty quantity;

    private IModel model;
    private ViewHandler viewHandler;


    public AddViewModel(IModel model, ViewHandler viewHandler) {
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


    public void backToMain() {
        viewHandler.openMainView();
    }

    public void addItem() {
        try {
            model.addItem(new Item(0,author.getValue(),title.getValue(), type.getValue(), Integer.parseInt(quantity.getValue())));
            model.info("Item has been added.");
        }
        catch (NumberFormatException e)
        {
            model.error("Quantity must be a number.");
        }
    }
}

package ViewModel.MainViewModel;

import Model.IModel;
import Shared.Item;
import View.ViewHandler;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.beans.PropertyChangeEvent;

public class MainViewModel {

    private ListProperty<Item> list;
    private Item storedValue;


    private IModel model;
    private ViewHandler viewHandler;

    public MainViewModel(IModel model, ViewHandler viewHandler) {
        this.model = model;
        this.viewHandler = viewHandler;

        list = new SimpleListProperty<>(FXCollections.observableArrayList());
        ObservableList<Item> oList = FXCollections.observableArrayList(model.getMainTable());
        list.setValue(oList);

        model.addListener("UpdateMainTable",this::updateList);

    }

    private void updateList(PropertyChangeEvent propertyChangeEvent) {
        ObservableList<Item> oList = FXCollections.observableArrayList(model.getMainTable());
        list.setValue(oList);
    }

    public Item getStoredValue() {
        return storedValue;
    }

    public void setStoredValue(Item storedValue) {
        this.storedValue = storedValue;
    }

    public ListProperty<Item> getList() { return list;  }

    public void openEditView() {
        if(getStoredValue()==null){
                model.error("Please select an item");
        }else {
            model.setStoredItem(getStoredValue());
            viewHandler.openEditView();
        }
    }

    public void addItem() {
        viewHandler.openAddView();
    }

    public void deleteItem() {
        if(getStoredValue()==null){
            model.error("Please select an item");
        }else {
            model.setStoredItem(getStoredValue());
            model.deleteItem();
        }
    }

    public void openBorrowedView() {
        model.updateBorrowedTable();
        viewHandler.openBorrowedView();
    }
}

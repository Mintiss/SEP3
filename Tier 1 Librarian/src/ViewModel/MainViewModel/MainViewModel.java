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
    private SimpleStringProperty search;


    private IModel model;
    private ViewHandler viewHandler;

    public MainViewModel(IModel model, ViewHandler viewHandler) {
        this.model = model;
        this.viewHandler = viewHandler;

        list = new SimpleListProperty<>(FXCollections.observableArrayList());
        ObservableList<Item> oList = FXCollections.observableArrayList(model.getMainTable());
        list.setValue(oList);

        model.addListener("UpdateMainTable",this::updateList);
        search = new SimpleStringProperty();
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

    public SimpleStringProperty searchProperty(){return search;}

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

    public void openReservedView() {
        model.updateReservedTable();
        viewHandler.openReservedView();
    }

    public void openUsersView() {
        model.updateUsersTable();
        viewHandler.openUsersView();
    }

    public void openLendItemView() {

        if(getStoredValue()==null){
            model.error("Please select an item");
        }else {
            model.setStoredItem(getStoredValue());
            model.updateUsersTable();
            viewHandler.openLendItemView();

        }
    }

    public void refreshTable() {
        model.updateMainTable();
    }

    public void searchId() {
        model.searchMainId(search.getValue());
    }
    public void searchTitle() {
        model.searchMainTitle(search.getValue());
    }
    public void searchAuthor() {
        model.searchMainAuthor(search.getValue());
    }

    public void noSearchSelected() {
        model.error("Please select a search category");
    }

    public void openFineList() {
        model.updateFinesTable();
        viewHandler.openFinesView();
    }
}

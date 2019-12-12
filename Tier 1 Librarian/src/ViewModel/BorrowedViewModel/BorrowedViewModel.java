package ViewModel.BorrowedViewModel;

import Model.IModel;
import Shared.Borrowed;
import Shared.Item;
import View.ViewHandler;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;

public class BorrowedViewModel {

    private ListProperty<Borrowed> list;
    private Borrowed storedValue;


    private IModel model;
    private ViewHandler viewHandler;

    public BorrowedViewModel(IModel model, ViewHandler viewHandler) {
        this.model = model;
        this.viewHandler = viewHandler;

        list = new SimpleListProperty<>(FXCollections.observableArrayList());
        ObservableList<Borrowed> oList = FXCollections.observableArrayList(model.getBorrowedTable());
        list.setValue(oList);

        model.addListener("UpdateBorrowedTable",this::updateList);

    }

    private void updateList(PropertyChangeEvent propertyChangeEvent) {
        ObservableList<Borrowed> oList = FXCollections.observableArrayList(model.getBorrowedTable());
        list.setValue(oList);
    }

    public Borrowed getStoredValue() {
        return storedValue;
    }

    public void setStoredValue(Borrowed storedValue) {
        this.storedValue = storedValue;
    }

    public ListProperty<Borrowed> getList() { return list;  }

    public void backToMain() {
        viewHandler.openMainView();
    }

    public void returnItem() {
        if(getStoredValue()==null){
            model.error("Please select a Borrowed item");
        }else {
            model.setStoredBorrow(getStoredValue());
            model.returnItem();
        }
    }
}

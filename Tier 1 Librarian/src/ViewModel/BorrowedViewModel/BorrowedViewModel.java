package ViewModel.BorrowedViewModel;

import Model.IModel;
import Shared.Borrowed;
import Shared.Item;
import View.ViewHandler;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;

public class BorrowedViewModel {

    private SimpleStringProperty search;
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

        search = new SimpleStringProperty();

        model.addListener("UpdateBorrowedTable",this::updateList);

    }

    private void updateList(PropertyChangeEvent propertyChangeEvent) {
        ObservableList<Borrowed> oList = FXCollections.observableArrayList(model.getBorrowedTable());
        list.setValue(oList);
    }

    public StringProperty searchProperty(){return search;}

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

    public void refresh() {
        model.updateBorrowedTable();
    }

    public void searchId() {
        model.searchBorrowedId(search.getValue());
    }

    public void noSearchSelected() {
        model.error("Please select a search category");
    }

    public void searchItemId() {
        model.searchBorrowedItemId(search.getValue());
    }

    public void searchUsername() {
        model.searchBorrowedUsername(search.getValue());
    }
}

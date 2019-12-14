package ViewModel.FinesViewModel;

import Model.IModel;
import Shared.Borrowed;
import View.ViewHandler;
import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;

public class FinesViewModel {
    private ListProperty<Borrowed> list;
    private Borrowed storedValue;
    private SimpleStringProperty search;


    private IModel model;
    private ViewHandler viewHandler;

    public FinesViewModel(IModel model, ViewHandler viewHandler) {
        this.model = model;
        this.viewHandler = viewHandler;

        list = new SimpleListProperty<>(FXCollections.observableArrayList());
        ObservableList<Borrowed> oList = FXCollections.observableArrayList(model.getFinesTable());
        list.setValue(oList);

        model.addListener("UpdateFinesTable",this::updateList);
        search = new SimpleStringProperty();
    }

    private void updateList(PropertyChangeEvent propertyChangeEvent) {
        ObservableList<Borrowed> oList = FXCollections.observableArrayList(model.getFinesTable());
        Platform.runLater(()->list.setValue(oList));
    }

    public Borrowed getStoredValue() {
        return storedValue;
    }

    public void setStoredValue(Borrowed storedValue) {
        this.storedValue = storedValue;
    }

    public ListProperty<Borrowed> getList() { return list;  }

    public StringProperty searchProperty(){return search;}

    public void backToMain() {
        viewHandler.openMainView();
    }

    public void payFine() {
        if(getStoredValue()==null)
            model.error("Please select fine");
        else {
            model.setStoredFine(getStoredValue());
            model.payFine(getStoredValue());
        }
    }

    public void refresh() {
        model.updateFinesTable();
    }

    public void searchId() {
        model.searchFinesId(search.getValue());
    }

    public void noSearchSelected() {
        model.error("Please select a search category");
    }

    public void searchItemId() {
        model.searchFinesItemId(search.getValue());
    }

    public void searchUsername() {
        model.searchFinesUsername(search.getValue());
    }
}

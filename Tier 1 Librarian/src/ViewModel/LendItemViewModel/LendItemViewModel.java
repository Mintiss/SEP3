package ViewModel.LendItemViewModel;

import Model.IModel;
import Shared.User;
import View.ViewHandler;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;

public class LendItemViewModel {

    private ListProperty<User> list;
    private User storedValue;
    private SimpleStringProperty months;
    private SimpleStringProperty search;


    private IModel model;
    private ViewHandler viewHandler;

    public LendItemViewModel(IModel model, ViewHandler viewHandler) {
        this.model = model;
        this.viewHandler = viewHandler;

        list = new SimpleListProperty<>(FXCollections.observableArrayList());
        ObservableList<User> oList = FXCollections.observableArrayList(model.getUsersTable());
        list.setValue(oList);

        months= new SimpleStringProperty();
        search = new SimpleStringProperty();

        model.addListener("UpdateUsersTable",this::updateList);

    }

    private void updateList(PropertyChangeEvent propertyChangeEvent) {
        ObservableList<User> oList = FXCollections.observableArrayList(model.getUsersTable());
        list.setValue(oList);
    }

    public StringProperty monthsProperty(){return months;}
    public StringProperty searchProperty(){return search;}

    public User getStoredValue() {
        return storedValue;
    }

    public void setStoredValue(User storedValue) {
        this.storedValue = storedValue;
    }

    public ListProperty<User> getList() { return list;  }

    public void backToMain() {
        viewHandler.openMainView();
    }

    public void confirmBorrow() {
        if(getStoredValue()==null){
            model.error("Please select a User");
        }
        else if(months.getValue()==""||months.getValue().equals("")||months.getValue()==null){
            model.error("Please enter months.");
        }
        else {
            model.setStoredUser(getStoredValue());
            model.confirmBorrow(months.getValue());
        }
    }

    public void searchUser() {
        model.searchLendItem(search.getValue());
    }

    public void refreshList() {
        model.updateUsersTable();
    }
}

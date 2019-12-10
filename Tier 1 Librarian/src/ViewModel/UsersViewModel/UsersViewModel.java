package ViewModel.UsersViewModel;

import Model.IModel;
import Shared.User;
import View.UsersView.UsersView;
import View.ViewHandler;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;

public class UsersViewModel {

    private ListProperty<User> list;
    private User storedValue;

    private IModel model;
    private ViewHandler viewHandler;

    public UsersViewModel(IModel model, ViewHandler viewHandler) {
        this.model = model;
        this.viewHandler = viewHandler;

        list = new SimpleListProperty<>(FXCollections.observableArrayList());
        ObservableList<User> oList = FXCollections.observableArrayList(model.getUsersTable());
        list.setValue(oList);

        model.addListener("UpdateUsersTable",this::updateList);

    }

    private void updateList(PropertyChangeEvent propertyChangeEvent) {
        ObservableList<User> oList = FXCollections.observableArrayList(model.getUsersTable());
        list.setValue(oList);
    }

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

    public void deleteUser() {
        if(getStoredValue()==null){
            model.error("Please select a User");
        }else {
            model.setStoredUser(getStoredValue());
            model.deleteUser();
        }
    }

    public void changePassword() {
        if(getStoredValue()==null){
            model.error("Please select a User");
        }else {
            model.setStoredUser(getStoredValue());
            viewHandler.openChangePasswordView();
        }
    }
}

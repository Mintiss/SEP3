package ViewModel.ChangePassViewModel;

import Model.IModel;
import Shared.User;
import View.ViewHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ChangePassViewModel {
    private SimpleStringProperty password;

    private IModel model;
    private ViewHandler viewHandler;


    public ChangePassViewModel(IModel model, ViewHandler viewHandler) {
        this.model = model;
        this.viewHandler = viewHandler;
        password = new SimpleStringProperty();
    }

    public StringProperty passwordProperty(){return password;}

    public void backToMain() {
        viewHandler.openMainView();
    }

    public void changePassword() {

            model.changePassword(new User(model.getStoredUser().getUsername(), password.getValue(), model.getStoredUser().getType()));
    }
}

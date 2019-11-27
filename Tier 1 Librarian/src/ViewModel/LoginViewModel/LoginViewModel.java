package ViewModel.LoginViewModel;

import Model.IModel;
import Model.Model;
import View.ViewHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginViewModel {

    private SimpleStringProperty username;
    private SimpleStringProperty password;

    private IModel model;
    private ViewHandler viewHandler;

    public LoginViewModel(IModel model, ViewHandler viewHandler) {
        this.model = model;
        this.viewHandler = viewHandler;
        password = new SimpleStringProperty();
        username = new SimpleStringProperty();
    }

    public StringProperty passwordProperty(){return password;}
    public StringProperty usernameProperty(){return username;}


    public void ConfirmPassword() {
        model.logInAction(username.getValue(),password.getValue());
    }
}

package ViewModel.LoginViewModel;

import Model.IModel;
import View.ViewHandler;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;

import java.beans.PropertyChangeEvent;

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

        model.addListener("LogInFailed",this::logInFailed);
        model.addListener("LogInConfirmed",this::logInConfirmed);
    }

    public StringProperty passwordProperty(){return password;}
    public StringProperty usernameProperty(){return username;}


    public void ConfirmPassword() {
        model.logInAction(username.getValue(),password.getValue());

    }

    public void logInFailed(PropertyChangeEvent evt){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid information");
                alert.setContentText("Try different");
                alert.showAndWait();
            }
        });
    }

    public void logInConfirmed(PropertyChangeEvent evt){
        model.updateMainTable();
        Platform.runLater(()->viewHandler.openMainView());
    }


}

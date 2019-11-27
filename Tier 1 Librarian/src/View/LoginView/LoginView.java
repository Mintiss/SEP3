package View.LoginView;

import ViewModel.LoginViewModel.LoginViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class LoginView {

    @FXML
    private TextField UsernameTextField;
    @FXML
    private TextField PasswordTextField;

    private LoginViewModel loginViewModel;

    public void ConfirmPasswordAction(ActionEvent actionEvent) {
        loginViewModel.ConfirmPassword();
    }

    public void init(LoginViewModel loginViewModel){
        this.loginViewModel= loginViewModel;
        loginViewModel.passwordProperty().bindBidirectional(PasswordTextField.textProperty());
        loginViewModel.usernameProperty().bindBidirectional(UsernameTextField.textProperty());

    }

}

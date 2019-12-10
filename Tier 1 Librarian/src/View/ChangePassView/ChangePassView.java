package View.ChangePassView;

import ViewModel.ChangePassViewModel.ChangePassViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ChangePassView {
    @FXML
    private TextField PasswordField;


    private ChangePassViewModel changePassViewModel;

    public void init(ChangePassViewModel changePassViewModel) {
        this.changePassViewModel= changePassViewModel;
        changePassViewModel.passwordProperty().bindBidirectional(PasswordField.textProperty());

    }

    public void BackToMainAction(ActionEvent actionEvent) {
        changePassViewModel.backToMain();
    }

    public void ChangePasswordAction(ActionEvent actionEvent) {
        changePassViewModel.changePassword();
    }
}

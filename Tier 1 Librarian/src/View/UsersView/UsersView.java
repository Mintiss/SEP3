package View.UsersView;

import Shared.User;
import ViewModel.UsersViewModel.UsersViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class UsersView {

    @FXML
    private TextField SearchField;
    @FXML
    private TableView<User> mainTable;
    @FXML
    private TableColumn<String, String> UsernameColumn, TypeColumn;

    private UsersViewModel usersViewModel;

    public void init(UsersViewModel usersViewModel){
        this.usersViewModel= usersViewModel;

        TypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        UsernameColumn.setCellValueFactory(new PropertyValueFactory<>("Username"));


        mainTable.itemsProperty().bind(usersViewModel.getList());
    }

    public void SearchTextField(ActionEvent actionEvent) {
    }

    public void SearchAction(ActionEvent actionEvent) {
    }

    public void BackToMain(ActionEvent actionEvent) {
        usersViewModel.backToMain();
    }

    public void ChangePasswordAction(ActionEvent actionEvent) {
        usersViewModel.setStoredValue(mainTable.getSelectionModel().getSelectedItem());
        usersViewModel.changePassword();

    }

    public void DeleteUserAction(ActionEvent actionEvent) {
        usersViewModel.setStoredValue(mainTable.getSelectionModel().getSelectedItem());
        usersViewModel.deleteUser();

    }
}

package View.LendItemView;

import Shared.User;
import ViewModel.LendItemViewModel.LendItemViewModel;
import ViewModel.UsersViewModel.UsersViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class LendItemView {
    @FXML
    public TextField MonthsTextField;
    @FXML
    private TextField SearchField;
    @FXML
    private TableView<User> mainTable;
    @FXML
    private TableColumn<String, String> UsernameColumn;

    private LendItemViewModel lendItemViewModel;

    public void init(LendItemViewModel lendItemViewModel){
        this.lendItemViewModel= lendItemViewModel;

        UsernameColumn.setCellValueFactory(new PropertyValueFactory<>("Username"));

        lendItemViewModel.monthsProperty().bindBidirectional(MonthsTextField.textProperty());
        lendItemViewModel.searchProperty().bindBidirectional(SearchField.textProperty());


        mainTable.itemsProperty().bind(lendItemViewModel.getList());
    }

    public void SearchTextField(ActionEvent actionEvent) {
    }

    public void SearchAction(ActionEvent actionEvent) {
    }

    public void BackToMain(ActionEvent actionEvent) {
        lendItemViewModel.backToMain();
    }

    public void ConfirmBorrow(ActionEvent actionEvent) {
        lendItemViewModel.setStoredValue(mainTable.getSelectionModel().getSelectedItem());
        lendItemViewModel.confirmBorrow();
    }
}

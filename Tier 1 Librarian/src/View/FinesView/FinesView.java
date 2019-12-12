package View.FinesView;

import Shared.Borrowed;
import ViewModel.BorrowedViewModel.BorrowedViewModel;
import ViewModel.FinesViewModel.FinesViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class FinesView {
    @FXML
    private TextField SearchField;
    @FXML
    private TableView<Borrowed> mainTable;
    @FXML
    private TableColumn<String, String> IdColumn, UsernameColumn;
    @FXML
    private TableColumn<String, String> ItemIdColumn;
    @FXML
    private TableColumn<String,String> BorrowedAtColumn, ReturnByColumn;

    private FinesViewModel finesViewModel;

    public void init(FinesViewModel finesViewModel) {
        this.finesViewModel= finesViewModel;

        IdColumn.setCellValueFactory(new PropertyValueFactory<>("borrowedId"));
        ItemIdColumn.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        UsernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        BorrowedAtColumn.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        ReturnByColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));




        mainTable.itemsProperty().bind(finesViewModel.getList());

    }

    public void SearchAction(ActionEvent actionEvent) {
    }

    public void BackToMain(ActionEvent actionEvent) {
        finesViewModel.backToMain();
    }

    public void PayFineAction(ActionEvent actionEvent) {
        finesViewModel.setStoredValue(mainTable.getSelectionModel().getSelectedItem());
        finesViewModel.payFine();
    }
}

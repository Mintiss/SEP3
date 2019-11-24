package View.MainView;

import Shared.Item;
import ViewModel.LoginViewModel.LoginViewModel;
import ViewModel.MainViewModel.MainViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class MainView {

    private MainViewModel mainViewModel;

    @FXML
    private TextField SearchField;
    @FXML
    private TableView<Item> mainTable;

    public void init(MainViewModel mainViewModel){
        this.mainViewModel= mainViewModel;

    }

    public void LentItemAction(ActionEvent actionEvent) {
    }

    public void AddtemAction(ActionEvent actionEvent) {
    }

    public void SeeInfoAction(ActionEvent actionEvent) {
    }

    public void EditItemAction(ActionEvent actionEvent) {
    }

    public void SearchTextField(ActionEvent actionEvent) {
    }

    public void SearchAction(ActionEvent actionEvent) {
    }

    public void DeleteItemAction(ActionEvent actionEvent) {
    }

    public void OpenReservedItemsList(ActionEvent actionEvent) {
    }

    public void OpenLentItemsList(ActionEvent actionEvent) {
    }

    public void OpenBorrowersList(ActionEvent actionEvent) {
    }

    public void OpenFineList(ActionEvent actionEvent) {
    }
}

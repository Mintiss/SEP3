package View.MainView;

import Shared.Item;
import ViewModel.LoginViewModel.LoginViewModel;
import ViewModel.MainViewModel.MainViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.lang.reflect.Type;


public class MainView {

    @FXML
    private TextField SearchField;
    @FXML
    private TableView<Item> mainTable;
    @FXML
    private TableColumn<String, String> IdColumn, AuthorColumn;
    @FXML
    private TableColumn<String, String> TitleColumn, TypeColumn;
    @FXML
    private TableColumn<String,String> QuantityColumn;

    private Item storedItem;

    private MainViewModel mainViewModel;


    public void init(MainViewModel mainViewModel){
        this.mainViewModel= mainViewModel;

        IdColumn.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        AuthorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        TitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        QuantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        mainTable.itemsProperty().bind(mainViewModel.getList());
    }

    public void LentItemAction(ActionEvent actionEvent) {
    }

    public void AddItemAction(ActionEvent actionEvent) {
        mainViewModel.addItem();
    }


    public void EditItemAction(ActionEvent actionEvent) {

            mainViewModel.setStoredValue(mainTable.getSelectionModel().getSelectedItem());
            mainViewModel.openEditView();


    }

    public void SearchTextField(ActionEvent actionEvent) {
    }

    public void SearchAction(ActionEvent actionEvent) {
    }

    public void DeleteItemAction(ActionEvent actionEvent) {
        mainViewModel.setStoredValue(mainTable.getSelectionModel().getSelectedItem());
        mainViewModel.deleteItem();
    }

    public void OpenReservedItemsList(ActionEvent actionEvent) {
        mainViewModel.openReservedView();
    }

    public void OpenLentItemsList(ActionEvent actionEvent) {
        mainViewModel.openBorrowedView();
    }
    

    public void OpenFineList(ActionEvent actionEvent) {
    }

    public void OpenUserList(ActionEvent actionEvent) {
        mainViewModel.openUsersView();
    }
}

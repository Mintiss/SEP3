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
import java.time.LocalDate;


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
    private TableColumn<String,String> QuantityColumn, LocationColumn;

    @FXML
    private RadioButton IdRadio, AuthorRadio, TitleRadio, AarhusRadio, HorsensRadio, BothRadio;

    private Item storedItem;

    private MainViewModel mainViewModel;


    public void init(MainViewModel mainViewModel){
        this.mainViewModel= mainViewModel;

        IdColumn.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        AuthorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        TitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        QuantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        LocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));


        mainViewModel.searchProperty().bindBidirectional(SearchField.textProperty());


        mainTable.itemsProperty().bind(mainViewModel.getList());
    }

    public void LentItemAction(ActionEvent actionEvent) {
        mainViewModel.setStoredValue(mainTable.getSelectionModel().getSelectedItem());
        mainViewModel.openLendItemView();
    }

    public void AddItemAction(ActionEvent actionEvent) {
        mainViewModel.addItem();
    }


    public void EditItemAction(ActionEvent actionEvent) {

            mainViewModel.setStoredValue(mainTable.getSelectionModel().getSelectedItem());
            mainViewModel.openEditView();
    }

    public void SearchAction(ActionEvent actionEvent) {
        if(IdRadio.isSelected())
            mainViewModel.searchId();
        else if(TitleRadio.isSelected())
            mainViewModel.searchTitle();
        else if (AuthorRadio.isSelected())
            mainViewModel.searchAuthor();
        else
            mainViewModel.noSearchSelected();
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
        mainViewModel.openFineList();
    }

    public void OpenUserList(ActionEvent actionEvent) {
        mainViewModel.openUsersView();
    }

    public void IdRadioAction(ActionEvent actionEvent) {
        AuthorRadio.setSelected(false);
        TitleRadio.setSelected(false);
    }

    public void AuthorRadioAction(ActionEvent actionEvent) {
            TitleRadio.setSelected(false);
            IdRadio.setSelected(false);
    }

    public void TitleRadioAction(ActionEvent actionEvent) {
        IdRadio.setSelected(false);
        AuthorRadio.setSelected(false);
    }

    public void RefreshListAction(ActionEvent actionEvent) {
        mainViewModel.refreshTable();

    }

    public void BothRadioAction(ActionEvent actionEvent) {
        BothRadio.setSelected(true);
        AarhusRadio.setSelected(false);
        HorsensRadio.setSelected(false);
    }

    public void AarhusRadioAction(ActionEvent actionEvent) {
        AarhusRadio.setSelected(true);
        BothRadio.setSelected(false);
        HorsensRadio.setSelected(false);
    }

    public void HorsensRadioAction(ActionEvent actionEvent) {
        HorsensRadio.setSelected(true);
        AarhusRadio.setSelected(false);
        BothRadio.setSelected(false);
    }

    public void SelectLibraryAction(ActionEvent actionEvent) {
    }
}

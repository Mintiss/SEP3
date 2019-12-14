package View.BorrowedView;

import Shared.Borrowed;
import ViewModel.BorrowedViewModel.BorrowedViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class BorrowedView {
    @FXML
    private TextField SearchField;
    @FXML
    private TableView<Borrowed> mainTable;
    @FXML
    private TableColumn<String, String> IdColumn, UsernameColumn;
    @FXML
    private TableColumn<String, Boolean> ItemIdColumn, IsReturnedColumn;
    @FXML
    private TableColumn<String,String> BorrowedAtColumn, ReturnByColumn;

    @FXML
    private RadioButton IdRadio, UsernameRadio, ItemIdRadio;

    private BorrowedViewModel borrowedViewModel;

    public void init(BorrowedViewModel borrowedViewModel) {
        this.borrowedViewModel= borrowedViewModel;

        IdColumn.setCellValueFactory(new PropertyValueFactory<>("borrowedId"));
        ItemIdColumn.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        UsernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        BorrowedAtColumn.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        ReturnByColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        IsReturnedColumn.setCellValueFactory(new PropertyValueFactory<>("isReturned"));


        borrowedViewModel.searchProperty().bindBidirectional(SearchField.textProperty());

        mainTable.itemsProperty().bind(borrowedViewModel.getList());
    }

    public void SearchAction(ActionEvent actionEvent) {
        if(IdRadio.isSelected())
            borrowedViewModel.searchId();
        else if(UsernameRadio.isSelected())
            borrowedViewModel.searchUsername();
        else if (ItemIdRadio.isSelected())
            borrowedViewModel.searchItemId();
        else
            borrowedViewModel.noSearchSelected();
    }

    public void BackToMain(ActionEvent actionEvent) {
        borrowedViewModel.backToMain();
    }

    public void ReturnItemAction(ActionEvent actionEvent) {
        borrowedViewModel.setStoredValue(mainTable.getSelectionModel().getSelectedItem());
        borrowedViewModel.returnItem();
    }
    public void IdRadioAction(ActionEvent actionEvent) {
        IdRadio.setSelected(true);
        UsernameRadio.setSelected(false);
        ItemIdRadio.setSelected(false);
    }

    public void UsernameRadioAction(ActionEvent actionEvent) {
        IdRadio.setSelected(false);
        UsernameRadio.setSelected(true);
        ItemIdRadio.setSelected(false);
    }

    public void ItemIdRadioAction(ActionEvent actionEvent) {
        IdRadio.setSelected(false);
        UsernameRadio.setSelected(false);
        ItemIdRadio.setSelected(true);
    }

    public void RefreshAction(ActionEvent actionEvent) {
        borrowedViewModel.refresh();
    }
}

package View.ReservedView;

import Shared.Reservation;
import Shared.User;
import ViewModel.ReservedViewModel.ReservedViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ReservedView {

    @FXML
    private TextField SearchField;
    @FXML
    private TextField MonthsField;
    @FXML
    private TableView<Reservation> mainTable;
    @FXML
    private TableColumn<String, String> IdColumn, UsernameColumn;
    @FXML
    private TableColumn<String, String> ItemIdColumn;
    @FXML
    private TableColumn<String,String> ReservedAtColumn, ExpiresAtColumn;

    @FXML
    private RadioButton IdRadio, UsernameRadio, ItemIdRadio;

    private ReservedViewModel reservedViewModel;

    public void init(ReservedViewModel reservedViewModel){
        this.reservedViewModel= reservedViewModel;

        IdColumn.setCellValueFactory(new PropertyValueFactory<>("reservationId"));
        UsernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        ItemIdColumn.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        ReservedAtColumn.setCellValueFactory(new PropertyValueFactory<>("reservedAt"));
        ExpiresAtColumn.setCellValueFactory(new PropertyValueFactory<>("reservationExpirationDate"));

        reservedViewModel.monthsProperty().bindBidirectional(MonthsField.textProperty());
        reservedViewModel.searchProperty().bindBidirectional(SearchField.textProperty());


        mainTable.itemsProperty().bind(reservedViewModel.getList());
    }


    public void SearchAction(ActionEvent actionEvent) {
        if(IdRadio.isSelected())
            reservedViewModel.searchId();
        else if(UsernameRadio.isSelected())
            reservedViewModel.searchUsername();
        else if (ItemIdRadio.isSelected())
            reservedViewModel.searchItemId();
        else
            reservedViewModel.noSearchSelected();
    }

    public void BackToMain(ActionEvent actionEvent) {
        reservedViewModel.backToMain();
    }


    public void MoveToBorrowedAction(ActionEvent actionEvent) {
        reservedViewModel.setStoredValue(mainTable.getSelectionModel().getSelectedItem());
        reservedViewModel.moveToBorrowed();
    }

    public void DeleteReservation(ActionEvent actionEvent) {
        reservedViewModel.setStoredValue(mainTable.getSelectionModel().getSelectedItem());
        reservedViewModel.deleteReservation();
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
        reservedViewModel.refresh();
    }
}

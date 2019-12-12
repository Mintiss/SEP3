package View.ReservedView;

import Shared.Reservation;
import ViewModel.ReservedViewModel.ReservedViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    private ReservedViewModel reservedViewModel;

    public void init(ReservedViewModel reservedViewModel){
        this.reservedViewModel= reservedViewModel;

        IdColumn.setCellValueFactory(new PropertyValueFactory<>("reservationId"));
        UsernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        ItemIdColumn.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        ReservedAtColumn.setCellValueFactory(new PropertyValueFactory<>("reservedAt"));
        ExpiresAtColumn.setCellValueFactory(new PropertyValueFactory<>("reservationExpirationDate"));

        reservedViewModel.monthsProperty().bindBidirectional(MonthsField.textProperty());

        mainTable.itemsProperty().bind(reservedViewModel.getList());
    }


    public void SearchAction(ActionEvent actionEvent) {
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
}

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
    private TableView<Reservation> mainTable;
    @FXML
    private TableColumn<String, String> IdColumn, UsernameColumn;
    @FXML
    private TableColumn<String, String> ItemIdColumn, ItemTitleColumn;
    @FXML
    private TableColumn<String,String> ReservedAtColumn, ExpiresAtColumn;

    private ReservedViewModel reservedViewModel;

    public void init(ReservedViewModel reservedViewModel){
        this.reservedViewModel= reservedViewModel;

        IdColumn.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        UsernameColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        ItemIdColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        ItemTitleColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        ReservedAtColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        ExpiresAtColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));


        mainTable.itemsProperty().bind(reservedViewModel.getList());
    }

    public void SearchTextField(ActionEvent actionEvent) {
    }

    public void SearchAction(ActionEvent actionEvent) {
    }

    public void BackToMain(ActionEvent actionEvent) {
        reservedViewModel.backToMain();
    }

    public void EditInfo(ActionEvent actionEvent) {
    }
}

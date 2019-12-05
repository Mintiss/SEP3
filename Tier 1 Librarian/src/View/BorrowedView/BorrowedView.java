package View.BorrowedView;

import Shared.Borrowed;
import Shared.Item;
import ViewModel.BorrowedViewModel.BorrowedViewModel;
import ViewModel.EditViewModel.EditViewModel;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class BorrowedView {
    @FXML
    private TextField SearchField;
    @FXML
    private TableView<Borrowed> mainTable;
    @FXML
    private TableColumn<String, String> IdColumn, UsernameColumn;
    @FXML
    private TableColumn<String, String> ItemIdColumn, ItemTitleColumn;
    @FXML
    private TableColumn<String,String> BorrowedAtColumn, ReturnByColumn;

    private BorrowedViewModel borrowedViewModel;

    public void init(BorrowedViewModel borrowedViewModel) {
        this.borrowedViewModel= borrowedViewModel;

        IdColumn.setCellValueFactory(new PropertyValueFactory<>("borrowedId"));
        ItemIdColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        ItemTitleColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        BorrowedAtColumn.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        BorrowedAtColumn.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        ReturnByColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

        ItemTitleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Borrowed, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Borrowed, String> c) {
                return new SimpleStringProperty(c.getValue().getItem().getTitle().);
            }
        });

        BorrowedAtColumn.setCellValueFactory(
                Borrowed -> {
                    SimpleObjectProperty property = new SimpleObjectProperty();
                    property.setValue(Borrowed.getValue().);
                    return property;
                }
        );


        mainTable.itemsProperty().bind(borrowedViewModel.getList());

    }

    public void SearchTextField(ActionEvent actionEvent) {
    }

    public void SearchAction(ActionEvent actionEvent) {
    }

    public void BackToMain(ActionEvent actionEvent) {
        borrowedViewModel.backToMain();
    }

    public void EditInfo(ActionEvent actionEvent) {
    }
}

package View.AddView;

import ViewModel.AddViewModel.AddViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class AddView {
    @FXML
    private TextField AuthorField;
    @FXML
    private TextField TitleField;
    @FXML
    private TextField TypeField;
    @FXML
    private TextField QuantityField;

    private AddViewModel addViewModel;

    public void init(AddViewModel editViewModel) {
        this.addViewModel= editViewModel;
        addViewModel.authorProperty().bindBidirectional(AuthorField.textProperty());
        addViewModel.titleProperty().bindBidirectional(TitleField.textProperty());
        addViewModel.typeProperty().bindBidirectional(TypeField.textProperty());
        addViewModel.quantityProperty().bindBidirectional(QuantityField.textProperty());
    }

    public void BackToMainAction(ActionEvent actionEvent) {
        addViewModel.backToMain();
    }

    public void AddItemAction(ActionEvent actionEvent) {
        addViewModel.addItem();
    }
}

package View.EditView;

import ViewModel.EditViewModel.EditViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class EditView {
    @FXML
    private TextField AuthorField;
    @FXML
    private TextField TitleField;
    @FXML
    private TextField QuantityField;
    @FXML
    private TextField TypeField;

    private EditViewModel editViewModel;

    public void EditItemAction(ActionEvent actionEvent) {
        editViewModel.editItem();
    }

    public void BackToMainAction(ActionEvent actionEvent) {
        editViewModel.backToMain();
    }

    public void init(EditViewModel editViewModel) {
        this.editViewModel= editViewModel;
        editViewModel.authorProperty().bindBidirectional(AuthorField.textProperty());
        editViewModel.titleProperty().bindBidirectional(TitleField.textProperty());
        editViewModel.typeProperty().bindBidirectional(TypeField.textProperty());
        editViewModel.quantityProperty().bindBidirectional(QuantityField.textProperty());

        AuthorField.setText(editViewModel.getAuthor());
        TitleField.setText(editViewModel.getTitle());
        QuantityField.setText(Integer.toString(editViewModel.getQuantity()));
        TypeField.setText(editViewModel.getMonth());

    }

}

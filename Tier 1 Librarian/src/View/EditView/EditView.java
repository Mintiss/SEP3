package View.EditView;

import ViewModel.EditViewModel.EditViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
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

    @FXML
    private RadioButton AarhusRadio, HorsensRadio;

    private EditViewModel editViewModel;

    public void EditItemAction(ActionEvent actionEvent) {
        if(AarhusRadio.isSelected())
            editViewModel.editItem("Aarhus");
        else if(HorsensRadio.isSelected())
            editViewModel.editItem("Horsens");
        else
            editViewModel.selectLocation();
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

        try {
            if (editViewModel.getLocation().equals("Horsens"))
                HorsensRadio.setSelected(true);
            if (editViewModel.getLocation().equals("Aarhus"))
                AarhusRadio.setSelected(true);
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
    }

    public void HorsensRadioAction(ActionEvent actionEvent) {
        HorsensRadio.setSelected(true);
        AarhusRadio.setSelected(false);
    }

    public void AarhusRadioAction(ActionEvent actionEvent) {
        HorsensRadio.setSelected(false);
        AarhusRadio.setSelected(true);
    }

}

package ViewModel.MainViewModel;

import Model.IModel;
import View.ViewHandler;
import javafx.beans.property.SimpleStringProperty;

public class MainViewModel {

    private IModel model;
    private ViewHandler viewHandler;

    public MainViewModel(IModel model, ViewHandler viewHandler) {
        this.model = model;
        this.viewHandler = viewHandler;
    }
}

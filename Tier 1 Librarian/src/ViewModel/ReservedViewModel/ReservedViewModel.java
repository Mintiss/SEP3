package ViewModel.ReservedViewModel;

import Model.IModel;
import Shared.Reservation;
import View.ViewHandler;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;

public class ReservedViewModel {

    private ListProperty<Reservation> list;
    private Reservation storedValue;


    private IModel model;
    private ViewHandler viewHandler;

    public ReservedViewModel(IModel model, ViewHandler viewHandler) {
        this.model = model;
        this.viewHandler = viewHandler;

        list = new SimpleListProperty<>(FXCollections.observableArrayList());
        ObservableList<Reservation> oList = FXCollections.observableArrayList(model.getReservedTable());
        list.setValue(oList);

        model.addListener("UpdateReservationTable",this::updateList);

    }

    private void updateList(PropertyChangeEvent propertyChangeEvent) {
        ObservableList<Reservation> oList = FXCollections.observableArrayList(model.getReservedTable());
        list.setValue(oList);
    }

    public Reservation getStoredValue() {
        return storedValue;
    }

    public void setStoredValue(Reservation storedValue) {
        this.storedValue = storedValue;
    }

    public ListProperty<Reservation> getList() { return list;  }

    public void backToMain() {
        viewHandler.openMainView();
    }
}

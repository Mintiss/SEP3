package ViewModel.ReservedViewModel;

import Model.IModel;
import Shared.Reservation;
import View.ViewHandler;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;

public class ReservedViewModel {

    private SimpleStringProperty months;
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

        months = new SimpleStringProperty();

        model.addListener("UpdateReservationTable",this::updateList);

    }

    private void updateList(PropertyChangeEvent propertyChangeEvent) {
        ObservableList<Reservation> oList = FXCollections.observableArrayList(model.getReservedTable());
        list.setValue(oList);
    }

    public Reservation getStoredValue() {
        return storedValue;
    }

    public StringProperty monthsProperty(){return months;}

    public void setStoredValue(Reservation storedValue) {
        this.storedValue = storedValue;
    }

    public ListProperty<Reservation> getList() { return list;  }

    public void backToMain() {
        viewHandler.openMainView();
    }

    public void deleteReservation() {
        if(getStoredValue()==null)
            model.error("Please select reservation");
        else {
            model.setStoredReservation(getStoredValue());
            model.deleteReservation();
        }

    }

    public void moveToBorrowed() {
        if(getStoredValue()==null)
            model.error("Please select reservation");
        else {
            model.setStoredReservation(getStoredValue());
            model.moveToBorrowed(months.getValue());
        }
    }
}

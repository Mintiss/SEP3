package Model;

import networkingC.Client;

import java.beans.PropertyChangeListener;

public interface IModel {
    void setClient(Client client);
    void logInAction(String user,String password);
    void logInConfirmed();
    void logInFailed();
    void addListener(String eventName, PropertyChangeListener listener);
}

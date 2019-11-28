package Model;

import Shared.User;
import networkingC.Client;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Model implements IModel {

    private Client client;
    private PropertyChangeSupport support=new PropertyChangeSupport(this);



    public Model() {
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        if(eventName == null || "".equals(eventName)) {
            support.addPropertyChangeListener(listener);
        } else {
            support.addPropertyChangeListener(eventName, listener);
        }
    }

    public void setClient(Client client){
        this.client=client;
    }

    public void logInAction(String username,String password){
        client.sendLoginInfo(new User(username,password));
    }


    public void logInConfirmed() {
        support.firePropertyChange("LogInConfirmed",null,null);
    }


    public void logInFailed() {
        support.firePropertyChange("LogInFailed",null,null);
    }

}

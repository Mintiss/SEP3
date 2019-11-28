package Model;

import Shared.User;
import com.google.gson.Gson;
import networkingC.Client;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Model implements IModel {

    private Client client;
    private PropertyChangeSupport support=new PropertyChangeSupport(this);
    private Gson gson = new Gson();
    private String json;



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

        client.sendLoginInfo(json =gson.toJson(new User(username,password)));
    }


    public void logInConfirmed() {
        support.firePropertyChange("LogInConfirmed",null,null);
    }


    public void logInFailed() {
        support.firePropertyChange("LogInFailed",null,null);
    }

}

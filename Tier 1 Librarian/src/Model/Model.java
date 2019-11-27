package Model;

import Shared.User;
import networkingC.Client;

public class Model implements IModel {

    private Client client;

    public Model() {
    }

    public void setClient(Client client){
        this.client=client;
    }

    public void logInAction(String username,String passowrd){
        client.sendLoginInfo(new User(username,passowrd));
    }

}

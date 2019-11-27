package Model;

import networkingC.Client;

public interface IModel {
    void setClient(Client client);
    void logInAction(String user,String password);
}

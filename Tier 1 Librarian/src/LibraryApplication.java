import Model.IModel;
import View.ViewHandler;
import javafx.application.Application;
import javafx.stage.Stage;
import Model.Model;
import networkingC.Client;
import networkingC.SocketClient;

public class LibraryApplication extends Application {

    public void start (Stage stage) throws  Exception{
        IModel model = new Model();
        Client client=new SocketClient(model);
        ViewHandler viewHandler = new ViewHandler(stage, model);
        viewHandler.start();
    }


}

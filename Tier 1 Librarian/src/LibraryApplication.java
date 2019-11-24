import Model.IModel;
import View.ViewHandler;
import javafx.application.Application;
import javafx.stage.Stage;
import Model.Model;

public class LibraryApplication extends Application {

    public void start (Stage stage) throws  Exception{
        IModel model = new Model();
        ViewHandler viewHandler = new ViewHandler(stage, model);
        viewHandler.start();
    }


}

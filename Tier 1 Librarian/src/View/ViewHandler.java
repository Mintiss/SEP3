package View;

import Model.IModel;
import View.LoginView.LoginView;
import View.MainView.MainView;
import ViewModel.ViewModelProvider;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler {

    private ViewModelProvider viewModelProvider;
    private Stage mainStage;

    public ViewHandler(Stage stage, IModel model) {
        viewModelProvider = new ViewModelProvider(model, ViewHandler.this);
        mainStage = stage;
    }

    public void start() {
        openLoginView();
    }

    public void openLoginView() {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("LoginView/LoginView.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        LoginView view = loader.getController();
        view.init(viewModelProvider.getLoginViewModel());
        mainStage.setTitle("Login Screen");

        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();

    }


    public void openMainView() {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("MainView/MainView.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        MainView view = loader.getController();
        view.init(viewModelProvider.getMainViewModel());
        mainStage.setTitle("Main Screen");

        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();

    }
}

package View;

import Model.IModel;
import View.AddView.AddView;
import View.BorrowedView.BorrowedView;
import View.EditView.EditView;
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
    public void openEditView() {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("EditView/EditView.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        EditView view = loader.getController();
        view.init(viewModelProvider.getEditViewModel());
        mainStage.setTitle("Edit Screen");

        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }
    public void openAddView() {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("AddView/AddView.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        AddView view = loader.getController();
        view.init(viewModelProvider.getAddViewModel());
        mainStage.setTitle("Add Screen");

        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }
    public void openBorrowedView() {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("BorrowedView/BorrowedView.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BorrowedView view = loader.getController();
        view.init(viewModelProvider.getBorrowedViewModel());
        mainStage.setTitle("Borrowed Items Screen");

        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }
}

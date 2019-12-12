package View;

import Model.IModel;
import View.AddView.AddView;
import View.BorrowedView.BorrowedView;
import View.ChangePassView.ChangePassView;
import View.EditView.EditView;
import View.FinesView.FinesView;
import View.LendItemView.LendItemView;
import View.LoginView.LoginView;
import View.MainView.MainView;
import View.ReservedView.ReservedView;
import View.UsersView.UsersView;
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
    public void openReservedView() {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("ReservedView/ReservedView.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ReservedView view = loader.getController();
        view.init(viewModelProvider.getReservedViewModel());
        mainStage.setTitle("Reserved Items Screen");

        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }
    public void openUsersView() {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("UsersView/UsersView.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        UsersView view = loader.getController();
        view.init(viewModelProvider.getUsersViewModel());
        mainStage.setTitle("Users Screen");

        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }
    public void openChangePasswordView() {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("ChangePassView/ChangePassView.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ChangePassView view = loader.getController();
        view.init(viewModelProvider.getChangePassViewModel());
        mainStage.setTitle("Change Password Screen");

        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }
    public void openLendItemView() {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("LendItemView/LendItemView.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        LendItemView view = loader.getController();
        view.init(viewModelProvider.getLendItemViewModel());
        mainStage.setTitle("Lend Item Screen");

        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }
    public void openFinesView() {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("FinesView/FinesView.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FinesView view = loader.getController();
        view.init(viewModelProvider.getFinesViewModel());
        mainStage.setTitle("Fines Screen");

        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }
}

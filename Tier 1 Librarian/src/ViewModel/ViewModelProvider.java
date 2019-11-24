package ViewModel;

import Model.IModel;
import View.ViewHandler;
import ViewModel.LoginViewModel.LoginViewModel;
import ViewModel.MainViewModel.MainViewModel;
import com.sun.tools.javac.Main;

public class ViewModelProvider {

    private LoginViewModel loginViewModel;
    private MainViewModel mainViewModel;

    public ViewModelProvider(IModel model, ViewHandler viewHandler) {

        loginViewModel = new LoginViewModel(model, viewHandler);
        mainViewModel = new MainViewModel(model, viewHandler);

    }

    public LoginViewModel getLoginViewModel() {return loginViewModel; }

    public MainViewModel getMainViewModel() { return  mainViewModel;}
}

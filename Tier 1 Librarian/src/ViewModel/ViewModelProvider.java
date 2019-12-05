package ViewModel;

import Model.IModel;
import View.EditView.EditView;
import View.ViewHandler;
import ViewModel.AddViewModel.AddViewModel;
import ViewModel.BorrowedViewModel.BorrowedViewModel;
import ViewModel.EditViewModel.EditViewModel;
import ViewModel.LoginViewModel.LoginViewModel;
import ViewModel.MainViewModel.MainViewModel;
import com.sun.tools.javac.Main;

public class ViewModelProvider {

    private LoginViewModel loginViewModel;
    private MainViewModel mainViewModel;
    private EditViewModel editViewModel;
    private AddViewModel addViewModel;
    private BorrowedViewModel borrowedViewModel;

    public ViewModelProvider(IModel model, ViewHandler viewHandler) {
        loginViewModel = new LoginViewModel(model, viewHandler);
        mainViewModel = new MainViewModel(model, viewHandler);
        editViewModel = new EditViewModel(model, viewHandler);
        addViewModel = new AddViewModel(model, viewHandler);
        borrowedViewModel = new BorrowedViewModel(model, viewHandler);

    }

    public  BorrowedViewModel getBorrowedViewModel() {return  borrowedViewModel;}
    public AddViewModel getAddViewModel() { return  addViewModel;}
    public LoginViewModel getLoginViewModel() {return loginViewModel; }
    public MainViewModel getMainViewModel() { return  mainViewModel;}
    public EditViewModel getEditViewModel() {return  editViewModel;}
}

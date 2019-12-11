package ViewModel;

import Model.IModel;
import View.ViewHandler;
import ViewModel.AddViewModel.AddViewModel;
import ViewModel.BorrowedViewModel.BorrowedViewModel;
import ViewModel.ChangePassViewModel.ChangePassViewModel;
import ViewModel.EditViewModel.EditViewModel;
import ViewModel.FinesViewModel.FinesViewModel;
import ViewModel.LendItemViewModel.LendItemViewModel;
import ViewModel.LoginViewModel.LoginViewModel;
import ViewModel.MainViewModel.MainViewModel;
import ViewModel.ReservedViewModel.ReservedViewModel;
import ViewModel.UsersViewModel.UsersViewModel;


public class ViewModelProvider {

    private LoginViewModel loginViewModel;
    private MainViewModel mainViewModel;
    private EditViewModel editViewModel;
    private AddViewModel addViewModel;
    private BorrowedViewModel borrowedViewModel;
    private ReservedViewModel reservedViewModel;
    private UsersViewModel usersViewModel;
    private ChangePassViewModel changePassViewModel;
    private LendItemViewModel lendItemViewModel;
    private FinesViewModel finesViewModel;

    public ViewModelProvider(IModel model, ViewHandler viewHandler) {
        loginViewModel = new LoginViewModel(model, viewHandler);
        mainViewModel = new MainViewModel(model, viewHandler);
        editViewModel = new EditViewModel(model, viewHandler);
        addViewModel = new AddViewModel(model, viewHandler);
        borrowedViewModel = new BorrowedViewModel(model, viewHandler);
        reservedViewModel = new ReservedViewModel(model, viewHandler);
        usersViewModel = new UsersViewModel(model, viewHandler);
        changePassViewModel = new ChangePassViewModel(model, viewHandler);
        lendItemViewModel = new LendItemViewModel(model,viewHandler);
        finesViewModel = new FinesViewModel(model,viewHandler);

    }

    public FinesViewModel getFinesViewModel() { return finesViewModel; }
    public ChangePassViewModel getChangePassViewModel() {return changePassViewModel;}
    public  BorrowedViewModel getBorrowedViewModel() {return  borrowedViewModel;}
    public AddViewModel getAddViewModel() { return  addViewModel;}
    public LoginViewModel getLoginViewModel() {return loginViewModel; }
    public MainViewModel getMainViewModel() { return  mainViewModel;}
    public EditViewModel getEditViewModel() {return  editViewModel;}
    public ReservedViewModel getReservedViewModel() {return  reservedViewModel;}
    public UsersViewModel getUsersViewModel() {return usersViewModel;}
    public LendItemViewModel getLendItemViewModel() {return lendItemViewModel;}
}

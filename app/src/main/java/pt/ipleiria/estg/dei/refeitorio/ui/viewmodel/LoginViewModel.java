package pt.ipleiria.estg.dei.refeitorio.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import pt.ipleiria.estg.dei.refeitorio.data.repository.UserRepository;

public class LoginViewModel extends ViewModel {
    private final UserRepository userRepository;

    public MutableLiveData<String> login = new MutableLiveData<>("");
    public MutableLiveData<String> password = new MutableLiveData<>("");


    private MutableLiveData<Boolean> doSuccess = new MutableLiveData<>(false);

    private MutableLiveData<String> errors = new MutableLiveData<>();

    public LiveData<String> getErrors(){
        return errors;
    }

    public LiveData<Boolean> getDoSuccess(){
        return doSuccess;
    }


    public LoginViewModel(){
        this.userRepository = new UserRepository();
    }

    public void doLogin(){
        userRepository.login(login.getValue(), password.getValue(), response -> {
            doSuccess.postValue(true);
        }, (error, status) -> {
            errors.postValue(error);
        });
    }
}

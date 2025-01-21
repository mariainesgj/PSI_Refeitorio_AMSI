package pt.ipleiria.estg.dei.refeitorio.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Objects;

import pt.ipleiria.estg.dei.refeitorio.data.repository.UserRepository;

public class RegisterViewModel extends ViewModel {
    private boolean isRequestInProgress = false;

    private final UserRepository userRepository;

    public MutableLiveData<String> username = new MutableLiveData<>("");
    public MutableLiveData<String> email = new MutableLiveData<>("");
    public MutableLiveData<String> password = new MutableLiveData<>("");
    public MutableLiveData<String> nameUser = new MutableLiveData<>("");
    public MutableLiveData<String> phoneNumber = new MutableLiveData<>("");
    public MutableLiveData<String> address = new MutableLiveData<>("");
    public MutableLiveData<String> locale = new MutableLiveData<>("");
    public MutableLiveData<String> postalCode = new MutableLiveData<>("");
    public MutableLiveData<String> role = new MutableLiveData<>("professor");
    public MutableLiveData<Integer> cozinha = new MutableLiveData<>(1);
    private MutableLiveData<Boolean> doSuccess = new MutableLiveData<>(false);
    private MutableLiveData<String> errors = new MutableLiveData<>();
    public LiveData<String> getErrors(){
        return errors;
    }
    public LiveData<Boolean> getDoSuccess(){
        return doSuccess;
    }


    public RegisterViewModel(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void doRegister() {
        if (isRequestInProgress) {
            return;
        }

        isRequestInProgress = true;

        Integer cozinhaValue = cozinha.getValue();
        if (cozinhaValue == null) {
            errors.postValue("Cozinha must not be null.");
            return;
        }

        userRepository.register(
                username.getValue(),
                email.getValue(),
                password.getValue(),
                nameUser.getValue(),
                phoneNumber.getValue(),
                address.getValue(),
                locale.getValue(),
                postalCode.getValue(),
                role.getValue(),
                cozinhaValue,
                response -> {
                    isRequestInProgress = false;
                    doSuccess.postValue(true);
                },
                error -> {
                    isRequestInProgress = false;
                    errors.postValue(error);
                }
        );

    }

}

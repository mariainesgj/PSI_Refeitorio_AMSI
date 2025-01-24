package pt.ipleiria.estg.dei.refeitorio.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Objects;

import pt.ipleiria.estg.dei.refeitorio.data.models.Cozinha;
import pt.ipleiria.estg.dei.refeitorio.data.repository.UserRepository;

public class RegisterViewModel extends ViewModel {
    private boolean isRequestInProgress = false;

    private final UserRepository userRepository;


    public String[] roles = new String[]{"Aluno", "Professor"};


    public MutableLiveData<Cozinha[]> cozinhas = new MutableLiveData<>(new Cozinha[]{});


    public MutableLiveData<String> username = new MutableLiveData<>("");
    public MutableLiveData<String> email = new MutableLiveData<>("");
    public MutableLiveData<String> password = new MutableLiveData<>("");
    public MutableLiveData<String> nameUser = new MutableLiveData<>("");
    public MutableLiveData<String> phoneNumber = new MutableLiveData<>("");
    public MutableLiveData<String> address = new MutableLiveData<>("");
    public MutableLiveData<String> locale = new MutableLiveData<>("");
    public MutableLiveData<String> postalCode = new MutableLiveData<>("");
    public MutableLiveData<Integer> roleIndex = new MutableLiveData<>(0);
    public MutableLiveData<Integer> cozinhaIndex = new MutableLiveData<>(0);
    private MutableLiveData<Boolean> doSuccess = new MutableLiveData<>(false);
    private MutableLiveData<String> errors = new MutableLiveData<>();
    public LiveData<String> getErrors(){
        return errors;
    }
    public LiveData<Boolean> getDoSuccess(){
        return doSuccess;
    }


    public RegisterViewModel(){
        this.userRepository = new UserRepository();
    }



    public void fetchCozinha(){
        userRepository.fetchCozinha( result -> {
            cozinhas.postValue(result);
        }, (erro, status) ->{

        });
    }

    public void doRegister() {
        if (isRequestInProgress) {
            return;
        }

        isRequestInProgress = true;

        Integer cozinhaInd = cozinhaIndex.getValue();
        if (cozinhaInd == null) {
            errors.postValue("Cozinha must not be null.");
            return;
        }


        Cozinha[] arrCozinhas = cozinhas.getValue();
        if (arrCozinhas == null) {
            errors.postValue("Cozinha must not be null.");
            return;
        }



        String selectedRole = roles[roleIndex.getValue()].toLowerCase();
        Cozinha cozinha = arrCozinhas[cozinhaInd];

        userRepository.register(
                username.getValue(),
                email.getValue(),
                password.getValue(),
                nameUser.getValue(),
                phoneNumber.getValue(),
                address.getValue(),
                locale.getValue(),
                postalCode.getValue(),
                selectedRole,
                cozinha.id,
                response -> {
                    isRequestInProgress = false;
                    doSuccess.postValue(true);
                },
                (error, status) -> {
                    isRequestInProgress = false;
                    errors.postValue(error);
                }
        );

    }

}

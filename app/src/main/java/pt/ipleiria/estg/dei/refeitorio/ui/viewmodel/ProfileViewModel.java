package pt.ipleiria.estg.dei.refeitorio.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import pt.ipleiria.estg.dei.refeitorio.data.models.Cozinha;
import pt.ipleiria.estg.dei.refeitorio.data.repository.UserRepository;

public class ProfileViewModel extends ViewModel {

    private final UserRepository userRepository;
    private MutableLiveData<String> username = new MutableLiveData<>();
    private MutableLiveData<String> email = new MutableLiveData<>();
    private MutableLiveData<String> name = new MutableLiveData<>();
    private MutableLiveData<String> phone = new MutableLiveData<>();
    private MutableLiveData<String> address = new MutableLiveData<>();
    private MutableLiveData<String> locale = new MutableLiveData<>();
    private MutableLiveData<String> postalCode = new MutableLiveData<>();

    public MutableLiveData<Cozinha[]> cozinhas = new MutableLiveData<>(new Cozinha[]{});




    public void setUsername(String username) {
        this.username.setValue(username);
    }

    public void setEmail(String email) {
        this.email.setValue(email);
    }

    public void setName(String name) {
        this.name.setValue(name);
    }

    public void setPhone(String phone) {
        this.phone.setValue(phone);
    }

    public void setAddress(String address) {
        this.address.setValue(address);
    }

    public void setLocale(String locale) {
        this.locale.setValue(locale);
    }

    public void setPostalCode(String postalCode) {
        this.postalCode.setValue(postalCode);
    }

    public LiveData<String> getUsername() {
        return username;
    }

    public LiveData<String> getEmail() {
        return email;
    }

    public LiveData<String> getName() {
        return name;
    }

    public LiveData<String> getPhone() {
        return phone;
    }

    public LiveData<String> getAddress() {
        return address;
    }

    public LiveData<String> getLocale() {
        return locale;
    }

    public LiveData<String> getPostalCode() {
        return postalCode;
    }


    public ProfileViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void fetchCozinha(){
        userRepository.fetchCozinha( result -> {
            cozinhas.postValue(result);
        }, (erro, status) ->{

        });
    }
}

package pt.ipleiria.estg.dei.refeitorio.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import pt.ipleiria.estg.dei.refeitorio.data.models.EmentaMenu;
import pt.ipleiria.estg.dei.refeitorio.data.models.Fatura;
import pt.ipleiria.estg.dei.refeitorio.data.repository.CompraRepository;

public class MenuViewModel extends ViewModel {
    private final CompraRepository compraRepository;
    public MenuViewModel(){
        this.compraRepository = new CompraRepository();
    }


    private MutableLiveData<EmentaMenu[]> result = new MutableLiveData<>();

    public LiveData<EmentaMenu[]> getResult(){
        return result;
    }

    public MutableLiveData<Boolean> loading = new MutableLiveData<>(true);

    public void fetchEmentaMenu(){
        loading.postValue(true);
        compraRepository.fetchEmentaMenu(
                response -> {
                    result.postValue(response);
                    loading.postValue(false);
                },
                (error, status) -> {
                    loading.postValue(false);
                }
        );
    }




}

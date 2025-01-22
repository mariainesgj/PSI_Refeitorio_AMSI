package pt.ipleiria.estg.dei.refeitorio.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Arrays;

import pt.ipleiria.estg.dei.refeitorio.data.models.Fatura;
import pt.ipleiria.estg.dei.refeitorio.data.repository.CompraRepository;

public class FaturaViewModel extends ViewModel {
    private final CompraRepository compraRepository;

    private MutableLiveData<Fatura[]> result = new MutableLiveData<>();

    public LiveData<Fatura[]> getResult(){
        return result;
    }

    public FaturaViewModel() {
        this.compraRepository = new CompraRepository();
    }


    public void fetchFaturas(){
        compraRepository.fetchFaturas(
            response -> {
                result.postValue(response);
            },
            error -> {

            }
        );
    }


}

package pt.ipleiria.estg.dei.refeitorio.ui.viewmodel;

import android.util.Log;
import android.util.Pair;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import pt.ipleiria.estg.dei.refeitorio.data.models.PratoDia;
import pt.ipleiria.estg.dei.refeitorio.data.repository.MenuRepository;

public class PratoDiaViewModel extends ViewModel {
    private MenuRepository repository;

    private MutableLiveData<PratoDia> result = new MutableLiveData<>();

    public LiveData<PratoDia> getResult(){
        return result;
    }
    public MutableLiveData<Boolean> loading = new MutableLiveData<>(true);

    /**
     * 1 - Mensagem
     * 2 - Status
     */
    private MutableLiveData<Pair<String, String>> error = new MutableLiveData<>();

    public LiveData<Pair<String, String>> getError(){
        return error;
    }

    public PratoDiaViewModel(){
        this.repository = new MenuRepository();
    }


    public void fetchPratoDia(String date){
        loading.postValue(true);
        repository.fetchPratoDoDia(date, response -> {
            result.postValue(response);
            loading.postValue(false);
        }, (err, status) -> {
            loading.postValue(false);
            error.postValue(new Pair<>(err, status));
        });
    }
}

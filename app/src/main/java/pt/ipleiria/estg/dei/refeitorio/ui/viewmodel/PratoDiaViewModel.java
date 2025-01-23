package pt.ipleiria.estg.dei.refeitorio.ui.viewmodel;

import android.util.Log;

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

    public PratoDiaViewModel(){
        this.repository = new MenuRepository();
    }


    public void fetchPratoDia(String date){
        loading.postValue(true);
        repository.fetchPratoDoDia(date, response -> {
            result.postValue(response);
            loading.postValue(false);
        }, error -> {
            Log.i("TESTE", "fetchPratoDia: error");
            loading.postValue(false);
        });
    }
}

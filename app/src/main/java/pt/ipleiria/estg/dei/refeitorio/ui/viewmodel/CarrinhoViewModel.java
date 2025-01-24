package pt.ipleiria.estg.dei.refeitorio.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import pt.ipleiria.estg.dei.refeitorio.data.models.Carrinho;
import pt.ipleiria.estg.dei.refeitorio.data.models.Fatura;
import pt.ipleiria.estg.dei.refeitorio.data.repository.CompraRepository;

public class CarrinhoViewModel extends ViewModel {

    private final CompraRepository compraRepository;

    public CarrinhoViewModel(){
        this.compraRepository = new CompraRepository();
    }


    private MutableLiveData<Carrinho> result = new MutableLiveData<>();

    public MutableLiveData<Boolean> loading = new MutableLiveData<>(true);

    public LiveData<Carrinho> getResult(){
        return result;
    }

    public void fetchCarrinho(){
        loading.postValue(true);
        compraRepository.fetchCarrinho(
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

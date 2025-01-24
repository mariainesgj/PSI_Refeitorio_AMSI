package pt.ipleiria.estg.dei.refeitorio.ui.viewmodel;

import android.util.Pair;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import pt.ipleiria.estg.dei.refeitorio.data.models.Carrinho;
import pt.ipleiria.estg.dei.refeitorio.data.models.Fatura;
import pt.ipleiria.estg.dei.refeitorio.data.network.RequestHandler;
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


    private MutableLiveData<String> error = new MutableLiveData<>();


    public MutableLiveData<String> carNumber = new MutableLiveData<>("");
    public MutableLiveData<String> expirationDate = new MutableLiveData<>("");
    public MutableLiveData<String> securityCode = new MutableLiveData<>("");
    public MutableLiveData<String> cardHolder = new MutableLiveData<>("");


    public void fetchCarrinho(RequestHandler.ErrorListener onError){
        loading.postValue(true);
        compraRepository.fetchCarrinho(
                response -> {
                    result.postValue(response);
                    loading.postValue(false);
                },
                (err, status) -> {
                    loading.postValue(false);
                    onError.onError(err, status);
                }
        );
    }


    public void postCheckout( RequestHandler.SuccessListener<Boolean> onSuccess,
                               RequestHandler.ErrorListener onError){


        String mCardNumber = carNumber.getValue();
        String mExpirationDate = expirationDate.getValue();
        String mSecurityCode = securityCode.getValue();
        String mCardHolder = cardHolder.getValue();


             if( mCardNumber.trim().isEmpty() ||
                mExpirationDate.trim().isEmpty() ||
                     mSecurityCode.trim().isEmpty() ||
                     mCardHolder.trim().isEmpty()){

                 error.postValue("Prencha todos os campos!");
                 return;
             }


        loading.postValue(true);
        compraRepository.postCheckout(
                mCardNumber,
                mExpirationDate,
                mSecurityCode,
                mCardHolder,
                response -> {
                    onSuccess.onSuccess(true);
                    loading.postValue(false);
                },
                (error, status) -> {
                    onError.onError(error, status);
                    loading.postValue(false);
                }
        );
    }


}

package pt.ipleiria.estg.dei.refeitorio.data.repository;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pt.ipleiria.estg.dei.refeitorio.MainApplication;
import pt.ipleiria.estg.dei.refeitorio.data.models.Carrinho;
import pt.ipleiria.estg.dei.refeitorio.data.models.EmentaMenu;
import pt.ipleiria.estg.dei.refeitorio.data.models.Fatura;
import pt.ipleiria.estg.dei.refeitorio.data.models.User;
import pt.ipleiria.estg.dei.refeitorio.data.network.ApiClient;
import pt.ipleiria.estg.dei.refeitorio.data.network.ApiEndpoints;
import pt.ipleiria.estg.dei.refeitorio.data.network.RequestHandler;

public class CompraRepository {

    private static String TAG = "CompraRepository";

    private final Context context = MainApplication.getContext();

    public void fetchFaturas(RequestHandler.SuccessListener<Fatura[]> onSuccess, RequestHandler.ErrorListener onError){
        RequestHandler.fetchData(context, ApiEndpoints.FATURA_FETCH, response -> {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                if (RequestHandler.checkIfSuccess(jsonResponse)) {
                    JSONArray data = jsonResponse.getJSONArray("data");

                    Fatura[] results =  new Gson().fromJson(data.toString(), Fatura[].class);

                    onSuccess.onSuccess(results);
                }else {
                    String errorMessage = jsonResponse.optString("message", "Erro desconhecido");
                    String statusMessage = jsonResponse.optString("status", ApiClient.RESULT_UNEXPECTED);
                    onError.onError(errorMessage, statusMessage);
                }

            }
            catch (Exception ex){
                onError.onError("Erro ao processar a resposta do servidor.", ApiClient.RESULT_UNEXPECTED);
            }
        }, onError);
    }

    public void fetchEmentaMenu(RequestHandler.SuccessListener<EmentaMenu[]> onSuccess, RequestHandler.ErrorListener onError){
        RequestHandler.fetchData(context, ApiEndpoints.PRATOS_SENHAS_FETCH, response -> {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                if (RequestHandler.checkIfSuccess(jsonResponse)) {
                    JSONArray data = jsonResponse.getJSONArray("data");

                    EmentaMenu[] results =  new Gson().fromJson(data.toString(), EmentaMenu[].class);

                    onSuccess.onSuccess(results);
                }else {
                    String errorMessage = jsonResponse.optString("message", "Erro desconhecido");
                    String statusMessage = jsonResponse.optString("status", ApiClient.RESULT_UNEXPECTED);
                    onError.onError(errorMessage, statusMessage);
                }

            }
            catch (Exception ex){
                onError.onError("Erro ao processar a resposta do servidor.", ApiClient.RESULT_UNEXPECTED);
            }
        }, onError);
    }


    public void fetchCarrinho(RequestHandler.SuccessListener<Carrinho> onSuccess, RequestHandler.ErrorListener onError){
        RequestHandler.fetchData(context, ApiEndpoints.CARRINHO_FETCH, response -> {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                if (RequestHandler.checkIfSuccess(jsonResponse)) {
                    JSONObject data = jsonResponse.getJSONObject("data");

                    Carrinho results =  new Gson().fromJson(data.toString(), Carrinho.class);

                    onSuccess.onSuccess(results);
                }else {
                    String errorMessage = jsonResponse.optString("message", "Erro desconhecido");
                    String statusMessage = jsonResponse.optString("status", ApiClient.RESULT_UNEXPECTED);
                    onError.onError(errorMessage, statusMessage);
                }

            }
            catch (Exception ex){
                onError.onError("Erro ao processar a resposta do servidor.", ApiClient.RESULT_UNEXPECTED);
            }
        }, onError);
    }


    public void postAddCarrinhoLinha(
            Integer ementaId,
            Integer pratoId,
            RequestHandler.SuccessListener<Boolean> onSuccess,
            RequestHandler.ErrorListener onError
    ){


        HashMap<String, String> params = new HashMap<>();

        params.put("ementa_id", ementaId.toString());
        params.put("prato_id", pratoId.toString());


        RequestHandler.postData(
            context,
            ApiEndpoints.CARRINHO_ADD_ITEM_POST,
            null,
            params,
        response -> {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                if (RequestHandler.checkIfSuccess(jsonResponse)) {
                    onSuccess.onSuccess(true);
                }else {
                    String errorMessage = jsonResponse.optString("message", "Erro desconhecido");
                    String statusMessage = jsonResponse.optString("status", ApiClient.RESULT_UNEXPECTED);
                    onError.onError(errorMessage, statusMessage);
                }
            }
            catch (Exception ex){
                onError.onError("Erro ao processar a resposta do servidor.", ApiClient.RESULT_UNEXPECTED);
            }
        }, onError);
    }

    public void deleteCarrinhoLinha(
            Integer linhaId,
            RequestHandler.SuccessListener<Boolean> onSuccess,
            RequestHandler.ErrorListener onError
    ){
        RequestHandler.deleteData(
                context,
                String.format(ApiEndpoints.CARRINHO_EXCLUIR_ITEM_DELETE, linhaId.toString()),
                null,
                response -> {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        if (RequestHandler.checkIfSuccess(jsonResponse)) {
                            onSuccess.onSuccess(true);
                        }else {
                            String errorMessage = jsonResponse.optString("message", "Erro desconhecido");
                            String statusMessage = jsonResponse.optString("status", ApiClient.RESULT_UNEXPECTED);
                            onError.onError(errorMessage, statusMessage);
                        }
                    }
                    catch (Exception ex){
                        onError.onError("Erro ao processar a resposta do servidor.", ApiClient.RESULT_UNEXPECTED);
                    }
                }, onError);
    }


    public void postCheckout(
            String cardNumber,
            String expirationDate,
            String securityCode,
            String cardHolder,
            RequestHandler.SuccessListener<Boolean> onSuccess,
            RequestHandler.ErrorListener onError
    ){
        JsonObject json = new JsonObject();
        json.addProperty("cardNumber", cardNumber);
        json.addProperty("expirationDate", expirationDate);
        json.addProperty("securityCode", securityCode);
        json.addProperty("cardHolder", cardHolder);


        RequestHandler.postData(
                context,
                ApiEndpoints.CHECKOUT_POST,
                json,
                null,
                response -> {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        if (RequestHandler.checkIfSuccess(jsonResponse)) {
                            onSuccess.onSuccess(true);
                        }else {
                            String errorMessage = jsonResponse.optString("message", "Erro desconhecido");
                            String statusMessage = jsonResponse.optString("status", ApiClient.RESULT_UNEXPECTED);
                            onError.onError(errorMessage, statusMessage);
                        }
                    }
                    catch (Exception ex){
                        onError.onError("Erro ao processar a resposta do servidor.", ApiClient.RESULT_UNEXPECTED);
                    }
                }, onError);
    }
}

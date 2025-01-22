package pt.ipleiria.estg.dei.refeitorio.data.repository;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import pt.ipleiria.estg.dei.refeitorio.MainApplication;
import pt.ipleiria.estg.dei.refeitorio.data.models.Fatura;
import pt.ipleiria.estg.dei.refeitorio.data.models.User;
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
                    onError.onError(errorMessage);
                }

            }
            catch (Exception ex){
                onError.onError("Erro ao processar a resposta do servidor.");
            }
        }, onError);
    }
}

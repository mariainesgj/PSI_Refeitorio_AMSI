package pt.ipleiria.estg.dei.refeitorio.data.repository;

import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import pt.ipleiria.estg.dei.refeitorio.MainApplication;
import pt.ipleiria.estg.dei.refeitorio.data.models.Fatura;
import pt.ipleiria.estg.dei.refeitorio.data.models.PratoDia;
import pt.ipleiria.estg.dei.refeitorio.data.network.ApiEndpoints;
import pt.ipleiria.estg.dei.refeitorio.data.network.RequestHandler;

public class MenuRepository {

    private final Context context = MainApplication.getContext();

    public void fetchPratoDoDia(String date, RequestHandler.SuccessListener<PratoDia> onSuccess, RequestHandler.ErrorListener onError){
        RequestHandler.fetchData(context, String.format(ApiEndpoints.PRATO_DIA_FETCH, date), response -> {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                if (RequestHandler.checkIfSuccess(jsonResponse)) {
                    JSONObject data = jsonResponse.getJSONObject("data");

                    PratoDia results =  new Gson().fromJson(data.toString(), PratoDia.class);

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

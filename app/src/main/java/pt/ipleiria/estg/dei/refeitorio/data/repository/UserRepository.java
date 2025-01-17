package pt.ipleiria.estg.dei.refeitorio.data.repository;

import static java.util.Map.entry;

import android.content.Context;

import java.util.Map;

import pt.ipleiria.estg.dei.refeitorio.data.network.ApiEndpoints;
import pt.ipleiria.estg.dei.refeitorio.data.network.RequestHandler;

public class UserRepository {
    private final Context context;

    public UserRepository(Context context){
        this.context = context;
    }

    public void login(
            String login,
            String password,
            RequestHandler.SuccessListener<Boolean>  onSuccess,
            RequestHandler.ErrorListener onError
    ){

        Map<String, String> params = Map.ofEntries(
                entry("login", login),
                entry("password", password)
        );

        RequestHandler.postData(context, ApiEndpoints.LOGIN, params , response -> {
            //TODO parse json to Model
            //TODO save tokens
            onSuccess.onSuccess(true);

        }, error -> {
            //TODO parse error
            onError.onError("Mensagem de error");
        });
    }
}

package pt.ipleiria.estg.dei.refeitorio.data.repository;

import static java.util.Map.entry;

import android.content.Context;

import java.util.HashMap;
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

        Map<String, String> params = new HashMap<>();
        params.put("login", login);
        params.put("password", password);


        RequestHandler.postData(context, ApiEndpoints.LOGIN, params , response -> {
            //TODO parse json to Model
            //TODO save tokens
            onSuccess.onSuccess(true);

        }, error -> {
            //TODO parse error
            onError.onError("Mensagem de error");
        });
    }

    public void register(
            String username,
            String email,
            String password,
            String nameUser,
            String phoneNumber,
            String address,
            String locale,
            String postalCode,
            String role,
            int cozinha,
            RequestHandler.SuccessListener<Boolean> onSuccess,
            RequestHandler.ErrorListener onError
    ) {
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("email", email);
        params.put("password", password);
        params.put("name", nameUser);
        params.put("mobile", phoneNumber);
        params.put("street", address);
        params.put("locale", locale);
        params.put("postalCode", postalCode);
        params.put("role", role);
        params.put("cozinha_id", String.valueOf(cozinha));

        RequestHandler.postData(context, ApiEndpoints.REGISTER, params, response -> {
            // TODO: Parsear o JSON para o modelo
            // TODO: Salvar tokens
            onSuccess.onSuccess(true);

        }, error -> {
            // TODO: Tratar erros
            onError.onError("Mensagem de erro");
        });
    }

}

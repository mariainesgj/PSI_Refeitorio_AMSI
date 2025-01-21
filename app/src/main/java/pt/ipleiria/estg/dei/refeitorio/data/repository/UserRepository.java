package pt.ipleiria.estg.dei.refeitorio.data.repository;

import static java.util.Map.entry;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import pt.ipleiria.estg.dei.refeitorio.data.models.User;
import pt.ipleiria.estg.dei.refeitorio.data.network.ApiClient;
import pt.ipleiria.estg.dei.refeitorio.data.network.ApiEndpoints;
import pt.ipleiria.estg.dei.refeitorio.data.network.RequestHandler;
import pt.ipleiria.estg.dei.refeitorio.helpers.SharedPref;

public class UserRepository {
    private final Context context;

    public UserRepository(Context context) {
        this.context = context;
    }

    public void login(
            String login,
            String password,
            RequestHandler.SuccessListener<Boolean> onSuccess,
            RequestHandler.ErrorListener onError
    ) {
        if (login == null || login.isEmpty() || password == null || password.isEmpty()) {
            onError.onError("Username ou palavra-passe não podem estar vazios.");
            return;
        }

        JsonObject body = new JsonObject();
        body.addProperty("username", login);
        body.addProperty("password", password);

        RequestHandler.postData(context, ApiEndpoints.LOGIN, body, new HashMap<>(), response -> {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                if (RequestHandler.checkIfSuccess(jsonResponse)) {

                    JSONObject data = jsonResponse.getJSONObject("data");
                    // TODO Validar response, verificar se possui chaves to objeto User
                    User user = new Gson().fromJson(data.getString("user"), User.class);

                    String token = jsonResponse.getString("access_token");
                    SharedPref.setItem(SharedPref.TOKEN, token);
                    SharedPref.setItem(SharedPref.KEY_USER, user);

                    onSuccess.onSuccess(true);
                } else {
                    String errorMessage = jsonResponse.optString("message", "Erro desconhecido");
                    onError.onError(errorMessage);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                onError.onError("Erro ao processar a resposta do servidor.");
            }
        }, onError);
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

        if (username == null || username.isEmpty() || email == null || email.isEmpty()
                || password == null || password.isEmpty() || nameUser == null || nameUser.isEmpty()
                || phoneNumber == null || phoneNumber.isEmpty() || address == null || address.isEmpty()
                || locale == null || locale.isEmpty() || postalCode == null || postalCode.isEmpty()
                || role == null || role.isEmpty() || cozinha <= 0) {
            onError.onError("Preencha todos os campos, por favor.");
            return;
        }

        JsonObject body = new JsonObject();
        body.addProperty("username", username);
        body.addProperty("email", email);
        body.addProperty("password", password);
        body.addProperty("name", nameUser);
        body.addProperty("mobile", phoneNumber);
        body.addProperty("street", address);
        body.addProperty("locale", locale);
        body.addProperty("postalCode", postalCode);
        body.addProperty("role", role);
        body.addProperty("cozinha_id", cozinha);

        RequestHandler.postData(context, ApiEndpoints.REGISTER, body, new HashMap<>(), response -> {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                if (RequestHandler.checkIfSuccess(jsonResponse)) {
                    JSONObject data = jsonResponse.getJSONObject("data");
                    User user = new Gson().fromJson(data.getString("user"), User.class);
                    String token = jsonResponse.getString("auth_key");
                    SharedPref.setItem(SharedPref.TOKEN, token);
                    SharedPref.setItem(SharedPref.KEY_USER, user);
                    onSuccess.onSuccess(true);
                } else {
                    String errorMessage = jsonResponse.optString("message", "Erro desconhecido");
                    onError.onError(errorMessage);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                onError.onError("Erro ao processar a resposta do servidor.");
            }
        }, onError);
    }

}

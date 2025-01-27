package pt.ipleiria.estg.dei.refeitorio.data.repository;

import static java.util.Map.entry;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import pt.ipleiria.estg.dei.refeitorio.MainApplication;
import pt.ipleiria.estg.dei.refeitorio.data.models.Cozinha;
import pt.ipleiria.estg.dei.refeitorio.data.models.EmentaMenu;
import pt.ipleiria.estg.dei.refeitorio.data.models.User;
import pt.ipleiria.estg.dei.refeitorio.data.network.ApiClient;
import pt.ipleiria.estg.dei.refeitorio.data.network.ApiEndpoints;
import pt.ipleiria.estg.dei.refeitorio.data.network.RequestHandler;
import pt.ipleiria.estg.dei.refeitorio.helpers.SharedPref;

public class UserRepository {
    private final Context context = MainApplication.getContext();


    public void login(
            String login,
            String password,
            RequestHandler.SuccessListener<Boolean> onSuccess,
            RequestHandler.ErrorListener onError
    ) {
        if (login == null || login.isEmpty() || password == null || password.isEmpty()) {
            onError.onError("Username ou palavra-passe não podem estar vazios.", ApiClient.RESULT_INVALID);
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
                    String statusMessage = jsonResponse.optString("status", ApiClient.RESULT_UNEXPECTED);
                    onError.onError(errorMessage, statusMessage);
                }
            } catch (JSONException e) {
                //e.printStackTrace();
                onError.onError("Erro ao processar a resposta do servidor.", ApiClient.RESULT_UNEXPECTED);
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
            onError.onError("Preencha todos os campos, por favor.",ApiClient.RESULT_INVALID);
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
                    String token = jsonResponse.getString("access_token");
                    SharedPref.setItem(SharedPref.TOKEN, token);
                    SharedPref.setItem(SharedPref.KEY_USER, user);
                    onSuccess.onSuccess(true);
                } else {
                    String errorMessage = jsonResponse.optString("message", "Erro desconhecido");
                    String statusMessage = jsonResponse.optString("status", ApiClient.RESULT_UNEXPECTED);
                    onError.onError(errorMessage, statusMessage);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                onError.onError("Erro ao processar a resposta do servidor.", ApiClient.RESULT_UNEXPECTED);
            }
        }, onError);
    }


    public void fetchCozinha(RequestHandler.SuccessListener<Cozinha[]> onSuccess, RequestHandler.ErrorListener onError){
        RequestHandler.fetchData(context, ApiEndpoints.COZINHA_FETCH, response -> {
            try {
                JSONArray jsonResponse = new JSONArray(response);
                Cozinha[] results =  new Gson().fromJson(jsonResponse.toString(), Cozinha[].class);
                onSuccess.onSuccess(results);
            }
            catch (Exception ex){
                onError.onError("Erro ao processar a resposta do servidor.", ApiClient.RESULT_UNEXPECTED);
            }
        }, onError);
    }

    public void update(
            String username,
            String email,
            String nameUser,
            String phoneNumber,
            String address,
            String locale,
            String postalCode,
            RequestHandler.SuccessListener<Boolean> onSuccess,
            RequestHandler.ErrorListener onError
    ) {

        if (username == null || username.isEmpty() || email == null || email.isEmpty()
                || nameUser == null || nameUser.isEmpty()
                || phoneNumber == null || phoneNumber.isEmpty() || address == null || address.isEmpty()
                || locale == null || locale.isEmpty() || postalCode == null || postalCode.isEmpty()) {
            onError.onError("Preencha todos os campos, por favor.",ApiClient.RESULT_INVALID);
            return;
        }

        JsonObject body = new JsonObject();
        body.addProperty("username", username);
        body.addProperty("email", email);
        body.addProperty("name", nameUser);
        body.addProperty("mobile", phoneNumber);
        body.addProperty("street", address);
        body.addProperty("locale", locale);
        body.addProperty("postalCode", postalCode);

        RequestHandler.putData(context, ApiEndpoints.USER_UPDATE, body, new HashMap<>(), response -> {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                if (RequestHandler.checkIfSuccess(jsonResponse)) {
                    JSONObject data = jsonResponse.getJSONObject("data");
                    User user = new Gson().fromJson(data.getString("user"), User.class);
                    SharedPref.setItem(SharedPref.KEY_USER, user);
                    onSuccess.onSuccess(true);
                } else {
                    String errorMessage = jsonResponse.optString("message", "Erro desconhecido");
                    String statusMessage = jsonResponse.optString("status", ApiClient.RESULT_UNEXPECTED);
                    onError.onError(errorMessage, statusMessage);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                onError.onError("Erro ao processar a resposta do servidor.", ApiClient.RESULT_UNEXPECTED);
            }
        }, onError);
    }

}

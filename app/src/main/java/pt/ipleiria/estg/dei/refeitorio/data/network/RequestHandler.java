package pt.ipleiria.estg.dei.refeitorio.data.network;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class RequestHandler<T> {
    public static void fetchData(Context context, String url,
                                 final SuccessListener<String> onSuccess, final ErrorListener onError) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, url,
                onSuccess::onSuccess,
                error -> onError.onError(error.getMessage() != null ? error.getMessage() : "An error occurred")
        );

        ApiClient.getInstance(context).addToRequestQueue(stringRequest);
    }

    public static void postData(
            Context context,
            String url,
            final JsonElement body,
            final Map<String, String> params,
            final SuccessListener<String> onSuccess,
            final ErrorListener onError
    ) {
        StringRequest postRequest = new StringRequest(
                Request.Method.POST, url,
                onSuccess::onSuccess,
                error -> {
                    try {
                        String response = new String(error.networkResponse.data);
                        JsonObject json = JsonParser.parseString(response).getAsJsonObject();
                        onError.onError(json.get("message").getAsString());
                    }catch (Exception e){
                        onError.onError(error.getMessage() != null ? error.getMessage() : "An error occurred");
                    }
                }
        ) {

            @Override
            public byte[] getBody() throws AuthFailureError {
                return body.toString().getBytes(StandardCharsets.UTF_8);
            }

            @Override
            protected Map<String, String> getParams() {
                return params;
            }
        };

        ApiClient.getInstance(context).addToRequestQueue(postRequest);
    }


    public static void putData(
            Context context,
            String url,
            final JsonObject body,
            final Map<String, String> params,
            final SuccessListener<String> onSuccess,
            final ErrorListener onError
    ) {
        StringRequest postRequest = new StringRequest(
                Request.Method.PUT, url,
                onSuccess::onSuccess,
                error -> {
                    try {
                        String response = new String(error.networkResponse.data);
                        JsonObject json = JsonParser.parseString(response).getAsJsonObject();
                        onError.onError(json.get("message").getAsString());
                    }catch (Exception e){
                        onError.onError(error.getMessage() != null ? error.getMessage() : "An error occurred");
                    }
                }
        ) {

            @Override
            public byte[] getBody() throws AuthFailureError {
                return body.toString().getBytes(StandardCharsets.UTF_8);
            }

            @Override
            protected Map<String, String> getParams() {
                return params;
            }
        };

        ApiClient.getInstance(context).addToRequestQueue(postRequest);
    }


    public static void deleteData(Context context, String url, final Map<String, String> params,
                                  final SuccessListener<String> onSuccess, final ErrorListener onError) {
        StringRequest postRequest = new StringRequest(
                Request.Method.DELETE, url,
                onSuccess::onSuccess,
                error -> onError.onError(error.getMessage() != null ? error.getMessage() : "An error occurred")
        ) {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }
        };

        ApiClient.getInstance(context).addToRequestQueue(postRequest);
    }

    public static boolean checkIfSuccess(JSONObject json) {
        try {
            return json.getString("status").equals(ApiClient.RESULT_SUCCESS);
        } catch (JSONException e) {
            return false;
        }
    }

    public interface SuccessListener<T> {
        void onSuccess(T response);
    }

    public interface ErrorListener {
        void onError(String error);
    }
}


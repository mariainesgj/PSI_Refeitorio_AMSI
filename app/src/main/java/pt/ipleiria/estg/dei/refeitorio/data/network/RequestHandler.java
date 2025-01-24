package pt.ipleiria.estg.dei.refeitorio.data.network;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pt.ipleiria.estg.dei.refeitorio.helpers.SharedPref;

public class RequestHandler<T> {

    private static Map<String, String> getCustomHeaders(){
        Map<String, String> params = new HashMap<>();
        String token  = SharedPref.getItem(SharedPref.TOKEN, String.class);
        if(token != null){
            params.put("Authorization", "Bearer "+ token);
        }

        return params;
    }


    public static void fetchData(Context context, String url,
                                 final SuccessListener<String> onSuccess, final ErrorListener onError) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, url,
                onSuccess::onSuccess,
                error -> {
                    handleError(error, onError);
                }
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return getCustomHeaders();
            }
        };

        ApiClient.getInstance(context).addToRequestQueue(stringRequest);
    }

    private static void handleError(VolleyError error, ErrorListener onError) {
        try {
            String response = new String(error.networkResponse.data);
            JsonObject json = JsonParser.parseString(response).getAsJsonObject();
            onError.onError(json.get("message").getAsString(), json.get("status").getAsString());
        } catch (Exception e) {
            onError.onError(error.getMessage() != null ? error.getMessage() : "An error occurred", ApiClient.RESULT_UNEXPECTED);
        }
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
                    handleError(error, onError);
                }
        ) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return getCustomHeaders();
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                if(body == null){
                    return super.getBody();
                }
                return body.toString().getBytes(StandardCharsets.UTF_8);
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                if (params == null) {
                    return super.getParams();
                }
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
                    handleError(error, onError);
                }
        ) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return getCustomHeaders();
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                if(body == null){
                    return new byte[0];
                }
                return body.toString().getBytes(StandardCharsets.UTF_8);
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                if (params == null) {
                    return super.getParams();
                }
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
                error -> {
                    handleError(error, onError);
                }
        ) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return getCustomHeaders();
            }
            @Override
            protected Map<String, String> getParams() {
                if (params == null) {
                    return new HashMap<>();
                }
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
        void onError(String error, String status);
    }
}


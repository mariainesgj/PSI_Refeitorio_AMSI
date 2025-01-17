package pt.ipleiria.estg.dei.refeitorio.data.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

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

    public static void postData(Context context, String url, final Map<String, String> params,
                                final SuccessListener<String> onSuccess, final ErrorListener onError) {
        StringRequest postRequest = new StringRequest(
                Request.Method.POST, url,
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


    public static void putData(Context context, String url, final Map<String, String> params,
                               final SuccessListener<String> onSuccess, final ErrorListener onError) {
        StringRequest postRequest = new StringRequest(
                Request.Method.PUT, url,
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

    public interface SuccessListener<T> {
        void onSuccess(T response);
    }

    public interface ErrorListener {
        void onError(String error);
    }
}


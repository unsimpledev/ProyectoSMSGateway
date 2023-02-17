package app.unsimpledev.smsgatewayapp;

import android.content.Context;
import android.util.Base64;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class PostRequest {

    private Context context;
    private String url;
    private Map<String, String> params;

    public PostRequest(Context context, String url, Map<String, String> params) {
        this.context = context;
        this.url = url;
        this.params = params;

    }

    public void execute(final PostRequestCallback callback) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Maneje la respuesta del servidor
                        callback.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Maneje el error
                        callback.onError(error);
                    }
                }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                String credenciales = Config.USER_CONN_REST + ":"+ Config.PASS_CONN_REST;
                String auth = "Basic " + Base64.encodeToString(credenciales.getBytes(), Base64.NO_WRAP);
                headers.put("Authorization", auth);
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                return params;
            }
        };

        // Agregue la solicitud a la cola de solicitudes
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    public interface PostRequestCallback {
        void onSuccess(String response);
        void onError(VolleyError error);
    }
}

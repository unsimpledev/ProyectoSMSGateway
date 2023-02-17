package app.unsimpledev.smsgatewayapp;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registrarDispositivo();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_DENIED) {
                Log.d("permission", "permission denied to SEND_SMS - requesting it");
                String[] permissions = {Manifest.permission.SEND_SMS};
                requestPermissions(permissions, 101);
            }
        }
    }

    protected void registrarDispositivo(){
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("NOTIF", "Fetching FCM registration token failed failed", task.getException());
                            Toast.makeText(getApplicationContext(), "Error obteniendo token en FCM", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        String token = task.getResult();
                        Log.d("NOTIF", "Token: " + token);
                        // Envíe el token a su servidor o back-end
                        String tokenGuardado = getTokenGuardado();
                        //Toast.makeText(getApplicationContext(), token, Toast.LENGTH_SHORT).show();
                        if (token!= null && !token.equals(tokenGuardado)) {
                            // Use the token
                            enviarTokenAServidor(token);
                        }
                    }
                });
    }

    private void enviarTokenAServidor(String token){
        Map<String, String> params = new HashMap<>();
        params.put("TOKEN", token);
        Integer idGuardado = getIdGuardado();
        if (idGuardado!=0){
            params.put("ID", idGuardado.toString());
        }

        PostRequest postRequest = new PostRequest(this, Config.URL_TOKEN_REGISTER, params);
        postRequest.execute(new PostRequest.PostRequestCallback() {
            @Override
            public void onSuccess(String response) {
                // Maneje la respuesta del servidor
                Log.d("NOTIFREG", "Response: " + response);
                //Si la respuesta es OK lo guardo en la app, para no volver a enviar hasta que cambie
                try{
                    JSONObject respJson = new JSONObject(response);
                    String statusResp = respJson.getString("status");
                    Integer idRec = respJson.getInt("ID");

                    if ("success".equals(statusResp) && idRec!=null && idRec!=0){
                        saveToken(token);
                        saveId(idRec);
                        Toast.makeText(getApplicationContext(), "ID: " + idRec + " Token:" +token, Toast.LENGTH_SHORT).show();
                    }
                }catch (JSONException e) {
                    Log.d("NOTIFREG", "Error response parse json: " + e);
                }
            }

            @Override
            public void onError(VolleyError error) {
                // Maneje el error
                Log.e("NOTIFREG", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), "Error registrando token en sv", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void saveToken(String token) {
        SharedPreferences pref = getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(Constants.KEY_TOKEN_FCM, token);
        editor.apply();
    }

    public String getTokenGuardado() {
        SharedPreferences pref = getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
        return pref.getString(Constants.KEY_TOKEN_FCM, null);
    }

    public void saveId(Integer id) {
        SharedPreferences pref = getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(Constants.KEY_ID_REGISTRO_FCM, id);
        editor.apply();
    }

    public Integer getIdGuardado() {
        SharedPreferences pref = getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
        return pref.getInt(Constants.KEY_ID_REGISTRO_FCM, 0);
    }
}
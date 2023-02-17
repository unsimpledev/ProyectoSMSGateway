package app.unsimpledev.smsgatewayapp;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        // Aquí puede manejar el mensaje recibido de FCM
        if (remoteMessage.getData().size() > 0) {
            Log.d("FCM SMS", "Message data payload: " + remoteMessage.getData());
            Map<String, String> data = remoteMessage.getData();
            String phone = data.get("phone");
            String message = data.get("message");
            if (phone != null && message != null) {
                //Podemos hacer el envio directo o crear una tarea para que lo haga en segundo plano
                //Porque el servicio se cierra luego de 10 segundos
                try {
                    SmsSender.sendSms(this, phone, message);
                }catch(Exception e){
                    Log.d("FCM ERROR SEND MSG", "Phone: " + phone + " Message: " + message + " MSG: " + e.getMessage() );
                }
            }
        }
    }


}

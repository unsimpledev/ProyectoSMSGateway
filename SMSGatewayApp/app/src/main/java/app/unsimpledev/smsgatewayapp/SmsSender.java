package app.unsimpledev.smsgatewayapp;

import android.content.Context;
import android.telephony.SmsManager;

public class SmsSender {

    public static void sendSms(Context context, String phoneNumber, String message) {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber, null, message, null, null);
    }
}
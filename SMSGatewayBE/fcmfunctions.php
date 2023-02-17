<?

include_once "config.php";

function send_fcm_notification($token, $phone, $message) {
    $url = 'https://fcm.googleapis.com/fcm/send';
    $apiKey = FCM_KEY_HTTP;

    $fields = array(
        'to' => $token,
        'data' => array(
            'phone' => $phone,
            'message' => $message
        ),
    );


    $headers = array(
        'Authorization: key=' . $apiKey,
        'Content-Type: application/json'
    );

    $ch = curl_init();
    curl_setopt($ch, CURLOPT_URL, $url);
    curl_setopt($ch, CURLOPT_POST, true);
    curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($fields));
    $result = curl_exec($ch);
    curl_close($ch);

    return $result;
}


?>
<?php

include_once "headers.php";
include_once "config.php";
include_once "fcmfunctions.php";
include_once "authfunctions.php";

validateRequest(REQ_CUSTOMER_USERNAME, REQ_CUSTOMER_PASSWORD);

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $phone = $_POST['PHONE'];
    $message = $_POST['MESSAGE'];


    if (!empty($phone) && !empty($message)) {
        
        $servername = DB_SERVER_NAME;
        $username = DB_USERNAME;
        $password = DB_PASSWORD;
        $dbname = DB_NAME;
        
        $conn = mysqli_connect($servername, $username, $password, $dbname);
        
        if ($conn->connect_error) {
            http_response_code(503);
           die("Connection failed: " . $conn->connect_error);
        }

        $sql = "SELECT TOKEN FROM fcm_tokens WHERE ACTIVO='S' LIMIT 1";
        $result = $conn->query($sql);

        if ($result->num_rows > 0) {
            $row = $result->fetch_assoc();
            $token = $row['TOKEN'];
        } else {
            http_response_code(503);
            echo json_encode(array("status" => "error", "message" => "No hay celular de envio disponible."));
        }

        $conn->close();


        if ($token != NULL){        // Invocamos la función para enviar la notificación
            $result = send_fcm_notification($token, $phone, $message);
            
            // Retornamos un JSON con el resultado
            if ($result) {
                http_response_code(200);
                echo json_encode(array("status" => "success", "message" => "Notificación enviada con éxito."));
            } else {
                http_response_code(503);
                echo json_encode(array("status" => "error", "message" => "Error al enviar la notificación."));
            }
        }
    } else {
        http_response_code(400);
        echo json_encode(array("status" => "error","message" => "Parámetros requeridos faltantes."));
    }
 
}
?>
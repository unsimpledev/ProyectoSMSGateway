<?php

include_once "config.php";
include_once "headers.php";
include_once "authfunctions.php";

validateRequest(REQ_APP_USERNAME, REQ_APP_PASSWORD);

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $id = $_POST['ID'];
    $token = $_POST['TOKEN'];

    $servername = DB_SERVER_NAME;
    $username = DB_USERNAME;
    $password = DB_PASSWORD;
    $dbname = DB_NAME;

    // Create connection
    $conn = mysqli_connect($servername, $username, $password, $dbname);

    // Check connection
    if (!$conn) {
        $response = array(
            "status" => "error",
            "message" => "Error en conexion a DB"
        );
        echo json_encode($response);
        http_response_code(500);
        die("Connection failed: " . mysqli_connect_error());
    }

    $sql = "INSERT INTO fcm_tokens (ID, TOKEN)
    VALUES (?,?)
    ON DUPLICATE KEY UPDATE TOKEN=?";

    $stmt = mysqli_prepare($conn, $sql);
    mysqli_stmt_bind_param($stmt, "iss", $id, $token, $token);

    if (mysqli_stmt_execute($stmt)) {
        $id_inserted = mysqli_insert_id($conn);
        $response = array(
            "status" => "success",
            "ID" => $id_inserted
        );
        http_response_code(200);
        echo json_encode($response);
    } else {
        $response = array(
            "status" => "error",
            "message" => mysqli_error($conn)
        );
        http_response_code(200);
        echo json_encode($response);
    }

    mysqli_close($conn);
}

?>
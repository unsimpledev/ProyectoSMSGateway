<?php

function validateRequest($validuser, $validpass){
    // obtener todos los headers enviados por el cliente
    $headers = getallheaders();

    // verificar si existe el header de autenticación
    if (!isset($headers['Authorization'])) {
    // el header de autenticación no está presente
    header('HTTP/1.1 401 Unauthorized');
    exit();
    }

    // obtener la cadena de autenticación
    $auth = $headers['Authorization'];

    // verificar si la cadena de autenticación empieza con "Basic"
    if (strpos($auth, 'Basic') !== 0) {
    // la cadena de autenticación no es válida
    header('HTTP/1.1 401 Unauthorized');
    exit();
    }

    // obtener la cadena codificada en base64
    $auth = substr($auth, 6);

    // decodificar la cadena codificada en base64
    $auth = base64_decode($auth);

    // separar la cadena en nombre de usuario y contraseña
    list($username, $password) = explode(':', $auth);

    // verificar si el nombre de usuario y contraseña son válidos
    if ($username !== $validuser || $password !== $validpass) {
    // el nombre de usuario o contraseña son inválidos
    header('HTTP/1.1 401 Unauthorized');
    exit();
    }
}


?>
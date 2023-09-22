# ProyectoSMSGateway
Implementación de servicio rest para envío de SMS (utilizando celular propio)

Consta de dos módulos, app android y backend (codificado en php).

En los archivos config.php y Configuracion de la app, se deben cambiar los valores a los del ambiente de uso.

<h2>Ejecutar App - Paso a Paso</h2>

<h3>1) Descargar el proyecto</h3>

![smsgateway_app_paso1](https://github.com/unsimpledev/ProyectoSMSGateway/assets/120734027/6ea953c5-6311-41be-b14d-6f19fe2ca6b6)

<h3>2) Ubicarlo en alguna carpeta</h3>

![smsgateway_carpeta](https://github.com/unsimpledev/ProyectoSMSGateway/assets/120734027/392041de-2143-4dbf-9126-7ff5bcfcecb1)

<h3>3) Abrir Android Studio e ir a File Open</h3>

![smsgateway_androidstudio1](https://github.com/unsimpledev/ProyectoSMSGateway/assets/120734027/c9b8f375-bf0f-45f7-9d41-f990fa04e6e0)

<h3>4) Ir a la carpeta y seleccionar SMSGatewayApp</h3>

  ![smsgateway_androidstudio2](https://github.com/unsimpledev/ProyectoSMSGateway/assets/120734027/97305bca-9c39-4f18-81eb-8014b5c3e508)

<h3>5) Se puede ver el proyecto importado</h3>

![smsgateway_androidstudio4](https://github.com/unsimpledev/ProyectoSMSGateway/assets/120734027/70691840-00a7-46c7-818a-13c693b07dda)

<h3>6) En el archivo Config.java indicar los valores correspondientes al Servidor donde se aloje el Backend, el usuario y el password de autenticación</h3>

![smsgateway_androidstudio4](https://github.com/unsimpledev/ProyectoSMSGateway/assets/120734027/34179610-bcad-4683-a43b-d1d0ca8ad0c0)

<h3>7) Ir a Firebase, descargar el archivo google-services.json y colocarlo en la carpeta app (En el video se explica cómo descargar este archivo)</h3>

![smsgateway_androidstudio7](https://github.com/unsimpledev/ProyectoSMSGateway/assets/120734027/0808669e-3210-4f36-92db-c9ad1187e810)

<h3>8) Ir a Build -> Rebuild Project</h3>

  ![smsgateway_androidstudio5](https://github.com/unsimpledev/ProyectoSMSGateway/assets/120734027/94f8d102-fa57-434b-8f6e-22532faf5264)

<h3>9) Se puede ver si está ok</h3>

  ![smsgateway_androidstudio8](https://github.com/unsimpledev/ProyectoSMSGateway/assets/120734027/d7d15649-4054-46fc-8011-46ddda8535d2)

<h2>BackEnd - Paso a Paso</h2>

<h3>1) Ejecutar el script de la base de datos - crear el usuario</h3>

![db1](https://github.com/unsimpledev/ProyectoSMSGateway/assets/120734027/261ca8e6-7484-4eb9-aa74-4b8f0658c2e9)

<h3>2) Subir la carpeta SMSGatewayBE al servidor</h3>

![be1](https://github.com/unsimpledev/ProyectoSMSGateway/assets/120734027/9b16138c-467d-43be-91a4-c10fd0556393)

<h3>3) Ajustar los valores de las constantes de configuración para el entorno</h3>

![be2](https://github.com/unsimpledev/ProyectoSMSGateway/assets/120734027/ce8d87e4-deab-47a4-b96a-b1ea2ce9ddc4)


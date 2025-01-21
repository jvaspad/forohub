# ForoHub

Este proyecto es parte de un *Challenge* de Alura Latam donde se debe crear un foro utilizando IntelliJ IDEA, Spring Initializr, MySQL e Insomnia. Este *Challenge* pone en práctica lo que hemos aprendido sobre Java.

## Preparación

Para ejecutar la aplicación, primero se necesita IntelliJ IDEA para ejecutar el código, MySQL para crear la base de datos donde se guardarán los datos del foro e Insomnia para poder interactuar con la aplicación. Al instalar MySQL, se creará un usuario (que por defecto será "root") y una contraseña. Además, se ejecutará en un puerto específico, que en mi caso es el 3306. En el archivo "application.properties", habrá que reemplazar el puerto y la contraseña si es necesario. Además, habrá que crear la base de datos. En MySQL, hay que ejecutar "CREATE DATABASE forohub".

## Ejecución

En IntelliJ IDEA, se debe ejecutar el archivo "ForoHubApplication". Después, en Insomnia, habrá que ir a "http://localhost:8080/login" para iniciar sesion con un usuario creado en MySQL para poder utilizar el foro. 

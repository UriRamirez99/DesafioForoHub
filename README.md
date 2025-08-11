# ForoHub - API REST
Descripción del proyecto
ForoHub es una API REST diseñada para ser el backend de un foro de discusión. Permite a los usuarios crear, leer, actualizar y eliminar tópicos de conversación. La API está construida con Spring Boot 3 y sigue los principios de una arquitectura RESTful, asegurando la comunicación segura y escalable a través de JSON Web Tokens (JWT).

## 💻 - Características principales
*API RESTful:* Endpoints bien definidos para una fácil integración.

*Seguridad con JWT:* Autenticación de usuarios y protección de rutas con tokens JWT.

*CRUD completo de Tópicos:* Funcionalidad para gestionar tópicos de discusión.

*Validación de Datos:* Uso de validaciones de Jakarta Bean Validation para asegurar la integridad de los datos de entrada.

*Persistencia de Datos:* Gestión de datos con JPA y MySQL.

*Migraciones de base de datos:* Manejo de la evolución del esquema de la base de datos con Flyway.

### 🚀 Tecnologías utilizadas
Java 21

Spring Boot 3

Spring Security

JPA (Jakarta Persistence API)

MySQL

Flyway

Auth0 java-jwt

Lombok

Jakarta Validation

### ⚙️ Configuración del entorno
Para ejecutar este proyecto localmente, necesitas tener instalado:

Java 21 JDK o superior

Maven 3.x

MySQL

Un IDE (como IntelliJ IDEA o Visual Studio Code)

### Clonar el repositorio:

git clone https://github.com/UriRamirez99/DesafioForoHub.git

### Configurar la base de datos:

- Crea una base de datos MySQL llamada forohub.

- Actualiza el archivo src/main/resources/application.properties con tus credenciales de MySQL.

### Configurar la seguridad:

Asegúrate de tener la siguiente línea en application.properties para la clave secreta del JWT:

api.security.token.secret=${JWT_SECRET:12345678}

Ejecutar el proyecto:
Puedes usar tu IDE para ejecutar la clase principal o usar Maven desde la terminal:

mvn spring-boot:run

## 🔑 Endpoints de la API (Para ejecutar las request se requiere usar INSOMNIA o POSTMAN):


*POST* --- */login*

--> Inicia sesión y genera un token JWT.
  

*POST* --- */topicos*

--> Crea un nuevo tópico de discusión. Requiere autenticación.
  

*GET* --- */topicos*

--> Obtiene una lista de todos los tópicos. Requiere autenticación.
  

*GET* --- */topicos/{id}*

--> Obtiene un tópico específico por ID. Requiere autenticación.
  

*PUT* --- */topicos/{id}*

--> Actualiza un tópico existente. Requiere autenticación.
  

*DELETE* --- */topicos/{id}*

--> Elimina un tópico por ID. Requiere autenticación.
  

## 🛡️ Autenticación
Todas las rutas, excepto /login, están protegidas. Para acceder a ellas, debes incluir un encabezado Authorization con un token JWT válido.

Ejemplo de encabezado de autenticación:
Authorization: Bearer <TU_TOKEN_JWT_AQUÍ>

## 📄 Autor
_Uriel Ramirez_ 

# AYB API - GA7-220501096-AA5-EV03

## Descripción

Este proyecto corresponde a la evidencia **GA7-220501096-AA5-EV03 - Diseño y desarrollo de servicios web – proyecto** del programa de Análisis y Desarrollo de Software del SENA.

La API fue desarrollada para apoyar el proyecto formativo de la **Tienda de Ropa AYB**. Incluye servicios REST para autenticación de usuarios y gestión de productos.

## Tecnologías utilizadas

- Java 17
- Spring Boot 3.4.5
- Spring Web
- Spring Data JPA
- Spring Validation
- Spring Security Crypto (BCrypt)
- H2 Database
- Maven
- Git / GitHub para versionamiento
- Postman para pruebas

## Servicios principales

### Autenticación

- `POST /api/auth/registro` - registra un usuario.
- `POST /api/auth/login` - valida usuario y contraseña.

### Productos

- `GET /api/productos` - consulta todos los productos.
- `GET /api/productos/{id}` - consulta un producto por ID.
- `GET /api/productos?categoria=Camisa` - filtra por categoría.
- `POST /api/productos` - crea un producto.
- `PUT /api/productos/{id}` - actualiza un producto.
- `DELETE /api/productos/{id}` - elimina un producto.

## Ejemplo de producto

```json
{
  "nombre": "Camiseta básica",
  "categoria": "Camisetas",
  "talla": "M",
  "color": "Blanco",
  "precio": 45000,
  "stock": 10,
  "descripcion": "Camiseta básica de algodón para dama."
}
```

## Ejemplo de autenticación

```json
{
  "usuario": "alison",
  "password": "123456"
}
```

## Ejecución

1. Abrir el proyecto en Visual Studio Code.
2. Verificar Java 17 y Maven.
3. Ejecutar `mvn clean test`.
4. Ejecutar `mvn spring-boot:run`.
5. Probar los endpoints desde Postman usando `http://localhost:8080`.

## Base de datos

Se utiliza H2 en modo archivo:

`jdbc:h2:file:./data/aybdb`

La consola está disponible en:

`http://localhost:8080/h2-console`

## Documentación

La documentación detallada de cada servicio se encuentra en `docs/DOCUMENTACION_API.md`.

## Versionamiento

El proyecto está preparado para ser gestionado con Git y publicado en GitHub. El enlace del repositorio se encuentra en `REPOSITORIO.txt`.

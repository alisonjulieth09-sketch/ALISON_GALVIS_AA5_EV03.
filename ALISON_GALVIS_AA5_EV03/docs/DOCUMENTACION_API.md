# Documentación de servicios web - AYB API

**Evidencia:** GA7-220501096-AA5-EV03  
**Proyecto:** Sistema de gestión para la Tienda de Ropa AYB  
**Tecnología:** Java 17 / Spring Boot 3.4.5

## 1. Objetivo

Diseñar y desarrollar servicios web REST que permitan realizar operaciones relacionadas con los usuarios y los productos de la Tienda de Ropa AYB.

## 2. URL base

```text
http://localhost:8080
```

## 3. Servicio de registro

**Método:** POST  
**Ruta:** `/api/auth/registro`

### Descripción
Registra un nuevo usuario y almacena la contraseña utilizando BCrypt.

### Body
```json
{
  "usuario": "alison",
  "password": "123456"
}
```

### Respuesta exitosa - 201
```json
{
  "success": true,
  "message": "Registro realizado correctamente"
}
```

### Respuesta si el usuario existe - 409
```json
{
  "success": false,
  "message": "El usuario ya está registrado"
}
```

## 4. Servicio de inicio de sesión

**Método:** POST  
**Ruta:** `/api/auth/login`

### Body
```json
{
  "usuario": "alison",
  "password": "123456"
}
```

### Respuesta exitosa - 200
```json
{
  "success": true,
  "message": "Autenticación satisfactoria"
}
```

### Respuesta con credenciales incorrectas - 401
```json
{
  "success": false,
  "message": "Error en la autenticación"
}
```

## 5. Consultar productos

**Método:** GET  
**Ruta:** `/api/productos`

### Descripción
Devuelve la lista de productos registrados.

### Respuesta - 200
```json
[
  {
    "id": 1,
    "nombre": "Camiseta básica",
    "categoria": "Camisetas",
    "talla": "M",
    "color": "Blanco",
    "precio": 45000,
    "stock": 10,
    "descripcion": "Camiseta básica de algodón para dama."
  }
]
```

## 6. Consultar producto por ID

**Método:** GET  
**Ruta:** `/api/productos/{id}`

Ejemplo:

```text
GET http://localhost:8080/api/productos/1
```

Si el producto no existe, la API responde con estado 404.

## 7. Filtrar productos por categoría

**Método:** GET  
**Ruta:** `/api/productos?categoria={categoria}`

Ejemplo:

```text
GET http://localhost:8080/api/productos?categoria=Camisetas
```

## 8. Crear producto

**Método:** POST  
**Ruta:** `/api/productos`

### Body
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

### Respuesta - 201
La API devuelve el producto creado incluyendo su ID.

## 9. Actualizar producto

**Método:** PUT  
**Ruta:** `/api/productos/{id}`

Ejemplo:

```text
PUT http://localhost:8080/api/productos/1
```

### Body
```json
{
  "nombre": "Camiseta básica actualizada",
  "categoria": "Camisetas",
  "talla": "L",
  "color": "Negro",
  "precio": 50000,
  "stock": 8,
  "descripcion": "Camiseta de algodón actualizada."
}
```

## 10. Eliminar producto

**Método:** DELETE  
**Ruta:** `/api/productos/{id}`

Ejemplo:

```text
DELETE http://localhost:8080/api/productos/1
```

La respuesta exitosa es `204 No Content`.

## 11. Validaciones

Los servicios de productos validan:

- Nombre obligatorio.
- Categoría obligatoria.
- Precio mayor que cero.
- Stock igual o mayor que cero.
- Longitud máxima de los campos de texto.

Cuando los datos no cumplen las reglas, la API devuelve `400 Bad Request` con los errores de validación.

## 12. Arquitectura

La aplicación utiliza una estructura por capas:

```text
Controller -> Service -> Repository -> Base de datos H2
```

- **Controller:** recibe las peticiones HTTP.
- **Service:** contiene la lógica del negocio.
- **Repository:** realiza el acceso a los datos mediante JPA.
- **Model:** representa las entidades Usuario y Producto.
- **DTO:** transporta los datos de autenticación.
- **Exception:** maneja errores y validaciones.

## 13. Pruebas sugeridas en Postman

1. Registrar usuario.
2. Iniciar sesión con datos correctos.
3. Iniciar sesión con contraseña incorrecta.
4. Crear producto.
5. Consultar productos.
6. Consultar producto por ID.
7. Filtrar por categoría.
8. Actualizar producto.
9. Eliminar producto.
10. Enviar un producto con datos inválidos para comprobar la validación.

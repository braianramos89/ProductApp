# API de Productos con Spring Security

## Descripción
Esta API permite la gestión de productos a través de operaciones CRUD, con seguridad implementada mediante Spring Security y JWT. Los endpoints están protegidos según los roles de los usuarios.

## Características
- Autenticación basada en JWT.
- Roles: `admin` y `user`.
- Acceso controlado a los endpoints basado en los roles.
- Implementación de refresh tokens.

## Endpoints

| Método | Ruta                  | Descripción                         | Rol Requerido |
|--------|------------------------|-------------------------------------|---------------|
| GET    | /productos              | Obtener todos los productos         | Todos         |
| GET    | /productos/{id}         | Obtener un producto por ID          | Todos         |
| POST   | /productos              | Crear un nuevo producto             | Admin         |
| PUT    | /productos/{id}         | Actualizar un producto existente    | Admin         |
| DELETE | /productos/{id}         | Eliminar un producto                | Admin         |

## Configuración de Seguridad
- Autenticación mediante usuario y contraseña con JWT.
- Validación de JWT en cada solicitud.
- Sistema de refresh tokens para renovar el token de acceso.

## Pruebas
Pruebas realizadas con Postman para verificar el acceso a rutas protegidas y no protegidas.

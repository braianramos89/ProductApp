package ar.edu.undef.fie.productApp.controllers;



import ar.edu.undef.fie.productApp.dto.UserRequest;
import ar.edu.undef.fie.productApp.services.IKeycloakService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;


@RestController
@RequestMapping("/api/v1/keycloak/users")
@PreAuthorize("hasRole('admin_client_role')")
public class KeycloakController {

    @Autowired
    private IKeycloakService keycloakService;


    @Operation(summary = "Listar todos los usuarios")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Listado de usuarios"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Error al listar usuarios")
    })
    @GetMapping
    public ResponseEntity<?> findAllUsers(){
        return ResponseEntity.ok(keycloakService.findAllUsers());
    }

    @Operation(summary = "Buscar usuario por username")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Usuario")
    })

    @GetMapping("/{username}")
    public ResponseEntity<?> searchUserByUsername(@Parameter(description = "Nombre de usuario", example = "user1")
                                                      @PathVariable String username){
        return ResponseEntity.ok(keycloakService.searchUserByUsername(username));
    }

    @Operation(summary = "Crear un usuario")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Usuario")
    })
    @PostMapping
    public ResponseEntity<?> createUser(@Parameter(description = "Datos del usuario",
                                        example = "{ \"username\": \"user1\", \"email\": \"user1@example.com\"}")
                                            @RequestBody UserRequest userDTO) throws URISyntaxException {
        String response = keycloakService.createUser(userDTO);
        return ResponseEntity.created(new URI("/keycloak/user")).body(response);
    }

    @Operation(summary = "Actualizar un usuario")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Usuario actualizado")
    })
    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@Parameter(description = "ID del usuario", example = "1")
                                            @PathVariable String userId, @RequestBody UserRequest userDTO){
        keycloakService.updateUser(userId, userDTO);
        return ResponseEntity.ok("User updated successfully");
    }


    @Operation(summary = "Eliminar un usuario")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "Usuario eliminado")
    })
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@Parameter(description = "ID del usuario", example = "1")
                                            @PathVariable String userId){
        keycloakService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
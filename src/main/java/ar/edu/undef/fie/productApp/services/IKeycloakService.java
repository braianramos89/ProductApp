package ar.edu.undef.fie.productApp.services;



import ar.edu.undef.fie.productApp.dto.UserRequest;
import org.keycloak.representations.idm.UserRepresentation;

import java.security.Principal;
import java.util.List;

public interface IKeycloakService {


    List<UserRepresentation> findAllUsers();
    List<org.keycloak.representations.idm.UserRepresentation> searchUserByUsername(String username);
    String createUser(UserRequest userDTO);
    void deleteUser(String userId);
    void updateUser(String userId, UserRequest userDTO);

    String getEmailFromToken(Principal principal);
    String getUsernameFromToken(Principal principal);

}

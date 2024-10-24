package ar.edu.undef.fie.productApp.utils;

import io.github.cdimascio.dotenv.Dotenv;
import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;

public class KeycloakProvider {



    // Carga las variables del archivo .env
    private static final Dotenv dotenv = Dotenv.load();

    private static final String SERVER_URL = dotenv.get("KEYCLOAK_SERVER_URL");
    private static final String REALM_NAME = dotenv.get("KEYCLOAK_REALM_NAME");
    private static final String REALM_MASTER = dotenv.get("KEYCLOAK_REALM_MASTER");
    private static final String ADMIN_CLI = dotenv.get("KEYCLOAK_ADMIN_CLI");
    private static final String USER_CONSOLE = dotenv.get("KEYCLOAK_USER_CONSOLE");
    private static final String PASSWORD_CONSOLE = dotenv.get("KEYCLOAK_PASSWORD_CONSOLE");
    private static final String CLIENT_SECRET = dotenv.get("KEYCLOAK_CLIENT_SECRET");



    public static RealmResource getRealmResource() {
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(SERVER_URL)
                .realm(REALM_MASTER)
                .clientId(ADMIN_CLI)
                .username(USER_CONSOLE)
                .password(PASSWORD_CONSOLE)
                .clientSecret(CLIENT_SECRET)
                .resteasyClient(new ResteasyClientBuilderImpl()
                        .connectionPoolSize(10)
                        .build())
                .build();

        return keycloak.realm(REALM_NAME);
    }

    public static UsersResource getUserResource() {
        RealmResource realmResource = getRealmResource();
        return realmResource.users();
    }

}

package parent.service;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.context.annotation.PropertySource;
import parent.exceptions.BadConfigurationException;

import javax.ws.rs.core.Response;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

@PropertySource("./")
public class CloakConfig {

//    private Properties properties;
//    static{
//        System.setProperty("conf.file","src/java/resources/application.properties");
//    }

    private String userName;
    private String firstName;
    private String lastName;
    private String email;

    public CloakConfig(String userName, String firstName, String lastName, String email) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public void getKeyCloakConfig() {
        String serverUrl = "http://localhost:8080/auth/";
        String realm = "test-netflix";
        // idm-client needs to allow "Direct Access Grants: Resource Owner Password Credentials Grant"
        String clientId = "test";
        String clientUserName = "anshul";
        String clientPassword = "anshul";
//        String clientSecret = "fEuzpwwtSXNNqs9QWxmZ2nLfe2HAIySq";

//        Keycloak keycloak = KeycloakBuilder.builder()
//                .serverUrl(serverUrl)
//                .realm(realm)
//                .grantType(OAuth2Constants.PASSWORD)
//                .clientId(clientId).clientSecret(clientSecret)
//                .resteasyClient(new ResteasyClientBuilder()
//                        .connectionPoolSize(10).build())
//                .build();

        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(realm)
                .grantType(OAuth2Constants.PASSWORD)
                .clientId(clientId)
                .username(clientUserName)
                .password(clientPassword)
                .resteasyClient(new ResteasyClientBuilder()
                        .connectionPoolSize(10).build())
                .build();

        // Define user
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(this.userName);
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setEmail(this.email);
        user.setAttributes(Collections.singletonMap("origin", Arrays.asList("demo")));

        // Get realm
        RealmResource realmResource = keycloak.realm(realm);
        UsersResource usersRessource = realmResource.users();

        // Create user (requires manage-users role)
        Response response = usersRessource.create(user);
        System.out.printf("Repsonse: %s %s%n", response.getStatus(), response.getStatusInfo());
        System.out.println(response.getLocation());
        String userId = CreatedResponseUtil.getCreatedId(response);

        System.out.printf("User created with userId: %s%n", userId);

        // Define password credential
        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue("anshul");

        UserResource userResource = usersRessource.get(userId);

        // Set password credential
        userResource.resetPassword(passwordCred);
    }
}


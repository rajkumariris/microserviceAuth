package dev.raj.authproject;

import dev.raj.authproject.SpringSecurity.Models.Client;
import dev.raj.authproject.SpringSecurity.Repositories.ClientRepository;
import dev.raj.authproject.SpringSecurity.Repositories.JpaRegisteredClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

import java.util.UUID;

@SpringBootTest
public class RegisterClientTest {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JpaRegisteredClientRepository clientRepository;
    @Test
    public void registeredClientRepository() {

        RegisteredClient postmanClient = RegisteredClient.withId(UUID.randomUUID().toString())
                    .clientId("rajkumar")
                    .clientSecret(passwordEncoder.encode("rajkumarpassword"))
                    .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                    .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                    .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                    .redirectUri("http://oauth2.pstmn.io/v1/callback")
                    .postLogoutRedirectUri("http://127.0.0.1:9000/")
                    .scope(OidcScopes.OPENID)
                    .scope(OidcScopes.PROFILE)
                    .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                    .build();
      clientRepository.save(postmanClient);
//            return new InMemoryRegisteredClientRepository(oidcClient);
    }
}

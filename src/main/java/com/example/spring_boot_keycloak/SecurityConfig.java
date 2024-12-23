/*
 * This is a Spring Boot security configuration file that manages authentication and authorization.
 * This class uses OIDC via Keycloak for user authentication where only authenticated users can access any request apart from '/public/**' endpoints.
 * It handles redirection to Keycloak's login page on OAuth2 login, redirects to home page after successful login, and establishes logout handling for OIDC.
 * The handlers are injected by Spring as Beans.
 * It also handles the OIDC logout scenario providing a redirection after successful logout.
 */
package com.example.spring_boot_keycloak;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@EnableWebSecurity
@Component
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, ClientRegistrationRepository clientRegistrationRepository) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/public/**").permitAll() // Public paths
                                .anyRequest().authenticated() // Protect all other routes
                )
                // .oauth2Login(Customizer.withDefaults()) // Enables OAuth2 login
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/oauth2/authorization/keycloak")  // Redirects to Keycloak login page
                        .defaultSuccessUrl("/home", true)             // Redirect to homepage after successful login
                )
                .logout(logout -> logout
                        .logoutSuccessHandler(oidcLogoutSuccessHandler(clientRegistrationRepository)) // Handle OIDC logout
                );

        return http.build();
    }

    // OIDC logout handler
    private OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler(ClientRegistrationRepository clientRegistrationRepository) {
        OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler =
                new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);
        oidcLogoutSuccessHandler.setPostLogoutRedirectUri("{baseUrl}/");
        return oidcLogoutSuccessHandler;
    }
}

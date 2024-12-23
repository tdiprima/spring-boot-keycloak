/*
This is a Spring Boot controller class for managing the '/home' endpoint. It uses OpenID Connect (OIDC) for authentication.

On a GET request to '/home', it checks if the user is authenticated. If so, it retrieves the user's full name from the OIDC user object and adds it to the model that will be used by the view. If the OIDC user is null, indicating that the user is not authenticated, it prints a message "User is null". 

The method returns the string 'home' which is the name of the view (most likely a Thymeleaf template named 'home.html' located in 'src/main/resources/templates') that will be used to render the response.
*/
package com.example.spring_boot_keycloak;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model, @AuthenticationPrincipal OidcUser oidcUser) {
        // Ensure the user is authenticated before accessing the homepage
        if (oidcUser != null) {
            String userName = oidcUser.getFullName(); // or oidcUser.getPreferredUsername();
            model.addAttribute("userName", userName);
        } else {
            System.out.println("User is null");
        }
        return "home";  // Return view name (home.html in src/main/resources/templates)
    }
}

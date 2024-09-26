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

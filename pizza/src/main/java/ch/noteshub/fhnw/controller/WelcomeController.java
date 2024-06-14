package ch.noteshub.fhnw.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ch.noteshub.fhnw.security.CustomUserDetails;
import java.util.Collection;

import io.swagger.v3.oas.annotations.Hidden;

@RestController
@Hidden // Hide this controller from the Swagger UI
public class WelcomeController {

    @GetMapping(value="/")
    public String getWelcomeString() {
        
        return "Hello, welcome to our noteshub application!";
    }

    @GetMapping(value="/user")
    public String getUserRoleAndID(Authentication auth) {
        System.out.println(auth);  // Log the full authentication object
        if (auth == null || !auth.isAuthenticated() || auth instanceof AnonymousAuthenticationToken) {
            return "No authenticated user";
        }
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        Collection<?> authorities = userDetails.getAuthorities();
        if (!authorities.isEmpty()) {
            String username = userDetails.getUsername();
            String ID = userDetails.getUserId().toString();
            return username + ";" + ID;
        } else {
            return "No roles found";
        }
    }}
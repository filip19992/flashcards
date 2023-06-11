package com.flashcards.flashcards;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
public class LoginController {

    private final UserDetailsService userDetailsService;

    // JWT secret key
    @Value("${jwt.secret}")
    private String jwtSecret;

    // JWT expiration time (in milliseconds)
    @Value("${jwt.expiration}")
    private long jwtExpiration;

    public LoginController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginForm loginForm, HttpServletResponse response) {
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginForm.getUsername());

            String authToken = generateToken(userDetails.getUsername());

            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("message", "Login successful");

            response.setHeader("Access-Control-Expose-Headers", "Authorization"); // Add this line

            return ResponseEntity.ok()
                    .header("Authorization", authToken)
                    .body(jsonResponse.toString());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
        }
    }

    private static ResponseEntity<Object> getAuthorization(String authToken, JSONObject jsonResponse) {
        return ResponseEntity.ok().header("Authorization", "Bearer " + authToken).body(jsonResponse.toString());
    }

    private String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
}

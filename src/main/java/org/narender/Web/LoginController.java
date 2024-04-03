package org.narender.Web;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;

@RestController
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    // Secret for signing JWT - in a real application, store this securely!
    private static final String SECRET = "mySecretKey";

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request){
        logger.info("Request received for /login - {}", request.toString());

        // Here, insert your logic to validate the username and password.
        // This example assumes validation is successful.
        boolean isAuthenticated = authenticate(request.getUsername(), request.getPassword());

        LoginResponse response = new LoginResponse();
        if (isAuthenticated) {
            String token = generateJwtToken(request.getUsername());
            response.setToken(token);
        } else {
            // Handle authentication failure case
            // For simplicity, we're just returning a response with no token.
            response.setToken("Authentication failed");
        }

        return response;
    }

    private boolean authenticate(String username, String password) {
        // Insert your authentication logic here.
        // This is a placeholder that always returns true.
        return true; // Always authenticates for demonstration purposes.
    }

    private String generateJwtToken(String username) {
        // Specify the token expiration time
        Date expiryDate = new Date(System.currentTimeMillis() + 3600_000); // 1 hour

        // Create and sign the token
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(expiryDate)
                .sign(Algorithm.HMAC512(SECRET));
    }
}

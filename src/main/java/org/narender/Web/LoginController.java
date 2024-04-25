package org.narender.Web;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.narender.DOA.UserManagerWithJDBC;
import org.narender.Objects.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        logger.info("Request received for /login - {}", request.toString());

        ResponseEntity<LoginResponse> responseEntity = new ResponseEntity<>(new LoginResponse(), HttpStatus.CREATED);

        // Here, insert your logic to validate the username and password.
        // This example assumes validation is successful.
        User user = authenticate(request.getUsername(), request.getPassword());

        LoginResponse response = new LoginResponse();
        if (user != null) {
            String token = generateJwtToken(user.getUsername(), user.getFirstName(), user.getEmail());

            response.setToken(token);
            //responseEntity.ok(response);

            return new ResponseEntity<>(response, HttpStatus.OK);


        } else {
            // Handle authentication failure case
            // For simplicity, we're just returning a response with no token.
            //response.setToken("Authentication failed");
            response.setToken("Authentication failed");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);

        }

    }

    private User authenticate(String username, String password) {
        // Insert your authentication logic here.
        // This is a placeholder that always returns true.
        // In a real application, this would be where you would check
        // the username and password against a database or other
        // authentication mechanism.
        //
        // For now, just return true to indicate successful authentication.

        User user = UserManagerWithJDBC.verifyUser(username, password);

        return user;// Always authenticates for demonstration purposes.
    }

    private String generateJwtToken(String username,String firstName, String email) {
        // Specify the token expiration time
        Date expiryDate = new Date(System.currentTimeMillis() + 3600_000); // 1 hour

        // Create and sign the token
        return JWT.create()
                .withClaim("username", username)
                .withClaim("firstName", firstName)
                .withClaim("email", email)
                .withExpiresAt(expiryDate)
                .sign(Algorithm.HMAC512(SECRET));
    }
}

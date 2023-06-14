package com.example.ims.Controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.ims.Model.User;
import com.example.ims.Service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin
@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User loginRequest, HttpServletResponse response) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
    
        // Validate the username and password
        boolean isValidUser = userService.validateUser(username, password);
    
        if (isValidUser) {
            // Generate a unique session ID
            String sessionId = userService.generateSessionId();
    
            // Set the session ID as a cookie in the response
            Cookie cookie = new Cookie("sessionID", sessionId);
            cookie.setMaxAge(3600); // Set the cookie expiration time in seconds (e.g., 1 hour)
            cookie.setPath("/"); // Set the cookie path
            response.addCookie(cookie);
    
            // Return the session ID in the response body
            return ResponseEntity.ok("Login successful. Session ID: " + sessionId);
        } else {
            // Return an error message or appropriate response
            return ResponseEntity.badRequest().body("Invalid username or password");
        }
    }
    

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    @PostMapping("/user")
    public String createUser(@RequestBody User user) {
        userService.createUser(user);
        return "User created successfully";
    }
}

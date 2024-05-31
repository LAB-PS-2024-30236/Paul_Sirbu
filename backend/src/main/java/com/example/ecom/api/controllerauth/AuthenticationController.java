package com.example.ecom.api.controllerauth;


import com.example.ecom.api.model.LoginBody;
import com.example.ecom.api.model.LoginResponse;
import com.example.ecom.api.model.RegistrationBody;
import com.example.ecom.api.model.Session;
import com.example.ecom.api.model.SessionRepository;
import com.example.ecom.authservice.UserService;
import com.example.ecom.exception.UserAlreadyExistsException;
import com.example.ecom.model.LocalUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Rest Controller for handling authentication requests.
 */@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthenticationController {

    private UserService userService;
    private SessionRepository sessionRepository;

    @Autowired
    public AuthenticationController(UserService userService, SessionRepository sessionRepository) {
        this.userService = userService;
        this.sessionRepository = sessionRepository;
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@Valid @RequestBody RegistrationBody registrationBody) {
        try {
            userService.registerUser(registrationBody);
            return ResponseEntity.ok().build();
        } catch (UserAlreadyExistsException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LocalUser> loginUser(@Valid @RequestBody LoginBody loginBody) {
        var user = userService.loginUser(loginBody);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            Session session = new Session();
            session.setUserId(user.getId());
            session.setTimestamp(LocalDateTime.now());
            sessionRepository.save(session);
            return ResponseEntity.ok(user);
        }
    }


    @GetMapping("/me")
    public LocalUser getLoggedInUserProfile(@AuthenticationPrincipal LocalUser user) {
        return user;
    }
}
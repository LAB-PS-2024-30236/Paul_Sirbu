package com.example.ecom.authservice;

import com.example.ecom.api.model.LoginBody;
import com.example.ecom.api.model.RegistrationBody;
import com.example.ecom.exception.UserAlreadyExistsException;
import com.example.ecom.model.LocalUser;
import com.example.ecom.model.dao.LocalUserDAO;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import java.math.BigInteger;
import java.util.Optional;
import java.util.UUID;

/**
 * Service for handling user actions.
 */
@Service
public class UserService {

    /** The LocalUserDAO. */
    private LocalUserDAO localUserDAO;
    private EncryptionService encryptionService;
    private JWTService jwtService;

    /**
     * Constructor injected by spring.
     *
     * @param localUserDAO
     * @param encryptionService
     * @param jwtService
     */
    public UserService(LocalUserDAO localUserDAO, EncryptionService encryptionService, JWTService jwtService) {
        this.localUserDAO = localUserDAO;
        this.encryptionService = encryptionService;
        this.jwtService = jwtService;
    }

    /**
     * Attempts to register a user given the information provided.
     * @param registrationBody The registration information.
     * @return The local user that has been written to the database.
     * @throws UserAlreadyExistsException Thrown if there is already a user with the given information.
     */
    public LocalUser registerUser(RegistrationBody registrationBody) throws UserAlreadyExistsException {
        if (localUserDAO.findByEmailIgnoreCase(registrationBody.getEmail()).isPresent()
                || localUserDAO.findByUsernameIgnoreCase(registrationBody.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        LocalUser user = new LocalUser();
        user.setEmail(registrationBody.getEmail());
        user.setUsername(registrationBody.getUsername());
        user.setFirstName(registrationBody.getFirstName());
        user.setLastName(registrationBody.getLastName());
        user.setPassword(encryptionService.encryptPassword(registrationBody.getPassword()));
        return localUserDAO.save(user);
    }

    /**
     * Logins in a user and provides an authentication token back.
     * @param loginBody The login request.
     * @return The authentication token. Null if the request was invalid.
     */
    public LocalUser loginUser(LoginBody loginBody) {
        Optional<LocalUser> opUser = localUserDAO.findByUsernameIgnoreCase(loginBody.getUsername());
        if (opUser.isPresent()) {
            LocalUser user = opUser.get();
            if (loginBody.getPassword().equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }



}

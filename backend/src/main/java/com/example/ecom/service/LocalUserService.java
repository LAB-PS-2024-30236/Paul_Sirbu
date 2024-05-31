package com.example.ecom.service;

import com.example.ecom.model.LocalUser;
import com.example.ecom.repository.LocalUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocalUserService {
    @Autowired
    private LocalUserRepository localUserRepository;

    public LocalUser saveUser(LocalUser user) {

        return localUserRepository.save(user);
    }

    public List<LocalUser> getAllUsers() {
        return localUserRepository.findAll();
    }

    public Optional<LocalUser> getUserById(Long userId) {
        return localUserRepository.findById(userId);
    }

    public LocalUser updateUser(Long userId, LocalUser updatedUser) {
        Optional<LocalUser> existingUserOptional = localUserRepository.findById(userId);
        if (existingUserOptional.isPresent()) {
            LocalUser existingUser = existingUserOptional.get();
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getLastName());

            return localUserRepository.save(existingUser);
        } else {
            // Handle user not found scenario
            return null;
        }
    }

    public void deleteUser(Long userId) {
        localUserRepository.deleteById(userId);
    }

}

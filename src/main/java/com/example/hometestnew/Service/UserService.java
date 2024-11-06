package com.example.hometestnew.Service;

import com.example.hometestnew.models.Service; // Import the Service model
import com.example.hometestnew.models.UpdateProfileRequest;
import com.example.hometestnew.models.User;
import com.example.hometestnew.repository.ServiceRepository; // Assuming you have a ServiceRepository to interact with the database
import com.example.hometestnew.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {

    @Autowired
    private UserRepository userRepository; // User repository for database interaction

    @Autowired
    private ServiceRepository serviceRepository; // Service repository for fetching services

    // Define a method to get the default service (if there's a concept of a default service)
    private Service getDefaultService() {
        // For example, get the first service from the database
        return serviceRepository.findAll().stream().findFirst()
                .orElseThrow(() -> new RuntimeException("Default service not found"));
    }

    public User updateUserProfile(String email, UpdateProfileRequest updateProfileRequest) {
        // Find the user by email
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        // Fetch or use the default service
        Service defaultService = getDefaultService();

        // Update user fields
        user.setFirstName(updateProfileRequest.getFirstName());
        user.setLastName(updateProfileRequest.getLastName());
        user.setProfileImage(updateProfileRequest.getProfileImage());
        user.setService(defaultService);  // Assign the default service to the user

        return userRepository.save(user); // Save and return the updated user
    }
}

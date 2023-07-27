package com.codingdojo.ideasite.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.ideasite.models.LoginUser;
import com.codingdojo.ideasite.models.User;
import com.codingdojo.ideasite.repositories.UserRepository;
    
@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepo;
    
    public UserService(UserRepository userRepository) {
    	this.userRepo = userRepository;
    }
    
    public List<User> allUsers() {
    	return userRepo.findAll();
    }
    
    public User findUser(Long id) {
    	Optional<User> userOptional = userRepo.findById(id);
    	return userOptional.orElse(null);
    }
    
    public User register(User newUser, BindingResult result) {
    	if (userRepo.existsByEmail(newUser.getEmail())) {
            result.rejectValue("email", "Duplicate", "Email already exists");
            return null;
        }
    	
        if (!newUser.getPassword().equals(newUser.getConfirm())) {
            result.rejectValue("confirm", "Match", "Password confirmation must match the password");
        }
        
        if (result.hasErrors()) {
            return null;
        }
        
        String hashedPassword = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
        newUser.setPassword(hashedPassword);

        User savedUser = userRepo.save(newUser);

        return savedUser;
    }
    
    public User login(LoginUser newLoginObject, BindingResult result) {
        Optional<User> userOptional = userRepo.findByEmail(newLoginObject.getEmail());
        if (userOptional.isEmpty()) {
            result.rejectValue("email", "NotFound", "User not found");
            return null;
        }
        
        User user = userOptional.get();
        
        if (!BCrypt.checkpw(newLoginObject.getPassword(), user.getPassword())) {
            result.rejectValue("password", "Invalid", "Invalid password");
            return null;
        }
        
        if (result.hasErrors()) {
            return null;
        }
        
        return user;
    }
}


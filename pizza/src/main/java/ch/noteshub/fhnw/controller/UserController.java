package ch.noteshub.fhnw.controller;

import ch.noteshub.fhnw.data.domain.Favorite;
import ch.noteshub.fhnw.data.domain.User;
import ch.noteshub.fhnw.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder; }

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/me")
    public ResponseEntity<Map<String, Object>> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUserUsername(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        Map<String, Object> response = new HashMap<>();
        response.put("id", user.getUserId());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{id}/favorites")
    public ResponseEntity<Set<Favorite>> getFavByUser(@PathVariable Long id){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get().getFavorites());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    
    

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User userToUpdate = user.get();
            userToUpdate.setUserFirstname(userDetails.getUserFirstname());
            userToUpdate.setUserLastname(userDetails.getUserLastname());
            userToUpdate.setUserEmail(userDetails.getUserEmail());
            userToUpdate.setUserUsername(userDetails.getUserUsername());
            userToUpdate.setUserPassword(userDetails.getUserPassword());
            return ResponseEntity.ok(userRepository.save(userToUpdate));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PatchMapping("/{id}/username")
public ResponseEntity<User> updateUsername(@PathVariable Long id, @RequestBody Map<String, String> update) {
    Optional<User> user = userRepository.findById(id);
    if (!user.isPresent()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    User userToUpdate = user.get();
    String newUsername = update.get("username");
    if (newUsername != null && !newUsername.isEmpty()) {
        userToUpdate.setUserUsername(newUsername);
        userRepository.save(userToUpdate);
        return ResponseEntity.ok(userToUpdate);
    } else {
        return ResponseEntity.badRequest().body(userToUpdate);
    }
}


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<?> updatePassword(@PathVariable Long id, @RequestBody Map<String, String> passwords) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
    
        User user = userOptional.get();
        String oldPassword = passwords.get("oldPassword");
        String newPassword = passwords.get("newPassword");
    
        // Check if old password is not empty and matches the current password
        if (oldPassword == null || newPassword == null || oldPassword.trim().isEmpty() || newPassword.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Both old and new passwords are required");
        }
    
        // Verify old password
        if (!passwordEncoder.matches(oldPassword, user.getUserPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Old password does not match");
        }
    
        // Encrypt and set the new password
        String encryptedPassword = passwordEncoder.encode(newPassword);
        user.setUserPassword(encryptedPassword);
        userRepository.save(user);
    
        return ResponseEntity.ok().build();
    }
}
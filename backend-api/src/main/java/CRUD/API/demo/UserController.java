package CRUD.API.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(404).body("User with ID " + id + " not found");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody User user) {
        if (userService.emailExists(user.getEmail())) {
            return ResponseEntity.status(400).body("Email already in use.");
        }
        if (userService.usernameExists(user.getUsername())) {
            return ResponseEntity.status(400).body("Username already taken.");
        }
        User savedUser = userService.createUser(user);
        return ResponseEntity.status(201).body(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User loginRequest) {
        Optional<User> user = userService.findByEmail(loginRequest.getEmail());
        if (user.isPresent() && user.get().getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(401).body("Invalid email or password.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody User user) {
        Optional<User> existing = userService.getUserById(id);
        if (existing.isPresent()) {
            User updated = userService.updateUser(id, user);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.status(404).body("User not found.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        Optional<User> existing = userService.getUserById(id);
        if (existing.isPresent()) {
            userService.deleteUser(id);
            return ResponseEntity.ok("User deleted.");
        } else {
            return ResponseEntity.status(404).body("User not found.");
        }
    }
    @PutMapping("/{id}/profile-picture")
    public ResponseEntity<Object> uploadProfilePicture(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        try {
            Optional<User> existing = userService.getUserById(id);
            if (existing.isPresent()) {
                User user = existing.get();
                user.setProfilePicture(file.getBytes());
                userService.updateUser(id, user);
            return ResponseEntity.ok("Profile picture updated.");
        } else {
            return ResponseEntity.status(404).body("User not found.");
        }
    } catch (Exception e) {
        return ResponseEntity.status(500).body("Could not upload image.");
    }
}

    @GetMapping("/{id}/profile-picture")
        public ResponseEntity<byte[]> getProfilePicture(@PathVariable Long id) {
            Optional<User> existing = userService.getUserById(id);
                if (existing.isPresent() && existing.get().getProfilePicture() != null) {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(existing.get().getProfilePicture());
    } else {
        return ResponseEntity.status(404).build();
    }
}
}
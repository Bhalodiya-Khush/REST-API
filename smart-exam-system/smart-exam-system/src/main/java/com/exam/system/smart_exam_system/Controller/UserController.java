package com.exam.system.smart_exam_system.Controller;

import com.exam.system.smart_exam_system.Entity.User;
import com.exam.system.smart_exam_system.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Register User
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {

        User savedUser = userService.registerUser(user);
        return ResponseEntity.ok(savedUser);
    }

    // Get User by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {

        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    // Get User by Email (Login use)
    @GetMapping("/email")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {

        User user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }
}
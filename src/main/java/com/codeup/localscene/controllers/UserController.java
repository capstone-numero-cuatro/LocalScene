package com.codeup.localscene.controllers;

import com.codeup.localscene.models.User;
import com.codeup.localscene.repositories.UserRepository;
import com.codeup.localscene.services.EmailService;
import com.codeup.localscene.services.UserDetailsLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final UserDetailsLoader userDetailsLoader;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserRepository userRepository, EmailService emailService, UserDetailsLoader userDetailsLoader, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.userDetailsLoader = userDetailsLoader;
        this.passwordEncoder = passwordEncoder;
    }
    @Value("${filestack.api.key}")
    private String filestackApiKey;

    @GetMapping("/sign-up")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("filestackKey", filestackApiKey);
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@ModelAttribute User user) {

            // Check if username or email already exists
            User existingUserByUsername = userRepository.findByUsername(user.getUsername());
            User existingUserByEmail = userRepository.findByEmail(user.getEmail());

            if(existingUserByUsername != null) {
                return ResponseEntity.badRequest().body("Error during registration. Username already exists.");
            }

            if(existingUserByEmail != null) {
                return ResponseEntity.badRequest().body("Error during registration. Email already in use.");
            }

            // Registration process
            emailService.registerUser(user);
            return ResponseEntity.ok("Registration successful. Please check your email for verification link.");

    }


    @GetMapping("/verify")
    public String verifyEmail(@RequestParam String code, Model model) {
        boolean verified = emailService.verifyUser(code);
        if (verified) {
            model.addAttribute("message", "Account activated successfully.");
            return "verification-success";
        } else {
            model.addAttribute("message", "Error during account activation. Please try again.");
            return "verification-failure";
        }
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }


    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        Model model) {
        boolean isAuthenticated = emailService.authenticateUser(email, password);

        if (isAuthenticated) {
            return "redirect:/home";
        } else {
            // add error message to the model
            model.addAttribute("errorMessage", "Invalid login. Please try again.");
            return "login";
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> handleForgotPassword(@RequestParam("email") String email) {
        try {
            emailService.sendPasswordResetEmail(email);
            return ResponseEntity.ok("Password reset email sent. Please check your email.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error during sending password reset email. Please try again.");
        }
    }

    @GetMapping("/reset-password")
    public String showResetPasswordForm(@RequestParam("token") String token, Model model) {
        User user = emailService.findByResetPasswordToken(token);
        if (user != null) {
            model.addAttribute("token", token);
            return "users/reset-password";
        } else {
            model.addAttribute("message", "Invalid password reset token. Please try again.");
            return "users/message";
        }
    }

    @PostMapping("/reset-password")
    public String handleResetPassword(@RequestParam("token") String token,
                                      @RequestParam("password") String newPassword,
                                      Model model) {
        User user = emailService.findByResetPasswordToken(token);
        if (user == null) {
            model.addAttribute("message", "Invalid token. Please try again.");
            return "users/message";
        }

        emailService.updatePassword(user, newPassword);
        model.addAttribute("message", "Your password has been updated successfully. Please log in.");
        return "users/message";
    }



}


package com.codeup.localscene.controllers;
import com.codeup.localscene.services.EmailService;
import com.codeup.localscene.models.User;
import com.codeup.localscene.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;


@Controller
public class UserController {

    private final EmailService emailService;
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserController(EmailService emailService, UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.emailService = emailService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/sign-up")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@ModelAttribute User user) {
        try {
            emailService.registerUser(user);
            return ResponseEntity.ok("Registration successful. Please check your email for verification link.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error during registration. Please try again.");
        }
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

    @PostMapping("/perform_login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        Model model) {
        // Authenticate user using userService (or any other service you use for authentication)
        boolean isAuthenticated = userService.authenticateUser(email, password);

        // If authentication is successful, redirect to home page.
        if (isAuthenticated) {
            return "redirect:/home";
        } else {
            // add error message to the model
            model.addAttribute("errorMessage", "Invalid login. Please try again.");
            return "login";
        }
    }
    @GetMapping("/home")
    public String showHomePage() {
        return "home";
    }

    @GetMapping("/forgot-password")
    public String showForgotPasswordForm() {
        return "forgot-password";
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
        User user = userService.findByResetPasswordToken(token);
        if (user != null) {
            model.addAttribute("token", token);
            return "reset-password";
        } else {
            model.addAttribute("message", "Invalid password reset token. Please try again.");
            return "message";
        }
    }

    @PostMapping("/reset-password")
    public String handleResetPassword(@RequestParam("token") String token,
                                      @RequestParam("password") String newPassword,
                                      Model model) {
        User user = userService.findByResetPasswordToken(token);
        if (user == null) {
            model.addAttribute("message", "Invalid token. Please try again.");
            return "message";
        }

        String encodedPassword = passwordEncoder.encode(newPassword);
        userService.updatePassword(encodedPassword, user);
        model.addAttribute("message", "Your password has been updated successfully. Please log in.");
        return "login";
    }
}



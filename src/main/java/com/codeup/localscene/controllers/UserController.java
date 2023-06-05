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
import org.springframework.ui.Model;


@Controller
public class UserController {

    private final EmailService emailService;
    private final UserService userService; // Assuming you have UserService to authenticate users

    @Autowired
    public UserController(EmailService emailService, UserService userService) {
        this.emailService = emailService;
        this.userService = userService;
    }

    @GetMapping("/sign-up")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@ModelAttribute @Valid User user, BindingResult bindingResult) {
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

}


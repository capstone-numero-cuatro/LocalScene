package com.codeup.localscene.controllers;
import com.codeup.localscene.services.EmailService;
import com.codeup.localscene.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


@Controller
public class UserController {

    private final EmailService emailService;

    @Autowired
    public UserController(EmailService emailService) {
        this.emailService = emailService;
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
}

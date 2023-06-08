package com.codeup.localscene.controllers;

import com.codeup.localscene.models.Bands;
import com.codeup.localscene.models.PasswordChangeRequest;
import com.codeup.localscene.models.Posts;
import com.codeup.localscene.models.Users;
import com.codeup.localscene.repositories.BandRepository;
import com.codeup.localscene.repositories.PostRepository;
import com.codeup.localscene.repositories.UserRepository;
import com.codeup.localscene.services.EmailService;
import com.codeup.localscene.services.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProfileController {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    private final BandRepository bandRepository;
    private final PasswordResetService passwordResetService;
    private final EmailService emailService;

    @Autowired
    public ProfileController(UserRepository userRepository, PostRepository postRepository, BandRepository bandRepository, PasswordResetService passwordResetService, EmailService emailService) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.bandRepository = bandRepository;
        this.passwordResetService = passwordResetService;
        this.emailService = emailService;
    }

    @GetMapping("/profile/{id}")
    public String showProfile(@PathVariable long id, Model model) {
        Users user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return "redirect:/login";
        }

        List<Posts> posts = postRepository.findByUser(user);
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Posts()); // Add this line

        // suppose Bands is your another entity
        model.addAttribute("bands", new Bands()); // Add this line

        return "users/profile";
    }
    @GetMapping("/profile/reset-password")
    public String showResetPasswordForm(@RequestParam("token") String token, Model model) {
        Users user = passwordResetService.findByResetPasswordToken(token);
        if (user != null) {
            model.addAttribute("token", token);
            return "/home";
        } else {
            model.addAttribute("message", "Invalid password reset token. Please try again.");
            return "/home";
        }
    }

    @PostMapping("/profile/reset-password")
    public String handleResetPassword(@RequestParam("token") String token,
                                      @RequestParam("password") String newPassword,
                                      Model model) {
        Users user = passwordResetService.findByResetPasswordToken(token);
        if (user == null) {
            model.addAttribute("message", "Invalid token. Please try again.");
            return "/home";
        }

        passwordResetService.updatePassword(user, newPassword);
        model.addAttribute("message", "Your password has been updated successfully. Please log in.");
        return "/home";
    }


}




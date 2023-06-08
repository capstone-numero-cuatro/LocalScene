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
//    @PostMapping("/profile/reset-password")
//    public String handlePasswordChange(@ModelAttribute PasswordChangeRequest passwordChangeRequest, Model model) {
//        Long userId = passwordChangeRequest.getId();
//        Users user = userRepository.findById(userId).orElse(null);
//        if (user != null) {
//            String currentPassword = passwordChangeRequest.getCurrentPassword();
//            String newPassword = passwordChangeRequest.getNewPassword();
//            if (passwordResetService.authenticateUser(user.getEmail(), currentPassword)) {
//                passwordResetService.updatePassword(user, newPassword);
//                model.addAttribute("message", "Password changed successfully!");
//            } else {
//                model.addAttribute("error", "The current password is incorrect. Please try again.");
//            }
//        } else {
//            model.addAttribute("error", "The user does not exist.");
//        }
//        return "/home";
//    }

}




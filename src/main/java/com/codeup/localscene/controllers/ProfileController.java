package com.codeup.localscene.controllers;

import com.codeup.localscene.models.Bands;
import com.codeup.localscene.models.PasswordResetForm;
import com.codeup.localscene.models.Posts;
import com.codeup.localscene.models.Users;
import com.codeup.localscene.repositories.BandRepository;
import com.codeup.localscene.repositories.PostRepository;
import com.codeup.localscene.repositories.UserRepository;
import com.codeup.localscene.services.EmailService;
import com.codeup.localscene.services.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
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
            return "redirect:/404";
        }

        List<Posts> posts = postRepository.findByUser(user);
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Posts());
        model.addAttribute("bands", new Bands());
        model.addAttribute("passwordResetForm", new PasswordResetForm());
        model.addAttribute("user", user);

        return "users/profile";
    }
    @PostMapping("/profile/posts/create")
    public String createPost(@ModelAttribute Posts posts) {
        postRepository.save(posts);
        return "redirect:/posts";
    }
    @PostMapping("/bands/create")
    public String createBand(@ModelAttribute Bands band) {
        bandRepository.save(band);
        return "redirect:/band-profile/" + band.getId();
    }
    @PostMapping("/profile/reset-password")
    public String handlePasswordReset(@ModelAttribute("passwordResetForm") PasswordResetForm form, Model model, Principal principal, RedirectAttributes redirectAttributes) {
        String username = principal.getName();

        Users user = userRepository.findByUsername(username);
        if (user == null) {
            model.addAttribute("errorMessage", "User does not exist.");
            return "users/profile";
        }
        String email = user.getEmail();

        // check if current password is correct
        if (!passwordResetService.authenticateUser(email, form.getCurrentPassword())) {
            model.addAttribute("errorMessage", "Current password is incorrect.");
            return "users/profile";
        }

        // check if new password and confirmation match
        if (!form.getNewPassword().equals(form.getConfirmPassword())) {
            model.addAttribute("errorMessage", "New password and confirmation do not match.");
            return "users/profile";
        }

        // update password
        passwordResetService.updatePassword(user, form.getNewPassword());

        redirectAttributes.addFlashAttribute("message", "Password successfully updated.");

        return "redirect:/profile/" + user.getId();
    }
}




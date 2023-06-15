package com.codeup.localscene.controllers;

import com.codeup.localscene.models.Band;
import com.codeup.localscene.models.PasswordResetForm;
import com.codeup.localscene.models.Posts;
import com.codeup.localscene.models.User;
import com.codeup.localscene.repositories.BandRepository;
import com.codeup.localscene.repositories.PostRepository;
import com.codeup.localscene.repositories.UserRepository;
import com.codeup.localscene.services.BandService;
import com.codeup.localscene.services.EmailService;
import com.codeup.localscene.services.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final BandService bandService;

    @Autowired
    public ProfileController(UserRepository userRepository, PostRepository postRepository, BandRepository bandRepository, PasswordResetService passwordResetService, EmailService emailService, BandService bandService) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.bandRepository = bandRepository;
        this.passwordResetService = passwordResetService;
        this.emailService = emailService;
        this.bandService = bandService;
    }

    @GetMapping("/profile/{id}")
    public String showProfile(@PathVariable long id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));

        List<Posts> posts = postRepository.findByUser(user);

        model.addAttribute("posts", posts);
        model.addAttribute("newPost", new Posts());
        model.addAttribute("band", new Band());
        model.addAttribute("passwordResetForm", new PasswordResetForm());
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/profile/posts/create")
    public String createPost(@ModelAttribute Posts posts) {

        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        loggedInUser = userRepository.findByUsername(loggedInUser.getUsername());

        posts.setUser(loggedInUser);

        postRepository.save(posts);

        return "redirect:/profile/" + loggedInUser.getId();
    }

    @PostMapping("/profile/bands/create")
    public String createBand(@ModelAttribute Band band) {

        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        loggedInUser = userRepository.getReferenceById(loggedInUser.getId());
        loggedInUser.setBand(band);

        bandRepository.save(band);
        return "redirect:/band-profile/" + band.getId();
    }

    @PostMapping("/profile/reset-password")
    public String handlePasswordReset(@ModelAttribute("passwordResetForm") PasswordResetForm form, Model model, Principal principal, RedirectAttributes redirectAttributes) {
        String username = principal.getName();

        User user = userRepository.findByUsername(username);
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



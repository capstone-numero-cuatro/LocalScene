package com.codeup.localscene.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import com.codeup.localscene.models.Users;
import com.codeup.localscene.repositories.BandRepository;
import com.codeup.localscene.repositories.PostRepository;
import com.codeup.localscene.repositories.UserRepository;
import com.codeup.localscene.services.EmailService;
import com.codeup.localscene.services.UserDetailsLoader;
import com.codeup.localscene.services.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class EditController {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final BandRepository bandRepository;
    private final PasswordResetService passwordResetService;
    private final EmailService emailService;
    private final UserDetailsLoader userService;

    @Autowired
    public EditController(UserRepository userRepository, PostRepository postRepository, BandRepository bandRepository, PasswordResetService passwordResetService, EmailService emailService, UserDetailsLoader userService) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.bandRepository = bandRepository;
        this.passwordResetService = passwordResetService;
        this.emailService = emailService;
        this.userService = userService;
    }
    @Value("${filestack.api.key}")
    private String filestackApiKey;
    @GetMapping("/profile/edit")
    public String showEditProfileForm(Model model, Principal principal) {
        String username = principal.getName();
        Users user = userRepository.findByUsername(username);
        model.addAttribute("user", user);
        model.addAttribute("filestackKey", filestackApiKey);
        return "/users/edit-profile";
    }

    @PostMapping("/profile/edit")
    public String updateUserProfile(@ModelAttribute("user") Users user, Model model, Principal principal) {
        try {
            String username = principal.getName();
            Users currentUser = userRepository.findByUsername(username);

            if (currentUser != null) {
                currentUser.setUsername(user.getUsername());
                currentUser.setEmail(user.getEmail());
                currentUser.setProfileImage(user.getProfileImage());

                userRepository.save(currentUser);

                UserDetails userDetails = userService.loadUserByUsername(currentUser.getEmail());
                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);

                model.addAttribute("message", "Profile updated successfully!");
                return "redirect:/profile/" + currentUser.getId();
            } else {
                model.addAttribute("error", "Error updating profile.");
                return "redirect:/profile/edit";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Error updating profile: " + e.getMessage());
            return "redirect:/profile/edit";
        }
    }
}

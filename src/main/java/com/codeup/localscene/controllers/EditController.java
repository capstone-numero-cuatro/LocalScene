package com.codeup.localscene.controllers;

import com.codeup.localscene.models.PasswordResetForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import com.codeup.localscene.models.User;
import com.codeup.localscene.repositories.UserRepository;
import com.codeup.localscene.services.UserDetailsLoader;
import com.codeup.localscene.services.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class EditController {
    private final UserRepository userRepository;
    private final PasswordResetService passwordResetService;
    private final UserDetailsLoader userService;

    @Autowired
    public EditController(UserRepository userRepository, PasswordResetService passwordResetService, UserDetailsLoader userService) {
        this.userRepository = userRepository;
        this.passwordResetService = passwordResetService;
        this.userService = userService;
    }
    @Value("${filestack.api.key}")
    private String filestackApiKey;
    @GetMapping("/profile/edit")
    public String showEditProfileForm(Model model, Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        model.addAttribute("user", user);
        model.addAttribute("filestackKey", filestackApiKey);
        model.addAttribute("passwordResetForm", new PasswordResetForm());

        return "/users/edit-profile";
    }

    @PostMapping("/profile/edit")
    public String updateUserProfile(@ModelAttribute("user") User user, @RequestParam("profileImage") String profileImageUrl, Model model, Principal principal) {

        try {
            String username = principal.getName();
            User currentUser = userRepository.findByUsername(username);

            if (currentUser != null) {
                currentUser.setUsername(user.getUsername());
                currentUser.setEmail(user.getEmail());

                if (profileImageUrl != null && !profileImageUrl.isEmpty()) {
                    currentUser.setProfileImage(profileImageUrl);
                }

                userRepository.save(currentUser);

                UserDetails userDetails = userService.loadUserByUsername(currentUser.getEmail());
                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);

                model.addAttribute("messages", "Profile updated successfully!");
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
    @PostMapping("/profile/edit/reset-password")
    public String handlePasswordReset(@ModelAttribute("passwordResetForm") PasswordResetForm form, Principal principal, RedirectAttributes redirectAttributes) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        String email = user.getEmail();

        // check if current password is correct
        if (!passwordResetService.authenticateUser(email, form.getCurrentPassword())) {
            redirectAttributes.addFlashAttribute("errorMessage", "Current password is incorrect.");
            return "redirect:/profile/edit";
        }

        // update password
        passwordResetService.updatePassword(user, form.getNewPassword());

        redirectAttributes.addFlashAttribute("message", "Password successfully updated.");
        return "redirect:/profile/edit";
    }
}

package com.codeup.localscene.controllers;

import com.codeup.localscene.models.User;
import com.codeup.localscene.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@ControllerAdvice
public class AuthenticationController {
    private final UserRepository userRepository;

    @Autowired
    public AuthenticationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ModelAttribute
    public void addLoggedInUser(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User loggedInUser = userRepository.findByUsername(currentUserName);

        if (loggedInUser != null) {
            model.addAttribute("loggedInUser", loggedInUser);
        }
    }
}

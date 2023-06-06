package com.codeup.localscene.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

@Controller
public class ProfileController {

    @GetMapping("/{user_id}/profile")
    public String showProfilePage(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        model.addAttribute("username", currentUser.getUsername());
        // add other user attributes to model as needed
        return "user/profile";
    }

}

package com.codeup.localscene.controllers;

import com.codeup.localscene.models.Bands;
import com.codeup.localscene.models.Posts;
import com.codeup.localscene.models.Users;
import com.codeup.localscene.repositories.BandRepository;
import com.codeup.localscene.repositories.PostRepository;
import com.codeup.localscene.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class ProfileController {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    // suppose you have a BandsRepository
    private final BandRepository bandRepository;

    @Autowired
    public ProfileController(UserRepository userRepository, PostRepository postRepository, BandRepository bandRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.bandRepository = bandRepository;
    }

    @GetMapping("/profile/{id}")
    public String showProfile(@PathVariable long id, Model model) {
        Users user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return "redirect:/404";
        }

        model.addAttribute("posts", new Posts());
        model.addAttribute("band", new Bands());

        return "users/profile";
    }

    @PostMapping("/profile/posts/create")
    public String createPost(@ModelAttribute Posts posts) {
        postRepository.save(posts);
        return "redirect:/profile";
    }

    @PostMapping("/bands/create")
    public String createBand(@ModelAttribute Bands band) {
        bandRepository.save(band);
        return "redirect:/band-profile/" + band.getId();
    }

    @PostMapping("/saveSocialMediaLink")
    public String saveSocialMediaLink(@ModelAttribute Users user) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        Users currentUser = userRepository.findByUsername(username);

        currentUser.setFacebook(user.getFacebook());
        currentUser.setTwitter(user.getTwitter());
        currentUser.setInstagram(user.getInstagram());

        userRepository.save(currentUser);

        return "redirect:/profile/" + currentUser.getId();
    }

    @PostMapping("/saveFacebook")
    public String saveFacebook(@ModelAttribute Users facebook){
        userRepository.save(facebook);
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        // Retrieve the user from the database using the username
        Users currentUser = userRepository.findByUsername(username);

        return "redirect:/profile/" + currentUser.getId();
    }


}




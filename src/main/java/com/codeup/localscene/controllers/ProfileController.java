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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

        return "profile";
    }

    @PostMapping("/profile/posts/create")
    public String createPost(@ModelAttribute Posts posts) {
        //Access the logged in user (bottom of security)
        Users loggedInUser = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        loggedInUser = userRepository.findByUsername(loggedInUser.getUsername());

        posts.setUser_id(loggedInUser);

        System.out.println("posts.getUser_id().getUsername() = " + posts.getUser_id().getUsername());

        postRepository.save(posts);
        return "redirect:/home";
    }

    @PostMapping("/bands/create")
    public String createBand(@ModelAttribute Bands band) {
        bandRepository.save(band);
        return "redirect:/band-profile/" + band.getId();
    }






}




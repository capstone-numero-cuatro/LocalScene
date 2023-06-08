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
            return "redirect:/login";
        }

        List<Posts> posts = postRepository.findByUser(user);
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Posts()); // Add this line

        // suppose Bands is your another entity
        model.addAttribute("band", new Bands()); // Add this line

        return "users/profile";
    }

    @PostMapping("/bands/create")
    public String createBand(@ModelAttribute Bands band) {
        Users testUser = userRepository.getReferenceById(1L);
        band.setUser(testUser);
        bandRepository.save(band);

        // Redirect to the newly created band's URL
        return "redirect:/band-profile?band_id=" + band.getId();
    }

    @PostMapping("/submit")
    public String saveSocialMediaLink(@ModelAttribute Users user)
    // Retrieve the currently authenticated user
    // Assuming you have implemented user authentication and have UserDetails available
    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String username = userDetails.getUsername();

    // Retrieve the user from the database using the username
    Users currentUser = userRepository.findByUsername(username);

    // Update the social media links
    currentUser.setFacebook(user.getFacebook());
    currentUser.setTwitter(user.getTwitter());
    currentUser.setInstagram(user.getInstagram());

    // Save the updated user
    userRepository.save(currentUser);

    // Redirect back to the profile page
    return "redirect:/profile/" + currentUser.getId();


}




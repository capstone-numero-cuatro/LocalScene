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
import java.util.Optional;

@Controller
public class ProfileController {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final BandRepository bandRepository;


    @Autowired
    public ProfileController(UserRepository userRepository, PostRepository postRepository, BandRepository bandRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.bandRepository = bandRepository;
    }

    @GetMapping("/profile/{id}")
    public String showProfile(@PathVariable long id, Model model, Principal principal) {
        User currentUser = userRepository.findByUsername(principal.getName());

        if (currentUser.getId() != id) {
            return "redirect:/error";
        }

        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            return "redirect:/error";
        }

        User user = optionalUser.get();

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

}



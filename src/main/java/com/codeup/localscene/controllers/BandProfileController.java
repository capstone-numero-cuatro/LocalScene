package com.codeup.localscene.controllers;

import com.codeup.localscene.models.*;

import com.codeup.localscene.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BandProfileController {

    private final BandPostRepository bandPostRepository;
    private final BandRepository bandRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;


    @Autowired
    public BandProfileController(BandPostRepository bandPostRepository,
                                 BandRepository bandRepository, EventRepository eventRepository,
                                 UserRepository userRepository) {
        this.bandPostRepository = bandPostRepository;
        this.bandRepository = bandRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

//    @Autowired
//    public BandProfileController(BandPostRepository bandPostRepository) {
//        this.bandPostRepository = bandPostRepository;
//    }

    @GetMapping("/band-profile/{bandId}")
    public String showBandProfile( Model model, @PathVariable("bandId") Long bandId){
        Band band = bandRepository.getReferenceById(bandId);
        if (band == null){
            return "redirect:/404";
        }

        User users = userRepository.getReferenceById(bandId);

        // Get the currently logged-in user
//        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Check if the logged-in user is associated with the band
//        if (!band.getUser().equals(loggedInUser)) {
//            return "redirect:/404"; // Redirect to error page if user doesn't have access to this band profile
//        }

        model.addAttribute("events", new Events());
        model.addAttribute("bandPost", new BandPosts());
        model.addAttribute("band", band);
        model.addAttribute("bandId", bandId);
        model.addAttribute("users", users);
        return "users/band-profile";
    }



    @PostMapping("/band-profile/{bandId}")
    public String createPostBandProfile(@ModelAttribute Events events){

        eventRepository.save(events);
        return "redirect:/home";
    }

    @GetMapping("/band-profile/{bandId}/edit")
    public String editBandProfile(@PathVariable("bandId") Long bandId, Model model) {
        Band band = bandRepository.findById(bandId).orElse(null);
        if (band == null) {
            return "redirect:/404";
        }
        model.addAttribute("band", band);
        return "users/band-profile";
    }

    @GetMapping("/band-profile/{bandId}/delete")
    public String deleteBandProfile(@PathVariable("bandId") Long bandId) {
        Band band = bandRepository.findById(bandId).orElse(null);
        if (band == null) {
            return "redirect:/404";
        }
        bandRepository.delete(band);
        return "redirect:/profile";
    }
    @PostMapping("/bandPostsCreate")
    public String createBandPost(@ModelAttribute BandPosts bandPost ){
        bandPostRepository.save(bandPost);


        return "redirect:/home";
    }

    @PostMapping("/bandEventCreate")
    public String createEventPost(@ModelAttribute Events events){
        eventRepository.save(events);

        return "redirect:/home";
    }

//    @PostMapping("/band-profile/{bandId}/add-user")
//    public String addUserToBand(@RequestParam("username") String username){
//
//        User users = userRepository.findByUsername(username);
//        if (users == null) {
//            System.err.println("User not found for username: " + username);
//            return "redirect:/404";
//        }
//
//        band.getUser().add(users);
//        bandRepository.save(band);
//
//        return "redirect:/users/band-profile";
//    }

    @PostMapping("/band-profile/{bandId}/add-user")
    public String addUserToBand(@PathVariable("bandId") Long bandId, @RequestParam("username") String username) {
        Band band = bandRepository.findById(bandId).orElse(null);
        User user = userRepository.findByUsername(username);

        if (band == null || user == null) {
            return "redirect:/404"; // Handle the case where the band or user is not found
        }

        user.setBand(band);
        userRepository.save(user);

        return "redirect:/band-profile/" + bandId;
    }


}

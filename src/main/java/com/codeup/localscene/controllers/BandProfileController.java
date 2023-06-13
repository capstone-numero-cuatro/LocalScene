package com.codeup.localscene.controllers;

import com.codeup.localscene.models.*;

import com.codeup.localscene.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BandProfileController {

    private final BandPostRepository bandPostRepository;
    private final BandRepository bandRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final BandUserRepository bandUserRepository;

    @Autowired
    public BandProfileController(BandPostRepository bandPostRepository,
                                 BandRepository bandRepository, EventRepository eventRepository,
                                 UserRepository userRepository, BandUserRepository bandUserRepository) {
        this.bandPostRepository = bandPostRepository;
        this.bandRepository = bandRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.bandUserRepository = bandUserRepository;
    }

//    @Autowired
//    public BandProfileController(BandPostRepository bandPostRepository) {
//        this.bandPostRepository = bandPostRepository;
//    }

    @GetMapping("/band-profile/{band_id}")
    public String showBandProfile( Model model, @PathVariable("band_id") Long band_id){
        Bands band = bandRepository.findById(band_id).orElse(null);
        if (band == null){
            return "redirect:/404";
        }

        // Get the currently logged-in user
        Users loggedInUser = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Check if the logged-in user is associated with the band
        if (!band.getUser().equals(loggedInUser)) {
            return "redirect:/404"; // Redirect to error page if user doesn't have access to this band profile
        }

        model.addAttribute("events", new Events());
        model.addAttribute("bandPost", new BandPosts());
        model.addAttribute("band", band);
        model.addAttribute("band_id", band_id);
        return "users/band-profile";
    }



    @PostMapping("/band-profile/{band_id}")
    public String createPostBandProfile(@ModelAttribute Events events){

        eventRepository.save(events);
        return "redirect:/home";
    }

    @GetMapping("/band-profile/{band_id}/edit")
    public String editBandProfile(@PathVariable("band_id") Long band_id, Model model) {
        Bands band = bandRepository.findById(band_id).orElse(null);
        if (band == null) {
            return "redirect:/404";
        }
        model.addAttribute("band", band);
        return "users/band-profile";
    }

    @GetMapping("/band-profile/{band_id}/delete")
    public String deleteBandProfile(@PathVariable("band_id") Long band_id) {
        Bands band = bandRepository.findById(band_id).orElse(null);
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

//    @PostMapping("/band-profile/{band_id}/add-user")
//    public String addUserToBand(@PathVariable("band_id") Long bandId,
//                                @RequestParam("user_id") Long userId){
//
//        Bands band = bandRepository.findById(bandId).orElse(null);
//        if(band == null){
//            return "redirect:/404";
//        }
//
//        Users user = userRepository.findById(userId).orElse(null);
//        if(user == null){
//            return "redirect:/404";
//        }
//
//        BandUser bandUser = new BandUser(band, user);
//
//        bandUserRepository.save(bandUser);
//
//        return "redirect:/users/band-profile";
//    }

}

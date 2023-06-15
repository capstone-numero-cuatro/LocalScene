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

    @GetMapping("/band-profile/{bandId}")
    public String showBandProfile( Model model, @PathVariable("bandId") Long bandId){
        Band band = bandRepository.getReferenceById(bandId);
        User users = userRepository.getReferenceById(bandId);

        List<BandPosts> bandPosts = bandPostRepository.findByBand(band);

        model.addAttribute("events", new Events());
        model.addAttribute("bandPosts", bandPosts);
        model.addAttribute("newBandPosts", new BandPosts());
        model.addAttribute("band", band);
        model.addAttribute("bandId", bandId);
        model.addAttribute("users", users);
        return "users/band-profile";
    }

//    @PostMapping("/band-profile/{bandId}")
//    public String createPostBandProfile(@ModelAttribute Events events){
//
//        eventRepository.save(events);
//        return "redirect:/home";
//    }

//    @GetMapping("/band-profile/{bandId}/edit")
//    public String editBandProfile(@PathVariable("bandId") Long bandId, Model model) {
//        Band band = bandRepository.findById(bandId).orElse(null);
//        if (band == null) {
//            return "redirect:/404";
//        }
//        model.addAttribute("band", band);
//        return "users/band-profile";
//    }
//
//    @GetMapping("/band-profile/{bandId}/delete")
//    public String deleteBandProfile(@PathVariable("bandId") Long bandId) {
//        Band band = bandRepository.findById(bandId).orElse(null);
//        if (band == null) {
//            return "redirect:/404";
//        }
//        bandRepository.delete(band);
//        return "redirect:/profile";
//    }

    @PostMapping("/band-profile/{bandId}/band-posts/create")
    public String createBandPost(@ModelAttribute BandPosts bandPost,
                             @PathVariable("bandId") Long bandId) {
        Band band = bandRepository.getReferenceById(bandId);

//        Band loggedInUser = (Band) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        loggedInUser = bandRepository.findByUsername(loggedInUser);

        bandPost.setBand(band);
        List<BandPosts> bandPosts = bandPostRepository.findByBand(band);

//        System.out.println("posts.getUser_id().getUsername() = " + posts.getUser().getUsername());

        bandPostRepository.save(bandPost);

        return "redirect:/band-profile/" + band.getId();
    }

//    @PostMapping("/band-profile/{band_id}/bandPosts/create")
//    public String createBandPost(@ModelAttribute BandPosts bandPosts, @PathVariable Long id){
//        Band band = bandRepository.findById(id).orElse(null);
//
////        Band loggedInUser = (Band) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
////        loggedInUser = bandRepository.findByBandname(loggedInUser.getBandname());
////
////        bandPosts.setBand(loggedInUser);
//
//        bandPosts.setBand(band);
//        bandPostRepository.save(bandPosts);
//
//        return "redirect:/band-profile" + id;
//    }

//    @PostMapping("/band-profile/band-posts/create")
//    public String createBandPost(@ModelAttribute("bandPost") BandPosts bandPost) {
//        Band loggedInUser = (Band) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Band band = bandRepository.findByBandname(loggedInUser.getBandname());
//
//
//        bandPost.setBand(band);
//        bandPostRepository.save(bandPost);
//
//        return "redirect:/band-profile";
//    }


    @PostMapping("/bandEventCreate")
    public String createEventPost(@ModelAttribute Events events){
        eventRepository.save(events);

        return "redirect:/home";
    }

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

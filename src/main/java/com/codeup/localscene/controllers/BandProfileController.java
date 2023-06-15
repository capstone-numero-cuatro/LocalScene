package com.codeup.localscene.controllers;

import com.codeup.localscene.models.*;

import com.codeup.localscene.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
        List<Events> events = eventRepository.findByBand(band);


        model.addAttribute("events", events);
        model.addAttribute("newEvent", new Events());
        model.addAttribute("bandPosts", bandPosts);
        model.addAttribute("newBandPosts", new BandPosts());
        model.addAttribute("band", band);
        model.addAttribute("bandId", bandId);
        model.addAttribute("users", users);
        return "users/band-profile";
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
    @PostMapping("/band-profile/{bandId}/change-image")
    public String changeBandImage(@ModelAttribute("band") Band band, @PathVariable long bandId, @AuthenticationPrincipal UserDetails currentUser) {
            // get the authenticated user
            User user = userRepository.findByUsername(currentUser.getUsername());

            // get the band from the database
            Band existingBand = bandRepository.findById(bandId).orElse(null);

            // update the image
            existingBand.setBand_image(band.getBand_image());

            // save the band
            bandRepository.save(existingBand);

            return "redirect:/band-profile/" + bandId;
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

    @PostMapping("/band-profile/{bandId}/band-posts/create")
    public String createBandPost(@ModelAttribute BandPosts bandPost,
                             @PathVariable("bandId") Long bandId) {
        Band band = bandRepository.getReferenceById(bandId);

        bandPost.setBand(band);
        List<BandPosts> bandPosts = bandPostRepository.findByBand(band);

        bandPostRepository.save(bandPost);

        return "redirect:/band-profile/" + band.getId();
    }

    @PostMapping("/band-profile/{bandId}/band-events/create")
    public String createEventPost(@ModelAttribute Events event,
                                  @PathVariable("bandId") Long bandId){
        Band band = bandRepository.getReferenceById(bandId);

        event.setBand(band);

        List<Events> events = eventRepository.findByBand(band);

        eventRepository.save(event);

        return "redirect:/band-profile/" + band.getId();
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

package com.codeup.localscene.controllers;

import com.codeup.localscene.models.BandPosts;
import com.codeup.localscene.models.Bands;
import com.codeup.localscene.models.Events;
import com.codeup.localscene.repositories.BandPostRepository;
import com.codeup.localscene.repositories.BandRepository;
import com.codeup.localscene.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BandProfileController {

    private final BandPostRepository bandPostRepository;
    private EventRepository eventRepository;

    @Autowired
    private BandRepository bandRepository;

    public BandProfileController(BandPostRepository bandPostRepository, BandRepository bandRepository, EventRepository eventRepository) {
        this.bandPostRepository = bandPostRepository;
        this.bandRepository = bandRepository;
        this.eventRepository = eventRepository;
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

        model.addAttribute("events", new Events());
        model.addAttribute("bandPost", new BandPosts());
        model.addAttribute("band", band);
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


}

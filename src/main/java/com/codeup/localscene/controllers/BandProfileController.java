package com.codeup.localscene.controllers;

import com.codeup.localscene.models.BandPosts;
import com.codeup.localscene.models.Bands;
import com.codeup.localscene.models.Posts;
import com.codeup.localscene.repositories.BandPostRepository;
import com.codeup.localscene.repositories.BandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BandProfileController {

    private final BandPostRepository bandPostRepository;

    @Autowired
    private BandRepository bandRepository;

    @Autowired
    public BandProfileController(BandPostRepository bandPostRepository) {
        this.bandPostRepository = bandPostRepository;
    }

    @GetMapping("/band-profile/{band_id}")
    public String showBandProfile( Model model, @PathVariable("band_id") Long band_id){
        Bands band = bandRepository.findById(band_id).orElse(null);
        if (band == null){
            return "redirect:/404";
        }


        model.addAttribute("bandPost", new BandPosts());
        model.addAttribute("band", band);
        return "users/band-profile";
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


        return "redirect:/band-profile/";
    }


}

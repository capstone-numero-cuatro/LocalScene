package com.codeup.localscene.controllers;

import com.codeup.localscene.models.Bands;
import com.codeup.localscene.repositories.BandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BandProfileController {

    @Autowired
    private BandRepository bandRepository;

    @GetMapping("/band-profile")
    public String showBandProfile(@RequestParam Long band_id, Model model){
        Bands band = bandRepository.findById(band_id).orElse(null);
        if (band == null){
            return "redirect:/404";
        }
        model.addAttribute("band", band);
        return "/users/profile";
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

    @GetMapping("/home")
    public String showHomePage() {
        return "home";
    }

}

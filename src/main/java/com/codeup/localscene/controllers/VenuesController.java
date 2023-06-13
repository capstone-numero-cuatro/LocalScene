package com.codeup.localscene.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VenuesController {

    @GetMapping("/venues")
    public String venues(){
        return "venues";
    }




}

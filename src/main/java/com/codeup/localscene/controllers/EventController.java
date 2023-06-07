package com.codeup.localscene.controllers;

import com.codeup.localscene.models.BandPosts;
import com.codeup.localscene.models.Events;
import com.codeup.localscene.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//@Controller
//public class EventController {
//
//    @Autowired
//    private EventRepository eventRepository;
//
//    //retrieves list of events
//    @GetMapping("/profile/{id}/events")
//    public String getEvents(Model model) {
//        List<Events> events = eventRepository.findAll();
//        model.addAttribute("events", events);
//        model.addAttribute("event", new Events());
//        return "events";
//    }
//
//    //create event, saves event, redirects to list of events
//    @PostMapping("/profile/{id}/events/create")
//    public String createEvents(@ModelAttribute("event") Events event) {
//        eventRepository.save(event);
//        return "redirect:/events";
//    }
//
//    //delete
//    @PostMapping("/profile/{id}/events/delete")
//    public String deleteEvents(@ModelAttribute("event") Events event){
//        eventRepository.delete(event);
//        return "redirect:/events";
//    }
//
//}

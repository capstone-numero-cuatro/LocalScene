package com.codeup.localscene.controllers;

import com.codeup.localscene.models.Events;
import com.codeup.localscene.repositories.BandRepository;
import com.codeup.localscene.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EventController {

    private final EventRepository eventRepository;
    private final BandRepository bandRepository;

    @Autowired
    public EventController(EventRepository eventRepository, BandRepository bandRepository){
        this.eventRepository = eventRepository;
        this.bandRepository = bandRepository;
    }

    @GetMapping("/events")
    public String events(){
        return "events";
    }

    //retrieves list of events
    @GetMapping("/band-profile/{band_id}/events")
    public String getEvents(Model model, @PathVariable String band_id) {
        List<Events> events = eventRepository.findAll();

        model.addAttribute("events", events);
        model.addAttribute("newEvent", new Events());
        return "redirect:/home";
    }

//    create event, saves event, redirects to list of events
    @PostMapping("/band-profile/events/create")
    public String createEvent(@ModelAttribute("event") Events events) {
        eventRepository.save(events);
        return "redirect:/home";
    }
//
//    //delete
//    @PostMapping("/band-profile/{band_id}/events/delete")
//    public String deleteEvents(@ModelAttribute("event") Events event){
//        eventRepository.delete(event);
//        return "redirect:/home";
//    }

}

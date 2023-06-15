package com.codeup.localscene.controllers;

import com.codeup.localscene.models.Band;
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


    //retrieves list of events
    @GetMapping("/events")
    public String getEvents(Model model, @PathVariable String band_id) {
        List<Events> events = eventRepository.findAll();

        model.addAttribute("events", events);
        model.addAttribute("newEvent", new Events());
        return "redirect:/events";
    }

//    create event, saves event, redirects to list of events
    @PostMapping("/band-profile/events/create")
    public String createEvent(@ModelAttribute("event") Events events,
                              @PathVariable("bandId") Long bandId) {
        Band band = bandRepository.getReferenceById(bandId);
        events.setBand(band);
        eventRepository.save(events);
        return "redirect:/band-profile/" + bandId;
    }


    //delete
    @PostMapping("/band-profile/{bandId}/events/{eventId}/delete")
    public String deleteEvents(@PathVariable("bandId") Long bandId,
                               @PathVariable("eventId") Long eventId){
        eventRepository.deleteById(eventId);
        return "redirect:/band-profile/{bandId}";
    }

}

package com.codeup.localscene.controllers;

import com.codeup.localscene.models.BandPosts;
import com.codeup.localscene.models.Events;
import com.codeup.localscene.models.Posts;
import com.codeup.localscene.repositories.BandPostRepository;
import com.codeup.localscene.repositories.EventRepository;
import com.codeup.localscene.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private PostRepository postRepository;
    private BandPostRepository bandPostRepository;
    private EventRepository eventRepository;

    public HomeController(PostRepository postRepository, BandPostRepository bandPostRepository,
                          EventRepository eventRepository) {
        this.postRepository = postRepository;
        this.bandPostRepository = bandPostRepository;
        this.eventRepository = eventRepository;
    }



    @GetMapping("/home")
    public String welcome(Model model){
        List<Posts> posts =postRepository.findAll();
        List<BandPosts> bandPosts = bandPostRepository.findAll();
        List<Events> events = eventRepository.findAll();

        model.addAttribute("events", events);
        model.addAttribute("posts", posts);
        model.addAttribute("bandPosts", bandPosts);

        return "home";}

    @GetMapping("/")
    public String home(Model model){
        List<Posts> posts =postRepository.findAll();
        List<BandPosts> bandPosts = bandPostRepository.findAll();
        List<Events> events = eventRepository.findAll();

        model.addAttribute("events", events);
        model.addAttribute("posts", posts);
        model.addAttribute("bandPosts", bandPosts);

        return "home";}




}

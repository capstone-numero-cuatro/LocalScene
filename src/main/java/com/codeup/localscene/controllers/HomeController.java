package com.codeup.localscene.controllers;

import com.codeup.localscene.models.*;
import com.codeup.localscene.repositories.*;
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
    private UserRepository userRepository;
    private BandRepository bandRepository;

    public HomeController(PostRepository postRepository, BandPostRepository bandPostRepository,
                          EventRepository eventRepository, UserRepository userRepository, BandRepository bandRepository) {
        this.postRepository = postRepository;
        this.bandPostRepository = bandPostRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.bandRepository = bandRepository;
    }



    @GetMapping("/home")
    public String welcome(Model model){
        List<Band> bands = bandRepository.findAll();
        List<User> users = userRepository.findAll();

        List<Posts> posts =postRepository.findAll();
        List<BandPosts> bandPosts = bandPostRepository.findAll();
        List<Events> events = eventRepository.findAll();



        model.addAttribute("events", events);
        model.addAttribute("posts", posts);
        model.addAttribute("bandPosts", bandPosts);
        model.addAttribute("users", users);
        model.addAttribute("band", bands);

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

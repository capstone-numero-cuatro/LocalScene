package com.codeup.localscene.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//public class Post {
//    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
//    @Controller
//    public class PostController {
//
//        @Autowired
//        private PostRepository postRepository;
//
//        @GetMapping("/posts")
//        public String getPosts(Model model) {
//            List<Post> posts = postRepository.findAll();
//            model.addAttribute("posts", posts);
//            model.addAttribute("post", new Post());
//            return "posts";
//        }
//
//        @PostMapping("/posts/create")
//        public String createPost(@ModelAttribute("post") Post post) {
//            postRepository.save(post);
//            return "redirect:/posts";
//        }
//
//        private class PostRepository {
//            public List<Post> findAll() {
//                return null;
//            }
//
//            public void save(Post post) {
//            }
//        }
//    }
//}


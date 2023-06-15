package com.codeup.localscene.models;

import jakarta.persistence.*;

@Entity
@Table(name = "posts")
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 45)
    private String title;

    @Column(nullable = false, length = 200)
    private String description;

    @Column
    private String post_image;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Posts(Posts copy){
        id = copy.id;
        title = copy.title;
        description = copy.description;
        post_image = copy.post_image;
        user = copy.user;
    }

    public Posts() {
    }

    public long getId() {
        return id;
    }

    public void setId(long post_id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPost_image(){
        return post_image;
    }

    public void setPost_image(String post_image) {
        this.post_image = post_image;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
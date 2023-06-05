package com.codeup.localscene.model;

import jakarta.persistence.*;

@Entity
@Table(name = "posts")
public class Posts {

    @Id
    @Column(name = "post_id")
    private long post_id;

    @Column(nullable = false, length = 45)
    private String title;

    @Column(nullable = false, length = 200)
    private String description;

    @Column
    private long post_image;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user_id;

    public Posts(Posts copy){
        post_id = copy.post_id;
        title = copy.title;
        description = copy.description;
        post_image = copy.post_image;
        user_id = copy.user_id;
    }

    public Posts() {
    }

    public long getPostId() {
        return post_id;
    }

    public void setPostId(long post_id) {
        this.post_id = post_id;
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

    public long getPostImage(){
        return post_image;
    }

    public void setPostImage(long post_image) {
        this.post_image = post_image;
    }

}
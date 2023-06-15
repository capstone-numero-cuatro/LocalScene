package com.codeup.localscene.models;

import jakarta.persistence.*;

@Entity
@Table(name = "band_posts")
public class BandPosts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 45)
    private String title;

    @Column(nullable = false, length = 200)
    private String description;

    @Column
    private long post_image;

    @ManyToOne
    @JoinColumn(name = "band_id")
    private Band band;

    public BandPosts(BandPosts copy){
        id = copy.id;
        title = copy.title;
        description = copy.description;
        post_image = copy.post_image;
        band = copy.band;
    }

    public BandPosts() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public long getPostImage(){
        return post_image;
    }

    public void setPostImage(long post_image) {
        this.post_image = post_image;
    }

    public Band getBand(){
        return band;
    }

    public void setBand(Band band) {
        this.band = band;
    }
}

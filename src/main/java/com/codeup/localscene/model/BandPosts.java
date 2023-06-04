package com.codeup.localscene.model;

import jakarta.persistence.*;

@Entity
@Table(name = "band_posts")
public class BandPosts {

    @Id
    @Column(name = "band_post_id")
    private long band_post_id;

    @Column(nullable = false, length = 45)
    private String title;

    @Column(nullable = false, length = 200)
    private String description;

    @Column
    private long post_image;

    @ManyToOne
    @JoinColumn(name = "band_id")
    private Bands band_id;

    public BandPosts(BandPosts copy){
        band_post_id = copy.band_post_id;
        title = copy.title;
        description = copy.description;
        post_image = copy.post_image;
        band_id = copy.band_id;
    }

    public BandPosts() {
    }

    public long getBandPostId() {
        return band_post_id;
    }

    public void setBandPostId(long band_post_id) {
        this.band_post_id = band_post_id;
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

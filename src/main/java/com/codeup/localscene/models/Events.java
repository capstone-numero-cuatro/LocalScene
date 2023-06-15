package com.codeup.localscene.models;

import jakarta.persistence.*;

@Entity
@Table(name = "events")
public class Events {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 45)
    private String title;

    @Column(nullable = false, length = 200)
    private String description;

    @Column
    private String event_image;

    @ManyToOne
    @JoinColumn(name = "band_id")
    private Band band;

    public Events(Events copy){
        id = copy.id;
        title = copy.title;
        description = copy.description;
        event_image = copy.event_image;
        band = copy.band;
    }

    public Events() {
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

    public String getEventImage(){
        return event_image;
    }

    public void setEventImage(String event_image) {
        this.event_image = event_image;
    }

    public Band getBand(){
        return band;
    }

    public void setBand(Band band) {
        this.band = band;
    }
}
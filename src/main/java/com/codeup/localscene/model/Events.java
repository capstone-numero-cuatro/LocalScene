package com.codeup.localscene.model;

import jakarta.persistence.*;

@Entity
@Table(name = "events")
public class Events {

    @Id
    @Column(name = "event_id")
    private long event_id;

    @Column(nullable = false, length = 45)
    private String title;

    @Column(nullable = false, length = 200)
    private String description;

    @Column
    private long event_image;

    @ManyToOne
    @JoinColumn(name = "band_id")
    private Bands band_id;

    public Events(Events copy){
        event_id = copy.event_id;
        title = copy.title;
        description = copy.description;
        event_image = copy.event_image;
        band_id = copy.band_id;
    }

    public Events() {
    }

    public long getEventsId() {
        return event_id;
    }

    public void setEventsId(long event_id) {
        this.event_id = event_id;
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

    public long getEventImage(){
        return event_image;
    }

    public void setEventImage(long event_image) {
        this.event_image = event_image;
    }

}
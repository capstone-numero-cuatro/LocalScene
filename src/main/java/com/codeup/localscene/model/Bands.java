package com.codeup.localscene.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bands")
public class Bands {

    @Id
    @Column(name = "band_id")
    private long band_id;

    @Column(nullable = false, length = 45)
    private String bandname;

    @Column(nullable = false, length = 200)
    private String description;

    @Column
    private long band_image;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user_id;

    public Bands(Bands copy){
        band_id = copy.band_id;
        bandname = copy.bandname;
        description = copy.description;
        band_image = copy.band_image;
        user_id = copy.user_id;
    }

    public Bands() {
    }


    public long getBandId() {
        return band_id;
    }

    public void setBandId(long band_id) {
        this.band_id = band_id;
    }

    public String getBandname() {
        return bandname;
    }

    public void setBandname(String bandname) {
        this.bandname = bandname;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getBandImage(){
        return band_image;
    }

    public void setBandImage(long band_image) {
        this.band_image = band_image;
    }
}

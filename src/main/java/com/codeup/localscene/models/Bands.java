package com.codeup.localscene.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "bands")
public class Bands {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "band")
    private List<BandUser> band;

    @Column(nullable = false, length = 45)
    private String bandname;

    @Column(nullable = false, length = 200)
    private String description;

    @Column
    private String band_image;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    public Bands(Bands copy){
        id = copy.id;
        band = copy.band;
        bandname = copy.bandname;
        description = copy.description;
        band_image = copy.band_image;
        user = copy.user;
    }

    public Bands() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getBandImage(){
        return band_image;
    }

    public void setBandImage(String band_image) {
        this.band_image = band_image;
    }
}

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

    @Column(name = "band_image", length = 500)
    private String bandImage;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    public Bands(Bands copy){
        id = copy.id;
        band = copy.band;
        bandname = copy.bandname;
        description = copy.description;
        bandImage = copy.bandImage;
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

    public List<BandUser> getBand() {
        return band;
    }

    public void setBand(List<BandUser> band) {
        this.band = band;
    }

    public String getBandImage() {
        return bandImage;
    }

    public void setBandImage(String band_image) {
        this.bandImage = bandImage;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

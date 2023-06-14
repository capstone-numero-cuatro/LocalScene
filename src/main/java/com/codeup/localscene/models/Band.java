package com.codeup.localscene.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "band")
public class Band {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @OneToMany(mappedBy = "band")
//    private List<BandUser> band;

    @Column(nullable = false, length = 45)
    private String bandname;

    @Column(nullable = false, length = 200)
    private String description;

    @Column
    private String band_image;

    @OneToMany(mappedBy = "band", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> users;

    public Band(Band copy){
        id = copy.id;
//        bands = copy.bands;
        bandname = copy.bandname;
        description = copy.description;
        band_image = copy.band_image;
        users = copy.users;
    }

    public Band() {
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


//    public List<BandUser> getBand() {
//        return band;
//    }
//
//    public void setBand(List<BandUser> band) {
//        this.band = band;
//    }


    public String getBand_image() {
        return band_image;
    }

    public void setBand_image(String band_image) {
        this.band_image = band_image;
    }

    public List<User> getUser() {
        return users;
    }

    public void setUser(List<User> users) {
        this.users = users;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

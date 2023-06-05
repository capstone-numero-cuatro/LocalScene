package com.codeup.localscene.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "bands")
public class Bands {

    @Id
    @Column(name = "band_id")
    private long id;

    @OneToMany(mappedBy = "band")
    private List<BandUser> band;

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
        id = copy.id;
        bandname = copy.bandname;
        description = copy.description;
        band_image = copy.band_image;
        user_id = copy.user_id;
    }

    public Bands() {
    }


    public long getId() {
        return id;
    }

    public void setId(long band_id) {
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

    public long getBandImage(){
        return band_image;
    }

    public void setBandImage(long band_image) {
        this.band_image = band_image;
    }
}

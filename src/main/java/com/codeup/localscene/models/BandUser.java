package com.codeup.localscene.models;

import jakarta.persistence.*;


@Entity
@Table(name = "band_users")
public class BandUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "band_id")
    private Bands band;


    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public Users getUser(){
        return user;
    }

    public void setUser(Users user_id){
        this.user = user;
    }

    public Bands getBand(){
        return band;
    }

    public void setBand(Bands band_id) {
        this.band = band;
    }

}

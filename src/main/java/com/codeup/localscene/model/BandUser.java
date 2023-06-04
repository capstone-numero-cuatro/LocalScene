package com.codeup.localscene.model;

import jakarta.persistence.*;


@Entity
@Table(name = "band_users")
public class BandUser {

    @Id
    private Long id;
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    @OneToMany
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "users_id")
    private Long user_id;

    @OneToMany
    @JoinColumn(name = "band_id")
    private Long band_id;



    public Long getUserId(){
        return user_id;
    }

    public void setUserId(Long user_id){
        this.user_id = user_id;
    }

    public Long getBandId(){
        return band_id;
    }

    public void setBandId(Long band_id) {
        this.band_id = band_id;
    }
}

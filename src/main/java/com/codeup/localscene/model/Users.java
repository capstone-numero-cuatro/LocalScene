package com.codeup.localscene.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "user")
    private List<BandUser> user;

    @Column(nullable = false, length = 45)
    private String username;

    @Column(nullable = false, length = 45)
    private String password;

    @Column(nullable = false, length = 200, unique = true)
    private String email;

    @Column(name = "verification_code", length = 64)
    private String verification_code;

    @Column()
    private boolean enabled;

    @Column(length = 45)
    private String instrument;

    @Column()
    private boolean band_member;

    @Column()
    private long user_image;


    public Users(Users copy) {
        id = copy.id; // This line is SUPER important! Many things won't work if it's
        // absent
        username = copy.username;
        password = copy.password;
        email = copy.email;
        verification_code = copy.verification_code;
        enabled = copy.enabled;
        instrument = copy.instrument;
        band_member = copy.band_member;
        user_image = copy.user_image;
    }

    public Users() {

    }



    public long getId(){
        return id;
    }

    public void setId(long user_id){
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVerification_code() {
        return verification_code;
    }

    public void setVerificationCode(String verification_code) {
        this.verification_code = verification_code;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public boolean getBandMember() {
        return band_member;
    }

    public void setBandMember(boolean band_member) {
        this.band_member = band_member;
    }

    public long getUserImage (){
        return user_image;
    }

    public void setUserImage(Long user_image) {
        this.user_image = user_image;
    }
}
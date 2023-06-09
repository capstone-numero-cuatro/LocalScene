package com.codeup.localscene.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 45)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, unique = true, length = 200)
    private String email;

    @Column(name = "verification_code", length = 64)
    private String verificationCode;

    @Column(name = "reset_password_token", length = 100)
    private String resetPasswordToken;

    @Column()
    private boolean enabled;

    @Column(name = "profile_image", length = 500)
    private String profileImage;

    @ManyToOne
    @JoinColumn(name = "band_id")
    private Band band;



    public User(User copy) {
        id = copy.id;
        username = copy.username;
        password = copy.password;
        verificationCode = copy.verificationCode;
        resetPasswordToken = copy.resetPasswordToken;
        enabled = copy.enabled;
        profileImage = copy.profileImage;
        band = copy.band;
    }

    public User (){
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
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

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public Band getBand(){
        return this.band;
    }

    public void setBand(Band band){
        this.band = band;
    }
}
package com.codeup.localscene.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class Users {

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

    @Column(length = 45)
    private String instrument;

    @Column
    private boolean band_member;



    @Column()
    private String user_image;

    @Column()
    private String instagram;

    @Column()
    private String facebook;

    @Column()
    private String twitter;




    @Transient
    private String confirmPassword;


    public Users(Users copy) {
        id = copy.id;
        username = copy.username;
        password = copy.password;
        verificationCode = copy.verificationCode;
        resetPasswordToken = copy.resetPasswordToken;
        enabled = copy.enabled;
        instrument = copy.instrument;
        band_member = copy.band_member;
        user_image = copy.user_image;
        instagram = copy.instagram;
        facebook = copy.facebook;
        twitter = copy.twitter;
    }

    public Users (){
    }

    public Users(Long id, String username, String password, String email, String verificationCode, String resetPasswordToken, boolean enabled, String instrument, boolean band_member, String user_image, String instagram, String facebook, String twitter, String confirmPassword) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.verificationCode = verificationCode;
        this.resetPasswordToken = resetPasswordToken;
        this.enabled = enabled;
        this.instrument = instrument;
        this.band_member = band_member;
        this.user_image = user_image;
        this.instagram = instagram;
        this.facebook = facebook;
        this.twitter = twitter;
        this.confirmPassword = confirmPassword;
    }

    public Users(String username, String password, String email, String verificationCode, String resetPasswordToken, boolean enabled, String instrument, boolean band_member, String user_image, String instagram, String facebook, String twitter, String confirmPassword) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.verificationCode = verificationCode;
        this.resetPasswordToken = resetPasswordToken;
        this.enabled = enabled;
        this.instrument = instrument;
        this.band_member = band_member;
        this.user_image = user_image;
        this.instagram = instagram;
        this.facebook = facebook;
        this.twitter = twitter;
        this.confirmPassword = confirmPassword;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public boolean isBand_member() {
        return band_member;
    }

    public void setBand_member(boolean band_member) {
        this.band_member = band_member;
    }

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }



}
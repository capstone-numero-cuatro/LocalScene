package com.codeup.localscene.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long user_id;

    @Column(nullable = false, length = 45)
    private String username;
    @Column(nullable = false, length = 45)
    private String password;
    @Column(nullable = false, unique = true, length = 200)
    private String email;
    @Column(name = "verification_code", length = 64)
    private String verificationCode;
    @Column
    private boolean enabled;
    @Column(length = 45)
    private String instrument;
    @Column
    private boolean band_member;

    private String confirmPassword;
// Getter and Setter for confirmPassword

    public User() {}

    public User(User copy) {
        user_id = copy.user_id;
        email = copy.email;
        username = copy.username;
        password = copy.password;
        verificationCode = copy.verificationCode;
        enabled = copy.enabled;
        instrument = copy.instrument;
        band_member = copy.band_member;
    }

    public Long getId() {
        return user_id;
    }

    public void setId(Long id) {
        this.user_id = id;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}

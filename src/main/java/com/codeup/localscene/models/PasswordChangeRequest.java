package com.codeup.localscene.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class PasswordChangeRequest {
    @NotNull
    private Long id;

    @NotEmpty
    private String currentPassword;

    @NotEmpty
    private String newPassword;

    @NotEmpty
    private String confirmNewPassword;

    @NotEmpty
    private String username;

    // Getters and setters
    public String getCurrentPassword() {
        return currentPassword;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

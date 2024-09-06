package com.olivierfabre.lundimatin.models;

public class AuthRequest {
    private String username;
    private String password;
    private int password_type = 0; // le mdp est en clair
    private String code_application = "webservice_externe";
    private String code_version = "1";

    public AuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters et setters
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

    public int getPassword_type() {
        return password_type;
    }

    public void setPassword_type(int password_type) {
        this.password_type = password_type;
    }

    public String getCode_application() {
        return code_application;
    }

    public void setCode_application(String code_application) {
        this.code_application = code_application;
    }

    public String getCode_version() {
        return code_version;
    }

    public void setCode_version(String code_version) {
        this.code_version = code_version;
    }
}

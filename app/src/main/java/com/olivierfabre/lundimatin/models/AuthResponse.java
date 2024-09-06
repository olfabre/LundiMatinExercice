package com.olivierfabre.lundimatin.models;

public class AuthResponse {
    private int code;
    private String message;
    private AuthData datas;

    public AuthData getDatas() {
        return datas;
    }

    public class AuthData {
        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

    // Getters et setters
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
package com.olivierfabre.lundimatin.models;

import java.util.List;

public class ClientResponse {
    private int code;
    private String message;
    private List<Client> datas; // Liste des clients

    private List<String> warnings; // Liste des avertissements

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

    public List<Client> getDatas() {
        return datas;
    }

    public void setDatas(List<Client> datas) {
        this.datas = datas;
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }
}
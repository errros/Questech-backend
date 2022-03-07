package com.elab.elearning.elearning.jwt;

public class JwtResponseModel {

    private String jwtToken;


    public JwtResponseModel() {
    }

    public JwtResponseModel(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    @Override
    public String toString() {
        return "JwtResponseModel{" +
                "jwtToken='" + jwtToken + '\'' +
                '}';
    }
}

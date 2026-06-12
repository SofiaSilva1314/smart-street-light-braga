package com.smartlight.streetlight.dto;

/** DTO - dados que chegam no pedido de login (username + password). */
public class LoginRequest {
    private String username;
    private String password;
    public String getUsername() { return username; }
    public void setUsername(String v) { this.username = v; }
    public String getPassword() { return password; }
    public void setPassword(String v) { this.password = v; }
}

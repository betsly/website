/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.beans;

import java.time.LocalDate;

/**
 *
 * @author zmugg
 */
public class User {
    private String email;
    private String username;
    private String pw;
    LocalDate accountCreationDate;

    public User() {
    }

    public User(String email, String username, String pw, LocalDate accountCreationDate) {
        this.email = email;
        this.username = username;
        this.pw = pw;
        this.accountCreationDate = accountCreationDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public LocalDate getAccountCreationDate() {
        return accountCreationDate;
    }

    public void setAccountCreationDate(LocalDate accountCreationDate) {
        this.accountCreationDate = accountCreationDate;
    }

    @Override
    public String toString() {
        return username + " " + pw;
    }
    
    
}

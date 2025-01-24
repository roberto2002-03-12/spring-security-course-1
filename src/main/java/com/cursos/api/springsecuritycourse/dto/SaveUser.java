package com.cursos.api.springsecuritycourse.dto;

import jakarta.validation.constraints.Size;
import java.io.Serializable;

public class SaveUser implements Serializable {
    @Size(min = 3, max = 45)
    private String username;

    @Size(min = 3, max = 65)
    private String name;

    @Size(min = 8, max = 32)
    private String password;

    @Size(min = 8, max = 32)
    private String repeatPassword;

    public SaveUser() {}

    public SaveUser(String username, String name, String password, String repeatPassword) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}

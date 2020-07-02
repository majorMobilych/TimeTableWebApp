package com.web.app.hibernate.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "users", schema = "public", catalog = "postgres")
@Data
public class UsersEntity {
    private String login;
    private String name;
    private String password;
    private String role;

    public UsersEntity() {
    }

    public UsersEntity(String login, String name, String password, String role) {
        this.login = login;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    @Id
    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    @Basic
    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

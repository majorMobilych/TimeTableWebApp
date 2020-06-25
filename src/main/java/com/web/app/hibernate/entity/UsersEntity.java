package com.web.app.hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name = "users", schema = "public", catalog = "postgres")
public class UsersEntity {
    private String login;
    private String name;
    private String password;
    private int role_id;

    public UsersEntity() {
    }

    public UsersEntity(String login, String name, String password, int role_id) {
        this.login = login;
        this.name = name;
        this.password = password;
        this.role_id = role_id;
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
    @Column(name = "role_id")
    public int getRole_id() {
        return role_id;
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

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }
}

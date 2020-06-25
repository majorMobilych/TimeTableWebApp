package com.web.app.hibernate.entity;

import javax.persistence.*;

//TODO: это я сам писал. Почему хибернейт не видит эту таблицу?
@Entity
@Table(name = "roles", schema = "public", catalog = "postgres")
public class RolesEntity {
    private int id;
    private String role;

    public RolesEntity() {
    }

    public RolesEntity(int id, String role) {
        this.id = id;
        this.role = role;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    @Basic
    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

}

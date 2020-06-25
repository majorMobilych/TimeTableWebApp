package com.web.app.hibernate.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "agenda", schema = "public", catalog = "postgres")
public class AgendaEntity {
    private int id;
    private String username;
    private String day;
    private String time;
    private String note;
    private Boolean privacy;

    public AgendaEntity() {
    }

    public AgendaEntity(int id, String username, String day, String time, String note, boolean privacy) {
        this.id = id;
        this.username = username;
        this.day = day;
        this.time = time;
        this.note = note;
        this.privacy = privacy;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "day")
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Basic
    @Column(name = "time")
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Basic
    @Column(name = "note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Basic
    @Column(name = "privacy")
    public Boolean getPrivacy() {
        return privacy;
    }

    public void setPrivacy(Boolean privacy) {
        this.privacy = privacy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AgendaEntity that = (AgendaEntity) o;

        if (id != that.id) return false;
        if (!Objects.equals(username, that.username)) return false;
        if (!Objects.equals(day, that.day)) return false;
        if (!Objects.equals(time, that.time)) return false;
        if (!Objects.equals(note, that.note)) return false;
        return Objects.equals(privacy, that.privacy);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (day != null ? day.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        result = 31 * result + (privacy != null ? privacy.hashCode() : 0);
        return result;
    }
}

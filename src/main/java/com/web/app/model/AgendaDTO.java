package com.web.app.model;

public class AgendaDTO {
    private int id;
    private String username;
    private String day;
    private String time;
    private String note;
    private boolean privacy;

    public AgendaDTO(int id, String username, String day, String time, String note, boolean privacy) {
        this.id = id;
        this.username = username;
        this.day = day;
        this.time = time;
        this.note = note;
        this.privacy = privacy;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }

    public String getNote() {
        return note;
    }

    public boolean getPrivacy() {
        return privacy;
    }
}

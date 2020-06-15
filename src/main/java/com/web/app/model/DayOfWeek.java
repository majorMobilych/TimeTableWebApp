package com.web.app.model;

public enum DayOfWeek {
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6),
    SUNDAY(7);
    public final int day;
    DayOfWeek(int day) {
        this.day = day;
    }
}

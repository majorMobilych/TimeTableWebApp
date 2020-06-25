package com.web.app.util;

import com.web.app.hibernate.entity.AgendaEntity;
import com.web.app.model.DayOfWeek;

import java.util.Comparator;

public class ComparatorFactory {

    private ComparatorFactory() {
    }

    public static Comparator<AgendaEntity> getMainComparator() {
        return (agendaEntity, t1) -> {
            int diffDay = DayOfWeek.valueOf(agendaEntity.getDay().toUpperCase()).day - DayOfWeek.valueOf(t1.getDay().toUpperCase()).day;
            if (diffDay == 0) {
                return agendaEntity.getTime().compareTo(t1.getTime());
            }
            return diffDay;
        };
    }
}

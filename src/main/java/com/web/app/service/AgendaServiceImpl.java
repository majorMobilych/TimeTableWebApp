package com.web.app.service;

import com.web.app.hibernate.entity.AgendaEntity;
import com.web.app.model.DayOfWeek;
import com.web.app.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class AgendaServiceImpl implements AgendaService {
    private final AgendaRepository agendaRepository;

    @Autowired
    public AgendaServiceImpl(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    @Override
    public List<AgendaEntity> sortedUsersEntity(String userName) {
        List<AgendaEntity> agendaEntities = agendaRepository.getUsersAgenda(userName);
        Comparator<AgendaEntity> comparator = (agendaEntity, t1) -> {
            int diffDay = DayOfWeek.valueOf(agendaEntity.getDay().toUpperCase()).day - DayOfWeek.valueOf(t1.getDay().toUpperCase()).day;
            if (diffDay == 0) {
                return agendaEntity.getTime().compareTo(t1.getTime());
            }
            return diffDay;
        };
        agendaEntities.sort(comparator);
        return agendaEntities;
    }
}

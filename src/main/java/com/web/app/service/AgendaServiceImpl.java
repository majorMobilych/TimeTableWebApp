package com.web.app.service;

import com.web.app.hibernate.entity.AgendaEntity;
import com.web.app.repository.AgendaRepository;
import com.web.app.util.ComparatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        agendaEntities.sort(ComparatorFactory.getMainComparator());
        return agendaEntities;
    }
}

package com.web.app.repository;

import com.web.app.hibernate.entity.AgendaEntity;
import com.web.app.model.AgendaDTO;

import java.util.List;

public interface AgendaRepository {
    List<AgendaEntity> getUsersAgenda(String userName);
    void updateUsersAgenda(AgendaDTO agendaDTO);
    void saveUsersAgenda(AgendaDTO agendaDTO);
    void deleteUsersAgenda(int id);
}
package com.web.app.repository;

import com.web.app.hibernate.entity.AgendaEntity;

import java.util.List;

public interface AgendaRepository {
    List<AgendaEntity> getUsersAgenda(String userName);
}

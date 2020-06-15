package com.web.app.service;

import com.web.app.hibernate.entity.AgendaEntity;

import java.util.List;

public interface AgendaService {
    List<AgendaEntity> sortedUsersEntity(String userName);
}

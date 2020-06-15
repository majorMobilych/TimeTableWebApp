package com.web.app.util;

import com.web.app.hibernate.entity.AgendaEntity;
import com.web.app.hibernate.entity.UsersEntity;
import com.web.app.model.AgendaDTO;
import com.web.app.model.UsersDTO;

public class DTOEntityConverter {
    public static AgendaEntity agendaToEntity(AgendaDTO agendaDTO) {
        return new AgendaEntity(agendaDTO.getId(), agendaDTO.getUsername(), agendaDTO.getDay(),
                agendaDTO.getTime(), agendaDTO.getNote());
    }

    public static UsersEntity usersToEntity(UsersDTO usersDTO) {
        return new UsersEntity(usersDTO.getLogin(), usersDTO.getName(), usersDTO.getPassword());
    }
}
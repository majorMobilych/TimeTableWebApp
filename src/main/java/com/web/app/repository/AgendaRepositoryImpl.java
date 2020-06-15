package com.web.app.repository;

import com.web.app.hibernate.entity.AgendaEntity;
import com.web.app.model.AgendaDTO;
import com.web.app.util.DTOEntityConverter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AgendaRepositoryImpl implements AgendaRepository {
    private final SessionFactory localSessionFactoryBean;

    @Autowired
    public AgendaRepositoryImpl(SessionFactory localSessionFactoryBean) {
        this.localSessionFactoryBean = localSessionFactoryBean;
    }

    @Override
    public List<AgendaEntity> getUsersAgenda(String userName) {
        Session currentSession = localSessionFactoryBean.openSession();
        List list = currentSession.createQuery("FROM AgendaEntity WHERE username = :username")
                .setParameter("username", userName).list();
        currentSession.close();
        return list;
    }

    @Override
    public void updateUsersAgenda(AgendaDTO agendaDTO) {
        Session session = localSessionFactoryBean.openSession();
        session.beginTransaction();
        session.update(DTOEntityConverter.agendaToEntity(agendaDTO));
        session.getTransaction().commit();
        session.close();
    }


    @Override
    public void saveUsersAgenda(AgendaDTO agendaDTO) {
        Session session = localSessionFactoryBean.openSession();
        session.beginTransaction();
        session.save(DTOEntityConverter.agendaToEntity(agendaDTO));
        session.getTransaction().commit();
        session.close();
    }
}

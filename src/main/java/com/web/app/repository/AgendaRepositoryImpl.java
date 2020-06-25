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
        Session session = localSessionFactoryBean.openSession();
        List<AgendaEntity> usersAgenda = session.createQuery("FROM AgendaEntity WHERE username = :username",
                AgendaEntity.class).setParameter("username", userName).list();
        session.close();
        return usersAgenda;
    }

    @Override
    public void updateUsersAgenda(AgendaDTO agendaDTO) {
        Session session = localSessionFactoryBean.openSession();
        //TODO: что означает транзакция и зачем она тут нужна?
        session.beginTransaction();
        session.update(DTOEntityConverter.agendaToEntity(agendaDTO));
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveUsersAgenda(AgendaDTO agendaDTO) {
        Session session = localSessionFactoryBean.openSession();
        //TODO: что означает транзакция и зачем она тут нужна?
        session.beginTransaction();
        session.save(DTOEntityConverter.agendaToEntity(agendaDTO));
        session.getTransaction().commit();
        session.close();
    }

    //TODO: я пока написал простой createQuery, но что такое NATIVEQUERY? + не факт, что это метод работает(я не тестил)
    @Override
    public void deleteUsersAgenda(int id) {
        Session session = localSessionFactoryBean.openSession();
        session.createQuery("DELETE FROM AgendaEntity WHERE id = :id").setParameter("id", id).executeUpdate();
        session.close();
    }
}

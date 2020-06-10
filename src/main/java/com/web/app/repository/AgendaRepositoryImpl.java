package com.web.app.repository;

import com.web.app.hibernate.entity.AgendaEntity;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AgendaRepositoryImpl implements AgendaRepository {
    private final SessionFactory localSessionFactoryBean;

    @Override
    public List<AgendaEntity> getUsersAgenda(String userName) {
        Session currentSession = localSessionFactoryBean.openSession();
        Query query = currentSession.createQuery("FROM AgendaEntity WHERE username = :username");
        query.setParameter("username", userName);
        List<AgendaEntity> list = query.list();
        currentSession.close();
        return list;
    }

}

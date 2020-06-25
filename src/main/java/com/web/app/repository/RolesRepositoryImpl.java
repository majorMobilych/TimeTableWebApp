package com.web.app.repository;

import com.google.common.collect.Lists;
import com.web.app.hibernate.entity.RolesEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RolesRepositoryImpl implements RolesRepository {
    private final SessionFactory localSessionFactoryBean;

    @Autowired
    public RolesRepositoryImpl(SessionFactory localSessionFactoryBean) {
        this.localSessionFactoryBean = localSessionFactoryBean;
    }

    @Override
    public List<String> getRoles() {
        Session session = localSessionFactoryBean.openSession();
        List<RolesEntity> fromRolesEntity = session.createQuery("FROM RolesEntity", RolesEntity.class).list();
        return fromRolesEntity.stream().map(RolesEntity::getRole).collect(Collectors.toList());
    }
}

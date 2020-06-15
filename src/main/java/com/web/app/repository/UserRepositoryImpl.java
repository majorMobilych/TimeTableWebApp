package com.web.app.repository;

import com.web.app.hibernate.entity.UsersEntity;
import com.web.app.model.CheckUserStatus;
import com.web.app.model.UsersDTO;
import com.web.app.util.DTOEntityConverter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class UserRepositoryImpl implements UserRepository {
    private final SessionFactory localSessionFactoryBean;

    @Autowired
    public UserRepositoryImpl(SessionFactory localSessionFactoryBean) {
        this.localSessionFactoryBean = localSessionFactoryBean;
    }

    @Override
    public void saveUser(UsersDTO usersDTO) {
        if (checkUser(usersDTO.getLogin(), usersDTO.getPassword()).getCheckUserStatus()
                .equals(CheckUserStatus.SUCCESS.name())) {
            Session session = localSessionFactoryBean.openSession();
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(DTOEntityConverter.usersToEntity(usersDTO));
            transaction.commit();
            session.close();
            //TODO НАПИСАТЬ НОРМАЛЬНУЮ ЛОГИКУ В ЭЛСЕ, ЖЕЛАТЕЛЬНО С ТРАНЗАКШ МЭНЕДЖЕРОМ
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public UsersDTO checkUser(String login, String password) {
        Session currentSession = localSessionFactoryBean.openSession();
        UsersEntity selectUser = (UsersEntity) currentSession.createQuery("FROM UsersEntity WHERE login = :login").setParameter("login", login)
                .uniqueResult();
        currentSession.close();
        if (selectUser == null) {
            log.error("USES DOES NOT EXIST");
            return new UsersDTO(CheckUserStatus.USER_NOT_FOUND.name());
            // throw new NotExistingUserException("Not existing user");
        } else {
            if (!password.equals(selectUser.getPassword())) {
                log.error("PASSWORD IS INCORRECT");
                return new UsersDTO(CheckUserStatus.INCORRECT_PASSWORD.name());
                // throw new IncorrectPasswordException("Incorrect user password");
            } else {
                log.error("LOG IN SUCCESS");
                return new UsersDTO(selectUser.getLogin(), selectUser.getName(), selectUser.getPassword(),
                        CheckUserStatus.SUCCESS.name());
            }
        }
    }
}
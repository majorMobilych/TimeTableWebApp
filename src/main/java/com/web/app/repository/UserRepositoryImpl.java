package com.web.app.repository;

import com.web.app.exceptions.IncorrectPasswordException;
import com.web.app.exceptions.NotExistingUserException;
import com.web.app.hibernate.entity.UsersEntity;
import com.web.app.model.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserRepositoryImpl implements UserRepository {
    private final SessionFactory localSessionFactoryBean;

    public UserRepositoryImpl(SessionFactory localSessionFactoryBean) {
        this.localSessionFactoryBean = localSessionFactoryBean;
    }

    @Override
    public void saveUser(UserDTO userDTO) {
        Session currentSession = localSessionFactoryBean.openSession();
        Transaction transaction = currentSession.beginTransaction();
        UsersEntity usersEntity = new UsersEntity(userDTO.getLogin(), userDTO.getName(), userDTO.getPassword());
        System.out.println("usersEntity = " + usersEntity);
        System.out.println("userDTO = " + userDTO);
        currentSession.save(new UsersEntity(userDTO.getLogin(), userDTO.getName(),
                userDTO.getPassword()));
        transaction.commit();
        currentSession.close();
    }

    @Override
    public UsersEntity getUser(String login, String password) throws NotExistingUserException, IncorrectPasswordException {
        Session currentSession = localSessionFactoryBean.openSession();
        Query isUserPresent = currentSession.createQuery("FROM UsersEntity WHERE login = :login");
        isUserPresent.setParameter("login", login);
        UsersEntity selectUser = (UsersEntity) isUserPresent.uniqueResult();
        currentSession.close();
        if (selectUser == null) {
            throw new NotExistingUserException("Not existing user");
        } else {
            if (!password.equals(selectUser.getPassword())) {
                log.info("PASSWORD IS INCORRECT");
                throw new IncorrectPasswordException("Incorrect user password");
            } else {
                log.info("LOG IN SUCCESS");
                return selectUser;
            }
        }
    }
}
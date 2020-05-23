package com.web.app.repository;

import com.web.app.exceptions.IncorrectPasswordException;
import com.web.app.exceptions.NotExistingUserException;
import com.web.app.hibernate.entity.UsersEntity;
import com.web.app.model.UserDTO;
import lombok.SneakyThrows;
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
        currentSession.save(new UsersEntity(userDTO.getUserEmail(), userDTO.getUserName(),
                userDTO.getUserPassword()));
        transaction.commit();
        currentSession.close();
    }

    @SneakyThrows
    @Override
    public UsersEntity getUser(String userEmail, String userPassword) {
        Session currentSession = localSessionFactoryBean.openSession();
        Query isUserPresent = currentSession.createQuery("FROM UsersEntity WHERE useremail = :userEmail");
        isUserPresent.setParameter("userEmail", userEmail);
        UsersEntity selectUser = (UsersEntity) isUserPresent.uniqueResult();
        if (selectUser == null) {
            throw new NotExistingUserException();
        } else {
            if (!userPassword.equals(selectUser.getUserpassword())) {
                log.info("PASSWORD IS INCORRECT");
                throw new IncorrectPasswordException();
            } else {
                log.info("LOG IN SUCCESS");
                return selectUser;
            }
        }
    }
}
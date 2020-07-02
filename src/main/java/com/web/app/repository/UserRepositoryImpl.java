package com.web.app.repository;

import com.web.app.exceptions.UserAlreadyExistsException;
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

import java.util.List;

@Repository
@Slf4j
public class UserRepositoryImpl implements UserRepository {
    private final SessionFactory localSessionFactoryBean;

    @Autowired
    public UserRepositoryImpl(SessionFactory localSessionFactoryBean) {
        this.localSessionFactoryBean = localSessionFactoryBean;
    }

    @Override
    public void saveUser(UsersDTO usersDTO) throws UserAlreadyExistsException {
        if (isUserUnique(usersDTO)) {
            Session session = localSessionFactoryBean.openSession();
            //TODO: что означает транзакция и зачем она тут нужна?
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(DTOEntityConverter.usersToEntity(usersDTO));
            transaction.commit();
            session.close();
            log.debug("USER SUCCESSFULLY SAVED TO DATABASE");
        } else {
            throw new UserAlreadyExistsException("USER" + usersDTO.getName() + "ALREADY EXISTS");
        }
    }

    private boolean isUserUnique(UsersDTO usersDTO) {
        Session session = localSessionFactoryBean.openSession();
        List listUsersEntity = session
                .createQuery("FROM UsersEntity WHERE login = :login OR name = :username")
                .setParameter("login", usersDTO.getLogin()).setParameter("username", usersDTO.getName()).list();
        return listUsersEntity.size() == 0;
    }

    @Override
    public UsersDTO checkUser(String login, String password) {
        Session currentSession = localSessionFactoryBean.openSession();
        UsersEntity selectUser = (UsersEntity) currentSession.createQuery("FROM UsersEntity WHERE login = :login")
                .setParameter("login", login).uniqueResult();
        currentSession.close();
        if (selectUser == null) {
            log.error("USES DOES NOT EXIST");
            return new UsersDTO(CheckUserStatus.USER_NOT_FOUND.name());
        } else {
            if (!password.equals(selectUser.getPassword())) {
                log.error("PASSWORD IS INCORRECT");
                return new UsersDTO(CheckUserStatus.INCORRECT_PASSWORD.name());
            } else {
                log.error("LOG IN SUCCESS");
                return new UsersDTO(selectUser.getLogin(), selectUser.getName(), selectUser.getPassword(),
                        CheckUserStatus.SUCCESS.name());
            }
        }
    }

    @Override
    public UsersEntity getUser(String userLogin) {
        Session currentSession = localSessionFactoryBean.openSession();
        UsersEntity selectUser = (UsersEntity) currentSession.createQuery("FROM UsersEntity WHERE login = :login")
                .setParameter("login", userLogin).uniqueResult();
        currentSession.close();
        return selectUser;
    }
}
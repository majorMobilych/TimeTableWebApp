/*
package com.web.app.service;

import com.web.app.hibernate.entity.UsersEntity;
import com.web.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Qualifier("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String s) {
        System.out.println("s = " + s);
        UsersEntity user = userRepository.getUser(s);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        System.out.println("user = " + user);
        grantedAuthorities.add(new SimpleGrantedAuthority(String.valueOf(user.getRole_id())));
        System.out.println("grantedAuthorities = " + grantedAuthorities);
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
                grantedAuthorities);
    }
}
*/

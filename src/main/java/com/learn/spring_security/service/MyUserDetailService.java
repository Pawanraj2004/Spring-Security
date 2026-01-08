package com.learn.spring_security.service;

import com.learn.spring_security.model.UserPrincipal;
import com.learn.spring_security.model.Users;
import com.learn.spring_security.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user=userRepo.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("Not Found");
        }
        return new UserPrincipal(user);
    }
}

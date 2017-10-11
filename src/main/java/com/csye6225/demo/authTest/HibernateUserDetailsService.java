package com.csye6225.demo.authTest;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import com.csye6225.demo.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("hibernateUserDetailsService")
public class HibernateUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Resource(name="userRepository")
    public void setBaseCollectionDao(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserDetails loadUserByUsername(String userEmail)
            throws UsernameNotFoundException
    {
        com.csye6225.demo.pojos.User users = userRepository.findByEmail(userEmail);

        if(users == null)
            throw new UsernameNotFoundException("USER: " + userEmail + " NOT EXIST");

//        String[] roles = users.getRoles().split(",");


        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

//        for(String role : roles)
//        {
            authorities.add(new SimpleGrantedAuthority(userEmail));
//        }


        return new User(users.getEmail(), users.getPassword(), authorities);
    }
}
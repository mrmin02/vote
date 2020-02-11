package com.vote.vote.service;

import com.vote.vote.repository.MemberJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


import com.vote.vote.db.dto.Member;


@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private MemberJpaRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{

        Member entityUser = userRepository.findByMemberId(username);
        
        if(ObjectUtils.isEmpty(entityUser)){
            throw new UsernameNotFoundException("Invalid username");
        }

        UserDetails user = 
				User
				.withUsername(entityUser.getMemberId())
					.password(entityUser.getPassword())
					.username(entityUser.getMemberId())
					.authorities(AuthorityUtils.createAuthorityList("USER"))
					.roles("USER")
				.build();
        return user;
    }

    
}
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

import com.vote.vote.config.CustomUserDetails;
import com.vote.vote.db.dto.Member;


@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private MemberJpaRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{

        Member entityUser = userRepository.findByUserid(username);
        
        if(ObjectUtils.isEmpty(entityUser)){
            throw new UsernameNotFoundException("Invalid username");
        }

        // UserDetails user = 
		// 		User
		// 		.withUsername(entityUser.getMemberId())
		// 			.password(entityUser.getPassword())
		// 			.username(entityUser.getMemberId())
		// 			.authorities(AuthorityUtils.createAuthorityList("USER"))
		// 			.roles("USER")
        // 		.build();
        
        CustomUserDetails user2 = new CustomUserDetails();
        user2.setID(entityUser.getUserid());
        user2.setPASSWORD(entityUser.getPassword());
        user2.setIMG(entityUser.getProfile());
        user2.setNAME(entityUser.getName());
        user2.setAUTHORITY("USER");
        //  = (CustomUserDetails)usert;
        
        // user.setIMG("img");
        // user.setNAME("이름 출력되나?");

        return user2;
    }

    
}
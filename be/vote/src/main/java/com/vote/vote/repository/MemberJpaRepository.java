package com.vote.vote.repository;

import java.util.ArrayList;

import com.vote.vote.db.dto.Member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member,String>{
    public Member findByUserid(String userid);
    public ArrayList<Member> findAll();
}
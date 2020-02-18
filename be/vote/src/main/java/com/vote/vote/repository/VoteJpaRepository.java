package com.vote.vote.repository;

import java.util.ArrayList;

import com.vote.vote.db.dto.Vote;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteJpaRepository extends JpaRepository<Vote,String>{
    // public Vote findByMemberId(String memberId);
    public Vote findById(int id);
    public ArrayList<Vote> findAll();
}
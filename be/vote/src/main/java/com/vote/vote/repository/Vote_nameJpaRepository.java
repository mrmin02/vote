package com.vote.vote.repository;

import java.util.ArrayList;

import com.vote.vote.db.dto.Vote_name;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Vote_nameJpaRepository extends JpaRepository<Vote_name,String>{
    // public Vote findByMemberId(String memberId);
    public Vote_name findById(int id);
    public ArrayList<Vote_name> findAll();
}
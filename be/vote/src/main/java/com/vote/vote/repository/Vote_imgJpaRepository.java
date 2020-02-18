package com.vote.vote.repository;

import java.util.ArrayList;

import com.vote.vote.db.dto.Vote_img;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Vote_imgJpaRepository extends JpaRepository<Vote_img,String>{
    // public Vote findByMemberId(String memberId);
    public Vote_img findById(int id);
    public ArrayList<Vote_img> findAll();
}
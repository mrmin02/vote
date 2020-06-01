package com.vote.vote.repository;

import java.util.ArrayList;

import com.vote.vote.db.dto.Voter;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VoterJpaRepository extends JpaRepository<Voter,String>{

    public Voter findByVoteId(int voteId);
    public Voter findByMemberId(int memberId);
    

    public Voter findByVoteIdAndMemberId(int voteId, int memberId);

    // public Voter findById(int id);
    public ArrayList<Voter> findAll();
}
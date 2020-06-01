package com.vote.vote.repository;

import com.vote.vote.db.dto.VoterHash;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VoterHashJpaRepository extends JpaRepository<VoterHash, String> {
    
}
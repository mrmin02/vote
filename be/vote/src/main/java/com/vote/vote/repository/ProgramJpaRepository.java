package com.vote.vote.repository;

import com.vote.vote.db.dto.Program;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramJpaRepository extends JpaRepository<Program, String> {

    public Program findById(int id);
    
}
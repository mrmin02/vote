package com.vote.vote.repository;

import java.util.ArrayList;

import com.vote.vote.db.dto.Vote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

public interface VoteJpaRepository extends JpaRepository<Vote,String>{
    // public Vote findByMemberId(String memberId);
    public Vote findById(int id);
    public ArrayList<Vote> findAll();

    @Query(value = "select * from vote where starttime <= ?1 and ?1 <= endtime",nativeQuery = true)
    public ArrayList<Vote> findDoingVote(String nowTime);


    @Query(value = "select * from vote where ?1 < starttime",nativeQuery = true)
    public ArrayList<Vote> findPreVote(String nowTime);

    @Query(value = "select * from vote where endtime < ?1",nativeQuery = true)
    public ArrayList<Vote> findEndVote(String nowTime);


    @Query(value = "select * from vote where (:aa is null or voteid = :aa)", nativeQuery = true)
    public ArrayList<Vote> findByOptionVotes(@Nullable @Param("aa") int option);
}
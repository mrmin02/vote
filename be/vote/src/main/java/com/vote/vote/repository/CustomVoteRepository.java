package com.vote.vote.repository;

import java.util.ArrayList;
import java.util.List;

import com.vote.vote.db.dto.Vote;

import org.json.simple.JSONObject;
import org.springframework.data.domain.Pageable;

public interface CustomVoteRepository{
    List<Vote> customFindVotes(String time,Pageable page,int state,int program, String text);
    
    long getFindVotesCount();
}
package com.vote.vote.db.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="voter")
public class Voter{
    
    @Id
    @Column(name="id", nullable=false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="VOTER_SEQ_GENERATOR")
    @SequenceGenerator(name="VOTER_SEQ_GENERATOR", sequenceName="VOTER_SEQ", allocationSize = 1)
    private int id;

    @Column(name="voteid",nullable=false)
    private int voteId;

    @Column(name="userid",nullable=false)
    private String userId;

    @Column(nullable=true)
    private int state;

    @Column(nullable=true)
    private String hash;


    public int getId(){
        return id;
    }
    public int getVoteId(){
        return voteId;
    }
    public String getUserId(){
        return userId;
    }
    public int getState(){
        return state;
    }
    public String getHash(){
        return hash;
    }

    public void setId(int id){
        this.id = id;
    }
    public void setVoteId(int voteId){
        this.voteId = voteId;
    }
    public void setUserid(String userId){
        this.userId = userId;
    }
    public void setState(int state){
        this.state = state;
    }
    public void setHash(String hash){
        this.hash = hash;
    }


}
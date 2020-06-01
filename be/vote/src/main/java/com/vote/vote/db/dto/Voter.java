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

    @Column(name="r_id",nullable=false)
    private int memberId;

    @Column(nullable=false)
    private int state;

    public int getId(){
        return id;
    }
    public int getVoteId(){
        return voteId;
    }

    public int getState(){
        return state;
    }


    public void setId(int id){
        this.id = id;
    }
    public void setVoteId(int voteId){
        this.voteId = voteId;
    }

    public void setState(int state){
        this.state = state;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }




}
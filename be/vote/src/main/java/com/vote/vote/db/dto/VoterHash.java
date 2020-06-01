package com.vote.vote.db.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="voter_hash")
public class VoterHash {
    @Id
    @Column(name="h_id", nullable=false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="VOTER_HASH_SEQ_GENERATOR")
    @SequenceGenerator(name="VOTER_HASH_SEQ_GENERATOR", sequenceName="VOTER_HASH_SEQ", allocationSize = 1)
    private int id;
    
    @Column(name="voteid",nullable=false)
    private int voteId; // 투표번호

    @Column(name="voterid",nullable=false)
    private int voterId; // 유권자번호

    @Column(name="r_id", nullable=false)
    private int memberId;// 유저아이디

    @Column(nullable=true)
    private String hash;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVoteId() {
        return voteId;
    }

    public void setVoteId(int voteId) {
        this.voteId = voteId;
    }





    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getVoterId() {
        return voterId;
    }

    public void setVoterId(int voterId) {
        this.voterId = voterId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }


}
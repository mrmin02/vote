package com.vote.vote.db.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name="candidate")
public class Candidate{

    @Id
    @Column(name="id", nullable=false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CANDIDATE_SEQ_GENERATOR")
    @SequenceGenerator(name="CANDIDATE_SEQ_GENERATOR", sequenceName="CANDIDATE_SEQ", allocationSize = 1)
    private int id;


    @Column(name="voteid", nullable=false)
    private int voteId;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private String img;



    public int getId(){
        return id;
    }
    public int getVoteId(){
        return voteId;
    }
    public String getName(){
        return name;
    }
    public String getImg(){
        return img;
    }
    
    public void setId(int id){
        this.id = id;
    }
    public void setVoteId(int voteId){
        this.voteId = voteId;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setImg(String img){
        this.img = img;
    }



}
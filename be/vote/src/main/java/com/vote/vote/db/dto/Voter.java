package com.vote.vote.db.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Voter{
    @Id
    @Column(name="id", nullable=false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column
    private int voteId;

    @Column 
    private String memberId;

    @Column 
    private int state;


    // @Column 
    // private String privateKey;

    // @Column 
    // private String address;


    public int getId(){
        return id;
    }
    public int getVoteId(){
        return voteId;
    }
    public String getMemberId(){
        return memberId;
    }
    public int getState(){
        return state;
    }

    // public String getPrivateKey(){
    //     return privateKey;
    // }
    // public String address(){
    //     return address;
    // }



    public void setId(int id){
        this.id = id;
    }
    public void setVoteId(int voteId){
        this.voteId = voteId;
    }
    public void setMemberId(String memberId){
        this.memberId = memberId;
    }
    public void setState(int state){
        this.state = state;
    }

    // public void setPrivateKey(String privateKey){
    //     this.privateKey = privateKey;
    // }
    // public void setAddress(String address){
    //     this.address = address;
    // }


}
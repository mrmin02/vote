package com.vote.vote.db.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="member")
public class Member{

    @Id
    @Column(name="id", nullable=false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column
    private String memberId;

    @Column
    private String password;

    public int getId(){
        return id;
    }
    public String getMemberId(){
        return memberId;
    }
    public String getPassword(){
        return password;
    }

    public void setId(int id){
        this.id = id;
    }
    public void setUserId(String memberId){
        this.memberId = memberId;
    }
    public void setPassword(String password){
        this.password = password;
    }

}

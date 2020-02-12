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

    @Column
    private String name;

    @Column
    private String birth;

    @Column
    private String phone;

    @Column
    private String email;

    public int getId(){
        return id;
    }
    public String getMemberId(){
        return memberId;
    }
    public String getPassword(){
        return password;
    }
    public String getName(){
        return name;
    }
    public String getBirth(){
        return birth;
    }
    public String getPhone(){
        return phone;
    }
    public String getEmail(){
        return email;
    }

    public void setId(int id){
        this.id = id;
    }
    public void setMemberId(String memberId){
        this.memberId = memberId;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setBirth(String birth){
        this.birth = birth;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    public void setEmail(String email){
        this.email = email;
    }
}

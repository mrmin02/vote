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

    @Column(nullable=true)
    private String password;

    @Column
    private String name;

    @Column(nullable=true)
    private String birth;

    @Column(nullable=true)
    private String phone;

    @Column(nullable=true)
    private String email;

    @Column(nullable=true)
    private String privateKey;

    @Column(nullable=true)
    private String address;

    @Column(nullable=true)
    private String img;

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

    public String getPrivateKey(){
        return privateKey;
    }
    public String getAddress(){
        return address;
    }

    public String getImg(){
        return img;
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

    public void setPrivateKey(String privateKey){
        this.privateKey = privateKey;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public void setImg(String img){
        this.img = img;
    }
}

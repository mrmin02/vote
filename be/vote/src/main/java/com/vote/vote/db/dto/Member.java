package com.vote.vote.db.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.SequenceGenerator;



@Entity
@Table(name="r_user")
public class Member{

    @Id
    @Column(name="r_id", nullable=false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="R_USER_SEQ_GENERATOR")
    @SequenceGenerator(name="R_USER_SEQ_GENERATOR", sequenceName="R_USER_SEQ", allocationSize = 1)
    private int no;

    @Column(nullable=false)
    private String userid;

    @Column(name="userpw",nullable=true)
    private String password;

    @Column(name="username",nullable=false)
    private String name;

    @Column(nullable=true)
    private String gender;

    @Column(nullable=true)
    private String birth;

    @Column(nullable=true)
    private String nickname;

    @Column(nullable=true)
    private String profile;

    @Column(nullable=true)
    private String phone;

    @Column(nullable=true)
    private String joindate;

    @Column(nullable=true)
    private String addr;

    @Column(nullable=true)//상세주소
    private String addr2;

    @Column(nullable=true)
    private int point;
   
    public void setNo(int no){
        this.no = no;
    }
    public int getNo(){
        return this.no;
    }
    public void setUserid(String userid){
        this.userid = userid;
    }
    public String getUserid(){
        return this.userid;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return this.password;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setGender(String gender){
        this.gender = gender;
    }
    public String getGender(){
        return this.gender;
    }
    public void setBirth(String birth){
        this.birth = birth;
    }
    public String getBirth(){
        return this.birth;
    }
    public void setNickname(String nickname){
        this.nickname = nickname;
    }
    public String getNickname(){
        return this.nickname;
    }
    public void setProfile(String profile){
        this.profile = profile;
    }
    public String getProfile(){
        return this.profile;
    }
    public void setPhone(String Phone){
        this.phone = phone;
    }
    public String getPhone(){
        return this.phone;
    }
    public void setJoindate(String joindate){
        this.joindate = joindate;
    }
    public String getJoindate(){
        return this.joindate;
    }
    public void setAddr(String addr){
        this.addr = addr;
    }
    public String getAddr(){
        return this.addr;
    }
    public void setAddr2(String addr2){
        this.addr2 = addr2;
    }
    public String getAddr2(){
        return this.addr2;
    }

    public void setPoint(int Point){
        this.point = point;
    }
    public int getPoint(){
        return this.point;
    }
}

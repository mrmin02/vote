package com.vote.vote.db.dto;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.SequenceGenerator;


@Entity
@Table(name="vote")//name="vote" 생략가능
public class Vote{
    
    @Id
    @Column(name="voteid", nullable=false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="VOTE_SEQ_GENERATOR")
    @SequenceGenerator(name="VOTE_SEQ_GENERATOR", sequenceName="VOTE_SEQ", allocationSize = 1)
    private int id;

    @Column(nullable=false)
    private String title;

    @Column(nullable=false)
    private String writer;

    @Column(nullable=true)
    private String address;

    @Column
    private int count;

    @Column(name="starttime", nullable=true)
    private String startTime;
    @Column(name="endTime", nullable=true)
    private String endTime;

    @Column
    private String thumbNail;

    @Column(nullable=true)
    private int program_id;


    public int getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }
    public String getWriter(){
        return writer;
    }
    public String getAddress(){
        return address;
    }
    public int getCount(){
        return count;
    }
    public String getStartTime(){
        return startTime;
    }
    public String getEndTime(){
        return endTime;
    }
    public String getThumbnail(){
        return thumbNail;
    }
    public int getProgram_id(){
        return this.program_id;
    }
    public Long getLongStartTime(){
        return Long.parseLong(this.startTime.replaceAll("[^0-9]",""));
    }
    public Long getLongEndTime(){
        return Long.parseLong(this.endTime.replaceAll("[^0-9]",""));
    }

    public void setId(int id){
        this.id = id;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setWriter(String writer){
        this.writer = writer;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public void setCount(int count){
        this.count = count;
    }
    public void setStartTime(String startTime){
        this.startTime = startTime;
    }
    public void setEndTIme(String endTime){
        this.endTime = endTime;
    }
    public void setThumbnail(String thumbNail){
        this.thumbNail = thumbNail;
    }
    public void setProgram_id(int program_id){
        this.program_id = program_id;
    }

}
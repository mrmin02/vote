package com.vote.vote.db.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Vote{
    @Id
    @Column(name="id", nullable=false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column
    private String title;

    @Column 
    private String writer;

    @Column 
    private int img;

    @Column 
    private int name;

    @Column 
    private String address;

    @Column
    private int count;

    public int getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }
    public String getWriter(){
        return writer;
    }
    public int getImg(){
        return img;
    }
    public int getName(){
        return name;
    }
    public String getAddress(){
        return address;
    }
    public int getCount(){
        return count;
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
    public void setImg(int img){
        this.img = img;
    }
    public void setName(int name){
        this.name = name;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public void setCount(int count){
        this.count = count;
    }

}
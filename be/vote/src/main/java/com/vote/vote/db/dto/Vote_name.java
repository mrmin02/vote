package com.vote.vote.db.dto;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Vote_name{

    public Vote_name(){}
    // Vote_name(String name1, String name2, String name3, String name4, String name5, String name6){
    //     this.name1 = name1;
    //     this.name2 = name2;
    //     this.name3 = name3;
    //     this.name4 = name4;
    //     this.name5 = name5;
    //     this.name6 = name6;
    // }
    public Vote_name(ArrayList<String> names){
        this.name1 = names.get(0);
        this.name2 = names.get(1);
        this.name3 = names.get(2);
        this.name4 = names.get(3);
        this.name5 = names.get(4);
        this.name6 = names.get(5);
    }


    @Id
    @Column(name="id", nullable=false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(nullable=true)
    private String name1;
    
    @Column(nullable=true)
    private String name2;

    @Column(nullable=true)
    private String name3;

    @Column(nullable=true)
    private String name4;

    @Column(nullable=true)
    private String name5;

    @Column(nullable=true)
    private String name6;

    public int getId(){
        return id;
    }
    
    public String getName1(){
        return name1;
    } 
    public String getName2(){
        return name2;
    } 
    public String getName3(){
        return name3;
    } 
    public String getName4(){
        return name4;
    } 
    public String getName5(){
        return name5;
    } 
    public String getName6(){
        return name6;
    } 

    public void setId(int id){
        this.id = id;
    }

    public void setName1(String name1){
        this.name1 = name1;
    }
    public void setName2(String name2){
        this.name2 = name2;
    }
    public void setName3(String name3){
        this.name3 = name3;
    }
    public void setName4(String name4){
        this.name4 = name4;
    }
    public void setName5(String name5){
        this.name5 = name5;
    }
    public void setName6(String name6){
        this.name6 = name6;
    }


    public ArrayList<String> getAllName(){
        ArrayList<String> name = new ArrayList<String>();

        name.add(name1);
        name.add(name2);
        name.add(name3);
        name.add(name4);
        name.add(name5);
        name.add(name6);

        return name;
    }
}

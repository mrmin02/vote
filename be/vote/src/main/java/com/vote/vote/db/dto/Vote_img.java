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
public class Vote_img{

    public Vote_img(){

    }
    // Vote_img(String img1, String img2, String img3, String img4, String img5, String img6){
    //     this.img1 = img1;
    //     this.img2 = img2;
    //     this.img3 = img3;
    //     this.img4 = img4;
    //     this.img5 = img5;
    //     this.img6 = img6;
    // }
    public Vote_img(ArrayList<String> imgs){
        this.img1 = imgs.get(0);
        this.img2 = imgs.get(1);
        this.img3 = imgs.get(2);
        this.img4 = imgs.get(3);
        this.img5 = imgs.get(4);
        this.img6 = imgs.get(5);
    }



    @Id
    @Column(name="id", nullable=false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(nullable=true)
    private String img1;
    
    @Column(nullable=true)
    private String img2;

    @Column(nullable=true)
    private String img3;

    @Column(nullable=true)
    private String img4;

    @Column(nullable=true)
    private String img5;

    @Column(nullable=true)
    private String img6;


    public int getId(){
        return id;
    }
    public String getImg1(){
        return img1;
    } 

    public String getImg2(){
        return img2;
    } 
    public String getImg3(){
        return img3;
    } 
    public String getImg4(){
        return img4;
    } 
    public String getImg5(){
        return img5;
    } 
    public String getImg6(){
        return img6;
    } 

    public void setId(int id){
        this.id = id;
    }

    public void setImg1(String img1){
        this.img1 = img1;
    }
    public void setImg2(String img2){
        this.img2 = img2;
    }
    public void setImg3(String img3){
        this.img3 = img3;
    }
    public void setImg4(String img4){
        this.img4 = img4;
    }
    public void setImg5(String img5){
        this.img5 = img5;
    }
    public void setImg6(String img6){
        this.img6 = img6;
    }


    public ArrayList<String> getAllImg(){
        ArrayList<String> imgs = new ArrayList<String>();

        imgs.add(img1);
        imgs.add(img2);
        imgs.add(img3);
        imgs.add(img4);
        imgs.add(img5);
        imgs.add(img6);

        return imgs;
    }

}
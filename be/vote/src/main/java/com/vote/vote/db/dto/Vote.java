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

    @Column(name="r_id",nullable=false)
    private int memberId;

    @Column(nullable=true)
    private String address;

    @Column
    private int count;

    @Column(name="starttime", nullable=false)
    private String startTime;
    @Column(name="endTime", nullable=false)
    private String endTime;

    @Column
    private String thumbNail;

    @Column(name="program_id",nullable=false)
    private int programId;

    @Column(nullable=false)
    private int selectNum;

    @Column(nullable=false)
    private int voteCanNum;

    @Column(nullable=false)
    private int showState;

    @Column(name="resultshowtime", nullable =false)
    private String resultShowTime;


    public int getId(){
        return id;
    }

    public String getTitle(){
        return title;
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


    public int getSelectNum() {
        return selectNum;
    }

    public void setSelectNum(int selectNum) {
        this.selectNum = selectNum;
    }

    public int getVoteCanNum() {
        return voteCanNum;
    }

    public void setVoteCanNum(int voteCanNum) {
        this.voteCanNum = voteCanNum;
    }

    public int getShowState() {
        return showState;
    }

    public void setShowState(int showState) {
        this.showState = showState;
    }


    public String getResultShowTime() {
        return resultShowTime;
    }
    public Long getLongResultShowTime() {
        return Long.parseLong(this.resultShowTime.replaceAll("[^0-9]",""));
    }

    public void setResultShowTime(String resultShowTime) {
        this.resultShowTime = resultShowTime;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getProgramId() {
        return programId;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }

}
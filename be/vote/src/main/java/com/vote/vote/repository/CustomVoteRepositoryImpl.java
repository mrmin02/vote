package com.vote.vote.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.vote.vote.db.dto.QVote;
import com.vote.vote.db.dto.Vote;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;




// @Transactional(readOnly = true)
@Repository
public class CustomVoteRepositoryImpl extends QuerydslRepositorySupport implements CustomVoteRepository{

    @PersistenceContext
    private EntityManager em;

    private QVote vote = QVote.vote;

    private Long count = 0L;

    public CustomVoteRepositoryImpl(){
        super(Vote.class);
    }

    @Override
    public List<Vote> customFindVotes(String time, Pageable page, int state, int program){//시간
        JPAQueryFactory query = new JPAQueryFactory(em);


        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (state == -1){
            System.out.println("전체 투표");
        }// 투표 목록 선택 안했으면,
        else if(state == 0){//시작전 투표
            System.out.println("시작 전 투표");
            booleanBuilder.and(vote.startTime.gt(time));// 시작시간 > 현재시간
        }
        else if(state == 2){// 마감된 투표
            System.out.println("마감된 투표");
            booleanBuilder.and(vote.endTime.loe(time)); // 종료시간 =< 현재시간
        }
        else{// state == 1
            System.out.println("현재 투표 투표");
            booleanBuilder.and(vote.startTime.loe(time)).and(vote.endTime.gt(time));
        }

        if(program == 0){
            System.out.println("전체 프로그램");
        }else{
            System.out.println(program+" 번 프로그램의 투표");
            booleanBuilder.and(vote.program_id.eq(program));
        }

        List<Vote> voteList =  query.select(vote).from(vote).offset(page.getOffset()).limit(page.getPageSize()).where(booleanBuilder).fetch();

        count = query.select(vote).from(vote).where(booleanBuilder).fetchCount();
    
        

       
        return voteList;
        
    }
    @Override
    public long getFindVotesCount(){
        return count;
    }
    
}
package com.mancity.user.club.domain;

import com.mancity.user.ClubMember.domain.ClubMember;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ClubMember> clubMembers = new ArrayList<>();

    //클럽 이름
    private String name;

    //클럽장 아이디
    private Long masterId;

    //엠블럼
    private String emblem;

    //인원
    private int memberCnt;

    //클럽 스코어
    private int score;

    //지역
    private String region;

    public void uploadEmblem(String url) {
        this.emblem = url;
    }

    public void addMasterInClubMember(ClubMember master) {
        log.info("마스터 클럽 멤버 add");
        this.clubMembers.add(master);
        this.memberCnt = clubMembers.size(); //인원 업데이트
    }

    public void joinMember(ClubMember clubMember) {
        log.info("memberCnt= {}", this.memberCnt);
        this.clubMembers.add(clubMember);
//        for (ClubMember cm: clubMembers) {
//            log.info("clubMember ={}", cm.getUser().getId());
//        }
        this.memberCnt = clubMembers.size(); //인원 업데이트
        log.info("추가 후 memberCnt= {}", this.memberCnt);
    }
}

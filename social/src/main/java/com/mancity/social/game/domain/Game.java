package com.mancity.social.game.domain;

import com.mancity.social.game.application.dto.request.GameDataInputDto;
import com.mancity.social.highlight.domain.Highlight;
import com.mancity.social.participant.domain.Participant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String replayUrl;

    private String boxImageUrl;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Highlight> highlights  = new ArrayList<>();

    private int gender;  // 1남자 2여자 3혼성

    private Long manager;

    private LocalDate startDate; // 경기일

    private int time; // 경기 시간대, 0~24

    private boolean isOver; // 경기 종료 여부

    private int playerNumber; // 총 인원 수

    private boolean isCalcOver;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn
    private Team teamA;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn
    private Team teamB;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn
    @Builder.Default
    private List<Player> playersA = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn
    @Builder.Default
    private List<Player> playersB = new ArrayList<>();

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Participant> participants = new ArrayList<>();

    @Enumerated
    private GameLevel level;

    private long courtId;

    public void updateHighlights(Highlight highlight) {
        this.highlights.add(highlight);
    }

    public void uploadVideo(String url) {
        this.replayUrl = url;
    }

    public void inputData(GameDataInputDto dto) {
        this.teamA = dto.getTeamA();
        this.teamB = dto.getTeamB();
        this.playersA.addAll(dto.getPlayersA());
        this.playersB.addAll(dto.getPlayersB());
    }

    public boolean isExistParticipant(Long userId) {
        for (Participant p : participants) {
            if (p.getUserId().equals(userId)) {
                return true;
            }
        }
        return false;
    }

    public void participate(Participant participant) {
        this.participants.add(participant);
    }

    public void updateCalcOver(){
        this.isCalcOver = true;
    }

    public void updateBoxImageUrl(String url){
        this.boxImageUrl = url;
    }
}

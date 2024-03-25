package com.mancity.social.game.application.dto.request;

import com.mancity.social.game.domain.Game;
import com.mancity.social.game.domain.GameLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GameCreateRequestDto {

    private String replayUrl;

    private int gender;

    private Long manager;

    private LocalDate startDate; // 경기일

    private int time; // 경기 시간대, 0~24

    private boolean isOver;

    private int playerNumber;

    private String level;

    private long courtId;

    public Game toEntity() {
        return Game.builder()
                .replayUrl("")
                .gender(gender)
                .manager(manager)
                .startDate(startDate)
                .time(time)
                .isOver(isOver)
                .playerNumber(playerNumber)
                .level(GameLevel.valueOf(level))
                .courtId(courtId)
                .build();
    }
}

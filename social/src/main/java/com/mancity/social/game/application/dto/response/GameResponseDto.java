package com.mancity.social.game.application.dto.response;

import com.mancity.social.game.domain.Game;
import com.mancity.social.game.domain.GameLevel;
import com.mancity.social.participant.application.dto.respose.ParticipantResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GameResponseDto {

    private long gameId;

    private String replayUrl;

    private List<String> highlights; // 회원 id,

    private int gender;

    private Long manager;

    private LocalDate startDate; // 경기일

    private int time; // 경기 시간대, 0~24

    private boolean isOver;

    private int playerNumber;

    private TeamResponseDto teamA;

    private TeamResponseDto teamB;

    private List<PlayerResonseDto> playersA;

    private List<PlayerResonseDto> playersB;

    private List<ParticipantResponseDto> participants;

    private String level;

    private long courtId;

    public static GameResponseDto from(Game game) {
        return GameResponseDto.builder()
                .gameId(game.getId())
                .replayUrl(game.getReplayUrl())
                .highlights(game.getHighlights())
                .gender(game.getGender())
                .manager(game.getManager())
                .startDate(game.getStartDate())
                .time(game.getTime())
                .isOver(game.isOver())
                .teamA(TeamResponseDto.fromTeamA(game))
                .teamB(TeamResponseDto.fromTeamB(game))
                .playersA(PlayerResonseDto.fromPlayersA(game))
                .playersB(PlayerResonseDto.fromPlayersB(game))
                .playerNumber(game.getPlayerNumber())
                .participants(game.getParticipants()
                        .stream()
                        .map(ParticipantResponseDto::from)
                        .toList())
                .level(game.getLevel().getGameLevel())
                .courtId(game.getCourtId())
                .build();
    }
}

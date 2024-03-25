package com.mancity.social.game.application.dto.response;

import com.mancity.social.game.domain.Game;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TeamDataResponseDto {

    private TeamResponseDto teamA;

    private TeamResponseDto teamB;

    public static TeamDataResponseDto from(Game game) {
        return TeamDataResponseDto.builder()
                .teamA(TeamResponseDto.fromTeamA(game))
                .teamB(TeamResponseDto.fromTeamB(game))
                .build();
    }
}

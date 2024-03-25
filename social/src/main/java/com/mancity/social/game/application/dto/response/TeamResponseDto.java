package com.mancity.social.game.application.dto.response;

import com.mancity.social.game.domain.Game;
import com.mancity.social.game.domain.Team;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TeamResponseDto {

    private int goal;

    private int pass;

    private int shot;

    private int shotOnTarget;

    public static TeamResponseDto fromTeamA(Game game) {
        return extractTeam(game.getTeamA());
    }

    public static TeamResponseDto fromTeamB(Game game) {
        return extractTeam(game.getTeamB());
    }

    private static TeamResponseDto extractTeam(Team team) {
        if (ObjectUtils.isEmpty(team)) {
            return TeamResponseDto.builder().build();
        }
        return TeamResponseDto.builder()
                .goal(team.getGoal())
                .pass(team.getPass())
                .shot(team.getShot())
                .shotOnTarget(team.getShotOnTarget())
                .build();
    }
}

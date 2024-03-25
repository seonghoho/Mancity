package com.mancity.social.game.application.dto.response;

import com.mancity.social.game.domain.Game;
import com.mancity.social.game.domain.Player;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PlayerResonseDto {

    private long id;

    private String nickname;

    private int speed; // 최고속도

    private int distanceCovered; // 총 이동 거리

    private int pass; // 총 패스 횟수

    private int shotsOnTarget; // 총 유효 슈팅 수

    private int shot; // 총 슈팅 횟수

    private int goal; // 총 득점 수

    private int assist; // 총 어시스트 수

    private int turnOverInOffense; // 공격 시 턴오버 당한 총 횟수

    private int turnOverInDefense; // 수비 시 턴오버 성공한 총 횟수

    public static List<PlayerResonseDto> fromPlayersA(Game game) {
        List<Player> players = game.getPlayersA();
        return transfer(players);
    }

    public static List<PlayerResonseDto> fromPlayersB(Game game) {
        List<Player> players = game.getPlayersB();
        return transfer(players);
    }

    private static List<PlayerResonseDto> transfer(List<Player> players) {
        return players.stream()
                .map(PlayerResonseDto::extractPlayers)
                .toList();
    }

    private static PlayerResonseDto extractPlayers(Player player) {
        return PlayerResonseDto.builder()
                .id(player.getId())
                .nickname(player.getNickname())
                .speed(player.getSpeed())
                .distanceCovered(player.getDistanceCovered())
                .pass(player.getPass())
                .shotsOnTarget(player.getShotOnTarget())
                .shot(player.getShot())
                .goal(player.getGoal())
                .assist(player.getAssist())
                .turnOverInDefense(player.getTurnOverInDefense())
                .turnOverInOffense(player.getTurnOverInOffense())
                .build();
    }
}

package com.mancity.social.user.application.dto.request;

import com.mancity.social.game.domain.Player;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserPlusRequestDto {

    private int speed; // 최고속도

    private int distanceCovered; // 총 이동 거리

    private int pass; // 총 패스 횟수

    private int shotOnTarget; // 총 유효 슈팅 수

    private int shot; // 총 슈팅 횟수

    private int goal; // 총 득점 수

    private int assist; // 총 어시스트 수

    private int turnOverInOffense; // 공격 시 턴오버 당한 총 횟수

    private int turnOverInDefense; // 수비 시 턴오버 성공한 총 횟수

    public static UserPlusRequestDto from(Player player) {
        return UserPlusRequestDto.builder()
                .speed(player.getSpeed())
                .distanceCovered(player.getDistanceCovered())
                .pass(player.getPass())
                .shotOnTarget(player.getShotOnTarget())
                .shot(player.getShot())
                .goal(player.getGoal())
                .assist(player.getAssist())
                .turnOverInDefense(player.getTurnOverInDefense())
                .turnOverInOffense(player.getTurnOverInOffense())
                .build();
    }
}


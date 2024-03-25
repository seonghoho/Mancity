package com.mancity.social.game.application.dto.response;

import com.mancity.social.game.domain.Game;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class PlayerDataResponseDto {

    private List<PlayerResonseDto> playersA;

    private List<PlayerResonseDto> playersB;

    public static PlayerDataResponseDto from(Game game) {
        return PlayerDataResponseDto.builder()
                .playersA(PlayerResonseDto.fromPlayersA(game))
                .playersB(PlayerResonseDto.fromPlayersB(game))
                .build();
    }
}

package com.mancity.social.user.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UserResponseDto {

    private Long id;

    private String nickName;

    private String email;

    private int height;

    private int weight;

    private int foot;

    private boolean isPlayer;

}

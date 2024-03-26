package com.mancity.user.club.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClubResponseDto {

    private Long id;

    private String name;

    private String emblem;

    private int memberCnt;

    private int score;

    private String region;

}

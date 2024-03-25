package com.mancity.social.participant.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GameManagerResponseDto {

    private long participateRequestId;

    private boolean response;
}

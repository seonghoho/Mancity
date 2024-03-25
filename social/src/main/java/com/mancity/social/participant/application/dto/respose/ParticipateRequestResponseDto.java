package com.mancity.social.participant.application.dto.respose;

import com.mancity.social.participant.domain.ParticipateRequest;
import com.mancity.social.participant.domain.ParticipateStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParticipateRequestResponseDto {

    private long id;

    private long sender;

    private long gameId;

    private ParticipateStatus status; // 수락 Y, 거절N, 대기 W

    public static ParticipateRequestResponseDto from(ParticipateRequest participateRequest) {
        return ParticipateRequestResponseDto.builder()
                .id(participateRequest.getId())
                .sender(participateRequest.getSender())
                .status(participateRequest.getStatus())
                .gameId(participateRequest.getGameId())
                .build();
    }

}

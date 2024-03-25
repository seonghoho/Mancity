package com.mancity.social.participant.application.dto.request;

import com.mancity.social.participant.domain.Participant;
import com.mancity.social.participant.domain.ParticipateRequest;
import com.mancity.social.participant.domain.ParticipateStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class GameParticipateRequestDto {

    private long gameId;

    private long userId;

    public ParticipateRequest toEntity(){
        return ParticipateRequest.builder()
                .sender(userId)
                .gameId(gameId)
                .status(ParticipateStatus.W)
                .build();
    }
}

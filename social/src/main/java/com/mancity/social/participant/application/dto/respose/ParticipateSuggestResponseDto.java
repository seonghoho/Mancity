package com.mancity.social.participant.application.dto.respose;

import com.mancity.social.participant.domain.ParticipateRequest;
import com.mancity.social.participant.domain.ParticipateStatus;
import com.mancity.social.participant.domain.ParticipateSuggest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParticipateSuggestResponseDto {

    private long id;

    private long senderId;

    private long gameId;

    private long receiverId;

    private ParticipateStatus status; // 수락 Y, 거절N, 대기 W

    public static ParticipateSuggestResponseDto from(ParticipateSuggest participateSuggest) {
        return ParticipateSuggestResponseDto.builder()
                .id(participateSuggest.getId())
                .senderId(participateSuggest.getSenderId())
                .status(participateSuggest.getStatus())
                .gameId(participateSuggest.getGameId())
                .receiverId(participateSuggest.getReceiverId())
                .build();
    }
}

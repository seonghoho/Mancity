package com.mancity.social.participant.application.dto.request;

import com.mancity.social.participant.domain.ParticipateStatus;
import com.mancity.social.participant.domain.ParticipateSuggest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GameParticipateSuggestDto {

    private long senderId;

    private long receiverId;

    private long gameId;

    public ParticipateSuggest toEntity(){
        return ParticipateSuggest.builder()
                .senderId(senderId)
                .receiverId(receiverId)
                .gameId(gameId)
                .status(ParticipateStatus.W)
                .build();
    }

}

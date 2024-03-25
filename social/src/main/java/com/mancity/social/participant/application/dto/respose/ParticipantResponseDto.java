package com.mancity.social.participant.application.dto.respose;

import com.mancity.social.participant.domain.Participant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParticipantResponseDto {

    private Long id;

    private Long userId;

    public static ParticipantResponseDto from(Participant participant){
        return ParticipantResponseDto.builder()
                .id(participant.getId())
                .userId(participant.getUserId())
                .build();
    }
}

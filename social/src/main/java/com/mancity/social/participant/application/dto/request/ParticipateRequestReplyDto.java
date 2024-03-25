package com.mancity.social.participant.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParticipateRequestReplyDto {

    private long participateRequestId;

    private boolean response;
}

package com.mancity.user.ClubMember.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JoinRequestReplyDto {

    private Long joinRequestId;

    private boolean response;




}

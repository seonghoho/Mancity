package com.mancity.user.ClubMember.application.dto.request;

import com.mancity.user.ClubMember.domain.JoinRequest;
import com.mancity.user.ClubMember.domain.JoinStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JoinRequestDto {

    Long userId;

    Long clubId;
    
    public JoinRequest toEntity(){
        return JoinRequest.builder()
                .requestUserId(userId)
                .clubId(clubId)
                .status(JoinStatus.WAIT) //디폴트 값 대기상태
                .build();
    }
}

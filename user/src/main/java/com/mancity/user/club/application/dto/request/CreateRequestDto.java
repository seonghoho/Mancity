package com.mancity.user.club.application.dto.request;

import com.mancity.user.club.domain.Club;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateRequestDto {

    //클럽 생성 사용자 id
    private Long id;

    private String name;

    private String region;

    public Club toEntity() {
        return Club.builder()
                .name(name)
                .masterId(id)
                .memberCnt(1)
                .score(0)
                .region(region)
                .build();
    }
}

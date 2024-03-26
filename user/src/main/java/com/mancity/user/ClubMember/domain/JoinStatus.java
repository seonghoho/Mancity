package com.mancity.user.ClubMember.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum JoinStatus {

    ACCEPT("수락"),
    DENY("거절"),
    WAIT("대기");

    private final String meaning;
}

package com.mancity.social.participant.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ParticipateStatus {

    W("대기"),
    Y("수락"),
    N("거절");

    private final String meaning;
}

package com.mancity.social.participant.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter

public class ParticipateRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long sender;

    private long gameId;

    @Enumerated
    private ParticipateStatus status; // 수락 Y, 거절N, 대기 W

    public void updateStatus(boolean response) {
        this.status = response ? ParticipateStatus.Y : ParticipateStatus.N;
    }

}

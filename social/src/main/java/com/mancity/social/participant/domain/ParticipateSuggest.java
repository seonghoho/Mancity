package com.mancity.social.participant.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParticipateSuggest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long senderId;

    private long receiverId;

    private long gameId;

    private ParticipateStatus status;

    public void updateStatus(boolean response) {
        this.status = response ? ParticipateStatus.Y : ParticipateStatus.N;
    }

}

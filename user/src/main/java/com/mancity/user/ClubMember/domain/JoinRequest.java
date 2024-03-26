package com.mancity.user.ClubMember.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JoinRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long requestUserId;

    private Long clubId;

    @Enumerated
    private JoinStatus status;

    public void updateStatus(boolean response) {
        if (response) {
            this.status = JoinStatus.ACCEPT;
        }
        else
            this.status = JoinStatus.DENY;
    }
}

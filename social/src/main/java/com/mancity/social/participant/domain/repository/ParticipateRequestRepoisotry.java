package com.mancity.social.participant.domain.repository;

import com.mancity.social.participant.domain.ParticipateRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParticipateRequestRepoisotry extends JpaRepository<ParticipateRequest, Long> {

    Optional<ParticipateRequest> findByGameIdAndSender(long gameId, long sender);

    List<ParticipateRequest> findAllByGameId(long gameId);

}

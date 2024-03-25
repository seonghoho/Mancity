package com.mancity.social.participant.domain.repository;

import com.mancity.social.participant.domain.ParticipateSuggest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipateSuggestRepository extends JpaRepository<ParticipateSuggest, Long> {

    List<ParticipateSuggest> findAllByGameId(long gameId);

    List<ParticipateSuggest> findAllBySenderId(long senderId);

    List<ParticipateSuggest> findAllByReceiverId(long receiverId);
}

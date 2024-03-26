package com.mancity.user.ClubMember.domain.repository;

import com.mancity.user.ClubMember.domain.JoinRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JoinRequestRepository extends JpaRepository<JoinRequest, Long> {

     Optional<JoinRequest> findByClubIdAndRequestUserId(Long clubId, Long requestUserId);

}

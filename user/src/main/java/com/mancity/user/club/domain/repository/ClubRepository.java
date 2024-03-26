package com.mancity.user.club.domain.repository;

import com.mancity.user.club.domain.Club;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club, Long> {
}

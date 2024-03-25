package com.mancity.social.game.domain.repository;

import com.mancity.social.game.domain.Game;
import com.mancity.social.game.domain.GameLevel;
import com.mancity.social.game.domain.QGame;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.mancity.social.participant.domain.QParticipant.participant;

@Repository
public class GameRepositorySupport {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    QGame qGame = QGame.game;

    public List<Game> findGamesByParticipantUserId(Long userId) {
        return jpaQueryFactory
                .selectFrom(qGame)
                .distinct()
                .innerJoin(qGame.participants, participant)
                .where(participant.userId.eq(userId))
                .fetch();
    }

    public List<Game> findAllByFilters(Integer gender, Integer region, Integer playerNumber, String level) {
        BooleanBuilder builder = new BooleanBuilder();

        if (gender != null) {
            System.out.println(gender);
            builder.and(qGame.gender.eq(gender));
        }

//        if (region != null) {
//            builder.and(qGame.region.eq(region));
//        }

        if (playerNumber != null) {
            builder.and(qGame.playerNumber.eq(playerNumber));
        }

        if (level != null) {
            builder.and(qGame.level.eq(GameLevel.valueOf(level)));
        }

        return jpaQueryFactory.selectFrom(qGame)
                .where(builder)
                .fetch();
    }
}

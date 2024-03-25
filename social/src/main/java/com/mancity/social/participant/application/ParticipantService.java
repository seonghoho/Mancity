package com.mancity.social.participant.application;

import com.mancity.social.participant.application.dto.request.GameManagerResponseDto;
import com.mancity.social.participant.application.dto.request.GameParticipateRequestDto;
import com.mancity.social.game.domain.Game;
import com.mancity.social.game.domain.repository.GameRepository;
import com.mancity.social.game.exception.NoSuchGameException;
import com.mancity.social.participant.application.dto.respose.ParticipantResponseDto;
import com.mancity.social.participant.application.dto.respose.ParticipateRequestResponseDto;
import com.mancity.social.participant.domain.Participant;
import com.mancity.social.participant.domain.ParticipateRequest;
import com.mancity.social.participant.domain.repository.ParticipateRequestRepoisotry;
import com.mancity.social.participant.exception.AlreadyParticipatedException;
import com.mancity.social.participant.exception.AlreadyRequestedGameException;
import com.mancity.social.participant.exception.GameFullException;
import com.mancity.social.participant.exception.NoSuchParticipantRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ParticipantService {

    private final GameRepository gameRepository;

    private final ParticipateRequestRepoisotry participateRequestRepoisotry;

    public void requestParticipate(GameParticipateRequestDto dto) {
        validateParticipateRequest(dto);
        Game game = gameRepository.findById(dto.getGameId())
                .orElseThrow(NoSuchGameException::new);
        participateRequestRepoisotry.save(dto.toEntity());
        // TODO : 알람만들기
    }

    public void responseParticipate(GameManagerResponseDto dto) {
        ParticipateRequest participateRequest = participateRequestRepoisotry.findById(dto.getParticipateRequestId())
                .orElseThrow(NoSuchParticipantRequestException::new);
        // 참여요청을 수락/거절 바꿔주고
        participateRequest.updateStatus(dto.isResponse());

        // 만일 수락이면
        if (dto.isResponse()) {
            // 해당 게임에 해당 유저를 participant 에 넣어주고
            Game game = gameRepository.findById(participateRequest.getGameId()).orElseThrow(NoSuchGameException::new);
            Participant participant = Participant.builder()
                    .userId(participateRequest.getSender())
                    .game(game)
                    .build();
            game.participate(participant);
        }
        // TODO : sender 에게 매치장이 수락/거절에 대한 알람 보내기 구현 필요
    }

    public List<ParticipantResponseDto> findParticipantsByGameId(long id) {
        Game game = gameRepository.findById(id).orElseThrow(NoSuchGameException::new);
        return game.getParticipants()
                .stream()
                .map(ParticipantResponseDto::from)
                .toList();
    }

    public List<ParticipateRequestResponseDto> findRequestsByGameId(long id) {
        return participateRequestRepoisotry.findAllByGameId(id)
                .stream()
                .map(ParticipateRequestResponseDto::from)
                .toList();
    }

    private void validateParticipateRequest(GameParticipateRequestDto dto) {
        Game game = gameRepository.findById(dto.getGameId())
                .orElseThrow(NoSuchGameException::new);
        if (game.getParticipants().size() >= game.getPlayerNumber()) { // 사람이 다 찼다면
            throw new GameFullException();
        }

        if (game.isExistParticipant(dto.getUserId())) { // 이미 참여했다면
            throw new AlreadyParticipatedException();
        }

        // 해당 게임에서, 어떤 유저가 참여 요청을 보낸게 있는지 확인
        // 있다면, 재요청이 불가능
        participateRequestRepoisotry.findByGameIdAndSender(dto.getGameId(), dto.getUserId())
                .ifPresent(pr -> {
                    throw new AlreadyRequestedGameException();
                });
    }

}

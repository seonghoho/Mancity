package com.mancity.social.game.application;

import com.mancity.social.game.application.dto.request.*;
import com.mancity.social.game.application.dto.response.GameResponseDto;
import com.mancity.social.game.domain.repository.GameRepositorySupport;
import com.mancity.social.user.application.dto.response.UserResponseDto;
import com.mancity.social.game.domain.Game;
import com.mancity.social.game.domain.Player;
import com.mancity.social.game.domain.repository.GameRepository;
import com.mancity.social.game.domain.repository.PlayerRepository;
import com.mancity.social.game.exception.NoSuchGameException;
import com.mancity.social.game.exception.NoSuchPlayerException;
import com.mancity.social.game.infrastructure.file.util.S3Uploader;
import com.mancity.social.user.presentation.UserFeignClient;
import com.mancity.social.user.application.dto.request.UserPlusRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class GameService {

    private final GameRepository gameRepository;

    private final PlayerRepository playerRepository;

    private final S3Uploader uploader;

    private final UserFeignClient userFeignClient;

    private final GameRepositorySupport gameRepositorySupport;

    public void create(GameCreateRequestDto dto) {
        gameRepository.save(dto.toEntity());
    }

    public GameResponseDto upload(List<MultipartFile> files, GameVideoUploadDto dto) {
        List<String> url = uploader.uploadVideo("match", files);
        Game game = findById(dto.getId());
        game.uploadVideo(url.get(0));

        // 업로드 완료 시, flask에 업로드 완료에 대해 call
        return GameResponseDto.from(game);
    }

    public GameResponseDto findMatchById(Long id) {
        Game game = findById(id);
        return GameResponseDto.from(game);
    }

    public boolean checkManager(CheckManagerDto dto) {
        Game game = findById(dto.getMatchId());
        return game.getManager() == dto.getUserId();
    }

    public void inputData(GameDataInputDto dto) {
        Game game = findById(dto.getGameId());
        game.inputData(dto);
    }

    public List<GameResponseDto> findAllByUserId(long id) {
        String nickname = findByIdFromUserService(id).getNickName();
        List<Game> games = gameRepository.findAllByNickname(nickname);
        return games.stream()
                .map(GameResponseDto::from)
                .toList();
    }

    public void allocateData(GameDataAllocateDto dto) {
        Player player = playerRepository.findById(dto.getGamePlayerId()).orElseThrow(NoSuchPlayerException::new);
        player.allocateData(findByIdFromUserService(dto.getUserId()).getNickName());
        userFeignClient.plus(UserPlusRequestDto.from(player));
    }

    public List<GameResponseDto> findGamesByParticipantUserId(Long userId) {
        return gameRepositorySupport.findGamesByParticipantUserId(userId)
                .stream()
                .map(GameResponseDto::from)
                .collect(Collectors.toList());
    }

    private Game findById(long id) {
        return gameRepository.findById(id)
                .orElseThrow(NoSuchGameException::new);
    }

    private UserResponseDto findByIdFromUserService(long id) {
        return userFeignClient.findById(id);
    }

    public List<GameResponseDto> findAllByFilters(Integer gender, Integer region, Integer playerNumber, String level) {
        return gameRepositorySupport.findAllByFilters(gender, region, playerNumber, level)
                .stream()
                .map(GameResponseDto::from)
                .toList();
    }
}

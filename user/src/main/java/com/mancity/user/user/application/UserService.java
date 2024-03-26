package com.mancity.user.user.application;

import com.mancity.user.common.s3.util.S3Uploader;
import com.mancity.user.stat.application.StatGenerator;
import com.mancity.user.user.application.dto.request.*;
import com.mancity.user.user.application.dto.response.UserResponseDto;
import com.mancity.user.user.domain.User;
import com.mancity.user.user.domain.repository.UserRepository;
import com.mancity.user.user.exception.PasswordNotMatchException;
import com.mancity.user.user.exception.UserNotExistException;
import com.mancity.user.user.infrastructure.util.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    private final StatGenerator statGenerator;

    private final S3Uploader s3Uploader;

    public void login(LoginRequestDto dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(UserNotExistException::new);

        String inputPassword = PasswordEncoder.hashingPassword(dto.getPassword(), user.getSalt());
        if (isCorrectPassword(inputPassword, user.getPassword())) {
            // 토큰 생성 후 리턴 로직 넣어주시면 됩니다. ㅎ_ㅎ
            // 스프링 시큐리티 이용하는 경우 여기 말고 필터 기반이면 다르게 구현하시면 됩니다. ㅎ_ㅎ
            // token 생성 후 response dto는 구현 안되어있습니다.
            // 토큰이랑 유저 정보를 넘겨줘야 할 수도 ?
        }
    }

    public void signUp(SingUpRequestDto dto) {
        User user = dto.toEntity();
        // 해당 유저의 stat 생성 필요
        userRepository.save(user);
        statGenerator.create(user.getId());
    }

    public void withdraw() { // 탈퇴
        // 타 서비스 구현 후 작성 필요
        // 얘가 가진거 다 삭제해줘야할 듯
    }

    public void updateInfo(MultipartFile image, UpdateRequestDto dto) {
        User user = userRepository.findById(dto.getId())
                .orElseThrow(UserNotExistException::new);
        if (image != null) {
            String url = s3Uploader.uploadEmblem("image", image);
            user.uploadImage(url);
        }
        user.update(dto);
    }

    public void updateFcmToken(FcmUpdateRequestDto dto) { // 완료
        User user = userRepository.findById(dto.getId()).orElseThrow(UserNotExistException::new);
        user.updateFcmToken(dto.getFcmToken());
    }

    public UserResponseDto findById(Long id) { // 완료
        User user = userRepository.findById(id).orElseThrow(UserNotExistException::new);
        return UserResponseDto.from(user);
    }

    public boolean isDuplicateEmail(DuplicateEmailCheckRequestDto dto) {
        return userRepository.existsByEmail(dto.getEmail());
    }

    public boolean isDuplicateNickName(DuplicateNickNameCheckRequestDto dto) {
        return userRepository.existsByNickName(dto.getNickName());
    }

    private boolean isCorrectPassword(String inputPassword, String original) {
        if (!inputPassword.equals(original)) {
            throw new PasswordNotMatchException();
        }
        return true;
    }

}

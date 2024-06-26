package com.mancity.user.user.presentation;

import com.mancity.user.user.application.UserService;
import com.mancity.user.user.application.dto.request.*;
import com.mancity.user.user.application.dto.response.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Long> login(@RequestBody LoginRequestDto dto) throws Exception {
        return new ResponseEntity<>(userService.login(dto), HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SingUpRequestDto dto) {
        userService.signUp(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/withdraw")
    public ResponseEntity<?> withdraw() {
        // 아직 미구현
        // 다른 서비스 생성 후 구현해야 할듯.
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateInfo(@RequestPart(required = false) MultipartFile image, @RequestPart UpdateRequestDto dto) {
        userService.updateInfo(image, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/fcm")
    public ResponseEntity<?> updateFcmToken(@RequestBody FcmUpdateRequestDto dto) {
        userService.updateFcmToken(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/check/email")
    public ResponseEntity<Boolean> isDuplicateEmail(@RequestBody DuplicateEmailCheckRequestDto dto) {
        return new ResponseEntity<>(userService.isDuplicateEmail(dto), HttpStatus.OK);
    }

    @PostMapping("/check/nickname")
    public ResponseEntity<Boolean> isDuplicateNickName(@RequestBody DuplicateNickNameCheckRequestDto dto) {
        return new ResponseEntity<>(userService.isDuplicateNickName(dto), HttpStatus.OK);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<ProfileResponseDto> getProfilePage(@PathVariable("id") long id) {
        return new ResponseEntity<>(userService.getProfilePage(id), HttpStatus.OK);
    }

    @GetMapping("/players")
    public ResponseEntity<List<PlayerListResponseDto>> getPlayerList() {
        return new ResponseEntity<>(userService.getPlayerList(), HttpStatus.OK);
    }

    @GetMapping("/players/{mainStat}")
    public ResponseEntity<List<PlayerListResponseDto>> getPlayerListOrderByMainStat(@PathVariable String mainStat) {
        return new ResponseEntity<>(userService.getPlayerListOrderByMainStat(mainStat), HttpStatus.OK);
    }

    @GetMapping("/main/{id}")
    public ResponseEntity<MainPageResponseDto> getMainPage(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.getMainPage(id), HttpStatus.OK);
    }

    @GetMapping("/search/{word}")
    public ResponseEntity<List<UserSearchResponseDto>> findByNickNameAndEmail(@PathVariable("word") String word) {
        return new ResponseEntity<>(userService.findByNickNameAndEmail(word), HttpStatus.OK);
    }
}

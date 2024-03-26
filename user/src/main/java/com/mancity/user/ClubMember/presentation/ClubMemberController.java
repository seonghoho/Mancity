package com.mancity.user.ClubMember.presentation;

import com.mancity.user.ClubMember.application.ClubMemberService;
import com.mancity.user.ClubMember.application.dto.request.JoinRequestReplyDto;
import com.mancity.user.ClubMember.application.dto.request.JoinRequestDto;
import com.mancity.user.ClubMember.application.dto.response.JoinRequestReplyResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/clubMember")
public class ClubMemberController {

    private final ClubMemberService clubMemberService;

    //    참여 요청
    @PostMapping("/joinReq")
    public ResponseEntity<?> joinRequest(@RequestBody JoinRequestDto dto) {
        clubMemberService.joinRequest(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //    참여 응답
    @PostMapping("joinRes")
    public ResponseEntity<JoinRequestReplyResponseDto> joinResponse(@RequestBody JoinRequestReplyDto dto) {
        return new ResponseEntity<>(clubMemberService.joinResponse(dto), HttpStatus.OK);
    }
}

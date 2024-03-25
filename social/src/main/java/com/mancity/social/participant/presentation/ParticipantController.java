package com.mancity.social.participant.presentation;


import com.mancity.social.participant.application.dto.request.ParticipateRequestReplyDto;
import com.mancity.social.participant.application.dto.request.GameParticipateRequestDto;
import com.mancity.social.participant.application.ParticipantService;
import com.mancity.social.participant.application.dto.request.GameParticipateSuggestDto;
import com.mancity.social.participant.application.dto.request.ParticipateSuggestReplyDto;
import com.mancity.social.participant.application.dto.respose.ParticipantResponseDto;
import com.mancity.social.participant.application.dto.respose.ParticipateRequestResponseDto;
import com.mancity.social.participant.application.dto.respose.ParticipateSuggestResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/social/participant")
public class ParticipantController {

    private final ParticipantService participantService;

    @PostMapping("/request")
    public ResponseEntity<?> requestParticipate(@RequestBody GameParticipateRequestDto dto){
        participantService.requestParticipate(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/response")
    public ResponseEntity<?> replyRequestParticipate(@RequestBody ParticipateRequestReplyDto dto){
        participantService.replyRequestParticipate(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 현재 참여자 조회
    @GetMapping("/{gameId}")
    public ResponseEntity<List<ParticipantResponseDto>> findParticipantsByGameId(@PathVariable("gameId") long id){
        return new ResponseEntity<>(participantService.findParticipantsByGameId(id), HttpStatus.OK);
    }

    // 해당매치의 참여요청조회
    @GetMapping("/request/{gameId}")
    public ResponseEntity<List<ParticipateRequestResponseDto>> findRequestsByGameId(@PathVariable("gameId") long id){
        return new ResponseEntity<>(participantService.findRequestsByGameId(id), HttpStatus.OK);
    }

    // 다른 유저에게 매치 참여 제안
    @PostMapping("/suggest")
    public ResponseEntity<?> suggestParticipate (@RequestBody GameParticipateSuggestDto dto){
        participantService.suggestParticipate(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/suggest/reply")
    public ResponseEntity<?> replySuggestParticipate(@RequestBody ParticipateSuggestReplyDto dto){
        participantService.replySuggestParticipate(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/suggest/{gameId}")
    public ResponseEntity<List<ParticipateSuggestResponseDto>> findSuggestsByGameId(@PathVariable("gameId") long id){
        return new ResponseEntity<>(participantService.findSuggestsByGameId(id), HttpStatus.OK);
    }

    @GetMapping("/suggest/sender/{senderId}")
    public ResponseEntity<List<ParticipateSuggestResponseDto>> findSuggestsBySenderId(@PathVariable("senderId") long senderId){
        return new ResponseEntity<>(participantService.findSuggestsBySenderId(senderId), HttpStatus.OK);
    }

    @GetMapping("/suggest/receiver/{receiverId}")
    public ResponseEntity<List<ParticipateSuggestResponseDto>> findSuggestsByReceiverId(@PathVariable("receiverId") long receiverId){
        return new ResponseEntity<>(participantService.findSuggestsByReceiverId(receiverId), HttpStatus.OK);
    }
}

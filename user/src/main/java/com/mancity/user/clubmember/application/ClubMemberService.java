package com.mancity.user.clubmember.application;

import com.mancity.user.alarm.application.AlarmService;
import com.mancity.user.alarm.application.dto.request.AlarmCreateDto;
import com.mancity.user.clubmember.application.dto.request.JoinRequestDto;
import com.mancity.user.clubmember.application.dto.request.JoinRequestReplyDto;
import com.mancity.user.clubmember.application.dto.response.JoinRequestReplyResponseDto;
import com.mancity.user.clubmember.domain.ClubMember;
import com.mancity.user.clubmember.domain.JoinRequest;
import com.mancity.user.clubmember.domain.repository.JoinRequestRepository;
import com.mancity.user.clubmember.exception.AlreadyExsitClubMemberException;
import com.mancity.user.clubmember.exception.AlreadyExsitJoinRequestException;
import com.mancity.user.clubmember.exception.NoSuchJoinRequestException;
import com.mancity.user.club.domain.Club;
import com.mancity.user.club.domain.repository.ClubRepository;
import com.mancity.user.club.exception.NoSuchClubException;
import com.mancity.user.user.domain.User;
import com.mancity.user.user.domain.repository.UserRepository;
import com.mancity.user.user.exception.UserNotExistException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ClubMemberService {

    private final ClubRepository clubRepository;

    private final UserRepository userRepository;

    private final JoinRequestRepository joinRequestRepository;

    private final AlarmService alarmService;


    public void joinRequest(JoinRequestDto dto) {
        Club club = clubRepository.findById(dto.getClubId()).orElseThrow(NoSuchClubException::new);
        User user = userRepository.findById(dto.getUserId()).orElseThrow(UserNotExistException::new);

        for (ClubMember cm : club.getClubMembers()) {
            log.info("클럽 멤버 ={}", cm.toString());
        }
        //클럽에 이미 있는 유저인지 검사
        for (ClubMember cm : club.getClubMembers()) {
            if (cm.getId() == user.getId()) {
                throw new AlreadyExsitClubMemberException();
            }
        }

        //이미 클럽에 가입 요청을 보낸 사용자인지 검사
        if (joinRequestRepository.findByClubIdAndRequestUserId(club.getId(), user.getId()).isPresent()) {
            throw new AlreadyExsitJoinRequestException();
        }

        JoinRequest joinRequest = joinRequestRepository.save(dto.toEntity());
        alarmService.createAlarm(AlarmCreateDto.of(dto.getUserId(), club.getMasterId(), "CLUB_REQUEST", joinRequest.getId()));

    }

    public JoinRequestReplyResponseDto joinResponse(JoinRequestReplyDto dto) {
        //가입 요청 찾기
        JoinRequest joinRequest = joinRequestRepository.findById(dto.getJoinRequestId()).orElseThrow(NoSuchJoinRequestException::new);

        //가입요청 응답 여부에 따라 status 변경
        joinRequest.updateStatus(dto.isResponse());

        //만약 가입요청이 수락되었다면
        if (dto.isResponse()) {
            //클럽, 유저 유효성 검사
            Club club = clubRepository.findById(joinRequest.getClubId()).orElseThrow(NoSuchClubException::new);
            User user = userRepository.findById(joinRequest.getRequestUserId()).orElseThrow(UserNotExistException::new);


            ClubMember clubMember = ClubMember.builder()
                    .club(club)
                    .user(user)
                    .build();
            log.info("clubId={}", club.getId());
            log.info("userId={}", user.getId());
            log.info("클럽 멤버가 들어갈 클럽이름={}", clubMember.getClub().getName());
            log.info("클럽 멤버 아이디={}", clubMember.getUser().getId());
            //클럽 멤버에 가입
            club.joinMember(clubMember);

            alarmService.createAlarm(AlarmCreateDto.of(club.getMasterId(), joinRequest.getRequestUserId(), "CLUB_REQUEST_REPLY", 0L));
        }

        return new JoinRequestReplyResponseDto(dto.isResponse());

    }
}

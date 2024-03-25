package com.mancity.social.participant.exception;


public class NoSuchParticipantRequestException extends RuntimeException{

    private static final String MESSAGE = "해당 참여 요청은 존재하지 않습니다.";

    public NoSuchParticipantRequestException(){
        super(MESSAGE);
    }
}


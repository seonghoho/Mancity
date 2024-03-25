package com.mancity.social.participant.exception;

public class AlreadyParticipatedException extends RuntimeException{

    private static final String MESSAGE = "이미 해당 매치에 참여한 회원입니다.";

    public AlreadyParticipatedException(){
        super(MESSAGE);
    }
}

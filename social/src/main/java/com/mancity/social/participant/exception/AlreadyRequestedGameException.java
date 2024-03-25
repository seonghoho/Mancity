package com.mancity.social.participant.exception;

public class AlreadyRequestedGameException extends RuntimeException{

    private static final String MESSAGE = "해당 매치는 이미 요청한 매치입니다.";

    public AlreadyRequestedGameException(){
        super(MESSAGE);
    }
}

package com.mancity.social.participant.exception;

public class GameFullException extends RuntimeException{

    private static final String MESSAGE = "해당 경기는 이미 모든 플레이어가 참여 완료된 상태입니다.";

    public GameFullException(){
        super(MESSAGE);
    }
}

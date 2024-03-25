package com.mancity.social.game.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GameLevel {

    H("상") {
        @Override
        public String getGameLevel() {
            return H.getLevel();
        }
    },
    M("중") {
        @Override
        public String getGameLevel() {
            return M.getLevel();
        }
    },
    L("하") {
        @Override
        public String getGameLevel() {
            return L.getLevel();
        }
    };

    private final String level;

    public abstract String getGameLevel();
}

package io.saqaStudio.com.model.strategy;

import io.saqaStudio.com.model.Mario;

public class MoveLeft implements MoveStrategy {
    @Override
    public void move(Mario mario) {
        mario.leftMove();
    }
}

package io.saqaStudio.com.model.strategy;

import io.saqaStudio.com.model.Mario;

public class MoveRight implements MoveStrategy {
    @Override
    public void move(Mario mario) {
        mario.rightMove();
    }
}

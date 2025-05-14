package io.saqaStudio.com.model;

public class MoveLeft implements MoveStrategy {
    @Override
    public void move(Mario mario) {
        mario.leftMove();
    }
}

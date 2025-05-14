package io.saqaStudio.com.model;

public class MoveRight implements MoveStrategy {
    @Override
    public void move(Mario mario) {
        mario.rightMove();
    }
}

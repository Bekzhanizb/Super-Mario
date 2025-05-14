package io.saqaStudio.com.model;


public class JumpingState implements MarioState {
    @Override
    public void handleInput(Mario mario) {
        mario.setY(mario.getY() - 10);
        mario.setStatus("jumping");
    }

}

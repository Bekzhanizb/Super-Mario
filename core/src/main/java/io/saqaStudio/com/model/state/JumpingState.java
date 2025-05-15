package io.saqaStudio.com.model.state;


import io.saqaStudio.com.model.Mario;

public class JumpingState implements MarioState {
    @Override
    public void handleInput(Mario mario) {
        mario.setY(mario.getY() - 10);
        mario.setStatus("jumping");
    }

}

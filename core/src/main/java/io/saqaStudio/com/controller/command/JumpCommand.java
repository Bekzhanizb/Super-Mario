package io.saqaStudio.com.controller.command;

import io.saqaStudio.com.model.Mario;

public class JumpCommand implements Command {
    private Mario mario;

    public JumpCommand(Mario mario) {
        this.mario = mario;
    }

    @Override
    public void execute() {
        mario.jump();
    }
}

package io.saqaStudio.com.controller.command;

import io.saqaStudio.com.model.Mario;

public class MoveLeftCommand implements Command {
    private final Mario mario;

    public MoveLeftCommand(Mario mario) {
        this.mario = mario;
    }

    @Override
    public void execute() {
        mario.leftMove();
    }
}

package io.saqaStudio.com.controller.command;

import io.saqaStudio.com.model.Mario;

public class MoveRightCommand implements Command {
    private final Mario mario;

    public MoveRightCommand(Mario mario) {
        this.mario = mario;
    }

    @Override
    public void execute() {
        mario.rightMove();
    }
}

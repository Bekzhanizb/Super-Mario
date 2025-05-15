package io.saqaStudio.com.view;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import io.saqaStudio.com.controller.GameController;
import io.saqaStudio.com.controller.command.Command;
import io.saqaStudio.com.controller.command.JumpCommand;
import io.saqaStudio.com.controller.command.MoveLeftCommand;
import io.saqaStudio.com.model.Mario;

public class GameInputProcessor implements InputProcessor {

    private final GameController controller;
    private Command moveLeftCommand;
    private Command moveRightCommand;
    private Command jumpCommand;
    private Mario mario = null;

    public GameInputProcessor(GameController controller, Mario mario) {
        this.controller = controller;
        this.mario = mario;
    }


    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.LEFT) {
            controller.stopMarioMovement();
        } else if (keycode == Input.Keys.RIGHT) {
            controller.stopMarioMovement();
        }
        return true;
    }

    @Override public boolean keyTyped(char character) { return false; }
    @Override public boolean touchDown(int screenX, int screenY, int pointer, int button) { return false; }
    @Override public boolean touchUp(int screenX, int screenY, int pointer, int button) { return false; }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override public boolean touchDragged(int screenX, int screenY, int pointer) { return false; }
    @Override public boolean mouseMoved(int screenX, int screenY) { return false; }
    @Override public boolean scrolled(float amountX, float amountY) { return false; }

    Command moveLeft = new MoveLeftCommand(mario);
    Command jump = new JumpCommand(mario);


    public void setCommands(Command left, Command right, Command jump) {
        this.moveLeftCommand = left;
        this.moveRightCommand = right;
        this.jumpCommand = jump;
    }
    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.LEFT) moveLeftCommand.execute();
        if (keycode == Input.Keys.RIGHT) moveRightCommand.execute();
        if (keycode == Input.Keys.SPACE) jumpCommand.execute();
        return true;
    }
}

package io.saqaStudio.com.view;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import io.saqaStudio.com.controller.GameController;

public class GameInputProcessor implements InputProcessor {

    private final GameController controller;

    public GameInputProcessor(GameController controller) {
        this.controller = controller;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.LEFT) {
            controller.moveMarioLeft();
        } else if (keycode == Input.Keys.RIGHT) {
            controller.moveMarioRight();
        } else if (keycode == Input.Keys.SPACE) {
            controller.makeMarioJump();
        }
        return true;
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
}

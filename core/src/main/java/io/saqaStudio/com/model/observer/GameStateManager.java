package io.saqaStudio.com.model.observer;

import io.saqaStudio.com.GameMain;
import io.saqaStudio.com.view.GameOverScreen;

public class GameStateManager implements MarioStateObserver {
    private final GameMain game;

    public GameStateManager(GameMain game) {
        this.game = game;
    }

    @Override
    public void onAliveChanged(boolean isDie) {
        if (isDie) {
            game.setScreen(new GameOverScreen(game));
        }
    }
}

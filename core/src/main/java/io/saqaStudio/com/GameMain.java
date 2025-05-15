package io.saqaStudio.com;

import com.badlogic.gdx.Game;
import io.saqaStudio.com.view.GameScreen;
import io.saqaStudio.com.view.MainMenuScreen;

public class GameMain extends Game {
    @Override
    public void create() {
        setScreen(new MainMenuScreen(this));
    }
}

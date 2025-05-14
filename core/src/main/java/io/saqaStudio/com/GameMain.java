package io.saqaStudio.com;

import com.badlogic.gdx.Game;
import io.saqaStudio.com.view.GameScreen;

public class GameMain extends Game {
    @Override
    public void create() {
        setScreen(new GameScreen());  // Запускаем основной игровой экран
    }
}

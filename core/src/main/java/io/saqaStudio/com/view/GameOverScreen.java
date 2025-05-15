package io.saqaStudio.com.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.InputAdapter;
import io.saqaStudio.com.GameMain;

public class GameOverScreen implements Screen {

    private final GameMain game;
    private SpriteBatch batch;
    private Texture backgroundTexture;
    private Texture backButtonTexture;
    private Texture exitButtonTexture;

    private int buttonWidth = 200;
    private int buttonHeight = 80;
    private int backButtonX;
    private int exitButtonX;
    private int buttonsY;

    public GameOverScreen(GameMain game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        backgroundTexture = new Texture("assets/over.gif");
        backButtonTexture = new Texture("assets/buttons/back.png");
        exitButtonTexture = new Texture("assets/buttons/exit.png");

        backButtonX = 50;  // слева внизу
        exitButtonX = Gdx.graphics.getWidth() - buttonWidth - 50;  // справа внизу
        buttonsY = 50;  // отступ от низа

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                int correctedY = Gdx.graphics.getHeight() - screenY;

                if (isInsideButton(screenX, correctedY, backButtonX, buttonsY)) {
                    game.setScreen(new MainMenuScreen(game));
                    return true;
                }
                if (isInsideButton(screenX, correctedY, exitButtonX, buttonsY)) {
                    Gdx.app.exit();
                    return true;
                }
                return false;
            }
        });
    }

    private boolean isInsideButton(int x, int y, int buttonX, int buttonY) {
        return x >= buttonX && x <= buttonX + buttonWidth &&
            y >= buttonY && y <= buttonY + buttonHeight;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(backButtonTexture, backButtonX, buttonsY, buttonWidth, buttonHeight);
        batch.draw(exitButtonTexture, exitButtonX, buttonsY, buttonWidth, buttonHeight);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        backButtonX = 50;
        exitButtonX = width - buttonWidth - 50;
        buttonsY = 50;
    }

    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
        backgroundTexture.dispose();
        backButtonTexture.dispose();
        exitButtonTexture.dispose();
    }
}

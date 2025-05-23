package io.saqaStudio.com.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.InputAdapter;
import io.saqaStudio.com.GameMain;

public class MainMenuScreen implements Screen {

    private final GameMain game;
    private SpriteBatch batch;
    private Texture backgroundTexture;
    private Texture startButtonTexture;
    private Texture exitButtonTexture;

    private int buttonWidth = 300;
    private int buttonHeight = 100;
    private int centerX;
    private int startButtonY;
    private int exitButtonY;

    public MainMenuScreen(GameMain game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        backgroundTexture = new Texture("assets/firststage.gif");
        startButtonTexture = new Texture("assets/buttons/start.png");
        exitButtonTexture = new Texture("assets/buttons/exit.png");

        centerX = (Gdx.graphics.getWidth() - buttonWidth) / 2;
        startButtonY = Gdx.graphics.getHeight() / 2 + 50;
        exitButtonY = Gdx.graphics.getHeight() / 2 - 50;

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                int correctedY = Gdx.graphics.getHeight() - screenY;

                if (isInsideButton(screenX, correctedY, centerX, startButtonY)) {
                    game.setScreen(new GameScreen(game));
                    return true;
                }
                if (isInsideButton(screenX, correctedY, centerX, exitButtonY)) {
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
        batch.draw(startButtonTexture, centerX, startButtonY, buttonWidth, buttonHeight);
        batch.draw(exitButtonTexture, centerX, exitButtonY, buttonWidth, buttonHeight);
        batch.end();
    }

    @Override public void resize(int width, int height) {
        centerX = (width - buttonWidth) / 2;
        startButtonY = height / 2 + 50;
        exitButtonY = height / 2 - 50;
    }

    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
        backgroundTexture.dispose();
        startButtonTexture.dispose();
        exitButtonTexture.dispose();
    }
}

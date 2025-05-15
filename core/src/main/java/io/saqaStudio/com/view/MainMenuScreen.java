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

    private int buttonX = 300;
    private int buttonY = 200;
    private int buttonWidth = 300;
    private int buttonHeight = 100;

    public MainMenuScreen(GameMain game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        backgroundTexture = new Texture("assets/firststage.gif");
        startButtonTexture = new Texture("assets/start.gif");

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                int correctedY = Gdx.graphics.getHeight() - screenY;

                if (screenX >= buttonX && screenX <= buttonX + buttonWidth &&
                    correctedY >= buttonY && correctedY <= buttonY + buttonHeight) {
                    game.setScreen(new GameScreen());
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(startButtonTexture, buttonX, buttonY, buttonWidth, buttonHeight);
        batch.end();
    }

    @Override
    public void resize(int width, int height) { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() {
        batch.dispose();
        backgroundTexture.dispose();
        startButtonTexture.dispose();
    }
}

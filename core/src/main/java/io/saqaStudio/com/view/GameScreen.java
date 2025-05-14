package io.saqaStudio.com.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.saqaStudio.com.controller.GameController;
import io.saqaStudio.com.model.Background;
import io.saqaStudio.com.model.Enemy;
import io.saqaStudio.com.model.Mario;
import io.saqaStudio.com.model.Turtle;
import io.saqaStudio.com.model.StaticValues;

public class GameScreen implements Screen {

    private SpriteBatch batch;
    private Mario mario;
    private Background background;
    private boolean isGameOver = false;
    private GameController gameController;

    @Override
    public void show() {
        StaticValues.init();
        batch = new SpriteBatch();
        background = new Background(1, false);  // или 0 если это первый уровень
        mario = new Mario(0, 60);
        mario.setBackground(background);
        this.gameController=new GameController(mario, background);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float scale = 2.0f;
        Texture marioTex = mario.getImage();

        handleInput();

        batch.begin();
        batch.draw(background.getBackgroundImage(), 0, 0);

        for (Enemy ob : background.getObstructions()) {
            batch.draw(ob.getImage(), ob.getX(), ob.getY());
        }
        for (Enemy e : background.getEnemies()) {
            batch.draw(e.getImage(), e.getX(), e.getY());
            System.out.println("Enemy: " + e.getX() + "," + e.getY() + " → " + e.getImage());
        }
        Turtle t = background.getTurtle();
        if (t != null) {
            batch.draw(t.image, t.x, t.y);
        }
        System.out.println("Mario: " + mario.getX() + ", " + mario.getY() + ", image = " + mario.getImage());

        batch.draw(marioTex, mario.getX(), mario.getY(), marioTex.getWidth() * scale, marioTex.getHeight() * scale);

        gameController.update();

        batch.end();
    }

    private void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) mario.leftMove();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) mario.rightMove();
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) mario.down();
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) mario.jump();
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
        // другие ресурсы можно тоже тут освобождать
    }
}


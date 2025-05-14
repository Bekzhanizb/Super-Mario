package io.saqaStudio.com.model;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.List;

public class Background {
    private boolean isOver = false;
    private Texture backgroundImage;
    private int sort;
    private boolean flag;
    private List<Enemy> enemies = new ArrayList<>();
    private List<Enemy> obstructions = new ArrayList<>();
    private List<Enemy> removedEnemies = new ArrayList<>();
    private Turtle turtle;

    public Background(int sort, boolean flag) {
        this.sort = sort;
        this.flag = flag;
        this.backgroundImage = flag ? StaticValues.end : StaticValues.bgImage;
        initLevel(sort);
    }

    private void initLevel(int level) {
        if (level == 1) {
            for (int i = 0; i < 15; i++) {
                obstructions.add(new Enemy(i * 60, 540, 9));
            }
            obstructions.add(new Enemy(120, 360, 4));
            obstructions.add(new Enemy(300, 360, 0));
            obstructions.add(new Enemy(360, 360, 4));
            obstructions.add(new Enemy(420, 360, 0));
            obstructions.add(new Enemy(480, 360, 4));
            obstructions.add(new Enemy(540, 360, 0));
            obstructions.add(new Enemy(420, 180, 0));

            enemies.add(new MoveEnemy(690, 360, true, 2, 420, 540, this));

            obstructions.add(new Enemy(660, 540, 6));
            obstructions.add(new Enemy(720, 540, 5));
            obstructions.add(new Enemy(660, 480, 8));
            obstructions.add(new Enemy(720, 480, 7));

            enemies.add(new MoveEnemy(300, 200, true, 1, this));
        }

        if (level == 2) {
            for (int i = 0; i < 15; i++) {
                if (i != 10) obstructions.add(new Enemy(i * 60, 540, 9));
            }
            obstructions.add(new Enemy(60, 540, 6));
            obstructions.add(new Enemy(120, 540, 5));
            obstructions.add(new Enemy(60, 480, 6));
            obstructions.add(new Enemy(120, 480, 5));
            obstructions.add(new Enemy(60, 420, 8));
            obstructions.add(new Enemy(120, 420, 7));

            enemies.add(new MoveEnemy(90, 540, true, 2, 350, 540, this));
            enemies.add(new MoveEnemy(270, 540, true, 2, 300, 400, this));

            turtle = new Turtle(680, 480);
        }

        if (level == 3) {
            for (int i = 0; i < 15; i++) {
                obstructions.add(new Enemy(i * 60, 540, 9));
            }
            obstructions.add(new Enemy(550, 180, 11));
            obstructions.add(new Enemy(520, 480, 2));
        }
    }

    public Texture getBackgroundImage() {
        return backgroundImage;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<Enemy> getObstructions() {
        return obstructions;
    }

    public List<Enemy> getRemovedEnemies() {
        return removedEnemies;
    }

    public Turtle getTurtle() {
        return turtle;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public boolean isOver() {
        return isOver;
    }

    public void setOver(boolean over) {
        isOver = over;
    }
}

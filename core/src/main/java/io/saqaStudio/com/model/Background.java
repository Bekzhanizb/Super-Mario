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
            // Земля (нижний ряд из блоков)
            for (int i = 0; i < 16; i++) {
                obstructions.add(new Enemy(i * 60, 0, 9));
            }

            // Платформы ниже
            obstructions.add(new Enemy(60, 180, 4));
            obstructions.add(new Enemy(180, 180, 0));
            obstructions.add(new Enemy(240, 180, 4));
            obstructions.add(new Enemy(360, 60, 0));

            // Враги ближе к игроку и чуть уменьшены по Y
            enemies.add(new MoveEnemy(600, 60, true, 1, this));
            enemies.add(new MoveEnemy(450, 60, true, 2, 60, 180, this));
            enemies.add(new MoveEnemy(300, 60, true, 1, this));
        }

        if (level == 2) {
            for (int i = 0; i < 15; i++) {
                if (i != 10) obstructions.add(new Enemy(i * 60, 0, 9));
            }
            obstructions.add(new Enemy(60, 0, 6));
            obstructions.add(new Enemy(120, 0, 5));
            obstructions.add(new Enemy(60, 60, 6));
            obstructions.add(new Enemy(120, 60, 5));
            obstructions.add(new Enemy(60, 120, 8));
            obstructions.add(new Enemy(120, 120, 7));

            enemies.add(new MoveEnemy(90, 60, true, 2, 60, 180, this));
            enemies.add(new MoveEnemy(270, 60, true, 2, 60, 180, this));

            turtle = new Turtle(680, 60);
        }

        if (level == 3) {
            for (int i = 0; i < 15; i++) {
                obstructions.add(new Enemy(i * 60, 0, 9));
            }
            obstructions.add(new Enemy(550, 180, 11));
            obstructions.add(new Enemy(520, 300, 2));
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

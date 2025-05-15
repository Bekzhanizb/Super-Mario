package io.saqaStudio.com.model;

import com.badlogic.gdx.graphics.Texture;

public class Enemy {

    private int x;
    private int y;
    private Texture image;
    private boolean isDead;

    public Enemy(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.image = StaticValues.obstruction[type];  // или свой механизм загрузки текстур
    }

    public void dead() {
        this.image = null;
        this.isDead = true;
    }
    public boolean isDead() {
        return isDead;
    }


    public int getX() { return x; }
    public int getY() { return y; }
    public Texture getImage() { return image; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
}

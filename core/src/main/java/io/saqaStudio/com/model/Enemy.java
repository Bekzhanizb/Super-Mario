package io.saqaStudio.com.model;

import com.badlogic.gdx.graphics.Texture;

public class Enemy {
    private int x;
    private int y;
    private int type;
    private int startType;
    private Texture image;

    public Enemy(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.startType = type;
        setImage();
    }

    public void reset() {
        this.type = startType;
        setImage();
    }

    public void setImage() {
        this.image = StaticValues.obstruction[type];
    }

    public Texture getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStartType() {
        return startType;
    }

    public void setStartType(int startType) {
        this.startType = startType;
    }
}

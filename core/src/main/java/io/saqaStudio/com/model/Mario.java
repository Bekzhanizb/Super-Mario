package io.saqaStudio.com.model;

import com.badlogic.gdx.graphics.Texture;

public class Mario {

    private int x;
    private int y;
    private int xmove = 0;
    private int ymove = 0;
    private String status = "standing";
    private Texture image;
    private MoveStrategy strategy;
    private Background background;

    public Mario(int x, int y) {
        this.x = x;
        this.y = y;
        this.strategy = new MoveRight();
        this.image = StaticValues.mariao[0]; // по умолчанию стоячая текстура
    }

    public void executeMove() {
        if (strategy != null)
            strategy.move(this);
    }

    public void leftMove() {
        this.strategy = new MoveLeft();
        this.x -= 5;
        this.image = StaticValues.mariao[1];
    }

    public void rightMove() {
        this.strategy = new MoveRight();
        this.x += 5;
        this.image = StaticValues.mariao[2];
    }

    public void leftstop() {
        this.strategy = null;
        this.image = StaticValues.mariao[0];
    }

    public void rightstop() {
        this.strategy = null;
        this.image = StaticValues.mariao[0];
    }

    public void down() {
        this.y -= 5;
    }

    public void jump() {
        this.y += 10;
        this.image = StaticValues.mariao[3];
    }

    public Texture getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setBackground(Background background) {
        this.background = background;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

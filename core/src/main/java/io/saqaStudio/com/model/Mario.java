package io.saqaStudio.com.model;

import com.badlogic.gdx.graphics.Texture;
import io.saqaStudio.com.model.observer.MarioStateObserver;
import io.saqaStudio.com.model.strategy.MoveLeft;
import io.saqaStudio.com.model.strategy.MoveRight;
import io.saqaStudio.com.model.strategy.MoveStrategy;

import java.util.ArrayList;
import java.util.List;

public class Mario {

    private int x;
    private int y;
    private int XMove = 0;
    private int YMove = 0;
    private final int spawnX;
    private final int spawnY;
    private String status = "standing";
    private Texture image;
    private MoveStrategy strategy;
    private Background background;

    private boolean die = false;
    private boolean isJumping = false;
    private float velocityY = 0;
    private final float GRAVITY = -0.5f;

    private final List<MarioStateObserver> observers = new ArrayList<>();


    public Mario(int spawnX, int spawnY) {
        this.spawnX = spawnX;
        this.spawnY = spawnY;
        respawn();
        this.strategy = null;
        this.image = StaticValues.mariao[0]; // default standing texture
    }

    public void respawn() {
        this.x = spawnX;
        this.y = spawnY;
        this.die = false;
        this.XMove = 0;
        this.YMove = 0;
        this.image = StaticValues.mariao[0];
        this.strategy = null;
    }

    public void executeMove() {
        this.x += XMove;

        if (x < 0) x = 0;
        if (x > 840) x = 840;

        if (strategy != null) {
            strategy.move(this);
        }

        if (isJumping) {
            velocityY += GRAVITY;
            y += (int) velocityY;

            if (y <= 60) {
                y = 60;
                isJumping = false;
                velocityY = 0;
                image = StaticValues.mariao[0];
            }
        }
    }

    public void leftMove() {
        this.strategy = new MoveLeft();
        this.XMove = -2;
        this.image = StaticValues.mariao[1];  // Sprite movement to the left
    }

    public void rightMove() {
        this.strategy = new MoveRight();
        this.XMove = 2;
        this.image = StaticValues.mariao[2]; // Sprite movement to the right
    }

    public void leftstop() {
        this.strategy = null;
        this.XMove = 0;
        this.image = StaticValues.mariao[0];  // Standing
    }

    public void rightstop() {
        this.strategy = null;
        this.XMove = 0;
        this.image = StaticValues.mariao[0];  // Standing
    }

    public void down() {
        this.y -= 5;
    }

    public void jump() {
        if (!isJumping) {
            isJumping = true;
            velocityY = 10;
            image = StaticValues.mariao[3];
        }
    }

    public void die() {
        this.die = true;
        this.XMove = 0;
        this.YMove = 0;
        this.image = StaticValues.die;  // Sprite of death
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

    public Background getBackground() {
        return background;
    }

    public String getStatus() {
        return status;
    }

    public void setXMove(int XMove) {
        this.XMove = XMove;
    }

    public void setYMove(int YMove) {
        this.YMove = YMove;
    }

    public boolean isDie() {
        return die;
    }

    public void setDie(boolean die) {
        this.die = die;
        notifyObservers();
    }

    public void addObserver(MarioStateObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(MarioStateObserver observer) {
        observers.remove(observer);
    }
    private void notifyObservers() {
        for (MarioStateObserver observer : observers) {
            observer.onAliveChanged(isDie());
        }
    }
}

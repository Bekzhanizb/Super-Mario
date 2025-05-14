package io.saqaStudio.com.controller;

import io.saqaStudio.com.model.Background;
import io.saqaStudio.com.model.Enemy;
import io.saqaStudio.com.model.Mario;

public class GameController {

    private final Mario mario;
    private final Background background;

    // Размеры спрайтов (подбери под свои текстуры)
    private static final int MARIO_WIDTH = 50;
    private static final int MARIO_HEIGHT = 50;
    private static final int BLOCK_WIDTH = 60;
    private static final int BLOCK_HEIGHT = 60;

    public GameController(Mario mario, Background background) {
        this.mario = mario;
        this.background = background;
    }

    public void update() {
        mario.executeMove();
        checkCollisions();       // Checking for collisions
        checkLevelTransition();  // Checking the transition between levels
    }

    public void moveMarioLeft() {
        mario.leftMove();
    }

    public void moveMarioRight() {
        mario.rightMove();
    }

    public void makeMarioJump() {
        mario.jump();
    }

    public void stopMarioMovement() {
        mario.leftstop();
        mario.rightstop();
    }

    private void checkCollisions() {
        // Checking for collisions with blocks (obstacles)
        for (Enemy block : background.getObstructions()) {
            if (collides(mario, block)) {
                // Collision on the right — stopping the movement to the right
                if (mario.getX() < block.getX() && mario.getX() + MARIO_WIDTH > block.getX()) {
                    if (mario.getX() > 0) {
                        mario.rightstop();
                        mario.setX(block.getX() - MARIO_WIDTH);
                    }
                }
                // Столкновение слева — остановка движения влево
                else if (mario.getX() > block.getX() && mario.getX() < block.getX() + BLOCK_WIDTH) {
                    if (mario.getX() < 0) {
                        mario.leftstop();
                        mario.setX(block.getX() + BLOCK_WIDTH);
                    }
                }
                // Можно добавить вертикальные столкновения, если есть прыжки и платформы
            }
        }

        // Проверка столкновений с врагами
        for (Enemy enemy : background.getEnemies()) {
            if (collides(mario, enemy)) {
                mario.die();
            }
        }
    }

    private boolean collides(Mario mario, Enemy enemy) {
        return mario.getX() < enemy.getX() + BLOCK_WIDTH &&
            mario.getX() + MARIO_WIDTH > enemy.getX() &&
            mario.getY() < enemy.getY() + BLOCK_HEIGHT &&
            mario.getY() + MARIO_HEIGHT > enemy.getY();
    }

    private void checkLevelTransition() {
        if (mario.getX() >= 840) {
            mario.setX(0);
            mario.setY(60);
            background.setSort(background.getSort() + 1);
        }

        if (mario.getY() < 0) {
            mario.die();
        }
    }
}

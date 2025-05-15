package io.saqaStudio.com.controller;

import io.saqaStudio.com.GameMain;
import io.saqaStudio.com.model.Background;
import io.saqaStudio.com.model.Enemy;
import io.saqaStudio.com.model.Mario;
import io.saqaStudio.com.view.GameOverScreen;

import java.util.ArrayList;
import java.util.List;

public class GameController {

    private final Mario mario;
    private final Background background;
    private final GameMain game;

    private static final int BLOCK_WIDTH = 60;
    private static final int BLOCK_HEIGHT = 60;

    private static final int MARIO_WIDTH = 31 * 2;
    private static final int MARIO_HEIGHT = 42 * 2;

    private static final int ENEMY_WIDTH = 60;
    private static final int ENEMY_HEIGHT = 60;

    public GameController(Mario mario, Background background, GameMain game) {
        this.mario = mario;
        this.background = background;
        this.game = game;
    }

    public void update() {
        mario.executeMove();
        checkEnemyCollision();

        checkLevelTransition();

        background.getEnemies().removeIf(Enemy::isDead);

        if (mario.isDie()) {
            game.setScreen(new GameOverScreen(game));
        }
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
        for (Enemy enemy : background.getEnemies()) {
            if (simpleCollision(mario, enemy)) {
                if (mario.getY() + MARIO_HEIGHT <= enemy.getY() + 10) {  // Марио сверху
                    enemy.dead();
                    mario.jump();
                } else {
                    mario.die();
                }
            }
        }
    }

    private boolean simpleCollision(Mario mario, Enemy enemy) {
        int marioRight = mario.getX() + MARIO_WIDTH;
        int enemyRight = enemy.getX() + ENEMY_WIDTH;
        int marioTop = mario.getY() + MARIO_HEIGHT;
        int enemyTop = enemy.getY() + ENEMY_HEIGHT;

        return mario.getX() < enemyRight && marioRight > enemy.getX() &&
            mario.getY() < enemyTop && marioTop > enemy.getY();
    }

    private boolean collides(Mario mario, Enemy enemy, int marioWidth, int marioHeight, int enemyWidth, int enemyHeight) {
        return mario.getX() < enemy.getX() + enemyWidth &&
            mario.getX() + marioWidth > enemy.getX() &&
            mario.getY() < enemy.getY() + enemyHeight &&
            mario.getY() + marioHeight > enemy.getY();
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
    private void checkEnemyCollision() {
        List<Enemy> enemiesToRemove = new ArrayList<>();

        for (Enemy enemy : background.getEnemies()) {
            if (collides(mario, enemy, MARIO_WIDTH, MARIO_HEIGHT, ENEMY_WIDTH, ENEMY_HEIGHT)) {
                boolean marioAboveEnemy = mario.getY() + MARIO_HEIGHT <= enemy.getY() + (ENEMY_HEIGHT * 0.75f) &&
                    mario.getX() + MARIO_WIDTH > enemy.getX() &&
                    mario.getX() < enemy.getX() + ENEMY_WIDTH;

                if (marioAboveEnemy) {
                    enemy.dead();
                    enemiesToRemove.add(enemy);
                    mario.jump();
                } else {
                    mario.die();
                }
            }
        }

        background.getEnemies().removeAll(enemiesToRemove);
    }


}

package io.saqaStudio.com.model;

import com.badlogic.gdx.graphics.Texture;

public class MoveEnemy extends Enemy implements Runnable {

    private int x;
    private int y;
    private boolean isLeftOrUp;
    private int type;
    private Texture image;
    private int imageType = 0;
    private final Thread thread;
    private final Background background;
    private boolean alive = true;

    public MoveEnemy(int x, int y, boolean isLeft, int type, Background background) {
        super(x, y, type);
        this.x = x;
        this.y = y;
        this.isLeftOrUp = isLeft;
        this.type = type;
        this.background = background;
        this.image = StaticValues.trangel[0];
        thread = new Thread(this);
        thread.start();
    }

    public MoveEnemy(int x, int y, boolean isUp, int type, int upMax, int downMax, Background background) {
        super(x, y, type);
        this.x = x;
        this.y = y;
        this.isLeftOrUp = isUp;
        this.type = type;
        this.background = background;
        this.image = StaticValues.flower[0];
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (alive) {
            if (type == 1) {
                // Логика движения
                if (isLeftOrUp) {
                    x -= 7;
                } else {
                    x += 7;
                }

                imageType = (imageType == 0) ? 1 : 0;
                this.image = StaticValues.trangel[imageType];

                if (x < 0) isLeftOrUp = false;
                else if (x > 840) isLeftOrUp = true;
            }

            if (type == 2) {
                if (isLeftOrUp) {
                    y -= 7;
                } else {
                    y += 7;
                }

                imageType = (imageType == 0) ? 1 : 0;
                this.image = StaticValues.flower[imageType];

                int downMax = 0;
                int upMax = 0;
                isLeftOrUp = y > upMax;
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    @Override
    public void dead() {
        alive = false;
        this.image = null;
        background.getEnemies().remove(this);
        background.getRemovedEnemies().add(this);
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public Texture getImage() { return image; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

    public boolean isLeftOrUp() {
        return isLeftOrUp;
    }

    public void setLeftOrUp(boolean leftOrUp) {
        isLeftOrUp = leftOrUp;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getImageType() {
        return imageType;
    }

    public void setImageType(int imageType) {
        this.imageType = imageType;
    }
}

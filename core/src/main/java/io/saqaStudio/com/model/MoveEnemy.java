package io.saqaStudio.com.model;

import com.badlogic.gdx.graphics.Texture;

public class MoveEnemy extends Enemy implements Runnable {

    private int type;
    private Texture image;
    private int imagetype = 0;
    private boolean isLeftOrUp = true;
    private int upMax = 0;
    private int downMax = 0;
    private Thread thread;
    private Background background;

    public MoveEnemy(int x, int y, boolean isLeft, int type, Background background) {
        super(x, y, type);
        this.isLeftOrUp = isLeft;
        this.type = type;
        this.background = background;
        if (type == 1) {
            this.image = StaticValues.trangel[0];
        }
        startThread();
    }

    public MoveEnemy(int x, int y, boolean isUp, int type, int upMax, int downMax, Background background) {
        super(x, y, type);
        this.isLeftOrUp = isUp;
        this.type = type;
        this.upMax = upMax;
        this.downMax = downMax;
        this.background = background;
        if (type == 2) {
            this.image = StaticValues.flower[0];
        }
        startThread();
    }

    private void startThread() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            if (type == 1) {
                setX(getX() + (isLeftOrUp ? -3 : 3));
                imagetype = (imagetype == 0) ? 1 : 0;
                this.image = StaticValues.trangel[imagetype];

                for (Enemy ob : background.getObstructions()) {
                    if (ob.getX() == getX() + 60 && Math.abs(ob.getY() - getY()) < 50) {
                        isLeftOrUp = true;
                    }
                    if (ob.getX() == getX() - 60 && Math.abs(ob.getY() - getY()) < 50) {
                        isLeftOrUp = false;
                    }
                }
                if (getX() < 0) isLeftOrUp = !isLeftOrUp;
            }

            if (type == 2) {
                setY(getY() + (isLeftOrUp ? -3 : 3));
                imagetype = (imagetype == 0) ? 1 : 0;
                this.image = StaticValues.flower[imagetype];

                if (isLeftOrUp && getY() <= upMax) isLeftOrUp = false;
                if (!isLeftOrUp && getY() >= downMax) isLeftOrUp = true;
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Texture getImage() {
        return image;
    }

    public void setImage(Texture image) {
        this.image = image;
    }
}

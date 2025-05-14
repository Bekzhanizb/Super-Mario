package io.saqaStudio.com.model;

import com.badlogic.gdx.graphics.Texture;

public class Turtle implements Runnable {
    public int x;
    public int y;
    public Texture image;
    public int speed = 5;
    public int type = 2;  // 1 - влево, 2 - вправо, 3 - пауза
    private Thread thread;
    private boolean toggle = false;

    public Turtle(int x, int y) {
        this.x = x;
        this.y = y;
        this.image = StaticValues.turtle[0];
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            switch (type) {
                case 1: // влево
                    image = toggle ? StaticValues.turtle[0] : StaticValues.turtle[1];
                    x -= speed;
                    break;
                case 2: // вправо
                    image = toggle ? StaticValues.turtle[2] : StaticValues.turtle[3];
                    x += speed;
                    break;
                case 3: // пауза (грустная)
                    image = StaticValues.turtle[4];
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    type = 1;
                    continue;
            }

            toggle = !toggle;

            if (x <= 660) type = 2;
            if (x >= 840) type = 1;
        }
    }
}

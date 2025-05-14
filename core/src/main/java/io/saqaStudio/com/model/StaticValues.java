package io.saqaStudio.com.model;

import com.badlogic.gdx.graphics.Texture;

public class StaticValues {

    public static Texture[] mariao = new Texture[10];
    public static Texture start;
    public static Texture end;
    public static Texture bgImage;
    public static Texture[] flower = new Texture[5];
    public static Texture[] trangel = new Texture[3];
    public static Texture[] turtle = new Texture[5];
    public static Texture[] obstruction = new Texture[12];
    public static Texture die;

    public static void init() {
        // Загрузка текстур (в идеале использовать AssetManager)
        for (int i = 0; i < 9; i++) {
            mariao[i] = new Texture("assets/" + (i + 1) + ".gif");
        }


        start = new Texture("assets/start.gif");
        end = new Texture("assets/firststageend.gif");
        bgImage = new Texture("assets/firststage.gif");
        die = new Texture("assets/over.gif");

        for (int i = 0; i < 2; i++) {
            flower[i] = new Texture("assets/flower" + (i + 1) + ".gif");
        }
        for (int i = 0; i < 3; i++) {
            trangel[i] = new Texture("assets/triangle" + (i + 1) + ".gif");
        }
        for (int i = 0; i < 5; i++) {
            turtle[i] = new Texture("assets/Turtle" + (i + 1) + ".gif");
        }
        for (int i = 0; i < 12; i++) {
            obstruction[i] = new Texture("assets/ob" + (i + 1) + ".gif");
        }
    }
}

package main;

import main.Game;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 * Created by X on 9/3/2017.
 */
public class Main {

    public static void main(String[] args) throws SlickException {
        AppGameContainer appgc = new AppGameContainer(new Game("PacMan"));
        appgc.setTargetFrameRate(120);
        appgc.setDisplayMode(800, 800, false);
        appgc.start();
    }
}

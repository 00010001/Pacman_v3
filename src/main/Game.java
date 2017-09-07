package main;

import elements.Element;
import entities.PacMan;
import entities.MoveDirection;
import entities.ghosts.Ghost;
import entities.ghosts.MoveMode;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Line;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game extends BasicGame {

    public static final int GRIDSIZE = 32;
    private PacMan pacMan;

    public static List<Element> wallList = new ArrayList<>();
    public static List<Element> pacDotList = new ArrayList<>();
    static List<Element> emptyFields = new ArrayList<>();
    static List<Ghost> ghosts = new ArrayList<>();
    public static List<Element> powerUpList = new ArrayList<>();
    public static int score = 0;

    Game(String title) {
        super(title);
    }


    @Override
    public void init(GameContainer gameContainer)  {

        this.pacMan = PacMan.getInstance();
        loadLevel("level1.txt");

    }

    private void loadLevel(String filename){
        try {
            new Level(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reset(){
        wallList = new ArrayList<>();
        pacDotList = new ArrayList<>();
        emptyFields = new ArrayList<>();
        ghosts = new ArrayList<>();
        powerUpList = new ArrayList<>();
        score = 0;
        loadLevel("level1.txt");
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {

        updatePlayerInput(gc);
        pacMan.update();
        updateGhosts();
       // System.out.println(powerUpList.size());
    }

    private void updatePlayerInput(GameContainer gc){
        Input input = gc.getInput();

        if (input.isKeyPressed(Input.KEY_UP) || input.isKeyDown(Input.KEY_UP)) {
            pacMan.setDirection(MoveDirection.UP);
        }
        if (input.isKeyPressed(Input.KEY_DOWN) || input.isKeyDown(Input.KEY_DOWN)) {
            pacMan.setDirection(MoveDirection.DOWN);
        }
        if (input.isKeyPressed(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_LEFT)) {
            pacMan.setDirection(MoveDirection.LEFT);
        }
        if (input.isKeyPressed(Input.KEY_RIGHT) || input.isKeyDown(Input.KEY_RIGHT)) {
            pacMan.setDirection(MoveDirection.RIGHT);
        }
        if (input.isKeyPressed(Input.KEY_R)){
            reset();
        }
    }

    private void updateGhosts(){
        for (Ghost ghost : ghosts) {
            ghost.update();
        }
    }

    public static void frightedGhost(MoveMode moveMode){
        for (Ghost ghost : ghosts) {
            ghost.setMove_mode(moveMode);
            ghost.setFrightenedTime(800);
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {

        renderElements(g, Color.blue, wallList, false);
        renderElements(g, Color.white, pacDotList, true);
        renderElements(g, Color.pink, powerUpList, true);

        renderGhosts(g);
        renderPacMan(g);
        g.drawString("Points: " + score,650,10);
    }

    private void renderPacMan(Graphics g){
        g.setColor(Color.yellow);
        g.fill(pacMan.getShape());
    }

    private void renderGhosts(Graphics g){
        for (Ghost ghost : ghosts) {
            g.setColor(ghost.getColor());
            g.fill(ghost.getShape());
        }
    }

    private void renderElements(Graphics g, Color color, List<Element> elements, boolean fill) {

        g.setColor(color);

        for (Element element : elements) {
            if (fill) {
                g.fill(element.getShape());
            } else {
                g.draw(element.getShape());
            }
        }
    }

    private void renderGrid(GameContainer gc, Graphics g) {
        g.setColor(Color.darkGray);
        for (int i = 0; i < gc.getHeight(); i += GRIDSIZE) {
            g.draw(new Line(0, i, gc.getWidth(), i));
        }
        for (int i = 0; i < gc.getWidth(); i += GRIDSIZE) {
            g.draw(new Line(i, 0, i, gc.getHeight()));
        }
    }
}

package entities;

import elements.Element;
import elements.PacDot;
import entities.ghosts.MoveMode;
import main.Game;
import main.Vector;
import org.newdawn.slick.geom.Rectangle;

import java.util.List;

public class PacMan {

    private int gridSize = Game.GRIDSIZE;
    private int steps = 0;
    private Rectangle shape = new Rectangle(0, 0, gridSize, gridSize);
    private MoveDirection direction = MoveDirection.LEFT;
    private static PacMan instance;

    private PacMan() {
    }

    public static PacMan getInstance() {
        if (instance == null) {
            instance = new PacMan();
        }
        return instance;
    }

    public Rectangle getShape() {
        return shape;
    }

    public void setShapePosition(float x, float y) {
        shape.setX(x * gridSize);
        shape.setY(y * gridSize);
    }

    public Vector getVectorPosition(){
        return new Vector((int) PacMan.getInstance().getShape().getX() / gridSize,(int) PacMan.getInstance().getShape().getY() / gridSize);
    }

    public MoveDirection getDirection() {
        return direction;
    }

    public void setDirection(MoveDirection direction) {
        if (this.steps == 0 && collide(this, direction, Game.wallList) == null) {
            this.direction = direction;
            this.steps = gridSize;
        }
    }

    public void update() {
        checkForCollisionsWithPacDots();
        checkForCollisionsWithPowerUps();
        move();
    }

    private void move() {
        if (steps > 0) {
            switch (direction) {
                case UP:
                    shape.setY(shape.getY() - 1);
                    break;
                case DOWN:
                    shape.setY(shape.getY() + 1);
                    break;
                case LEFT:
                    shape.setX(shape.getX() - 1);
                    break;
                case RIGHT:
                    shape.setX(shape.getX() + 1);
                    break;
            }
            steps--;
        }
    }

    private void checkForCollisionsWithPowerUps() {
        Element element = collide(this, MoveDirection.NONE, Game.powerUpList);
        if (element != null) {
            if(element.getClass().getName().equals("elements.PowerUp")){
                System.out.println("fff");
                Game.frightedGhost(MoveMode.FRIGHTENED);
            }
            Game.powerUpList.remove(element);
        }
    }

    private void checkForCollisionsWithPacDots() {
        PacDot pacDot = (PacDot) collide(this, MoveDirection.NONE, Game.pacDotList);
        if (pacDot != null) {
            Game.pacDotList.remove(pacDot);
            Game.score++;
        }
    }

    private Element collide(PacMan pacMan, MoveDirection direction, List<Element> elements) {
        for (Element element : elements) {
            if ((element.getX() * gridSize == pacMan.shape.getX() + direction.getX() * gridSize) && (element.getY() * gridSize == pacMan.shape.getY() + direction.getY() * gridSize)) {
                return element;
            }
        }
        return null;
    }
}

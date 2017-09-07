package entities.ghosts;

import algorithms.AStar;
import entities.MoveDirection;
import main.Game;
import main.Vector;
import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Rectangle;


public class Ghost {

    private Vector currentPosition = new Vector();
    private Vector homePos = new Vector();
    private Vector targetPosition = new Vector();

    private int gridSize = Game.GRIDSIZE;
    private Rectangle shape = new Rectangle(0,0,gridSize,gridSize);
    private Color color;
    private int movingSteps = 0;
    private MoveDirection direction = MoveDirection.LEFT;
    private MoveMode move_mode = MoveMode.CHASE;
    private float speed = 32;
    private int frightenedTime = 0;


    Ghost(float x, float y, Color color) {
        this.color = color;
        shape.setX(x * gridSize);
        shape.setY(y * gridSize);
        homePos.set((int)x,(int)y);
    }

    public Color getColor() {
        return color;
    }

    public Rectangle getShape() {
        return shape;
    }

    public MoveMode getMove_mode() {
        return move_mode;
    }

    public void setFrightenedTime(int frightenedTime) {
        this.frightenedTime = frightenedTime;
    }

    public void setMove_mode(MoveMode move_mode) {
        this.move_mode = move_mode;
    }

    public void frightened() {
        if(frightenedTime > 0){
            targetPosition = homePos;
            frightenedTime--;
        } else {
            frightenedTime = 0;
            move_mode = MoveMode.CHASE;
        }
    }

    public void setTargetPosition(Vector targetPosition) {
        this.targetPosition = targetPosition;
    }

    public void update() {
        if (this.movingSteps == 0) {
            updatePosition();
            direction = AStar.pathFind(currentPosition.getX(), currentPosition.getY(),targetPosition.getX(),targetPosition.getY());
            this.movingSteps = (int)speed;
        }
        move();
    }

    private void updatePosition() {
        currentPosition.set((int)shape.getX()/gridSize,(int)shape.getY()/gridSize);
    }

    private void move() {
        if (movingSteps > 0) {
            switch (direction) {
                case UP:
                    shape.setY(shape.getY() - gridSize/speed);
                    break;
                case DOWN:
                    shape.setY(shape.getY() + gridSize/speed);
                    break;
                case LEFT:
                    shape.setX(shape.getX() - gridSize/speed);
                    break;
                case RIGHT:
                    shape.setX(shape.getX() + gridSize/speed);
                    break;
            }
            movingSteps--;
        }
    }
}

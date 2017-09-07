package entities.ghosts;

import elements.Element;
import entities.MoveDirection;
import entities.PacMan;
import main.Game;
import main.Vector;
import org.newdawn.slick.Color;

public class Speedy extends Ghost {

    private int targetX;
    private int targetY;

    public Speedy(float x, float y) {
        super(x, y, Color.pink);
    }

    @Override
    public void update() {
        switch (super.getMove_mode()) {
            case CHASE:
                super.setTargetPosition(determineTarget());
                break;
            case SCATTER:
                break;
            case FRIGHTENED:
                super.frightened();
                break;
        }
      //  System.out.println(super.getMove_mode());
        super.update();
    }

    private Vector determineTarget() {

        targetX = PacMan.getInstance().getVectorPosition().getX();
        targetY = PacMan.getInstance().getVectorPosition().getY();

        switch (PacMan.getInstance().getDirection()) {
            case RIGHT:
                findWall(MoveDirection.RIGHT);
                break;
            case LEFT:
                findWall(MoveDirection.LEFT);
                break;
            case DOWN:
                findWall(MoveDirection.DOWN);
                break;
            case UP:
                findWall(MoveDirection.UP);
                break;
        }
        Vector target = new Vector(targetX,targetY);
        //System.out.println(target);
        return target;
    }

    private void findWall(MoveDirection moveDirection) {
        switch (moveDirection) {
            case RIGHT:
                if (!collideWithWall(targetX + 1, targetY)) {
                    targetX = targetX + 1;
                    findWall(moveDirection);
                }
                break;
            case LEFT:
                if (!collideWithWall(targetX - 1, targetY)) {
                    targetX = targetX - 1;
                    findWall(moveDirection);
                }
                break;
            case DOWN:
                if (!collideWithWall(targetX, targetY + 1)) {
                    targetY = targetY + 1;
                    findWall(moveDirection);
                }
                break;
            case UP:
                if (!collideWithWall(targetX, targetY - 1)) {
                    targetY = targetY - 1;
                    findWall(moveDirection);
                }
                break;
        }


    }

    private boolean collideWithWall(int x, int y) {
        for (Element element : Game.wallList) {
            if (element.getX() == x && element.getY() == y)
                return true;
        }
        return false;
    }

}

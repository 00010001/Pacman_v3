package entities.ghosts;

import entities.PacMan;
import org.newdawn.slick.Color;

import static main.Game.GRIDSIZE;

public class Shadow extends Ghost{

    public Shadow(float x, float y) {
        super(x, y, Color.red);
    }

    @Override
    public void update() {
        switch (super.getMove_mode()){
            case CHASE:
                super.setTargetPosition(PacMan.getInstance().getVectorPosition());
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
}

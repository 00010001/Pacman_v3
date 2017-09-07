package elements;

import org.newdawn.slick.geom.Rectangle;

public class Wall extends Element {

    public Wall(int x, int y) {
        super(new Rectangle(x*32+6,y*32+6,20,20), x, y);
    }
}

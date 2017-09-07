package elements;

import org.newdawn.slick.geom.Rectangle;

/**
 * Created by X on 9/3/2017.
 */
public class Element {

    private Rectangle shape;
    private int x;
    private int y;

    public Element(Rectangle shape, int x, int y) {
        this.shape = shape;
        this.x = x;
        this.y = y;
    }

    public Rectangle getShape() {
        return shape;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

package elements;

import org.newdawn.slick.geom.Rectangle;

public class PacDot extends Element {

    public PacDot(int x, int y) {
            super(new Rectangle(x * 32 + 14, y * 32 + 14, 4, 4), x, y);
    }
}

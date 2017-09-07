package elements;

import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

public class PowerUp extends Element {

    public PowerUp(int x, int y) {
        super(new Rectangle(x * 32 + 10, y * 32 + 10, 12, 12), x, y);
    }
}

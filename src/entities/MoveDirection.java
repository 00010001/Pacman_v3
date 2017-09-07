package entities;

/**
 * Created by X on 9/3/2017.
 */
public enum MoveDirection {
    UP(0,-1),
    DOWN(0,1),
    LEFT(-1,0),
    RIGHT(1,0),
    NONE(0,0);

    private float x;
    private float y;

    MoveDirection(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}

package algorithms;

import elements.Element;
import entities.PacMan;

public class Node extends Element {

    private float cost;
    private Node parent;

    public Node(int x, int y) {
        super(null, x, y);
    }

    public Node(int x, int y,Node parent) {
        super(null, x, y);
        this.parent = parent;
        cost = (float)Math.sqrt(Math.pow((super.getX()- PacMan.getInstance().getShape().getX()/32), 2) + Math.pow((super.getY()- PacMan.getInstance().getShape().getY()/32), 2));
    }

    public float getCost() {
        return cost;
    }

    public Node getParent() {
        return parent;
    }
}

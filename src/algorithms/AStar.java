package algorithms;

import elements.Element;
import entities.MoveDirection;
import main.Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by X on 9/3/2017.
 */
public class AStar {


    private static List<Node> openList;
    private static List<Node> closedList;
    private static List<Node> path;

    private static int originX;
    private static int originY;

    private static int targetX;
    private static int targetY;

    public static MoveDirection pathFind(int originX, int originY, int targetX, int targetY) {

        AStar.originX = originX;
        AStar.originY = originY;

        AStar.targetX = targetX;
        AStar.targetY = targetY;

        openList = new ArrayList<>();
        closedList = new ArrayList<>();

        Node startingNode = new Node(originX, originY);
        openList.add(startingNode);
        Node node;

        if (!targetReached()) {
            while (!targetReached()) {
                node = findBestNode(openList);
                lookAround(node);
                targetReached();
            }
            return determineDirection();
        }
        return MoveDirection.NONE;

    }

    private static MoveDirection determineDirection() {

        int tileToGoX = path.get(0).getX();
        int tileToGoY = path.get(0).getY();

        int offsetX = tileToGoX - originX;
        int offsetY = tileToGoY - originY;

        if (offsetX == 1 && offsetY == 0) {
            return MoveDirection.RIGHT;
        }
        if (offsetX == -1 && offsetY == 0) {
            return MoveDirection.LEFT;
        }
        if (offsetX == 0 && offsetY == -1) {
            return MoveDirection.UP;
        }
        if (offsetX == 0 && offsetY == 1) {
            return MoveDirection.DOWN;
        }
        return null;
    }

    private static boolean targetReached() {
        for (Node node : openList) {
            if (node.getX() == targetX) {
                if (node.getY() == targetY) {
                    buildPath(node);
                    return true;
                }
            }
        }
        return false;
    }

    private static void buildPath(Node node) {
        path = new ArrayList<>();
        while (node.getParent() != null) {
            path.add(node);
            node = node.getParent();
        }
        reversePathArray();
    }

    private static void reversePathArray() {
        List<Node> shallowCopy = path.subList(0, path.size());
        Collections.reverse(shallowCopy);
        path = shallowCopy;
    }


    private static Node findBestNode(List<Node> openList) {
        Node found = openList.get(0);
        for (Node node : openList) {
            if (node.getCost() < found.getCost())
                found = node;
        }
        return found;
    }

    private static void lookAround(Node node) {

        int x = node.getX();
        int y = node.getY();

        Node up = new Node(x, y - 1, node);
        Node down = new Node(x, y + 1, node);
        Node left = new Node(x - 1, y, node);
        Node right = new Node(x + 1, y, node);

        for (Element element : Game.wallList) {
            if (up != null) {
                if (element.getX() == up.getX() && element.getY() == up.getY()) {
                    up = null;
                }
            }
            if (down != null) {
                if (element.getX() == down.getX() && element.getY() == down.getY()) {
                    down = null;
                }
            }
            if (left != null) {
                if (element.getX() == left.getX() && element.getY() == left.getY()) {
                    left = null;
                }
            }
            if (right != null) {
                if (element.getX() == right.getX() && element.getY() == right.getY()) {
                    right = null;
                }
            }
        }
        if ((up != null) && (!listContain(up))) {
            openList.add(up);
        }
        if (down != null && (!listContain(down))) {
            openList.add(down);
        }
        if (left != null && (!listContain(left))) {
            openList.add(left);
        }
        if (right != null && (!listContain(right))) {
            openList.add(right);
        }

        openList.remove(node);
        closedList.add(node);


    }

    private static boolean listContain(Node node) {
        return listContain(node, openList) || listContain(node, closedList);
    }

    private static boolean listContain(Node nodeToCheck, List<Node> list) {
        for (Node nodeFromList : list) {
            if (nodeToCheck.getX() == nodeFromList.getX() && nodeToCheck.getY() == nodeFromList.getY()) {
                return true;
            }
        }
        return false;
    }


}

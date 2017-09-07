package main;

import elements.Element;
import algorithms.Node;
import elements.PacDot;
import elements.PowerUp;
import elements.Wall;
import entities.PacMan;
import entities.ghosts.Ghost;
import entities.ghosts.Shadow;
import entities.ghosts.Speedy;

import java.io.*;
import java.util.List;

/**
 * Created by X on 8/27/2017.
 */
public class Level {

    private List<Element> wallList = Game.wallList;
    private List<Element> pacDotList = Game.pacDotList;
    private List<Ghost> ghosts = Game.ghosts;
    private List<Element> emptyFields = Game.emptyFields;
    private List<Element> powerUpList = Game.powerUpList;



    public Level(String filename) throws IOException {
        generateLevelFromFile(filename);
    }

    public void generateLevelFromFile(String filename) throws IOException {
//        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//        File file = new File((classLoader.getResource(filename)).getFile());
        File file = new File(filename);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        int row = 0;
        while ((line = bufferedReader.readLine()) != null) {
            String[] items = line.split(" ");
            for(int i = 0; i<items.length;i++){

                switch (items[i]) {
                    case "W":
                        wallList.add(new Wall(i,row));
                        break;
                    case ".":
                        pacDotList.add(new PacDot(i,row));
                        break;
                    case ",":
                        emptyFields.add(new Node(i,row));
                        break;
                    case "U":
                        powerUpList.add(new PowerUp(i,row));
                        break;
                    case "P":
                        PacMan.getInstance().setShapePosition(i,row);
                        break;
                    case "1":
                        ghosts.add(new Shadow(i,row));
                        break;
                    case "2":
                        ghosts.add(new Speedy(i,row));
                        break;
                    case "3":
                        break;
                    case "4":
                        break;
                }
            }
            row++;
        }

    }

}

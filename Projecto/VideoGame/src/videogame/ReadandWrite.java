/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

/**
 *
 * @author adolfo
 */
public class ReadandWrite {

    private Game game;  //game class object

    public ReadandWrite(Game game) {
        this.game = game;
    }

    /**
     * This mehtod save all the positions of the enemies/allies, the hero and
     * save the score and lives when the key s is pressed
     *
     * @param strFileName name of the file
     * @param game calling the game itself to acces the elements
     * @param enemies to have acces to all the enemies in the linkedlist
     * @param allies to have acces to all the allies in the linkedlist
     */
    public static void Save(String strFileName, Game game, LinkedList<Enemy> enemies, LinkedList<Ally> allies) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(strFileName));
            int vidas = game.getLives();
            int score = game.getScore();
            writer.println("" + vidas + "/" + score);
            //player
            int x = game.getPlayer().getX();
            int y = game.getPlayer().getY();
            writer.println("" + x + "/" + y);
            //enemies
            for (Enemy enemy : enemies) {
                x = enemy.getX();
                y = enemy.getY();
                writer.println("" + x + "/" + y);
            }
            //allies
            for (Ally ally : allies) {
                x = ally.getX();
                y = ally.getY();
                writer.println("" + x + "/" + y);
            }
            writer.close();
        } catch (IOException ioe) {
            System.out.println("Cannot create File");
        }

    }

    /**
     * To load the save last of the game, all the position are restored, the
     * score and lives are also restored
     *
     * @param strFileName name of the file
     * @param game the game to acces the elements
     * @param enemies to have acces to the enemies
     * @param allies to have acces to the allies
     */
    public static void Load(String strFileName, Game game, LinkedList<Enemy> enemies, LinkedList<Ally> allies) {
        try {
            FileReader file = new FileReader(strFileName);
            BufferedReader reader = new BufferedReader(file);
            String line;
            String datos[];
            line = reader.readLine();
            datos = line.split("/");
            int vidas = Integer.parseInt(datos[0]);
            int score = Integer.parseInt(datos[1]);
            game.setLives(vidas);
            game.setScore(score);
            line = reader.readLine();
            datos = line.split("/");
            int x = Integer.parseInt(datos[0]);
            int y = Integer.parseInt(datos[1]);
            game.getPlayer().setX(x);
            game.getPlayer().setY(y);
            //System.out.println("Se leyo  hero x = "+ x + " y hero y = " + y);
            //enemies
            for (Enemy enemy : enemies) {
                line = reader.readLine();
                datos = line.split("/");
                x = Integer.parseInt(datos[0]);
                y = Integer.parseInt(datos[1]);
                enemy.setX(x);
                enemy.setY(y);
            }
            //allies
            for (Ally ally : allies) {
                x = ally.getX();
                y = ally.getY();
                ally.setX(x);
                ally.setY(y);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("File Not found CALL 911");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args, Game game, LinkedList<Enemy> enemies, LinkedList<Ally> allies) {
        // TODO code application logic here
        Save("Archivo.txt", game, enemies, allies);
        Load("Archivo.txt", game, enemies, allies);
    }

}

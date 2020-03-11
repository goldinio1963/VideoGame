/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.image.BufferedImage;

/**
 *
 * @author adolfo
 */
public class Assets {

    public static BufferedImage background; // to store background image
    public static BufferedImage player;     // to store the player image
    public static BufferedImage enemy;      // to store the enemy image
    public static BufferedImage ally;       // to store the ally image
    public static BufferedImage gameOver;   // to store the gameover image
    public static SoundClip enemysound;     // to store the enemy sound
    public static SoundClip allysound;      // to store the ally sound

    /**
     * initializing the images of the game
     */
    public static void init() {
        background = ImageLoader.loadImage("/images/Background.jpg");
        player = ImageLoader.loadImage("/images/mario.jpg");
        enemy = ImageLoader.loadImage("/images/enemy.png");
        ally = ImageLoader.loadImage("/images/ally.png");
        gameOver = ImageLoader.loadImage("/images/gameOver.jpg");
        enemysound = new SoundClip("/sounds/crash.wav");
        allysound = new SoundClip("/sounds/coin.wav");
    }

}

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
    public static BufferedImage sprites;    //to store the spritesheet
    //player
    public static BufferedImage playerUp[]; // to store the animation of the movement right
    public static BufferedImage playerDown[];   // to store the animation of the movement down
    public static BufferedImage playerLeft[];   // to store the animation of the movement left
    public static BufferedImage playerRight[];  // to store the animation of the movement right
    public static BufferedImage playerIdle[];   //to sotre the animation of idle player
    //enemy
    public static BufferedImage Enemysprite;  //to store the enemy sprite
    public static BufferedImage EnemyLeft[];    //to store the enemy animation left
    //ally
    public static BufferedImage AllySprite;   //to store the ally sprite
    public static BufferedImage AllyRight[];    //to store the ally animation
    //sounds
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
        
        //hero
        //load the sprites and corp
        sprites = ImageLoader.loadImage("/images/Hero.png");
        
        SpriteSheet spritesheet = new SpriteSheet(sprites);
        playerUp = new BufferedImage[3];
        playerDown = new BufferedImage[3];
        playerLeft = new BufferedImage[3];
        playerRight = new BufferedImage[3];
        playerIdle = new BufferedImage[3];
        
        //crop the spritesheet
        for (int i = 0; i < 3; i++){
            playerUp[i] = spritesheet.crop(i*24,0,24,32);
            playerRight[i] = spritesheet.crop(i*24,32,24,32);
            playerDown[i] = spritesheet.crop(i*24, 64, 24, 32);
            playerLeft[i] = spritesheet.crop(i*24,96,24,32);
            playerIdle[i] = spritesheet.crop(24, (i*32)+32, 24, 32);
        }
        //enemy
        Enemysprite = ImageLoader.loadImage("/images/Enemyspritesheet.png");
        
        SpriteSheet enemyspritesheet = new SpriteSheet(Enemysprite);
        EnemyLeft = new BufferedImage[10];
        
        //crop the spritesheet
        for(int i=0; i< 10; i++){
            EnemyLeft[i] = enemyspritesheet.crop(i*512, 0, 512, 512);
        }
        
        //ally
        AllySprite = ImageLoader.loadImage("/images/Allyspritesheet.png");
        
        SpriteSheet allyspritesheet = new SpriteSheet(AllySprite);
        AllyRight = new BufferedImage[10];
        
        //crop the spritesheet
        for(int i=0; i< 10; i++){
            AllyRight[i] = allyspritesheet.crop(i*512, 0, 512, 512);
        }
        
    }

}

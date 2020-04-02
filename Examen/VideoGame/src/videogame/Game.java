/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

/**
 *
 * @author Adolfo
 */
public class Game implements Runnable {
    private BufferStrategy bs;      // to have several buffers when displaying
    private Graphics g;             // to paint objects
    private Display display;        // to display in the game
    String title;                   // title of the window
    private int width;              // width of the window
    private int height;             // height of the window
    private Thread thread;          // thread to create the game
    private boolean running;        // to set the game
    private Player player;          // to use a player
    private LinkedList<Enemy> enemies;     //to use the enemy
    private Ball ball;        //to use the ball
    private KeyManager keyManager;  // to manage the keyboard
    private int score;              //keep score
    private int lives;              //player lives
    private int enemyCollisions;    //keep the times collision
    private boolean gameOver;       //game-over
    
    
    /**
     * to create title, width and height and set the game is still not running
     * @param title to set the title of the window
     * @param width to set the width of the window
     * @param height  to set the height of the window
     */
    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        running = false;
        keyManager = new KeyManager();
    }

    public Player getPlayer() {
        return player;
    }

    /**
     * To get the width of the game window
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get the height of the game window
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * To get the score of the game window
     * @return an <code>int</code> value with the score
     */
    public int getScore() {
        return score;
    }

    /**
     * To set the score
     * @param score of the game
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * To get the lives of the game window
     * @return an <code>int</code> value with the lives
     */
    public int getLives() {
        return lives;
    }

    /**
     * to set lives
     * @param lives of the player 
     */
    public void setLives(int lives) {
        this.lives = lives;
    }

    /**
     * To get the times the player have collision
     * @return an <code>int</code> value with the times collision
     */
    public int getEnemyCollisions() {
        return enemyCollisions;
    }

    /**
     * to set the times a player collision with an enemy
     * @param enemyCollisions of the player with an enemy 
     */
    public void setEnemyCollisions(int enemyCollisions) {
        this.enemyCollisions = enemyCollisions;
    }
    
    /**
     * To get if it is a gameOver
     * @return an <code>boolean</code> if is a gameOver
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * to set the boolean
     * @param gameOver if the reach a gameOver
     */
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
    
    
    
    /**
     * initializing the display window of the game
     */
    private void init() {
         display = new Display(title, getWidth(), getHeight());  
         Assets.init();
         //init the player, enemy and ally positions and list
         player = new Player(getWidth(), getHeight()-200, 1, 100, 100, this);
         enemies = new LinkedList();
         
         //set gameover false
         setGameOver(false);
         //set the collision to 0
         setEnemyCollisions(0);
         //set the lives random from 5-7
         int randomLives = (int)((Math.random()*3)+5);
         setLives(randomLives);
         //set the score to 0
         setScore(0);
         //((int)(Math.random()*b-a+1))+a b->upper limit a->lower limit
         //to have the number of enemies in the game
         int randomEnemy = (int)((Math.random()*3)+6);
         randomEnemy = 5;
         for(int i = 1; i <= 3; i++){
             for( int j = 1; j <= 12; j++){
                Enemy enemy = new Enemy(j * 50 + 30, 
                                i*50, 1, 50, 50, this);
                enemies.add(enemy);
             }
         }
         //https://youtu.be/SWYqp7iY_Tc

         //to have the number of allie in the game
         ball = new Ball((getWidth()/2-50), (getHeight()/2-50), 1, 100, 100, this);
         
         display.getJframe().addKeyListener(keyManager);
    }
    
    @Override
    public void run() {
        init();
        // frames per second
        int fps = 50;
        // time for each tick in nano segs
        double timeTick = 1000000000 / fps;
        // initializing delta
        double delta = 0;
        // define now to use inside the loop
        long now;
        // initializing last time to the computer time in nanosecs
        long lastTime = System.nanoTime();
        while (running) {
            // setting the time now to the actual time
            now = System.nanoTime();
            // acumulating to delta the difference between times in timeTick units
            delta += (now - lastTime) / timeTick;
            // updating the last time
            lastTime = now;
            
            // if delta is positive we tick the game
            if (delta >= 1) {
                tick();
                render();
                delta --;
            }
        }
        stop();
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }
    
    private void tick() {
        keyManager.tick();
        // avancing player with colision
        player.tick();
        //move the enemies
        for(Enemy enemy : enemies) {
            enemy.tick();
            
            //to detect if there is a collision with an enemy
            if (player.collision(enemy)) {
                setEnemyCollisions(getEnemyCollisions()+1);
                //change the number of collision and take away a life
                if(getEnemyCollisions() == 3){
                    setEnemyCollisions(0);
                    setLives(getLives()-1);
                }
                //set the gameOver
                if (getLives() == 0){
                    setGameOver(true);
                }
                //restart the enemies
                enemy.setX((int) (Math.random() * getWidth() - 100));
                enemy.setY((int)(Math.random()*getHeight())-250);
                //play the sound unless is gameOver
                if(!isGameOver()){
                    Assets.enemysound.play();
                }
            }
        }
        
        //movement of the ball
        ball.tick();
        
        
    }
        
    private void render() {
        // get the buffer strategy from the display
        bs = display.getCanvas().getBufferStrategy();
        /* if it is null, we define one with 3 buffers to display images of
        the game, if not null, then we display every image of the game but
        after clearing the Rectanlge, getting the graphic object from the 
        buffer strategy element. 
        show the graphic and dispose it to the trash system
        */
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        }
        else
        {
            g = bs.getDrawGraphics();
            g.drawImage(Assets.background, 0, 0, width, height, null);
            player.render(g);
            //show the enemies
            for (Enemy enemy : enemies) {
                enemy.render(g);
            }
            //Show the ball in the game
            ball.render(g);
            //show the score and lives
            bs.getDrawGraphics().drawString("Score: " +String.valueOf(getScore()), 20, 50);
            bs.getDrawGraphics().drawString("Lives: "+String.valueOf(getLives()), 20, 20);
            //bs.getDrawGraphics().setColor(Color.red);
            
            
            //change to a gameOver screen
            if(gameOver){
                g.drawImage(Assets.gameOver, 0, 0, width, height, null);
            }
            
            bs.show();
            g.dispose();
        }
       
    }
    
    
    /**
     * setting the thead for the game
     */
    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }
    
    /**
     * stopping the thread
     */
    public synchronized void stop() {
        if (running) {
            running = false;
            try {
                thread.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }           
        }
    }
    


}

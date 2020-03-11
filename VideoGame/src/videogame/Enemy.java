/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Graphics;

/**
 * class of the enemies
 * @author Adolfo
 */
public class Enemy extends Item{

    private int direction;
    private Game game;
    private boolean floor;
    private int score;
    private int speed;
    private boolean init=true;
    private int floorCoins;
    
    //constructor
    public Enemy(int x, int y, int direction, int width, int height, Game game) {
        super(x, y, width, height);
        this.direction = direction;
        this.game = game;
        
    }
    
    //setters and getters

    public int getDirection() {
        return direction;
    }


    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean isFloor() {
        return floor;
    }

    public void setFloor(boolean floor) {
        this.floor = floor;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public int getFloorCoins() {
        return floorCoins;
    }

    public void setFloorCoins(int floorCoins) {
        this.floorCoins = floorCoins;
    }


    @Override
    public void tick() {
        //initialization
        if(isInit()){
            setInit(false);
            setFloor(false);
            setSpeed(1);
            setFloorCoins(0);
        }
        int random = (int)((Math.random()*3)+3);  
        setSpeed(random);
        // moving enemy
        setY(getY()+getSpeed());
        
        // reset x position and y position if colision
        if (getX() + 60 >= game.getWidth()) {
            setX(game.getWidth() - 60);
        }
        else if (getX() <= -30) {
            setX(-30);
        }
        
        if (getY() + 80 >= game.getHeight()) {
            setY(game.getHeight() - 80);
            setY(((int)(Math.random()*game.getHeight()))-350);
            setX((int)(Math.random()*game.getWidth()-100));
            //setSpeed(getSpeed()+1);
            //setFloorCoins(getFloorCoins() + 1);
        }
        /*
        else if (getY() <= -20) {
            setY(-20);
        }
        */
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.enemy, getX(), getY(), getWidth(), getHeight(), null);
    }
}